package exec.StringProcess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * # 76
 */
public class MinimumWindowSubstring {
    
    /**
     * 集合相乘法
     */
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0) {
            return "";
        }
        if (s.length() < t.length()) {
            return "";
        }
        if (t.length() == 1) {
            return s.contains(t) ? t : "";
        }
        if (s.contains(t)) {
            return t;
        }
        
        Map<Character, Set<Integer>> map = initIndexMap(s);
        
        char[] chars = t.toCharArray();
        Arrays.sort(chars);
        
        Set<List<Integer>> list = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        for (char c : chars) {
            sb.append(c);
            if (map.containsKey(c)) {
                list = multiply(list, map.get(c), s, sb);
                if (list.isEmpty()) {
                    return "";
                }
                //System.out.println(list.toString());
            } else {
                return "";
            }
        }
        List<Integer> resultIndex = minLength(list);
        return resultIndex == null ? "" : s.substring(resultIndex.get(0), resultIndex.get(1) + 1);
    }
    
    //生成字母和字符串坐标的对应关系
    private Map<Character, Set<Integer>> initIndexMap(String s) {
        Map<Character, Set<Integer>> map = new HashMap<>();
        for (int index = 0; index < s.length(); index++) {
            char c = s.charAt(index);
            if (map.containsKey(c)) {
                map.get(c).add(index);
            } else {
                Set<Integer> indexSet = new HashSet<>(8);
                indexSet.add(index);
                map.put(c, indexSet);
            }
        }
        return map;
    }
    
    private List<Integer> minLength(Collection<List<Integer>> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        List<Integer> result = new ArrayList<>(2);
        
        Iterator<List<Integer>> iterator = list.iterator();
        List<Integer> temp = iterator.next();
        
        if (temp.size() < 2) {
            return null;
        }
        int length = temp.get(1) - temp.get(0);
        
        while (iterator.hasNext()) {
            List<Integer> l = iterator.next();
            l.sort(Integer::compareTo);
            int tempLength = l.size() > 1 ? l.get(l.size() - 1) - l.get(0) : 1;
            if (tempLength < length) {
                length = tempLength;
                temp = l;
            }
        }
        result.add(temp.get(0));
        result.add(temp.get(temp.size() - 1));
        return result;
    }
    
    //集合差乘
    private Set<List<Integer>> multiply(Set<List<Integer>> list, Set<Integer> indexSet, String s, StringBuilder temp) {
        Set<List<Integer>> result = new HashSet<>();
        if (list.isEmpty()) {
            for (Integer index : indexSet) {
                List<Integer> item = new ArrayList<>();
                item.add(index);
                result.add(item);
            }
        } else {
            for (List<Integer> l : list) {
                for (Integer index : indexSet) {
                    int start = l.get(0);
                    int end = l.get(l.size() - 1);
                    if (index < start) {
                        List<Integer> item = new ArrayList<>();
                        item.add(index);
                        item.add(end);
                        result.add(item);
                    } else if (end < index) {
                        List<Integer> item = new ArrayList<>();
                        item.add(start);
                        item.add(index);
                        result.add(item);
                    } else if (index != start && index != end) {
                        if (satisfy(s, l.get(0), l.get(1), temp)) {
                            List<Integer> item = new ArrayList<>(l);
                            result.add(item);
                        }
                    }
                }
            }
        }
        return result;
    }
    
    private boolean satisfy(String s, Integer start, Integer end, StringBuilder temp) {
        StringBuilder sb = new StringBuilder(s.substring(start, end + 1));
        for (int i = 0; i < temp.length(); i++) {
            char c = temp.charAt(i);
            int index = sb.indexOf(String.valueOf(c));
            if (index == -1) {
                return false;
            } else {
                sb.deleteCharAt(index);
            }
        }
        return true;
    }
    
    //活动窗口法 窗口由大变小
    public String slidingWindow2(String s, String t) {
        
        if (s == null || t == null || s.length() == 0 || t.length() == 0 || s.length() < t.length()) {
            return "";
        }
        
        if (t.length() == 1) {
            return s.contains(t) ? t : "";
        }
        if (s.contains(t)) {
            return t;
        }
        
        Map<Character, Integer> map = initCharSizeMap(t, 0, t.length());
        
        List<Integer> targetIndex = new ArrayList<>();
        for (int index = 0; index < s.length(); index++) {
            char c = s.charAt(index);
            if (map.containsKey(c)) {
                targetIndex.add(index);
            }
        }
        int leftIndex = 0, rightIndex = targetIndex.size() - 1;
        int startIndex = 0, endIndex = s.length() - 1;
        int length = s.length();
        
        while (leftIndex <= rightIndex && rightIndex < targetIndex.size()) {
            int left = targetIndex.get(leftIndex);
            int right = targetIndex.get(rightIndex);
            if (sizeSatisfy(s, left, right, map)) {
                rightIndex--;
            } else {
                rightIndex++;
                if (rightIndex >= targetIndex.size()) {
                    break;
                }
                right = targetIndex.get(rightIndex);
                
                if (right - left + 1 < length) {
                    startIndex = left;
                    endIndex = right;
                    length = endIndex - startIndex + 1;
                }
                leftIndex++;
                rightIndex = targetIndex.size() - 1;
            }
        }
        if (startIndex == 0 && endIndex == s.length() - 1) {
            return sizeSatisfy(s, startIndex, endIndex, map) ? s : "";
        } else {
            return s.substring(startIndex, endIndex + 1);
        }
    }
    
    /**
     *  活动窗口法 窗口由小变大 277/278 passed WTF
     */
    public String slidingWindow(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0 || s.length() < t.length()) {
            return "";
        }
        
        if (t.length() == 1) {
            return s.contains(t) ? t : "";
        }
        if (s.contains(t)) {
            return t;
        }
        
        Map<Character, Integer> map = initCharSizeMap(t, 0, t.length());
        Map<Character, Integer> curSizeMap = new HashMap<>();
        int minLength = Integer.MAX_VALUE;
        String result = "";
        int left = 0;
        while (left < s.length()) {
            int right = left;
            while (right < s.length()) {
                char c = s.charAt(right);
                if (map.containsKey(c)) {
                    int num = 1;
                    if (curSizeMap.containsKey(c)) {
                        num += curSizeMap.get(c);
                    }
                    curSizeMap.put(c, num);
                    if (satisfy(curSizeMap, map)) {
                        int temp  = left;
                        while (temp < right) {
                            char tempC = s.charAt(temp);
                            if (map.containsKey(tempC)) {
                                int num1 = curSizeMap.get(tempC);
                                int num2 = map.get(tempC);
                                if (num1 - 1 < num2) {
                                    break;
                                }
                                curSizeMap.put(tempC, num1 - 1);
                            }
                            temp++;
                        }
                        if (minLength > (right - temp + 1)) {
                            minLength = right - temp + 1;
                            result = s.substring(temp, right + 1);
                        }
                        break;
                    }
                }
                right++;
            }
            do {
                left++;
            } while (left < s.length() && !map.containsKey(s.charAt(left)));
            curSizeMap.clear();
        }
        return result;
    }

    private boolean satisfy(Map<Character, Integer> map1, Map<Character, Integer> map2) {
        if (map1.keySet().size() != map2.keySet().size()) {
            return false;
        }
        for (Character c : map2.keySet()) {
            int num1 = map1.getOrDefault(c, 0);
            int num2 = map2.getOrDefault(c, 0);
            if (num1 < num2) {
                return false;
            }
        }
        return true;
    }

    private boolean sizeSatisfy(String s, int left, int right, Map<Character, Integer> map) {
        int size = right - left + 1;
        if (size < map.keySet().size()) {
            return false;
        }
        Map<Character, Integer> sMap = initCharSizeMap(s, left, right + 1);
        for (Character c : map.keySet()) {
            int sSize = sMap.getOrDefault(c, 0);
            int tSize = map.get(c);
            if (sSize < tSize) {
                return false;
            }
        }
        return true;
    }
    
    private Map<Character, Integer> initCharSizeMap(String t, int startIndex, int endIndex) {
        Map<Character, Integer> result = new HashMap<>();
        for (int i = startIndex; i < endIndex; i++) {
            char c = t.charAt(i);
            int size = 1;
            if (result.containsKey(c)) {
                size += result.get(c);
            }
            result.put(c, size);
        }
        return result;
    }
    
    public static void main(String[] args) {
        MinimumWindowSubstring solution = new MinimumWindowSubstring();
//        System.out.println(solution.minwindow1("ADOBECOPEBANC", "ABC"));
//        System.out.println(solution.minwindow1("bbaa", "aba"));
//        System.out.println(solution
//            .minwindow1("ask_not_what_your_country_can_do_for_you_ask_what_you_can_do_for_your_country", "ask_country"));
//        System.out.println(solution.minwindow1("abbbb", "aa"));
//        System.out.println(solution.minwindow1("aaaaaaaaaaaabbbbbcdd", "abcdd"));
//        System.out.println(solution.minwindow1
//         ("wegdtzwabazduwwdysdetrrctotpcepalxdewzezbfewbabbseinxbqqplitpxtcwwhuyntbtzxwzyaufihclztckdwccpeyonumbpnuonsnnsjscrvpsqsftohvfnvtbphcgxyumqjzltspmphefzjypsvugqqjhzlnylhkdqmolggxvneaopadivzqnpzurmhpxqcaiqruwztroxtcnvhxqgndyozpcigzykbiaucyvwrjvknifufxducbkbsmlanllpunlyohwfsssiazeixhebipfcdqdrcqiwftutcrbxjthlulvttcvdtaiwqlnsdvqkrngvghupcbcwnaqiclnvnvtfihylcqwvderjllannflchdklqxidvbjdijrnbpkftbqgpttcagghkqucpcgmfrqqajdbynitrbzgwukyaqhmibpzfxmkoeaqnftnvegohfudbgbbyiqglhhqevcszdkokdbhjjvqqrvrxyvvgldtuljygmsircydhalrlgjeyfvxdstmfyhzjrxsfpcytabdcmwqvhuvmpssingpmnpvgmpletjzunewbamwiirwymqizwxlmojsbaehupiocnmenbcxjwujimthjtvvhenkettylcoppdveeycpuybekulvpgqzmgjrbdrmficwlxarxegrejvrejmvrfuenexojqdqyfmjeoacvjvzsrqycfuvmozzuypfpsvnzjxeazgvibubunzyuvugmvhguyojrlysvxwxxesfioiebidxdzfpumyon","ozgzyywxvtublcl"));

//        System.out.println(solution.slidingWindow2("ADOBECOPEBANC", "ABC"));
//        System.out.println(solution.slidingWindow2("ADOBECOPEBANCABC", "ABC"));
//        System.out.println(solution
//            .slidingWindow2("ask_not_what_your_country_can_do_for_you_ask_what_you_can_do_for_your_country",
//            "ask_country"));
//        System.out.println(solution.slidingWindow2("abbbb", "aa"));
//        System.out.println(solution.slidingWindow2("aaaaaaaaaaaabbbbbcdd", "abcdd"));
//        System.out.println(solution.slidingWindow2("abc", "ac"));
//        System.out.println(solution.slidingWindow2("bba", "ab"));
//
//        System.out.println(solution.slidingWindow("ADOBECOPEBANC", "ABC"));
//        System.out.println(solution.slidingWindow("ADOBECOPEBANCABC", "ABC"));
//        System.out.println(solution
//            .slidingWindow("ask_not_what_your_country_can_do_for_you_ask_what_you_can_do_for_your_country",
//            "ask_country"));
//        System.out.println(solution.slidingWindow("abbbb", "aa"));
//        System.out.println(solution.slidingWindow("aaaaaaaaaaaabbbbbcdd", "abcdd"));
//        System.out.println(solution.slidingWindow("abc", "ac"));
//        System.out.println(solution.slidingWindow("bba", "ab"));
//        System.out.println(solution.slidingWindow("acbbaca", "aba"));
//
//        System.out.println(solution.slidingWindow(
//            "obzcopzocynyrsgsarijyxnkpnukkrvzuwdjldxndmnvevpgmxrmvfwkutwekrffnloyqnntbdohyfqndhzyoykiripdzwiojyoznbtogjyfpouuxvumtewmmnqnkadvzrvouqfbbdiqremqzgevkbhyoznacqwbhtrcjwfkzpdstpjswnpiqxjhywjanhdwavajrhwtwzlrqwmombxcaijzevbtcfsdcuovckoalcseaesmhrrizcjgxkbartdtotpsefsrjmvksqyahpijsrppdqpvmuocofuunonybjivbjviyftsyiicbzxnwnrmvlgkzticetyfcvqcbjvbufdxgcmesdqnowzpshuwcseenwjqhgsdlxatamysrohfnixfprdsljyyfhrnnjsagtuihuczilgvtfcjwgdhpbixlzmakebszxbhrdibpoxiwztshwczamwnninzmqrmpsviydkptjzpktksrortapgpxwojofxeasoyvyprjoguhqobehugwdvtzlenrcttuitsiijswpogicjolfxhiscjggzzissfcnxnvgftxvbfzkukqrtalvktdjsodmtgzqtuyaqvvrbuexgwqzwduixzrpnvegddyyywaquxjxrnuzlmyipuqotkghfkpknqinoidifnfyczzonxydtqroazxhjnrxfbmtlqcsfhshjrxwqvblovaouxwempdrrplefnxmwrwfjtebrfnfanvvmtbzjesctdgbsfnpxlwihalyiafincfcwgdfkvhebphtxukwgjgplrntsuchyjjuqozakiglangxkttsczhnswjksnuqwflmumpexxrznzwxurrysaokwxxqkrggytvsgkyfjrewrcvntomnoazmzycjrjrqemimyhriyxgrzcfuqtjhvjtuhwfzhwpljzajitrhryaqchnuawbxhxrpvyqcvhpggrpplhychyulijhkglinibedauhvdydkqszdbzfkzbvhldstocgydnbfjkcnkfxcyyfbzmmyojgzmasccaahpdnzproaxnexnkamwmkmwslksfpwirexxtymkmojztgmfhydvlqtddewjvsrmyqjrpycbmndhupmdqqabiuelacuvxnhxgtpvrtwfgzpcrbhhtikbcqpctlxszgpfbgcsbaaiapmtsucocmpecgixshrrnhyrpalralbccnxvjzjllarqhznzghswqsnfuyywmzbopyjyauknxddgdthlabjqtwxpxwljvoxkpjjpfvccyikbbrpdsyvlxscuoofkecwtnfkvcnzbxkeabtdusyhrqklhaqreupakxkfzxgawqfwsaboszvlshwzhosojjotgyagygguzntrouhiweuomqptfjjqsxlbylhwtpssdlltgubczxslqjgxuqnmpynnlwjgmebrpokxjnbiltvbebyytnnjlcwyzignmhedwqbfdepqakrelrdfesqrumptwwgifmmbepiktxavhuavlfaqxqhreznbvvlakzeoomykkzftthoemqwliednfsqcnbexbimrvkdhllcesrlhhjsspvfupxwdybablotibypmjutclgjurbmhztboqatrdwsomnxnmocvixxvfiqwmednahdqhxjkvcyhpxxdmzzuyyqdjibvmfkmonfxmohhshpkhmntnoplphqyprveyfsmsxjfosmicdsjrieeytpnbhlsziwxnpmgoxneqbnufhfwrjbqcsdfarybzwaplmxckkgclvwqdbpumsmqkswmjwnkuqbicykoisqwoootrdpdvcuiuswfqmrkctsgrevcxnyncmivsxbpbxzxpwchiwtkroqisnmrbmefbmatmdknaklpgpyqlsccgunaibsloyqpnsibwuowebomrmcegejozypjzjunjmeygozcjqbnrpakdermjcckartbcppmbtkhkmmtcngteigjnxxyzaibtdcwutkvpwezisskfaeljmxyjwykwglqlnofhycwuivdbnpintuyhtyqpwaoelgpbuwiuyeqhbvkqlsfgmeoheexbhnhutxvnvfjwlzfmvpcghiowocdsjcvqrdmkcizxnivbianfpsnzabxqecinhgfyjrjlbikrrgsbgfgyxtzzwwpayapfgueroncpxogouyrdgzdfucfrywtywjeefkvtzxlwmrniselyeodysirqflpduvibfdvedgcrzpzrunpadvawfsmmddqzaaahfxlifobffbyzqqbtlcpquedzjvykvarayfldvmkapjcfzfbmhscdwhciecsbdledspgpdtsteuafzbrjuvmsfrajtulwirzagiqjdiehefmfifocadxfuxrpsemavncdxuoaetjkavqicgndjkkfhbvbhjdcygfwcwyhpirrfjziqonbyxhibelinpllxsjzoiifscwzlyjdmwhnuovvugfhvquuleuzmehggdfubpzolgbhwyeqekzccuypaspozwuhbzbdqdtejuniuuyagackubauvriwneeqfhtwkocuipcelcfrcjcymcuktegiikyosumeioatfcxrheklookaqekljtvtdwhxsteajevpjviqzudnjnqbucnfvkybggaybebljwcstmktgnipdyrxbgewqczzkaxmeazpzbjsntltjwlmuclxirwytvxgvxscztryubtjweehapvxrguzzsatozzjytnamfyiitreyxmanhzeqwgpoikcjlokebksgkaqetverjegqgkicsyqcktmwjwakivtsxjwrgakphqincqrxqhzbcnxljzwturmsaklhnvyungjrxaonjqomdnxpnvihmwzphkyuhwqwdboabepmwgyatyrgtboiypxfavbjtrgwswyvcqhzwibpisydtmltbkydhznbsvxktyfxopwkxzbftzknnwipghuoijrbgqnzovxckvojvsqqraffwowfvqvfcmiicwitrhxdeombgesxexedlakitfovtydxunqnwqqdeeekiwjnwoshqcsljiicgobbbuqakjdonjawgjlezdnqhfdqnmsuavxdpnfzwipmspiabveaarshzwxmirgkmfncvtdrdvfxkpxlkdokxgtwcskmjryyymcthfnkasinihaunohkxaibtsqelockaefjmsuolebtnepauwmrxutspjwaxbmahsjtkfkxlnszribmeofbkyvbjscjtqjakuwvcgunvnonvqbbggfshauqsyznokqbhowjusypfnecffenojfvlblgzntqzlrgzprvhqnpfrrkzxznieiuivajivzijsqijigtatifmbplzqahuidegfoobpymkputzamzvweiyvvzlwihgmmmrcburbgbsdxrfjsbiylitghgcpqjbevvgypxcybubyoijijrhuzcdijfybqbfowlookqmlnplbxvjjosfqviygqyhgamuwzjklbyzopkrnhbywtfoqomweldmlrhjqswctubiknzzvcztyehouvnyiqnvkufaobehxhrjvtisxjlxoumipzjarwvbsaegdkpbsjmpevjbewzuqnfhoohhmdjgfpmjzdmtmykqvtucptwfidpwtwffzolffzqfdearclkyeecuzabjeqhxpmfodsvisnpxrqowdawheydfyhoexvcmihdlzavtqlshdhdgjzpozvvackebhgqppvcrvymljfvooauxcjnbejdivikcoaugxwzsulgfqdtefpehbrlhaoqxwcancuvbqutnfbuygoemditeagmcveatgaikwflozgdhkyfqmjcruyyuemwbqwxyyfiwnvlmbovlmccaoguieu",
//            "cjgamyzjwxrgwedhsexosmswogckohesskteksqgrjonnrwhywxqkqmywqjlxnfrayykqotkzhxmbwvzstrcjfchvluvbaobymlrcgbbqaprwlsqglsrqvynitklvzmvlamqipryqjpmwhdcsxtkutyfoiqljfhxftnnjgmbpdplnuphuksoestuckgopnlwiyltezuwdmhsgzzajtrpnkkswsglhrjprxlvwftbtdtacvclotdcepuahcootzfkwqhtydwrgqrilwvbpadvpzwybmowluikmsfkvbebrxletigjjlealczoqnnejvowptikumnokysfjyoskvsxztnqhcwsamopfzablnrxokdxktrwqjvqfjimneenqvdxufahsshiemfofwlyiionrybfchuucxtyctixlpfrbngiltgtbwivujcyrwutwnuajcxwtfowuuefpnzqljnitpgkobfkqzkzdkwwpksjgzqvoplbzzjuqqgetlojnblslhpatjlzkbuathcuilqzdwfyhwkwxvpicgkxrxweaqevziriwhjzdqanmkljfatjifgaccefukavvsfrbqshhswtchfjkausgaukeapanswimbznstubmswqadckewemzbwdbogogcysfxhzreafwxxwczigwpuvqtathgkpkijqiqrzwugtr"));


        System.out.println(solution.minWindow2("ADOBECOPEBANC", "ABC"));
        System.out.println(solution.minWindow2("ADOBECOPEBANCABC", "ABC"));
        System.out.println(solution
                .minWindow2("ask_not_what_your_country_can_do_for_you_ask_what_you_can_do_for_your_country",
                        "ask_country"));
        System.out.println(solution.minWindow2("abbbb", "aa"));
        System.out.println(solution.minWindow2("aaaaaaaaaaaabbbbbcdd", "abcdd"));
        System.out.println(solution.minWindow2("abc", "ac"));
        System.out.println(solution.minWindow2("bba", "ab"));
        System.out.println(solution.minWindow2("acbbaca", "aba"));

        System.out.println(solution.minWindow2(
                "obzcopzocynyrsgsarijyxnkpnukkrvzuwdjldxndmnvevpgmxrmvfwkutwekrffnloyqnntbdohyfqndhzyoykiripdzwiojyoznbtogjyfpouuxvumtewmmnqnkadvzrvouqfbbdiqremqzgevkbhyoznacqwbhtrcjwfkzpdstpjswnpiqxjhywjanhdwavajrhwtwzlrqwmombxcaijzevbtcfsdcuovckoalcseaesmhrrizcjgxkbartdtotpsefsrjmvksqyahpijsrppdqpvmuocofuunonybjivbjviyftsyiicbzxnwnrmvlgkzticetyfcvqcbjvbufdxgcmesdqnowzpshuwcseenwjqhgsdlxatamysrohfnixfprdsljyyfhrnnjsagtuihuczilgvtfcjwgdhpbixlzmakebszxbhrdibpoxiwztshwczamwnninzmqrmpsviydkptjzpktksrortapgpxwojofxeasoyvyprjoguhqobehugwdvtzlenrcttuitsiijswpogicjolfxhiscjggzzissfcnxnvgftxvbfzkukqrtalvktdjsodmtgzqtuyaqvvrbuexgwqzwduixzrpnvegddyyywaquxjxrnuzlmyipuqotkghfkpknqinoidifnfyczzonxydtqroazxhjnrxfbmtlqcsfhshjrxwqvblovaouxwempdrrplefnxmwrwfjtebrfnfanvvmtbzjesctdgbsfnpxlwihalyiafincfcwgdfkvhebphtxukwgjgplrntsuchyjjuqozakiglangxkttsczhnswjksnuqwflmumpexxrznzwxurrysaokwxxqkrggytvsgkyfjrewrcvntomnoazmzycjrjrqemimyhriyxgrzcfuqtjhvjtuhwfzhwpljzajitrhryaqchnuawbxhxrpvyqcvhpggrpplhychyulijhkglinibedauhvdydkqszdbzfkzbvhldstocgydnbfjkcnkfxcyyfbzmmyojgzmasccaahpdnzproaxnexnkamwmkmwslksfpwirexxtymkmojztgmfhydvlqtddewjvsrmyqjrpycbmndhupmdqqabiuelacuvxnhxgtpvrtwfgzpcrbhhtikbcqpctlxszgpfbgcsbaaiapmtsucocmpecgixshrrnhyrpalralbccnxvjzjllarqhznzghswqsnfuyywmzbopyjyauknxddgdthlabjqtwxpxwljvoxkpjjpfvccyikbbrpdsyvlxscuoofkecwtnfkvcnzbxkeabtdusyhrqklhaqreupakxkfzxgawqfwsaboszvlshwzhosojjotgyagygguzntrouhiweuomqptfjjqsxlbylhwtpssdlltgubczxslqjgxuqnmpynnlwjgmebrpokxjnbiltvbebyytnnjlcwyzignmhedwqbfdepqakrelrdfesqrumptwwgifmmbepiktxavhuavlfaqxqhreznbvvlakzeoomykkzftthoemqwliednfsqcnbexbimrvkdhllcesrlhhjsspvfupxwdybablotibypmjutclgjurbmhztboqatrdwsomnxnmocvixxvfiqwmednahdqhxjkvcyhpxxdmzzuyyqdjibvmfkmonfxmohhshpkhmntnoplphqyprveyfsmsxjfosmicdsjrieeytpnbhlsziwxnpmgoxneqbnufhfwrjbqcsdfarybzwaplmxckkgclvwqdbpumsmqkswmjwnkuqbicykoisqwoootrdpdvcuiuswfqmrkctsgrevcxnyncmivsxbpbxzxpwchiwtkroqisnmrbmefbmatmdknaklpgpyqlsccgunaibsloyqpnsibwuowebomrmcegejozypjzjunjmeygozcjqbnrpakdermjcckartbcppmbtkhkmmtcngteigjnxxyzaibtdcwutkvpwezisskfaeljmxyjwykwglqlnofhycwuivdbnpintuyhtyqpwaoelgpbuwiuyeqhbvkqlsfgmeoheexbhnhutxvnvfjwlzfmvpcghiowocdsjcvqrdmkcizxnivbianfpsnzabxqecinhgfyjrjlbikrrgsbgfgyxtzzwwpayapfgueroncpxogouyrdgzdfucfrywtywjeefkvtzxlwmrniselyeodysirqflpduvibfdvedgcrzpzrunpadvawfsmmddqzaaahfxlifobffbyzqqbtlcpquedzjvykvarayfldvmkapjcfzfbmhscdwhciecsbdledspgpdtsteuafzbrjuvmsfrajtulwirzagiqjdiehefmfifocadxfuxrpsemavncdxuoaetjkavqicgndjkkfhbvbhjdcygfwcwyhpirrfjziqonbyxhibelinpllxsjzoiifscwzlyjdmwhnuovvugfhvquuleuzmehggdfubpzolgbhwyeqekzccuypaspozwuhbzbdqdtejuniuuyagackubauvriwneeqfhtwkocuipcelcfrcjcymcuktegiikyosumeioatfcxrheklookaqekljtvtdwhxsteajevpjviqzudnjnqbucnfvkybggaybebljwcstmktgnipdyrxbgewqczzkaxmeazpzbjsntltjwlmuclxirwytvxgvxscztryubtjweehapvxrguzzsatozzjytnamfyiitreyxmanhzeqwgpoikcjlokebksgkaqetverjegqgkicsyqcktmwjwakivtsxjwrgakphqincqrxqhzbcnxljzwturmsaklhnvyungjrxaonjqomdnxpnvihmwzphkyuhwqwdboabepmwgyatyrgtboiypxfavbjtrgwswyvcqhzwibpisydtmltbkydhznbsvxktyfxopwkxzbftzknnwipghuoijrbgqnzovxckvojvsqqraffwowfvqvfcmiicwitrhxdeombgesxexedlakitfovtydxunqnwqqdeeekiwjnwoshqcsljiicgobbbuqakjdonjawgjlezdnqhfdqnmsuavxdpnfzwipmspiabveaarshzwxmirgkmfncvtdrdvfxkpxlkdokxgtwcskmjryyymcthfnkasinihaunohkxaibtsqelockaefjmsuolebtnepauwmrxutspjwaxbmahsjtkfkxlnszribmeofbkyvbjscjtqjakuwvcgunvnonvqbbggfshauqsyznokqbhowjusypfnecffenojfvlblgzntqzlrgzprvhqnpfrrkzxznieiuivajivzijsqijigtatifmbplzqahuidegfoobpymkputzamzvweiyvvzlwihgmmmrcburbgbsdxrfjsbiylitghgcpqjbevvgypxcybubyoijijrhuzcdijfybqbfowlookqmlnplbxvjjosfqviygqyhgamuwzjklbyzopkrnhbywtfoqomweldmlrhjqswctubiknzzvcztyehouvnyiqnvkufaobehxhrjvtisxjlxoumipzjarwvbsaegdkpbsjmpevjbewzuqnfhoohhmdjgfpmjzdmtmykqvtucptwfidpwtwffzolffzqfdearclkyeecuzabjeqhxpmfodsvisnpxrqowdawheydfyhoexvcmihdlzavtqlshdhdgjzpozvvackebhgqppvcrvymljfvooauxcjnbejdivikcoaugxwzsulgfqdtefpehbrlhaoqxwcancuvbqutnfbuygoemditeagmcveatgaikwflozgdhkyfqmjcruyyuemwbqwxyyfiwnvlmbovlmccaoguieu",
                "cjgamyzjwxrgwedhsexosmswogckohesskteksqgrjonnrwhywxqkqmywqjlxnfrayykqotkzhxmbwvzstrcjfchvluvbaobymlrcgbbqaprwlsqglsrqvynitklvzmvlamqipryqjpmwhdcsxtkutyfoiqljfhxftnnjgmbpdplnuphuksoestuckgopnlwiyltezuwdmhsgzzajtrpnkkswsglhrjprxlvwftbtdtacvclotdcepuahcootzfkwqhtydwrgqrilwvbpadvpzwybmowluikmsfkvbebrxletigjjlealczoqnnejvowptikumnokysfjyoskvsxztnqhcwsamopfzablnrxokdxktrwqjvqfjimneenqvdxufahsshiemfofwlyiionrybfchuucxtyctixlpfrbngiltgtbwivujcyrwutwnuajcxwtfowuuefpnzqljnitpgkobfkqzkzdkwwpksjgzqvoplbzzjuqqgetlojnblslhpatjlzkbuathcuilqzdwfyhwkwxvpicgkxrxweaqevziriwhjzdqanmkljfatjifgaccefukavvsfrbqshhswtchfjkausgaukeapanswimbznstubmswqadckewemzbwdbogogcysfxhzreafwxxwczigwpuvqtathgkpkijqiqrzwugtr"));
    }

    /**
     * 此方法未考虑t中包含重复字符也需要匹配的状况
     */
    public String minwindow1(String s, String t) {
        if (s == null || s.length() == 0) {
            return "";
        }
        if (t == null || t.length() == 0) {
            return "";
        }
        Map<Character, Integer> map = new HashMap<>();
        int target = processT2Map(t, map);
        int[] array = changeToArray(s, map);
        int start = 0;
        while (start < s.length()) {
            if (array[start] == 0) {
                start++;
            } else {
                break;
            }
        }
        int minLength = Integer.MAX_VALUE;
        String result = "";
        for (int i = start; i < s.length(); i++) {
            int sum = 0;
            int j = i;
            while (j < s.length()) {
                sum |= array[j];
                if (sum == target) {
                    int length = j - i + 1;
                    if (length < minLength) {
                        minLength = j - i + 1;
                        result = s.substring(i, j + 1);
                    }
                }
                j++;
            }
        }
        return result;
    }

    private static int[] changeToArray(String s, Map<Character, Integer> map) {
        int[] result = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            result[i] = map.getOrDefault(c, 0);
        }
        return result;
    }

    private static int processT2Map(String t, Map<Character, Integer> map) {
        int value = 1, result = 0;
        for (int i = 0; i < t.length(); i++) {
            Character c = t.charAt(i);
            if (!map.containsKey(c)) {
                map.put(c, value);
                result += value;
                value = value << 1;
            }
        }
        return result;
    }


    private String minWindow2(String s, String t) {
        if (s == null || t == null || s.length() == 0 || t.length() == 0 || s.length() < t.length()) {
            return "";
        }

        if (t.length() == 1) {
            return s.contains(t) ? t : "";
        }
        Map<Character, Integer> needCharMap = initCharSizeMap(t, 0, t.length());
        Map<Character, Integer> windowCharMap = new HashMap<>();
        //还需要凑齐多少个不同的字母，为0时完美包含子串
        int valid = needCharMap.keySet().size();
        int left = 0, right = 0;
        int start = 0, end = 0;
        int length = Integer.MAX_VALUE;
        while (right < s.length()) {
            char rightChar = s.charAt(right);
            if (needCharMap.containsKey(rightChar)) {
                int time = windowCharMap.getOrDefault(rightChar, 0);
                time++;
                if (time == needCharMap.get(rightChar)) {
                    valid--;
                }
                windowCharMap.put(rightChar, time);
            }

            while (valid == 0) {
                //满足覆盖子串
                if (right - left < length) {
                    length = right - left;
                    start = left;
                    end = right;
                }
                char leftChar = s.charAt(left);
                if (windowCharMap.containsKey(leftChar)) {
                    int time = windowCharMap.get(leftChar);
                    time--;
                    if (time < needCharMap.get(leftChar)) {
                        valid++;
                    }
                    windowCharMap.put(leftChar, time);
                }
                left++;
            }
            right++;
        }
        return length == Integer.MAX_VALUE ? "" : s.substring(start, end + 1);
    }
}
