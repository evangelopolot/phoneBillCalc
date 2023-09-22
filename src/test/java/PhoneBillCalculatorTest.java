import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import uk.gov.dwp.BillCalculator;

import static org.junit.Assert.assertEquals;

public class PhoneBillCalculatorTest {
    @Test
    @DisplayName("Given a single phone log, return total cost")
    public void givenASinglePhoneLogReturnTotalCost(){
        BillCalculator billCalculator = new BillCalculator();
        int result = billCalculator.calculateBill("00:01:07,400-234-090");
        assertEquals("Should return 201 as total cost",201, result);
    }

}
