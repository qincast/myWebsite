package com.example.logindemo.util;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Deprecated
public class Hash {


    public static byte[] SHA(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA");
            md.update(str.getBytes());
            //System.out.println("SHA-1:"+md.digest().toString());
            return md.digest();

            // String outStr = Base64.encodeBase64String(md.digest());
            //System.out.println("SHA-2:"+outStr);
            //System.out.println("SHA-3:"+Base64.decodeBase64(outStr));
            //System.out.println("jdk sha-1 : " + Hex.encodeHexString(md.digest()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }

    }
}
