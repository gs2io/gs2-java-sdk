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
public class CreateAccountRequest extends Gs2BasicRequest<CreateAccountRequest> {
    private String email;
    private String fullName;
    private String companyName;
    private String password;
    private String lang;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public CreateAccountRequest withEmail(String email) {
		this.email = email;
		return this;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public CreateAccountRequest withFullName(String fullName) {
		this.fullName = fullName;
		return this;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public CreateAccountRequest withCompanyName(String companyName) {
		this.companyName = companyName;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public CreateAccountRequest withPassword(String password) {
		this.password = password;
		return this;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public CreateAccountRequest withLang(String lang) {
		this.lang = lang;
		return this;
	}

    public static CreateAccountRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new CreateAccountRequest()
            .withEmail(data.get("email") == null || data.get("email").isNull() ? null : data.get("email").asText())
            .withFullName(data.get("fullName") == null || data.get("fullName").isNull() ? null : data.get("fullName").asText())
            .withCompanyName(data.get("companyName") == null || data.get("companyName").isNull() ? null : data.get("companyName").asText())
            .withPassword(data.get("password") == null || data.get("password").isNull() ? null : data.get("password").asText())
            .withLang(data.get("lang") == null || data.get("lang").isNull() ? null : data.get("lang").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("email", getEmail());
                put("fullName", getFullName());
                put("companyName", getCompanyName());
                put("password", getPassword());
                put("lang", getLang());
            }}
        );
    }
}