package exec.NumberArray;

import java.util.ArrayList;
import java.util.List;

public class KidWithGreatestCandies {

    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List<Boolean> result = new ArrayList<>();
        if (candies == null || candies.length == 0) {
            return result;
        }
        int max = 0;
        for (int candy : candies) {
            max = Math.max(candy, max);
        }
        for (int candy : candies) {
            if (max - candy <= extraCandies) {
                result.add(true);
            } else {
                result.add(false);
            }
        }
        return result;
    }

}
