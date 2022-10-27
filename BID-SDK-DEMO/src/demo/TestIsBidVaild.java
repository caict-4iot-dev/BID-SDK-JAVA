package src.demo;

import cn.ac.caict.bid.SDK;
import cn.ac.caict.bid.model.result.Result;
import cn.bif.exception.SDKException;

public class TestIsBidVaild {
        public static void main(String[] args){
            SDK bidSdk = new SDK();
            String bid = "did:bid:efJgt44mNDewKK1VEN454R17cjso3mSG";
            try {
                Result result = bidSdk.isValidBid(bid);
                System.out.println(result);
            } catch (SDKException e) {
                System.out.println(e.getMessage());
            }
        }
}
