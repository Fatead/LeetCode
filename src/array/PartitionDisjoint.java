package array;

public class PartitionDisjoint {

    private int getMax(int[] A,int left,int right){
        int max = Integer.MIN_VALUE;
        for (int i = left; i <right ; i++) {
            max = Math.max(A[i],max);
        }
        return max;
    }

    private int getMin(int[] A,int left,int right){
        int min = Integer.MAX_VALUE;
        for (int i = left; i <right ; i++) {
            min = Math.min(A[i],min);
        }
        return min;
    }

    public int partitionDisjoint(int[] A) {
        int min = Integer.MAX_VALUE;
        int minPos = 0;
        for (int i = 0; i <A.length ; i++) {
            if(min>A[i]){
                min = A[i];
                minPos = i;
            }
        }
        int leftMax = getMax(A,0,minPos);
        for (int i = minPos; i <A.length ; i++) {
            if(A[i]<leftMax)continue;
            leftMax = getMax(A,0,i);
            int rightMin = getMin(A,i,A.length);
            if(leftMax<=rightMin){
                if(i == 0)return 1;
                return i;
            }
        }
        return minPos+1;
    }

    public static void main(String[] args) {
        PartitionDisjoint partitionDisjoint = new PartitionDisjoint();
        int[] A = {0,0,0,0,1};
        System.out.println(partitionDisjoint.partitionDisjoint(A));
    }

}
