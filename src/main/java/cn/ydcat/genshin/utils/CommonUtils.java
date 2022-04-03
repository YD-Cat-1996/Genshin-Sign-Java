package cn.ydcat.genshin.utils;

import cn.ydcat.genshin.config.ConfigEnum;
import cn.ydcat.genshin.utils.md5.MD5Utils;
import org.apache.commons.lang3.RandomStringUtils;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CommonUtils {
    private static final String COMPUTER_NAME = System.getenv("COMPUTERNAME");
    // 请求头部
    private static final HashMap<String, String> headers = new HashMap<>();
    static {
        // 静态头部信息
        // 生成UUID
        String uuid = UUID.nameUUIDFromBytes(COMPUTER_NAME.getBytes(StandardCharsets.UTF_8)).toString().toUpperCase();
        uuid = uuid.replaceAll("-","");

        headers.put("User-Agent", ConfigEnum.USER_AGENT.value());
        headers.put("Referer", ConfigEnum.REFERER_URL.value());
        headers.put("Accept-Encoding", "gzip, deflate, br");
        headers.put("x-rpc-device_id", uuid);
        headers.put("x-rpc-client_type", "5"); // 1:ios 2:android 4:pc web 5:mobile web
        headers.put("x-rpc-app_version", ConfigEnum.APP_VERSION.value());
        headers.put("DS", getDS());
    }
    /**
     * 获取请求头部
     * uuid 根据cookie，基于命名空间生成，替换掉-
     * @return
     */
    public static Map<String, String> getHeaders(String cookie){
        // 动态头部信息
        headers.put("Cookie", cookie);
        return headers;
    }

    /**
     * 请求头部中DS的算法
     * @return
     */
    private static final String getDS(){
        String DS = "";
        String n = "h8w582wxwgqvahcdkpvdhbh2w9casgfl"; // 盐
        String i = String.valueOf(System.currentTimeMillis()/1000); // 时间戳，10位
        String r = RandomStringUtils.randomAlphanumeric(6).toLowerCase(); // 2个顺序英文小写字母+顺序十个数字，随机取6个
        String orgStr = "salt=" + n + "&t=" + i + "&r=" + r;
        String c = MD5Utils.encodeToMD5(orgStr).toLowerCase(); // MD5编码，32位小写
        DS = String.format("%s,%s,%s", i,r,c);
        return DS;
    }
}
