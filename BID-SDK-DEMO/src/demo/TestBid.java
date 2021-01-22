import cn.bid.SDK;
import cn.bid.exceptions.SDKException;
import cn.bid.model.KeyPairEntity;
import cn.bid.model.Result;

public class TestBid {
    public static void main(String[] args){
        testGetBidAndKeyPair();
        testGetBidAndKeyPairByChaincode("test");
        String publicKey="B06566766FC2A97B74339EB3F662B49410CD56C23D5CC8B31005459AA92B5D4D7D7563";
        testGetBidByPublicKey(publicKey);
        testGetBidByPublicKeyChaincode(publicKey, "test");
        testIsDidValid();
    }
    static void testGetBidAndKeyPairByChaincode(String chaincode){
        //创建SDK实例
        SDK bidSdk = new SDK();
        try {
            KeyPairEntity kaypairEntity = bidSdk.getBidAndKeyPair(chaincode);
            String publicKey = kaypairEntity.getPublicKey();
            String privateKey = kaypairEntity.getPrivateKey();
            String bid = kaypairEntity.getBid();
            System.out.println(publicKey);
            System.out.println(privateKey);
            System.out.println(bid);
        }catch (SDKException e){
             System.out.println(e.getMessage());;
        }
    }
    static void testGetBidAndKeyPair(){
        //创建SDK实例
        SDK bidSdk = new SDK();
        try {
            KeyPairEntity kaypairEntity = bidSdk.getBidAndKeyPair();
            String publicKey = kaypairEntity.getPublicKey();
            String privateKey = kaypairEntity.getPrivateKey();
            String bid = kaypairEntity.getBid();
            System.out.println(publicKey);
            System.out.println(privateKey);
            System.out.println(bid);
        }catch (SDKException e){
             System.out.println(e.getMessage());;
        }
    }
    static void testGetBidByPublicKey(String publicKey){
        //创建SDK实例
        SDK bidSdk = new SDK();
        try {
            String bid = bidSdk.getBidByBifPubkey(publicKey);
            System.out.println(bid);
        }catch (SDKException e){
             System.out.println(e.getMessage());;
        }
    }
    static void testGetBidByPublicKeyChaincode(String publicKey,String chaincode){
        //创建SDK实例
        SDK bidSdk = new SDK();
        try {
            String bid = bidSdk.getBidByBifPubkey(publicKey, chaincode);
            System.out.println(bid);
        }catch (SDKException e){
             System.out.println(e.getMessage());;
        }
    }
    static  void testIsDidValid(){
    SDK bidSdk = new SDK();
    String bid = "did:bid:efJgt44mNDewKK1VEN454R17cjso3mSG";
        try {
        Result result = bidSdk.isValidBid(bid);
        System.out.println(result);
    } catch (SDKException e) {
         System.out.println(e.getMessage());;
    }}
}
