package intervals;

import java.util.function.Supplier;
import java.util.random.RandomGenerator;

public record NumbersProvider(int length, int interval) implements Supplier<int[]> {
    private static final RandomGenerator randomGenerator = RandomGenerator.of("L128X1024MixRandom");

    @Override
    public int[] get() {
        var data = new int[length];
        var probability = 1. / interval;
        var number = randomGenerator.nextInt(-10, 10);

        for (int i = 0; i < data.length; ++i) {
            data[i] = number++;
            if (randomGenerator.nextDouble() <= probability) {
                number += randomGenerator.nextInt(1, 20);
            }
        }
        return data;
    }
}
