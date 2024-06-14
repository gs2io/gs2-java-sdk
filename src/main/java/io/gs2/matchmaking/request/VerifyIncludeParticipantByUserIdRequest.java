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

package io.gs2.matchmaking.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class VerifyIncludeParticipantByUserIdRequest extends Gs2BasicRequest<VerifyIncludeParticipantByUserIdRequest> {
    private String namespaceName;
    private String seasonName;
    private Long season;
    private Long tier;
    private String seasonGatheringName;
    private String userId;
    private String verifyType;
    private String timeOffsetToken;
    private String duplicationAvoider;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public VerifyIncludeParticipantByUserIdRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getSeasonName() {
		return seasonName;
	}
	public void setSeasonName(String seasonName) {
		this.seasonName = seasonName;
	}
	public VerifyIncludeParticipantByUserIdRequest withSeasonName(String seasonName) {
		this.seasonName = seasonName;
		return this;
	}
	public Long getSeason() {
		return season;
	}
	public void setSeason(Long season) {
		this.season = season;
	}
	public VerifyIncludeParticipantByUserIdRequest withSeason(Long season) {
		this.season = season;
		return this;
	}
	public Long getTier() {
		return tier;
	}
	public void setTier(Long tier) {
		this.tier = tier;
	}
	public VerifyIncludeParticipantByUserIdRequest withTier(Long tier) {
		this.tier = tier;
		return this;
	}
	public String getSeasonGatheringName() {
		return seasonGatheringName;
	}
	public void setSeasonGatheringName(String seasonGatheringName) {
		this.seasonGatheringName = seasonGatheringName;
	}
	public VerifyIncludeParticipantByUserIdRequest withSeasonGatheringName(String seasonGatheringName) {
		this.seasonGatheringName = seasonGatheringName;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public VerifyIncludeParticipantByUserIdRequest withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public String getVerifyType() {
		return verifyType;
	}
	public void setVerifyType(String verifyType) {
		this.verifyType = verifyType;
	}
	public VerifyIncludeParticipantByUserIdRequest withVerifyType(String verifyType) {
		this.verifyType = verifyType;
		return this;
	}
	public String getTimeOffsetToken() {
		return timeOffsetToken;
	}
	public void setTimeOffsetToken(String timeOffsetToken) {
		this.timeOffsetToken = timeOffsetToken;
	}
	public VerifyIncludeParticipantByUserIdRequest withTimeOffsetToken(String timeOffsetToken) {
		this.timeOffsetToken = timeOffsetToken;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public VerifyIncludeParticipantByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static VerifyIncludeParticipantByUserIdRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new VerifyIncludeParticipantByUserIdRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withSeasonName(data.get("seasonName") == null || data.get("seasonName").isNull() ? null : data.get("seasonName").asText())
            .withSeason(data.get("season") == null || data.get("season").isNull() ? null : data.get("season").longValue())
            .withTier(data.get("tier") == null || data.get("tier").isNull() ? null : data.get("tier").longValue())
            .withSeasonGatheringName(data.get("seasonGatheringName") == null || data.get("seasonGatheringName").isNull() ? null : data.get("seasonGatheringName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withVerifyType(data.get("verifyType") == null || data.get("verifyType").isNull() ? null : data.get("verifyType").asText())
            .withTimeOffsetToken(data.get("timeOffsetToken") == null || data.get("timeOffsetToken").isNull() ? null : data.get("timeOffsetToken").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("seasonName", getSeasonName());
                put("season", getSeason());
                put("tier", getTier());
                put("seasonGatheringName", getSeasonGatheringName());
                put("userId", getUserId());
                put("verifyType", getVerifyType());
                put("timeOffsetToken", getTimeOffsetToken());
            }}
        );
    }
}