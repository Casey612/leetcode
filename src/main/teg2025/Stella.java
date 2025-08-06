package teg2025;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Stella {

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        List<String> resultList = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            String code = scanner.next();
            int k = scanner.nextInt();
            String result = getK(code, k);
            resultList.add(result);
        }
        for (int i = 0; i < n; i++) {
            System.out.println(resultList.get(i));
        }
    }

    public static final List<String> codeList = Arrays.asList("O", "B", "A", "F", "G", "K", "M");
    private static String getK(String code, int k) {
        String letter = code.substring(0, 1);
        int num = Integer.parseInt(code.substring(1));
        int curIndex = codeList.indexOf(letter);

        int total = curIndex * 10 + num;
        if (total < k) {
            return "Invalid";
        }
        int count = total - k;
        int index = count / 10;

        return codeList.get(index) + count % 10;
    }

}
