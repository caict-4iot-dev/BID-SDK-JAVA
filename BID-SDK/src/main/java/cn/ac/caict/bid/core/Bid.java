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
package cn.ac.caict.bid.core;


import cn.ac.caict.bid.SDK;
import cn.ac.caict.bid.common.*;
import cn.ac.caict.bid.model.*;
import cn.ac.caict.bid.model.extension.*;
import cn.ac.caict.bid.model.extension.BIDExtensionOperation;
import cn.ac.caict.bid.model.request.BIDRequest;
import cn.ac.caict.bid.model.response.BIDDocumentResponse;
import cn.ac.caict.bid.model.result.Result;
import cn.ac.caict.bid.model.service.BIDServiceOperation;
import cn.ac.caict.bid.utils.CheckTool;
import cn.bif.api.BIFSDK;
import cn.bif.common.Constant;
import cn.bif.common.JsonUtils;
import cn.bif.common.Tools;
import cn.bif.exception.SDKException;
import cn.bif.exception.SdkError;
import cn.bif.model.request.BIFBatchContractInvokeRequest;
import cn.bif.model.request.BIFContractCallRequest;
import cn.bif.model.request.BIFTransactionGetInfoRequest;
import cn.bif.model.request.operation.BIFContractInvokeOperation;
import cn.bif.model.response.BIFContractCallResponse;
import cn.bif.model.response.BIFContractInvokeResponse;
import cn.bif.model.response.BIFTransactionGetInfoResponse;
import cn.bif.model.response.result.data.BIFGasSendInfo;
import cn.bif.model.response.result.data.BIFOperation;
import cn.bif.model.response.result.data.BIFTransactionHistory;
import cn.bif.model.response.result.data.BIFTransactionInfo;
import cn.bif.module.encryption.key.PrivateKeyManager;
import cn.bif.module.encryption.key.PublicKeyManager;
import cn.bif.module.encryption.model.KeyType;
import cn.bif.utils.hex.HexFormat;
import cn.bif.utils.http.HttpUtils;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

import static cn.ac.caict.bid.utils.CheckTool.paramValidity;


public class Bid {

    public static Result resolverBid(String bid) {
        BIDDocumentResponse response =new BIDDocumentResponse();
        try{
            if(Tools.isEmpty(SDK.getSdk().getUrl())){
                return new Result(false,SdkError.URL_EMPTY_ERROR.getDescription());
            }
            if(!CheckTool.encAddressValid(bid)){
                return new Result(false,"Invalid bid Address Parameter");
            }
            String url= SDK.getSdk().getUrl()+"/"+bid;
            String result = HttpUtils.httpGet(url);
            response= JsonUtils.toJavaObject(result, BIDDocumentResponse.class);
            if(response.getErrorCode().equals(BIDConstant.ERRORCODE)){
                return new Result(false,"The bid document does not exist");
            }else{
                return new Result(true, JsonUtils.toJSONString(response.getData()));
            }
        }catch (Exception e) {
            return new Result(false,String.valueOf(SdkError.CONNECTNETWORK_ERROR.getDescription()));
        }
    }

    public static Result createBID(BIDRequest request){
        if (Tools.isEmpty(SDK.getSdk().getUrl())) {
            throw new SDKException(SdkError.URL_EMPTY_ERROR);
        }
        BIFSDK sdk = BIFSDK.getInstance(SDK.getSdk().getUrl());
        List<BIFContractInvokeOperation> operations = new ArrayList<>();
        List<BID> bidList=request.getBids();
        if(!Tools.isEmpty(bidList) && bidList.size() <= BIDConstant.LIMINT_NUMBER){
            for (int i = 0; i < bidList.size(); i++) {
                BID bid=bidList.get(i);
                Result newResult = paramValidity(bid);
                if(!newResult.getStatus()){
                    return newResult;
                }
                //操作对象
                String  input = "{\"method\":\"creation\",\"params\":"+JsonUtils.toJSONString(bid)+"}";
                BIFContractInvokeOperation operation=new BIFContractInvokeOperation();
                operation.setContractAddress(BIDConstant.DDO_CONTRACT);
                operation.setBIFAmount(request.getBIFAmount());
                operation.setInput(input);
                operations.add(operation);
            }
        }else {
            return new Result(false,"Exceeded the number limit 100!");
        }
        BIFBatchContractInvokeRequest requestBatch = new BIFBatchContractInvokeRequest();
        requestBatch.setSenderAddress(request.getSenderAddress());
        requestBatch.setPrivateKey(request.getPrivateKey());
        requestBatch.setRemarks(request.getRemarks());
        requestBatch.setDomainId(request.getDomainId());
        requestBatch.setOperations(operations);
        // 调用 batchContractInvoke 接口
        BIFContractInvokeResponse response = sdk.getBIFContractService().batchContractInvoke(requestBatch);
        if (response.getErrorCode() == 0) {
            System.out.println(JsonUtils.toJSONString(response.getResult()));
            return new Result(true, JsonUtils.toJSONString(response.getResult()));
        }
        return new Result(false, JsonUtils.toJSONString(response));
    }

