package com.tu.study.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.*;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author tuyongjian
 * @date 2023/2/27 15:17
 */

public class Aes {



        public static final String ALGORITHM = "AES";

        /**
         * 生成密钥
         *
         * @return
         * @throws NoSuchAlgorithmException
         */
        public static SecretKey generateKey() throws NoSuchAlgorithmException {
            KeyGenerator secretGenerator = KeyGenerator.getInstance(ALGORITHM);
            SecureRandom secureRandom = new SecureRandom();
            secretGenerator.init(secureRandom);
            SecretKey secretKey = secretGenerator.generateKey();
            return secretKey;
        }

        static Charset charset = Charset.forName("UTF-8");

        /**
         * 加密
         *
         * @param content
         * @param secretKey
         * @return
         */
        public static String encrypt(String content, SecretKey secretKey) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException { // 加密
            return new BASE64Encoder().encode(aes(content.getBytes(charset), Cipher.ENCRYPT_MODE, secretKey));
        }

        /**
         * 解密
         *
         * @param contentArray
         * @param secretKey
         * @return
         */
        public static String decrypt(byte[] contentArray, SecretKey secretKey) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException { // 解密
            byte[] result = aes(contentArray, Cipher.DECRYPT_MODE, secretKey);
            return new String(result, charset);
        }

        private static byte[] aes(byte[] contentArray, int mode, SecretKey secretKey)
                throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(mode, secretKey);
            byte[] result = cipher.doFinal(contentArray);
            return result;
        }

        public static void main(String[] args) {
            String content = "{\"businessId\":\"PMS2020\",\"orderId\":\"2000014210608155520711006X056N1L\",\"thirdWay\":\"0\",\"valueCard\":\"0.0\",\"point\":\"0\",\"wallet\":\"0.0\",\"thirdMon\":\"98.0\",\"isUnionFirPay\":\"0\",\"totalAmount\":\"98.0\",\"orderTitle\":\"华住酒店订单2000014210608155520711006X056N1L\",\"partAmount\":\"98.0\",\"isPartPay\":\"0\",\"isCreditAuth\":\"0\",\"language\":\"zh-CN\",\"devNo\":\"6641b4ba1c30f04a\",\"brand\":\"samsung\",\"manufacturer\":\"samsung\",\"model\":\"SM-F9000\",\"os\":\"11\",\"CHANNEL_ID\":\"getui\",\"PUSH_TOKEN\":\"90850db1a508e0148670b0ca166f6a24\",\"Jpush_CHANNEL_ID\":\"getui\",\"Jpush_PUSH_TOKEN\":\"90850db1a508e0148670b0ca166f6a24\",\"access_mode\":\"WIFI\",\"ver\":\"8.0.60\",\"channel\":\"guanwang\",\"platform\":\"android\",\"isUseWifiProxy\":\"UseWifiProxy\",\"traceid\":\"d9f12b18-7fbd-4035-898f-1c5101b18609\",\"echotoken\":\"672d2b40-0dc9-4eb9-813a-9aa3d8743cbc\",\"pageid\":\"709\",\"Token\":\"e86cf210d6c74c078be6e13a26b7db71\",\"ssoToken\":\"f7abeab5e6354909be171231bcf693d8215310089\",\"resultKey\":\"B5iupOTYFZZNiaVhMXyYSF9MVko=\"}" +
                    "{\n" +
                    "\t\"account\": \"18221483894\",\n" +
                    "\t\"mobilePlace\": \"86\",\n" +
                    "\t\"pass\": \"123321ak\",\n" +
                    "\t\"captcha\": \"\",\n" +
                    "\t\"challenge\": \"\",\n" +
                    "\t\"seccode\": \"\",\n" +
                    "\t\"validate\": \"\",\n" +
                    "\t\"language\": \"zh-CN\",\n" +
                    "\t\"devNo\": \"39639e7d7589e11a\",\n" +
                    "\t\"brand\": \"HUAWEI\",\n" +
                    "\t\"manufacturer\": \"HUAWEI\",\n" +
                    "\t\"model\": \"HMA-AL00\",\n" +
                    "\t\"MAC\": \"DC:16:B2:5F:59:F3\",\n" +
                    "\t\"os\": \"10\",\n" +
                    "\t\"CHANNEL_ID\": \"\",\n" +
                    "\t\"PUSH_TOKEN\": \"\",\n" +
                    "\t\"Jpush_CHANNEL_ID\": \"JPush\",\n" +
                    "\t\"Jpush_PUSH_TOKEN\": \"\",\n" +
                    "\t\"access_mode\": \"cmnet\",\n" +
                    "\t\"ver\": \"7.91.7\",\n" +
                    "\t\"channel\": \"huawei\",\n" +
                    "\t\"platform\": \"android\",\n" +
                    "\t\"Longitude\": \"121.337313\",\n" +
                    "\t\"Latitude\": \"31.238848\",\n" +
                    "\t\"isUseWifiProxy\": \"UseWifiProxy\",\n" +
                    "\t\"CurrentCityCode\": \"3101\",\n" +
                    "\t\"NewCurrentCityCode\": \"310100\",\n" +
                    "\t\"NewCurrentCityName\": \"上海\",\n" +
                    "\t\"traceid\": \"6ef97cf8-e2a2-44d9-bfc8-a055e4b0fb63\",\n" +
                    "\t\"echotoken\": \"e070d3b3-9d4c-4181-9f37-b85c90abbd9c\",\n" +
                    "\t\"pageid\": \"540\",\n" +
                    "\t\"resultKey\": \"sdjkfj@#$#@$CC>LK:^&%$#$SFSFSFSF$#@$#@$\"\n" +
                    "}";
            SecretKey secretKey;
            try {

                // 生成密钥
                secretKey = generateKey();
                long timeStartEncry = System.currentTimeMillis();
                String encryptResult = encrypt(content, secretKey);
                long timeEndEncry = System.currentTimeMillis();
                System.out.println("加密后的结果为==" + encryptResult);
                System.out.println("加密用时：" + (timeEndEncry - timeStartEncry));

                long timeStartDncry = System.currentTimeMillis();
                String decryptResult = decrypt(new BASE64Decoder().decodeBuffer(encryptResult), secretKey);
                System.out.println("解密后的结果==" + decryptResult);

                System.out.println("解密用时：" + (System.currentTimeMillis() - timeStartDncry));
            } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | IOException e) {
                e.printStackTrace();
            }
        }
}
