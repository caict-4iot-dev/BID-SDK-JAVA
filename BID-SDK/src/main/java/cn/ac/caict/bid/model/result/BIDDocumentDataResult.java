package cn.ac.caict.bid.model.result;

import cn.hutool.json.JSONObject;
import com.fasterxml.jackson.annotation.JsonProperty;

public class BIDDocumentDataResult {
    @JsonProperty(value =  "didDocument")
    private JSONObject didDocument;

    public JSONObject getDidDocument() {
        return didDocument;
    }

}
