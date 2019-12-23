package misc;

abstract class Heap<T extends Comparable<T>>{
    T[]  heap;
    private int size;
    private int maxSize;

    public void add(T item){
        if(size<maxSize-1){
            heap[size++]=item;
            int current = size;
            int parent = parent(current);
            while(current>=0 && heap[current].compareTo(heap[parent])==-1){
                swap( current, parent );
                current =  parent;
            }
        }else{
            pop();
            add(item);
        }
    }
    public T pop(){
        T top=heap[0];
        heap[0]=heap[size];
        size--;
        heapify(0);
        return top;
    }
    abstract public int compareTo(T e);
    public T peek(){
        return heap[0];
    }
    public boolean isEmpty(){
        return size==0;
    }
    private void swap(int i, int j){
        T temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
    private void heapify(int root){
        int left = leftChild(root);
        int right = rightChild(root);
        int shouldBeTop = root;
        if(left<heap.length && heap[shouldBeTop].compareTo(heap[left])==-1)
            shouldBeTop = left;
        if(right<heap.length && heap[shouldBeTop].compareTo(heap[right])==-1)
            shouldBeTop = right;
        if(shouldBeTop!=root)
            swap(shouldBeTop, root);
        heapify(shouldBeTop);
    }
    private int parent(int current){
        return current/2;
    }
    private int leftChild(int pos)
    {
        return 2*pos;
    }
    private int rightChild(int pos)
    {
        return 2*pos+1;
    }
}

class MaxHeap<Integer extends Comparable<Integer>> extends Heap<Integer>{

    public MaxHeap(int k) {
    }

    @Override
    public int compareTo(Integer e) {
        return e.compareTo(this.peek());
    }

}

class MinHeap<Integer extends Comparable<Integer>> extends Heap<Integer>{

    public MinHeap(int k) {

    }

    @Override
    public int compareTo(Integer e) {
        return this.peek().compareTo(e);
    }

}