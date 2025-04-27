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

package io.gs2.project.request;

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
public class GetProjectTokenByIdentifierRequest extends Gs2BasicRequest<GetProjectTokenByIdentifierRequest> {
    private String accountName;
    private String projectName;
    private String userName;
    private String password;
    private String otp;
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public GetProjectTokenByIdentifierRequest withAccountName(String accountName) {
		this.accountName = accountName;
		return this;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public GetProjectTokenByIdentifierRequest withProjectName(String projectName) {
		this.projectName = projectName;
		return this;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public GetProjectTokenByIdentifierRequest withUserName(String userName) {
		this.userName = userName;
		return this;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public GetProjectTokenByIdentifierRequest withPassword(String password) {
		this.password = password;
		return this;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public GetProjectTokenByIdentifierRequest withOtp(String otp) {
		this.otp = otp;
		return this;
	}

    public static GetProjectTokenByIdentifierRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new GetProjectTokenByIdentifierRequest()
            .withAccountName(data.get("accountName") == null || data.get("accountName").isNull() ? null : data.get("accountName").asText())
            .withProjectName(data.get("projectName") == null || data.get("projectName").isNull() ? null : data.get("projectName").asText())
            .withUserName(data.get("userName") == null || data.get("userName").isNull() ? null : data.get("userName").asText())
            .withPassword(data.get("password") == null || data.get("password").isNull() ? null : data.get("password").asText())
            .withOtp(data.get("otp") == null || data.get("otp").isNull() ? null : data.get("otp").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("accountName", getAccountName());
                put("projectName", getProjectName());
                put("userName", getUserName());
                put("password", getPassword());
                put("otp", getOtp());
            }}
        );
    }
}