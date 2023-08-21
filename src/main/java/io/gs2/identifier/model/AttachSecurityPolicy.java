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

package io.gs2.identifier.model;

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
public class AttachSecurityPolicy implements IModel, Serializable, Comparable<AttachSecurityPolicy> {
	private String userId;
	private List<String> securityPolicyIds;
	private Long attachedAt;
	private Long revision;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public AttachSecurityPolicy withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public List<String> getSecurityPolicyIds() {
		return securityPolicyIds;
	}
	public void setSecurityPolicyIds(List<String> securityPolicyIds) {
		this.securityPolicyIds = securityPolicyIds;
	}
	public AttachSecurityPolicy withSecurityPolicyIds(List<String> securityPolicyIds) {
		this.securityPolicyIds = securityPolicyIds;
		return this;
	}
	public Long getAttachedAt() {
		return attachedAt;
	}
	public void setAttachedAt(Long attachedAt) {
		this.attachedAt = attachedAt;
	}
	public AttachSecurityPolicy withAttachedAt(Long attachedAt) {
		this.attachedAt = attachedAt;
		return this;
	}
	public Long getRevision() {
		return revision;
	}
	public void setRevision(Long revision) {
		this.revision = revision;
	}
	public AttachSecurityPolicy withRevision(Long revision) {
		this.revision = revision;
		return this;
	}

    public static AttachSecurityPolicy fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new AttachSecurityPolicy()
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withSecurityPolicyIds(data.get("securityPolicyIds") == null || data.get("securityPolicyIds").isNull() ? new ArrayList<String>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("securityPolicyIds").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.asText();
                }
            ).collect(Collectors.toList()))
            .withAttachedAt(data.get("attachedAt") == null || data.get("attachedAt").isNull() ? null : data.get("attachedAt").longValue())
            .withRevision(data.get("revision") == null || data.get("revision").isNull() ? null : data.get("revision").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("userId", getUserId());
                put("securityPolicyIds", getSecurityPolicyIds() == null ? new ArrayList<String>() :
                    getSecurityPolicyIds().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
                put("attachedAt", getAttachedAt());
                put("revision", getRevision());
            }}
        );
    }

	@Override
	public int compareTo(AttachSecurityPolicy o) {
		return userId.compareTo(o.userId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.securityPolicyIds == null) ? 0 : this.securityPolicyIds.hashCode());
        result = prime * result + ((this.attachedAt == null) ? 0 : this.attachedAt.hashCode());
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
		AttachSecurityPolicy other = (AttachSecurityPolicy) o;
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (securityPolicyIds == null) {
			return other.securityPolicyIds == null;
		} else if (!securityPolicyIds.equals(other.securityPolicyIds)) {
			return false;
		}
		if (attachedAt == null) {
			return other.attachedAt == null;
		} else if (!attachedAt.equals(other.attachedAt)) {
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