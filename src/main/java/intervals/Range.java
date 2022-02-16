package intervals;

public record Range(int first, int last) {

    public Range(int number) {
        this(number, number);
    }

    public String print() {
        return switch (last - first) {
            case 0 -> "" + first;
            case 1 -> first + "," + last;
            default -> first + "-" + last;
        };
    }
}
