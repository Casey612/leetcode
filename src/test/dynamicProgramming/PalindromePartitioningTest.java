package dynamicProgramming;

import exec.dynamicProgramming.PalindromePartitioning;
import org.junit.jupiter.api.Assertions;

public class PalindromePartitioningTest {

    public static void main(String[] args){
        Assertions.assertTrue(PalindromePartitioning.isPalindrome("a", 0,0));
        Assertions.assertTrue(PalindromePartitioning.isPalindrome("aba", 0, 2));
        Assertions.assertTrue(PalindromePartitioning.isPalindrome("aa", 0, 1));
        Assertions.assertTrue(PalindromePartitioning.isPalindrome("acca", 0, 3));
        Assertions.assertFalse(PalindromePartitioning.isPalindrome("acbd", 0, 3));
        Assertions.assertFalse(PalindromePartitioning.isPalindrome("ab", 0, 1));
    }


}
