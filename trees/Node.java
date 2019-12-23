package trees;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Node {
    int data;
    Node left;
    Node right;
    private Node(){

    }
    Node (int d){
        this.data = d;
        this.left = null;
        this.right = null;
    }
}
class NextRightNode extends Node{
    NextRightNode nextRight;

    NextRightNode(int d){
        super(d);
        this.nextRight = null;
    }
}

