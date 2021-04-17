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
import cn.ac.caict.bid.crypto.sm2.SM2;

import java.io.UnsupportedEncodingException;

/**
 * @Author: Tristan
 * @Date: 22/3/2021 09:22
 * @Version 1.0
 */
public class SM2PublicKey extends BasePublicKey {

    SM2PublicKey(byte[] bRawPublicKey, EncodeType encodeType) {
        rawPublicKey = bRawPublicKey;
        this.encodeType = encodeType;
        this.keyType = KeyType.SM2;
    }

    @Override
    public boolean verifySign(byte[] srcMsg, byte[] signature) throws SDKException {
        if (rawPublicKey == null) {
            throw new SDKException(ExceptionCommon.EXCEPTIONCODE_INVALID_KEY);
        }
        if (signature == null) {
            throw new SDKException(ExceptionCommon.EXCEPTIONCODE_INVALID_SIGN);
        }
        boolean verfyResult = false;
        try {
           verfyResult = SM2.verify(srcMsg,signature,rawPublicKey);
        } catch (UnsupportedEncodingException e) {
            throw new SDKException(ExceptionCommon.EXCEPTIONCODE_UNSUPPORT_ENCODETYPE);
        }
        return verfyResult;
    }
}
