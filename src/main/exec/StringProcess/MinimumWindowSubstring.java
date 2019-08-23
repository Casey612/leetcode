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
    
    //活动窗口法
    public String slidingWindown(String s, String t) {
        
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
            } else  {
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
    
    private boolean sizeSatisfy(String s, int left, int right, Map<Character, Integer> map) {
        int size = right - left + 1;
        if (size < map.keySet().size()) {
            return false;
        }
        Map<Character, Integer> sMap = initCharSizeMap(s, left, right + 1);
        for (Character c : map.keySet()){
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
        for (int i = startIndex; i < endIndex; i++){
            char c = t.charAt(i);
            int size = 1;
            if (result.containsKey(c)) {
                size += result.get(c);
            }
            result.put(c, size);
        }
        return result;
    }
    
    private boolean containSatisfy(int startIndex, int endIndex, Map<Character, Set<Integer>> map, String t) {
        Map<Character, Set<Integer>> indexMap = new HashMap<>(map);
        for (Character c : map.keySet()) {
            Set<Integer> set = new HashSet<>(map.get(c));
            indexMap.put(c, set);
        }
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            Set<Integer> indexes = indexMap.get(c);
            Integer target = Integer.MIN_VALUE;
            for (Integer index : indexes) {
                if (index >= startIndex && index <= endIndex) {
                    target = index;
                    break;
                }
            }
            if (target == Integer.MIN_VALUE) {
                return false;
            }
            indexes.remove(target);
        }
        return true;
    }
    
    public static void main(String[] args) {
        MinimumWindowSubstring solution = new MinimumWindowSubstring();
        //System.out.println(solution.minWindow("ADOBECOPEBANC", "ABC"));
        //System.out.println(solution.minWindow("bbaa", "aba"));
        //System.out.println(solution
        //    .minWindow("ask_not_what_your_country_can_do_for_you_ask_what_you_can_do_for_your_country",
        //    "ask_country"));
        //System.out.println(solution.minWindow("abbbb", "aa"));
        //System.out.println(solution.minWindow("aaaaaaaaaaaabbbbbcdd", "abcdd"));
        //System.out.println(solution.minWindow
        // ("wegdtzwabazduwwdysdetrrctotpcepalxdewzezbfewbabbseinxbqqplitpxtcwwhuyntbtzxwzyaufihclztckdwccpeyonumbpnuonsnnsjscrvpsqsftohvfnvtbphcgxyumqjzltspmphefzjypsvugqqjhzlnylhkdqmolggxvneaopadivzqnpzurmhpxqcaiqruwztroxtcnvhxqgndyozpcigzykbiaucyvwrjvknifufxducbkbsmlanllpunlyohwfsssiazeixhebipfcdqdrcqiwftutcrbxjthlulvttcvdtaiwqlnsdvqkrngvghupcbcwnaqiclnvnvtfihylcqwvderjllannflchdklqxidvbjdijrnbpkftbqgpttcagghkqucpcgmfrqqajdbynitrbzgwukyaqhmibpzfxmkoeaqnftnvegohfudbgbbyiqglhhqevcszdkokdbhjjvqqrvrxyvvgldtuljygmsircydhalrlgjeyfvxdstmfyhzjrxsfpcytabdcmwqvhuvmpssingpmnpvgmpletjzunewbamwiirwymqizwxlmojsbaehupiocnmenbcxjwujimthjtvvhenkettylcoppdveeycpuybekulvpgqzmgjrbdrmficwlxarxegrejvrejmvrfuenexojqdqyfmjeoacvjvzsrqycfuvmozzuypfpsvnzjxeazgvibubunzyuvugmvhguyojrlysvxwxxesfioiebidxdzfpumyon","ozgzyywxvtublcl"));
        
        //System.out.println(solution.slidingWindown("ADOBECOPEBANC", "ABC"));
        //System.out.println(solution.slidingWindown("ADOBECOPEBANCABC", "ABC"));
        //System.out.println(solution
        //    .slidingWindown("ask_not_what_your_country_can_do_for_you_ask_what_you_can_do_for_your_country",
        //    "ask_country"));
        //System.out.println(solution.slidingWindown("abbbb", "aa"));
        //System.out.println(solution.slidingWindown("aaaaaaaaaaaabbbbbcdd", "abcdd"));
        //System.out.println(solution.slidingWindown("abc", "ac"));
        //System.out.println(solution.slidingWindown("bba", "ab"));
        System.out.println(solution.slidingWindown("obzcopzocynyrsgsarijyxnkpnukkrvzuwdjldxndmnvevpgmxrmvfwkutwekrffnloyqnntbdohyfqndhzyoykiripdzwiojyoznbtogjyfpouuxvumtewmmnqnkadvzrvouqfbbdiqremqzgevkbhyoznacqwbhtrcjwfkzpdstpjswnpiqxjhywjanhdwavajrhwtwzlrqwmombxcaijzevbtcfsdcuovckoalcseaesmhrrizcjgxkbartdtotpsefsrjmvksqyahpijsrppdqpvmuocofuunonybjivbjviyftsyiicbzxnwnrmvlgkzticetyfcvqcbjvbufdxgcmesdqnowzpshuwcseenwjqhgsdlxatamysrohfnixfprdsljyyfhrnnjsagtuihuczilgvtfcjwgdhpbixlzmakebszxbhrdibpoxiwztshwczamwnninzmqrmpsviydkptjzpktksrortapgpxwojofxeasoyvyprjoguhqobehugwdvtzlenrcttuitsiijswpogicjolfxhiscjggzzissfcnxnvgftxvbfzkukqrtalvktdjsodmtgzqtuyaqvvrbuexgwqzwduixzrpnvegddyyywaquxjxrnuzlmyipuqotkghfkpknqinoidifnfyczzonxydtqroazxhjnrxfbmtlqcsfhshjrxwqvblovaouxwempdrrplefnxmwrwfjtebrfnfanvvmtbzjesctdgbsfnpxlwihalyiafincfcwgdfkvhebphtxukwgjgplrntsuchyjjuqozakiglangxkttsczhnswjksnuqwflmumpexxrznzwxurrysaokwxxqkrggytvsgkyfjrewrcvntomnoazmzycjrjrqemimyhriyxgrzcfuqtjhvjtuhwfzhwpljzajitrhryaqchnuawbxhxrpvyqcvhpggrpplhychyulijhkglinibedauhvdydkqszdbzfkzbvhldstocgydnbfjkcnkfxcyyfbzmmyojgzmasccaahpdnzproaxnexnkamwmkmwslksfpwirexxtymkmojztgmfhydvlqtddewjvsrmyqjrpycbmndhupmdqqabiuelacuvxnhxgtpvrtwfgzpcrbhhtikbcqpctlxszgpfbgcsbaaiapmtsucocmpecgixshrrnhyrpalralbccnxvjzjllarqhznzghswqsnfuyywmzbopyjyauknxddgdthlabjqtwxpxwljvoxkpjjpfvccyikbbrpdsyvlxscuoofkecwtnfkvcnzbxkeabtdusyhrqklhaqreupakxkfzxgawqfwsaboszvlshwzhosojjotgyagygguzntrouhiweuomqptfjjqsxlbylhwtpssdlltgubczxslqjgxuqnmpynnlwjgmebrpokxjnbiltvbebyytnnjlcwyzignmhedwqbfdepqakrelrdfesqrumptwwgifmmbepiktxavhuavlfaqxqhreznbvvlakzeoomykkzftthoemqwliednfsqcnbexbimrvkdhllcesrlhhjsspvfupxwdybablotibypmjutclgjurbmhztboqatrdwsomnxnmocvixxvfiqwmednahdqhxjkvcyhpxxdmzzuyyqdjibvmfkmonfxmohhshpkhmntnoplphqyprveyfsmsxjfosmicdsjrieeytpnbhlsziwxnpmgoxneqbnufhfwrjbqcsdfarybzwaplmxckkgclvwqdbpumsmqkswmjwnkuqbicykoisqwoootrdpdvcuiuswfqmrkctsgrevcxnyncmivsxbpbxzxpwchiwtkroqisnmrbmefbmatmdknaklpgpyqlsccgunaibsloyqpnsibwuowebomrmcegejozypjzjunjmeygozcjqbnrpakdermjcckartbcppmbtkhkmmtcngteigjnxxyzaibtdcwutkvpwezisskfaeljmxyjwykwglqlnofhycwuivdbnpintuyhtyqpwaoelgpbuwiuyeqhbvkqlsfgmeoheexbhnhutxvnvfjwlzfmvpcghiowocdsjcvqrdmkcizxnivbianfpsnzabxqecinhgfyjrjlbikrrgsbgfgyxtzzwwpayapfgueroncpxogouyrdgzdfucfrywtywjeefkvtzxlwmrniselyeodysirqflpduvibfdvedgcrzpzrunpadvawfsmmddqzaaahfxlifobffbyzqqbtlcpquedzjvykvarayfldvmkapjcfzfbmhscdwhciecsbdledspgpdtsteuafzbrjuvmsfrajtulwirzagiqjdiehefmfifocadxfuxrpsemavncdxuoaetjkavqicgndjkkfhbvbhjdcygfwcwyhpirrfjziqonbyxhibelinpllxsjzoiifscwzlyjdmwhnuovvugfhvquuleuzmehggdfubpzolgbhwyeqekzccuypaspozwuhbzbdqdtejuniuuyagackubauvriwneeqfhtwkocuipcelcfrcjcymcuktegiikyosumeioatfcxrheklookaqekljtvtdwhxsteajevpjviqzudnjnqbucnfvkybggaybebljwcstmktgnipdyrxbgewqczzkaxmeazpzbjsntltjwlmuclxirwytvxgvxscztryubtjweehapvxrguzzsatozzjytnamfyiitreyxmanhzeqwgpoikcjlokebksgkaqetverjegqgkicsyqcktmwjwakivtsxjwrgakphqincqrxqhzbcnxljzwturmsaklhnvyungjrxaonjqomdnxpnvihmwzphkyuhwqwdboabepmwgyatyrgtboiypxfavbjtrgwswyvcqhzwibpisydtmltbkydhznbsvxktyfxopwkxzbftzknnwipghuoijrbgqnzovxckvojvsqqraffwowfvqvfcmiicwitrhxdeombgesxexedlakitfovtydxunqnwqqdeeekiwjnwoshqcsljiicgobbbuqakjdonjawgjlezdnqhfdqnmsuavxdpnfzwipmspiabveaarshzwxmirgkmfncvtdrdvfxkpxlkdokxgtwcskmjryyymcthfnkasinihaunohkxaibtsqelockaefjmsuolebtnepauwmrxutspjwaxbmahsjtkfkxlnszribmeofbkyvbjscjtqjakuwvcgunvnonvqbbggfshauqsyznokqbhowjusypfnecffenojfvlblgzntqzlrgzprvhqnpfrrkzxznieiuivajivzijsqijigtatifmbplzqahuidegfoobpymkputzamzvweiyvvzlwihgmmmrcburbgbsdxrfjsbiylitghgcpqjbevvgypxcybubyoijijrhuzcdijfybqbfowlookqmlnplbxvjjosfqviygqyhgamuwzjklbyzopkrnhbywtfoqomweldmlrhjqswctubiknzzvcztyehouvnyiqnvkufaobehxhrjvtisxjlxoumipzjarwvbsaegdkpbsjmpevjbewzuqnfhoohhmdjgfpmjzdmtmykqvtucptwfidpwtwffzolffzqfdearclkyeecuzabjeqhxpmfodsvisnpxrqowdawheydfyhoexvcmihdlzavtqlshdhdgjzpozvvackebhgqppvcrvymljfvooauxcjnbejdivikcoaugxwzsulgfqdtefpehbrlhaoqxwcancuvbqutnfbuygoemditeagmcveatgaikwflozgdhkyfqmjcruyyuemwbqwxyyfiwnvlmbovlmccaoguieu",
            "cjgamyzjwxrgwedhsexosmswogckohesskteksqgrjonnrwhywxqkqmywqjlxnfrayykqotkzhxmbwvzstrcjfchvluvbaobymlrcgbbqaprwlsqglsrqvynitklvzmvlamqipryqjpmwhdcsxtkutyfoiqljfhxftnnjgmbpdplnuphuksoestuckgopnlwiyltezuwdmhsgzzajtrpnkkswsglhrjprxlvwftbtdtacvclotdcepuahcootzfkwqhtydwrgqrilwvbpadvpzwybmowluikmsfkvbebrxletigjjlealczoqnnejvowptikumnokysfjyoskvsxztnqhcwsamopfzablnrxokdxktrwqjvqfjimneenqvdxufahsshiemfofwlyiionrybfchuucxtyctixlpfrbngiltgtbwivujcyrwutwnuajcxwtfowuuefpnzqljnitpgkobfkqzkzdkwwpksjgzqvoplbzzjuqqgetlojnblslhpatjlzkbuathcuilqzdwfyhwkwxvpicgkxrxweaqevziriwhjzdqanmkljfatjifgaccefukavvsfrbqshhswtchfjkausgaukeapanswimbznstubmswqadckewemzbwdbogogcysfxhzreafwxxwczigwpuvqtathgkpkijqiqrzwugtr"));
    }
    
}
