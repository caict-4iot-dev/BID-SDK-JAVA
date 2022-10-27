package src.demo;

import cn.ac.caict.bid.SDK;
import cn.bif.exception.SDKException;

public class TestGetPubkeyByPrikey {
        public static void main(String[] args){
            String privateKey = "priSrrqp43q9g2fT99zwPeFZn7vnAwZrzi9697mPitUPqbxDJg";
            SDK sdk = new SDK();
            try {
                String publicKey = sdk.getBifPubkeyByPrivateKey(privateKey);
                System.out.println(publicKey);
            }catch (SDKException e){
                System.out.println(e.getMessage());
            }
        }
}