    public static Result updateBID(BIDRequest request){
        if (Tools.isEmpty(SDK.getSdk().getUrl())) {
            throw new SDKException(SdkError.URL_EMPTY_ERROR);
        }
        BIFSDK sdk = BIFSDK.getInstance(SDK.getSdk().getUrl());
        List<BIFContractInvokeOperation> operations = new ArrayList<>();
        List<BID> bidList=request.getBids();
        if(!Tools.isEmpty(bidList) && bidList.size() <= BIDConstant.LIMINT_NUMBER){
            for (int i = 0; i < bidList.size(); i++) {
                BID bid=bidList.get(i);
                Result newResult = paramValidity(bid);
                if(!newResult.getStatus()){
                    return newResult;
                }
                //操作对象
                String  input = "{\"method\":\"update\",\"params\":"+JsonUtils.toJSONString(bid)+"}";
                BIFContractInvokeOperation operation=new BIFContractInvokeOperation();
                operation.setContractAddress(BIDConstant.DDO_CONTRACT);
                operation.setBIFAmount(request.getBIFAmount());
                operation.setInput(input);
                operations.add(operation);
            }
        }else {
            return new Result(false,"Exceeded the number limit 100!");
        }
        BIFBatchContractInvokeRequest requestBatch = new BIFBatchContractInvokeRequest();
        requestBatch.setSenderAddress(request.getSenderAddress());
        requestBatch.setPrivateKey(request.getPrivateKey());
        requestBatch.setRemarks(request.getRemarks());
        requestBatch.setDomainId(request.getDomainId());
        requestBatch.setOperations(operations);
        // 调用 batchContractInvoke 接口
        BIFContractInvokeResponse response = sdk.getBIFContractService().batchContractInvoke(requestBatch);
        if (response.getErrorCode() == 0) {
            System.out.println(JsonUtils.toJSONString(response.getResult()));
            return new Result(true, JsonUtils.toJSONString(response.getResult()));
        }
        return new Result(false, JsonUtils.toJSONString(response));
    }

