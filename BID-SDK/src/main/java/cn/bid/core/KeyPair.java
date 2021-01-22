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
package cn.bid.core;

import cn.bid.constant.EncodeType;
import cn.bid.constant.KeyType;
import cn.bid.exceptions.SDKException;
import cn.bid.core.address.Bid;
import cn.bid.core.privkey.PrivateKeyManager;
import cn.bid.core.pubkey.PublicKeyManager;
import cn.bid.model.KeyPairEntity;

public class KeyPair {
    public String getBidVersion(){
        return Bid.getBidVersionNumber();
    }

    public KeyPairEntity getBIDAndKeyPair(String chainCode) throws SDKException {
        PrivateKeyManager privateKeyManager = new PrivateKeyManager(KeyType.ED25519,EncodeType.Base58);
        PublicKeyManager publicKeyManager = new PublicKeyManager(privateKeyManager.getRawPublicKey(),privateKeyManager.getKeyType(), privateKeyManager.getEncodeType());
        Bid bid = new Bid(privateKeyManager.getRawPublicKey(),chainCode,privateKeyManager.getKeyType(), privateKeyManager.getEncodeType());

        KeyPairEntity keyPairEntity = new KeyPairEntity();
        keyPairEntity.setPrivateKey(privateKeyManager.getBifPrivateKey());
        keyPairEntity.setPublicKey(publicKeyManager.getBifPublicKey());
        keyPairEntity.setBid(bid.getBidStr());
        return keyPairEntity;
    }
    public KeyPairEntity getBIDAndKeyPair() throws SDKException {
        PrivateKeyManager privateKeyManager = new PrivateKeyManager(KeyType.ED25519,EncodeType.Base58);
        PublicKeyManager publicKeyManager = new PublicKeyManager(privateKeyManager.getRawPublicKey(),privateKeyManager.getKeyType(), privateKeyManager.getEncodeType());
        Bid bid = new Bid(privateKeyManager.getRawPublicKey(),null,privateKeyManager.getKeyType(), privateKeyManager.getEncodeType());

        KeyPairEntity keyPairEntity = new KeyPairEntity();
        keyPairEntity.setPrivateKey(privateKeyManager.getBifPrivateKey());
        keyPairEntity.setPublicKey(publicKeyManager.getBifPublicKey());
        keyPairEntity.setBid(bid.getBidStr());
        return keyPairEntity;
    }

    public String getBifPubkeyByPrivateKey(String privateKey) throws SDKException {
        PrivateKeyManager privateKeyManager = new PrivateKeyManager(privateKey);
        PublicKeyManager publicKeyManager = new PublicKeyManager(privateKeyManager.getRawPublicKey(),privateKeyManager.getKeyType(), privateKeyManager.getEncodeType());
        return publicKeyManager.getBifPublicKey();
    }
    /**
     * 根据星火链公钥生成bid
     * @param publicKey
     * @return
     * @throws Exception
     */
    public String getBidStr(String publicKey) throws SDKException {
        Bid bid = new Bid(publicKey,null);
        return bid.getBidStr();
    }

    /**
     * 根据星火链公钥生成bid，带chaincode
     * @param publicKey
     * @param chainCode
     * @return
     * @throws Exception
     */
    public String getBidStr(String publicKey, String chainCode) throws SDKException {
        Bid bid = new Bid(publicKey, chainCode);
        return bid.getBidStr();
    }

}
