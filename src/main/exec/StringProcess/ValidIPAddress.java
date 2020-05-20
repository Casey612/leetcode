package exec.StringProcess;

public class ValidIPAddress {

    private static final String IPV4 = "IPv4";
    private static final String IPV6 = "IPv6";
    private static final String NONE = "Neither";

    public static void main(String[] args) {
        ValidIPAddress valid = new ValidIPAddress();
        System.out.println(valid.validIPAddress("1.0.1."));
    }

    public String validIPAddress(String IP) {
        if (IP == null || IP.trim().isEmpty()) {
            return NONE;
        }
        if (IP.contains(".")) {
            String[] array = IP.split("\\.", -1);
            if (array.length != 4) {
                return NONE;
            }
            for (String item : array) {
                if (item.isEmpty()) {
                    return NONE;
                }
                for (char c : item.toCharArray()) {
                    if (c < '0' || c > '9') {
                        return NONE;
                    }
                }
                if (item.length() > 3) {
                    return NONE;
                }
                int value = Integer.parseInt(item);
                if (value < 0 || value > 255) {
                    return NONE;
                }
                if (item.startsWith("0") && value != 0) {
                    return NONE;
                }
                if (value == 0 && item.length() != 1) {
                    return NONE;
                }
            }
            return IPV4;
        }
        if (IP.contains(":")) {
            String[] array = IP.split(":");
            if (array.length != 8) {
                return NONE;
            }
            for (String item : array) {
                if (item.isEmpty() || item.length() > 4) {
                    return NONE;
                }
                for (char c : item.toCharArray()) {
                    if ( (c >= '0' && c <= '9') || (c >= 'A' && c <= 'F') || (c >= 'a' && c <= 'f')) {
                        //continue
                    } else {
                        return NONE;
                    }
                }
            }
            return IPV6;
        }
        return NONE;
    }

}
