import java.util.stream.IntStream;

public class FSM {

    final String alphabet = "ABC";
    int[][] delta;
    int length;

    public FSM(String pattern) {
        length = pattern.length();
        initDelta(pattern);
    }

    private void initDelta(String pattern) {
        delta = new int[length + 1][alphabet.length()];

        IntStream.range(0, length + 1).forEach(q -> {
            for (byte c : alphabet.getBytes()) {
                int k = q + 1;
                if (q == length) k--;
                String line = left(pattern, q) + (char) c;
                while (!left(pattern, k).equals(right(line, k))) {
                    k--;
                }
                delta[q][c - alphabet.getBytes()[0]] = k;
            }
        });
    }

    public int search(String text) {
        int q = 0;
        for (int i = 0; i < text.length(); i++) {
            int aByte = text.getBytes()[i];
            int aByte1 = alphabet.getBytes()[0];
            int i1 = aByte - aByte1;
            q = delta[q][i1];
            if (q == length) {
                return i - length + 1;
            }
        }
        return -1;
    }


    private static String left(String line, int x) {
        return line.substring(0,x);
    }

    private static String right(String line, int x) {
        return  line.substring(line.length()-x);
    }
}
