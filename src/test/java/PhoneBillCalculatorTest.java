import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;
import uk.gov.dwp.BillCalculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

public class PhoneBillCalculatorTest {

    @Test
    @DisplayName("Given a single phone log, return total cost")
    public void givenASinglePhoneLogReturnTotalCost(){
        BillCalculator billCalculator = new BillCalculator();
        int result = billCalculator.calculateBill("00:01:07,400-234-090");
        assertEquals("Should return 201 as total cost",201, result);
    }

    @Test
    @DisplayName("Given multiple phone logs, return the total cost")
    public void givenMultiplePhoneLogsReturnTotalCost(){
        BillCalculator billCalculator = new BillCalculator();
        int result = billCalculator.calculateBill("00:01:07,400-234-090\n00:05:01,701-080-080\n00:05:00,400-234-090");
        assertEquals("Should return 900 as total cost", 900, result);
    }

    @Test
    @DisplayName("Given an empty phone log, calculateBill should throw an exception")
    public void givenAnEmptyPhoneLogThrowAnExpection(){
        BillCalculator billCalculator = new BillCalculator();
        assertThrows(RuntimeException.class, () -> {
            billCalculator.calculateBill("");
        });
    }

//    @Test
//    @DisplayName("Given an unrecognised rateId, getRate should return null")
//    public void givenAnUnrecgonisedRateIdGetRateReturnsNull(){
//        BillCalculator billCalculator = new BillCalculator();
//        RateData rateData = new RateData();
//        ChargeRateInterface chargeRateInterface = Mockito.mock(ChargeRateInterface.class);
//        when(chargeRateInterface.getRate(1)).thenReturn(null);
//    }

}
