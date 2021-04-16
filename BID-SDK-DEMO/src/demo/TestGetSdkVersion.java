import cn.ac.caict.bid.SDK;

public class TestGetSdkVersion {
    public static void main(String[] args){
        //create SDK instance
        SDK bidSdk = new SDK();
        String sdkVersion= bidSdk.getSdkVersion();
        System.out.println(sdkVersion);
    }
}