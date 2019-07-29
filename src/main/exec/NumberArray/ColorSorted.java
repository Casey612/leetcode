package exec.NumberArray;

public class ColorSorted {

    public void sortColors(int[] nums) {
        int[] counts = new int[3];
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < 3; j++) {
                if (nums[i] == j) {
                    counts[j]++;
                }
            }
        }
        int index = 0;
        for(int j = 0; j < 3; j++){
            while(counts[j] > 0){
                nums[index] = j;
                counts[j]--;
                index++;
            }
        }
    }

}
