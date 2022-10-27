package cn.ac.caict.bid.common;

import java.util.*;

public enum AttributeType {
    //属性类型
    ATTRIBUTE_PERSON(101,"人"),
    ATTRIBUTE_ENTERPRISE(102,"企业"),
    ATTRIBUTE_NODE(103,"节点"),
    ATTRIBUTE_EQUIPMENT(104,"智能设备"),
    ATTRIBUTE_CONTRACT(105,"智能合约"),
    ATTRIBUTE_IMAGE(201,"图片"),
    ATTRIBUTE_VIDEO(202,"视频"),
    ATTRIBUTE_FILE(203,"文档"),
    ATTRIBUTE_RESOURCE(204,"资源数据"),
    ATTRIBUTE_VC(205,"凭证"),
    ATTRIBUTE_AC(206,"AC号"),
    ATTRIBUTE_OTHER(999,"其他");

    private final Integer code;
    private final String description;

    AttributeType(Integer  code, String description) {
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
    public static List<Integer> getCodeEnumsByAttributeType() {
        List<Integer> list = new ArrayList<>();
        AttributeType[] values = values();
        for (int i = 0; i < values.length; i++) {
            list.add(values[i].getCode()) ;
        }
        return list;
    }

}
