package cn.ac.caict.bid.model.response;

import cn.ac.caict.bid.model.result.BIDDocumentDataResult;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BIDDocumentResponse {
    @JsonProperty(value =  "errorCode")
    private String errorCode;
    @JsonProperty(value =  "message")
    private String message;
    @JsonProperty(value =  "data")
    private BIDDocumentDataResult data;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BIDDocumentDataResult getData() {
        return data;
    }

    public void setData(BIDDocumentDataResult data) {
        this.data = data;
    }
}
