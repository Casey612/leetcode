package exec;

public class MyAtoi {
    public static int myAtoi(String str) {
        if (str == null || str.trim().length() == 0) {
            return 0;
        }
        str = str.trim();
        int result = 0, digit;
        boolean negFlag = false, start = false;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (!start && (c == '+' || c == '-')) {
                start = true;
                if (c == '-') {
                    negFlag = true;
                }
            } else if ('0' <= c && c <= '9') {
                start = true;
                digit = c - '0';
                if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && digit > 7)) {
                    //overflow
                    if (negFlag) {
                        return Integer.MIN_VALUE;
                    } else {
                        return Integer.MAX_VALUE;
                    }
                }
                result = result * 10 + digit;
            } else {
                break;
            }
        }
        if (negFlag) {
            return result * -1;
        } else {
            return result;
        }
    }


    public static void main(String[] args) {
        String s = "-91283472332";
        System.out.println(myAtoi(s));
        String s1 = "+-2";
        System.out.println(myAtoi(s1));
        String s2 = "  -1123u3761867";
        System.out.println(myAtoi(s2));
    }

}
