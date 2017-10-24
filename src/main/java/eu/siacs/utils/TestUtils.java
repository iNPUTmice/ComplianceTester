package eu.siacs.utils;

import java.util.List;
import java.util.Set;

public class TestUtils {

    public static boolean hasAnyone(List<String> needles, Set<String> haystack) {
        for(String needle : needles) {
            if (haystack.contains(needle)) {
                return true;
            }
        }
        return false;
    }
}
