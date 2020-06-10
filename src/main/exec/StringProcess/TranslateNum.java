package exec.StringProcess;

public class TranslateNum {

    public int translateNum(int num) {
        String str = String.valueOf(num);
        char[] chars = str.toCharArray();
        return translateNum(chars, 0, chars.length - 1);
    }

    private int translateNum(char[] chars, int start, int end) {
        if (end < start) {
            return 0;
        }
        if (start == end) {
            return 1;
        }
        char cur = chars[start];
        if (cur > '2' || start + 1 > end) {
            //不能够作为两位数的首位
            return translateNum(chars, start + 1, end);
        } else {
            //能够作为两位数的首位
            int result = translateNum(chars, start + 1, end);
            if ((cur == '2' && chars[start + 1] < '6') || (cur == '1')) {
                int temp = translateNum(chars, start + 2, end);
                result += temp == 0 ? 1 : temp;
            }
            return result;
        }
    }

    public static void main(String[] args) {
        TranslateNum translateNum = new TranslateNum();
        System.out.println(translateNum.translateNum(12258));
        System.out.println(translateNum.translateNum(25));
        System.out.println(translateNum.translateNum(28));
        System.out.println(translateNum.translateNum(18580));
    }

}
