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

package io.gs2.quest.model;

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
public class CompletedQuestList implements IModel, Serializable, Comparable<CompletedQuestList> {
	private String completedQuestListId;
	private String userId;
	private String questGroupName;
	private List<String> completeQuestNames;
	private Long createdAt;
	private Long updatedAt;

	public String getCompletedQuestListId() {
		return completedQuestListId;
	}

	public void setCompletedQuestListId(String completedQuestListId) {
		this.completedQuestListId = completedQuestListId;
	}

	public CompletedQuestList withCompletedQuestListId(String completedQuestListId) {
		this.completedQuestListId = completedQuestListId;
		return this;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public CompletedQuestList withUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public String getQuestGroupName() {
		return questGroupName;
	}

	public void setQuestGroupName(String questGroupName) {
		this.questGroupName = questGroupName;
	}

	public CompletedQuestList withQuestGroupName(String questGroupName) {
		this.questGroupName = questGroupName;
		return this;
	}

	public List<String> getCompleteQuestNames() {
		return completeQuestNames;
	}

	public void setCompleteQuestNames(List<String> completeQuestNames) {
		this.completeQuestNames = completeQuestNames;
	}

	public CompletedQuestList withCompleteQuestNames(List<String> completeQuestNames) {
		this.completeQuestNames = completeQuestNames;
		return this;
	}

	public Long getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	public CompletedQuestList withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}

	public Long getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}

	public CompletedQuestList withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public static CompletedQuestList fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new CompletedQuestList()
            .withCompletedQuestListId(data.get("completedQuestListId") == null || data.get("completedQuestListId").isNull() ? null : data.get("completedQuestListId").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withQuestGroupName(data.get("questGroupName") == null || data.get("questGroupName").isNull() ? null : data.get("questGroupName").asText())
            .withCompleteQuestNames(data.get("completeQuestNames") == null || data.get("completeQuestNames").isNull() ? new ArrayList<String>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("completeQuestNames").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.asText();
                }
            ).collect(Collectors.toList()))
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("completedQuestListId", getCompletedQuestListId());
                put("userId", getUserId());
                put("questGroupName", getQuestGroupName());
                put("completeQuestNames", getCompleteQuestNames() == null ? new ArrayList<String>() :
                    getCompleteQuestNames().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
            }}
        );
    }

	@Override
	public int compareTo(CompletedQuestList o) {
		return completedQuestListId.compareTo(o.completedQuestListId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.completedQuestListId == null) ? 0 : this.completedQuestListId.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.questGroupName == null) ? 0 : this.questGroupName.hashCode());
        result = prime * result + ((this.completeQuestNames == null) ? 0 : this.completeQuestNames.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
        result = prime * result + ((this.updatedAt == null) ? 0 : this.updatedAt.hashCode());
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
		CompletedQuestList other = (CompletedQuestList) o;
		if (completedQuestListId == null) {
			return other.completedQuestListId == null;
		} else if (!completedQuestListId.equals(other.completedQuestListId)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (questGroupName == null) {
			return other.questGroupName == null;
		} else if (!questGroupName.equals(other.questGroupName)) {
			return false;
		}
		if (completeQuestNames == null) {
			return other.completeQuestNames == null;
		} else if (!completeQuestNames.equals(other.completeQuestNames)) {
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
		return true;
	}
}