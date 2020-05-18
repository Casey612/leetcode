package exec.StringProcess;

public class ValidPalindrome2 {

    public boolean validPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            char lChar = s.charAt(left), rChar = s.charAt(right);
            if (lChar == rChar) {
                left++;
                right--;
            } else {
                if (left + 1 == right) {
                    return true;
                } else {
                    char rs = s.charAt(right - 1), ls = s.charAt(left + 1);
                    if (lChar == rs) {
                        if (validPalindrome(s, left, right - 1)){
                            return true;
                        }
                    }
                    if (rChar == ls){
                        return validPalindrome(s, left + 1, right);
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public boolean validPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {
                return false;
            }
        }
        return true;
    }
}
