package com.ashandilya.citizenfeedbacksystem;

public class Feedback {

    private String feedbackText;
    private String feedbackCategory;

    public Feedback(){

    }

    public Feedback(String feedbackText, String feedbackCategory) {
        this.feedbackText = feedbackText;
        this.feedbackCategory = feedbackCategory;
    }

    public String getFeedbackText(String trim) {
        return feedbackText;
    }

    public String getFeedbackCategory() {
        return feedbackCategory;
    }
}
