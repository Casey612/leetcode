package exec;

public class PlusOne {

    public int[] plusOne(int[] digits) {
        if (digits == null || digits.length == 0) {
            return null;
        }
        int pre = 0;
        for (int i = digits.length - 1; i >= 0; i--) {
            int cur = digits[i];
            if(i == (digits.length - 1)){
                digits[i] = (cur + 1 + pre) % 10;
                pre = (cur + 1 + pre) / 10;
            }else {
                digits[i] = (cur + pre) % 10;
                pre = (cur + pre) / 10;
            }
        }
        if (pre != 0) {
            int[] result = new int[digits.length + 1];
            result[0] = pre;
            for (int i = 0; i < digits.length; i++) {
                result[i + 1] = digits[i];
            }
            return result;
        } else {
            return digits;
        }
    }

}
