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

package io.gs2.stamina.request;

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
public class SetRecoverValueByStatusRequest extends Gs2BasicRequest<SetRecoverValueByStatusRequest> {
    private String namespaceName;
    private String staminaName;
    private String accessToken;
    private String keyId;
    private String signedStatusBody;
    private String signedStatusSignature;

	public String getNamespaceName() {
		return namespaceName;
	}

	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}

	public SetRecoverValueByStatusRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}

	public String getStaminaName() {
		return staminaName;
	}

	public void setStaminaName(String staminaName) {
		this.staminaName = staminaName;
	}

	public SetRecoverValueByStatusRequest withStaminaName(String staminaName) {
		this.staminaName = staminaName;
		return this;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public SetRecoverValueByStatusRequest withAccessToken(String accessToken) {
		this.accessToken = accessToken;
		return this;
	}

	public String getKeyId() {
		return keyId;
	}

	public void setKeyId(String keyId) {
		this.keyId = keyId;
	}

	public SetRecoverValueByStatusRequest withKeyId(String keyId) {
		this.keyId = keyId;
		return this;
	}

	public String getSignedStatusBody() {
		return signedStatusBody;
	}

	public void setSignedStatusBody(String signedStatusBody) {
		this.signedStatusBody = signedStatusBody;
	}

	public SetRecoverValueByStatusRequest withSignedStatusBody(String signedStatusBody) {
		this.signedStatusBody = signedStatusBody;
		return this;
	}

	public String getSignedStatusSignature() {
		return signedStatusSignature;
	}

	public void setSignedStatusSignature(String signedStatusSignature) {
		this.signedStatusSignature = signedStatusSignature;
	}

	public SetRecoverValueByStatusRequest withSignedStatusSignature(String signedStatusSignature) {
		this.signedStatusSignature = signedStatusSignature;
		return this;
	}

    public static SetRecoverValueByStatusRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new SetRecoverValueByStatusRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withStaminaName(data.get("staminaName") == null || data.get("staminaName").isNull() ? null : data.get("staminaName").asText())
            .withAccessToken(data.get("accessToken") == null || data.get("accessToken").isNull() ? null : data.get("accessToken").asText())
            .withKeyId(data.get("keyId") == null || data.get("keyId").isNull() ? null : data.get("keyId").asText())
            .withSignedStatusBody(data.get("signedStatusBody") == null || data.get("signedStatusBody").isNull() ? null : data.get("signedStatusBody").asText())
            .withSignedStatusSignature(data.get("signedStatusSignature") == null || data.get("signedStatusSignature").isNull() ? null : data.get("signedStatusSignature").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("staminaName", getStaminaName());
                put("accessToken", getAccessToken());
                put("keyId", getKeyId());
                put("signedStatusBody", getSignedStatusBody());
                put("signedStatusSignature", getSignedStatusSignature());
            }}
        );
    }
}