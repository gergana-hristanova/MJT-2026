import java.util.Collection;

public class DnsResolver {
    void main() {
        System.out.println(resolve(new String[]{"www.fmi.bg", "fmi.uni-sofia.bg", "uni-sofia.bg"}, new String[]{"fmi.uni-sofia.bg", "uni-sofia.bg", "learn.fmi.bg"}, "www.fmi.bg"));
        System.out.println(resolve(new String[]{"moodle.bg", "login.moodle.bg"}, new String[]{"login.moodle.bg", "moodle.bg"}, "moodle.bg"));
        System.out.println(resolve(new String[]{"localhost"}, new String[]{"localhost"}, "localhost"));
        System.out.println(resolve(new String[]{"blog.me", "cdn.me", "cache.me"}, new String[]{"cdn.me", "cache.me", "cdn.me"}, "blog.me"));
        System.out.println(resolve(new String[]{"www.fmi.bg", "fmi.uni-sofia.bg"}, new String[]{"fmi.uni-sofia.bg", "uni-sofia.bg"}, "example.com"));
    }

    public static String resolve(String[] from, String[] to, String start) {
        int len = from.length; // assume from.length == to.length

        String slowHost = start;
        String fastHost = start;
        while (stringArrContains(from, slowHost)) {
            slowHost = getNextHost(from, to, slowHost);
            fastHost = getNextHost(from, to, getNextHost(from, to, fastHost));

            if (slowHost == null) break;

            if (slowHost.equals(fastHost)) return null;
        }

        return slowHost;
    }

    public static String getNextHost(String[] from, String[] to, String current) {
        if (current == null) return null;

        for (int i = 0; i < from.length; ++i) {
            if (current.equals(from[i])) {
                return to[i];
            }
        }

        return null;
    }

    public static boolean stringArrContains(String[] arr, String str) {
        if (str == null) return false;

        for (String s : arr)
        {
            if (s == null) continue;

            if (str.equals(s))
                return true;
        }

        return false;
    }
}