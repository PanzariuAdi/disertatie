package info.uaic.ro.sandbox.utils;

import lombok.experimental.UtilityClass;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class StringUtils {

    Pattern pattern = Pattern.compile("^\\D+");

    public static String extractCategory(String dataset) {
        Matcher matcher = pattern.matcher(dataset);

        if (matcher.find()) {
            return matcher.group();
        }

        return "";
    }
}
