package exec.NumberArray;

public class TrappingRainWater {
    
    public int trap(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }
        
        int result = 0, leftIndex = 0, rightIndex = height.length - 1;
        
        while (leftIndex < (height.length - 2) && rightIndex < height.length) {
            
            int poolSize = calPool(height, leftIndex, rightIndex);
            System.out.println("poolSize:" + poolSize);
            if (poolSize > 0) {
                //构成一个池塘
                result += poolSize;
                leftIndex = rightIndex;
                rightIndex = height.length - 1;
            } else {
                if (height[leftIndex] < height[rightIndex]) {
                    //左边较小
                    if (rightIndex > (leftIndex + 2)) {
                        //中间存在高柱子，一分为二
                        rightIndex--;
                    } else {
                        //没有比左边更小的柱子 更换左侧
                        leftIndex++;
                        rightIndex = height.length - 1;
                    }
                } else {
                    //右边较小
                    if (rightIndex < (leftIndex + 2)) {
                        //没有比左边更大的柱子 更换左边
                        leftIndex++;
                        rightIndex = height.length - 1;
                    } else {
                        rightIndex--;
                    }
                }
            }
        }
        
        return result;
    }
    
    public int trap2(int[] height) {
        if (height == null || height.length < 3) {
            return 0;
        }
        
        int result = 0, leftIndex = 0, rightIndex = height.length - 1;
        
        while (leftIndex < (height.length - 2) && rightIndex < height.length) {
            
            int poolSize = calPool(height, leftIndex, rightIndex);
            System.out.println("poolSize:" + poolSize);
            if (poolSize > 0) {
                //构成一个池塘
                result += poolSize;
                leftIndex = rightIndex;
                rightIndex = height.length - 1;
            } else {
                if (height[leftIndex] < height[rightIndex]) {
                    //左边较小
                    if (height[leftIndex] == 0) {
                        leftIndex++;
                        rightIndex = height.length - 1;
                    } else if (rightIndex > (leftIndex + 2)) {
                        //中间存在高柱子，一分为二
                        //从左侧寻找最高的柱子
                        int repeat = rightIndex;
                        rightIndex = leftIndex + 2;
                        while (rightIndex < height.length && height[rightIndex] < height[leftIndex]) {
                            rightIndex++;
                        }
                        if (rightIndex == repeat) {
                            leftIndex++;
                            rightIndex = height.length - 1;
                        }
                    } else {
                        //没有比左边更小的柱子 更换左侧
                        leftIndex++;
                        rightIndex = height.length - 1;
                    }
                } else {
                    //右边较小
                    if (rightIndex < (leftIndex + 2)) {
                        //没有比左边更大的柱子 更换左边
                        leftIndex++;
                        rightIndex = height.length - 1;
                    } else {
                        int temp = height[rightIndex];
                        rightIndex--;
                        while (rightIndex > (leftIndex + 1) && height[rightIndex] < temp) {
                            rightIndex--;
                        }
                    }
                }
            }
        }
        
        return result;
    }
    
    private int calPool(int[] height, int leftIndex, int rightIndex) {
        if (height == null || height.length == 0 || (rightIndex - leftIndex) <= 1) {
            return 0;
        }
        System.out.println(leftIndex + ":" + rightIndex);
        int left = height[leftIndex];
        int right = height[rightIndex];
        int min = left < right ? left : right;
        if (min == 0) {
            return 0;
        }
        
        int result = 0;
        for (int index = leftIndex + 1; index < rightIndex; index++) {
            if (height[index] < min) {
                result += (min - height[index]);
            } else {
                return 0;
            }
        }
        return result;
    }
    
    public static void main(String[] args) {
        TrappingRainWater water = new TrappingRainWater();
        //int r1 = water.trap2(new int[] {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1});
        //assert r1 == 6;
        //System.out.println(r1);
        //int r2 = water.trap2(new int[] {4, 2, 0, 3, 2, 5});
        //assert r2 == 9;
        //System.out.println(r2);
        //int r3 = water.trap2(new int[] {2, 1, 0, 2});
        //assert r3 == 3;
        //System.out.println(r3);
        
        //int r4 = water.trap(new int[] {5, 5, 1, 7, 1, 1, 5, 2, 7, 6});
        //int r4 = water.trap2(new int[] {5, 5, 1, 7, 1, 1, 5, 2, 7, 6});
        //System.out.println(r4);
    
        int r5 = water.trap2(new int[] {0, 7, 1, 4, 6});
        System.out.println(r5);
    }
    
}
