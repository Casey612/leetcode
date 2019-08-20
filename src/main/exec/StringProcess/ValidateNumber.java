package exec.StringProcess;

public class ValidateNumber {
    
    public boolean isNumber(String s) {
        if (s == null || s.trim().length() == 0){
            return false;
        }
        s = s.trim();
        int index = s.indexOf('e');
        int lastIndex = s.lastIndexOf('e');
        if (index != lastIndex) {
            return false;
        }
        if (s.indexOf('e') != -1) {
            String[] exponentParts = s.split("e");
            if (exponentParts.length != 2) {
                return false;
            } else {
                return isNumberic(exponentParts[0]) && isInteger(exponentParts[1], true, false);
            }
        } else {
            return isNumberic(s);
        }
    }
    
    private boolean isInteger(String str, boolean signal, boolean onlySignal) {
        if (str == null || str.trim().length() == 0){
            return false;
        }
        //仅处理整数部分
        boolean result = onlySignal;
        for (int index = 0; index < str.length(); index++) {
            char c = str.charAt(index);
            if (signal && index == 0 && (c == '-' || c == '+')) {
                //是否允许首位符号开头
            } else if (c >= '0' && c <= '9') {
                //1-9数字正常
                result = true;
            } else {
                return false;
            }
        }
        return result;
    }
    
    private boolean isNumberic(String str) {
        if (str == null || str.trim().length() == 0){
            return false;
        }
        int index = str.indexOf('.');
        int lastIndex = str.lastIndexOf('.');
        if (index != lastIndex) {
            return false;
        }
        if (index != -1) {
            String[] exponentParts = str.split("\\.");
            if (exponentParts.length > 2 || exponentParts.length < 1) {
                return false;
            } else if (index != 0 && index != str.length() - 1 && exponentParts.length == 2){
                return isInteger(exponentParts[0], true, true) && isInteger(exponentParts[1], false, false);
            } else if (index == 0 && exponentParts.length == 2) {
                // ".xxx" -> {"", "xxx"}
                return isInteger(exponentParts[1], false, false);
            } else if (index == str.length() - 1){
                // "xxx." -> {"xxx"}
                return isInteger(exponentParts[0], true, false);
            } else {
                return false;
            }
        } else {
            return isInteger(str, true, false);
        }
    }
    
    public static void main(String[] args) {
        ValidateNumber validator = new ValidateNumber();
        //System.out.println(validator.isNumber("0"));
        //System.out.println(validator.isNumber("0.1"));
        //System.out.println(validator.isNumber("abc"));
        //System.out.println(validator.isNumber("1 a"));
        //System.out.println(validator.isNumber("2e10"));
        //System.out.println(validator.isNumber("-90e3"));
        //System.out.println(validator.isNumber("1e"));
        //System.out.println(validator.isNumber("e3"));
        //System.out.println(validator.isNumber("6e-1"));
        //System.out.println(validator.isNumber("99e2.5"));
        //System.out.println(validator.isNumber("63.5e93"));
        //System.out.println(validator.isNumber("--6"));
        //System.out.println(validator.isNumber("-+3"));
        //System.out.println(validator.isNumber("95a54e53"));
        //System.out.println(validator.isNumber("1 "));
        //System.out.println(validator.isNumber(".1"));
        //System.out.println(validator.isNumber(". 1"));
        //System.out.println(validator.isNumber("."));
        //System.out.println(validator.isNumber("0.."));
        System.out.println(validator.isNumber(".1."));
        System.out.println(validator.isNumber("4e+"));
    }
    
}
