import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        // Множество
        Set<Integer> vertex = Set.of(1,2,3,4,5,6,7);
        var edges = Set.of(Edge.of(1,2), Edge.of(2,3), Edge.of(3,4), Edge.of(5,6), Edge.of(6,7));

        //Матрица смежности
        Integer[][] adjMatrix = {
                {0,1,0,0,0,0,0},
                {1,0,1,0,0,0,0},
                {0,1,0,1,0,0,0},
                {0,1,0,0,0,0,0},
                {0,0,0,0,0,1,0},
                {0,0,0,0,1,0,1},
                {0,0,0,0,0,1,0}
        };

        // Матрица инцидентности
        Integer[][] incMatrix = {
                {1,0,0,0,0},
                {1,1,0,0,0},
                {0,1,1,0,0},
                {0,0,1,0,0},
                {0,0,0,1,0},
                {0,0,0,1,1},
                {0,0,0,0,1},
        };
    }
}