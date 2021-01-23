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
package cn.ac.caict.bid.constant;


import cn.ac.caict.bid.exceptions.ExceptionCommon;
import cn.ac.caict.bid.exceptions.SDKException;
import cn.ac.caict.bid.util.ConvertUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 具体编码方式和编码类型的映射关系
 */
public class EncodeTypeChar {
    private final Byte base58='f';
    private final Byte base64='s';
    private final Byte bech32='t';

    private final Map<EncodeType,Byte> EncodeTypeByteMap;
    private final Map<Byte,EncodeType> EncodeKeyTypeMap;
    public EncodeTypeChar(){
        EncodeTypeByteMap = new HashMap<EncodeType,Byte>(3);
        EncodeTypeByteMap.put(EncodeType.Base58,base58);
        EncodeTypeByteMap.put(EncodeType.Base64,base64);

        EncodeKeyTypeMap = new HashMap<Byte,EncodeType>(3);
        EncodeKeyTypeMap.put(base58,EncodeType.Base58);
        EncodeKeyTypeMap.put(base64,EncodeType.Base64);
    }
    public EncodeType getEncodeTypeByByte(Byte encodeByte) {
        return EncodeKeyTypeMap.get(encodeByte);
    }
    public Byte getByteByEncodeType(EncodeType encodeType) {
        return EncodeTypeByteMap.get(encodeType);
    }
    public Byte getBase58(){return base58;}
    public Byte getBase64(){return base64;}
    public Byte getBech32(){return bech32;}

    public String getBase58Str() throws SDKException {
        try {
            return  ConvertUtil.byteToAscii(base58);
        }catch (Exception e){
            throw new SDKException(ExceptionCommon.EXCEPTIONCODE_UNSUPPORT_ENCODETYPE);
        }
    }

    public String getBase64Str() throws SDKException {
        try {
            return  ConvertUtil.byteToAscii(base64);
        }catch (Exception e){
            throw new SDKException(ExceptionCommon.EXCEPTIONCODE_UNSUPPORT_ENCODETYPE);
        }
    }

    public String getBech32Str() throws SDKException {
        try {
            return  ConvertUtil.byteToAscii(bech32);
        }catch (Exception e){
            throw new SDKException(ExceptionCommon.EXCEPTIONCODE_UNSUPPORT_ENCODETYPE);
        }
    }
}
