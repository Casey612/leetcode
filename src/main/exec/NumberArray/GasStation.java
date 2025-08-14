package exec.NumberArray;

public class GasStation {

    public static void main(String[] args){
        System.out.println(canCompleteCircuit(new int[]{1, 2, 3, 4, 5}, new int[]{3, 4, 5, 1, 2}));
        System.out.println(canCompleteCircuit(new int[]{2,3,4}, new int[]{3,4,3}));
    }


    public static int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas == null || cost == null) {
            return -1;
        }
        int[] left = new int[gas.length];
        for (int i = 0; i < gas.length; i++) {
            left[i] = gas[i] - cost[i];
        }
        if (gas.length == 1) {
            return left[0] >= 0 ? 0 : -1;
        }
        for(int i = 0; i < gas.length; i++) {
            if (left[i] > 0 && canTravel(i, gas, cost)) {
                return i;
            }
        }
        return -1;
    }

    private static boolean canTravel(int idx, int[] gas, int[] cost) {
        int length = gas.length;
        int tank = gas[idx];
        int travelIndex = idx;
        do {
            tank -= cost[travelIndex];
            if (tank < 0) {
                return false;
            }
            travelIndex = (travelIndex + 1) % length;
            tank += gas[travelIndex];
//            System.out.println("travel to station" + travelIndex + ", tank is " + tank);
        } while(travelIndex != idx);
        return true;
    }

}
