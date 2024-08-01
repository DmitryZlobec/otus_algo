import java.util.function.BiFunction;
import java.util.function.Function;

public class Main {

    static class Recursive {
        public static BiFunction<Integer, Integer, Integer> NOD;
    }


    public static void main(String[] args) {
        gorox(1, 2, 5, 15);
        gorox(1, 9973, 1, 9999);
    }

    public static void gorox(int a, int b, int c, int d) {
        int x = a * d + b * c;
        int y = b * d;
        int nod = Recursive.NOD.apply(x, y);
        x /= nod;
        y /= nod;
        System.out.println(x + "/" + y);
    }

    static Function<Integer, Boolean> even = (n) -> (n & 1) == 0;
    static Function<Integer, Boolean> odd = (n) -> (n & 1) == 1;

    static {
        Recursive.NOD = (x, y) -> {
            if (x.compareTo(y) == 0) return x;
            if (x.compareTo(0) == 0) return y;
            if (y.compareTo(0) == 0) return x;
            if (even.apply(x) && even.apply(y)) return Recursive.NOD.apply(x >> 1, y >> 1) << 1;
            if (even.apply(x) && odd.apply(y)) return Recursive.NOD.apply(x >> 1, y);
            if (odd.apply(x) && even.apply(y)) return Recursive.NOD.apply(x, y >> 1);
            if (x.compareTo(y) > 0) {
                return Recursive.NOD.apply((x - y) >> 1, y);
            } else {
                return Recursive.NOD.apply(x, (y - x) >> 1);
            }
        };
    }


}