    /**
     * 创建bid模板
     * @return
     * @throws Exception
     */
    public static Result getBIDTemplate() {
        PrivateKeyManager priKey = new PrivateKeyManager(KeyType.ED25519);
        String encPublicKey =  priKey.getEncPublicKey();
        String encAddress =  priKey.getEncAddress();
        String encPrivateKey= priKey.getEncPrivateKey();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date now = new Date();
        // Init request
        List<BID> bidList=new ArrayList<>();
        BID bid = new BID();
        BIDDocumentOperation bidDocumentOperation=new BIDDocumentOperation();
        String context[]=new String[1];
        context[0]="https://www.w3.org/ns/did/v1";
        //1.@context
        bidDocumentOperation.setContext(context);
        //2.version,文档的版本号
        bidDocumentOperation.setVersion("1.0.0");
        //3.id,文档的BID
        bidDocumentOperation.setId(encAddress);

        //4.publicKey
        BIDpublicKeyOperation publicKey[]=new  BIDpublicKeyOperation[1];
        BIDpublicKeyOperation keyOperation=new BIDpublicKeyOperation();
        keyOperation.setId(encAddress+"#key-1");
        keyOperation.setPublicKeyHex(encPublicKey);
        keyOperation.setType(KeyType.ED25519);
        keyOperation.setController(encAddress);
        publicKey[0]=keyOperation;
        bidDocumentOperation.setPublicKey(publicKey);

        //5. authentication
        String authentication[]=new String[1];
        authentication[0]=encAddress+"#key-1";
        bidDocumentOperation.setAuthentication(authentication);
        //6.alsoKnownAs
        BIDAlsoKnownAsOperation alsoKnownAsOperation=new BIDAlsoKnownAsOperation();
            //关联标识类型
        alsoKnownAsOperation.setType(RelevanceType.RELEVANCE_BID.getCode());
        alsoKnownAsOperation.setId(encAddress);
        BIDAlsoKnownAsOperation knownAsOperation[]=new BIDAlsoKnownAsOperation[1];
        knownAsOperation[0]=alsoKnownAsOperation;
        bidDocumentOperation.setAlsoKnownAs(knownAsOperation);

        //7. extension,BID扩展字段
        BIDExtensionOperation bidExtensionOperation=new BIDExtensionOperation();
        //7.1 recovery
        String recovery[]=new String[1];
        recovery[0]=encAddress+"#key-2";
        bidExtensionOperation.setRecovery(recovery);
        //7.2 ttl
        bidExtensionOperation.setTtl(86400L);
        //7.3delegateSign
        //签名
        byte[] signatureValue = PrivateKeyManager.sign(HexFormat.hexStringToBytes(encPublicKey), encPrivateKey);
        BIDDelegateSignOperation bidDelegateSignOperation=new BIDDelegateSignOperation();
        bidDelegateSignOperation.setSigner(encAddress+"#key-1");
        bidDelegateSignOperation.setSignatureValue(HexFormat.byteToHex(signatureValue));

        bidExtensionOperation.setDelegateSign(bidDelegateSignOperation);
        //7.4 type 属性类型
        bidExtensionOperation.setType(AttributeType.ATTRIBUTE_ENTERPRISE.getCode());
        //7.5 attributes
        BIDAttributesOperation attributes[] =new BIDAttributesOperation[1];
        BIDAttributesOperation attributesOperation=new BIDAttributesOperation();
        attributesOperation.setKey("name");
        attributesOperation.setDesc("名称");
        attributesOperation.setFormat("text");
        attributesOperation.setValue("BID文档");
        attributesOperation.setEncrypt(EncryptType.ENCRYPT_TYPE_YES.getCode());
        attributes[0]=attributesOperation;

        bidExtensionOperation.setAttributes(attributes);

        //7.6 acsns
        String acsns[]=new String[1];
        acsns[0]="acsn";
        bidExtensionOperation.setAcsns(acsns);
        //7.7 verifiableCredentials
        BIDVerifiableCredentialsOperation bidVerifiableCredentialsOperation=new BIDVerifiableCredentialsOperation();
        bidVerifiableCredentialsOperation.setId(encAddress);
        //凭证类型
        bidVerifiableCredentialsOperation.setType(CredentialsType.Credentials_VERIFIABLE.getCode());
        BIDVerifiableCredentialsOperation verifiableCredentialsOperations[] =new BIDVerifiableCredentialsOperation[1];
        verifiableCredentialsOperations[0]=bidVerifiableCredentialsOperation;
        bidExtensionOperation.setVerifiableCredentials(verifiableCredentialsOperations);
        bidDocumentOperation.setExtension(bidExtensionOperation);

        //8.service
        BIDServiceOperation service[]=new BIDServiceOperation[1];
        BIDServiceOperation serviceOperation=new BIDServiceOperation();
        serviceOperation.setId(encAddress+"#resolver");
        serviceOperation.setServiceEndpoint("https://bidresolver.com");
        //服务类型
        serviceOperation.setType(DidType.DID_DECRYPT.getCode());
            //解析服务协议类型
        serviceOperation.setProtocol(ResolverType.RESOLVER_HTTP.getCode());
            //服务地址类型,0为域名形式，1为IP地址形式
        serviceOperation.setServerType(AddressType.ADDRESS_DOMAIN.getCode());
        service[0]=serviceOperation;
        bidDocumentOperation.setService(service);
        //9.created,创建时间
        bidDocumentOperation.setCreated(sdf.format(now));
        //10.updated,上次的更新时间
        bidDocumentOperation.setUpdated(sdf.format(now));

        //签名
        String document=JsonUtils.toJSONString(bidDocumentOperation);
        byte[] documentByte = document.getBytes();
        String documentBlob = HexFormat.byteToHex(documentByte);
        byte[] sign_static = PrivateKeyManager.sign(HexFormat.hexStringToBytes(documentBlob), encPrivateKey);
        // 11.proof
        BIDProofOperstion bidProofOperstion=new BIDProofOperstion();
        bidProofOperstion.setCreator(encAddress+"#key-1");
        bidProofOperstion.setSignatureValue(HexFormat.byteToHex(sign_static));
        bidDocumentOperation.setProof(bidProofOperstion);
        bid.setDocument(bidDocumentOperation);

        bidList.add(bid);

        BIDRequest response=new BIDRequest();
        response.setBids(bidList);
        response.setSenderAddress(encAddress);
        response.setPrivateKey(encPrivateKey);
        return new Result(true, JsonUtils.toJSONString(response));
    }

