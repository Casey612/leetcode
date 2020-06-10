package exec.StringProcess;

public class ValidPalindrome {

    public boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return true;
        }
        int start = 0, end = s.length() - 1;
        while (start <= end) {

            char pre = s.charAt(start), tail = s.charAt(end);
            while (!((pre >= 'a' && pre <= 'z') || (pre >= 'A' && pre <= 'Z') || (pre >= '0' && pre <= '9'))) {
                start++;
                if (start < s.length()) {
                    pre = s.charAt(start);
                } else {
                    //no letter
                    return true;
                }
            }

            while (!((tail >= 'a' && tail <= 'z') || (tail >= 'A' && tail <= 'Z') || (tail >= '0' && tail <= '9'))) {
                end--;
                if (end >= 0) {
                    tail = s.charAt(end);
                } else {
                    //no letter
                    return true;
                }
            }

            //大小写归一化
            if (pre >= 'A' && pre <= 'Z') {
                pre = (char) ('a' + (pre - 'A'));
            }
            if (tail >= 'A' && tail <= 'Z') {
                tail = (char) ('a' + (tail - 'A'));
            }
            if (pre == tail) {
                start++;
                end--;
            } else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        ValidPalindrome valid = new ValidPalindrome();
        System.out.println(valid.isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(valid.isPalindrome("race a car"));
    }

}