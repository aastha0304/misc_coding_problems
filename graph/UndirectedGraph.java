package graphs;

public class UndirectedGraph extends Graph{

    public UndirectedGraph(int v) {
        super(v);
    }

    @Override
    public void addEdge(int s, int d) {
        super.addEdge(s, d);
        Edge edge = new Edge(d, s, 0);
        this.adjList[d].add(edge);
    }

}
