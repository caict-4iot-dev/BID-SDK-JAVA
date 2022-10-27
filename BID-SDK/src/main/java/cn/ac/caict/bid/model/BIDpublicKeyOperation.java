package cn.ac.caict.bid.model;

import cn.bif.module.encryption.model.KeyType;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class BIDpublicKeyOperation {
    private String id;
    private KeyType type ;
    private String publicKeyHex;
    private String controller;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public KeyType getType() {
        return type;
    }

    public void setType(KeyType type) {
        this.type = type;
    }

    public String getPublicKeyHex() {
        return publicKeyHex;
    }

    public void setPublicKeyHex(String publicKeyHex) {
        this.publicKeyHex = publicKeyHex;
    }

    public String getController() {
        return controller;
    }

    public void setController(String controller) {
        this.controller = controller;
    }
}
