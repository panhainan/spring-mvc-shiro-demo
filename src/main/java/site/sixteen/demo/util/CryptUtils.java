package site.sixteen.demo.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import site.sixteen.demo.enums.AlgorithmEnum;

/**
 * @author panhainan@yeah.net(@link http://sixteen.site)
 **/
@Slf4j
public class CryptUtils {

    public static String encrypt(String saltStr, String toBeEncryptStr, String hashAlgorithmName, int hashIterations) {
        Object credentials = toBeEncryptStr;
        Object salt = ByteSource.Util.bytes(saltStr);
        Object result = new SimpleHash(hashAlgorithmName, credentials, salt, hashIterations);
        return String.valueOf(result);
    }

    public static void main(String[] args) {
        String saltStr = "tourist";
        String toBeEncryptStr = "123456";
        int hashIterations = 1;
        log.info("{}:{}", saltStr, toBeEncryptStr);
        log.info("{}", CryptUtils.encrypt(saltStr, toBeEncryptStr, AlgorithmEnum.MD5.value(), hashIterations));

    }
}
