import cn.ac.caict.bid.SDK;
import cn.ac.caict.bid.constant.EncodeType;
import cn.ac.caict.bid.constant.KeyType;
import cn.ac.caict.bid.exceptions.SDKException;
import cn.ac.caict.bid.model.KeyPairEntity;
import cn.ac.caict.bid.model.Result;
import cn.ac.caict.bid.util.HexFormatUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @program: bid-sdkj-ava
 * @description:
 * @author: tjl
 * @create: 2021-01-19 09:51
 **/
public class BidSdkTest {
    SDK sdk;
    @Before
    public void createTestSdk(){
        sdk=new SDK();
    }

    @Test
    public void getSdkVersionNumber(){
      String sdkVersion= sdk.getSdkVersion();
      Assert.assertEquals("V1.0.1", sdkVersion);
    }

    @Test
    public void getBIDVersionNumber(){
        String BidVersion= sdk.getBidVersion();
        Assert.assertEquals("V1.0.0", BidVersion);
    }

    @Test
    public void getBIDAndKeyPair() throws SDKException {
        KeyPairEntity kaypairEntity = sdk.getBidAndKeyPair();
        String publicKey = kaypairEntity.getPublicKey();
        String privateKey = kaypairEntity.getPrivateKey();
        String bid = kaypairEntity.getBid();
        Assert.assertNotNull(publicKey);
        Assert.assertNotNull(privateKey);
        Assert.assertNotNull(bid);
        System.out.println(publicKey);
        System.out.println(privateKey);
        System.out.println(bid);
    }

    @Test
    public void getBIDAndKeyPairByChaincode() throws SDKException {
        KeyPairEntity kaypairEntity = sdk.getBidAndKeyPair("12av");
        String publicKey = kaypairEntity.getPublicKey();
        String privateKey = kaypairEntity.getPrivateKey();
        String bid = kaypairEntity.getBid();
        Assert.assertNotNull(publicKey);
        Assert.assertNotNull(privateKey);
        Assert.assertNotNull(bid);
        System.out.println(publicKey);
        System.out.println(privateKey);
        System.out.println(bid);
    }

    @Test
    public void getSm2Base58BIDAndKeyPairBy() throws SDKException {
        KeyPairEntity kaypairEntity = sdk.getBidAndKeyPair("12av", KeyType.SM2, EncodeType.Base58);
        String publicKey = kaypairEntity.getPublicKey();
        String privateKey = kaypairEntity.getPrivateKey();
        String bid = kaypairEntity.getBid();
        Assert.assertNotNull(publicKey);
        Assert.assertNotNull(privateKey);
        Assert.assertNotNull(bid);
        System.out.println(publicKey);
        System.out.println(privateKey);
        System.out.println(bid);
    }

    @Test
    public void getSm2Base64BIDAndKeyPairBy() throws SDKException {
        KeyPairEntity kaypairEntity = sdk.getBidAndKeyPair("12av", KeyType.SM2, EncodeType.Base64);
        String publicKey = kaypairEntity.getPublicKey();
        String privateKey = kaypairEntity.getPrivateKey();
        String bid = kaypairEntity.getBid();
        Assert.assertNotNull(publicKey);
        Assert.assertNotNull(privateKey);
        Assert.assertNotNull(bid);
        System.out.println(publicKey);
        System.out.println(privateKey);
        System.out.println(bid);
    }

    @Test
    public void getED25519Base58BIDAndKeyPairBy() throws SDKException {
        KeyPairEntity kaypairEntity = sdk.getBidAndKeyPair("12av", KeyType.ED25519, EncodeType.Base58);
        String publicKey = kaypairEntity.getPublicKey();
        String privateKey = kaypairEntity.getPrivateKey();
        String bid = kaypairEntity.getBid();
        Assert.assertNotNull(publicKey);
        Assert.assertNotNull(privateKey);
        Assert.assertNotNull(bid);
        System.out.println(publicKey);
        System.out.println(privateKey);
        System.out.println(bid);
    }

    @Test
    public void getED25519Base64BIDAndKeyPairBy() throws SDKException {
        KeyPairEntity kaypairEntity = sdk.getBidAndKeyPair("12av", KeyType.ED25519, EncodeType.Base64);
        String publicKey = kaypairEntity.getPublicKey();
        String privateKey = kaypairEntity.getPrivateKey();
        String bid = kaypairEntity.getBid();
        Assert.assertNotNull(publicKey);
        Assert.assertNotNull(privateKey);
        Assert.assertNotNull(bid);
        System.out.println(publicKey);
        System.out.println(privateKey);
        System.out.println(bid);
    }


    @Test
    public void getED25519BidByPublicKey() throws SDKException {
        String bid="did:bid:efJgt44mNDewKK1VEN454R17cjso3mSG";
        String publicKey="B06566766FC2A97B74339EB3F662B49410CD56C23D5CC8B31005459AA92B5D4D7D7563";
        String result = sdk.getBidByBifPubkey(publicKey);
        Assert.assertEquals(bid,result);
    }
    @Test
    public void getSM2BidByPublicKey() throws SDKException {
        String bid="did:bid:zsPQxwMKuQksAGjtTxkzorRAI07iI5HQ==";
        String publicKey="B07A7304B59CD63720D67861F2DDC2C9DEE4BA94449EE36D2A8B63B320C49E143E9316A35E9363BE52ECAA237181E7F7F316EFBE5E0F0BF953750CCE5205FF6D7E0D8070";
        String result = sdk.getBidByBifPubkey(publicKey);
        Assert.assertEquals(bid,result);
    }

