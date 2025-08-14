package exec.NumberArray;

public class GasStation2 {

    public static void main(String[] args){
        System.out.println(canCompleteCircuit(new int[]{1, 2, 3, 4, 5}, new int[]{3, 4, 5, 1, 2}));
        System.out.println(canCompleteCircuit(new int[]{2,3,4}, new int[]{3,4,3}));
    }


    public static int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas == null || cost == null) {
            return -1;
        }
        int total = 0;
        int curTotal = 0;
        int start = 0;
        for (int i = 0; i < gas.length; i++) {
            total += gas[i] - cost[i];
            curTotal += gas[i] - cost[i];
            if (curTotal < 0) {
                curTotal = 0;
                start = i + 1;
            }
        }
        return total < 0 ? -1 : start;
    }


}
