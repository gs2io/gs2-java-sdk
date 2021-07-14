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
public class UpdateAccountRequest extends Gs2BasicRequest<UpdateAccountRequest> {
    private String email;
    private String fullName;
    private String companyName;
    private String password;
    private String accountToken;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UpdateAccountRequest withEmail(String email) {
		this.email = email;
		return this;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public UpdateAccountRequest withFullName(String fullName) {
		this.fullName = fullName;
		return this;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public UpdateAccountRequest withCompanyName(String companyName) {
		this.companyName = companyName;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UpdateAccountRequest withPassword(String password) {
		this.password = password;
		return this;
	}

	public String getAccountToken() {
		return accountToken;
	}

	public void setAccountToken(String accountToken) {
		this.accountToken = accountToken;
	}

	public UpdateAccountRequest withAccountToken(String accountToken) {
		this.accountToken = accountToken;
		return this;
	}

    public static UpdateAccountRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new UpdateAccountRequest()
            .withEmail(data.get("email") == null || data.get("email").isNull() ? null : data.get("email").asText())
            .withFullName(data.get("fullName") == null || data.get("fullName").isNull() ? null : data.get("fullName").asText())
            .withCompanyName(data.get("companyName") == null || data.get("companyName").isNull() ? null : data.get("companyName").asText())
            .withPassword(data.get("password") == null || data.get("password").isNull() ? null : data.get("password").asText())
            .withAccountToken(data.get("accountToken") == null || data.get("accountToken").isNull() ? null : data.get("accountToken").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("email", getEmail());
                put("fullName", getFullName());
                put("companyName", getCompanyName());
                put("password", getPassword());
                put("accountToken", getAccountToken());
            }}
        );
    }
}