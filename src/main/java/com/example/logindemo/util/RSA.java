package com.example.logindemo.util;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;
//import java.
@Component

public class RSA {
    private static String publicKey="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArNDL3LYlS4dDROdA8daX\n" +
            "AwKerw81FZZbRiiLy7hJHSJ857n3heITVrEBcUiIE52w0Qy9Il4IhWrYZ6W0BAYG\n" +
            "B1A+K9H8AFCAwrDBalwKpvqd1HcEjq2tuQdodFMAz3Ws4pbfMmEHu7p+Wx+Fw+mN\n" +
            "z+SGUSV8oDsJBEMoXMFLdpIcEj1SIU/c+l1mX20doLHZ3qm4C2z7sqvkg65RujsX\n" +
            "52ekxz64rReiDP6kTKMbUhWLyhkkymDSe05A2HjfvL7uW9CAio6jOoN0vdg+hwly\n" +
            "AHsbvyc1lHlHBHA4+2PVaLnkp0+haSsHeoYKRcUQqqfsJCskRFOZdki3VWc0qGv4\n" +
            "9QIDAQAB";
    private static String privateKey="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCs0MvctiVLh0NE\n" +
            "50Dx1pcDAp6vDzUVlltGKIvLuEkdInznufeF4hNWsQFxSIgTnbDRDL0iXgiFathn\n" +
            "pbQEBgYHUD4r0fwAUIDCsMFqXAqm+p3UdwSOra25B2h0UwDPdazilt8yYQe7un5b\n" +
            "H4XD6Y3P5IZRJXygOwkEQyhcwUt2khwSPVIhT9z6XWZfbR2gsdneqbgLbPuyq+SD\n" +
            "rlG6OxfnZ6THPritF6IM/qRMoxtSFYvKGSTKYNJ7TkDYeN+8vu5b0ICKjqM6g3S9\n" +
            "2D6HCXIAexu/JzWUeUcEcDj7Y9VoueSnT6FpKwd6hgpFxRCqp+wkKyREU5l2SLdV\n" +
            "ZzSoa/j1AgMBAAECggEAGsvdcIHF0qpMofiBt34guFTFqjtfbdN8n6pV0ubmX2Aj\n" +
            "ogLSAK6HdR/JK3mlr/T5n9Cr2xJdm+JvX9Lv0NfmQ2gL29uZiarx7D5gH0/aTv35\n" +
            "TyKNUFpZ36Dob3EXZRPIFPJz9WnaOx+1zAddRH2Igb3bneWEJ4w/36tSLN/EBcRs\n" +
            "2uI3qWd/NPLXEhKKKnVPnlbD8VfGi3iIrMKRT7xEY4lKydo5ntX0ViB+r9yyraWR\n" +
            "G6Eq5aauTRDxs41/+ouEkcYwEHyxarHrFqWDeraRwq5R1aAurksIDxxF3W/d5scz\n" +
            "RM4wrqqc9r6BobRcCNcZn0yBQ8OHX7maaWPpBPAwgQKBgQDXMu31+IM1/FOcrvcy\n" +
            "KKC//zySC9UCjJcvc4PmpWencIYP02aZ36im403RJUSovCOW3CUcp6pA1fqMWT7A\n" +
            "0TLH0AVaOaNtSrm0uXaCcztNX9Wu3AcI5+wQCjB0fXEPTg9ORj2FLdORTqLnt1pc\n" +
            "IR4Bi+xKe1Qhxmb6KtFGZubspQKBgQDNlLcJPLji8BSrllI9+PYxto4xbsw5W08y\n" +
            "/XEnws/O/UNEj8GplUQs/YiZGkjepncAViPVLU3sSug3lUPOZ9tgTcCF06VHybb2\n" +
            "XfLwBG/c80YJLdgrGaPHHzjw8BMJP99JpQBwQBhw2NFXDQgPcyPG3NCL4pKpCBRF\n" +
            "OlkBod+aEQKBgCstJ7tLIiBJwQjVvfkNV7tN3Xw77X/2bzrxBUzYcOSSWgOA2bwT\n" +
            "uMXLI0jMHmtwW/+fqb6HEUyy4fMY0NNk5bhbT0eb+uMps/rDnzhrIEHU8Y2EzdCG\n" +
            "n8FUGga1SGLWuZFt3FgFKsajrWGqUvHbHycWSNFkzyt7oBufTZ+/c6OVAoGBAIeg\n" +
            "9oTUl9G4vJpbiRSzvJEdtH/ubjPlBsJILLAjuYAA3LFN+mGMRbc86TLBAZRK1LXX\n" +
            "HrrUsdUTslKBWoPBRx0pDrv73s4WyWuOnek1tCtcIDeMFFKTDtcexS1H5fczOXfX\n" +
            "sQtdbgJ9ONcqlMfEmbToF3pCV6nG18C4duI5zlBRAoGAcQQwknWUeHWCZ82BNVG5\n" +
            "jEIgDh7RYdbbxhs91wm4OIUmGtRmCXD8NUbvfuW24Z4jopTvQ/Pq3WeQMQtMj/Ql\n" +
            "Z/f6jtQ5cJZiHzZFs+3qzhJ3S373l5XmCerhTcsIz3JOWQbH1nJj6zwgJryhMXDR\n" +
            "Wx45g7Ci/BRvFKT5SuU90lY=";
    public static String encrypt(String str) throws Exception{
        //base64编码的公钥
        byte[] decoded = Base64.decodeBase64(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        String outStr = Base64.encodeBase64String(cipher.doFinal(str.getBytes("UTF-8")));
        return outStr;
    }
    public static String decrypt(String str) throws Exception{
        //64位解码加密后的字符串
        byte[] inputByte = Base64.decodeBase64(str.getBytes("UTF-8"));
        //base64编码的私钥
        byte[] decoded = Base64.decodeBase64(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        String outStr = new String(cipher.doFinal(inputByte));
        return outStr;
    }
    public static String generateToken(String jsonfiedInfo) {
        try {
            String token = encrypt(jsonfiedInfo);
            return token;
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    //TODO NOTGOOD AT ALL
    public static boolean verifyToken(String jsonfiedInfo,String token){
        try{
            String generatedToken =encrypt(jsonfiedInfo);
            if(generatedToken.equals(token)){
                return true;
            }
        }
        catch (Exception e){
            e.printStackTrace();
         //   return false;
        }
        return false;
    }
}
