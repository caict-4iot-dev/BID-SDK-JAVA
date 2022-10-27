package src.demo;

import cn.ac.caict.bid.SDK;

public class TestGetSdkVersion {
    public static void main(String[] args){
        //create SDK instance
        SDK sdk = new SDK();
        String sdkVersion= sdk.getSdkVersion();
        System.out.println(sdkVersion);
    }
}
