package intervals;

import intervals.printer.BinaryPrinter;
import intervals.printer.LinearPrinter;
import intervals.printer.Printer;
import intervals.printer.SimplePrinter;
import org.apache.commons.lang3.time.StopWatch;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        var dataProvider = new NumbersProvider(10_000_000, 1_000_000);

        var printers = List.of(new SimplePrinter(), new LinearPrinter(), new BinaryPrinter());

        var tester = new TestPrinter(dataProvider).generateRounds(50);

        printers.forEach(tester::test);
    }
}

class TestPrinter {
    private final NumbersProvider numbersProvider;
    private final StopWatch timer = new StopWatch();
    private int[][] testData;

    TestPrinter(NumbersProvider testDataProvider) {
        numbersProvider = testDataProvider;
    }

    TestPrinter generateRounds(int rounds) {
        timer.start();
        testData = new int[rounds][];
        for (int i = 0; i < rounds; ++i) {
            testData[i] = numbersProvider.get();
        }
        timer.stop();
        System.out.println("Generated: " + timer.formatTime());
        System.out.println();
        return this;
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