    @Test
    public void getED25519BidByPublicKeyAndChaincode() throws SDKException {
        String bid="did:bid:1234:efJgt44mNDewKK1VEN454R17cjso3mSG";
        String publicKey="B06566766FC2A97B74339EB3F662B49410CD56C23D5CC8B31005459AA92B5D4D7D7563";
        String result = sdk.getBidByBifPubkey(publicKey,"1234");
        Assert.assertEquals(bid,result);
    }
    @Test
    public void getSM2BidByPublicKeyAndChaincode() throws SDKException {
        String bid="did:bid:12av:zsPQxwMKuQksAGjtTxkzorRAI07iI5HQ==";
        String publicKey="B07A7304B59CD63720D67861F2DDC2C9DEE4BA94449EE36D2A8B63B320C49E143E9316A35E9363BE52ECAA237181E7F7F316EFBE5E0F0BF953750CCE5205FF6D7E0D8070";
        String result = sdk.getBidByBifPubkey(publicKey,"12av");
        Assert.assertEquals(bid,result);
    }

    @Test
    public void isValidBid() throws SDKException {
        String bid="did:bid:zsPQxwMKuQksAGjtTxkzorRAI07iI5HQ==";
        Result result = sdk.isValidBid(bid);
        System.out.println(result);
        Assert.assertTrue(result.getStatus());
    }

    @Test
    public void isValidED25519Base64Bid() throws SDKException {
        String bid="did:bid:12av:zsPQxwMKuQksAGjtTxkzorRAI07iI5HQ==";
        Result result = sdk.isValidBid(bid);
        System.out.println(result);
        Assert.assertTrue(result.getStatus());
    }

    @Test
    public void isValidSM2Base64Bid() throws SDKException {
        String bid="did:bid:12av:zsCdQOkwJr4AyuSVTt45P2Xp09mvBLug==";
        Result result = sdk.isValidBid(bid);
        System.out.println(result);
        Assert.assertTrue(result.getStatus());
    }
    @Test
    public void isValidED25519Base58Bid() throws SDKException {
        String bid="did:bid:1234:efJgt44mNDewKK1VEN454R17cjso3mSG";
        Result result = sdk.isValidBid(bid);
        System.out.println(result);
        Assert.assertTrue(result.getStatus());
    }

    @Test
    public void isValidSM2Base58Bid() throws SDKException {
        String bid="did:bid:12av:zf7cQMcho9fssnsJcD27GA4qgvedfjyj";
        Result result = sdk.isValidBid(bid);
        System.out.println(result);
        Assert.assertTrue(result.getStatus());
    }

    @Test
    public void signAndVerifyByED25519() throws SDKException {
        String privateKey = "priSPKhcPY6VdCMoJCtrkSj4zFcLxiBguNJdr1VERwP7LC1SU7";
        String publicKey = "b065667cc1e4584bc9ddb6806c455bdea9f8390724a77a6ed2f6369c830043418c0745";
        String msg = "1234";
        String sig =  HexFormatUtil.byteToHex(sdk.signBlob(privateKey, HexFormatUtil.hexStringToBytes(msg)));
        byte[] msgByte = HexFormatUtil.hexStringToBytes(msg);
        byte[] sigByte = HexFormatUtil.hexToByte(sig);
        Assert.assertTrue(sdk.verifySig(publicKey, HexFormatUtil.hexStringToBytes(msg), HexFormatUtil.hexToByte(sig)));
    }

    @Test
    public void signAndVerifyBySM2() throws SDKException {
        String privateKey = "priSrrq25SGG8w5C2XvJS7uWfRojUveCAXw8yZXeeXUDWy1P7a";
        String publicKey = "B07A66048E594CC2D0DC28A6186D519FB5B3A00FCE9EB4375B3CF3DAF220ADA35DA644834EF98A55C52CE27F447A2697CDCD857DFB27BF1DCE9013A900A4AC1A559F3FF4";
        String msg = "1234";
        String sig  = HexFormatUtil.byteToHex(sdk.signBlob(privateKey, HexFormatUtil.hexStringToBytes(msg)));
        byte[] msgByte = HexFormatUtil.hexStringToBytes(msg);
        byte[] sigByte = HexFormatUtil.hexToByte(sig);
        Assert.assertTrue(sdk.verifySig(publicKey,msgByte,sigByte));
    }

    @Test
    public void getBifPubkeyByPrivateKey() throws SDKException {
        String privateKey = "priSrrqp43q9g2fT99zwPeFZn7vnAwZrzi9697mPitUPqbxDJg";
        String firstPublicKey = sdk.getBifPubkeyByPrivateKey(privateKey);
        System.out.println(firstPublicKey);
        String publicKey = "B07A66041C537BBF90DA47E2B3A08D81524CA3711612D06907720B0800C5BD98A6CCDA709C0E83672E9FEBCAE31AF11F01199C948C6C58AB3FBD84582BC247B680987FEE";
        Assert.assertEquals(sdk.getBifPubkeyByPrivateKey(privateKey),publicKey);
    }
}
