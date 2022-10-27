package src.demo;

import cn.ac.caict.bid.SDK;
import cn.bif.exception.SDKException;
import cn.bif.model.crypto.KeyPairEntity;
import cn.bif.module.encryption.model.KeyType;

public class TestGetBidAndKeyPair {
        public static void main(String[] args){
            testGetBidAndKeyPairByKeyTypeAndChainCode();
            testGetBidAndKeyPairByKeyType();
            testGetBidAndKeyPairByChainCode();
            testGetBidAndKeyPair();
        }

        static void testGetBidAndKeyPairByKeyTypeAndChainCode(){
            SDK bidSdk = new SDK();
            try {
                KeyPairEntity kaypairEntity = bidSdk.getBidAndKeyPair(KeyType.SM2,"aa1c" );
                String publicKey = kaypairEntity.getEncPublicKey();
                String privateKey = kaypairEntity.getEncPrivateKey();
                String bid = kaypairEntity.getEncAddress();
                System.out.println(publicKey);
                System.out.println(privateKey);
                System.out.println(bid);
            } catch (SDKException e) {
                System.out.println(e.getMessage());
            }
        }

        static void testGetBidAndKeyPairByKeyType(){
            SDK bidSdk = new SDK();
            try {
                KeyPairEntity kaypairEntity = bidSdk.getBidAndKeyPair(KeyType.ED25519);
                String publicKey = kaypairEntity.getEncPublicKey();
                String privateKey = kaypairEntity.getEncPrivateKey();
                String bid = kaypairEntity.getEncAddress();
                System.out.println(publicKey);
                System.out.println(privateKey);
                System.out.println(bid);
            }catch (SDKException e) {
                System.out.println(e.getMessage());
            }

        }

        static void testGetBidAndKeyPairByChainCode(){
            SDK bidSdk = new SDK();
            try {
                KeyPairEntity kaypairEntity = bidSdk.getBidAndKeyPair("aa1c");
                String publicKey = kaypairEntity.getEncPublicKey();
                String privateKey = kaypairEntity.getEncPrivateKey();
                String bid = kaypairEntity.getEncAddress();
                System.out.println(publicKey);
                System.out.println(privateKey);
                System.out.println(bid);
            }catch (SDKException e) {
                System.out.println(e.getMessage());
            }
        }

        static void testGetBidAndKeyPair(){
            SDK bidSdk = new SDK();
            try {
                KeyPairEntity kaypairEntity = bidSdk.getBidAndKeyPair();
                String publicKey = kaypairEntity.getEncPublicKey();
                String privateKey = kaypairEntity.getEncPrivateKey();
                String bid = kaypairEntity.getEncAddress();
                System.out.println(publicKey);
                System.out.println(privateKey);
                System.out.println(bid);
            }catch (SDKException e) {
                System.out.println(e.getMessage());
            }
        }
}
