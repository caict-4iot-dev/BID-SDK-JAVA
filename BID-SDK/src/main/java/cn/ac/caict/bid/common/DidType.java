package cn.ac.caict.bid.common;

import java.util.ArrayList;
import java.util.List;

public enum DidType {
    //关联标识属性
    DID_DECRYPT("DIDDecrypt","加解密服务"),
    DID_STORAGE("DIDStorage","存储服务"),
    DID_REVOCATION("DIDRevocation","凭证吊销服务"),
    DID_RESOLVER("DIDResolver","解析服务"),
    DID_SUBRESOLVER("DIDSubResolver","子链解析服务");

    private final String code;
    private final String description;

    DidType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    /**
     * 获得所有枚举类型到list
     * @return
     */
    public static List<String> getCodeEnumsByServerType() {
        List<String> list = new ArrayList<>();
        DidType[] values = values();
        for (int i = 0; i < values.length; i++) {
            list.add(values[i].getCode()) ;
        }
        return list;
    }
}
