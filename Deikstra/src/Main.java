import java.util.stream.IntStream;

public class Main {
    static int[][] matrix =
            {
                    {0, 7, 9, 0, 0, 14},
                    {7, 0, 10, 15, 0, 0},
                    {9, 10, 0, 11, 0, 2},
                    {0, 15, 11, 0, 6, 0},
                    {0, 0, 0, 6, 0, 9},
                    {14, 0, 2, 0, 9, 0},
            };

    public static void main(String[] args) {
        IntStream.range(0, matrix.length).forEach(vertex -> {
            int[] ways = deikstra(vertex);
            System.out.println("Минимальные пути из вершины " +
                    (vertex + 1) + ":");
            IntStream.range(0, matrix.length).forEach(i->
                    System.out.print(ways[i]+" "));
            System.out.println();
        });
    }

    static int getMin(int[] mas, boolean [] visited)
    {
        int min = Integer.MIN_VALUE;
        for (int i = 0; i < mas.length; i++)
        {
            if (!visited[i]) {
                if (min == Integer.MIN_VALUE)
                    min = i;
                else if (mas[i] < mas[min])
                    min = i;
            }
        }
        return min;
    }

    public static int[] deikstra(int vertex)
    {
        int[] ways = new int[matrix.length];
        boolean[] visited = new boolean[matrix.length];

        IntStream.range(0, matrix.length).forEach(i -> {
            ways[i] = Integer.MAX_VALUE;
        });

        ways[vertex] = 0;
        IntStream.range(0, matrix.length).forEach(k -> {
            int min = getMin(ways, visited);
            visited[min] = true;
            IntStream.range(0, matrix.length).forEach(i-> {
                if(!visited[i] && matrix[min][i] != 0) {
                    int weight = ways[min] + matrix[min][i];
                    if (weight < ways[i])
                        ways[i] = weight;
                }
            });
        });
        return ways;
    }
}