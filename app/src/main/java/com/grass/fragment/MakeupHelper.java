package com.grass.fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.text.TextUtils;

/**
 * Created by baidu on 16/9/8.
 */
public class MakeupHelper {

    public static final String PARAGRAPH_SEPARATOR = "\n";
    public static final String PARAGRAPH_INDENT = "**";
    public static final int PARAGRAPH_MAX_CHAR_COUNT = 300;
    public static final int PARAGRAPH_MAX_INDENT_CHAR_COUNT = 3;

    public static final String PATTERN_TWO_MANY_LINE_SEPARATOR = "(\\n){3,}";
    public static final String PATTERN_PARAGRAPH_INDENT = "^(\\s)*";
    public static final String PATTERN_DUPLICATE_CHAR = "(.)\\1{10,}";
    public static final String PATTERN_PARAGRAPH_END = "(。|？|！|。。。|\\.|\\?|!|\\.\\.\\.)$";

    public static String removeDuplicateLineBreak(String str) {
        String pattern = "(\n)+";
        String returnStr = "";
        if (str != null) {
            System.out.println(str);
            returnStr = str.replaceAll(pattern, "\n");
            System.out.println("------------------------------------------------------");
            System.out.println(returnStr);
        }
        return returnStr;
    }

    public static boolean hasTooBigParagraph(String content) {
        if (TextUtils.isEmpty(content)) {
            return false;
        }
        content = removeDuplicateLineBreak(content);
        List<String> strings = splitParagraphs(content);
        if (strings.isEmpty()) {
            return false;
        }
        for (String string : strings) {
            if (string.length() >= PARAGRAPH_MAX_CHAR_COUNT) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasTooManyLineSeparator(String content) {
        if (TextUtils.isEmpty(content)) {
            return false;
        }
        List<String> regrexString = findRegrexString(content, PATTERN_TWO_MANY_LINE_SEPARATOR);
        return !regrexString.isEmpty();
    }

    public static boolean checkHasFirstLineIndent(String line) {
        if (TextUtils.isEmpty(line)) {
            return true;
        }
        return line.startsWith(" ");
    }

    public static int getFirstLineIndent(String line) {
        if (TextUtils.isEmpty(line)) {
            return 0;
        }
        int count = 0;
        for (char word : line.toCharArray()) {
            if (" ".equals(word)) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    public static boolean checkHasTooManyFirstLineIndent(String line) {
        int count = getFirstLineIndent(line);
        return count >= PARAGRAPH_MAX_INDENT_CHAR_COUNT;
    }

    public static List<String> splitParagraphs(String str) {
        if (TextUtils.isEmpty(str)) {
            return new ArrayList<String>();
        }
        String[] split = str.split(PARAGRAPH_SEPARATOR);
        List<String> strings = Arrays.asList(split);
        System.out.println("--------------------------------------------------------");
        for (String string : strings) {
            System.out.println("split: " + string);
        }
        return strings;
    }

    public static String checkFirstLineIndent(String line) {
        return line.replaceFirst(PATTERN_PARAGRAPH_INDENT, PARAGRAPH_INDENT);
    }

    public static String checkPeculiarChar(String line) {

        return line;
    }

    public static boolean checkParagraphEnd(String line) {
        List<String> list = findRegrexString(line, PATTERN_PARAGRAPH_END);
        return !list.isEmpty();
    }

    public static boolean checkPicsCount(int charCount, int picCOunt) {

        return false;
    }

    public static boolean hasDuplicateChars(String line) {
        List<String> list = findRegrexString(line, PATTERN_DUPLICATE_CHAR);
        return !list.isEmpty();
    }

    public static List<String> findRegrexString(String line, String regrex) {
        ArrayList<String> list = new ArrayList<String>();
        Pattern pattern = Pattern.compile(regrex);
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            list.add(matcher.group());
        }
        if (!list.isEmpty()) {
            System.out.println(list);
        }
        return list;
    }
}
