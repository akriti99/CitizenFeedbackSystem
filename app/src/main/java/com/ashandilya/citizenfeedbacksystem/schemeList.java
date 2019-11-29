package com.ashandilya.citizenfeedbacksystem;

public class schemeList {

   private int scheme_ID;
   private String scheme_Name, scheme_Description;
   private int scheme_Image;

    schemeList(int scheme_ID, String scheme_Name, String scheme_Description, int scheme_Image) {
        this.scheme_ID = scheme_ID;
        this.scheme_Name = scheme_Name;
        this.scheme_Description = scheme_Description;
        this.scheme_Image = scheme_Image;
    }

    public int getScheme_ID() {
        return scheme_ID;
    }

    String getScheme_Name() {
        return scheme_Name;
    }

    String getScheme_Description() {
        return scheme_Description;
    }

    int getScheme_Image() {
        return scheme_Image;
    }
}
