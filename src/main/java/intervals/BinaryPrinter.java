package intervals;


import java.util.ArrayDeque;
import java.util.Deque;

import static java.util.stream.Collectors.joining;

public class BinaryPrinter implements Printer {

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
        var isSequenceContinues = lastNumber - firstNumber == i1 - i0;

        if (isSequenceContinues) {
            sequence.add(new Range(firstNumber, lastNumber));
            return sequence;
        }

        var sequenceLength = i1 - i0 + 1;

        if (sequenceLength == 2) {
            sequence.add(new Range(firstNumber));
            sequence.add(new Range(lastNumber));
            return sequence;
        }

        var median = i0 + (sequenceLength - 1) / 2;

        var leftRanges = getRanges(numbers, i0, median);
        var rightRanges = getRanges(numbers, median + 1, i1);

        var isConnectable = rightRanges.getFirst().first() - leftRanges.getLast().last() == 1;

        if (isConnectable) {
            var a = leftRanges.pollLast().first();
            var b = rightRanges.pollLast().last();
            var combinedInterval = new Range(a, b);
            leftRanges.add(combinedInterval);
        }
        leftRanges.addAll(rightRanges);
        return leftRanges;
    }
}
