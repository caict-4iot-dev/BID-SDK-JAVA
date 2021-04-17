import cn.ac.caict.bid.SDK;

public class TestGetBidVersion {
        public static void main(String[] args){
            //create SDK instance
            SDK bidSdk = new SDK();
            String bidVersion= bidSdk.getBidVersion();
            System.out.println(bidVersion);
    }
}
