package com.company.calculator.Calculator;

import com.fasterxml.jackson.annotation.JsonAnyGetter;


public class Input {
    public String getInputString() {
        return inputString;
    }

    public void setInputString(String inputString) {
        this.inputString = inputString;
    }

    String inputString;
}
