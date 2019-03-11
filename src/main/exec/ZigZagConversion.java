package exec;

import com.sun.deploy.util.StringUtils;

public class ZigZagConversion {

    public static String convert(String s, int numRows) {
        if(s == null || s.length() == 0 || numRows == 0){
            return "";
        }
        if(numRows == 1){
            return s;
        }
        int length = s.length();
        int setSize = numRows * 2 - 2;
        int setNum = length / setSize;
        if (length % setSize != 0) {
            setNum++;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numRows; i++) {
            //first row or last row
            for (int j = 0; j < setNum; j++) {
                //each row has 2 nums
                int index = j * setSize + i;
                if (index < length) {
                    sb.append(s.charAt(index));
                }
                if (i != 0 && i != (numRows - 1)) {
                    index = j * setSize + (setSize - i);
                    if (index < length) {
                        sb.append(s.charAt(index));
                    }
                }

            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String ori = "PAYPALISHIRING";
        String r1 = convert(ori, 3);
        String r2 = convert(ori, 4);
        System.out.println(r1);
        System.out.println(r2);
    }
}
