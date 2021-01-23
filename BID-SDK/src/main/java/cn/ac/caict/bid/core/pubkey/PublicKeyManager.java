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
package cn.ac.caict.bid.core.pubkey;


import cn.ac.caict.bid.constant.EncodeType;
import cn.ac.caict.bid.constant.KeyType;
import cn.ac.caict.bid.exceptions.ExceptionCommon;
import cn.ac.caict.bid.exceptions.SDKException;
import cn.ac.caict.bid.model.KeyMember;

public class PublicKeyManager  extends BasePublicKey {
    BasePublicKey publicKey;
    public PublicKeyManager(byte[] bPublicKey, KeyType keyType, EncodeType encodeType)throws SDKException {
        if (keyType == KeyType.ED25519){
            publicKey = new ED25519PublicKey(bPublicKey,encodeType);
        }
        else{
            throw new SDKException(ExceptionCommon.EXCEPTIONCODE_UNSUPPORT_KEYTYPE);
        }
    }
    public PublicKeyManager(String sPublicKey) throws SDKException {
        KeyMember keyMember = BasePublicKey.decodePublicKey(sPublicKey);
        if (keyMember.getKeyType() == KeyType.ED25519){
            publicKey = new ED25519PublicKey(keyMember.getRawKey(), keyMember.getEncodeType());
        }
        else{
            throw new SDKException(ExceptionCommon.EXCEPTIONCODE_UNSUPPORT_KEYTYPE);
        }
    }

    @Override
    public KeyType getKeyType(){
        return publicKey.getKeyType();
    }
    @Override
    public EncodeType getEncodeType(){
        return publicKey.getEncodeType();
    }
    @Override
    public String getBifPublicKey() throws SDKException {
       return publicKey.getBifPublicKey();
    }
    @Override
    public byte[] getRawPublicKey(){
        return publicKey.getRawPublicKey();
    }
    @Override
    public boolean verifySign(byte[] srcMsg, byte[] signature) throws SDKException {
        return publicKey.verifySign(srcMsg, signature);
    }
}
