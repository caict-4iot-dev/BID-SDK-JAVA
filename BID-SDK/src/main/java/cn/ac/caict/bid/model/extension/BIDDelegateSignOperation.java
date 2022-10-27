package cn.ac.caict.bid.model.extension;


public class BIDDelegateSignOperation {

    private String signer ;

    private String signatureValue;

    public String getSigner() {
        return signer;
    }

    public void setSigner(String signer) {
        this.signer = signer;
    }

    public String getSignatureValue() {
        return signatureValue;
    }

    public void setSignatureValue(String signatureValue) {
        this.signatureValue = signatureValue;
    }
}
