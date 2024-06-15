/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kierjohn.testgui;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import org.json.JSONException;

/**
 * @author LENOVO
 */
public class APIHandler {

  protected static Quiz callAPI(int qCount, int qCatID, String qDiff, String qType) throws IOException, APIException {
    // qCatID is an number in range from 9 to 32, inclusive
    // qDiff can be {"easy", "medium", or "hard"}
    // qType can be {"any", "multiple", or "boolean"}
    //      if "any", the url tag is ommitted
    // question count of response is capped at 50

    String urlString = STR."""
                https://opentdb.com/api.php?\
                amount=\{qCount}\
                &category=\{qCatID}\
                &difficulty=\{qDiff}\
                &type=\{(qType)}\
                &encode=url3986""";

    System.out.println("Calling API URL: " + urlString);

    JSONObject response = getJsonData(callURL(urlString));
    JSONArray questions = response.getJSONArray("results");
    Quiz responseQuiz = resultsArrayToQuiz(questions, new Quiz(), "api");

    System.out.println("Successfully performed API call.");

    return responseQuiz;
  }

  private static String callURL(String urlString) throws IOException {
    URL url = new URL(urlString);
    StringBuilder response = new StringBuilder();

    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestMethod("GET");

    if (con.getResponseCode() == HttpURLConnection.HTTP_OK) {
      BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
      String inputLine;
      while ((inputLine = in.readLine()) != null) {
        response.append(inputLine);
      }

      in.close();
    }

    return response.toString();
  }

  private static JSONObject getJsonData(String returnedData) throws APIException {
    JSONObject response = new JSONObject(returnedData);

    switch (response.getInt("response_code")) {
      case 1 -> throw new APIQuestionCountException("Not enough questions for selected category and difficulty.");
      case 2 -> throw new APIInvalidParametersException("Invalid parameters.");
      case 3 -> throw new APISessionInvalidTokenException("Invalid session token.");
      case 4 -> throw new APISessionEmptyTokenException("Resetting session token is required.");
      case 5 -> throw new APIMaxAttemptsException("Too many requests. Try again later.");
    }

    return response;
  }

  protected static Quiz jsonObjectToQuiz(JSONObject jsonObj, String mode) {
    return resultsArrayToQuiz(jsonObj.getJSONArray("results"), new Quiz(jsonObj.getString("name")), mode);
  }

  private static Quiz resultsArrayToQuiz(JSONArray jsonArray, Quiz quiz, String mode) {
    for (Object obj : jsonArray) {
      JSONObject qJsonObj = (JSONObject) obj;

      String question = decodeURL3986(qJsonObj.getString("question"));
      String answer = decodeURL3986(qJsonObj.getString("correct_answer"));
      String qType = qJsonObj.getString("type");
      String category, difficulty;
      try {
        category = decodeURL3986(qJsonObj.getString("category"));
        difficulty = qJsonObj.getString("difficulty");
      } catch (JSONException ex) {
          System.out.println("Seems to be using an older version of the json file. Skipping missing tags...");
          category = difficulty = "";
      }

      switch (qType) {
        case "multiple" ->
                quiz.add(new Question(question, answer, getIncorrectAnswers(qJsonObj), category, difficulty));
        case "boolean" -> quiz.add(new Question(question, answer.equals("true"), category, difficulty));
        case "identification" -> quiz.add(new Question(question, answer, category, difficulty));
      }
    }

    return quiz;
  }

  private static String[] getIncorrectAnswers(JSONObject qJsonObj) {
    String[] incorrectAnswers = new String[3];
    JSONArray providedIncorrectAnswers = qJsonObj.getJSONArray("incorrect_answers");

    for (int j = 0; j < 3; j++) {
      incorrectAnswers[j] = decodeURL3986(providedIncorrectAnswers.getString(j));
    }

    return incorrectAnswers;
  }

  protected static String[] getQuestionCategories() throws IOException {
    JSONObject call = new JSONObject(callURL("https://opentdb.com/api_category.php"));
    JSONArray result = call.getJSONArray("trivia_categories");
    int catCount = result.length();
    String[] categories = new String[catCount];

    for (int i = 0; i < catCount; i++) {
      categories[i] = result.getJSONObject(i).getString("name");
    }

    return categories;
  }

  protected static int getQuestionCount(int qCatID, String qDiff) throws IOException {
    JSONObject result = new JSONObject(callURL("https://opentdb.com/api_count.php?category=" + qCatID));
    JSONObject catQCount = new JSONObject(result.getString("category_question_count"));

    return Integer.parseInt(catQCount.getString(qDiff));
  }

  protected static JSONObject quizToJson(Quiz quiz) {
    JSONObject quizJObj = new JSONObject();
    JSONArray questionsJArr = new JSONArray();

    quizJObj.put("name", quiz.getName());

    for (Question question : quiz.getQuestions()) {
      JSONObject questionJsonObj = new JSONObject();

      questionJsonObj.put("question", question.getQuestion());
      questionJsonObj.put("correct_answer", question.getAnswer());

      if (question.getType().equals("multiple")) {
        questionJsonObj.put("incorrect_answers", new JSONArray(question.getIncorrectAnswers()));
      }

      questionJsonObj.put("type", question.getType());
      questionsJArr.put(questionJsonObj);
    }

    quizJObj.put("results", questionsJArr);

    return quizJObj;
  }

  protected static void writeQuizToFile(Quiz quiz, File file) throws IOException {
    try (FileWriter f = new FileWriter(file)) {
      f.write(APIHandler.quizToJson(quiz).toString());
    }
  }

  private static String decodeURL3986(String str) {
    return URLDecoder.decode(str, StandardCharsets.UTF_8);
  }

  public static class APIException extends Exception {

    public APIException() {
      super("Exception in APIHandler");
    }

    public APIException(String message) {
      super(message);
    }
  }

  public static class APIMaxAttemptsException extends APIException {

    public APIMaxAttemptsException(String message) {
      super(message);
    }
  }

  public static class APIQuestionCountException extends APIException {

    public APIQuestionCountException(String message) {
      super(message);
    }
  }

  public static class APIInvalidParametersException extends APIException {

    public APIInvalidParametersException(String message) {
      super(message);
    }
  }

  public static class APISessionInvalidTokenException extends APIException {

    public APISessionInvalidTokenException(String message) {
      super(message);
    }
  }

  public static class APISessionEmptyTokenException extends APIException {

    public APISessionEmptyTokenException(String message) {
      super(message);
    }
  }

  public static class APIConnectionException extends APIException {

    public APIConnectionException(String message) {
      super(message);
    }
  }

}
