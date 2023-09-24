package uk.gov.dwp;

import java.util.HashMap;

public class BillCalculator {
    private final HashMap<String, Integer> logs = new HashMap<>();
    private int longestCallDuration = 0;
    private String numberWithLongestCallDuration = "";
    private static final int HOUR_IN_SECONDS = 3600;
    private static final int MINUTE_IN_SECONDS = 60;
    private static final int RATE_FOR_SECONDS = 3;
    private static final int RATE_FOR_MINUTES = 150;

    public int calculateBill(final String phoneLogInput) {

        if(phoneLogInput.isEmpty()){
            throw new RuntimeException("Empty phone log");
        }

        String[] phoneLogLines = phoneLogInput.split("\n");

        for(String phoneLogLine : phoneLogLines){
            processPhoneLog(phoneLogLine);
        }
        freePromotion(phoneLogLines);
        return calculateTotalCost();
    }

    private void processPhoneLog(String phoneLogLine) {
        String[] phoneLogDetails = phoneLogLine.split(",");
        int callDurationsInSeconds = parseCallDuration(phoneLogDetails);
        String phoneNumber = phoneLogDetails[1];
        updateCallDurations(phoneNumber, callDurationsInSeconds);
    }

    private int parseCallDuration(String[] phoneLogDetails){
        String[] timeSeparatedInParts = phoneLogDetails[0].split(":");
        int hours = Integer.parseInt(timeSeparatedInParts[0]);
        int minutes = Integer.parseInt(timeSeparatedInParts[1]);
        int seconds = Integer.parseInt(timeSeparatedInParts[2]);
        return (hours * HOUR_IN_SECONDS) + (minutes * MINUTE_IN_SECONDS) + seconds;
    }

    private void updateCallDurations(String phoneNumber, int callDurationInSeconds){
        logs.put(phoneNumber, logs.getOrDefault(phoneNumber, 0) + callDurationInSeconds);
        updateLongestCall(phoneNumber);
    }

    private void updateLongestCall(String phoneNumber){
        if(logs.get(phoneNumber) > longestCallDuration){
            longestCallDuration = logs.get(phoneNumber);
            numberWithLongestCallDuration = phoneNumber;
        }
    }

    private void freePromotion(String[] phoneLogLines){
        if (phoneLogLines.length > 1){
            logs.put(numberWithLongestCallDuration, 0);
        }
    }
    private int calculateTotalCost(){
        int totalCost = 0;
        for(int duration : logs.values()){
            if(duration <= 300){
                totalCost += duration * RATE_FOR_SECONDS;
            } else {
                totalCost += (int) Math.ceil(duration / 60.0) * RATE_FOR_MINUTES;
            }
        }
        return totalCost;
    }
}
