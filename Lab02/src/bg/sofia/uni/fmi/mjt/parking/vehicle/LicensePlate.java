package bg.sofia.uni.fmi.mjt.parking.vehicle;

public class LicensePlate {

    String value;

    private LicensePlate(String value) {
        this.value = value;
    }

    static char[] cyrillicLetters = { 'А', 'К', 'М', 'О', 'Т', 'В', 'Е', 'Н', 'Р', 'У', 'С', 'Х' };
    static char[] latinLetters = { 'A', 'K', 'M', 'O', 'T', 'B', 'E', 'H', 'P', 'Y', 'C', 'X' };

    public static LicensePlate parse(String text) {
        if (text == null || text.isEmpty()) return null;

        StringBuilder parsedBuilder = new StringBuilder();

        for (int i = 0; i < text.length(); ++i) {
            char curr = text.charAt(i);

            // spaces and dashes are allowed
            if (curr == ' ' || curr == '-') continue;

            if (!isLetterOrDigit(curr)) return null;

            // cyrillic to latin letter mapping
            if (Character.isLetter(curr)) {
                curr = Character.toUpperCase(curr);

                int currPos = posInCharArr(cyrillicLetters, curr);

                if (currPos >= 0) {
                    curr = latinLetters[currPos];
                }
            }

            parsedBuilder.append(curr);
        }

        String parsed = parsedBuilder.toString();
        int parsedLen = parsed.length();
        if (parsedLen < 6 || parsedLen > 8) {
            return null;
        }

        boolean validLayout = matchesLayout(parsed, 1, 1);
        if (parsedLen == 7) {
            validLayout = matchesLayout(parsed, 1, 2)
                    || matchesLayout(parsed, 2, 1);
        } else if (parsedLen == 8) {
            validLayout = matchesLayout(parsed, 2, 2);
        }
        if (!validLayout) {
            return null;
        }

        return new LicensePlate(parsed);
    }

    private static boolean matchesLayout(String plate, int prefixLength, int suffixLength) {
        int digitsEnd = prefixLength + 4;
        if (digitsEnd + suffixLength != plate.length()) {
            return false;
        }

        for (int i = 0; i < plate.length(); ++i) {
            boolean shouldBeDigit = i >= prefixLength && i < digitsEnd;
            boolean isDigit = plate.charAt(i) >= '0' && plate.charAt(i) <= '9';
            if (shouldBeDigit != isDigit) {
                return false;
            }
        }

        return true;
    }

    private static boolean isLetterOrDigit(char ch)
    {
        return (ch >= 'A' && ch <= 'Z')
                || (ch >= 'a' && ch <= 'z')
                || (ch >= '0' && ch <= '9')
                || posInCharArr(cyrillicLetters, Character.toUpperCase(ch)) >= 0;
    }

    private static int posInCharArr(char[] arr, char ch) {
        for (int i = 0; i < arr.length; ++i) {
            if (ch == arr[i]) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof LicensePlate) {
            return this.value.equals(((LicensePlate) other).value);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
