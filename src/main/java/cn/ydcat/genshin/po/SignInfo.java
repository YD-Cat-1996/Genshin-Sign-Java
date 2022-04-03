package cn.ydcat.genshin.po;

import lombok.Data;

/**
 * 签到信息
 */
@Data
public class SignInfo {
    private Integer total_sign_day; // 总签到数
    private String today; // 今天日期
    private Boolean is_sign; // 是否签到
    private Boolean first_bind;
    private Boolean is_sub;
    private Boolean month_first;
    private Integer sign_cnt_missed; // 漏签天数
}
