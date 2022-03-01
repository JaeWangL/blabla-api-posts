package com.blabla.posts.common.utils;

import static org.apache.commons.lang3.StringUtils.isEmpty;

public class ContentsUtils {
    public static String getSnippetByContents(final String contents) {
        if (isEmpty(contents) || contents.length() < 30) {
            return contents;
        }

        return contents.substring(0, 30);
    }
}
