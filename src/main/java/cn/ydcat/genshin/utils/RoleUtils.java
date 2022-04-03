package cn.ydcat.genshin.utils;

import cn.ydcat.genshin.config.ConfigEnum;
import cn.ydcat.genshin.po.Award;
import cn.ydcat.genshin.po.Role;
import cn.ydcat.genshin.po.SignInfo;
import cn.ydcat.genshin.utils.httpclient.HttpClientUtils;
import cn.ydcat.genshin.utils.httpclient.HttpResult;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RoleUtils {

    /**
     * 获取用户信息
     * @return
     * @throws URISyntaxException
     * @throws IOException
     */
    public static final List<Role> getRolesInfo(String cookie) throws URISyntaxException, IOException {
        Map<String, String> headers = CommonUtils.getHeaders(cookie);
        HttpResult httpResult = HttpClientUtils.sendGet(ConfigEnum.ROLE_INFO_URL.value(), null, headers);
        JSONObject resultJ = JSONObject.parseObject(httpResult.getResult());
        JSONObject data = resultJ.getJSONObject("data");
        JSONArray list = data.getJSONArray("list");
        List<Role> roles = list.toJavaList(Role.class);
        for (Role role : roles) {
            role.setCookie(cookie);
        }
        return roles;
    }

    /**
     * 获取签到信息
     * @param roles
     * @return
     */
    public static final List<Role> getSignInfo(List<Role> roles) throws URISyntaxException, IOException {
        for (Role role : roles) {
            Map<String, String> headers = CommonUtils.getHeaders(role.getCookie());
            String game_uid = role.getGame_uid();
            String region = role.getRegion();
            String url = String.format(ConfigEnum.SIGN_INFO_URL.value(), region, game_uid);

            // 请求信息
            HttpResult httpResult = HttpClientUtils.sendGet(url, null, headers);
            JSONObject resultJ = JSONObject.parseObject(httpResult.getResult());
            JSONObject data = resultJ.getJSONObject("data");
            SignInfo signInfo = data.toJavaObject(SignInfo.class);

            role.setSignInfo(signInfo);
        }
        return roles;
    }

    /**
     * 获取签到奖励信息
     * @param roles
     * @return
     * @throws URISyntaxException
     * @throws IOException
     */
    public static final List<Role> getAwards(List<Role> roles) throws URISyntaxException, IOException {
        for (Role role : roles) {
            Map<String, String> headers = CommonUtils.getHeaders(role.getCookie());
            HttpResult httpResult = HttpClientUtils.sendGet(ConfigEnum.AWARD_URL.value(), null, headers);
            JSONObject resultJ = JSONObject.parseObject(httpResult.getResult());
            JSONObject data = resultJ.getJSONObject("data");
            JSONArray awardsJ = data.getJSONArray("awards");
            List<Award> awards = awardsJ.toJavaList(Award.class);
            role.setAwardsInfo(awards);
        }
        return roles;
    }

    /**
     * 执行签到
     * @param roles
     * @return
     * @throws URISyntaxException
     * @throws IOException
     */
    public static final List<JSONObject> sign(List<Role> roles) throws URISyntaxException, IOException {
        List<JSONObject> list = new ArrayList<>(roles.size());
        for (Role role : roles) {
            Map<String, String> headers = CommonUtils.getHeaders(role.getCookie());

            JSONObject params = new JSONObject();
            params.put("act_id", "e202009291139501");
            params.put("region", "cn_gf01");
            params.put("uid", "101504865");

            HttpResult httpResult = HttpClientUtils.sendPostJson(ConfigEnum.SIGN_URL.value(), params.toJSONString(), headers);
            String result = httpResult.getResult();
            JSONObject resultJ = JSONObject.parseObject(result);
            list.add(resultJ);
        }
        return list;
    }
}