    /**
     * 创建bid
     * @return
     * @throws Exception
     */
    public static Result createBIDByTemplate(String request) {
        if (Tools.isEmpty(request)) {
            return new Result(false, SdkError.REQUEST_NULL_ERROR.getDescription());
        }
        BIDRequest  templateRequest = JsonUtils.toJavaObject(request, BIDRequest.class);
        if(templateRequest==null){
            return new Result(false,"Invalid bid data");
        }
        if (Tools.isEmpty(SDK.getSdk().getUrl())) {
            return new Result(false, SdkError.URL_EMPTY_ERROR.getDescription());
        }
        BIFSDK sdk = BIFSDK.getInstance(SDK.getSdk().getUrl());
        List<BIFContractInvokeOperation> operations = new ArrayList<>();
        List<BID> bidList=templateRequest.getBids();
        if(!Tools.isEmpty(bidList) && bidList.size() <= BIDConstant.LIMINT_NUMBER){
            for (int i = 0; i < bidList.size(); i++) {
                BID bid=bidList.get(i);
                Result newResult = paramValidity(bid);
                if(!newResult.getStatus()){
                    return newResult;
                }
                //操作对象
                String  input = "{\"method\":\"creation\",\"params\":"+JsonUtils.toJSONString(bid)+"}";
                BIFContractInvokeOperation operation=new BIFContractInvokeOperation();
                operation.setContractAddress(BIDConstant.DDO_CONTRACT);
                operation.setBIFAmount(templateRequest.getBIFAmount());
                operation.setInput(input);
                operations.add(operation);
            }
        }else {
            return new Result(false,"Exceeded the number limit 100!");
        }
        BIFBatchContractInvokeRequest requestBatch = new BIFBatchContractInvokeRequest();
        requestBatch.setSenderAddress(templateRequest.getSenderAddress());
        requestBatch.setPrivateKey(templateRequest.getPrivateKey());
        requestBatch.setRemarks(templateRequest.getRemarks());
        requestBatch.setDomainId(templateRequest.getDomainId());
        requestBatch.setOperations(operations);
        // 调用 batchContractInvoke 接口
        BIFContractInvokeResponse response = sdk.getBIFContractService().batchContractInvoke(requestBatch);
        if (response.getErrorCode() == 0) {
            System.out.println(JsonUtils.toJSONString(response.getResult()));
            return new Result(true, JsonUtils.toJSONString(response.getResult()));
        }
        return new Result(false, JsonUtils.toJSONString(response));
    }

