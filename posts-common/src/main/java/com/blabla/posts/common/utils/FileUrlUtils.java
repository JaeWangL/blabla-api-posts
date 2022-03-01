package com.blabla.posts.common.utils;

public class FileUrlUtils {
    public static String getUrlByDomainAndFilename(final String domain, final String blobName) {
        if (domain == null || blobName == null) {
            return null;
        }

        return String.format("%s%s", domain, blobName);
    }
}
