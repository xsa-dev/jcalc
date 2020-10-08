import com.xsa.calculator.ArabConverter;
import org.junit.jupiter.api.Test;

class ArabConverterTest {

    @Test
    void romanToArabic() {
        String roman2018 = "MMXVIII";
        int result = ArabConverter.romanToArabic(roman2018);
        assert (result == 2018);
    }

    @Test
    void arabicToRoman() {
        int arabic = 5;
        String result = ArabConverter.arabicToRoman(arabic);
        assert (result .equals( "V"));
    }

    @Test
    void main() {
        romanToArabic();
        arabicToRoman();
    }
}