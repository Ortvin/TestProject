package org.example.Utils;

public class NotNullConverter {

    String value;

    public NotNullConverter() {

    }

    public String notNullField(String value){
        if (value == null){
            return "";
        }
        return value.trim();
    }
}
