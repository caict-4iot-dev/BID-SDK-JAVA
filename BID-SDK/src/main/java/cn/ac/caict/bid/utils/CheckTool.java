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
package cn.ac.caict.bid.utils;


import cn.ac.caict.bid.common.*;
import cn.ac.caict.bid.model.BID;
import cn.ac.caict.bid.model.BIDAlsoKnownAsOperation;
import cn.ac.caict.bid.model.BIDDocumentOperation;
import cn.ac.caict.bid.model.BIDpublicKeyOperation;
import cn.ac.caict.bid.model.extension.BIDAttributesOperation;
import cn.ac.caict.bid.model.extension.BIDVerifiableCredentialsOperation;
import cn.ac.caict.bid.model.result.Result;
import cn.ac.caict.bid.model.service.BIDServiceOperation;
import cn.bif.common.Tools;
import cn.bif.module.encryption.key.PublicKeyManager;
import cn.bif.module.encryption.model.KeyType;
import cn.bif.utils.base.Base58;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

public class CheckTool {
    /**
     * 参数校验
     */
    public static Result paramValidity(BID bid){
        if(Tools.isEmpty(bid)){
            return new Result(false,"Bid parameter cannot be null");
        }
        BIDDocumentOperation paramDocument = bid.getDocument();
        List list = new ArrayList<>();
        //document校验
        if(!Tools.isEmpty(paramDocument)){
            if(Tools.isEmpty(paramDocument.getVersion())){
                return new Result(false,"Version parameter cannot be null");
            }
            if(Tools.isEmpty(paramDocument.getId())){
                return new Result(false,"Document Id parameter cannot be null");
            }else{
                if(!CheckTool.encAddressValid(paramDocument.getId())){
                    return new Result(false,"Invalid Document ID Address");
                }
            }
            if(!Tools.isEmpty(paramDocument.getPublicKey())){
                BIDpublicKeyOperation[] publicKey= bid.getDocument().getPublicKey();
                for(int i = 0; i < publicKey.length; i++){
                    //publicKey中地址校验
                    String publicKeyId= publicKey[i].getId();
                    if(publicKeyId.indexOf("#")!=-1){
                        String newPublicKeyId = publicKeyId.substring(0, publicKeyId.indexOf("#"));
                        if(!CheckTool.encAddressValid(newPublicKeyId)){
                            return new Result(false,"Invalid PublicKey Address Parameter");
                        }
                        //publicKey中地址id与controller是否相等
                        if(!newPublicKeyId.equals(publicKey[i].getController())){
                            return new Result(false,"Controller is not equal to the address ID in publicKey");
                        }
                    }else{
                        return new Result(false,"Invalid PublicKey Address Parameter");
                    }
                    //type取值校验
                    KeyType type=publicKey[i].getType();
                    if(!Tools.isEmpty(type)){
                        if(!(KeyType.ED25519).equals(type)&&!(KeyType.SM2).equals(type)){
                            return new Result(false,"Invalid PublicKey Parameter Enums type");
                        }
                    }
                    list.add(publicKey[i].getId());
                }
            }
            if(Tools.isEmpty(paramDocument.getAuthentication())){
                return new Result(false,"Authentication parameter cannot be null");
            }else {
                //authentication地址校验
                if(!Tools.isEmpty(paramDocument.getAuthentication())){
                    String[] authentication= paramDocument.getAuthentication();
                    for(int i = 0; i <authentication.length; i++){
                        if( authentication[i].indexOf("#")!=-1){
                            String newAuthenticationId = authentication[i].substring(0, authentication[i].indexOf("#"));
                            if(!CheckTool.encAddressValid(newAuthenticationId)){
                                return new Result(false,"Invalid Authentication parameter ID Address");
                            }
                        }else {
                            return new Result(false,"Invalid Authentication parameter ID Address");
                        }
                    }
                }
            }
            if(!Tools.isEmpty(paramDocument.getAlsoKnownAs())){
                //校验ID地址
                BIDAlsoKnownAsOperation alsoKnownAsOperation[]=paramDocument.getAlsoKnownAs();
                for(BIDAlsoKnownAsOperation operation:alsoKnownAsOperation){
                    String alsoKnownAs_Id =operation.getId();
                    if(!Tools.isEmpty(alsoKnownAs_Id)){
                        if(!CheckTool.encAddressValid(alsoKnownAs_Id)){
                            return new Result(false,"Invalid AlsoKnownAs Parameter Address");
                        }
                    }
                    //type枚举值
                    Integer alsoKnownAs_type=operation.getType();
                    if (!Tools.isEmpty(alsoKnownAs_type)) {
                        List<Integer> alsoKnownAsList= RelevanceType.getCodeEnumsByRelevanceType();
                        if(!alsoKnownAsList.contains(alsoKnownAs_type)){
                            return new Result(false,"Invalid AlsoKnownAs Parameter Enums type");
                        }
                    }
                }
            }
            if(!Tools.isEmpty(paramDocument.getExtension())){
                if(!Tools.isEmpty(paramDocument.getExtension().getRecovery())){
                    //recovery中id地址校验
                    String[] recovery =paramDocument.getExtension().getRecovery();
                    for(int i = 0; i < recovery.length; i++){
                        if(recovery[i].indexOf("#")!=-1){
                            String newRecoveryIdId = recovery[i].substring(0, recovery[i].indexOf("#"));
                            if(!CheckTool.encAddressValid(newRecoveryIdId)){
                                return new Result(false,"Invalid Recovery Address");
                            }
                        }else {
                            return new Result(false,"Invalid Recovery Address");
                        }
                    }
                }
                if(Tools.isEmpty(paramDocument.getExtension().getTtl())){
                    return new Result(false," Time-To-Live parameter cannot be null");
                }
                if(Tools.isEmpty(paramDocument.getExtension().getType())){
                    return new Result(false," Type parameter cannot be null");
                }else{
                    //枚举值
                    Integer type =paramDocument.getExtension().getType();
                    List<Integer> AttributeList= AttributeType.getCodeEnumsByAttributeType();
                    if(!AttributeList.contains(type)){
                        return new Result(false,"Invalid Extension in Type parameter enums type");
                    }
                }
                if(!Tools.isEmpty(paramDocument.getExtension().getDelegateSign()) && !Tools.isEmpty(paramDocument.getExtension().getDelegateSign().getSigner())){
                    //delegateSign中id地址校验
                    String signer=paramDocument.getExtension().getDelegateSign().getSigner();
                    if(signer.indexOf("#")!=-1){
                        String newSigner = signer.substring(0, signer.indexOf("#"));
                        if(!CheckTool.encAddressValid(newSigner)){
                            return new Result(false,"Invalid Signer Address");
                        }
                    }else {
                        return new Result(false,"Invalid Signer Address");
                    }
                }
                if(Tools.isEmpty(paramDocument.getExtension().getAttributes())){
                    return new Result(false,"Attributes parameter cannot be null");
                }else {
                    //encrypt枚举校验
                    if(!Tools.isEmpty(paramDocument.getExtension().getAttributes())){
                        BIDAttributesOperation[] attributes = paramDocument.getExtension().getAttributes();
                        for (BIDAttributesOperation operation:attributes) {
                            Integer encrypt=operation.getEncrypt();
                            if(!Tools.isEmpty(encrypt)){
                                List<Integer> encryptList = EncryptType.getCodeEnumsByEncrypt();
                                if(!encryptList.contains(encrypt)){
                                    return new Result(false,"Invalid Encrypt Enums type");
                                }
                            }
                        }
                    }
                }
                if(!Tools.isEmpty(paramDocument.getExtension().getAcsns())){
                    //Acsns长度校验
                    String[] value= paramDocument.getExtension().getAcsns();
                    for (String s:value) {
                        if(!CheckTool.isAc(s)){
                            return new Result(false,"Acsn must be numbers or letters and length must be equal to 4");
                        }
                    }
                }
                if(!Tools.isEmpty(paramDocument.getExtension().getVerifiableCredentials())){
                    BIDVerifiableCredentialsOperation[] credentials =paramDocument.getExtension().getVerifiableCredentials();
                    for(BIDVerifiableCredentialsOperation operation:credentials ){
                        if(!Tools.isEmpty(operation.getId())){
                            if(!CheckTool.encAddressValid(operation.getId())){
                                return new Result(false,"Invalid VerifiableCredentials Address");
                            }
                        }
                        if (!Tools.isEmpty(operation.getType())) {
                            //枚举值
                            List<Integer> credentialsList= CredentialsType.getCodeEnumsByCredentialsType();
                            if(!credentialsList.contains(operation.getType())){
                                return new Result(false,"Invalid VerifiableCredentials Enums type");
                            }
                        }
                    }
                }
            }
            if(!Tools.isEmpty(paramDocument.getService())){
                BIDServiceOperation[] service=paramDocument.getService();
                for (BIDServiceOperation operation : service) {
                    //Id地址校验
                    String serviceId=operation.getId();
                    if(!Tools.isEmpty(serviceId)){
                        if(serviceId.indexOf("#")!=-1){
                            String newServiceId = serviceId.substring(0, serviceId.indexOf("#"));
                            if(!CheckTool.encAddressValid(newServiceId)){
                                return new Result(false,"Invalid Service Address");
                            }
                        }else {
                            return new Result(false,"Invalid Service Address");
                        }
                    }
                    //当serverType=1时port不能为空及对serviceEndpoint规则校验
                    Integer serverType=operation.getServerType();
                    String port=operation.getPort();
                    if ((AddressType.ADDRESS_DOMAIN.getCode()).equals(serverType)) {
                        if(Tools.isEmpty(operation.getServiceEndpoint())){
                            return new Result(false,"Domain Address parameter cannot be null");
                        }
                    }else if ((AddressType.ADDRESS_IP.getCode()).equals(serverType)) {
                        if(Tools.isEmpty(port)){
                            return new Result(false,"Service Port parameter cannot be null");
                        }
                        //ip地址正则校验
                        String ipValid = "^((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)\\.){3}(25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)$";
                        if(!operation.getServiceEndpoint().matches(ipValid)){
                            return new Result(false,"Invalid ServiceEndpoint Parameter IP Address Format");
                        }
                    }
                    //serverType枚举值
                    if(!Tools.isEmpty(serverType)){
                        List<Integer> serverTypeList = AddressType.getCodeEnumsByAddressType();
                        if(!serverTypeList.contains(serverType)){
                            return new Result(false,"Invalid ServerType Parameter Enums type");
                        }
                    }
                    //type枚举值
                    String types =operation.getType();
                    if(!Tools.isEmpty(types)){
                        List<String> typeList = DidType.getCodeEnumsByServerType();
                        if(!typeList.contains(types)){
                            return new Result(false,"Invalid Operation in Type Parameter Enums type");
                        }
                    }
                    //protocol枚举值
                    Integer protocol=operation.getProtocol();
                    if(!Tools.isEmpty(protocol)){
                        List<Integer> protocolList = ResolverType.getCodeEnumsByResolverType();
                        if(!protocolList.contains(protocol)){
                            return new Result(false,"Invalid Protocol Enums type");
                        }
                    }
                }
            }
            if(Tools.isEmpty(paramDocument.getCreated())){
                return new Result(false," Created parameter cannot be null");
            }else{
                //created时间校验
                String created=paramDocument.getCreated();
                SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
                try {
                    Date date = dff.parse(created);
                } catch (ParseException e) {
                    return new Result(false," Created parameter time format error");
                }
            }
            if(Tools.isEmpty(paramDocument.getUpdated())){
                return new Result(false," Updated parameter cannot be null");
            }else {
                //updated时间校验
                String updated=paramDocument.getUpdated();
                SimpleDateFormat dff = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
                try {
                    Date date = dff.parse(updated);
                } catch (ParseException e) {
                    return new Result(false," Updated parameter time format error");
                }
            }
            if(!Tools.isEmpty(paramDocument.getProof())){
                String  creator= paramDocument.getProof().getCreator();
                if(!Tools.isEmpty(creator)){
                    //creator地址校验
                    if(creator.indexOf("#")!=-1){
                        String newCreator = creator.substring(0, creator.indexOf("#"));
                        if(!CheckTool.encAddressValid(newCreator)){
                            return new Result(false,"Invalid Creator Address Parameter");
                        }
                    }else {
                        return new Result(false,"Invalid Creator Address Parameter");
                    }
                    //判断proof与publicKey中地址id是否相等
                    if(!list.contains(creator)){
                        return new Result(false,"Proof is not equal to the address ID in publicKey");
                    }
                }else {
                    return new Result(false,"It's impossible to determine whether Proof is equal to the address ID in publicKey");
                }
            }
            if(Tools.isEmpty(paramDocument.getContext())){
                return new Result(false,"Context parameter cannot be null");
            }else {
                //context校验
                String  str= "https://www.w3.org/ns/did/v1";
                String[] context=paramDocument.getContext();
                for (int i = 0; i < context.length; i++) {
                    List<String> contextList  = Arrays.asList(context[i]);
                    if(!contextList.contains(str)){
                        return new Result(false,"Context parameter content error");
                    }
                }
            }
        }else{
            return new Result(false,"Invalid Document Parameter");
        }
        return new Result(true,"Parameter Validation Success");
    }

