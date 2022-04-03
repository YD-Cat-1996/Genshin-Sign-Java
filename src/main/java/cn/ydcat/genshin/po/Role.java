package cn.ydcat.genshin.po;

import lombok.Data;

import java.util.List;

/**
 * 游戏角色
 */
@Data
public class Role {
    private String game_biz;
    private String region; // 游戏区编号 cn_gf01:天空岛 cn_qd01:世界树
    private String game_uid; // UID
    private String nickname; // 用户名
    private Integer level; // 等级
    private Boolean is_chosen;
    private String region_name; // 游戏区名
    private Boolean is_official;

    private String cookie; // cookie
    private SignInfo signInfo; // 签到信息
    private List<Award> awardsInfo; // 签到奖励信息
}
