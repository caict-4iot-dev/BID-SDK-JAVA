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
package cn.ac.caict.bid.exception;


public enum SdkError {
    /*
     * INVALID_CHAIN_CODE
     */
    INVALID_CHAIN_CODE(13001, "The chainCode is invalid,must be numbers or letters and The length of value must be 4"),
    /*
     * CONNECTNETWORK_ERROR
     */
    CONNECTNETWORK_ERROR(11007, "Failed to connect to the network"),
    ;

    private final Integer code;
    private final String description;

    SdkError(Integer code, String description) {
        this.code = code;
        this.description = description;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

}
