import cn.ac.caict.bid.SDK;

public class TestGetSdkVersion {
    public static void main(String[] args){
        //创建SDK实例
        SDK bidSdk = new SDK();
        String sdkVersion= bidSdk.getSdkVersion();
        System.out.println(sdkVersion);
    }
}