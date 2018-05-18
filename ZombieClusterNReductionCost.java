//Hackerrank problems mainly
//zombie cluster and reductioncose

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

public  class JavaOOPsConcepts {
	
	static boolean isSafe(int M[][], int row, int col,
            boolean visited[][], int ROW, int COL)
  {
   return (row >= 0) && (row < ROW) &&
          (col >= 0) && (col < COL) &&
          (M[row][col]==1 && !visited[row][col]);
  }
  static void DFS(int M[][], int row, boolean visited[], int ROW, int COL)
  {
   int rowNbr[] = new int[] {-1, -1, -1,  0, 0,  1, 1, 1};
   int colNbr[] = new int[] {-1,  0,  1, -1, 1, -1, 0, 1};
   visited[row] = true;

   for (int j = 0; j < COL; ++j)
   {
     if(row!=j && M[row][j]==1 && !visited[j])
       DFS(M,j,visited,ROW, COL);
   }
   // Mark this cell as visited
   visited[row] = true;

   /**
   if (isSafe(M, row + 1, col, visited, ROW, COL) )
       DFS(M, row + 1, col, visited, ROW, COL);
   if (isSafe(M, row - 1, col, visited, ROW, COL) )
       DFS(M, row - 1, col, visited, ROW, COL);
   if (isSafe(M, row, col + 1, visited, ROW, COL) )
       DFS(M, row, col + 1, visited, ROW, COL);
   if (isSafe(M, row, col - 1, visited, ROW, COL) )
       DFS(M, row, col - 1, visited, ROW, COL); 
       */
    /**   
   for (int k = 0; k < 8; ++k)
       if (isSafe(M, row + rowNbr[k], col + colNbr[k], visited, ROW, COL) )
           DFS(M, row + rowNbr[k], col + colNbr[k], visited, ROW, COL);
           */
  }
  static int zombieCluster(String[] zombies) {

   int ROW = zombies.length;
   int COL = zombies[0].length();
   int[][] M = new int[ROW][COL];
   int i=0,j;
   for(String a:zombies){
       j=0;
       for(char c:a.toCharArray()){
           M[i][j] = c=='1'?1:0;
           j++;
       }
       i++;
   }
   display(M, ROW, COL);
   boolean visited[] = new boolean[ROW];

   int count = 0;
   for (i = 0; i < ROW; ++i)
       for (j = 0; j < COL; ++j)
           if (i!=j && !visited[j]) 
           {                                 DFS(M, j, visited, ROW, COL);
               ++count;
           }

   return count;
  }
  static void display(int M[][], int ROW, int COL) {
    for(int i=0;i<ROW;i++) {
      for(int j=0;j<COL;j++) {
        System.out.print(M[i][j]+" ");
      }
      System.out.println();
    }
  }
  static int reductionCost(int[] arr) {
    Integer[] a = new Integer[arr.length];
    int i = 0;
    for (int value : arr) {
        a[i++] = Integer.valueOf(value);
    }
      PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Arrays.asList(a));
      int sum = priorityQueue.poll();
      int cost = 0;
      while (!priorityQueue.isEmpty()) {
          int currentElement = priorityQueue.poll();
          if (currentElement < sum) {
              priorityQueue.add(sum);
              sum = currentElement;
          } else {
              sum += currentElement;
              cost += sum;
              continue;
          }

          sum += priorityQueue.poll();
          cost += sum;
      }

      return cost;
  }

	public static void main(String[] args) {
		JavaOOPsConcepts job = new JavaOOPsConcepts();
		 int v=2;
				    v += v++;
				    //System.out.println(v);
				    //System.out.println(v);
				    	String[] zombies = new String[] {"10001","01000","00100","00010","10001"};
				    	System.out.println(zombieCluster(zombies));
	}
}
