package cn.ac.caict.bid.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 类型码
 */
public enum AddressType {

    //服务地址类型
    ADDRESS_DOMAIN(0,"域名"),
    ADDRESS_IP(1,"IP");

    private final Integer code;
    private final String description;

    AddressType(Integer code, String description) {
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
    public static List<Integer> getCodeEnumsByAddressType() {
        List<Integer> list = new ArrayList<>();
        AddressType[] values = values();
        for (int i = 0; i < values.length; i++) {
            list.add(values[i].getCode()) ;
        }
        return list;
    }

}
