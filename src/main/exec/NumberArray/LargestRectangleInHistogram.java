package exec.NumberArray;

import java.util.Stack;

public class LargestRectangleInHistogram {
    /**
     * 时间复杂度 O(n^2)
     * 边长可变 1 - n
     * 起始位置可变 0 - (n - 1)
     * 分别计算面积，求最大值
     * @param heights
     * @return
     */
    public int largestRectangleArea1(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int result = 0;
        for (int width = heights.length; width > 0; width--) {
            for (int start = 0; start <= heights.length - width; start++) {
                int temp = calculateSize(start, width, heights);
                if (temp > result) {
                    result = temp;
                }
            }
        }
        return result;
    }
    
    private int calculateSize(int start, int width, int[] heights) {
        
        if (heights == null || heights.length == 0 || width == 0) {
            return 0;
        }
        int min = heights[start];
        for (int i = start + 1; i < start + width; i++) {
            if (heights[i] < min) {
                min = heights[i];
            }
        }
        return min * width;
    }
    
    /**
     * 将序列拆分为局部升序的子序列
     * 首先判断升序子序列内部，是否有更大的面积
     * 之后联系下一个升序子序列，看是否存在潜在的更大值
     * 若不存在，则单独看下一个子序列
     * 若存在，则将前一个升序子序列看作是第二个升序子序列的一部分，大于开头的部分视同相同（消峰）。
     * @param heights
     * @return
     */
    public int largestRectangleArea2(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        int result = 0;
        Stack<Integer> indexStack = new Stack<>();
        for (int index = 0; index < heights.length; index++) {
            int cur = heights[index];
            int lastIndex = -1;
            int sideLength = -1;
            while (!indexStack.isEmpty() && heights[indexStack.peek()] > cur) {
                lastIndex = indexStack.pop();
                
                if (sideLength == -1) {
                    sideLength = heights[lastIndex];
                } else {
                    sideLength = Math.min(heights[lastIndex], sideLength);
                }
                //不包含当前柱 只看升序序列构成的面积
                while (lastIndex > 0 && heights[lastIndex - 1] >= sideLength) {
                    lastIndex--;
                }
                
                result = Math.max(result, sideLength * (index - lastIndex));
            }
            //包含当前柱 当前柱最小
            if (lastIndex != -1 && heights[lastIndex] > cur) {
                int area = cur * (index - lastIndex + 1);
                if (area >= result) {
                    result = area;
                    indexStack.push(lastIndex);
                }
            }
            if (cur == 0) {
                indexStack.clear();
            } else {
                indexStack.push(index);
            }
        }
        int sideLength = -1;
        while (!indexStack.isEmpty()) {
            int lastIndex = indexStack.pop();
            if (sideLength == -1) {
                sideLength = heights[lastIndex];
            } else {
                sideLength = Math.min(heights[lastIndex], sideLength);
            }
            while (lastIndex > 0 && heights[lastIndex - 1] >= sideLength) {
                lastIndex--;
            }
            result = Math.max(result, sideLength * (heights.length - lastIndex));
        }
        return result;
    }
    
    public static void main(String[] args) {
        LargestRectangleInHistogram test = new LargestRectangleInHistogram();
        //System.out.println(test.largestRectangleArea2(new int[] {2, 1, 5, 6, 2, 3}));
        //System.out.println(test.largestRectangleArea2(new int[] {1, 2, 3, 4, 5}));
        //System.out.println(test.largestRectangleArea2(new int[] {2, 2, 3, 4, 5, 3, 4}));
        //System.out.println(test.largestRectangleArea2(new int[] {2, 1, 2}));
        //System.out.println(test.largestRectangleArea2(new int[] {2, 1, 0, 2}));
        //System.out.println(test.largestRectangleArea2(new int[] {6, 4, 2, 0, 3, 2, 0, 3, 1, 4, 5, 3, 2, 7, 5, 3, 0,
        // 1, 2, 1, 3, 4, 6, 8, 1, 3}));
        //System.out.println(test.largestRectangleArea2(new int[] {5, 4, 4, 6, 3, 2, 9, 5, 4, 8, 1, 0, 0, 4, 7, 2}));
        System.out.println(test.largestRectangleArea2(new int[] {4, 3, 5, 5, 9, 2, 8, 4, 7, 2, 3, 8, 3, 5, 4, 7, 9}));
    }
    
}
