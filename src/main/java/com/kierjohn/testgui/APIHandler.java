/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kierjohn.testgui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author LENOVO
 */
public class APIHandler {

    private int qCount;
    private JSONArray resultsJsonArray;
    private int callReconnectAttempts;
    protected static int maxCallReconnectAttempts = 25;

    public APIHandler() {
        callReconnectAttempts = 0;
    }

    protected Quiz callAPI(int qCount, int qCatID, String qDiff, String qType) throws IOException, APIException {
        // qCatID is an number in range from 9 to 32, inclusive
        // qDiff can be {"easy", "medium", or "hard"}
        // qType can be {"any", "multiple", or "boolean"}
        //      if "any", the url tag is ommitted
        JSONObject response;
        String urlString = STR."""
                https://opentdb.com/api.php?\
                amount=\{qCount}\
                &category=\{qCatID}\
                &difficulty=\{qDiff}\
                &type=\{(qType.equals("identification") ? "multiple" : qType)}\
                &encode=url3986""";
        System.out.println("Calling API URL: " + urlString);
        try {
            response = new JSONObject(APIHandler.callURL(urlString));
            switch ((Integer) response.get("response_code") + "") {
                case "1" ->
                    throw new APIQuestionCountException("Not enough questions for selected category and difficulty.");
                case "2" ->
                    throw new APIInvalidParametersException("Invalid parameters.");
                case "3" ->
                    throw new APISessionInvalidTokenException("Invalid session token.");
                case "4" ->
                    throw new APISessionEmptyTokenException("Resetting session token is required.");
                case "5" ->
                    throw new APIMaxAttemptsException("Too many requests. Try again later.");
            }
            if (callReconnectAttempts++ == maxCallReconnectAttempts) {
                throw new APIConnectionException("Something is wrong with your internet connectivity. Please try again later.");
            }
            resultsJsonArray = response.getJSONArray("results");
            this.qCount = qCount;
            System.out.println("Successful API call. Result:" + resultsJsonArray.toString(1));
        } catch (IOException e) {
            System.out.println(callReconnectAttempts + " / " + maxCallReconnectAttempts);
            throw new IOException("Something went wrong.");
        }
        return getResultToQuiz();
    }

    private Quiz getResultToQuiz() {
        return jsonArrayToQuiz(resultsJsonArray, new Quiz("API Quiz"));
    }
    
    protected static Quiz jsonObjectToQuiz(JSONObject jsonObj) {
        return jsonArrayToQuiz(jsonObj.getJSONArray("results"), new Quiz(jsonObj.getString("name")));
    }
    
    private static Quiz jsonArrayToQuiz(JSONArray jsonArr, Quiz quiz) {
        JSONObject qJsonObj;
        JSONArray providedIncorrectAnswers;
        String question, answer, qType; // , category, difficulty;

        System.out.println("Initializing quiz...");
        for (int i = 0; i < jsonArr.length(); i++) {
            qJsonObj = jsonArr.getJSONObject(i);
            question = decodeURL3986(qJsonObj.getString("question"));
            answer = decodeURL3986(qJsonObj.getString("correct_answer"));
            qType = qJsonObj.getString("type");
//            category = decodeURL3986(qJsonObj.getString("category"));
//            difficulty = qJsonObj.getString("difficulty");

            switch (qType) {
                case "identification" ->
                    quiz.addQuestion(new Question(question, answer)); //, category, difficulty));
                case "multiple" -> {
                    String[] incorrectAnswers;
                    providedIncorrectAnswers = qJsonObj.getJSONArray("incorrect_answers");
                    incorrectAnswers = new String[3];
                    for (int j = 0; j < 3; j++) {
                        incorrectAnswers[j] = decodeURL3986(providedIncorrectAnswers.getString(j));
                    }
                    quiz.addQuestion(new Question(question, answer, incorrectAnswers)); //, category, difficulty));
                }
                case "boolean" ->
                    quiz.addQuestion(new Question(question, answer.equals("True"))); //, category, difficulty));
            }
            System.out.println("Successfully added question: #" + i + ": \"" + decodeURL3986(qJsonObj.getString("question")) + "\".");
        }
        return quiz;
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

    protected static String[] getQuestionCategories() throws IOException {
        JSONArray result = new JSONObject(APIHandler.callURL("https://opentdb.com/api_category.php")).getJSONArray("trivia_categories");
        int catCount = result.length();
        String[] categories = new String[catCount];
        for (int i = 0; i < catCount; i++) {
            categories[i] = result.getJSONObject(i).getString("name");
        }
        return categories;
    }

    protected static int getQuestionCount(int qCatID, String qDiff) throws IOException {
        JSONObject result = new JSONObject(APIHandler.callURL("https://opentdb.com/api_count.php?category=" + qCatID));
        JSONObject catQCount = new JSONObject(result.getString("category_question_count"));
        return Integer.parseInt(catQCount.getString(qDiff));
    }

    protected static JSONObject quizToJson(Quiz quiz) {
        JSONObject quizJObj = new JSONObject();
        quizJObj.put("name", quiz.getName());
        JSONArray questionsJArr = new JSONArray();
        for (Question question : quiz.getQuestions()) {
            JSONObject qJObj = new JSONObject();
            qJObj.put("question", question.getQuestion());
            qJObj.put("correct_answer", question.getAnswer());
            if (question.getType().equals("multiple")) {
               qJObj.put("incorrect_answers", new JSONArray(question.getWrongChoices()));
            }
            qJObj.put("type", question.getType());
            questionsJArr.put(qJObj);
        }
        quizJObj.put("results", questionsJArr);
        return quizJObj;
    }

    private static String decodeURL3986(String str) {
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return str;
        }
    }

    protected int getCallAttempts() {
        return callReconnectAttempts;
    }

    public class APIException extends Exception {

        public APIException() {
            super("Exception in APIHandler");
        }

        public APIException(String message) {
            super(message);
        }
    }

    public class APIMaxAttemptsException extends APIException {

        public APIMaxAttemptsException(String message) {
            super(message);
        }
    }

    public class APIQuestionCountException extends APIException {

        public APIQuestionCountException(String message) {
            super(message);
        }
    }

    public class APIInvalidParametersException extends APIException {

        public APIInvalidParametersException(String message) {
            super(message);
        }
    }

    public class APISessionInvalidTokenException extends APIException {

        public APISessionInvalidTokenException(String message) {
            super(message);
        }
    }

    public class APISessionEmptyTokenException extends APIException {

        public APISessionEmptyTokenException(String message) {
            super(message);
        }
    }

    public class APIConnectionException extends APIException {

        public APIConnectionException(String message) {
            super(message);
        }
    }

}
