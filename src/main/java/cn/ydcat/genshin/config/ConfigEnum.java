package cn.ydcat.genshin.config;

public enum ConfigEnum {
    GAME_BIZ("hk4e_cn"),
    APP_VERSION("2.3.0"),
    USER_AGENT("Mozilla/5.0 (iPhone; CPU iPhone OS 14_0_1 like Mac OS X) AppleWebKit/605.1.15 (KHTML, like Gecko) miHoYoBBS/"+APP_VERSION.value()),
    ACT_ID("e202009291139501"),
    REFERER_URL("https://webstatic.mihoyo.com/bbs/event/signin-ys/index.html?" +
                        "act_id=" + ACT_ID.value() +
                        "&bbs_auth_required=" + "true" +
                        "&utm_source=" + "bbs" +
                        "&utm_medium=" + "mys" +
                        "&utm_campaign=" + "icon"),
    // 角色信息地址
    ROLE_INFO_URL("https://api-takumi.mihoyo.com/binding/api/getUserGameRolesByCookie?game_biz="+GAME_BIZ.value()),
    // 签到信息地址
    SIGN_INFO_URL("https://api-takumi.mihoyo.com/event/bbs_sign_reward/info?region=%s&uid=%s&act_id="+ACT_ID.value()),
    // 签到地址;
    SIGN_URL("https://api-takumi.mihoyo.com/event/bbs_sign_reward/sign"),
    // 获取签到奖励信息
    AWARD_URL("https://api-takumi.mihoyo.com/event/bbs_sign_reward/home?act_id="+ACT_ID.value());

    private String value;

    ConfigEnum(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
