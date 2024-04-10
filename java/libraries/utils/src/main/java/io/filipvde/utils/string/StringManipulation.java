package io.filipvde.utils.string;

import org.apache.commons.lang3.StringUtils;

public class StringManipulation {

    public static String removeSpecialCharacter(String text) {
        return removeSpecialCharacter(text, StringUtils.EMPTY);
    }

    public static String removeSpecialCharacter(String text, String keepCharacterList) {
        return text.replaceAll("[^a-zA-Z0-9" + keepCharacterList + "]+", "");
    }

    public static String replaceNewLineByBrTag(String text) {
        return text.replaceAll("(\r\n|\n)", "<br/>");
    }

}