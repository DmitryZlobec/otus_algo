import java.math.BigInteger;

public class Main {
    public static int[] cache = new int[65536];

    public static void main(String[] args) {


        for (int i=0; i<cache.length; i++) {
            cache[i] = oneCount(i);
        }
        long pos =  35;

        long rook = 1L << pos;

        long result = (255L << (8* (pos >>3))) ^ (72340172838076673L << (pos & 7))  ; //

        long moves = cacheCount(result);
        System.out.println("rook = " + rook);
        System.out.println("result = " + result);
        System.out.println("moves = " + moves);

    }

    public static int oneCount(long number) {
        int cnt =0;
        {
            while (number>0)
            {
                cnt++;
                long numberMinus = number - 1;
                number = number & numberMinus;
            }
        }
        return cnt;
    }

    public static int cacheCount(long number) {
        int cnt = 0;
        while ( number >0) {
            cnt += cache[(int) (number & 65535)];
            number>>= 16;
        }
        return cnt;
    }
}