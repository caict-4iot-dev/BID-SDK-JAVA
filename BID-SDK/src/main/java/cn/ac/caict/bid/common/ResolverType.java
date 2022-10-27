package cn.ac.caict.bid.common;

import java.util.ArrayList;
import java.util.List;

public enum ResolverType {

    //解析类型
    RESOLVER_UDP(0,"UDP"),
    RESOLVER_TCP(1,"TCP"),
    RESOLVER_HTTP(2,"HTTP"),
    RESOLVER_HTTPS(3,"HTTPS");

    private final Integer code;
    private final String description;

    ResolverType(Integer  code, String description) {
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
    public static List<Integer> getCodeEnumsByResolverType() {
        List<Integer> list = new ArrayList<>();
        ResolverType[] values = values();
        for (int i = 0; i < values.length; i++) {
            list.add(values[i].getCode()) ;
        }
        return list;
    }

}
