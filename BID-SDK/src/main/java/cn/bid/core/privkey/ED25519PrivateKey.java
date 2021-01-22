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

import cn.bid.exceptions.ExceptionCommon;
import cn.bid.exceptions.SDKException;
import cn.bid.constant.EncodeType;
import cn.bid.constant.KeyType;
import net.i2p.crypto.eddsa.EdDSAEngine;
import net.i2p.crypto.eddsa.EdDSAPrivateKey;
import net.i2p.crypto.eddsa.EdDSAPublicKey;
import net.i2p.crypto.eddsa.KeyPairGenerator;
import net.i2p.crypto.eddsa.spec.EdDSANamedCurveTable;
import net.i2p.crypto.eddsa.spec.EdDSAParameterSpec;
import net.i2p.crypto.eddsa.spec.EdDSAPrivateKeySpec;
import net.i2p.crypto.eddsa.spec.EdDSAPublicKeySpec;

import java.security.*;

public class ED25519PrivateKey extends BasePrivateKey {
    ED25519PrivateKey(EncodeType eEncodeType)throws SDKException {
        try {
            KeyPairGenerator keyPairGenerator = new KeyPairGenerator();
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            EdDSAPrivateKey priKey = (EdDSAPrivateKey) keyPair.getPrivate();
            EdDSAPublicKey pubKey = (EdDSAPublicKey) keyPair.getPublic();
            rawPrivateKey = priKey.getSeed();
            rawPublicKey = pubKey.getAbyte();
            encodeType = eEncodeType;
            keyType = KeyType.ED25519;
        }catch (Exception e){
            throw new SDKException(ExceptionCommon.EXCEPTIONCODE_GENERATOR_KEY_ERROR);
        }
    }
    ED25519PrivateKey(byte[] bRawPrivateKey,EncodeType eEncodeType) throws SDKException {
        try {
            EdDSAParameterSpec spec = EdDSANamedCurveTable.getByName("ed25519-sha-512");
            EdDSAPrivateKeySpec privKey = new EdDSAPrivateKeySpec(bRawPrivateKey, spec);
            EdDSAPublicKeySpec spec2 = new EdDSAPublicKeySpec(privKey.getA(), spec);
            EdDSAPublicKey pubKey = new EdDSAPublicKey(spec2);
            rawPrivateKey = bRawPrivateKey;
            rawPublicKey = pubKey.getAbyte();
            encodeType = eEncodeType;
            keyType = KeyType.ED25519;
        }catch (Exception e){
            throw new SDKException(ExceptionCommon.EXCEPTIONCODE_GENERATOR_KEY_ERROR);
        }
    }

    ED25519PrivateKey(String bifPrivateKey) throws SDKException {
        getPrivateKey(bifPrivateKey);
        try {
            EdDSAParameterSpec spec = EdDSANamedCurveTable.getByName("ed25519-sha-512");
            EdDSAPrivateKeySpec privKey = new EdDSAPrivateKeySpec(rawPrivateKey, spec);
            EdDSAPublicKeySpec spec2 = new EdDSAPublicKeySpec(privKey.getA(), spec);
            EdDSAPublicKey pDsaPublicKey = new EdDSAPublicKey(spec2);
            rawPublicKey = pDsaPublicKey.getAbyte();
        }catch (Exception e){
            throw new SDKException(ExceptionCommon.EXCEPTIONCODE_GENERATOR_KEY_ERROR);
        }
    }
    @Override
    public  byte[] signMessage(byte[] message) throws SDKException {
        if (rawPrivateKey == null){
            throw new SDKException(ExceptionCommon.EXCEPTIONCODE_INVALID_KEY);
        }
        byte[] signMsg = null;
        try {
            Signature sgr = new EdDSAEngine(MessageDigest.getInstance("SHA-512"));
            EdDSAParameterSpec spec = EdDSANamedCurveTable.getByName("ed25519-sha-512");
            EdDSAPrivateKeySpec sKeySpec = new EdDSAPrivateKeySpec(rawPrivateKey, spec);
            EdDSAPrivateKey sKey = new EdDSAPrivateKey(sKeySpec);
            sgr.initSign(sKey);
            sgr.update(message);
            signMsg = sgr.sign();
        }  catch (NoSuchAlgorithmException e) {
            throw new SDKException(ExceptionCommon.EXCEPTIONCODE_UNSUPPORT_KEYTYPE);
        } catch (InvalidKeyException e) {
            throw new SDKException(ExceptionCommon.EXCEPTIONCODE_INVALID_KEY);
        } catch (SignatureException e) {
            throw new SDKException(ExceptionCommon.EXCEPTIONCODE_SIGN_FAILED);
        }
        return signMsg;
    };
}