    public static boolean encAddressValid(String encAddress) {
        boolean valid;
        try {
            if (null == encAddress || !isBid(encAddress)) {
                return  false;
            }
            String[] items = encAddress.split(":",-1);

            if (items.length != 3 && items.length != 4) {
                valid = false;
            }
            if (items.length == 3) {
                encAddress = items[2];
                if(isAc(encAddress)){
                    return  true;
                }
            } else {
                encAddress = items[3];
                String ac = items[2];
                if(!isAc(ac)){
                    return  false;
                }
            }
            String prifx = encAddress.substring(0, 2);

            if (!prifx.equals("ef") && !prifx.equals("zf")) {
                return  false;
            }

            String address = encAddress.substring(2, encAddress.length());
            byte[] base58_address = Base58.decode(address);
            if (base58_address.length != 22) {
                return  false;
            }
            valid = true;
        } catch (Exception e) {
            valid = false;
        }

        return valid;
    }


    public static boolean isAcBid(String encAddress) {
        boolean valid;
        try {
            if (null == encAddress || !isBid(encAddress)) {
                return  false;
            }
            String[] items = encAddress.split(":",-1);

            if (items.length != 3 && items.length != 4) {
                return  false;
            }
            String ac = items[2];
            if(!isAc(ac)){
                return  false;
            }else{
                return  true;
            }
        } catch (Exception e) {
            valid = false;
        }

        return valid;
    }
    public static boolean isAc(String value) {
        String rule = "^[a-z\\d]{4}+$";
        Pattern pattern = Pattern.compile(rule);
        return pattern.matcher(value).matches();
    }
    public static boolean isBid(String value) {
        String rule="^did:bid:[a-zA-Z0-9]*:*[a-zA-Z0-9]*";
        Pattern pattern = Pattern.compile(rule);
        return pattern.matcher(value).matches();
    }

}
