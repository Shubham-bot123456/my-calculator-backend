package com.company.calculator.Calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RequestMapping("/")
@RestController
@SpringBootApplication
public class CalculatorApplication {
    @Autowired
    Calculator calculator;

    public static void main(String[] args) {
        SpringApplication.run(CalculatorApplication.class, args);
    }


    @GetMapping("/calculate/{string}")
    public String calculate(@PathVariable String string) {
        try {
            String immidiateAnswer = calculator.brackets(string);
            return immidiateAnswer.substring(1, immidiateAnswer.length() - 1);
        } catch (Exception e) {
            return "syntax error";
        }
    }
}
