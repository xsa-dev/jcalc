package com.xsa.calculator;

public class Main {
    public static void main(String[] args) throws Exception {
        Backend.inputDialogText();
        System.out.println(Backend.parseStringAndOutResults(Backend.readInputRow()));
    }
}
