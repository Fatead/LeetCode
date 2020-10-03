package tree;

public class BinaryHeap {

    private int[] array;

    private int N = 0;

    public BinaryHeap(int capacity) {
        array = new int[capacity+1];
        N = capacity;
    }

    public int max(){
        return array[1];
    }

    private int parent(int k){
        return k/2;
    }

    private int left(int k){
        return k*2;
    }

    private int right(int k){
        return k*2 + 1;
    }

    public void insert(int element){

    }

    /**
     * 删除并返回当前队列中最大的元素
     */
//    public int delMax(){
//
//    }

    /**
     * 上浮第K个元素，以维护最大堆的性质
     */
    private void swim(int k){
        while(k>1 && less(parent(k),k)){
            exchange(k,parent(k));
            k = parent(k);
        }
    }

    /**
     * 下沉第K个元素，以维护最大堆的性质
     */
    private void sink(int k){
        while(left(k) <= N){
            int older = left(k);
            if(right(k) <=N && less(left(k),right(k))){
                older = right(k);
            }
            if(less(older,k))break;
            exchange(k,older);
            k = older;
        }
    }

    private void exchange(int i,int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    /**
     * @param i
     * @param j
     * @return
     * 比较 array[i] 是否比 array[j]小
     */
    private boolean less(int i,int j){
        if(array[i] < array[j]){
            return true;
        }else {
            return false;
        }
    }

    public static void main(String[] args) {

    }
}
