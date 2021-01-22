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
package cn.bid.util;



import cn.bid.constant.KeyType;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {
    public static byte[] calHash(KeyType type, byte[] data) throws IOException {
        byte[] result = null;
        if (type == KeyType.ED25519 || type == KeyType.SECP256K1) {
            MessageDigest sha256 = null;
            try {
                sha256 = MessageDigest.getInstance("SHA-256");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            sha256.update(data);
            result = sha256.digest();
        }
        else {
            result = SM3.hash(data);
        }
        return result;
    }
}
