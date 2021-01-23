import cn.ac.caict.bid.SDK;
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
      Assert.assertEquals("V1.0.0", sdkVersion);
    }

    @Test
    public void getBIDVersionNumber(){
        String BidVersion= sdk.getBidVersion();
        Assert.assertEquals("V1.0.0", BidVersion);
    }

    @Test
    public void getDefaultBIDAndKeyPair() throws SDKException {
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
    public void getDefaultBIDAndKeyPairByChaincode() throws SDKException {
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
    public void getED25519BidByPublicKey() throws SDKException {
        String bid="did:bid:efJgt44mNDewKK1VEN454R17cjso3mSG";
        String publicKey="B06566766FC2A97B74339EB3F662B49410CD56C23D5CC8B31005459AA92B5D4D7D7563";
        String result = sdk.getBidByBifPubkey(publicKey);
        Assert.assertEquals(bid,result);
    }
    @Test
    public void getED25519BidByPublicKeyByChaincode() throws SDKException {
        String bid="did:bid:1234:efJgt44mNDewKK1VEN454R17cjso3mSG";
        String publicKey="B06566766FC2A97B74339EB3F662B49410CD56C23D5CC8B31005459AA92B5D4D7D7563";
        String result = sdk.getBidByBifPubkey(publicKey,"1234");
        Assert.assertEquals(bid,result);
    }
    @Test
    public void isValidED25519Bid() throws SDKException {
        String bid="did:bid:efJgt44mNDewKK1VEN454R17cjso3mSG";
        Result result = sdk.isValidBid(bid);
        System.out.println(result);
        Assert.assertTrue(result.getStatus());
    }
    @Test
    public void isValidED25519ChaincodeBid() throws SDKException {
        String bid="did:bid:1234:efJgt44mNDewKK1VEN454R17cjso3mSG";
        Result result = sdk.isValidBid(bid);
        System.out.println(result);
        Assert.assertTrue(result.getStatus());
    }
    @Test
    public void signMessageByED25519() throws SDKException {
        String msg = "1234";
        String privateKey = "priSPKhcPY6VdCMoJCtrkSj4zFcLxiBguNJdr1VERwP7LC1SU7";
        String result  = HexFormatUtil.byteToHex(sdk.signBlob(privateKey, HexFormatUtil.hexStringToBytes(msg)));
        System.out.println(result);
        Assert.assertEquals(result,"B91AB8D815D3230AC678AE560351A10CC536470F80C6B0B89498BB0DA2811A1A5500AAE1AAE25EF05FBC6FB0F9CBE919779C28F424629E7B324E9EA81924550D");
    }
    @Test
    public void verifySignByED25519() throws SDKException {
        String publicKey = "b065667cc1e4584bc9ddb6806c455bdea9f8390724a77a6ed2f6369c830043418c0745";
        String msg = "1234";
        String sig = "B91AB8D815D3230AC678AE560351A10CC536470F80C6B0B89498BB0DA2811A1A5500AAE1AAE25EF05FBC6FB0F9CBE919779C28F424629E7B324E9EA81924550D";
        Assert.assertTrue(sdk.verifySig(publicKey, HexFormatUtil.hexStringToBytes(msg), HexFormatUtil.hexToByte(sig)));
    }

    @Test
    public void getBifPubkeyByPrivateKey() throws SDKException {
        String privateKey = "priSPKhcPY6VdCMoJCtrkSj4zFcLxiBguNJdr1VERwP7LC1SU7";
        String publicKey = "B065667CC1E4584BC9DDB6806C455BDEA9F8390724A77A6ED2F6369C830043418C0745";
        Assert.assertEquals(sdk.getBifPubkeyByPrivateKey(privateKey),publicKey);
    }
}
