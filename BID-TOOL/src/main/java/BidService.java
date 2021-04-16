import cn.ac.caict.bid.SDK;
import cn.ac.caict.bid.constant.EncodeType;
import cn.ac.caict.bid.constant.KeyType;
import cn.ac.caict.bid.exceptions.SDKException;
import cn.ac.caict.bid.model.KeyPairEntity;
import cn.ac.caict.bid.model.Result;
import cn.ac.caict.bid.util.HexFormatUtil;

public class BidService {
    public static void getVersion(){
        SDK bidSdk = new SDK();
        System.out.println("version: " + bidSdk.getSdkVersion());
    }
    public static void getBidVersion(){
        SDK bidSdk = new SDK();
        System.out.println("version: " + bidSdk.getBidVersion());
    }
    public static void getBidAndKeypair(String chainCode){

        SDK bidSdk = new SDK();
        KeyPairEntity kaypairEntity = null;
        try{
            if (chainCode != null && !chainCode.isEmpty()){
                kaypairEntity = bidSdk.getBidAndKeyPair(chainCode);
            }else {
                kaypairEntity = bidSdk.getBidAndKeyPair();
            }
            String publicKey = kaypairEntity.getPublicKey();
            String privateKey = kaypairEntity.getPrivateKey();
            String bid = kaypairEntity.getBid();
            System.out.println("public-key: " + publicKey);
            System.out.println("private-key: " + privateKey);
            System.out.println("bid: " + bid);
        }catch (SDKException e){
            System.out.println(e.getExceptMessage());
        }
    }

    public static void getBidAndKeypair(String chainCode, KeyType keyType, EncodeType encodeType){

        SDK bidSdk = new SDK();
        KeyPairEntity kaypairEntity = null;
        try{
            if (chainCode != null && !chainCode.isEmpty()){
                kaypairEntity = bidSdk.getBidAndKeyPair(chainCode,keyType,encodeType);
            }else {
                kaypairEntity = bidSdk.getBidAndKeyPair(null,keyType,encodeType);
            }
            String publicKey = kaypairEntity.getPublicKey();
            String privateKey = kaypairEntity.getPrivateKey();
            String bid = kaypairEntity.getBid();
            System.out.println("public-key: " + publicKey);
            System.out.println("private-key: " + privateKey);
            System.out.println("bid: " + bid);
        }catch (SDKException e){
            System.out.println(e.getExceptMessage());
        }
    }
    public static void getBidByPubKey(String publicKey){
        SDK bidSdk = new SDK();
        String BID = null;
        try {
            BID = bidSdk.getBidByBifPubkey(publicKey);
            System.out.println("bid: " + BID);
        }catch (SDKException e){
            System.out.println(e.getExceptMessage());
        }
    }
    public static void getPubkeyByPrekey(String privateKey) {
        SDK bidSdk = new SDK();
        String publicKey = null;
        try {
            publicKey = bidSdk.getBifPubkeyByPrivateKey(privateKey);
            System.out.println("public-key: " + publicKey);
        }catch (SDKException e){
            System.out.println(e.getExceptMessage());
        }
    }
    public static void signedBlob(String privateKey, String blobData){
        SDK bidSdk = new SDK();
        String result = null;
        try {
            result = HexFormatUtil.byteToHex(bidSdk.signBlob(privateKey, HexFormatUtil.hexStringToBytes(blobData)));
            System.out.println("signed-data: " + result);
        }catch (SDKException e){
            System.out.println(e.getExceptMessage());
        }
    }
    public static void verifysing(String publicKey, String blobData, String sign){
        SDK bidSdk = new SDK();
        boolean result = false;
        try {
            result = bidSdk.verifySig(publicKey, HexFormatUtil.hexStringToBytes(blobData), HexFormatUtil.hexToByte(sign));
            System.out.println("verify-result: " + result);
        }catch (SDKException e) {
            System.out.println(e.getExceptMessage());
        }
    }
    public static void checkBid(String Bid){
        SDK bidSdk = new SDK();
        Result result = null;
        try {
            result = bidSdk.isValidBid(Bid);
            System.out.println("restlt: " + result.getStatus());
            if (!result.getStatus()) {
                System.out.println("failed reason: " + result.getMessage());
            }
        }catch (SDKException e){
            System.out.println(e.getExceptMessage());
        }
    }
}
