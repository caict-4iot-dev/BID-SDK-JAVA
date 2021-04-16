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
package cn.ac.caict.bid.exceptions;

public class ExceptionCommon {
    // key generator fail
    public static final int EXCEPTIONCODE_GENERATOR_KEY_ERROR = -1;
    // unsupport encodetype
    public static final int EXCEPTIONCODE_UNSUPPORT_ENCODETYPE = -2;
    // unsupport keytype
    public static final int  EXCEPTIONCODE_UNSUPPORT_KEYTYPE = -3;
    // invalid chaincode
    public static final int EXCEPTIONCODE_INVALID_CHAINCODE = -5;
    // invalid key
    public static final int EXCEPTIONCODE_INVALID_KEY = -6;
    // sign failed
    public static final int EXCEPTIONCODE_SIGN_FAILED = -7;
    //verify failed
    public static final int EXCEPTIONCODE_VERIFY_FAILED = -8;
    // system error
    public static final int EXCEPTIONCODE_SYSTEM_ERROR = -9;
    // invalid sign
    public static final int EXCEPTIONCODE_INVALID_SIGN = -10;
    //hash failed
    public static final int EXCEPTIONCODE_HASH_FAILED = -11;
}
