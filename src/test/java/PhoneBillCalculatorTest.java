import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import uk.gov.dwp.BillCalculator;

import static org.junit.Assert.assertEquals;

public class PhoneBillCalculatorTest {
    @Test
    @DisplayName("Given a single phone log, return call duration in seconds")
    public void givenASinglePhoneLogReturnCallDurationInSeconds(){
        BillCalculator billCalculator = new BillCalculator();
        int result = billCalculator.calculateBill("00:01:07,400-234-090");
        assertEquals("Should return 67 as call duration in seconds",67, result);
    }
}
