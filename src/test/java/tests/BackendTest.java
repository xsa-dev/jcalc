package tests;

import com.xsa.calculator.Backend;
import org.junit.jupiter.api.Test;

class BackendTest {

    @Test
    void parseStringAndOutResultsArabicPositive() {
        try {
            Backend.validateInputParams(1,2, Backend.NumbersTypes.arabic);
        } catch (Exception e) {
            e.printStackTrace();
            assert false;
        }
    }

    @Test
    void parseStringAndOutResultsArabicNegative() {
        try {
            Backend.validateInputParams(11,2, Backend.NumbersTypes.arabic);
        } catch (Exception e) {
            assert e.getMessage().contains("Входящие данные не могут быть меньше 1 и больше 10");
        }
    }

    @Test
    void parseStringAndOutResultsRomanPositive() {
        try {
            Backend.validateInputParams(1,9, Backend.NumbersTypes.romain);
        } catch (Exception e) {
            e.printStackTrace();
            assert false;
        }
    }

    @Test
    void parseStringAndOutResultsRomanNegative() {
        try {
            Backend.validateInputParams(11,2, Backend.NumbersTypes.romain);
        } catch (Exception e) {
            assert e.getMessage().contains("Входящие данные не могут быть меньше I и больше X");
        }
    }

    @Test
    void calculateFunctionArabicExamplePlus() throws Exception {
        String result = Backend.calculateFunction(1,2, "+");
        assert result.equals("3");
    }

    @Test
    void calculateFunctionArabicExampleMultiply() throws Exception {
        String result = Backend.calculateFunction(1,2, "*");
        assert result.equals("2");
    }

    @Test
    void calculateFunctionArabicExampleDelim() throws Exception {
        String result = Backend.calculateFunction(5,1, "/");
        assert result.equals("5");
    }

    @Test
    void calculateFunctionArabicExampleMinus() throws Exception {
        String result = Backend.calculateFunction(5,1, "-");
        assert result.equals("4");
    }

    @Test
    void parseArabicStringAndOutResultsMultiply() throws Exception {
        String result = Backend.parseStringAndOutResults("1 * 1");
        assert result.equals("1");
    }

    @Test
    void parseRomainStringAndOutResultsMultiply() throws Exception {
        String result = Backend.parseStringAndOutResults("V * V");
        assert result.equals("XXV");
    }

    @Test
    void parseRomainStringAndOutResultsMinusZero() throws Exception {
        String result = Backend.parseStringAndOutResults("V - V");
        assert result.equals("N");
    }

    @Test
    void parseRomainStringAndOutResultsMinus() throws Exception {
        String result = Backend.parseStringAndOutResults("V - II");
        assert result.equals("III");
    }

    @Test
    void parseRomainStringAndOutResultsPlus() throws Exception {
        String result = Backend.parseStringAndOutResults("V + I");
        assert result.equals("VI");
    }

    @Test
    void parseRomainStringLessZeroResult() throws Exception {
        try {
            String result = Backend.parseStringAndOutResults("V - VII");
        } catch (Exception e) {
            assert e.getMessage().contains("Римские значения не могут быть меньше 0");
        }
    }

}