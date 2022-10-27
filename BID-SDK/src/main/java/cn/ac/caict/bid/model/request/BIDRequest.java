package cn.ac.caict.bid.model.request;

import cn.ac.caict.bid.model.BID;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BIDRequest {
    @JsonProperty(value =  "senderAddress")
    private String senderAddress;
    @JsonProperty(value =  "feeLimit")
    private Long feeLimit=1000000L;
    @JsonProperty(value =  "BIFAmount")
    private Long BIFAmount=0L;
    @JsonProperty(value =  "bid")
    private List<BID> bids;
    @JsonProperty(value =  "ceilLedgerSeq")
    private Long ceilLedgerSeq=0L;
    @JsonProperty(value =  "remarks")
    private String remarks="create DDO";
    @JsonProperty(value =  "privateKey")
    private String privateKey;
    @JsonProperty(value =  "gasPrice")
    private Long gasPrice=1000L;
    @JsonProperty(value =  "domainId")
    private Integer domainId;

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public Long getFeeLimit() {
        return feeLimit;
    }

    public void setFeeLimit(Long feeLimit) {
        this.feeLimit = feeLimit;
    }

    public Long getBIFAmount() {
        return BIFAmount;
    }

    public void setBIFAmount(Long BIFAmount) {
        this.BIFAmount = BIFAmount;
    }

    public List<BID> getBids() {
        return bids;
    }

    public void setBids(List<BID> bids) {
        this.bids = bids;
    }

    public Long getCeilLedgerSeq() {
        return ceilLedgerSeq;
    }

    public void setCeilLedgerSeq(Long ceilLedgerSeq) {
        this.ceilLedgerSeq = ceilLedgerSeq;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public Long getGasPrice() {
        return gasPrice;
    }

    public void setGasPrice(Long gasPrice) {
        this.gasPrice = gasPrice;
    }

    public Integer getDomainId() {
        return domainId;
    }

    public void setDomainId(Integer domainId) {
        this.domainId = domainId;
    }
}
