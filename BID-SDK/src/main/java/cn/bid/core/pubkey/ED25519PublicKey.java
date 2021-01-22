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
package cn.bid.core.pubkey;

import cn.bid.exceptions.ExceptionCommon;
import cn.bid.exceptions.SDKException;
import cn.bid.constant.EncodeType;
import cn.bid.constant.KeyType;
import net.i2p.crypto.eddsa.EdDSAEngine;
import net.i2p.crypto.eddsa.EdDSAPublicKey;
import net.i2p.crypto.eddsa.spec.EdDSANamedCurveTable;
import net.i2p.crypto.eddsa.spec.EdDSAParameterSpec;
import net.i2p.crypto.eddsa.spec.EdDSAPublicKeySpec;

import java.security.*;

public class ED25519PublicKey  extends BasePublicKey {
    ED25519PublicKey(byte[] bRawPublicKey, EncodeType encodeType) {
       rawPublicKey = bRawPublicKey;
       this.encodeType = encodeType;
       this.keyType = KeyType.ED25519;
    }
    @Override
    public  boolean verifySign(byte[]srcMsg, byte[] signature) throws SDKException {
        if (rawPublicKey == null){
            throw new SDKException(ExceptionCommon.EXCEPTIONCODE_INVALID_KEY);
        }
        if (signature == null){
            throw new SDKException(ExceptionCommon.EXCEPTIONCODE_INVALID_SIGN);
        }
        boolean verfyResult = false;
        try {
            Signature sgr = new EdDSAEngine(MessageDigest.getInstance("SHA-512"));
            EdDSAParameterSpec spec = EdDSANamedCurveTable.getByName("ed25519-sha-512");
            EdDSAPublicKeySpec eddsaPubKey = new EdDSAPublicKeySpec(rawPublicKey, spec);
            EdDSAPublicKey vKey = new EdDSAPublicKey(eddsaPubKey);
            sgr.initVerify(vKey);
            sgr.update(srcMsg);
            verfyResult = sgr.verify(signature);
        }catch (NoSuchAlgorithmException e){
            throw new SDKException(ExceptionCommon.EXCEPTIONCODE_UNSUPPORT_KEYTYPE);
        }catch (InvalidKeyException e) {
            throw new SDKException(ExceptionCommon.EXCEPTIONCODE_INVALID_KEY);
        }catch(SignatureException e){
            verfyResult = false;
        }
        return  verfyResult;
    };
}
