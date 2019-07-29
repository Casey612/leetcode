package exec.StringProcess;

/**
 * @author yuzhe
 * @since 3/11/19
 */
public class MultiplyStrings {

    public static String multiply(String num1, String num2) {
        if (num1 == null || num2 == null || num1.length() == 0 || num2.length() == 0) {
            return null;
        }
        String mul1, mul2;
        if (num1.length() > num2.length()) {
            mul1 = num1;
            mul2 = num2;
        } else {
            mul1 = num2;
            mul2 = num1;
        }
        String[] array = new String[mul2.length()];
        for (int index = mul2.length() - 1; index >= 0; index--) {
            char c = mul2.charAt(index);
            array[index] = mul(mul1, c, mul2.length() - index);
        }
        return addStrings(array);
    }

    private static String addStrings(String[] array) {
        if (array.length == 1) {
            return array[0];
        }
        int longestLength = 0;
        for (String s : array) {
            longestLength = longestLength > s.length() ? longestLength : s.length();
        }
        int index = 1, higherCount = 0;
        StringBuilder sb = new StringBuilder();
        while (index <= longestLength) {
            int temp = higherCount;
            for (String s : array) {
                int i = s.length() - index;
                int c = 0;
                if (s.length() > i && i >= 0) {
                    c = s.charAt(i) - '0';
                }
                temp += c;
            }
            sb.insert(0, (temp % 10));
            higherCount = temp / 10;
            index++;
        }
        if (higherCount != 0) {
            sb.insert(0, higherCount);
        }
        return sb.toString();
    }

    private static String mul(String mul1, char c, int times) {
        int num = c - '0';
        if (num == 0) {
            return "0";
        }
        int higherCount = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = mul1.length() - 1; i >= 0; i--) {
            int cur = mul1.charAt(i) - '0';
            int temp = num * cur + higherCount;
            sb.insert(0, (temp % 10));
            higherCount = temp / 10;
        }
        if (higherCount != 0) {
            sb.insert(0, higherCount);
        }
        while (times > 1) {
            sb.append(0);
            times--;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(mul("99", '9', 2));
//        System.out.println(addStrings(new String[]{"123", "11", "3210"}));
        System.out.println(multiply("9", "99"));
//        System.out.println(multiply("2", "3"));
//        System.out.println(multiply("123", "456"));
    }

}
