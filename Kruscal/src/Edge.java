public class Edge implements Comparable{
    private int begin;
    private int end;
    private int weight;

    public Edge(int begin, int end, int weight) {
        this.begin = begin;
        this.end = end;
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof Edge) {
            return this.weight - ((Edge) o).getWeight();
        }
        throw new RuntimeException("Uncomparable");
    }
}
