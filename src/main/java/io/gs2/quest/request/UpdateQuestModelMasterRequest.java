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

package io.gs2.quest.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;
import io.gs2.quest.model.AcquireAction;
import io.gs2.quest.model.Contents;
import io.gs2.quest.model.VerifyAction;
import io.gs2.quest.model.ConsumeAction;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class UpdateQuestModelMasterRequest extends Gs2BasicRequest<UpdateQuestModelMasterRequest> {
    private String namespaceName;
    private String questGroupName;
    private String questName;
    private String description;
    private String metadata;
    private List<Contents> contents;
    private String challengePeriodEventId;
    private List<AcquireAction> firstCompleteAcquireActions;
    private List<VerifyAction> verifyActions;
    private List<ConsumeAction> consumeActions;
    private List<AcquireAction> failedAcquireActions;
    private List<String> premiseQuestNames;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public UpdateQuestModelMasterRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getQuestGroupName() {
		return questGroupName;
	}
	public void setQuestGroupName(String questGroupName) {
		this.questGroupName = questGroupName;
	}
	public UpdateQuestModelMasterRequest withQuestGroupName(String questGroupName) {
		this.questGroupName = questGroupName;
		return this;
	}
	public String getQuestName() {
		return questName;
	}
	public void setQuestName(String questName) {
		this.questName = questName;
	}
	public UpdateQuestModelMasterRequest withQuestName(String questName) {
		this.questName = questName;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public UpdateQuestModelMasterRequest withDescription(String description) {
		this.description = description;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public UpdateQuestModelMasterRequest withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public List<Contents> getContents() {
		return contents;
	}
	public void setContents(List<Contents> contents) {
		this.contents = contents;
	}
	public UpdateQuestModelMasterRequest withContents(List<Contents> contents) {
		this.contents = contents;
		return this;
	}
	public String getChallengePeriodEventId() {
		return challengePeriodEventId;
	}
	public void setChallengePeriodEventId(String challengePeriodEventId) {
		this.challengePeriodEventId = challengePeriodEventId;
	}
	public UpdateQuestModelMasterRequest withChallengePeriodEventId(String challengePeriodEventId) {
		this.challengePeriodEventId = challengePeriodEventId;
		return this;
	}
	public List<AcquireAction> getFirstCompleteAcquireActions() {
		return firstCompleteAcquireActions;
	}
	public void setFirstCompleteAcquireActions(List<AcquireAction> firstCompleteAcquireActions) {
		this.firstCompleteAcquireActions = firstCompleteAcquireActions;
	}
	public UpdateQuestModelMasterRequest withFirstCompleteAcquireActions(List<AcquireAction> firstCompleteAcquireActions) {
		this.firstCompleteAcquireActions = firstCompleteAcquireActions;
		return this;
	}
	public List<VerifyAction> getVerifyActions() {
		return verifyActions;
	}
	public void setVerifyActions(List<VerifyAction> verifyActions) {
		this.verifyActions = verifyActions;
	}
	public UpdateQuestModelMasterRequest withVerifyActions(List<VerifyAction> verifyActions) {
		this.verifyActions = verifyActions;
		return this;
	}
	public List<ConsumeAction> getConsumeActions() {
		return consumeActions;
	}
	public void setConsumeActions(List<ConsumeAction> consumeActions) {
		this.consumeActions = consumeActions;
	}
	public UpdateQuestModelMasterRequest withConsumeActions(List<ConsumeAction> consumeActions) {
		this.consumeActions = consumeActions;
		return this;
	}
	public List<AcquireAction> getFailedAcquireActions() {
		return failedAcquireActions;
	}
	public void setFailedAcquireActions(List<AcquireAction> failedAcquireActions) {
		this.failedAcquireActions = failedAcquireActions;
	}
	public UpdateQuestModelMasterRequest withFailedAcquireActions(List<AcquireAction> failedAcquireActions) {
		this.failedAcquireActions = failedAcquireActions;
		return this;
	}
	public List<String> getPremiseQuestNames() {
		return premiseQuestNames;
	}
	public void setPremiseQuestNames(List<String> premiseQuestNames) {
		this.premiseQuestNames = premiseQuestNames;
	}
	public UpdateQuestModelMasterRequest withPremiseQuestNames(List<String> premiseQuestNames) {
		this.premiseQuestNames = premiseQuestNames;
		return this;
	}

    public static UpdateQuestModelMasterRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new UpdateQuestModelMasterRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withQuestGroupName(data.get("questGroupName") == null || data.get("questGroupName").isNull() ? null : data.get("questGroupName").asText())
            .withQuestName(data.get("questName") == null || data.get("questName").isNull() ? null : data.get("questName").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withContents(data.get("contents") == null || data.get("contents").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("contents").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return Contents.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withChallengePeriodEventId(data.get("challengePeriodEventId") == null || data.get("challengePeriodEventId").isNull() ? null : data.get("challengePeriodEventId").asText())
            .withFirstCompleteAcquireActions(data.get("firstCompleteAcquireActions") == null || data.get("firstCompleteAcquireActions").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("firstCompleteAcquireActions").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return AcquireAction.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withVerifyActions(data.get("verifyActions") == null || data.get("verifyActions").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("verifyActions").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return VerifyAction.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withConsumeActions(data.get("consumeActions") == null || data.get("consumeActions").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("consumeActions").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return ConsumeAction.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withFailedAcquireActions(data.get("failedAcquireActions") == null || data.get("failedAcquireActions").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("failedAcquireActions").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return AcquireAction.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withPremiseQuestNames(data.get("premiseQuestNames") == null || data.get("premiseQuestNames").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("premiseQuestNames").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.asText();
                }
            ).collect(Collectors.toList()));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("questGroupName", getQuestGroupName());
                put("questName", getQuestName());
                put("description", getDescription());
                put("metadata", getMetadata());
                put("contents", getContents() == null ? null :
                    getContents().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("challengePeriodEventId", getChallengePeriodEventId());
                put("firstCompleteAcquireActions", getFirstCompleteAcquireActions() == null ? null :
                    getFirstCompleteAcquireActions().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("verifyActions", getVerifyActions() == null ? null :
                    getVerifyActions().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("consumeActions", getConsumeActions() == null ? null :
                    getConsumeActions().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("failedAcquireActions", getFailedAcquireActions() == null ? null :
                    getFailedAcquireActions().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("premiseQuestNames", getPremiseQuestNames() == null ? null :
                    getPremiseQuestNames().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
            }}
        );
    }
}