import java.util.*;

//By BFS, and PQ, and hashing on remaining; still slow :(

class NAryNode{
    int worker;
    int job;
    boolean assigned[];
    NAryNode parent;
    int cost;
    int pathCost;
    NAryNode(int x,int y, boolean[] assigned, NAryNode p){
        worker = x;
        job = y;
        this.assigned = new boolean[assigned.length];
        this.assigned = Arrays.copyOf(assigned, assigned.length);
        this.assigned[y] = true;
        this.parent = p;
    }
}
public class AssignmentProblem {
    static int findCost = 0;
    //happens 4848 times for n = 16!!!!
    private int findCost(int w, short[][] m, boolean[] assigned){
        findCost++;
        int cost = 0;
        for(int i=w+1;i<m.length;i++){
            int min = Integer.MAX_VALUE, n=0;
            for(int j=1;j<m.length;j++){
                //if(!assigned[j] && available[j] && m[i][j]<min){
                if(!assigned[j]  && m[i][j]<min){
                    min = m[i][j];
                    n = j;
                }
            }
            if(n>0) {
                cost += min;
            }
        }
        return cost;
    }
    private void display(short[][] m){
        for(int i=1;i<m.length;i++){
            for(int j=1;j<m.length;j++){
                System.out.print(m[i][j]+" ");
            }
            System.out.println();
        }
    }
    private void printAssignments(NAryNode current){
        while(current!=null){
            System.out.println("Worker "+current.worker+" is doing "+current.job);
            current = current.parent;
        }
    }
    private int getMinTimeAssignment(short[][] m) {
        display(m);
        NAryNode root = new NAryNode(0,-0, new boolean[m.length] , null);
        PriorityQueue<NAryNode> q = new PriorityQueue<>(new Comparator<NAryNode>() {
            @Override
            public int compare(NAryNode o1, NAryNode o2) {
                return Integer.compare(o1.cost, o2.cost);
            }
        });
        q.add(root);
        int qCnt = 0;
        while(!q.isEmpty()){
            qCnt++;
            NAryNode current = q.poll();
            System.out.println("Current polled "+current.worker+" "+current.job+" "+m[current.worker][current.job]+" "+current.cost);
            int i = current.worker+1;
            if(i==m.length) {
                printAssignments(current);
                System.out.println("Q was looped in "+qCnt+" times and findCost "+findCost+" times");
                return current.cost;
            }

            for(int j=1;j<m.length;j++){
                if(!current.assigned[j]){
                    NAryNode next = new NAryNode(i, j, current.assigned, current);
                    next.pathCost = current.pathCost + m[i][j];
                    next.cost = next.pathCost + findCost(i, m, next.assigned);
                    q.add(next);
                }
            }
        }
        System.out.println("Q was looped in "+qCnt+" times");
        return -1;
    }
    public static void main (String[] args) {
        AssignmentProblem workerAssignment = new AssignmentProblem();
        Scanner s = new Scanner(System.in);
        int t = Integer.parseInt( s.nextLine() );
        while( t!=0 ){
            String line = s.nextLine();
            int n = Integer.parseInt( line );
            line = s.nextLine();
            String[] elems = line.split(" ");
            short[][] times = new short[n+1][n+1];
            for(int i=1;i<=n;i++)
                for(int j=1;j<=n;j++)
                    times[i][j] = Short.parseShort( elems[(i-1)*n+(j-1)] );
            System.out.println(workerAssignment.getMinTimeAssignment(times));
            t--;
        }
    }
}
