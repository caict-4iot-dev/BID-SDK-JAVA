import cn.ac.caict.bid.SDK;
import cn.ac.caict.bid.exceptions.SDKException;
import cn.ac.caict.bid.util.HexFormatUtil;


public class TestKey {
    public static void main(String[] args){
        String privateKey = "priSPKhcPY6VdCMoJCtrkSj4zFcLxiBguNJdr1VERwP7LC1SU7";
        testSign(privateKey);

        String publicKey = "b065667cc1e4584bc9ddb6806c455bdea9f8390724a77a6ed2f6369c830043418c0745";
        testVerify(publicKey);
        testGetPublickeyByPrivateKey(privateKey);
    }
    static void testGetPublickeyByPrivateKey(String privateKey){
        SDK bidSdk = new SDK();
        try {
            String publicKey = bidSdk.getBifPubkeyByPrivateKey(privateKey);
            System.out.println(publicKey);
        }catch (SDKException e){
             System.out.println(e.getMessage());;
        }
    }
    static void testSign(String privateKey){
        SDK bidSdk = new SDK();
        String msg = "1234";
        try {
            String result = HexFormatUtil.byteToHex(bidSdk.signBlob(privateKey, HexFormatUtil.hexStringToBytes(msg)));
            System.out.println(result);
        }catch (SDKException e){
             System.out.println(e.getMessage());;
        }
    }
    static void testVerify(String publicKey){
        SDK bidSdk = new SDK();
        String msg = "1234";
        String sig = "B91AB8D815D3230AC678AE560351A10CC536470F80C6B0B89498BB0DA2811A1A5500AAE1AAE25EF05FBC6FB0F9CBE919779C28F424629E7B324E9EA81924550D";
        try {
            boolean result = bidSdk.verifySig(publicKey, HexFormatUtil.hexStringToBytes(msg), HexFormatUtil.hexToByte(sig));
            System.out.println(result);
        }catch (SDKException e){
             System.out.println(e.getMessage());;
        }
    }
}
