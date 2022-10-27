package cn.ac.caict.bid.model;

import cn.ac.caict.bid.model.extension.BIDExtensionOperation;
import cn.ac.caict.bid.model.service.BIDServiceOperation;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class BIDDocumentOperation {

    @JsonProperty(value =  "@context")
    private String context[];
    private String version;
    private String id;
    private BIDpublicKeyOperation publicKey[];
    private String authentication[];
    private BIDAlsoKnownAsOperation alsoKnownAs[];
    private BIDExtensionOperation extension;
    private BIDServiceOperation service[];
    private String created;
    private String updated;
    private BIDProofOperstion proof;

    public BIDProofOperstion getProof() {
        return proof;
    }

    public void setProof(BIDProofOperstion proof) {
        this.proof = proof;
    }



    public String[] getContext() {
        return context;
    }
    public void setContext(String[] context) {
        this.context = context;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public BIDpublicKeyOperation[] getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(BIDpublicKeyOperation[] publicKey) {
        this.publicKey = publicKey;
    }

    public BIDServiceOperation[] getService() {
        return service;
    }

    public void setService(BIDServiceOperation[] service) {
        this.service = service;
    }

    public BIDExtensionOperation getExtension() {
        return extension;
    }

    public void setExtension(BIDExtensionOperation extension) {
        this.extension = extension;
    }

    public String[] getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String[] authentication) {
        this.authentication = authentication;
    }

    public BIDAlsoKnownAsOperation[] getAlsoKnownAs() {
        return alsoKnownAs;
    }

    public void setAlsoKnownAs(BIDAlsoKnownAsOperation[] alsoKnownAs) {
        this.alsoKnownAs = alsoKnownAs;
    }
}
