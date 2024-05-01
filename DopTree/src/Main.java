import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        DOPTree<Integer, Integer> dopTree = new DOPTree<>();
        Random random = new Random();
        List<WNode<Integer, Integer, Integer>> nodes = IntStream.range(0, 10)
                .mapToObj(i -> new WNode<>(i, 100, random.nextInt(999)))
                .sorted(Comparator.comparing(n -> n.weight)).toList();
        nodes.reversed().forEach(dopTree::add);

        DOPTree<Integer, Integer> dopTree1 = new DOPTree<>();

        List<WNode<Integer, Integer, Integer>> nodes1 = IntStream.range(0, 10)
                .mapToObj(i -> new WNode<>(i, 100, random.nextInt(999)))
                .sorted(Comparator.comparing(n -> n.key)).toList();

        build(dopTree1, nodes1, 0, nodes1.size()-1);
        System.out.println("end");
    }

    private static void build(DOPTree<Integer, Integer> dopTree,
                       List<WNode<Integer, Integer, Integer>> array,
                       int left, int right ) {


        int root = findRoot(array, left, right);
        dopTree.add(array.get(root));
        if (left == right) {
            return;
        }
        build(dopTree, array, left, root - 1);
        build(dopTree, array, root + 1, right);
    }

    private static int findRoot(List<WNode<Integer, Integer, Integer>> array, int l, int r) {
        int[] sort = new int[array.size()];
        for(int i=l; i<=r;i++) {
            int left = findSide(array,l, i-1);
            int right = findSide(array,i+1, r);
            sort[i] = Math.abs(left - right);
        }
        return minPlace(sort,l,r);
    }

    private static int  findSide(List<WNode<Integer, Integer, Integer>> array, int i, int i1) {
        int res =0;
        for(int k=i; k<=i1; k++) {
            res = res + array.get(k).weight;
        }
        return res/2;
    }

    private static int minPlace(int[] array, int l, int r) {
        int min = r;
        for (int i = r - 1; i >=l ; i--) {
            if (array[i+1] > array[i]) {
                min = i ;
            }
        }
        return min;
    }
}