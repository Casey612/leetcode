package exec.NumberArray;

/**
 * # 665
 */
public class CheckPossibility {

    public boolean checkPossibility(int[] nums) {
        if (nums == null || nums.length <= 2) {
            return true;
        }
        int num = 0;
        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i - 1] > nums[i] && nums[i] > nums[i + 1]) {
                //3个逆序的数字
                return false;
            } else if (nums[i] > nums[i + 1]) {
                //nums[i - 1] <= nums[i] 正常
                //修正nums[i] or nums[i + 1]
                if (nums[ i - 1] <= nums[i + 1]) {
                    //修正nums[i]
                    nums[i] = nums[i - 1];
                    num++;
                } else {
                    //nums[i - 1] > nums[i + 1]
                    //alter nums[i + 1]
                    if (i + 2 < nums.length && nums[i + 2] >= nums[i]) {
                        nums[i + 1] = nums[i];
                        num++;
                    } else if (i + 2 == nums.length) {
                        nums[i + 1] = nums[i];
                        num++;
                    } else {
                        return false;
                    }
                }
            } else if (nums[i - 1] > nums[i]) {
                // nums[i] <= nums[i + 1]
                nums[i - 1] = nums[i];
                num++;
            }
        }
        return num <= 1;
    }

    public static void main(String[] args) {
        CheckPossibility possibility = new CheckPossibility();
//        System.out.println(possibility.checkPossibility(new int[]{-1, 4, 2, 3}));
        System.out.println(possibility.checkPossibility(new int[]{2, 3, 3, 2, 4}));
    }

}
