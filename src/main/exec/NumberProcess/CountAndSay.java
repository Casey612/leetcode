package exec.NumberProcess;

public class CountAndSay {
    
    private static  String[] array = new String[30];
    
    public String countAndSay(int n) {
        if (n < 1 || n > 30) {
            return null;
        }
        array[0] = "1";
        
        String result = array[n - 1];
        if (result != null) {
            return result;
        } else {
            String lastStr = array[n - 2];
            if (lastStr == null) {
                lastStr = countAndSay(n - 1);
                array[n - 2] = lastStr;
            }
            String temp = countAndSay(lastStr);
            array[n - 1] = temp;
            return array[n - 1];
        }
    }
    
    public String countAndSay(String str) {
        if (str == null || str.trim().length() == 0) {
            return null;
        }
        int count = 1;
        char num = str.charAt(0);
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < str.length(); i++) {
            char cur = str.charAt(i);
            if (num != cur) {
                result.append(count).append(num);
                count = 1;
                num = cur;
            } else {
                count++;
            }
        }
        result.append(count).append(num);
        return result.toString();
    }
    
    public static void main(String[] args) {
        CountAndSay countAndSay = new CountAndSay();
        
        String r1 = countAndSay.countAndSay(1);
        System.out.println(r1);
        
        String r2 = countAndSay.countAndSay(4);
        System.out.println(r2);
        
        String r3 = countAndSay.countAndSay(6);
        System.out.println(r3);
    }
    
}
