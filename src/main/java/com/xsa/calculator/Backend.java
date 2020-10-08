package com.xsa.calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Backend {
    public static String readInputRow() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return reader.readLine();
    }

    public enum NumbersTypes {
        romain,
        arabic
    }

    public static String parseStringAndOutResults(String input) throws Exception {
        String output;

        try {
            String[] values;
            values = input.split(" ");
            String operator = values[1];
            try {
                int a = Integer.parseInt(values[0]);
                int b = Integer.parseInt(values[2]);
                validateInputParams(a, b, NumbersTypes.arabic);
                output = calculateFunction(a, b, operator);
                return output;
            } catch (Exception e) {
                if (e.getMessage().contains("Входящие данные не могут быть меньше")) {
                    throw e;
                } else {
                    try {
                        int a = ArabConverter.romanToArabic(values[0]);
                        int b = ArabConverter.romanToArabic(values[2]);

                        validateInputParams(a, b, NumbersTypes.romain);
                        String int_values = calculateFunction(a, b, operator);
                        boolean isNegative = (Integer.parseInt(int_values) < 0);
                        if (isNegative) {
                            throw new Exception("Римские значения не могут быть меньше 0.");
                        } else {
                            output = ArabConverter.arabicToRoman(Integer.parseInt(int_values));
                        }
                        return output;
                    } catch (IndexOutOfBoundsException ie) {
                        throw new Exception("Разобранная строка отлична от ожидаемой: " + ie);
                    } catch (Exception ee) {
                        throw new Exception("Не удалось выполнить вычисления в римских цифрах из-за ошибки: " + ee);
                    }
                }
            }
        } catch (IndexOutOfBoundsException e) {
            throw new Exception("Строка имеет формат отличный от заданного в условиях. Не удалось разобрать значения.");
        } catch (Exception e) {
            throw new Exception("Вычисления прерваны из-за ошибки: " + e);
        }
    }

    public static void validateInputParams(int a, int b, NumbersTypes type) throws Exception {
        boolean validateA = (a > 10) | (a < 1);
        boolean validateB = (b > 10) | (b < 1);

        if (validateA || validateB) {
            if (type.equals(NumbersTypes.arabic)) {
                throw new Exception(String.format("Входящие данные не могут быть меньше 1 и больше 10! Переданы: a: %d, b: %d", a, b));
            } else if (type.equals(NumbersTypes.romain)) {
                throw new Exception(String.format("Входящие данные не могут быть меньше I и больше X! Переданы: a: %s, b: %s", ArabConverter.arabicToRoman(a), ArabConverter.arabicToRoman(b)));
            }
        }
    }

    public static String calculateFunction(int a, int b, String operator) throws Exception {
        String result;
        if ("+".equals(operator)) {
            result = Integer.toString(a + b);
        } else if ("-".equals(operator)) {
            result = Integer.toString(a - b);
        } else if ("/".equals(operator)) {
            result = Integer.toString(a / b);
        } else if ("*".equals(operator)) {
            result = Integer.toString(a * b);
        } else {
            throw new Exception("Не получилось определить оператора для символа: " + operator);
        }

        return result;
    }

    public static void inputDialogText() {
        System.out.println("~@~@~@~@~@~@~@~@~@~@~@~@~@~@~");
        System.out.println("Примеры в арабских: 1 + 1, 2 * 4, 5 / 1, 5 - 1");
        System.out.println("Примеры в римских: V - I, X * II, X / VII, X + X");
        System.out.println("Пожалуйста введите строку для калькулятора:");
    }
}
