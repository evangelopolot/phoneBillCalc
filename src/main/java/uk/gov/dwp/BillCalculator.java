package uk.gov.dwp;

import java.util.HashMap;

public class BillCalculator {
    public int calculateBill(final String phoneLogInput) {
        String[] phoneLogLines = phoneLogInput.split("\n");

        HashMap<String, Integer> logs = new HashMap<>();

        int longestCallDuration = 0;
        String numberWithLongestCallDuration = "";

        for(String phoneLogLine : phoneLogLines){
            String[] phoneLogDetails = phoneLogLine.split(",");
            String[] timeSeparatedInParts = phoneLogDetails[0].split(":");
            int hours = Integer.parseInt(timeSeparatedInParts[0]);
            int minutes = Integer.parseInt(timeSeparatedInParts[1]);
            int seconds = Integer.parseInt(timeSeparatedInParts[2]);
            int callDurationInSeconds = (hours * 3600) + (minutes * 60) + seconds;

            String phoneNumber = phoneLogDetails[1];
            logs.put(phoneNumber, logs.getOrDefault(phoneNumber, 0) + callDurationInSeconds);

            if(logs.get(phoneNumber) > longestCallDuration){
                longestCallDuration = logs.get(phoneNumber);
                numberWithLongestCallDuration = phoneNumber;

            } else if(logs.get(phoneNumber) == longestCallDuration){

                String currentNumber = phoneNumber.replaceAll("-","");
                String longestDurationNumber = numberWithLongestCallDuration.replaceAll("-","");;
                int currentNumberNumeric = Integer.parseInt(currentNumber);
                int longestDurationNumberNumeric = Integer.parseInt(longestDurationNumber);
                if(currentNumberNumeric < longestDurationNumberNumeric){
                    numberWithLongestCallDuration = phoneNumber;
                }
            }
        }
        if (phoneLogLines.length > 1){
            logs.put(numberWithLongestCallDuration, 0);
        }

        int totalCost = 0;
        for(int duration : logs.values()){
            if(duration <= 300){
               totalCost += duration * 3;
            } else {
                totalCost += (int) Math.ceil(duration / 60.0) * 150;
            }
        }

        return totalCost;
    }
}
