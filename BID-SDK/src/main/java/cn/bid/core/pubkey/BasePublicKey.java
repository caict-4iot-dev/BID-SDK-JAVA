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
 *
 *
 */

package cn.bid.core.pubkey;

import cn.bid.constant.BIFConstant;
import cn.bid.exceptions.ExceptionCommon;
import cn.bid.exceptions.SDKException;
import cn.bid.constant.EncodeType;
import cn.bid.constant.EncodeTypeChar;
import cn.bid.constant.KeyType;
import cn.bid.constant.KeyTypeChar;
import cn.bid.util.HexFormat;

import cn.bid.model.KeyMember;
public abstract class BasePublicKey {
    protected byte[] rawPublicKey;
    protected KeyType keyType;
    protected EncodeType encodeType;

    public byte[] getRawPublicKey(){
        return rawPublicKey;
    }
    public KeyType getKeyType(){
        return keyType;
    }
    public EncodeType getEncodeType(){
        return encodeType;
    }

    public String getBifPublicKey() throws SDKException{
        if (rawPublicKey == null || keyType == null || encodeType == null){
            throw new SDKException(ExceptionCommon.EXCEPTIONCODE_INVALID_KEY);
        }
        KeyTypeChar keyTypeChar = new KeyTypeChar();
        EncodeTypeChar encodeTypeChar = new EncodeTypeChar();

        //前缀的长度 + 类型的长度1 + 编码类型的长度1 + 二进制私钥的长度
        int shiftLen =1 + 1 + 1 + rawPublicKey.length;
        byte[] sketTmp = new byte[shiftLen];
        sketTmp[0] = BIFConstant.pubKeyPrefix;
        sketTmp[1] =  keyTypeChar.getByteByKeyType(keyType);
        sketTmp[2] =  encodeTypeChar.getByteByEncodeType(encodeType);

        System.arraycopy(rawPublicKey, 0, sketTmp, 3, rawPublicKey.length);
        return  HexFormat.byteToHex(sketTmp);
    }

    public static KeyMember decodePublicKey(String bPkey) throws SDKException {
        if (null == bPkey) {
            throw new SDKException(ExceptionCommon.EXCEPTIONCODE_INVALID_KEY);
        }

        if (!HexFormat.isHexString(bPkey)) {
            throw new SDKException(ExceptionCommon.EXCEPTIONCODE_INVALID_KEY);
        }
        KeyTypeChar keyTypeChar = new KeyTypeChar();
        EncodeTypeChar encodeTypeChar = new EncodeTypeChar();

        byte[] buffPKey = HexFormat.hexToByte(bPkey);

        //prefixlen + keytypelen + encodetypelen
        int shiftLen = 3;
        if (buffPKey.length < shiftLen) {
            throw new SDKException(ExceptionCommon.EXCEPTIONCODE_INVALID_KEY);
        }

        if (buffPKey[0] != BIFConstant.pubKeyPrefix) {
            throw new SDKException(ExceptionCommon.EXCEPTIONCODE_INVALID_KEY);
        }

        if (keyTypeChar.getKeyTypeByByte(buffPKey[1]) == null){
            throw new SDKException(ExceptionCommon.EXCEPTIONCODE_INVALID_KEY);
        }
        KeyMember keyMember = new KeyMember();
        keyMember.setKeyType(keyTypeChar.getKeyTypeByByte(buffPKey[1]));

        if (encodeTypeChar.getEncodeTypeByByte(buffPKey[2]) == null){
            throw new SDKException(ExceptionCommon.EXCEPTIONCODE_INVALID_KEY);
        }
        keyMember.setEncodeType(encodeTypeChar.getEncodeTypeByByte(buffPKey[2]));

        byte[] rawPublicKey = new byte[buffPKey.length - shiftLen];
        System.arraycopy(buffPKey, shiftLen, rawPublicKey, 0, buffPKey.length - shiftLen);
        keyMember.setRawKey(rawPublicKey);
        return keyMember;
    }
    protected  void getPublicKey(String bPkey) throws SDKException{
        KeyMember keyMember = decodePublicKey(bPkey);
        keyType = keyMember.getKeyType();
        encodeType = keyMember.getEncodeType();
        rawPublicKey =  keyMember.getRawKey();
    }
    public  abstract  boolean verifySign(byte[]srcMsg, byte[] signature) throws SDKException;
}
