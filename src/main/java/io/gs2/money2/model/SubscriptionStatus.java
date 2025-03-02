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

package io.gs2.money2.model;

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
public class SubscriptionStatus implements IModel, Serializable, Comparable<SubscriptionStatus> {
	private String userId;
	private String contentName;
	private String status;
	private Long expiresAt;
	private List<SubscribeTransaction> detail;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public SubscriptionStatus withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public String getContentName() {
		return contentName;
	}
	public void setContentName(String contentName) {
		this.contentName = contentName;
	}
	public SubscriptionStatus withContentName(String contentName) {
		this.contentName = contentName;
		return this;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public SubscriptionStatus withStatus(String status) {
		this.status = status;
		return this;
	}
	public Long getExpiresAt() {
		return expiresAt;
	}
	public void setExpiresAt(Long expiresAt) {
		this.expiresAt = expiresAt;
	}
	public SubscriptionStatus withExpiresAt(Long expiresAt) {
		this.expiresAt = expiresAt;
		return this;
	}
	public List<SubscribeTransaction> getDetail() {
		return detail;
	}
	public void setDetail(List<SubscribeTransaction> detail) {
		this.detail = detail;
	}
	public SubscriptionStatus withDetail(List<SubscribeTransaction> detail) {
		this.detail = detail;
		return this;
	}

    public static SubscriptionStatus fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new SubscriptionStatus()
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withContentName(data.get("contentName") == null || data.get("contentName").isNull() ? null : data.get("contentName").asText())
            .withStatus(data.get("status") == null || data.get("status").isNull() ? null : data.get("status").asText())
            .withExpiresAt(data.get("expiresAt") == null || data.get("expiresAt").isNull() ? null : data.get("expiresAt").longValue())
            .withDetail(data.get("detail") == null || data.get("detail").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("detail").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return SubscribeTransaction.fromJson(item);
                }
            ).collect(Collectors.toList()));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("userId", getUserId());
                put("contentName", getContentName());
                put("status", getStatus());
                put("expiresAt", getExpiresAt());
                put("detail", getDetail() == null ? null :
                    getDetail().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }

	@Override
	public int compareTo(SubscriptionStatus o) {
		return userId.compareTo(o.userId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.contentName == null) ? 0 : this.contentName.hashCode());
        result = prime * result + ((this.status == null) ? 0 : this.status.hashCode());
        result = prime * result + ((this.expiresAt == null) ? 0 : this.expiresAt.hashCode());
        result = prime * result + ((this.detail == null) ? 0 : this.detail.hashCode());
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
		SubscriptionStatus other = (SubscriptionStatus) o;
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (contentName == null) {
			return other.contentName == null;
		} else if (!contentName.equals(other.contentName)) {
			return false;
		}
		if (status == null) {
			return other.status == null;
		} else if (!status.equals(other.status)) {
			return false;
		}
		if (expiresAt == null) {
			return other.expiresAt == null;
		} else if (!expiresAt.equals(other.expiresAt)) {
			return false;
		}
		if (detail == null) {
			return other.detail == null;
		} else if (!detail.equals(other.detail)) {
			return false;
		}
		return true;
	}
}