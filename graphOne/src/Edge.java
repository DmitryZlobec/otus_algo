public class Edge {
    Integer startVertex;
    Integer finishVertex;

    public Edge(Integer startVertex, Integer finishVertex) {
        this.startVertex=startVertex;
        this.finishVertex=finishVertex;
    }

    public static Edge of(Integer startVertex, Integer finishVertex) {
        return new Edge(startVertex, finishVertex);
    }
}
