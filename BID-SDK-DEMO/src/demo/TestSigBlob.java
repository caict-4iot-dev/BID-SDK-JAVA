import cn.ac.caict.bid.SDK;
import cn.ac.caict.bid.exceptions.SDKException;
import cn.ac.caict.bid.util.HexFormatUtil;

public class TestSigBlob {
        public static void main(String[] args){
            SDK bidSdk = new SDK();
            String privateKey = "priSPKhcPY6VdCMoJCtrkSj4zFcLxiBguNJdr1VERwP7LC1SU7";
            String msg = "0a2e61303032643833343562383964633334613537353734656234393736333566663132356133373939666537376236100122b90108012ab4010a2e61303032663836366337663431356537313934613932363131386363353565346365393939656232396231363461123a123866756e6374696f6e206d61696e28696e707574537472297b0a202f2ae8bf99e698afe59088e7baa6e585a5e58fa3e587bde695b02a2f207d1a06080a1a020807223e0a0568656c6c6f1235e8bf99e698afe5889be5bbbae8b4a6e58fb7e79a84e8bf87e7a88be4b8ade8aebee7bdaee79a84e4b880e4b8aa6d65746164617461";
            try {
                String result = HexFormatUtil.byteToHex(bidSdk.signBlob(privateKey, HexFormatUtil.hexStringToBytes(msg)));
                System.out.println(result);
            }catch (SDKException e){
                System.out.println(e.getExceptMessage());
            }
        }
}
