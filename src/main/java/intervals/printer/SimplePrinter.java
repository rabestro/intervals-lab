package intervals.printer;

import java.util.StringJoiner;

public class SimplePrinter implements Printer {
    private static final String INTERVALS_DELIMITER = ",";
    private static final String TWO_NUMBERS_DELIMITER = ",";
    private static final String INTERVAL_SYMBOL = "-";

    private static boolean isIntervalEnd(int currentNumber, int lastNumber) {
        return currentNumber - lastNumber != 1;
    }

    private static String printInterval(int firstNumber, int lastNumber) {
        int numbersInInterval = lastNumber - firstNumber + 1;
        return switch (numbersInInterval) {
            case 1 -> String.valueOf(firstNumber);
            case 2 -> firstNumber + TWO_NUMBERS_DELIMITER + lastNumber;
            default -> firstNumber + INTERVAL_SYMBOL + lastNumber;
        };
    }

    @Override
    public String print(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return "";
        }
        var stringJoiner = new StringJoiner(INTERVALS_DELIMITER);
        int firstNumber = numbers[0];
        int lastNumber = firstNumber;

        for (int i = 1; i < numbers.length; ++i) {
            int currentNumber = numbers[i];

            if (isIntervalEnd(currentNumber, lastNumber)) {
                stringJoiner.add(printInterval(firstNumber, lastNumber));
                firstNumber = currentNumber;
            }

            lastNumber = currentNumber;
        }
        stringJoiner.add(printInterval(firstNumber, lastNumber));

        return stringJoiner.toString();
    }

}
