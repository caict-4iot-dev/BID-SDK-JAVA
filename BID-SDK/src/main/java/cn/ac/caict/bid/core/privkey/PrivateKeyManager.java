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
package cn.ac.caict.bid.core.privkey;

import cn.ac.caict.bid.constant.EncodeType;
import cn.ac.caict.bid.constant.KeyType;
import cn.ac.caict.bid.exceptions.ExceptionCommon;
import cn.ac.caict.bid.exceptions.SDKException;

public class PrivateKeyManager  extends BasePrivateKey {
    BasePrivateKey privateKey;
    //目前只支持sm2 和ed25519,后续扩展

    public PrivateKeyManager(KeyType keyType, EncodeType encodeType)throws SDKException {
        try {
            if (keyType == KeyType.ED25519) {
                privateKey = new ED25519PrivateKey(encodeType);
            } else {
                throw new SDKException(ExceptionCommon.EXCEPTIONCODE_UNSUPPORT_KEYTYPE);
            }
        }catch (Exception e){
            throw new SDKException(ExceptionCommon.EXCEPTIONCODE_GENERATOR_KEY_ERROR);
        }
    }
    public PrivateKeyManager(String sPrivateKey)throws SDKException {
        getPrivateKey(sPrivateKey);
        try {
            if (keyType == KeyType.ED25519) {
                privateKey = new ED25519PrivateKey(sPrivateKey);
            } else {
                throw new SDKException(ExceptionCommon.EXCEPTIONCODE_UNSUPPORT_KEYTYPE);
            }
        }catch (Exception e){
            throw new SDKException(ExceptionCommon.EXCEPTIONCODE_GENERATOR_KEY_ERROR);
        }
    }

    @Override
    public KeyType getKeyType(){
        return privateKey.getKeyType();
    }
    @Override
    public EncodeType getEncodeType(){
        return privateKey.getEncodeType();
    }
    @Override
    public String getBifPrivateKey() throws SDKException {
        return privateKey.getBifPrivateKey();
    }
    @Override
    public byte[] getRawPublicKey(){
        return privateKey.getRawPublicKey();
    }
    @Override
    public byte[] signBlob(byte[] message) throws SDKException {
        return privateKey.signBlob(message);
    }
}
