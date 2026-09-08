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

package io.gs2.friend.model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.IModel;


@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class TransactionSettingV2 implements IModel, Serializable {
	private String distributorNamespaceId;
	private Boolean enableParallelExecution;
	public String getDistributorNamespaceId() {
		return distributorNamespaceId;
	}
	public void setDistributorNamespaceId(String distributorNamespaceId) {
		this.distributorNamespaceId = distributorNamespaceId;
	}
	public TransactionSettingV2 withDistributorNamespaceId(String distributorNamespaceId) {
		this.distributorNamespaceId = distributorNamespaceId;
		return this;
	}
	public Boolean getEnableParallelExecution() {
		return enableParallelExecution;
	}
	public void setEnableParallelExecution(Boolean enableParallelExecution) {
		this.enableParallelExecution = enableParallelExecution;
	}
	public TransactionSettingV2 withEnableParallelExecution(Boolean enableParallelExecution) {
		this.enableParallelExecution = enableParallelExecution;
		return this;
	}

    public static TransactionSettingV2 fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new TransactionSettingV2()
            .withDistributorNamespaceId(data.get("distributorNamespaceId") == null || data.get("distributorNamespaceId").isNull() ? null : data.get("distributorNamespaceId").asText())
            .withEnableParallelExecution(data.get("enableParallelExecution") == null || data.get("enableParallelExecution").isNull() ? null : data.get("enableParallelExecution").booleanValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("distributorNamespaceId", getDistributorNamespaceId());
                put("enableParallelExecution", getEnableParallelExecution());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.distributorNamespaceId == null) ? 0 : this.distributorNamespaceId.hashCode());
        result = prime * result + ((this.enableParallelExecution == null) ? 0 : this.enableParallelExecution.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (getClass() != o.getClass())
			return false;
		TransactionSettingV2 other = (TransactionSettingV2) o;
		if (distributorNamespaceId == null) {
			return other.distributorNamespaceId == null;
		} else if (!distributorNamespaceId.equals(other.distributorNamespaceId)) {
			return false;
		}
		if (enableParallelExecution == null) {
			return other.enableParallelExecution == null;
		} else if (!enableParallelExecution.equals(other.enableParallelExecution)) {
			return false;
		}
		return true;
	}
}