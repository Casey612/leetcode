package exec.NumberArray;

import java.util.Arrays;

public class JumpGame2 {
    
    public int jump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        return jump(nums, 0, nums.length - 1);
    }
    
    private int jump(int[] nums, int startIndex, int endIndex) {
        if (nums == null || nums.length == 0 || startIndex > endIndex) {
            return 0;
        }
        
        System.out.println(startIndex + " - " + endIndex);
        int cur = nums[startIndex];
        
        if (cur == 0) {
            return nums.length;
        }
        if (cur >= (endIndex - startIndex)) {
            return 1;
        }
        
        int[] array = new int[cur];
        
        for (int i = 0; i < cur; i++) {
            array[i] = jump(nums, startIndex + i + 1, endIndex);
        }
        System.out.println(Arrays.toString(array));
        Arrays.sort(array);
        return array[0] + 1;
    }
    
    public int jump2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int length = nums.length;
        
        if (length == 1) {
            return 0;
        }
        
        int num = nums[0];
        
        if (num >= (length - 1)) {
            return 1;
        } else if (num + nums[num] >= (length - 1)){
            return 2;
        }
        
        boolean sameFlag = true;
        
        for (int temp : nums) {
            if (num != temp) {
                sameFlag = false;
            }
        }
        
        //全1矩阵
        if (sameFlag) {
            if (num != 0) {
                return length / num - 1;
            } else {
                return 0;
            }
        }
        
        //动态规划矩阵
        int[][] metric = new int[length][length];
        
        for (int i = 0; i < length; i++) {
            for (int j = i; j < length; j++) {
                if (i == j) {
                    metric[i][j] = 0;
                } else {
                    int cur = nums[i];
                    if (cur >= (j - i)) {
                        metric[i][j] = 1;
                    }
                }
            }
        }
        
        metricToString(metric);
        
        for (int row = length - 2; row >= 0; row--) {
            for (int col = row + 1; col < length; col++) {
                if (metric[row][col] == 0 && (col - row) > 1) {
                    System.out.println(row + ":" + col);
                    int[] temp = new int[col - row - 1];
                    int index = 0;
                    for (int i = row + 1; i < col; i++) {
                        if (metric[row][i] != 0 && metric[i][col] != 0) {
                            temp[index] = metric[row][i] + metric[i][col];
                        } else {
                            temp[index] = length;
                        }
                        index++;
                        System.out.println(metric[row][i] + ":" + metric[i][col]);
                    }
                    System.out.println("temp:" + Arrays.toString(temp));
                    Arrays.sort(temp);
                    metric[row][col] = temp[0];
                    metricToString(metric);
                }
            }
        }
        return metric[0][length - 1];
    }
    
    private void metricToString(int[][] metric) {
        for (int[] ints : metric) {
            System.out.println(Arrays.toString(ints));
        }
        System.out.println("---------");
    }
    
    public static void main(String[] args) {
        JumpGame2 game2 = new JumpGame2();
        
        int r11 = game2.jump(new int[] {2, 3, 0, 1, 4});
        System.out.println(r11);
        int r12 = game2.jump2(new int[] {2, 3, 0, 1, 4});
        System.out.println(r12);
        
        //int r21 = game2.jump2(new int[] {5, 9, 3, 2, 1, 0, 2, 3, 3, 1, 0, 0});
        //System.out.println(r21);
        //int r22 = game2.jump(new int[] {5, 9, 3, 2, 1, 0, 2, 3, 3, 1, 0, 0});
        //System.out.println(r22);
    }
    
}
