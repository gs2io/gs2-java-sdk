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
public class ArchiveDumpUserDataRequest extends Gs2BasicRequest<ArchiveDumpUserDataRequest> {
    private String ownerId;
    private String transactionId;
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	public ArchiveDumpUserDataRequest withOwnerId(String ownerId) {
		this.ownerId = ownerId;
		return this;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public ArchiveDumpUserDataRequest withTransactionId(String transactionId) {
		this.transactionId = transactionId;
		return this;
	}

    public static ArchiveDumpUserDataRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new ArchiveDumpUserDataRequest()
            .withOwnerId(data.get("ownerId") == null || data.get("ownerId").isNull() ? null : data.get("ownerId").asText())
            .withTransactionId(data.get("transactionId") == null || data.get("transactionId").isNull() ? null : data.get("transactionId").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("ownerId", getOwnerId());
                put("transactionId", getTransactionId());
            }}
        );
    }
}