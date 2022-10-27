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

import cn.ac.caict.bid.core.Bid;
import cn.ac.caict.bid.exception.SdkException;
import cn.ac.caict.bid.model.request.BIDRequest;
import cn.ac.caict.bid.model.result.Result;
import cn.ac.caict.bid.utils.CheckTool;
import cn.bif.exception.SDKException;

import cn.ac.caict.bid.exception.SdkError;
import cn.bif.model.crypto.KeyPairEntity;
import cn.bif.module.encryption.key.PrivateKeyManager;
import cn.bif.module.encryption.key.PublicKeyManager;
import cn.bif.module.encryption.model.KeyType;
import cn.bif.utils.hex.HexFormat;
import org.apache.commons.lang3.StringUtils;

public class SDK {
    private final static String sdkVersion="V1.0.0";
    private static SDK sdk = null;
    private String url;
    public synchronized static SDK getInstance(String url) throws SDKException {
        if (sdk == null) {
            sdk = new SDK();
        }
        sdk.init(url);
        return sdk;
    }
    /**
     * @Method getUrl
     * @Params []
     * @Return java.lang.String
     */
    public String getUrl() {
        return url;
    }
    /**
     * @Method init
     * @Params [url]
     * @Return void
     */
    private void init(String url) throws SDKException {
        if (StringUtils.isEmpty(url)) {
            throw new SDKException(11062,"Url cannot be empty");
        }
        sdk.url = url;
    }
    /**
     * Return the SDK version
     * @return
     */
    public String getSdkVersion() {
        return sdkVersion;
    }
    /**
     * @Method getSdk
     * @Return SDK
     */
    public static SDK getSdk() {
        return sdk;
    }
    /**
     * Verify the BID format is legal
     * @param BID
     * @return
     */
    public Result isValidBid(String BID){
        Boolean result= CheckTool.encAddressValid(BID);
        if(result){
            return  new Result(true, "Legal address");
        }
            return new Result(false, "Invalid address");
    }
    /**
     * Generate BID address according to user's public key in BIF format
     * @param publicKey
     * @return
     */
    public String getBidByBifPublicKey(String publicKey) throws SDKException {
        PublicKeyManager publicKeyManager = new PublicKeyManager(publicKey);
        return publicKeyManager.getEncAddress();
    }

    /**
     * Get the bid address and the public&private key pair in BIF format，
     * @param keyType
     * @param chainCode
     * @return
     */
    public KeyPairEntity getBidAndKeyPair(KeyType keyType,String chainCode){
        if(!CheckTool.isAc(chainCode)){
            throw new SdkException(SdkError.INVALID_CHAIN_CODE);
        }
        PrivateKeyManager priKey = new PrivateKeyManager(keyType,chainCode);
        KeyPairEntity keyPairEntity = new KeyPairEntity(priKey.getEncAddress(),priKey.getEncPublicKey(),priKey.getEncPrivateKey(),priKey.getRawPrivateKey(),priKey.getRawPublicKey());
        return keyPairEntity;
    }

    /**
     * Get the bid address and the public&private key pair in BIF format，
     * @param keyType
     * @return
     */
    public KeyPairEntity getBidAndKeyPair(KeyType keyType){
        PrivateKeyManager priKey = new PrivateKeyManager(keyType);
        KeyPairEntity keyPairEntity = new KeyPairEntity(priKey.getEncAddress(),priKey.getEncPublicKey(),priKey.getEncPrivateKey(),priKey.getRawPrivateKey(),priKey.getRawPublicKey());
        return keyPairEntity;
    }

    /**
     * Get the bid address and the public&private key pair in BIF format，
     * @param chainCode
     * @return
     */
    public KeyPairEntity getBidAndKeyPair(String chainCode){
        if(!CheckTool.isAc(chainCode)){
            throw new SdkException(SdkError.INVALID_CHAIN_CODE);
        }
        PrivateKeyManager priKey = new PrivateKeyManager(KeyType.ED25519,chainCode);
        KeyPairEntity keyPairEntity = new KeyPairEntity(priKey.getEncAddress(),priKey.getEncPublicKey(),priKey.getEncPrivateKey(),priKey.getRawPrivateKey(),priKey.getRawPublicKey());
        return keyPairEntity;
    }

    /**
     * Get the bid address and the public&private key pair in BIF format，
     * @return
     */
    public KeyPairEntity getBidAndKeyPair(){
        PrivateKeyManager priKey = new PrivateKeyManager(KeyType.ED25519);
        KeyPairEntity keyPairEntity = new KeyPairEntity(priKey.getEncAddress(),priKey.getEncPublicKey(),priKey.getEncPrivateKey(),priKey.getRawPrivateKey(),priKey.getRawPublicKey());
        return keyPairEntity;
    }
    /**
     * Return the BID address and BIF formate public private key pair，
     * @return
     */
    public String getBifPubkeyByPrivateKey(String privateKey) throws SDKException {
        return PrivateKeyManager.getEncPublicKey(privateKey);
    }

    /**
     * Signature verification
     * @param publicKey
     * @param srcMsg
     * @param signature
     * @return
     * @throws SDKException
     */
    public boolean verifySig(String publicKey, String srcMsg, byte[] signature)throws SDKException {
        byte[] msgByte = HexFormat.hexStringToBytes(srcMsg);
        return PublicKeyManager.verify(msgByte, signature, publicKey);
    }

    /**
     * signature
     * @param privateKey Private key in BIF format
     * @param message Message to be verified
     * @return
     */
    public byte[] signBlob(String message,String privateKey)throws SDKException {
        byte[] sign_static = PrivateKeyManager.sign(HexFormat.hexStringToBytes(message), privateKey);
        return  sign_static;
    }
    /**
     *  query the BID data
     * @param BID
     * @return
     * @throws Exception
     */
    public Result resolverBid(String BID){
        return Bid.resolverBid(BID);
    }
    /**
     * getBIDTemplate
     * @return
     */
    public Result getBIDTemplate() {
        return Bid.getBIDTemplate();
    }
    /**
     * createBIDByTemplate
     * @return
     */
    public Result createBIDByTemplate(String request) {
        return Bid.createBIDByTemplate(request);
    }
    /**
     * updateBIDByTemplate
     * @return
     */
    public Result updateBIDByTemplate(String request) {
        return Bid.updateBIDByTemplate(request);
    }
    /**
     * createBID
     * @param request
     * @return
     */
    public Result createBID(BIDRequest request) {
        return Bid.createBID(request);
    }

    /**
     * UpdateBID
     * @param request
     * @return
     */
    public Result updateBID(BIDRequest request) {
        return Bid.updateBID(request);
    }

    /**
     *  isValidProof
     * @param request
     * @return
     */
    public Result isValidProof(String request) {
        return Bid.isValidProof(request);
    }

    /**
     * bidContractQuery
     * @param bid
     * @return
     */
    public Result bidQueryByContract(String bid) {
        return Bid.bidQueryByContract(bid);
    }

    /**
     * query TransactionInfo By Hash
     * @param hash
     * @return
     */
    public Result queryTransactionInfoByHash(String hash){
        return Bid.queryTransactionInfoByHash(hash);
    }

    /**
     * 根据hash获取bid标识
     * @param hash
     * @return
     */
    public Result getBidByHash(String hash){
        return Bid.getBidByHash(hash);
    }
}
