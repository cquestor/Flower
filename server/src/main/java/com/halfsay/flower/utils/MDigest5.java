package com.halfsay.flower.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MDigest5 {
    private static String md5(String plainText) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update((plainText + Constant.APP_SECRET).getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getMD5(String plainText) {
        return md5(plainText);
    }
}
