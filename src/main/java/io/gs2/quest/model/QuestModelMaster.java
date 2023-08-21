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
public class QuestModelMaster implements IModel, Serializable, Comparable<QuestModelMaster> {
	private String questModelId;
	private String questGroupName;
	private String name;
	private String description;
	private String metadata;
	private List<Contents> contents;
	private String challengePeriodEventId;
	private List<AcquireAction> firstCompleteAcquireActions;
	private List<ConsumeAction> consumeActions;
	private List<AcquireAction> failedAcquireActions;
	private List<String> premiseQuestNames;
	private Long createdAt;
	private Long updatedAt;
	private Long revision;
	public String getQuestModelId() {
		return questModelId;
	}
	public void setQuestModelId(String questModelId) {
		this.questModelId = questModelId;
	}
	public QuestModelMaster withQuestModelId(String questModelId) {
		this.questModelId = questModelId;
		return this;
	}
	public String getQuestGroupName() {
		return questGroupName;
	}
	public void setQuestGroupName(String questGroupName) {
		this.questGroupName = questGroupName;
	}
	public QuestModelMaster withQuestGroupName(String questGroupName) {
		this.questGroupName = questGroupName;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public QuestModelMaster withName(String name) {
		this.name = name;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public QuestModelMaster withDescription(String description) {
		this.description = description;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public QuestModelMaster withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public List<Contents> getContents() {
		return contents;
	}
	public void setContents(List<Contents> contents) {
		this.contents = contents;
	}
	public QuestModelMaster withContents(List<Contents> contents) {
		this.contents = contents;
		return this;
	}
	public String getChallengePeriodEventId() {
		return challengePeriodEventId;
	}
	public void setChallengePeriodEventId(String challengePeriodEventId) {
		this.challengePeriodEventId = challengePeriodEventId;
	}
	public QuestModelMaster withChallengePeriodEventId(String challengePeriodEventId) {
		this.challengePeriodEventId = challengePeriodEventId;
		return this;
	}
	public List<AcquireAction> getFirstCompleteAcquireActions() {
		return firstCompleteAcquireActions;
	}
	public void setFirstCompleteAcquireActions(List<AcquireAction> firstCompleteAcquireActions) {
		this.firstCompleteAcquireActions = firstCompleteAcquireActions;
	}
	public QuestModelMaster withFirstCompleteAcquireActions(List<AcquireAction> firstCompleteAcquireActions) {
		this.firstCompleteAcquireActions = firstCompleteAcquireActions;
		return this;
	}
	public List<ConsumeAction> getConsumeActions() {
		return consumeActions;
	}
	public void setConsumeActions(List<ConsumeAction> consumeActions) {
		this.consumeActions = consumeActions;
	}
	public QuestModelMaster withConsumeActions(List<ConsumeAction> consumeActions) {
		this.consumeActions = consumeActions;
		return this;
	}
	public List<AcquireAction> getFailedAcquireActions() {
		return failedAcquireActions;
	}
	public void setFailedAcquireActions(List<AcquireAction> failedAcquireActions) {
		this.failedAcquireActions = failedAcquireActions;
	}
	public QuestModelMaster withFailedAcquireActions(List<AcquireAction> failedAcquireActions) {
		this.failedAcquireActions = failedAcquireActions;
		return this;
	}
	public List<String> getPremiseQuestNames() {
		return premiseQuestNames;
	}
	public void setPremiseQuestNames(List<String> premiseQuestNames) {
		this.premiseQuestNames = premiseQuestNames;
	}
	public QuestModelMaster withPremiseQuestNames(List<String> premiseQuestNames) {
		this.premiseQuestNames = premiseQuestNames;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public QuestModelMaster withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	public Long getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}
	public QuestModelMaster withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}
	public Long getRevision() {
		return revision;
	}
	public void setRevision(Long revision) {
		this.revision = revision;
	}
	public QuestModelMaster withRevision(Long revision) {
		this.revision = revision;
		return this;
	}

    public static QuestModelMaster fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new QuestModelMaster()
            .withQuestModelId(data.get("questModelId") == null || data.get("questModelId").isNull() ? null : data.get("questModelId").asText())
            .withQuestGroupName(data.get("questGroupName") == null || data.get("questGroupName").isNull() ? null : data.get("questGroupName").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withContents(data.get("contents") == null || data.get("contents").isNull() ? new ArrayList<Contents>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("contents").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return Contents.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withChallengePeriodEventId(data.get("challengePeriodEventId") == null || data.get("challengePeriodEventId").isNull() ? null : data.get("challengePeriodEventId").asText())
            .withFirstCompleteAcquireActions(data.get("firstCompleteAcquireActions") == null || data.get("firstCompleteAcquireActions").isNull() ? new ArrayList<AcquireAction>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("firstCompleteAcquireActions").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return AcquireAction.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withConsumeActions(data.get("consumeActions") == null || data.get("consumeActions").isNull() ? new ArrayList<ConsumeAction>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("consumeActions").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return ConsumeAction.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withFailedAcquireActions(data.get("failedAcquireActions") == null || data.get("failedAcquireActions").isNull() ? new ArrayList<AcquireAction>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("failedAcquireActions").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return AcquireAction.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withPremiseQuestNames(data.get("premiseQuestNames") == null || data.get("premiseQuestNames").isNull() ? new ArrayList<String>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("premiseQuestNames").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.asText();
                }
            ).collect(Collectors.toList()))
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue())
            .withRevision(data.get("revision") == null || data.get("revision").isNull() ? null : data.get("revision").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("questModelId", getQuestModelId());
                put("questGroupName", getQuestGroupName());
                put("name", getName());
                put("description", getDescription());
                put("metadata", getMetadata());
                put("contents", getContents() == null ? new ArrayList<Contents>() :
                    getContents().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("challengePeriodEventId", getChallengePeriodEventId());
                put("firstCompleteAcquireActions", getFirstCompleteAcquireActions() == null ? new ArrayList<AcquireAction>() :
                    getFirstCompleteAcquireActions().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("consumeActions", getConsumeActions() == null ? new ArrayList<ConsumeAction>() :
                    getConsumeActions().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("failedAcquireActions", getFailedAcquireActions() == null ? new ArrayList<AcquireAction>() :
                    getFailedAcquireActions().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("premiseQuestNames", getPremiseQuestNames() == null ? new ArrayList<String>() :
                    getPremiseQuestNames().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
                put("revision", getRevision());
            }}
        );
    }

	@Override
	public int compareTo(QuestModelMaster o) {
		return questModelId.compareTo(o.questModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.questModelId == null) ? 0 : this.questModelId.hashCode());
        result = prime * result + ((this.questGroupName == null) ? 0 : this.questGroupName.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.contents == null) ? 0 : this.contents.hashCode());
        result = prime * result + ((this.challengePeriodEventId == null) ? 0 : this.challengePeriodEventId.hashCode());
        result = prime * result + ((this.firstCompleteAcquireActions == null) ? 0 : this.firstCompleteAcquireActions.hashCode());
        result = prime * result + ((this.consumeActions == null) ? 0 : this.consumeActions.hashCode());
        result = prime * result + ((this.failedAcquireActions == null) ? 0 : this.failedAcquireActions.hashCode());
        result = prime * result + ((this.premiseQuestNames == null) ? 0 : this.premiseQuestNames.hashCode());
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
		QuestModelMaster other = (QuestModelMaster) o;
		if (questModelId == null) {
			return other.questModelId == null;
		} else if (!questModelId.equals(other.questModelId)) {
			return false;
		}
		if (questGroupName == null) {
			return other.questGroupName == null;
		} else if (!questGroupName.equals(other.questGroupName)) {
			return false;
		}
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (description == null) {
			return other.description == null;
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (metadata == null) {
			return other.metadata == null;
		} else if (!metadata.equals(other.metadata)) {
			return false;
		}
		if (contents == null) {
			return other.contents == null;
		} else if (!contents.equals(other.contents)) {
			return false;
		}
		if (challengePeriodEventId == null) {
			return other.challengePeriodEventId == null;
		} else if (!challengePeriodEventId.equals(other.challengePeriodEventId)) {
			return false;
		}
		if (firstCompleteAcquireActions == null) {
			return other.firstCompleteAcquireActions == null;
		} else if (!firstCompleteAcquireActions.equals(other.firstCompleteAcquireActions)) {
			return false;
		}
		if (consumeActions == null) {
			return other.consumeActions == null;
		} else if (!consumeActions.equals(other.consumeActions)) {
			return false;
		}
		if (failedAcquireActions == null) {
			return other.failedAcquireActions == null;
		} else if (!failedAcquireActions.equals(other.failedAcquireActions)) {
			return false;
		}
		if (premiseQuestNames == null) {
			return other.premiseQuestNames == null;
		} else if (!premiseQuestNames.equals(other.premiseQuestNames)) {
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