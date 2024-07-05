import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
            int[][] adjMatrix = {
                   //1 2 3 4 5 6 7 8 9 10
                    {0,1,0,0,0,0,0,0,0,0}, //1
                    {0,0,1,0,0,0,0,0,0,0}, //2
                    {0,0,0,1,0,0,0,0,0,0}, //3
                    {0,0,0,0,1,0,0,0,0,0}, //4
                    {0,0,0,0,0,1,0,0,0,0}, //5
                    {0,0,0,0,0,0,1,0,0,0}, //6
                    {0,0,0,0,0,0,0,1,0,0}, //7
                    {0,0,0,0,0,0,0,0,1,0}, //8
                    {0,0,0,0,0,0,0,0,0,1}, //9
                    {1,0,0,0,0,0,0,0,0,0}  //10
            };
        State[] vertex = new State[10];
        Arrays.fill(vertex, State.None);
        boolean found = dfs(0, 9, adjMatrix, vertex);
        System.out.println("found = " + found);

        Arrays.fill(vertex, State.None);
        boolean found_cycl = dfs_cyl(0, 9, adjMatrix, vertex);
        System.out.println("found_bfs = " + found_cycl);

        Arrays.fill(vertex, State.None);
        boolean found_bfs = bfs(0, 9, adjMatrix, vertex);
        System.out.println("found_bfs = " + found_bfs);

    }

    private static boolean dfs(int begin, int end, int[][] adjMatrix, State[] vertex) {
            vertex[begin] = State.Processed;
            if(begin == end) {
                return true;
            }
            for(int i=0; i< vertex.length; i++) {
                if(vertex[i] != State.None) {
                    continue;
                }
                if (adjMatrix[begin][i] == 0) {
                    continue;
                }
                if(dfs(i, end, adjMatrix, vertex)){
                    return true;
                }
            }
            return false;
    }

    private static boolean dfs_cyl(int begin, int end, int[][] adjMatrix, State[] vertex) {

        Stack<Integer> stack = new Stack<>();
        stack.push(begin);
        boolean found = false;
        while (!stack.isEmpty()) {
            Integer z = stack.pop();
            vertex[z] = State.Work;
            for (int i = 0; i < vertex.length; i++) {
                if(adjMatrix[z][i] == 0) {
                    continue;
                }
                if(State.Work == vertex[i]) {
                    continue;
                }
                if(i == end) {
                    found = true;
                    break;
                }
                if(State.Processed == vertex[i]) {
                    continue;
                }
                stack.push(i);
                vertex[i] = State.Processed;
            }
        }
        return found;
    }

    private static boolean bfs(int begin, int end, int[][] adjMatrix, State[] vertex) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(begin);
        boolean found = false;
        while (!queue.isEmpty()){
            Integer z = queue.poll();
            vertex[z] = State.Processed;
            for (int i = 0; i < vertex.length; i++) {
                if(adjMatrix[z][i] == 0) {
                    continue;
                }
                if(State.None != vertex[i] ) {
                    continue;
                }
                if(i== end) {
                    found = true;
                    break;
                }
                queue.offer(i);
                vertex[i]= State.Processed;
            }
            if(found) break;
        }

        return found;
    }

    }