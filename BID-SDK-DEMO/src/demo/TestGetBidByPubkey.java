import cn.ac.caict.bid.SDK;
import cn.ac.caict.bid.exceptions.SDKException;

public class TestGetBidByPubkey {
        public static void main(String[] args){
            String publicKey = "b065667cc1e4584bc9ddb6806c455bdea9f8390724a77a6ed2f6369c830043418c0745";
            SDK bidSdk = new SDK();
            try {
                String bid = bidSdk.getBidByBifPubkey(publicKey);
                System.out.println(bid);
            }catch (SDKException e){
                System.out.println(e.getExceptMessage());
            }
        }
}
