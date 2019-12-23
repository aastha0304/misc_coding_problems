package misc;

import java.util.*;


public class WorkerAssignmentSlow {
    private int getMinTimeAssignment(short[][] m){
        boolean[] remainingWorkers = new boolean[m.length];
        boolean[] remaingJobs = new boolean[m.length];
        Arrays.fill(remainingWorkers, true);
        Arrays.fill(remaingJobs, true);
        return getMinTimesUtil(m, remainingWorkers, remaingJobs);
    }
    private boolean isFull(boolean[] jobs){
        for(boolean b :jobs)
            if(b)
                return false;
        return true;
    }
    private int getMinTimesUtil(short[][] times, boolean[] remainingWorkers, boolean[] remainingJobs ){
        if(isFull(remainingJobs))
            return 0;
        else{
            int min = Integer.MAX_VALUE;
            for(int i=0;i<times.length;i++){
                //System.out.println("for worker: "+i);
                if(remainingWorkers[i]){
                    remainingWorkers[i] = false;
                    //now get minimum cost for this worker
                    for(int j=0;j<times.length;j++){
                        if(remainingJobs[j]){
                            //System.out.println("for worker: "+i+" job: "+j);
                            remainingJobs[j] = false;
                            int costWorkerJob = times[i][j];
                            if(costWorkerJob>min)
                            {
                                remainingJobs[j] = true;
                                continue;
                            }
                            int pathCostWorkerJob = costWorkerJob+
                                    getMinTimesUtil(times, remainingWorkers, remainingJobs);
                            if(min>pathCostWorkerJob)
                                min = pathCostWorkerJob;
                            remainingJobs[j] = true;
                        }
                    }
                }
                remainingWorkers[i] = true;
                //System.out.println("for worker: "+i+" min cost is "+min);
            }
            return min;
        }
    }
    public static void main (String[] args) {
        Scanner s = new Scanner(System.in);
        int t = Integer.parseInt( s.nextLine() );
        while( t!=0 ){
            String line = s.nextLine();
            int n = Integer.parseInt( line );
            line = s.nextLine();
            String[] elems = line.split(" ");
            short[][] times = new short[n][n];
            elems = line.split(" ");
            for(int i=0;i<n;i++)
                for(int j=0;j<n;j++)
                    times[i][j] = Short.parseShort( elems[i*n+j] );
            Utils.display(times);
            WorkerAssignmentSlow workerAssignment = new WorkerAssignmentSlow();
            System.out.println(workerAssignment.getMinTimeAssignment(times));
            t--;
        }
    }

}
