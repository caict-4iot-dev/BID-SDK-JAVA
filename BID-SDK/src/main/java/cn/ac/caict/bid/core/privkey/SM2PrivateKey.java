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
import cn.ac.caict.bid.crypto.sm2.SM2;
import cn.ac.caict.bid.crypto.sm2.SM2KeyPair;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

/**
 * @Author: Tristan
 * @Date: 18/3/2021 16:56
 * @Version 1.0
 */
public class SM2PrivateKey extends BasePrivateKey {

    SM2PrivateKey(EncodeType eEncodeType) throws SDKException {
        try {
            SM2KeyPair keyPair = SM2.getSM2KeyPair();
            byte[] rawSkey = SM2.getRawSkey(keyPair);
            byte[] rawPubKey = SM2.getRawPubKey(keyPair);
            rawPrivateKey = rawSkey;
            rawPublicKey = rawPubKey;
            encodeType = eEncodeType;
            keyType = KeyType.SM2;
        } catch (Exception e) {
            throw new SDKException(ExceptionCommon.EXCEPTIONCODE_GENERATOR_KEY_ERROR);
        }
    }

    SM2PrivateKey(byte[] bRawPrivateKey, EncodeType eEncodeType) throws SDKException {
        try {
            byte[] rawPubKey = SM2.FromSkeyBin(bRawPrivateKey);
            rawPrivateKey = bRawPrivateKey;
            rawPublicKey = rawPubKey;
            encodeType = eEncodeType;
            keyType = KeyType.SM2;
        } catch (Exception e) {
            throw new SDKException(ExceptionCommon.EXCEPTIONCODE_GENERATOR_KEY_ERROR);
        }
    }


    SM2PrivateKey(String bifPrivateKey) throws SDKException {
        getPrivateKey(bifPrivateKey);
        try {
            byte[] rawPubKey = SM2.FromSkeyBin(rawPrivateKey);
            rawPublicKey = rawPubKey;
        } catch (Exception e) {
            throw new SDKException(ExceptionCommon.EXCEPTIONCODE_GENERATOR_KEY_ERROR);
        }
    }


    @Override
    public byte[] signBlob(byte[] message) throws SDKException {
        if (rawPrivateKey == null) {
            throw new SDKException(ExceptionCommon.EXCEPTIONCODE_INVALID_KEY);
        }
        byte[] signMsg = null;
        try {
            BigInteger sKey = new BigInteger(rawPrivateKey);
            sKey = SM2.bigIntegerPreHandle(sKey);
            SM2KeyPair keyPair = new SM2KeyPair(SM2.G.multiply(sKey).normalize(), sKey);
            signMsg = SM2.signWithBytes(new String(message, "ISO_8859_1"), "1234567812345678", keyPair);
        } catch (UnsupportedEncodingException e) {
            throw new SDKException(ExceptionCommon.EXCEPTIONCODE_UNSUPPORT_ENCODETYPE);
        }
        return signMsg;
    }
}
