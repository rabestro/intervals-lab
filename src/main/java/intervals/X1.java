package intervals;


import java.util.ArrayDeque;
import java.util.Deque;

import static java.util.stream.Collectors.joining;

public class X1 implements Printer {

    public String print(int[] numbers) {
        if (numbers == null || numbers.length == 0) {
            return "";
        }
        var ranges = getRanges(numbers, 0, numbers.length - 1);
        return ranges.stream().map(Range::print).collect(joining(","));
    }

    private Deque<Range> getRanges(int[] numbers, int i0, int i1) {
        var sequence = new ArrayDeque<Range>();
        var firstNumber = numbers[i0];
        var lastNumber = numbers[i1];
        var sequenceLength = i1 - i0;
        if (lastNumber - firstNumber == sequenceLength) {
            sequence.add(new Range(firstNumber, lastNumber));
            return sequence;
        }

        return sequence;
    }
}
