package com.example.yasirnazir.clearscore.models;

/**
 * Created by yasirnazir on 3/14/18.
 */

public class CreditReportInfo {

    private int score;
    private int minScoreValue;
    private int maxScoreValue;

    public CreditReportInfo(int score, int minScoreValue, int maxScoreValue) {
        this.score = score;
        this.minScoreValue = minScoreValue;
        this.maxScoreValue = maxScoreValue;
    }

    public int getScore() {
        return score;
    }

    public int getMinScoreValue() {
        return minScoreValue;
    }

    public int getMaxScoreValue() {
        return maxScoreValue;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setMinScoreValue(int minScoreValue) {
        this.minScoreValue = minScoreValue;
    }

    public void setMaxScoreValue(int maxScoreValue) {
        this.maxScoreValue = maxScoreValue;
    }
}
