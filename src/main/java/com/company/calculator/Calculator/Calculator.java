package com.company.calculator.Calculator;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
@Component
public class Calculator {

    public  String brackets(String inputstring) {
        if (inputstring.contains(")")) {
            int baseIndex = inputstring.indexOf(")");
            int openingBracketIndex = baseIndex - 1;
            while (!String.valueOf(inputstring.charAt(openingBracketIndex)).equals("(")) {
                openingBracketIndex--;
            }
            String input = inputstring.substring(openingBracketIndex + 1, baseIndex);
            input = "$" + input + "$";
            String answerFromCalculator = calculator(input);
            return brackets(inputstring.substring(0, openingBracketIndex) + answerFromCalculator.substring(1, answerFromCalculator.length() - 1) + inputstring.substring(baseIndex + 1, inputstring.length()));
        } else return calculator("$" + inputstring + "$");
    }

    public  String calculator(String inputString) {

        try {
            Integer.parseInt(inputString.substring(1, inputString.length() - 1));
            return inputString;

        } catch (Exception e) {
            inputString = doubleSignOperator(inputString);
            String operation = "";

            int answer = 0;
            int baseIndex = 0;

            String[] priority = {"/", "*", "-", "+"};
            int _counter = 0;
            for (String string : inputString.split("")) {
                if (string.equals("-"))
                    _counter++;
            }
            if (String.valueOf(inputString.charAt(1)).equals("-") && _counter < 2) {
                priority[2] = "+";
                priority[3] = "-";
            }
            for (String prioritySymbol : priority) {
                if (inputString.contains(prioritySymbol)) {
                    baseIndex = inputString.indexOf(prioritySymbol);
                    int counter = 1;
                    if (baseIndex == 1 && prioritySymbol.equals("-") && inputString.substring(baseIndex + 1, inputString.length()).contains("-")) {
                        for (int i = baseIndex + 1; i < inputString.length(); i++) {
                            if (String.valueOf(inputString.charAt(i)).equals("-"))
                                counter++;
                            if (counter == 2) {
                                baseIndex = i;
                                break;
                            }
                        }
                    }
                    break;
                }
            }
            int leftPointer = baseIndex - 1;
            int rightPointer = baseIndex + 2;
            //now the index of the symbol is verified then we have to find the leftnumber and the rightnumber.
            while (!Arrays.asList(priority).contains(String.valueOf(inputString.charAt(rightPointer))) && !String.valueOf(inputString.charAt(rightPointer)).equals("$")) {
                rightPointer++;
            }
            int rightNumber = Integer.parseInt(inputString.substring(baseIndex + 1, rightPointer));
            while (!Arrays.asList(priority).contains(String.valueOf(inputString.charAt(leftPointer))) && !String.valueOf(inputString.charAt(leftPointer)).equals("$")) {
                leftPointer--;
            }
            int leftNumber = 0;
            if (leftPointer > 0 && String.valueOf(inputString.charAt(leftPointer - 1)).equals("$")) {
                leftNumber = Integer.parseInt(inputString.substring(leftPointer, baseIndex));
                leftPointer--;
            } else
                leftNumber = Integer.parseInt(inputString.substring(leftPointer + 1, baseIndex));

            answer = getAnswer(inputString, answer, baseIndex, rightNumber, leftNumber);
            inputString = inputString.substring(0, leftPointer + 1) + answer +

                    inputString.substring(rightPointer, inputString.length());
            return calculator(inputString);
        }
    }


    private  int getAnswer(String inputString, int answer, int baseIndex, int rightNumber, int leftNumber) {
        switch (String.valueOf(inputString.charAt(baseIndex))) {
            case "+":
                answer = leftNumber + rightNumber;
                break;
            case "-":
                answer = leftNumber - rightNumber;
                break;
            case "*":
                answer = leftNumber * rightNumber;
                break;
            case "/":
                answer = leftNumber / rightNumber;
                break;
        }
        return answer;
    }

    public  String doubleSignOperator(String inputString) {

        if (inputString.contains("++"))
            inputString = inputString.replace("++", "+");
        if (inputString.contains("--"))
            inputString = inputString.replace("--", "+");
        if (inputString.contains("+-"))
            inputString = inputString.replace("+-", "-");
        if (inputString.contains("-+"))
            inputString = inputString.replace("-+", "-");
        return inputString;

    }

    // this methoid will print the list
    public  void printhestring(List<String> stringList) {
        for (String string : stringList) {
            System.out.println(string);
        }
    }


   

}