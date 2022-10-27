package cn.ac.caict.bid.model.extension;

public class BIDVerifiableCredentialsOperation {
    private String id ;
    private Integer type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
