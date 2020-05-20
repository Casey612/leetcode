package exec.StringProcess;

/**
 * If version1 > version2 return 1;
 * if version1 < version2 return -1;
 * otherwise return 0.
 */
public class CompareVersionNumber {

    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        int index = 0;
        while (index < v1.length || index < v2.length) {
            int v1Num = index < v1.length ? Integer.valueOf(v1[index]) : 0;
            int v2Num = index < v2.length ? Integer.valueOf(v2[index]) : 0;
            if (v1Num > v2Num) {
                return 1;
            } else if (v1Num < v2Num){
                return -1;
            }
            index++;
        }
        return 0;
    }

    public static void main(String[] args) {
        CompareVersionNumber compare = new CompareVersionNumber();
        System.out.println(compare.compareVersion("0.1", "1.1"));
    }

}
