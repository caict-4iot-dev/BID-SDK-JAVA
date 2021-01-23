import cn.ac.caict.bid.SDK;

public class TestGetBidVersion {
        public static void main(String[] args){
            //创建SDK实例
            SDK bidSdk = new SDK();
            String bidVersion= bidSdk.getBidVersion();
            System.out.println(bidVersion);
    }
}
