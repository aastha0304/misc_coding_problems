package graphs;

public class WeightedDirectedGraph extends Graph{

    public WeightedDirectedGraph(int v) {
        super(v);
    }
    @Override
    public void print(){
        for(int i = 0; i < adjList.length ; i++) {
            System.out.print(i+":");
            for (Edge e : adjList[i]) {
                System.out.print(" "+e.dest+" for "+e.cost+",");
            }
            System.out.println();
        }
    }
    private Edge getEdge(int s, int d){
        for(Edge e:adjList[s]){
            if(e.dest==d)
                return e;
        }
        return null;
    }
    private int getChild(int parent, int[] parents){
        for(int i=0;i<parents.length;i++){
            if(parents[i]==parent)
                return i;
        }
        return -1;
    }
    private int preorderWalk(int start, int[] parent){
        int counter=1, cost=0;
        int currentParent = 0;
        while(counter<=parent.length){
            int currentChild = getChild(currentParent, parent);
            if(currentChild!=-1) {
                cost += getEdge(currentParent, currentChild).cost;
                currentParent = currentChild;
            }
            counter ++;
        }
        cost += getEdge(currentParent, start).cost;
        return cost;
    }
    //This method for TSP only works if dist(i,j) < dist(i,k)+dist(k,j) is true for all k
    //eg not here
    //0 887 2778 6916 7794 0 8336 5387 493 6650 0 1422 2363 28 8691 0
    public int solveTSPWithPrim(int start) {
        int[] costBuilder = buildPrimMST(start);
        for(int i=0;i<V;i++){
           System.out.println(i+" "+costBuilder[i]);
        }
        int cost = preorderWalk(start, costBuilder);

        //int returnCost = getEdge(co start);
        return cost; //+returnCost;
    }
    private int[] buildPrimMST(int start){
        int[] parent = new int[V];
        int[] key = new int[V];
        boolean[] inMst = new boolean[V];
        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            inMst[i] = false;
        }
        key[start] = 0;
        parent[start] = -1;
        for (int count = 1; count < V ; count++) {
            int newMinVertex = minKey(key, inMst);
            inMst[newMinVertex] = true;
            for(Edge e: adjList[newMinVertex]){
                if(e.cost<key[e.dest] && !inMst[e.dest]){
                    parent[e.dest] = newMinVertex;
                    key[e.dest] = e.cost;
                }
            }
        }
        return parent;
    }
    private int minKey(int[] key, boolean[] mstSet){
        //no neg cycle
        int min = Integer.MAX_VALUE, minDest = -1;
        for (int v = 0; v < V; v++)
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                minDest = v;
            }
        return minDest;
    }
    //TSP backtracking
    public int solveTSP(int start){
        boolean[] visited = new boolean[V];
        return tspUtil(start,1, visited, start, 0, Integer.MAX_VALUE);
    }
    private int tspUtil(int currentVertex, int covered, boolean[] visited, int start, int cost, int currentRes){
        if(covered==V){
            Edge returnEdge = getEdge(currentVertex, start);
            if(returnEdge!=null){
                currentRes = Math.min(currentRes, cost+returnEdge.cost);
                return currentRes;
            }
        }else{
            visited[currentVertex] = true;
            for(Edge e: adjList[currentVertex]){
                if(!visited[e.dest]){
                    currentRes = tspUtil(e.dest, covered+1, visited, start,
                            cost+getEdge(currentVertex, e.dest).cost, currentRes);
                }
            }
            visited[currentVertex] = false;
        }
        return currentRes;
    }
}
