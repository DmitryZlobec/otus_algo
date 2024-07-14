import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static final int MAX_SIZE = 3;

    public static void main(String[] args) {
        String text = "aaaaaaBBaaaaCCfffggggffffddddsssSSS";
        System.out.println(text);
        byte[] bytes = rleIn(text.getBytes());
        byte[] restored = rleOut(bytes);
        StringBuilder sb = new StringBuilder();
        for (byte b : restored) {
            sb.append((char) b);
        }
        System.out.println(sb);
        System.out.println("initial="+ text.length()+" compressed="+ bytes.length);
    }

    public static byte[] rleIn(byte[] input) {
        List<Byte> out = new ArrayList<>();
        byte k =0;
        byte calc =input[0];
        for (byte b: input ) {
            if (calc != b) {
                out.add(k);
                out.add(calc);
                k=0;
                calc = b;
            }
            if (k> MAX_SIZE) {
                out.add(k);
                out.add(calc);
                k=0;
            }
            k++;
        }
        out.add(k);
        out.add(calc);

        return getBytes(out);

    }

    private static byte[] getBytes(List<Byte> out) {
        byte[] outArray = new byte[out.size()];
        for (int i = 0; i < out.size() ; i++) {
            outArray[i] = out.get(i);
        }
        return outArray;
    }

    public static byte[] rleOut(byte[] input) {
        if(input.length%2 >0) {
            return new byte[1];
        }
        List<Byte> out = new ArrayList<>();
        for(int i=0; i< input.length;i=i+2)
        {

            byte count = input[i];
            byte character = input[i+1];
            for (int j = 0; j < count; j++) {
                out.add(character);
            }

        }

        return getBytes(out);
    }
}