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
public class QuestModel implements IModel, Serializable, Comparable<QuestModel> {
	private String questModelId;
	private String name;
	private String metadata;
	private List<Contents> contents;
	private String challengePeriodEventId;
	private List<ConsumeAction> consumeActions;
	private List<AcquireAction> failedAcquireActions;
	private List<String> premiseQuestNames;
	public String getQuestModelId() {
		return questModelId;
	}
	public void setQuestModelId(String questModelId) {
		this.questModelId = questModelId;
	}
	public QuestModel withQuestModelId(String questModelId) {
		this.questModelId = questModelId;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public QuestModel withName(String name) {
		this.name = name;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public QuestModel withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public List<Contents> getContents() {
		return contents;
	}
	public void setContents(List<Contents> contents) {
		this.contents = contents;
	}
	public QuestModel withContents(List<Contents> contents) {
		this.contents = contents;
		return this;
	}
	public String getChallengePeriodEventId() {
		return challengePeriodEventId;
	}
	public void setChallengePeriodEventId(String challengePeriodEventId) {
		this.challengePeriodEventId = challengePeriodEventId;
	}
	public QuestModel withChallengePeriodEventId(String challengePeriodEventId) {
		this.challengePeriodEventId = challengePeriodEventId;
		return this;
	}
	public List<ConsumeAction> getConsumeActions() {
		return consumeActions;
	}
	public void setConsumeActions(List<ConsumeAction> consumeActions) {
		this.consumeActions = consumeActions;
	}
	public QuestModel withConsumeActions(List<ConsumeAction> consumeActions) {
		this.consumeActions = consumeActions;
		return this;
	}
	public List<AcquireAction> getFailedAcquireActions() {
		return failedAcquireActions;
	}
	public void setFailedAcquireActions(List<AcquireAction> failedAcquireActions) {
		this.failedAcquireActions = failedAcquireActions;
	}
	public QuestModel withFailedAcquireActions(List<AcquireAction> failedAcquireActions) {
		this.failedAcquireActions = failedAcquireActions;
		return this;
	}
	public List<String> getPremiseQuestNames() {
		return premiseQuestNames;
	}
	public void setPremiseQuestNames(List<String> premiseQuestNames) {
		this.premiseQuestNames = premiseQuestNames;
	}
	public QuestModel withPremiseQuestNames(List<String> premiseQuestNames) {
		this.premiseQuestNames = premiseQuestNames;
		return this;
	}

    public static QuestModel fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new QuestModel()
            .withQuestModelId(data.get("questModelId") == null || data.get("questModelId").isNull() ? null : data.get("questModelId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withContents(data.get("contents") == null || data.get("contents").isNull() ? new ArrayList<Contents>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("contents").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return Contents.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withChallengePeriodEventId(data.get("challengePeriodEventId") == null || data.get("challengePeriodEventId").isNull() ? null : data.get("challengePeriodEventId").asText())
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
            ).collect(Collectors.toList()));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("questModelId", getQuestModelId());
                put("name", getName());
                put("metadata", getMetadata());
                put("contents", getContents() == null ? new ArrayList<Contents>() :
                    getContents().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("challengePeriodEventId", getChallengePeriodEventId());
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
            }}
        );
    }

	@Override
	public int compareTo(QuestModel o) {
		return questModelId.compareTo(o.questModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.questModelId == null) ? 0 : this.questModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.contents == null) ? 0 : this.contents.hashCode());
        result = prime * result + ((this.challengePeriodEventId == null) ? 0 : this.challengePeriodEventId.hashCode());
        result = prime * result + ((this.consumeActions == null) ? 0 : this.consumeActions.hashCode());
        result = prime * result + ((this.failedAcquireActions == null) ? 0 : this.failedAcquireActions.hashCode());
        result = prime * result + ((this.premiseQuestNames == null) ? 0 : this.premiseQuestNames.hashCode());
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
		QuestModel other = (QuestModel) o;
		if (questModelId == null) {
			return other.questModelId == null;
		} else if (!questModelId.equals(other.questModelId)) {
			return false;
		}
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
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
		return true;
	}
}