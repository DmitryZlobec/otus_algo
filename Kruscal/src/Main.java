import java.util.*;

public class Main {

    static Queue<Edge> edgesQ = new LinkedList<>();
    static int[][] matrix = {{0, 2, 2, 0, 1, 0, 3},
            {2, 0, 3, 3, 0, 0, 0},
            {2, 3, 0, 0, 0, 0, 0},
            {0, 3, 0, 0, 2, 0, 0},
            {1, 0, 0, 2, 0, 4, 0},
            {0, 0, 0, 0, 4, 0, 2},
            {3, 0, 0, 0, 0, 2, 0},
    };

    static int[] parent =  new int[matrix.length];
    static int  weghtMin = 0;


    public static void main(String[] args) {
        kruskal();
        edgesQ.forEach(edge -> {
            System.out.println((edge.getBegin()+1)+"-"+(edge.getEnd()+1)+":"+edge.getWeight());
        });
        System.out.println("weghtMin = " + weghtMin);
    }

    public static void kruskal() {
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < matrix.length ; i++) {
            for (int j = i+1; j < matrix.length ; j++) {
                if(matrix[i][j] == 0 ) {
                    continue;
                }
                Edge edge =  new Edge(i,j, matrix[i][j]);
                edges.add(edge);
            }
        }
        Collections.sort(edges);
        for (int i = 0; i < matrix.length; i++) {
            parent[i] = i;
        }

        for(Edge edge: edges) {
            int begin = getParent(edge.getBegin());
            int end = getParent(edge.getEnd());
            if(begin == end) continue;;
            edgesQ.offer(edge);
            weghtMin +=edge.getWeight();
            parent[end] = begin;
        };

    }

    public static int getParent(int v) {
        if(parent[v] == v) {
            return v;
        }
        return getParent(parent[v]);
    }
}