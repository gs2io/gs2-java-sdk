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

package io.gs2.ranking2.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;
import io.gs2.ranking2.model.AcquireAction;
import io.gs2.ranking2.model.RankingReward;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class UpdateClusterRankingModelMasterRequest extends Gs2BasicRequest<UpdateClusterRankingModelMasterRequest> {
    private String namespaceName;
    private String rankingName;
    private String description;
    private String metadata;
    private String clusterType;
    private Long minimumValue;
    private Long maximumValue;
    private Boolean sum;
    private Integer scoreTtlDays;
    private String orderDirection;
    private List<RankingReward> rankingRewards;
    private String entryPeriodEventId;
    private String accessPeriodEventId;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public UpdateClusterRankingModelMasterRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getRankingName() {
		return rankingName;
	}
	public void setRankingName(String rankingName) {
		this.rankingName = rankingName;
	}
	public UpdateClusterRankingModelMasterRequest withRankingName(String rankingName) {
		this.rankingName = rankingName;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public UpdateClusterRankingModelMasterRequest withDescription(String description) {
		this.description = description;
		return this;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public UpdateClusterRankingModelMasterRequest withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	public String getClusterType() {
		return clusterType;
	}
	public void setClusterType(String clusterType) {
		this.clusterType = clusterType;
	}
	public UpdateClusterRankingModelMasterRequest withClusterType(String clusterType) {
		this.clusterType = clusterType;
		return this;
	}
	public Long getMinimumValue() {
		return minimumValue;
	}
	public void setMinimumValue(Long minimumValue) {
		this.minimumValue = minimumValue;
	}
	public UpdateClusterRankingModelMasterRequest withMinimumValue(Long minimumValue) {
		this.minimumValue = minimumValue;
		return this;
	}
	public Long getMaximumValue() {
		return maximumValue;
	}
	public void setMaximumValue(Long maximumValue) {
		this.maximumValue = maximumValue;
	}
	public UpdateClusterRankingModelMasterRequest withMaximumValue(Long maximumValue) {
		this.maximumValue = maximumValue;
		return this;
	}
	public Boolean getSum() {
		return sum;
	}
	public void setSum(Boolean sum) {
		this.sum = sum;
	}
	public UpdateClusterRankingModelMasterRequest withSum(Boolean sum) {
		this.sum = sum;
		return this;
	}
	public Integer getScoreTtlDays() {
		return scoreTtlDays;
	}
	public void setScoreTtlDays(Integer scoreTtlDays) {
		this.scoreTtlDays = scoreTtlDays;
	}
	public UpdateClusterRankingModelMasterRequest withScoreTtlDays(Integer scoreTtlDays) {
		this.scoreTtlDays = scoreTtlDays;
		return this;
	}
	public String getOrderDirection() {
		return orderDirection;
	}
	public void setOrderDirection(String orderDirection) {
		this.orderDirection = orderDirection;
	}
	public UpdateClusterRankingModelMasterRequest withOrderDirection(String orderDirection) {
		this.orderDirection = orderDirection;
		return this;
	}
	public List<RankingReward> getRankingRewards() {
		return rankingRewards;
	}
	public void setRankingRewards(List<RankingReward> rankingRewards) {
		this.rankingRewards = rankingRewards;
	}
	public UpdateClusterRankingModelMasterRequest withRankingRewards(List<RankingReward> rankingRewards) {
		this.rankingRewards = rankingRewards;
		return this;
	}
	public String getEntryPeriodEventId() {
		return entryPeriodEventId;
	}
	public void setEntryPeriodEventId(String entryPeriodEventId) {
		this.entryPeriodEventId = entryPeriodEventId;
	}
	public UpdateClusterRankingModelMasterRequest withEntryPeriodEventId(String entryPeriodEventId) {
		this.entryPeriodEventId = entryPeriodEventId;
		return this;
	}
	public String getAccessPeriodEventId() {
		return accessPeriodEventId;
	}
	public void setAccessPeriodEventId(String accessPeriodEventId) {
		this.accessPeriodEventId = accessPeriodEventId;
	}
	public UpdateClusterRankingModelMasterRequest withAccessPeriodEventId(String accessPeriodEventId) {
		this.accessPeriodEventId = accessPeriodEventId;
		return this;
	}

    public static UpdateClusterRankingModelMasterRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new UpdateClusterRankingModelMasterRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withRankingName(data.get("rankingName") == null || data.get("rankingName").isNull() ? null : data.get("rankingName").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withMetadata(data.get("metadata") == null || data.get("metadata").isNull() ? null : data.get("metadata").asText())
            .withClusterType(data.get("clusterType") == null || data.get("clusterType").isNull() ? null : data.get("clusterType").asText())
            .withMinimumValue(data.get("minimumValue") == null || data.get("minimumValue").isNull() ? null : data.get("minimumValue").longValue())
            .withMaximumValue(data.get("maximumValue") == null || data.get("maximumValue").isNull() ? null : data.get("maximumValue").longValue())
            .withSum(data.get("sum") == null || data.get("sum").isNull() ? null : data.get("sum").booleanValue())
            .withScoreTtlDays(data.get("scoreTtlDays") == null || data.get("scoreTtlDays").isNull() ? null : data.get("scoreTtlDays").intValue())
            .withOrderDirection(data.get("orderDirection") == null || data.get("orderDirection").isNull() ? null : data.get("orderDirection").asText())
            .withRankingRewards(data.get("rankingRewards") == null || data.get("rankingRewards").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("rankingRewards").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return RankingReward.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withEntryPeriodEventId(data.get("entryPeriodEventId") == null || data.get("entryPeriodEventId").isNull() ? null : data.get("entryPeriodEventId").asText())
            .withAccessPeriodEventId(data.get("accessPeriodEventId") == null || data.get("accessPeriodEventId").isNull() ? null : data.get("accessPeriodEventId").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("rankingName", getRankingName());
                put("description", getDescription());
                put("metadata", getMetadata());
                put("clusterType", getClusterType());
                put("minimumValue", getMinimumValue());
                put("maximumValue", getMaximumValue());
                put("sum", getSum());
                put("scoreTtlDays", getScoreTtlDays());
                put("orderDirection", getOrderDirection());
                put("rankingRewards", getRankingRewards() == null ? null :
                    getRankingRewards().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("entryPeriodEventId", getEntryPeriodEventId());
                put("accessPeriodEventId", getAccessPeriodEventId());
            }}
        );
    }
}