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
package cn.bid.core.privkey;

import cn.bid.constant.*;
import cn.bid.exceptions.ExceptionCommon;
import cn.bid.exceptions.SDKException;
import cn.bid.model.KeyMember;
import cn.bid.util.Base58;

public abstract class BasePrivateKey {
     protected byte[] rawPrivateKey;
     protected byte[] rawPublicKey;
     protected KeyType keyType;
     protected EncodeType encodeType;
     public KeyType getKeyType(){
          return keyType;
     }
     public EncodeType getEncodeType(){
          return encodeType;
     }
     public byte[] getRawPublicKey(){
          return rawPublicKey;
     }
     public static KeyMember decodePrivateKey(String sPrivateKey)throws SDKException {
          try {
               KeyTypeChar keyTypeChar = new KeyTypeChar();
               EncodeTypeChar encodeTypeChar = new EncodeTypeChar();
               if (null == sPrivateKey) {
                    throw new SDKException(ExceptionCommon.EXCEPTIONCODE_INVALID_KEY);
               }
               //前缀的长度 + 类型的长度1 + 编码类型的长度1
               int shiftLen = BIFConstant.priKeyPrefix.length + 1 + 1;

               byte[] skeyTmp = Base58.decode(sPrivateKey);
               if (skeyTmp.length <= shiftLen) {
                    throw new SDKException(ExceptionCommon.EXCEPTIONCODE_INVALID_KEY);
               }
               if (keyTypeChar.getKeyTypeByByte(skeyTmp[BIFConstant.priKeyPrefix.length]) == null){
                    throw new SDKException(ExceptionCommon.EXCEPTIONCODE_INVALID_KEY);
               }
               KeyMember keyMember = new KeyMember();
               keyMember.setKeyType(keyTypeChar.getKeyTypeByByte(skeyTmp[BIFConstant.priKeyPrefix.length]));

               if (encodeTypeChar.getEncodeTypeByByte(skeyTmp[BIFConstant.priKeyPrefix.length + 1]) == null){
                    throw new SDKException(ExceptionCommon.EXCEPTIONCODE_INVALID_KEY);
               }
               keyMember.setEncodeType(encodeTypeChar.getEncodeTypeByByte(skeyTmp[BIFConstant.priKeyPrefix.length + 1]));

               keyMember.setEncodeType(encodeTypeChar.getEncodeTypeByByte(skeyTmp[BIFConstant.priKeyPrefix.length + 1]));

               byte[] rawPrivateKey = new byte[skeyTmp.length - shiftLen];
               System.arraycopy(skeyTmp, shiftLen, rawPrivateKey, 0, skeyTmp.length - shiftLen);
               keyMember.setRawKey(rawPrivateKey);
               return keyMember;
          } catch (Exception e) {
               throw new SDKException(ExceptionCommon.EXCEPTIONCODE_INVALID_KEY);
          }
     }
     protected  void getPrivateKey(String sPrivateKey) throws SDKException {
          KeyMember keyMember = decodePrivateKey(sPrivateKey);
          keyType = keyMember.getKeyType();
          encodeType = keyMember.getEncodeType();
          rawPrivateKey =  keyMember.getRawKey();
     }
     public String getBifPrivateKey() throws SDKException {
          if (rawPrivateKey == null || keyType == null || encodeType == null){
               throw new SDKException(ExceptionCommon.EXCEPTIONCODE_INVALID_KEY);
          }
          KeyTypeChar keyTypeChar = new KeyTypeChar();
          EncodeTypeChar encodeTypeChar = new EncodeTypeChar();

          //前缀的长度3 + 类型的长度1 + 编码类型的长度1
          int shiftLen = BIFConstant.priKeyPrefix.length + 1 + 1;
          byte[] sketTmp = new byte[rawPrivateKey.length + shiftLen];
          //prefix
          System.arraycopy(BIFConstant.priKeyPrefix, 0, sketTmp, 0, BIFConstant.priKeyPrefix.length);

          //prefix + keytype
          sketTmp[BIFConstant.priKeyPrefix.length] = keyTypeChar.getByteByKeyType(keyType);

          //prefix + keytype + encodetype
          sketTmp[BIFConstant.priKeyPrefix.length + 1] = encodeTypeChar.getByteByEncodeType(encodeType);

          //prefix + keytype + encodetype + privatekey
          System.arraycopy(rawPrivateKey, 0, sketTmp, shiftLen, rawPrivateKey.length);
          if (encodeType == EncodeType.Base58){
               return Base58.encode(sketTmp);
          }
          else{
               throw new SDKException(ExceptionCommon.EXCEPTIONCODE_UNSUPPORT_ENCODETYPE);
          }
     }
     public abstract byte[] signMessage(byte[] message) throws SDKException;
}
