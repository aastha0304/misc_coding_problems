package misc;

import java.util.*;


public class PennilessPilgrim {
    static int m=5,n=5;
    static void display(Coordinate[] res, int coord) {
        int i=0;
        while (i<coord) {
            System.out.print("at "+i+":"+res[i].r+" "+res[i].c+"\t");
            i++;
        }
        System.out.println();
    }
    static void display(float[][] matrix){
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                System.out.print(matrix[i][j]+"\t");
            }
            System.out.println();
        }
    }

    static void startPilgrimage(LinkedList<Coordinate> res, float[][] matrix, Set<Coordinate> dontGo,
                                Coordinate starting){
        int dx[] = {-1, 0, 1, 0};
        int dy[] = {0, 1, 0, -1};
        while(!res.isEmpty()) {

            Coordinate coord = res.pop();
            //System.out.print("Popped "+coord.x+" "+coord.y+" ,");
            float currentCost = matrix[coord.r][coord.c];
            if (!dontGo.contains(coord)) {
                for (int i = 0; i < 4; i++) {
                    int x = coord.r + dx[i];
                    int y = coord.c + dy[i];
                    Coordinate neighbor = new Coordinate(x,y);
                    float cost = 0;
                    if (neighbor.inBounds(m,n) && !dontGo.contains(neighbor) && !neighbor.equals(starting)) {
                        if(coord.isAbove(neighbor)) //going down
                            cost = currentCost*2;
                        else if(coord.isBelow(neighbor)) // going up
                            cost = currentCost/2;
                        else if(coord.isLeft(neighbor)) // going right
                            cost = currentCost+2;
                        else //going left
                            cost = currentCost-2;
                        if(neighbor.equals(new Coordinate(m-1,n-1)) && cost==0f) {
                            System.out.println("0 path found");
                            display(matrix);
                        }
                        if(matrix[x][y] > cost){
                            if(matrix[x][y]!=Float.MAX_VALUE) {
                                res.remove(neighbor);
                                //System.out.print("Removed "+x+" "+y+" "+"with weight "+matrix[x][y]);

                            }
                            matrix[x][y] = cost;
                            res.add(neighbor);
                            //System.out.print("Added "+x+" "+y+" "+"with weight "+matrix[x][y]);
                        } } } } }
    }
    static boolean inArray(Coordinate[] arr, Coordinate coord, int checkTill){
        for(int i=0;i<=checkTill;i++)
            if( arr[i]!=null && arr[i].equals(coord) )
                return true;
         return false;
    }
    static boolean startPilgrimageRecursive(Coordinate[] res, float[][] matrix, Set<Coordinate> dontGo,
                                         Coordinate current, float totalCost, Coordinate starting,
                                            int recDepth, int maxDepth){
        boolean result = false;
        res[recDepth] = current;
        if(recDepth >= maxDepth) {
            //display(res);
            result |= false;
        }
        else if(current.r==m-1 && current.c==n-1){
            if(totalCost==0) {
                display(res, recDepth);
                result |= true;
            }
            else {
                //display(res, recDepth);
                System.out.println("totalcost is" +totalCost);
                result |= false;
            }
        }else{
            int dx[] = {-1, 0, 1, 0};
            int dy[] = {0, 1, 0, -1};
            for (int i = 0; i < 4; i++) {
                int x = current.r + dx[i];
                int y = current.c + dy[i];
                Coordinate neighbor = new Coordinate(x, y);

                if (neighbor.inBounds(m,n) && !dontGo.contains(neighbor) && !inArray(res, neighbor, recDepth) &&
                        !neighbor.equals(starting)) {
                    if(neighbor.equals(new Coordinate(3,4)) && current.equals(new Coordinate(3, 3))
                            && (recDepth == 6) ) {
                        display(res, recDepth);
                        System.out.println("Going right from "+current.r+" "+current.c+"to "+neighbor.r+" "+neighbor.c
                                +" current cost is "+totalCost+", new cost is "+(totalCost+2));
                        result |= startPilgrimageRecursive(res, matrix, dontGo, neighbor, totalCost + 2, starting
                                , recDepth + 1, maxDepth);
                        System.out.println("Back from right to "+current.c+" "+current.c+"from "+neighbor.r+" "+neighbor.c
                                +" current cost is "+totalCost);
                    }
                    else if(current.isAbove(neighbor)) //down
                    {
                        System.out.println("Going below from "+current.r+" "+current.c+"to "+neighbor.r+" "+neighbor.c
                                +" current cost is "+totalCost+", new cost is "+(totalCost*2));
                        result |= startPilgrimageRecursive(res, matrix, dontGo, neighbor, totalCost * 2, starting
                                , recDepth + 1, maxDepth);
                        System.out.println("Back from below to "+current.r+" "+current.c+"from "+neighbor.r+" "+neighbor.c
                                +" current cost is "+totalCost);
                    }
                    else if(current.isBelow(neighbor) && !result) //up
                    {
                        System.out.println("Going above from "+current.r+" "+current.c+"to "+neighbor.r+" "+neighbor.c
                                +" current cost is "+totalCost+", new cost is "+(totalCost/2));
                        result |= startPilgrimageRecursive(res, matrix, dontGo, neighbor, totalCost / 2, starting
                                , recDepth + 1, maxDepth);
                        System.out.println("Back from above to "+current.r+" "+current.c+"from "+neighbor.r+" "+neighbor.c
                                +" current cost is "+totalCost);
                    }
                    else if(current.isLeft(neighbor) && !result)  //right
                    {
                        System.out.println("Going right from "+current.r+" "+current.c+"to "+neighbor.r+" "+neighbor.c
                                +" current cost is "+totalCost+", new cost is "+(totalCost+2));
                        result |= startPilgrimageRecursive(res, matrix, dontGo, neighbor, totalCost + 2, starting
                                , recDepth + 1, maxDepth);
                        System.out.println("Back from right to "+current.r+" "+current.c+"from "+neighbor.r+" "+neighbor.c
                                +" current cost is "+totalCost);
                    }
                    else if(current.isRight(neighbor) && !result) //left
                    {
                        System.out.println("Going left from "+current.r+" "+current.c+"to "+neighbor.r+" "+neighbor.c
                                +" current cost is "+totalCost+", new cost is "+(totalCost-2));
                        result |= startPilgrimageRecursive(res, matrix, dontGo, neighbor, totalCost - 2, starting
                                , recDepth + 1, maxDepth);
                        System.out.println("Back from left to "+current.r+" "+current.c+"from "+neighbor.r+" "+neighbor.c
                                +" current cost is "+totalCost);
                    }
                    }
            }
        }
//        if(!result) {
//            if(!current.equals(starting))
//                res.remove(current);
//        }
        return result;
    }
    public static void main(String[] args){

        Coordinate[] res = new Coordinate[25];

        Coordinate coordinate0 = new Coordinate(0, 0);
        Coordinate coordinate1 = new Coordinate(0, 1);
        Coordinate coordinate2 = new Coordinate(0, 2);


        res[0] = coordinate0;
        res[1] = coordinate1;
        //res.add(coordinate2);

        float[][] matrix = new float[m][n];
        matrix[0][0] = 0;
        matrix[0][1] = 2;
        matrix[0][2] = 4;
        matrix[0][3] = Float.MAX_VALUE;
        matrix[0][4] = Float.MAX_VALUE;
        for(int i=1;i<m;i++){
            for(int j=0;j<n;j++){
                matrix[i][j]=Float.MAX_VALUE;
            }
        }
        Set<Coordinate> dontGo = new HashSet<>();
        dontGo.add(coordinate0);
        dontGo.add(coordinate1);

        float total = 4;
        int recDepth = 2;
        int maxDepth = 25;

        //startPilgrimage(res, matrix, dontGo, coordinate2);
        System.out.println(startPilgrimageRecursive(res, matrix, dontGo, coordinate2, total , coordinate2,
                recDepth, maxDepth));

        //display(res);
    }
}
