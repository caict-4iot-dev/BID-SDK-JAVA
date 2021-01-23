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
package cn.ac.caict.bid.model;

/**
 * @program: bid-sdk-java
 * @description: bid验证结果
 * @author: tjl
 * @create: 2021-01-20 16:45
 **/
public class Result {
    /**
     * 验证结果 失败：false 成功：true
     */
    private boolean status;
    /**
     * 验证结果说明
     */
    private String message;

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Result(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    @Override
    public String toString() {
        return "{status" + ":" + status + "," + "message:" + message + "}";
    }
}
