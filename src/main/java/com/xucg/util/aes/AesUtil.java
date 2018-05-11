package com.xucg.util.aes;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

/**
 * @author Xucg
 * @since 2017/11/20 0017
 */
public class AesUtil {

    public static final String CIPHER_ALGORITHM = "AES/ECB/PKCS7Padding";
    public static final String KEY_ALGORITHM = "AES";

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    /**
     * AES256解密
     *
     * @param data //密文，被加密的数据
     * @param key  //秘钥
     * @return
     * @throws Exception
     */
    public static String decrypt(String data, String key) throws Exception {
        try {
            //被加密的数据
            byte[] dataByte = Base64.decodeBase64(data.getBytes());

            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM, "BC");

            SecretKeySpec ky = new SecretKeySpec(key.getBytes(), KEY_ALGORITHM);

            // 初始化
            cipher.init(Cipher.DECRYPT_MODE, ky);

            byte[] resultByte = cipher.doFinal(dataByte);

            if (null != resultByte && resultByte.length > 0) {

                return new String(resultByte, "UTF-8");

            }
            return null;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

}
