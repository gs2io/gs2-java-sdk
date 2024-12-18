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

package io.gs2.account.model;

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
public class Account implements IModel, Serializable, Comparable<Account> {
	private String accountId;
	private String userId;
	private String password;
	private Integer timeOffset;
	private List<BanStatus> banStatuses;
	private Boolean banned;
	private Long lastAuthenticatedAt;
	private Long createdAt;
	private Long revision;
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public Account withAccountId(String accountId) {
		this.accountId = accountId;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Account withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Account withPassword(String password) {
		this.password = password;
		return this;
	}
	public Integer getTimeOffset() {
		return timeOffset;
	}
	public void setTimeOffset(Integer timeOffset) {
		this.timeOffset = timeOffset;
	}
	public Account withTimeOffset(Integer timeOffset) {
		this.timeOffset = timeOffset;
		return this;
	}
	public List<BanStatus> getBanStatuses() {
		return banStatuses;
	}
	public void setBanStatuses(List<BanStatus> banStatuses) {
		this.banStatuses = banStatuses;
	}
	public Account withBanStatuses(List<BanStatus> banStatuses) {
		this.banStatuses = banStatuses;
		return this;
	}
	public Boolean getBanned() {
		return banned;
	}
	public void setBanned(Boolean banned) {
		this.banned = banned;
	}
	public Account withBanned(Boolean banned) {
		this.banned = banned;
		return this;
	}
	public Long getLastAuthenticatedAt() {
		return lastAuthenticatedAt;
	}
	public void setLastAuthenticatedAt(Long lastAuthenticatedAt) {
		this.lastAuthenticatedAt = lastAuthenticatedAt;
	}
	public Account withLastAuthenticatedAt(Long lastAuthenticatedAt) {
		this.lastAuthenticatedAt = lastAuthenticatedAt;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public Account withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	public Long getRevision() {
		return revision;
	}
	public void setRevision(Long revision) {
		this.revision = revision;
	}
	public Account withRevision(Long revision) {
		this.revision = revision;
		return this;
	}

    public static Account fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Account()
            .withAccountId(data.get("accountId") == null || data.get("accountId").isNull() ? null : data.get("accountId").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withPassword(data.get("password") == null || data.get("password").isNull() ? null : data.get("password").asText())
            .withTimeOffset(data.get("timeOffset") == null || data.get("timeOffset").isNull() ? null : data.get("timeOffset").intValue())
            .withBanStatuses(data.get("banStatuses") == null || data.get("banStatuses").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("banStatuses").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return BanStatus.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withBanned(data.get("banned") == null || data.get("banned").isNull() ? null : data.get("banned").booleanValue())
            .withLastAuthenticatedAt(data.get("lastAuthenticatedAt") == null || data.get("lastAuthenticatedAt").isNull() ? null : data.get("lastAuthenticatedAt").longValue())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withRevision(data.get("revision") == null || data.get("revision").isNull() ? null : data.get("revision").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("accountId", getAccountId());
                put("userId", getUserId());
                put("password", getPassword());
                put("timeOffset", getTimeOffset());
                put("banStatuses", getBanStatuses() == null ? null :
                    getBanStatuses().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("banned", getBanned());
                put("lastAuthenticatedAt", getLastAuthenticatedAt());
                put("createdAt", getCreatedAt());
                put("revision", getRevision());
            }}
        );
    }

	@Override
	public int compareTo(Account o) {
		return accountId.compareTo(o.accountId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.accountId == null) ? 0 : this.accountId.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.password == null) ? 0 : this.password.hashCode());
        result = prime * result + ((this.timeOffset == null) ? 0 : this.timeOffset.hashCode());
        result = prime * result + ((this.banStatuses == null) ? 0 : this.banStatuses.hashCode());
        result = prime * result + ((this.banned == null) ? 0 : this.banned.hashCode());
        result = prime * result + ((this.lastAuthenticatedAt == null) ? 0 : this.lastAuthenticatedAt.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
        result = prime * result + ((this.revision == null) ? 0 : this.revision.hashCode());
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
		Account other = (Account) o;
		if (accountId == null) {
			return other.accountId == null;
		} else if (!accountId.equals(other.accountId)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (password == null) {
			return other.password == null;
		} else if (!password.equals(other.password)) {
			return false;
		}
		if (timeOffset == null) {
			return other.timeOffset == null;
		} else if (!timeOffset.equals(other.timeOffset)) {
			return false;
		}
		if (banStatuses == null) {
			return other.banStatuses == null;
		} else if (!banStatuses.equals(other.banStatuses)) {
			return false;
		}
		if (banned == null) {
			return other.banned == null;
		} else if (!banned.equals(other.banned)) {
			return false;
		}
		if (lastAuthenticatedAt == null) {
			return other.lastAuthenticatedAt == null;
		} else if (!lastAuthenticatedAt.equals(other.lastAuthenticatedAt)) {
			return false;
		}
		if (createdAt == null) {
			return other.createdAt == null;
		} else if (!createdAt.equals(other.createdAt)) {
			return false;
		}
		if (revision == null) {
			return other.revision == null;
		} else if (!revision.equals(other.revision)) {
			return false;
		}
		return true;
	}
}