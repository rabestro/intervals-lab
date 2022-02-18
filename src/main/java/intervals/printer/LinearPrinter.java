package intervals.printer;

public class LinearPrinter implements Printer {
    @Override
    public String print(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return "";
        }
        return new SequenceBuilder().build(numbers);
    }
}

class SequenceBuilder {
    private static final char INTERVAL_DELIMITER = ',';
    private static final char TWO_NUMBERS_DELIMITER = ',';
    private static final char RANGE_DELIMITER = '-';

    private final StringBuilder sb = new StringBuilder();

    private int firstNumber;
    private int lastNumber;

    String build(int[] numbers) {
        firstNumber = lastNumber = numbers[0];
        startInterval();

        for (int i = 1; i < numbers.length; ++i) {
            int currentNumber = numbers[i];
            var isIntervalEnded = currentNumber - lastNumber != 1;

            if (isIntervalEnded) {
                closeInterval();
                firstNumber = currentNumber;
                sb.append(INTERVAL_DELIMITER);
                startInterval();
            }
            lastNumber = currentNumber;
        }
        closeInterval();
        return sb.toString();
    }

    private void startInterval() {
        sb.append(firstNumber);
    }

    private void closeInterval() {
        var sequenceLength = lastNumber - firstNumber;
        if (sequenceLength > 0) {
            sb.append(getRangeDelimiter(sequenceLength)).append(lastNumber);
        }
    }

    private char getRangeDelimiter(int sequenceLength) {
        return sequenceLength == 1 ? TWO_NUMBERS_DELIMITER : RANGE_DELIMITER;
    }

}
