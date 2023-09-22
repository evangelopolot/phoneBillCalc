package uk.gov.dwp;

public class BillCalculator {
    public int calculateBill(String phoneLog) {
        String[] phoneLogDetails = phoneLog.split(",");

        String[] callDuration = phoneLogDetails[0].split(":");
        int hours = Integer.parseInt(callDuration[0]);
        int minutes = Integer.parseInt(callDuration[1]);
        int seconds = Integer.parseInt(callDuration[2]);

        int callDurationInSeconds = (hours * 3600) + (minutes * 60) + seconds;

        return callDurationInSeconds *  3;
    }
}
