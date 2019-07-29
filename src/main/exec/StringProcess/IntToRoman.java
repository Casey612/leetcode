package exec.StringProcess;

/**
 * @author yuzhe
 * @since 3/5/19
 */
public class IntToRoman {

    public static String intToRoman(int num) {
        if (num < 1 || num > 3999) {
            return null;
        }
        String M[] = {"", "M", "MM", "MMM"};
        String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        StringBuilder sb = new StringBuilder();
        int digit = 1000;
        while (num > 0) {
            int mod = num / digit;
            switch (digit) {
                case 1000:
                    sb.append(M[mod]);
                    break;
                case 100:
                    sb.append(C[mod]);
                    break;
                case 10:
                    sb.append(X[mod]);
                    break;
                default:
                    sb.append(I[mod]);
            }
            num %= digit;
            digit /= 10;
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(intToRoman(3));
        System.out.println(intToRoman(4));
        System.out.println(intToRoman(9));
        System.out.println(intToRoman(58));
        System.out.println(intToRoman(1994));
    }

}
