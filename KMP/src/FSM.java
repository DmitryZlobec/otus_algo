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
        delta = new int[length+1][alphabet.length()];

            IntStream.range(0, length+1).forEach(q-> {
                for (byte c: alphabet.getBytes()) {
                    int k=q+1;
                    if(q == length) k--;
                    String line = left(pattern,q)+ (char) c;
                    while (!left(pattern,k).equals(right(line,k)) ) {
                        k--;
                    }
                    delta[q][c-alphabet.getBytes()[0]] = k;
                }
            });
    }

    private static String left(String line, int x) {
        return line.substring(0,x);
    }

    private static String right(String line, int x) {
        return  line.substring(line.length()-x);
    }
}
