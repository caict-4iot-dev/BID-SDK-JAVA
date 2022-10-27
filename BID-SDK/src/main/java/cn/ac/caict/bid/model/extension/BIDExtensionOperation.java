package cn.ac.caict.bid.model.extension;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class BIDExtensionOperation {
    private String recovery[];
    private Long ttl;
    private Integer type;
    private BIDDelegateSignOperation delegateSign;
    private BIDAttributesOperation attributes[];
    private String[] acsns;
    private BIDVerifiableCredentialsOperation verifiableCredentials[];

    public String[] getRecovery() {
        return recovery;
    }

    public void setRecovery(String[] recovery) {
        this.recovery = recovery;
    }

    public Long getTtl() {
        return ttl;
    }

    public void setTtl(Long ttl) {
        this.ttl = ttl;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public BIDAttributesOperation[] getAttributes() {
        return attributes;
    }

    public void setAttributes(BIDAttributesOperation[] attributes) {
        this.attributes = attributes;
    }

    public BIDDelegateSignOperation getDelegateSign() {
        return delegateSign;
    }

    public void setDelegateSign(BIDDelegateSignOperation delegateSign) {
        this.delegateSign = delegateSign;
    }

    public String[] getAcsns() {
        return acsns;
    }

    public void setAcsns(String[] acsns) {
        this.acsns = acsns;
    }

    public BIDVerifiableCredentialsOperation[] getVerifiableCredentials() {
        return verifiableCredentials;
    }

    public void setVerifiableCredentials(BIDVerifiableCredentialsOperation[] verifiableCredentials) {
        this.verifiableCredentials = verifiableCredentials;
    }
}
