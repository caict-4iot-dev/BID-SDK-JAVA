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
package cn.bid.constant;

import cn.bid.exceptions.ExceptionCommon;
import cn.bid.exceptions.SDKException;
import cn.bid.util.Utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 具体加密类型和公私钥对加密算法的映射关系
 */
public  class KeyTypeChar {
    private final Byte ed25519='e';
    private final Byte sm2='z';
    private final Byte secp256k1='s';
    private final Map<KeyType,Byte> KeyTypeByteMap;
    private final Map<Byte,KeyType> ByteKeyTypeMap;
    public KeyTypeChar(){
        KeyTypeByteMap = new HashMap<KeyType,Byte>(3);
        KeyTypeByteMap.put(KeyType.ED25519,ed25519);
        KeyTypeByteMap.put(KeyType.SM2,sm2);

        ByteKeyTypeMap = new HashMap<Byte,KeyType>(3);
        ByteKeyTypeMap.put(ed25519,KeyType.ED25519);
        ByteKeyTypeMap.put(sm2,KeyType.SM2);
    }
    public KeyType getKeyTypeByByte(Byte keyByte) {
        return ByteKeyTypeMap.get(keyByte);
    }
    public Byte getByteByKeyType(KeyType keyType) {
        return KeyTypeByteMap.get(keyType);
    }
    public Byte getEd25519() {
        return ed25519;
    }
    public Byte getSm2() {
        return sm2;
    }

    public Byte getSecp256k1() {
        return secp256k1;
    }

    public String getEd25519Str() throws SDKException {
        try {
            return Utils.byteToAscii(ed25519);
        }catch (Exception e){
            throw new SDKException(ExceptionCommon.EXCEPTIONCODE_UNSUPPORT_KEYTYPE);
        }
    }
    public String getSm2Str() throws SDKException {
        try {
            return Utils.byteToAscii(sm2);
        }catch (Exception e){
            throw new SDKException(ExceptionCommon.EXCEPTIONCODE_UNSUPPORT_KEYTYPE);
        }
    }
    public String getSecp256k1Str() throws SDKException {
        try {
            return Utils.byteToAscii(secp256k1);
        }catch (Exception e){
            throw new SDKException(ExceptionCommon.EXCEPTIONCODE_UNSUPPORT_KEYTYPE);
        }
    }
}
