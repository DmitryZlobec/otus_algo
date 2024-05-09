import java.util.Arrays;
import java.util.stream.Collectors;

public class TrieUtils {

    private TrieUtils() {};

    public static int[] stringify(String string) {
        return string.chars()
                .map(c -> c < 97 || c > 122 ? 0 : c - 'a')
                .toArray();
    }
}
