package cn.ydcat;

import cn.ydcat.genshin.po.Role;
import cn.ydcat.genshin.utils.RoleUtils;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws URISyntaxException, IOException {
        String cookie_property = System.getProperty("cookie");
        String[] cookies = cookie_property.split("#");
        for (String cookie : cookies) {
            List<Role> roles = RoleUtils.getRolesInfo(cookie);
            RoleUtils.getSignInfo(roles);
            RoleUtils.getAwards(roles);
            List<JSONObject> sign = RoleUtils.sign(roles);
            System.out.println(sign);
        }
    }
}