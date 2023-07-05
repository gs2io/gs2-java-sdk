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

package io.gs2.exchange.request;

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
public class UnlockIncrementalExchangeByUserIdRequest extends Gs2BasicRequest<UnlockIncrementalExchangeByUserIdRequest> {
    private String namespaceName;
    private String rateName;
    private String userId;
    private String lockTransactionId;
    private String duplicationAvoider;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public UnlockIncrementalExchangeByUserIdRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getRateName() {
		return rateName;
	}
	public void setRateName(String rateName) {
		this.rateName = rateName;
	}
	public UnlockIncrementalExchangeByUserIdRequest withRateName(String rateName) {
		this.rateName = rateName;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public UnlockIncrementalExchangeByUserIdRequest withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public String getLockTransactionId() {
		return lockTransactionId;
	}
	public void setLockTransactionId(String lockTransactionId) {
		this.lockTransactionId = lockTransactionId;
	}
	public UnlockIncrementalExchangeByUserIdRequest withLockTransactionId(String lockTransactionId) {
		this.lockTransactionId = lockTransactionId;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public UnlockIncrementalExchangeByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static UnlockIncrementalExchangeByUserIdRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new UnlockIncrementalExchangeByUserIdRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withRateName(data.get("rateName") == null || data.get("rateName").isNull() ? null : data.get("rateName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withLockTransactionId(data.get("lockTransactionId") == null || data.get("lockTransactionId").isNull() ? null : data.get("lockTransactionId").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("rateName", getRateName());
                put("userId", getUserId());
                put("lockTransactionId", getLockTransactionId());
            }}
        );
    }
}