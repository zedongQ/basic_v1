package com.qzd.utils;

/**
 * AES 128bit 加密解密工具类
 * @author cuiyuguo
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bcic.utils.base.StringUtils;
import org.bcics.sso.common.encrypt.base64.Base64;
import com.qzd.exception.AESCryptException;

import com.alibaba.fastjson.JSON;

import tk.mybatis.mapper.util.StringUtil;


public class AesCryptUtil {
	private static boolean useaes = Boolean.valueOf(PropertiesUtil.getString("useaes"));
    /**
     * 加密方法
     * @param data  要加密的数据
     * @param key 加密key
     * @param iv 加密iv
     * @return 加密的结果
     * @throws Exception
     */
    public static String encrypt(String data, String key, String iv) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");//"算法/模式/补码方式"
            int blockSize = cipher.getBlockSize();

            byte[] dataBytes = data.getBytes();
            int plaintextLength = dataBytes.length;
            if (plaintextLength % blockSize != 0) {
                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
            }

            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);

            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());

            cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
            byte[] encrypted = cipher.doFinal(plaintext);

			return new String(Base64.encode(encrypted), "UTF-8");

        } catch (Exception e) {
        	e.printStackTrace();
        	throw new AESCryptException(e);
        }
    }

    /**
     * 解密方法
     * @param data 要解密的数据
     * @param key  解密key
     * @param iv 解密iv
     * @return 解密的结果
     * @throws Exception
     */
    public static String decrypt(String data, String key, String iv) throws Exception {
    	try {
    		byte[] encrypted1 = Base64.decode(data);

            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec keyspec = new SecretKeySpec(key.getBytes(), "AES");
            IvParameterSpec ivspec = new IvParameterSpec(iv.getBytes());

            cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);

            byte[] original = cipher.doFinal(encrypted1);
            String originalString = new String(original);
            return originalString;
		} catch (Exception e) {
			e.printStackTrace();
			throw new AESCryptException(e);
		}
    }
    
    /**
     * 使用默认的key和iv加密
     * @param data
     * @return
     * @throws Exception
     */
    public static String encrypt(String data) throws Exception {
    	try {
    		if (useaes) {
    			if (StringUtil.isEmpty(data)) {
        			return data;
        		}
            	String key = StringUtils.random(16);
            	String encrypt = encrypt(data, key, StringUtils.reverse(key));
            	int location = new Random().nextInt(10);
            	return ""+location+encrypt.substring(0,location)+key+encrypt.substring(location);
			} else {
				return data;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new AESCryptException(e);
		}
    }
    
    /**
     * key被整合在加密字符串中
     * @param data
     * @return
     * @throws Exception
     */
    public static String decrypt(String data) throws Exception {
    	try {
    		if (StringUtil.isEmpty(data)) {
    			return data;
    		}
        	int location = data.charAt(0) - '0';
        	String encryptTxt = data.substring(1, location+1) + data.substring(location+17);
        	String encryptKey = data.substring(location+1, location+17);
        	String encryptIVKey = StringUtils.reverse(encryptKey);
        	
            return decrypt(encryptTxt, encryptKey, encryptIVKey);
		} catch (Exception e) {
			e.printStackTrace();
			throw new AESCryptException(e);
		}
    }
    
    /**
     * key被整合在加密字符串中
     * @param data
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	public static Map<String, String> decrypt2JSON(String data) throws Exception {
    	try {
    		if (useaes) {
    			String jsonText = decrypt(data);
            	if (StringUtil.isEmpty(jsonText)) {
        			return new HashMap<String, String>();
        		} else {
        			return JSON.parseObject(jsonText.trim(), Map.class);
        		}
			} else {
				return JSON.parseObject(data, Map.class);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new AESCryptException(e);
		}
    }
    
    /**
     * key被整合在加密字符串中
     * @param data
     * @return 数据模型对象
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
	public static<T> T decrypt2Object(String data, Class<T> objClass) throws Exception {
    	try {
    		if (useaes) {
    			String jsonText = decrypt(data);
            	if (StringUtil.isEmpty(jsonText)) {
        			return null;
        		} else {
        			return JSON.parseObject(jsonText.trim(), objClass);
        		}
			} else {
				return JSON.parseObject(data, objClass);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new AESCryptException(e);
		}
    }
    
    /**
    * 测试
    */
    public static void main(String args[]) throws Exception {
        String test = "fskdjfklsdjfkl";

        String data = null;
        String key = "dufy20170329java";
        String iv = "dufy20170329java";

        data = encrypt(test, key, iv);

        System.out.println(data);
        System.out.println(decrypt(data, key, iv));
        System.out.println(decrypt(RESULT.ok("操作正常").aes()));
    }
    
}