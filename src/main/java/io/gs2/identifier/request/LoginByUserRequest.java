/*
 * Copyright 2016 Game Server Services, Inc. or its affiliates. All Rights
 * Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License").
 * You may not use this file except in compliance with the License.
 * A copy of the License is located at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * or in the "license" file accompanying this file. This file is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package io.gs2.identifier.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class LoginByUserRequest extends Gs2BasicRequest<LoginByUserRequest> {
    private String userName;
    private String password;
    private String otp;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public LoginByUserRequest withUserName(String userName) {
		this.userName = userName;
		return this;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LoginByUserRequest withPassword(String password) {
		this.password = password;
		return this;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public LoginByUserRequest withOtp(String otp) {
		this.otp = otp;
		return this;
	}

    public static LoginByUserRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new LoginByUserRequest()
            .withUserName(data.get("userName") == null || data.get("userName").isNull() ? null : data.get("userName").asText())
            .withPassword(data.get("password") == null || data.get("password").isNull() ? null : data.get("password").asText())
            .withOtp(data.get("otp") == null || data.get("otp").isNull() ? null : data.get("otp").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("userName", getUserName());
                put("password", getPassword());
                put("otp", getOtp());
            }}
        );
    }
}