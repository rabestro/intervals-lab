package intervals;

import intervals.printer.BinaryPrinter;
import intervals.printer.Printer;
import intervals.printer.SimplePrinter;
import org.apache.commons.lang3.time.StopWatch;

public class Main {
    public static void main(String[] args) {
        var dataProvider = new NumbersProvider(10_000_000, 100_000);
        var binaryPrinter = new BinaryPrinter();
        var simplePrinter = new SimplePrinter();

        var tester = new TestPrinter(dataProvider);

        tester.prepare(50);

        tester.test(binaryPrinter);
        tester.test(simplePrinter);
    }
}

class TestPrinter {
    private final NumbersProvider numbersProvider;
    private final StopWatch timer = new StopWatch();
    private int[][] testData;

    TestPrinter(NumbersProvider testDataProvider) {
        numbersProvider = testDataProvider;
    }

    void prepare(int rounds) {
        timer.start();
        testData = new int[rounds][];
        for (int i = 0; i < rounds; ++i) {
            testData[i] = numbersProvider.get();
        }
        timer.stop();
        System.out.println("Generated: " + timer.formatTime());
        System.out.println();
    }

    void test(Printer printer) {
        timer.reset();
        timer.start();
        for (var numbers : testData) {
            printer.print(numbers);
        }
        timer.stop();
        System.out.println(printer.getClass().getSimpleName() + ": " + timer.formatTime());
    }
}