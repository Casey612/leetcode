package contest_108;

/**
 * @author: yuki
 * @date: 2018/10/28
 */
public class BeautyArray {
    public int[] beautifulArray(int N) {
        if(N == 1){
            return new int[]{1};
        } else if(N == 2){
            return new int[]{2, 1};
        } else {
            int[] result = new int[N];
            int[] last = beautifulArray(N - 1);
            //todo
            return result;
        }
    }

}
