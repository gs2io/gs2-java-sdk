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

package io.gs2.guild.model;

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
public class Inbox implements IModel, Serializable, Comparable<Inbox> {
	private String inboxId;
	private String guildName;
	private List<String> fromUserIds;
	private List<ReceiveMemberRequest> receiveMemberRequests;
	private Long createdAt;
	private Long updatedAt;
	private Long revision;
	public String getInboxId() {
		return inboxId;
	}
	public void setInboxId(String inboxId) {
		this.inboxId = inboxId;
	}
	public Inbox withInboxId(String inboxId) {
		this.inboxId = inboxId;
		return this;
	}
	public String getGuildName() {
		return guildName;
	}
	public void setGuildName(String guildName) {
		this.guildName = guildName;
	}
	public Inbox withGuildName(String guildName) {
		this.guildName = guildName;
		return this;
	}
    @Deprecated
	public List<String> getFromUserIds() {
		return fromUserIds;
	}
    @Deprecated
	public void setFromUserIds(List<String> fromUserIds) {
		this.fromUserIds = fromUserIds;
	}
    @Deprecated
	public Inbox withFromUserIds(List<String> fromUserIds) {
		this.fromUserIds = fromUserIds;
		return this;
	}
	public List<ReceiveMemberRequest> getReceiveMemberRequests() {
		return receiveMemberRequests;
	}
	public void setReceiveMemberRequests(List<ReceiveMemberRequest> receiveMemberRequests) {
		this.receiveMemberRequests = receiveMemberRequests;
	}
	public Inbox withReceiveMemberRequests(List<ReceiveMemberRequest> receiveMemberRequests) {
		this.receiveMemberRequests = receiveMemberRequests;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public Inbox withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	public Long getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Inbox withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}
	public Long getRevision() {
		return revision;
	}
	public void setRevision(Long revision) {
		this.revision = revision;
	}
	public Inbox withRevision(Long revision) {
		this.revision = revision;
		return this;
	}

    public static Inbox fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Inbox()
            .withInboxId(data.get("inboxId") == null || data.get("inboxId").isNull() ? null : data.get("inboxId").asText())
            .withGuildName(data.get("guildName") == null || data.get("guildName").isNull() ? null : data.get("guildName").asText())
            .withFromUserIds(data.get("fromUserIds") == null || data.get("fromUserIds").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("fromUserIds").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.asText();
                }
            ).collect(Collectors.toList()))
            .withReceiveMemberRequests(data.get("receiveMemberRequests") == null || data.get("receiveMemberRequests").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("receiveMemberRequests").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return ReceiveMemberRequest.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue())
            .withRevision(data.get("revision") == null || data.get("revision").isNull() ? null : data.get("revision").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("inboxId", getInboxId());
                put("guildName", getGuildName());
                put("fromUserIds", getFromUserIds() == null ? null :
                    getFromUserIds().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
                put("receiveMemberRequests", getReceiveMemberRequests() == null ? null :
                    getReceiveMemberRequests().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
                put("revision", getRevision());
            }}
        );
    }

	@Override
	public int compareTo(Inbox o) {
		return inboxId.compareTo(o.inboxId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.inboxId == null) ? 0 : this.inboxId.hashCode());
        result = prime * result + ((this.guildName == null) ? 0 : this.guildName.hashCode());
        result = prime * result + ((this.fromUserIds == null) ? 0 : this.fromUserIds.hashCode());
        result = prime * result + ((this.receiveMemberRequests == null) ? 0 : this.receiveMemberRequests.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
        result = prime * result + ((this.updatedAt == null) ? 0 : this.updatedAt.hashCode());
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
		Inbox other = (Inbox) o;
		if (inboxId == null) {
			return other.inboxId == null;
		} else if (!inboxId.equals(other.inboxId)) {
			return false;
		}
		if (guildName == null) {
			return other.guildName == null;
		} else if (!guildName.equals(other.guildName)) {
			return false;
		}
		if (fromUserIds == null) {
			return other.fromUserIds == null;
		} else if (!fromUserIds.equals(other.fromUserIds)) {
			return false;
		}
		if (receiveMemberRequests == null) {
			return other.receiveMemberRequests == null;
		} else if (!receiveMemberRequests.equals(other.receiveMemberRequests)) {
			return false;
		}
		if (createdAt == null) {
			return other.createdAt == null;
		} else if (!createdAt.equals(other.createdAt)) {
			return false;
		}
		if (updatedAt == null) {
			return other.updatedAt == null;
		} else if (!updatedAt.equals(other.updatedAt)) {
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