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
    private int callAttempts;
    protected static int maxCallAttempts = 25;

    public APIHandler() {
        callAttempts = 0;
    }
    
    protected void callAPI(int qCount, int qCatID, String qDiff) throws IOException, MaxAPICallAttemptException {
        // qCatID is an number in range from 9 to 32, inclusive
        // qDiff can be {"easy", "medium", or "hard"}
        // qType can be {"any", "multiple", or "boolean"}
        //      if "any", the url tag is ommitted

        StringBuilder response = new StringBuilder();
        String urlString = "https://opentdb.com/api.php?"
                + "amount=" + qCount
                + "&category=" + qCatID
                + "&difficulty=" + qDiff
                + "&type=multiple&encode=url3986";
        System.out.println("Calling API URL: " + urlString);
        try {
            URL url = new URL(urlString);
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
            resultsJsonArray = new JSONObject(response.toString()).getJSONArray("results");
            this.qCount = qCount;
            System.out.println("Successful API call. Saving result...");
        } catch (IOException e) {
            System.out.println(callAttempts + " / " + maxCallAttempts);
            if (callAttempts++ == maxCallAttempts) {
                throw new MaxAPICallAttemptException();
            }
            throw new IOException("Something went wrong.");
        }
    }

    protected void setAPIResultsToQuiz(Quiz quiz) {
        JSONObject qJsonObj;
        JSONArray providedIncorrectAnswers;
        String[] incorrectAnswers;

        System.out.println("Initializing quiz...");
        for (int i = 0; i < qCount; i++) {
            qJsonObj = resultsJsonArray.getJSONObject(i);
            providedIncorrectAnswers = qJsonObj.getJSONArray("incorrect_answers");
            incorrectAnswers = new String[3];
            for (int j = 0; j < 3; j++) {
                incorrectAnswers[j] = decodeURL3986((String) providedIncorrectAnswers.get(j));
            }
            quiz.addQuestion(
                    decodeURL3986(qJsonObj.getString("question")),
                    decodeURL3986(qJsonObj.getString("correct_answer")),
                    incorrectAnswers,
                    decodeURL3986(qJsonObj.getString("category")),
                    qJsonObj.getString("difficulty")
            );
            System.out.println("Successfully added question: \"" + decodeURL3986(qJsonObj.getString("question")) + "\".");
        }
    }

    private String decodeURL3986(String str) {
        try {
            return URLDecoder.decode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            // not gonna happen, as the encoding is hard-coded
            e.printStackTrace();
            return str;
        }
    }

    protected int getCallAttempts() {
        return callAttempts;
    }

    public class MaxAPICallAttemptException extends Exception {
        
        public MaxAPICallAttemptException() {
            super("Maximum tries to call API exceeded.");
        }

        public MaxAPICallAttemptException(String message) {
            super(message);
        }
    }
}
