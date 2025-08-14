package exec.NumberArray;

public class ErrorVersion {

    public static void main(String[] args){
        System.out.println(errorVersion(new boolean[]{true, true, true, false, false}));
        System.out.println(errorVersion(new boolean[]{true, true, false, false, false}));
        System.out.println(errorVersion(new boolean[]{true, true, true, true, true}));
        System.out.println(errorVersion(new boolean[]{false, false, false, false, false}));
        System.out.println(errorVersion(new boolean[]{false, false}));
        System.out.println(errorVersion(new boolean[]{true, false}));
        System.out.println(errorVersion(new boolean[]{true, true}));
    }

    public static int errorVersion(boolean[] ary) {
        if (ary == null || ary.length == 0) {
            return -1;
        }
        int mid = (ary.length - 1) / 2;
        do {
            if (ary[mid]) {
                //find right
                if (mid + 1 < ary.length && !ary[mid + 1]) {
                    return mid + 1;
                }
                mid += (ary.length - mid) / 2;
            } else {
                //find left
                if (mid - 1 >= 0 && ary[mid - 1]) {
                    return mid;
                }
                mid /= 2;
            }
        } while (mid > 0 && mid < ary.length - 1);
        return ary[mid] ? -1 : mid;
    }

}
