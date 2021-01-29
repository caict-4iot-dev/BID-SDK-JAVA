
import cn.ac.caict.bid.SDK;
import cn.ac.caict.bid.exceptions.ExceptionCommon;
import cn.ac.caict.bid.exceptions.SDKException;
import cn.ac.caict.bid.model.Result;
import cn.ac.caict.bid.util.HexFormatUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



public class BidFailTest {
    SDK sdk;

    @Before
    public void createTestSdk() {
        sdk = new SDK();
    }


    @Test
    public void pubkeyInvalidException() {
        try {
            String publicKey = "B065746C57FF5B12BCBBC38D236SFB8205";
            sdk.getBidByBifPubkey(publicKey, "1233");
        } catch (SDKException e) {
            Assert.assertEquals(ExceptionCommon.EXCEPTIONCODE_INVALID_KEY, e.getExceptionCode());
        }

    }

    @Test
    public void pubkeyNullException() {
        try {
            sdk.getBidByBifPubkey(null, "1233");
        } catch (SDKException e) {
            Assert.assertEquals(ExceptionCommon.EXCEPTIONCODE_INVALID_KEY, e.getExceptionCode());
        }

    }

    @Test
    public void chainCodeInvalidException() {
        try {
            String publicKey = "B06566766FC2A97B74339EB3F662B49410CD56C23D5CC8B31005459AA92B5D4D7D7563";
            String bid= sdk.getBidByBifPubkey(publicKey, "1233");
            System.out.println(bid);
        } catch (SDKException e) {
            Assert.assertEquals(ExceptionCommon.EXCEPTIONCODE_INVALID_CHAINCODE, e.getExceptionCode());
        }
    }
    @Test
    public void bidInvalidException() throws SDKException {
        String bid = "did:bid:asds:123:ef7M8MBQrQBMLiqJZaoCdXuwWyzH9pxe";
        String bid1 = "did:bid:";
        Result result = sdk.isValidBid(bid);
        Assert.assertEquals("Invalid address", result.getMessage());
        Result result1 = sdk.isValidBid(bid1);
        Assert.assertEquals("Invalid address", result.getMessage());
    }

    @Test
    public void bidNullException() throws SDKException {
        String bid = null;
        Result result = sdk.isValidBid(bid);
        Assert.assertEquals("Invalid address", result.getMessage());
    }
    @Test
    public void bidLenInvalidException() throws SDKException {
            String bid = "did:bid:1234:asdasddddddddddddddddddddddaaaaaaaaaaaaaaaaaaadfgffffsf3RQhfPDauDmWhwdESaCiDL4";
            Result result = sdk.isValidBid(bid);
            Assert.assertEquals("Address's length error", result.getMessage());
    }
    @Test
    public void bidPrefixInvalidException() throws SDKException {
        String bid = "did:vaa:ef7M8MBQrQBMLiqJZaoCdXuwWyzH9pxe";
        Result result = sdk.isValidBid(bid);
        Assert.assertEquals("Invalid address prefix", result.getMessage());
    }
    @Test
    public void bidChaincodeInvalidException() throws SDKException {
        String bid = "did:bid:张三:ef7M8MBQrQBMLiqJZaoCdXuwWyzH9pxe";
        Result result = sdk.isValidBid(bid);
        Assert.assertEquals("Invalid chaincode", result.getMessage());
    }
    @Test
    public void bidKeyTypeInvalidException() throws SDKException {
        String bid = "did:bid:af7M8MBQrQBMLiqJZaoCdXuwWyzH9pxe";
        Result result = sdk.isValidBid(bid);
        Assert.assertEquals("Invalid address,Unsupported KeyType", result.getMessage());
    }

    @Test
    public void bidEncodeTypeInvalidException() throws SDKException {
        String bid = "did:bid:ea7M8MBQrQBMLiqJZaoCdXuwWyzH9pxe";
        Result result = sdk.isValidBid(bid);
        Assert.assertEquals("Invalid address,Unsupported EncodeType", result.getMessage());
    }

    @Test
    public void bidEncodeInvalidException() throws SDKException {
        String bid = "did:bid:ef7M8MBQrQBMLiqJsdfZaoCdXuwWyzH9pxe";
        Result result = sdk.isValidBid(bid);
        Assert.assertEquals("Invalid address,The length of the public key hash is not valid", result.getMessage());
    }
    @Test
    public void verifyFailedException() throws SDKException {
        String publicKey = "b065667cc1e4584bc9ddb6806c455bdea9f8390724a77a6ed2f6369c830043418c0745";
        String msg = "123412";
        String sig = "B91AB8D815D3230AC678AE560351A10CC536470F80C6B0B89498BB0DA2811A1A5500AAE1AAE25EF05FBC6FB0F9CBE919779C28F424629E7B324E9EA81924550D";
        Assert.assertFalse(sdk.verifySig(publicKey, HexFormatUtil.hexStringToBytes(msg), HexFormatUtil.hexToByte(sig)));
    }
}