    /**
     * 创建bid
     * @return
     * @throws Exception
     */
    public static Result updateBIDByTemplate(String request) {
        if (Tools.isEmpty(request)) {
            return new Result(false,SdkError.REQUEST_NULL_ERROR.getDescription());
        }
        BIDRequest  templateRequest = JsonUtils.toJavaObject(request, BIDRequest.class);
        if(templateRequest==null){
            return new Result(false,"Invalid bid data");
        }
        if (Tools.isEmpty(SDK.getSdk().getUrl())) {
            return new Result(false,SdkError.URL_EMPTY_ERROR.getDescription());
        }
        BIFSDK sdk = BIFSDK.getInstance(SDK.getSdk().getUrl());
        List<BIFContractInvokeOperation> operations = new ArrayList<>();
        List<BID> bidList=templateRequest.getBids();
        if(!Tools.isEmpty(bidList) && bidList.size() <=BIDConstant.LIMINT_NUMBER){
            for (int i = 0; i < bidList.size(); i++) {
                BID bid =bidList.get(i);
                Result newResult = paramValidity(bid);
                if(!newResult.getStatus()){
                    return newResult;
                }
                //操作对象
                String  input = "{\"method\":\"update\",\"params\":"+JsonUtils.toJSONString(bid)+"}";
                BIFContractInvokeOperation operation=new BIFContractInvokeOperation();
                operation.setContractAddress(BIDConstant.DDO_CONTRACT);
                operation.setBIFAmount(templateRequest.getBIFAmount());
                operation.setInput(input);
                operations.add(operation);
            }
        }else {
            return new Result(false,"Exceeded the number limit 100!");
        }
        BIFBatchContractInvokeRequest requestBatch = new BIFBatchContractInvokeRequest();
        requestBatch.setSenderAddress(templateRequest.getSenderAddress());
        requestBatch.setPrivateKey(templateRequest.getPrivateKey());
        requestBatch.setRemarks(templateRequest.getRemarks());
        requestBatch.setDomainId(templateRequest.getDomainId());
        requestBatch.setOperations(operations);
        // 调用 batchContractInvoke 接口
        BIFContractInvokeResponse response = sdk.getBIFContractService().batchContractInvoke(requestBatch);
        if (response.getErrorCode() == 0) {
            System.out.println(JsonUtils.toJSONString(response.getResult()));
            return new Result(true, JsonUtils.toJSONString(response.getResult()));
        }
        return new Result(false, JsonUtils.toJSONString(response));
    }

    /**
     * 对文档内容的签名进行验签
     */
    public static Result isValidProof(String request){
        if (Tools.isEmpty(request)) {
            return new Result(false, SdkError.REQUEST_NULL_ERROR.getDescription());
        }
        BID  templateRequest = JsonUtils.toJavaObject(request, BID.class);
        if(templateRequest==null){
            return new Result(false,"Invalid bid data");
        }
        BIDDocumentOperation  templateDocument = templateRequest.getDocument();
        if(Tools.isEmpty(templateDocument)){
            return new Result(false,"Document parameter cannot be null");
        }
        BIDProofOperstion templateProof = templateDocument.getProof();
        if(Tools.isEmpty(templateProof)){
            return new Result(false,"Proof parameter cannot be null");
        }
        BIDpublicKeyOperation[] publicKey= templateDocument.getPublicKey();
        if(Tools.isEmpty(publicKey)){
            return new Result(false,"PublicKey parameter cannot be null");
        }
        String creator= templateProof.getCreator();
        templateDocument.setProof(null);
        String document=JsonUtils.toJSONString(templateDocument);

        byte[] documentByte=new byte[0];
        try{
            documentByte= document.getBytes("utf-8");
        }catch (UnsupportedEncodingException e) {
            return new Result(false,e.getMessage());
        }
        String documentBlob = HexFormat.byteToHex(documentByte);
        String signatureValue = templateProof.getSignatureValue();

        for(BIDpublicKeyOperation operation:publicKey){
            String publicKeyId=operation.getId();
            if(!Tools.isEmpty(creator)&&!Tools.isEmpty(publicKeyId)){
                if(publicKeyId.equals(creator)){
                    String publicKeyHex=operation.getPublicKeyHex();
                    try{
                        Boolean  verifyResult = PublicKeyManager.verify(HexFormat.hexStringToBytes(documentBlob), HexFormat.hexToByte(signatureValue), publicKeyHex);
                        if(verifyResult){
                            return  new Result(true, "Sign Success!");
                        }
                    }catch (IllegalArgumentException e) {
                        return  new Result(false, "Sign Failure!");
                    }
                }
            }else {
                return new Result(false," If the parameter is empty, the document cannot be verified");
            }
        }
        return new Result(false, "Sign Failure!");
    }

