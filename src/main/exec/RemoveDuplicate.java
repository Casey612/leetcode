package exec;

public class RemoveDuplicate {

    public static int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length <= 2) {
            return nums.length;
        }
        int pre = 0, tail = 1, length = nums.length;
        while (tail < length) {
            int cur = nums[pre];
            if (nums[tail] == cur) {
                tail++;
            } else {
                if (tail - pre <= 2) {
                    pre = tail;
                    tail++;
                } else {
                    pre += 2;
                    int temp = pre;
                    length -= (tail - pre);
                    while (tail < nums.length) {
                        nums[pre] = nums[tail];
                        pre++;
                        tail++;
                    }
                    pre = temp;
                    tail = pre + 1;
                }
            }
        }
        System.out.println(tail + " : " + pre);
        return (tail - pre) >= 2 ? pre + 2 : pre + 1;
    }

    public static void main(String[] args) {
//        System.out.println(removeDuplicates(new int[]{0,0,1,1,1,1,2,3,3}));
//        System.out.println(removeDuplicates(new int[]{1,1,1}));
        System.out.println(removeDuplicates(new int[]{1,2,2}));
        System.out.println(removeDuplicates(new int[]{1,1,1,2,2,3}));
    }

}
