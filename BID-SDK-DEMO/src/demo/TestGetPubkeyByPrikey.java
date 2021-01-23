import cn.ac.caict.bid.SDK;
import cn.ac.caict.bid.exceptions.SDKException;

public class TestGetPubkeyByPrikey {
        public static void main(String[] args){
            String privateKey = "priSPKhcPY6VdCMoJCtrkSj4zFcLxiBguNJdr1VERwP7LC1SU7";
            SDK bidSdk = new SDK();
            try {
                String publicKey = bidSdk.getBifPubkeyByPrivateKey(privateKey);
                System.out.println(publicKey);
            }catch (SDKException e){
                System.out.println(e.getExceptMessage());
            }
        }
}

