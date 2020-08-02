package dp;

public class CompleteCircuit {


    private boolean completeCircuit(int[] gas,int[] cost,int count){
        int sum = 0;
        for(int i = 0;i<gas.length;i++){
            int pos = (i+count)%gas.length;
            sum += gas[pos] - cost[pos];
            if(sum<0)return false;
        }
        return true;
    }

    public int canCompleteCircuit(int[] gas, int[] cost) {
        int len = gas.length;
        for(int i= 0;i<len;i++){
            if(gas[i]>=cost[i]){
                if(completeCircuit(gas,cost,i)){
                    return i;
                }
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        int[] gas = {2,3,4};
        int[] cost = {3,4,3};
        CompleteCircuit completeCircuit = new CompleteCircuit();
        int result = completeCircuit.canCompleteCircuit(gas,cost);
        System.out.println(result);
    }

}
