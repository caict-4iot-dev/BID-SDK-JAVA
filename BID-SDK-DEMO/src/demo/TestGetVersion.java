import cn.ac.caict.bid.SDK;

public class TestGetVersion {
    public static void main(String[] args){
        testGetSdkVersion();
        testGetBidVersion();
    }
    static void testGetSdkVersion(){
        //create SDK instance
        SDK bidSdk = new SDK();
        String sdkVersion= bidSdk.getSdkVersion();
        System.out.println(sdkVersion);
    }
    static void testGetBidVersion(){
        //create SDK instance
        SDK bidSdk = new SDK();
        String bidVersion= bidSdk.getBidVersion();
        System.out.println(bidVersion);
    }
}
