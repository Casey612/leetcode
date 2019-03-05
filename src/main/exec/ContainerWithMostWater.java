package exec;

/**
 * @author yuzhe
 * @since 3/5/19
 */
public class ContainerWithMostWater {

    public static int maxArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        int result = 0;
        int left = 0, right = height.length - 1;
        while (left < right) {
            int area = (right - left) * Integer.min(height[left], height[right]);
            result = result > area ? result : area;
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int result = maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7});
        System.out.println(result);
    }

}