    /**
     * BID地址合约查询
     */
    public static Result bidQueryByContract(String bid){
        if(!CheckTool.encAddressValid(bid)){
            return new Result(false,"Invalid Bid Address Parameter");
        }
        if(Tools.isEmpty(SDK.getSdk().getUrl())){
            return new Result(false,SdkError.URL_EMPTY_ERROR.getDescription());
        }
        BIFSDK sdk = BIFSDK.getInstance(SDK.getSdk().getUrl());
        String contractAddress=BIDConstant.DDO_CONTRACT;
        if(CheckTool.isAcBid(bid)){
            if(bid.split(":").length==4){
                bid= bid.substring(0,bid.lastIndexOf(":"));
            }
           contractAddress=BIDConstant.AC_CONTRACT;
        }
        String input ="{\"method\":\"queryBid\",\"params\":{\"id\":\""+bid+"\"}}";
        BIFContractCallRequest requestCall = new BIFContractCallRequest();
        requestCall.setContractAddress(contractAddress);
        requestCall.setInput(input);
        // 调用contractQuery接口
        BIFContractCallResponse response = sdk.getBIFContractService().contractQuery(requestCall);
        if (response.getErrorCode() == 0) {
            Map<String,Object> rest=JsonUtils.toMap(response.getResult().getQueryRets().get(0));

            if(!rest.containsKey("result")){
                return new Result(false, JsonUtils.toJSONString(rest.get("error")));
            }
            Map<String,Object> result=JsonUtils.toMap(rest.get("result"));
            Map<String,Object> value=JsonUtils.toMap(result.get("value"));
            Map<String, Object> resultItem = new HashMap<>();
            resultItem.put("didDocument", value);
            return new Result(true, JsonUtils.toJSONString(resultItem));
        }
        return new Result(false, JsonUtils.toJSONString(response));
    }

    /**
     * 根据hash查询交易信息
     */
    public static Result queryTransactionInfoByHash(String hash){
        if(Tools.isEmpty(SDK.getSdk().getUrl())){
            return new Result(false,SdkError.URL_EMPTY_ERROR.getDescription());
        }
        if (Tools.isEmpty(hash) || hash.length() != Constant.HASH_HEX_LENGTH) {
            return new Result(false,"Invalid Hash Address ");
        }
        BIFSDK sdk = BIFSDK.getInstance(SDK.getSdk().getUrl());
        BIFTransactionGetInfoRequest request = new BIFTransactionGetInfoRequest();
        request.setHash(hash);
        // 调用getTransactionInfo接口
        BIFTransactionGetInfoResponse response = sdk.getBIFTransactionService().getTransactionInfo(request);
        if (response.getErrorCode() == 0) {
            return new Result(true, JsonUtils.toJSONString(response.getResult()));
        }
        return new Result(false, JsonUtils.toJSONString(response));
    }

    /**
     * 通过hash
     * @param hash
     * @return
     */
    public static Result getBidByHash(String hash){
        if(Tools.isEmpty(SDK.getSdk().getUrl())){
            return new Result(false,SdkError.URL_EMPTY_ERROR.getDescription());
        }
        if (Tools.isEmpty(hash) || hash.length() != Constant.HASH_HEX_LENGTH) {
            return new Result(false,"Invalid Hash Address ");
        }
        BIFSDK sdk = BIFSDK.getInstance(SDK.getSdk().getUrl());
        BIFTransactionGetInfoRequest request = new BIFTransactionGetInfoRequest();
        request.setHash(hash);
        // 调用getTransactionInfo接口
        BIFTransactionGetInfoResponse response = sdk.getBIFTransactionService().getTransactionInfo(request);
        List<String> result=new ArrayList<>();
        if (response.getErrorCode() == 0) {
            if(response.getResult().getTotalCount().intValue() > 0){
                BIFTransactionHistory transactionHistory[]=response.getResult().getTransactions();
                for (BIFTransactionHistory item:transactionHistory) {
                    BIFTransactionInfo  transactionInfo=item.getTransaction();
                    if(transactionInfo.getOperations().length > 0){
                        BIFOperation operation[]=transactionInfo.getOperations();
                        for (BIFOperation oper:operation) {
                            BIFGasSendInfo sendInfo= oper.getSendGas();
                            String destAddress= sendInfo.getDestAddress();
                            if(destAddress.equals(BIDConstant.DDO_CONTRACT)){
                                String input=sendInfo.getInput();
                                Map<String, Object> inputMap=JsonUtils.toMap(input);
                                String params=JsonUtils.toJSONString(inputMap.get("params"));
                                BID bid=JsonUtils.toJavaObject(params,BID.class);
                                result.add(bid.getDocument().getId());
                            }
                        }

                    }
                }

            }
            if(result.size() > 0){
                return new Result(true, JsonUtils.toJSONString(result));
            }else{
                return new Result(false, "Invalid BID Transaction Hash");
            }

        }
        return new Result(false, JsonUtils.toJSONString(response));
    }
}
