/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 *© COPYRIGHT 2021 Corporation CAICT All rights reserved.
 * http://www.caict.ac.cn
 * https://www.citln.cn/
 */
package cn.ac.caict.bid;

import cn.ac.caict.bid.constant.EncodeType;
import cn.ac.caict.bid.constant.KeyType;
import cn.ac.caict.bid.core.address.Bid;
import cn.ac.caict.bid.core.privkey.PrivateKeyManager;
import cn.ac.caict.bid.core.pubkey.PublicKeyManager;
import cn.ac.caict.bid.exceptions.SDKException;
import cn.ac.caict.bid.model.KeyPairEntity;
import cn.ac.caict.bid.model.Result;

public class SDK {
    private final static String sdkVersion="V1.0.1";
    /**
     * Return the SDK version
     * @return
     */
    public String getSdkVersion() {
        return sdkVersion;
    }

    /**
     * Return the BID version
     * @return
     */
    public String getBidVersion() {
        return Bid.getBidVersionNumber();
    }

    /**
     * Signature verification
     * @param publicKey The BIF format public key
     * @param srcMsg Message to be verified
     * @param signature signature
     * @return
     */
    public boolean verifySig(String publicKey, byte[] srcMsg, byte[] signature)throws SDKException {
        PublicKeyManager publicKeyMgr =  new PublicKeyManager(publicKey);
        return publicKeyMgr.verifySign(srcMsg, signature);
    }

    /**
     * signature
     * @param prviteKey Private key in BIF format
     * @param message Message to be verified
     * @return
     */
    public byte[] signBlob(String prviteKey, byte[] message)throws SDKException {
        PrivateKeyManager privateKeyMgr = new PrivateKeyManager(prviteKey);
        return  privateKeyMgr.signBlob(message);
    }

    /**
     * Verify the BID format is legal
     * @param BID
     * @return
     */
    public Result isValidBid(String BID) throws SDKException {
        return Bid.isValidBid(BID);
    }

    /**
     * Generate BID address according to user's public key in BIF format
     * @param publKey
     * @param chaincode
     * @return
     */
    public String getBidByBifPubkey(String publKey, String chaincode) throws SDKException {
        Bid bid = new Bid(publKey, chaincode);
        return bid.getBidStr();
    }

    /**
     * Generate BID address according to user's public key in BIF format
     * @param publKey
     * @return
     */
    public String getBidByBifPubkey(String publKey) throws SDKException {
        Bid bid = new Bid(publKey,null);
        return bid.getBidStr();
    }

    /**
     * Get the default bid address and the public&private key pair in BIF format，
     * @param chaincode
     * @return
     */
    public KeyPairEntity getBidAndKeyPair(String chaincode) throws SDKException {
        PrivateKeyManager privateKeyManager = new PrivateKeyManager(KeyType.ED25519, EncodeType.Base58);
        PublicKeyManager publicKeyManager = new PublicKeyManager(privateKeyManager.getRawPublicKey(),privateKeyManager.getKeyType(), privateKeyManager.getEncodeType());
        Bid bid = new Bid(privateKeyManager.getRawPublicKey(),chaincode,privateKeyManager.getKeyType(), privateKeyManager.getEncodeType());

        KeyPairEntity keyPairEntity = new KeyPairEntity();
        keyPairEntity.setPrivateKey(privateKeyManager.getBifPrivateKey());
        keyPairEntity.setPublicKey(publicKeyManager.getBifPublicKey());
        keyPairEntity.setBid(bid.getBidStr());
        return keyPairEntity;
    }

    /**
     * Get the bid address and the public&private key pair in BIF format，
     * @param chaincode
     * @return
     */
    public KeyPairEntity getBidAndKeyPair(String chaincode,KeyType keyType,EncodeType encodeType) throws SDKException {
        PrivateKeyManager privateKeyManager = new PrivateKeyManager(keyType, encodeType);
        PublicKeyManager publicKeyManager = new PublicKeyManager(privateKeyManager.getRawPublicKey(),privateKeyManager.getKeyType(), privateKeyManager.getEncodeType());
        Bid bid = new Bid(privateKeyManager.getRawPublicKey(),chaincode,privateKeyManager.getKeyType(), privateKeyManager.getEncodeType());
        KeyPairEntity keyPairEntity = new KeyPairEntity();
        keyPairEntity.setPrivateKey(privateKeyManager.getBifPrivateKey());
        keyPairEntity.setPublicKey(publicKeyManager.getBifPublicKey());
        keyPairEntity.setBid(bid.getBidStr());
        return keyPairEntity;
    }

    /**
     * Get the default bid address and the public&private key pair in BIF format，
     * @return
     */
    public KeyPairEntity getBidAndKeyPair() throws SDKException {
        PrivateKeyManager privateKeyManager = new PrivateKeyManager(KeyType.ED25519, EncodeType.Base58);
        PublicKeyManager publicKeyManager = new PublicKeyManager(privateKeyManager.getRawPublicKey(),privateKeyManager.getKeyType(), privateKeyManager.getEncodeType());
        Bid bid = new Bid(privateKeyManager.getRawPublicKey(),null,privateKeyManager.getKeyType(), privateKeyManager.getEncodeType());

        KeyPairEntity keyPairEntity = new KeyPairEntity();
        keyPairEntity.setPrivateKey(privateKeyManager.getBifPrivateKey());
        keyPairEntity.setPublicKey(publicKeyManager.getBifPublicKey());
        keyPairEntity.setBid(bid.getBidStr());
        return keyPairEntity;
    }

    /**
     * Return the BID address and BIF formate public private key pair，
     * @return
     */
    public String getBifPubkeyByPrivateKey(String privateKey) throws SDKException {
        PrivateKeyManager privateKeyManager = new PrivateKeyManager(privateKey);
        PublicKeyManager publicKeyManager = new PublicKeyManager(privateKeyManager.getRawPublicKey(),privateKeyManager.getKeyType(), privateKeyManager.getEncodeType());
        return publicKeyManager.getBifPublicKey();
    }
}
