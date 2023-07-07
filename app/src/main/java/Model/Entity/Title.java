package Model.Entity;

public class Title {

    private int Id; //称号编号
    private String name; // 称号名称
    private String description; // 称号描述

    // 构造函数
    public Title(int id,String name, String description) {
        this.Id=id;
        this.name = name;
        this.description = description;
    }

    // 获取称号名称
    public String getName(Player player) {

        return name;
    }

    // 获取称号描述
    public String getDescription() {
        return description;
    }

    // 称号列表
    public static final Title[] TITLES = {
            new Title(1,"初尝胜果", "赢得1局游戏"),
            new Title(2,"小有所成", "赢得10局游戏"),
            new Title(3,"游戏达人", "赢得100局游戏"),
            new Title(4,"顺风顺水", "连胜5局"),
            new Title(5,"常胜将军", "连胜10局"),
            new Title(6,"打酱油的", "在一局游戏中，一张牌也没有出"),
            new Title(7,"行云流水", "同花顺五张")
    };
}