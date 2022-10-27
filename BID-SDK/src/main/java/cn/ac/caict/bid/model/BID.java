package cn.ac.caict.bid.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BID {
    @JsonAlias("didDocument")
    private BIDDocumentOperation document;

    public BIDDocumentOperation getDocument() {
        return document;
    }

    public void setDocument(BIDDocumentOperation document) {
        this.document = document;
    }

}
