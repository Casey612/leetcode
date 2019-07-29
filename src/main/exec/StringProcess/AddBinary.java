package exec.StringProcess;

public class AddBinary {

    public static String addBinary(String a, String b) {
        if (a == null || a.length() == 0 || b == null || b.length() == 0) {
            return null;
        }
        int l1 = a.length(), l2 = b.length();
        int pre = 0, left, right, leftIndex = l1 - 1, rightIndex = l2 - 1;
        StringBuilder sb = new StringBuilder();
        while (leftIndex >= 0 || rightIndex >= 0) {
            if (leftIndex >= 0) {
                left = a.charAt(leftIndex) - '0';
                leftIndex--;
            } else {
                left = 0;
            }
            if (rightIndex >= 0) {
                right = b.charAt(rightIndex) - '0';
                rightIndex--;
            } else {
                right = 0;
            }
            int cur = (left + right + pre) % 2;
            sb.insert(0, (char) (cur + '0'));
            pre = (left + right + pre) / 2;
        }
        if (pre != 0) {
            sb.insert(0, (char) (pre + '0'));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(addBinary("11", "1"));
    }

}
