import cn.ac.caict.bid.SDK;
import cn.ac.caict.bid.constant.EncodeType;
import cn.ac.caict.bid.constant.KeyType;
import cn.ac.caict.bid.exceptions.SDKException;
import cn.ac.caict.bid.model.KeyPairEntity;

public class TestGetBidAndKeyPair {
        public static void main(String[] args){
            //create SDK instance
            SDK bidSdk = new SDK();
            try {
                KeyPairEntity kaypairEntity = bidSdk.getBidAndKeyPair("aa1c", KeyType.SM2, EncodeType.Base58);
                String publicKey = kaypairEntity.getPublicKey();
                String privateKey = kaypairEntity.getPrivateKey();
                String bid = kaypairEntity.getBid();
                System.out.println(publicKey);
                System.out.println(privateKey);
                System.out.println(bid);
            } catch (SDKException e) {
                System.out.println(e.getExceptMessage());
            }
        }
}
