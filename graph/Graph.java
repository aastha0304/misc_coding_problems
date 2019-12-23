package graphs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

//works like a directed graph of 0 cost
public class Graph {
    int V;
    LinkedList<Edge>[] adjList;
    int[] inDegree;
    Graph(int v)
    {
        V = v;
        adjList = new LinkedList[v];
        for(int i = 0; i < v ; i++){
            adjList[i] = new LinkedList<>();
        }
        inDegree = new int[v];
    }

    public void addEdge(int s, int d){
        addEdge(s, d, 0);
    }
    public void addEdge(int s, int d, int cost){

        Edge edge = new Edge(s, d, cost);
        this.adjList[s].add(edge);
        inDegree[d]++;
    }
    public void print(){
        for(int i = 0; i < adjList.length ; i++) {
            System.out.print(i+":");
            for (Edge e : adjList[i]) {
                System.out.print(" "+e.dest+" ");
            }
            System.out.println();
        }
    }
    public boolean isEulerianCycle(){
        if(!isStronglyConnected())
            return false;
        for(int i = 0; i < adjList.length ; i++){
            if (adjList[i].size() != inDegree[i])
                return false;
        }
        return true;
    }
    public boolean isStronglyConnected(){
        if(!this.bfs())
            return false;
        Graph reversed = this.reverse();
        return reversed.bfs();
    }
    private boolean bfs(){
        Set<Integer> visited = new HashSet<>();
        List<Integer> queue = new LinkedList<>();
        Set<Integer> nonZero = new HashSet<>();
        int startVertex = -1;

        for(int i=0;i<V;i++){
            if(adjList[i].size()>0) {
                if(startVertex==-1)
                    startVertex = i;
                nonZero.add(i);
            }
        }
        queue.add(startVertex);
        while(!queue.isEmpty()){
            int currentVertex = ((LinkedList<Integer>) queue).removeFirst();
            visited.add(currentVertex);

            for(Edge edge: adjList[currentVertex]){
                if(!visited.contains(edge.getDest()))
                    queue.add(edge.getDest());
            }
        }

        for(int i: nonZero){
            if(!visited.contains(i))
                return false;
        }
        return true;
    }
    private Graph reverse(){
        Graph graph = new Graph(V);
        for(int i=0;i<V;i++){
            for(Edge e: this.adjList[i]){
                graph.addEdge(e.getDest(), e.getSrc());
            }
        }
        return graph;
    }
}
