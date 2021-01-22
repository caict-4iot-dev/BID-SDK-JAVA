import cn.bid.SDK;

public class TestGetVersion {
    public static void main(String[] args){
        testGetSdkVersion();
        testGetBidVersion();
    }
    static void testGetSdkVersion(){
        //创建SDK实例
        SDK bidSdk = new SDK();
        String sdkVersion= bidSdk.getSdkVersion();
        System.out.println(sdkVersion);
    }
    static void testGetBidVersion(){
        //创建SDK实例
        SDK bidSdk = new SDK();
        String bidVersion= bidSdk.getBidVersion();
        System.out.println(bidVersion);
    }
}
