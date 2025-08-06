package teg2025;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SignInProblem {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int times = scanner.nextInt();
        List<Integer> resultList = new ArrayList<>(times);
        for (int i = 0; i < times; i++) {
            int n = scanner.nextInt();
            int k = scanner.nextInt();
            String array = scanner.nextLine();
            int result = getSignInProblem(n, k, array);
            resultList.add(result);
        }
        for (int i = 0; i < times; i++) {
            System.out.println(resultList.get(i));
        }
    }

    private static int getSignInProblem(int n, int k, String array) {
        //补签

        //计算断签
        return getProblemTimes(n, array);
    }



    public static int getProblemTimes(int n, String array) {
        boolean start = false;
        int result = 0;
        for (int i = 0; i < n; i++) {
            char c = array.charAt(i);
            if (c == '1') {
                if (!start) {
                    start = true;
                }
            } else {
                if (start) {
                    result++;
                    start = false;
                }
            }
        }
        return result;
    }

}
