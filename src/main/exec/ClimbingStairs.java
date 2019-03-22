package exec;

public class ClimbingStairs {

    public int climbStairs(int n) {
        if(n == 1){
            return 1;
        }
        int[] array = new int[n];
        array[0] = 1;
        array[1] = 2;
        for(int i = 2; i < n; i++){
            array[i] = array[i - 1] + array[i - 2];
        }
        return array[n - 1];
    }

}
