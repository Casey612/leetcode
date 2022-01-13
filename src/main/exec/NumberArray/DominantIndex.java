package exec.NumberArray;

/**
 * # 747
 */
public class DominantIndex {

    public int dominantIndex(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            return 0;
        }
        int bigger = -1, biggest = -1;
        int result = -1;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num > biggest) {
                bigger = biggest;
                biggest = num;
                result = i;
            } else if (num > bigger) {
                bigger = num;
            }
        }
        return biggest >= (bigger * 2) ? result : -1;
    }

    public static void main(String[] args) {
        DominantIndex index = new DominantIndex();
        System.out.println(index.dominantIndex(new int[]{1, 2, 3, 4}));
    }

}
