package exec;

public class PalindromeNumber {

    public static boolean isPalindrome(int x) {
        String s = String.valueOf(x);
        for(int i = 0; i < s.length() / 2; i++){
            char cur = s.charAt(i);
            char tail = s.charAt(s.length() - 1 - i);
            if(cur != tail){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome(121));
        System.out.println(isPalindrome(-121));
        System.out.println(isPalindrome(10));
    }
}
