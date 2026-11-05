import java.util.Arrays;

public class HashtagExtractor {

    static int maxHashtagsPerPost = 32;

    void main() {
    IO.println(Arrays.toString(extractHashtags("Loving #Java and #java at #FMI!")));
    IO.println(Arrays.toString(extractHashtags("No tags here, just vibes")));
    IO.println(Arrays.toString(extractHashtags("#MJT #mjt #MjT are all one tag")));
    IO.println(Arrays.toString(extractHashtags("Adjacent #one#two tags")));
    IO.println(Arrays.toString(extractHashtags("A lone # is not a tag, but #this1 is")));
    IO.println(Arrays.toString(extractHashtags("Trailing hash #")));
    IO.println(Arrays.toString(extractHashtags("   ")));
    IO.println(Arrays.toString(extractHashtags("Happy new year #2027#2022")));
    }

    public static String[] extractHashtags(String post) {
        String[] res_buffer = new String[maxHashtagsPerPost];
        int postLen = post.length();

        int hashtagCounter = 0;
        for (int i = 0; i < postLen; ++i) {
            if (post.charAt(i) == '#') {
                StringBuilder currHashtag = new StringBuilder();
                int j;
                for (j = i + 1; j < postLen; ++j) {
                    char currCh = post.charAt(j);
                    if (!Character.isDigit(currCh) && !Character.isLetter(currCh)) {
                        break;
                    }
                    currHashtag.append(currCh);
                }

                if (!currHashtag.isEmpty() && !stringArrContains(res_buffer, currHashtag.toString())) {
                    res_buffer[hashtagCounter++] = currHashtag.toString().toLowerCase();
                }

                i = j - 1;
            }
        }

        String[] res = new String[hashtagCounter];
        System.arraycopy(res_buffer, 0, res, 0, hashtagCounter);

        return res;
    }

    public static boolean stringArrContains(String[] arr, String str) {
        for (String s : arr)
        {
            if (s == null) continue;

            if (str.toLowerCase().equals(s))
                return true;
        }

        return false;
    }
}