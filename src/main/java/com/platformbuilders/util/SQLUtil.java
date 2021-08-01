package com.platformbuilders.util;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Elvis Fernandes on 01/08/2021
 * @project platform-builders
 */
public abstract class SQLUtil {

    public static String addLike(String texto) {
        if(StringUtils.isEmpty(texto)) return "";
        return new StringBuilder().append("%").append(texto).append("%").toString();
    }
}
