import cn.ac.caict.bid.SDK;
import cn.ac.caict.bid.exception.SdkError;
import cn.ac.caict.bid.exception.SdkException;
import cn.ac.caict.bid.model.result.Result;
import cn.ac.caict.bid.utils.CheckTool;
import cn.bif.exception.EncException;
import cn.bif.exception.SDKException;
import cn.bif.model.crypto.KeyPairEntity;
import cn.bif.module.encryption.model.KeyType;
import cn.bif.utils.hex.HexFormat;


public class BidService {
    public static void getVersion(){
        SDK bidSdk = new SDK();
        System.out.println("version: " + bidSdk.getSdkVersion());
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
            String publicKey = kaypairEntity.getEncPublicKey();
            String privateKey = kaypairEntity.getEncPrivateKey();
            String bid = kaypairEntity.getEncAddress();
            System.out.println("public-key: " + publicKey);
            System.out.println("private-key: " + privateKey);
            System.out.println("bid: " + bid);
        }catch (SDKException e){
            System.out.println(e.getErrorDesc());
        }
    }

    public static void getBidAndKeypair(String chainCode, KeyType keyType){
        SDK bidSdk = new SDK();
        KeyPairEntity kaypairEntity = null;
        try{
            if (chainCode != null && !chainCode.isEmpty()){
                kaypairEntity = bidSdk.getBidAndKeyPair(keyType,chainCode);
            }else {
                kaypairEntity = bidSdk.getBidAndKeyPair(keyType);
            }
            String publicKey = kaypairEntity.getEncPublicKey();
            String privateKey = kaypairEntity.getEncPrivateKey();
            String bid = kaypairEntity.getEncAddress();
            System.out.println("public-key: " + publicKey);
            System.out.println("private-key: " + privateKey);
            System.out.println("bid: " + bid);
        }catch (SDKException e){
            System.out.println(e.getErrorDesc());
        }
    }

    public static void getBidByPublicKey(String publicKey){
        SDK bidSdk = new SDK();
        String BID = null;
        try {
            BID = bidSdk.getBidByBifPublicKey(publicKey);
            System.out.println("bid: " + BID);
        }catch (EncException e){
            System.out.println("publicKey: " + publicKey+ " is invalid");
        }catch (IllegalArgumentException e){
            System.out.println("publicKey: " + publicKey+ " is invalid");
        }catch (SDKException e){
            System.out.println(e.getErrorDesc());
        }
    }

    public static void getPubkeyByPrekey(String privateKey) {
        SDK bidSdk = new SDK();
        String publicKey = null;
        try {
            publicKey = bidSdk.getBifPubkeyByPrivateKey(privateKey);
            System.out.println("public-key: " + publicKey);
        }catch (EncException e){
            System.out.println("privateKey: " + privateKey+ " is invalid");
        }catch (IllegalArgumentException e){
            System.out.println("privateKey: " + privateKey+ " is invalid");
        }catch (SDKException e){
            System.out.println(e.getErrorDesc());
        }
    }

    public static void signedBlob(String privateKey, String blobData){
        SDK bidSdk = new SDK();
        String result = null;
        try {
            result = HexFormat.byteToHex(bidSdk.signBlob(blobData,privateKey));
            System.out.println("signed-data: " + result);
        }catch (EncException e){
            System.out.println("privateKey: " + privateKey+ " is invalid");
        }catch (IllegalArgumentException e){
            System.out.println("privateKey: " + privateKey+ " is invalid");
        }catch (SDKException e){
            System.out.println(e.getErrorDesc());
        }
    }

    public static void verifysing(String publicKey, String blobData, String sign){
        SDK bidSdk = new SDK();
        boolean result = false;
        try {
            result = bidSdk.verifySig(publicKey, blobData, HexFormat.hexToByte(sign));
            System.out.println("verify-result: " + result);
        }catch (EncException e){
            System.out.println("verify-result: " + result);
        }catch (IllegalArgumentException e) {
            System.out.println("verify-result: " + result);
        }catch (SDKException e) {
            System.out.println(e.getErrorDesc());
        }
    }

    public static void checkBid(String Bid){
        SDK bidSdk = new SDK();
        Result result = null;
        try {
            result = bidSdk.isValidBid(Bid);
            if (!result.getStatus()) {
                System.out.println("failed reason: " + result.getMessage());
            }
            System.out.println("result: " + result.getStatus());
        }catch (SDKException e){
            System.out.println(e.getErrorDesc());
        }
    }

    public static void getBIDTemplate(){
        SDK bidSdk = new SDK();
        Result result =null;
        try {
            result = bidSdk.getBIDTemplate();
            System.out.println("bid-template-result: " + result.getMessage());
        }catch (SDKException e) {
            System.out.println(e.getErrorDesc());
        }
    }

    public static void createBidByTemplate(String url,String Bid){
        SDK bidSdk = null;
        try {
            bidSdk = SDK.getInstance(url);
        } catch (SDKException e) {
            System.out.println("failed reason: " + e.getMessage());
        }
        Result result = null;
        try {
            result = bidSdk.createBIDByTemplate(Bid);
            if (!result.getStatus()) {
                System.out.println("failed reason: " + result.getMessage());
            }else{
                System.out.println("create-result: " + result.getMessage());
            }
        } catch (SDKException e){
            System.out.println(e.getErrorDesc());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void updateBidByTemplate(String url,String Bid){
        SDK bidSdk = null;
        try {
            bidSdk = SDK.getInstance(url);
        } catch (SDKException e) {
            System.out.println("failed reason: " + e.getMessage());
        }
        Result result = null;
        try {
            result = bidSdk.updateBIDByTemplate(Bid);
            if (!result.getStatus()) {
                System.out.println("failed reason: " + result.getMessage());
            }else {
                System.out.println("update-result: " + result.getMessage());
            }
        }catch (SDKException e){
            System.out.println(e.getErrorDesc());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void isValidProof(String Bid){
        SDK bidSdk = new SDK();
        Result result = null;
        try {
            result = bidSdk.isValidProof(Bid);
            if (!result.getStatus()) {
                System.out.println("failed reason: " + result.getMessage());
            }
            System.out.println("result: " + result.getStatus());
        }catch (SDKException e){
            System.out.println(e.getErrorDesc());
        }
    }

    public static void bidQueryByContract(String url,String bidAddress){
        SDK bidSdk = null;
        try {
            bidSdk = SDK.getInstance(url);
        } catch (SDKException e) {
            System.out.println("failed reason: " + e.getMessage());
        }
        Result result = null;
        try {
            result = bidSdk.bidQueryByContract(bidAddress);
            if (!result.getStatus()) {
                System.out.println("failed reason: " + result.getMessage());
            }else {
                System.out.println("result: " + result.getMessage());
            }
        }catch (SDKException e){
            System.out.println(e.getErrorDesc());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void resolverBid(String url,String bidAddress){
        SDK bidSdk = null;
        try {
            bidSdk = SDK.getInstance(url);
        } catch (SDKException e) {
            throw new SdkException(SdkError.CONNECTNETWORK_ERROR);
        }
        Result result = null;
        try {
            result = bidSdk.resolverBid(bidAddress);
            if (!result.getStatus()) {
                System.out.println("failed reason: " + result.getMessage());
            }else {
                System.out.println("result: " + result.getMessage());
            }
        }catch (SDKException e){
            System.out.println(e.getErrorDesc());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void queryTransactionInfoByHash(String url,String hash){
        SDK bidSdk = null;
        try {
            bidSdk = SDK.getInstance(url);
        } catch (SDKException e) {
            System.out.println("failed reason: " + e.getMessage());
        }
        Result result = null;
        try {
            result = bidSdk.queryTransactionInfoByHash(hash);
            if (!result.getStatus()) {
                System.out.println("failed reason: " + result.getMessage());
            }else {
                System.out.println("result: " + result.getMessage());
            }
        }catch (SDKException e){
            System.out.println(e.getErrorDesc());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public static void getBidByHash(String url,String hash){
        SDK bidSdk = null;
        try {
            bidSdk = SDK.getInstance(url);
        } catch (SDKException e) {
            System.out.println("failed reason: " + e.getMessage());
        }
        Result result = null;
        try {
            result = bidSdk.getBidByHash(hash);
            if (!result.getStatus()) {
                System.out.println("failed reason: " + result.getMessage());
            }else {
                System.out.println("result: " + result.getMessage());
            }
        }catch (SDKException e){
            System.out.println(e.getErrorDesc());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
