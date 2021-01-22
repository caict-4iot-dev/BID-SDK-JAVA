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
package cn.bid.exceptions;

/**
 * 异常类
 */
public class SDKException extends  Exception{
    private int excpCode;// 异常码

    public SDKException(int excpCode) {
        super();
        this.excpCode = excpCode;
    }

    public SDKException(int excpCode, String message) {
        super(message);
        this.excpCode = excpCode;
    }

    public SDKException(int excpCode, Throwable throwable) {
        super(throwable);
        this.excpCode = excpCode;
    }

    public SDKException(int excpCode, String message, Throwable throwable) {
        super(message, throwable);
        this.excpCode = excpCode;
    }

    public int getExceptionCode(){
        return excpCode;
    }

    public String toString() {
        String msg = getMessage();
        if (msg == null)
            msg = "";
        return "SDKException (" + getCodeDescription(excpCode) + ") " + msg;
    }

    public static final String getCodeDescription(int excpCode) {
        switch (excpCode) {
            case ExceptionCommon.EXCEPTIONCODE_GENERATOR_KEY_ERROR:
                return "GENERATOR_KEY_ERROR";
            case ExceptionCommon.EXCEPTIONCODE_UNSUPPORT_ENCODETYPE:
                return "UNSUPPORT_ENCODETYPE";
            case ExceptionCommon.EXCEPTIONCODE_UNSUPPORT_KEYTYPE:
                return "UNSUPPORT_KEYTYPE";
            case ExceptionCommon.EXCEPTIONCODE_INVALID_CHAINCODE:
                return "INVALID_CHAINCODE";
            case ExceptionCommon.EXCEPTIONCODE_INVALID_KEY:
                return "INVALID_KEY";
            case ExceptionCommon.EXCEPTIONCODE_SIGN_FAILED:
                return "SIGN_FAILED";
            case ExceptionCommon.EXCEPTIONCODE_VERIFY_FAILED:
                return "VERIFY_FAILED";
            case ExceptionCommon.EXCEPTIONCODE_SYSTEM_ERROR:
                return "SYSTEM_ERROR";
            default:
                return "UNKNOWN_ERROR(" + excpCode + ")";
        }
    }
}
