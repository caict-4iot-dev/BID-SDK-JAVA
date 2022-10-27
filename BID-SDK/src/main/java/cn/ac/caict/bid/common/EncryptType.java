package cn.ac.caict.bid.common;

import java.util.ArrayList;
import java.util.List;

/**
 * 类型码
 */
public enum EncryptType {

    //加密类型
    ENCRYPT_TYPE_NO(0,"非加密"),
    ENCRYPT_TYPE_YES(1,"加密");

    private final Integer code;
    private final String description;

    EncryptType(Integer code, String description) {
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
    public static List<Integer> getCodeEnumsByEncrypt() {
        List<Integer> list = new ArrayList<>();
        EncryptType[] values = values();
        for (int i = 0; i < values.length; i++) {
            list.add(values[i].getCode()) ;
        }
        return list;
    }

}
