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
package cn.bid;

import cn.bid.exceptions.SDKException;
import cn.bid.core.KeyPair;
import cn.bid.core.address.Bid;
import cn.bid.core.privkey.PrivateKeyManager;
import cn.bid.core.pubkey.PublicKeyManager;
import cn.bid.model.KeyPairEntity;
import cn.bid.model.Result;


public class SDK {
    private final static String sdkVersion="V1.0.0";
    /**
     * 版本SDK返回
     * @return
     */
    public String getSdkVersion() {
        return sdkVersion;
    }

    /**
     * 版本BID返回
     * @return
     */
    public String getBidVersion() {
        KeyPair keyPair= new KeyPair();
        return keyPair.getBidVersion();
    }

    /**
     * 验签
     * @param publicKey 星火格式的公钥
     * @param srcMsg 待验签的信息
     * @param signature 签名
     * @return
     */
    public boolean verifyMessage(String publicKey, byte[] srcMsg, byte[] signature)throws SDKException {
        PublicKeyManager publicKeyMgr =  new PublicKeyManager(publicKey);
        return publicKeyMgr.verifySign(srcMsg, signature);
    }

    /**
     * 签名
     * @param prviteKey 星火格式的私钥
     * @param message 待签名的信息
     * @return
     */
    public byte[] signMessage(String prviteKey, byte[] message)throws SDKException {
        PrivateKeyManager privateKeyMgr = new PrivateKeyManager(prviteKey);
        return  privateKeyMgr.signMessage(message);
    }

    /**
     * 验证bid格式是否合法
     * @param BID
     * @return
     */
    public Result isValidBid(String BID) throws SDKException {
        return Bid.isValidBid(BID);
    }

    /**
     * 根据用户星火格式的公钥生成BID地址
     * @param publKey
     * @param chaincode
     * @return
     */
    public String getBidByBifPubkey(String publKey, String chaincode) throws SDKException {
        KeyPair keyPair= new KeyPair();
        return keyPair.getBidStr(publKey, chaincode);
    }

    /**
     * 根据用户星火格式的公钥生成BID地址
     * @param publKey
     * @return
     */
    public String getBidByBifPubkey(String publKey) throws SDKException {
        KeyPair keyPair= new KeyPair();
        return keyPair.getBidStr(publKey);
    }

    /**
     * 获取BID地址和星火格式的公私钥对，
     * @param chaincode
     * @return
     */
    public KeyPairEntity getBidAndKeyPair(String chaincode) throws SDKException {
        KeyPair keyPair= new KeyPair();
        return keyPair.getBIDAndKeyPair(chaincode);
    }
    /**
     * 获取BID地址和星火格式的公私钥对，
     * @return
     */
    public KeyPairEntity getBidAndKeyPair() throws SDKException {
        KeyPair keyPair= new KeyPair();
        return keyPair.getBIDAndKeyPair();
    }

    /**
     * 获取BID地址和星火格式的公私钥对，
     * @return
     */
    public String getBifPubkeyByPrivateKey(String privateKey) throws SDKException {
        KeyPair keyPair= new KeyPair();
        return keyPair.getBifPubkeyByPrivateKey(privateKey);
    }
}
