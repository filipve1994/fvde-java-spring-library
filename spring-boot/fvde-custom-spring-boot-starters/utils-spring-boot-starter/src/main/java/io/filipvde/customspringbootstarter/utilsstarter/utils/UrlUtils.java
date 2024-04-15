package io.filipvde.customspringbootstarter.utilsstarter.utils;

import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;

public class UrlUtils {

	private static final Logger log = LoggerFactory.getLogger(UrlUtils.class);


    private UrlUtils() {
        //utils
    }


    public static String joinQueryparms(@Nonnull String url, @Nullable String queryparms) {
        if (StringUtils.isEmpty(queryparms)) {
            return url;
        }
        String middle = url.contains("?") ? "&" : "?";
        return url + middle + queryparms;
    }

    public static URL parseUrl(String url) {
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            log.error("Not a URL: " + url);

            throw new RuntimeException(e);
        }
    }
}
