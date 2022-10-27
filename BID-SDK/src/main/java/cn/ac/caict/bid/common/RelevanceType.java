package cn.ac.caict.bid.common;

import java.util.ArrayList;
import java.util.List;

public enum RelevanceType {

    //关联标识属性
    RELEVANCE_BID(101,"BID"),
    RELEVANCE_OTHER(102,"其他DID"),
    RELEVANCE_DOMAIN(103,"域名"),
    RELEVANCE_ZID(104,"Zid"),
    RELEVANCE_HANDLE(105,"handle");

    private final Integer code;
    private final String description;

    RelevanceType(Integer  code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    /**
     * 获得所有枚举类型到list
     * @return
     */
    public static List<Integer> getCodeEnumsByRelevanceType() {
        List<Integer> list = new ArrayList<>();
        RelevanceType[] values = values();
        for (int i = 0; i < values.length; i++) {
            list.add(values[i].getCode()) ;
        }
        return list;
    }

}
