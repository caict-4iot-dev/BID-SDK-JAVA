package cn.ac.caict.bid.common;

import java.util.ArrayList;
import java.util.List;

public enum CredentialsType {

    //凭证类型
    Credentials_VERIFIABLE(201, "可信认证"),
    Credentials_EDUCATIONAL(202, "学历认证"),
    Credentials_APTITUDE(203, "资质认证"),
    Credentials_AUTHORIZE(204, "授权认证");

    private final Integer code;
    private final String description;

    CredentialsType(Integer  code, String description) {
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
    public static List<Integer> getCodeEnumsByCredentialsType() {
        List<Integer> list = new ArrayList<>();
        CredentialsType[] values = values();
        for (int i = 0; i < values.length; i++) {
            list.add(values[i].getCode()) ;
        }
        return list;
    }

}
