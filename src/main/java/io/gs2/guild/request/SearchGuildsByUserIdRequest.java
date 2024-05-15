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

package io.gs2.guild.request;

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
public class SearchGuildsByUserIdRequest extends Gs2BasicRequest<SearchGuildsByUserIdRequest> {
    private String namespaceName;
    private String guildModelName;
    private String userId;
    private String displayName;
    private List<Integer> attributes1;
    private List<Integer> attributes2;
    private List<Integer> attributes3;
    private List<Integer> attributes4;
    private List<Integer> attributes5;
    private List<String> joinPolicies;
    private Boolean includeFullMembersGuild;
    private String pageToken;
    private Integer limit;
    private String timeOffsetToken;
    private String duplicationAvoider;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public SearchGuildsByUserIdRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getGuildModelName() {
		return guildModelName;
	}
	public void setGuildModelName(String guildModelName) {
		this.guildModelName = guildModelName;
	}
	public SearchGuildsByUserIdRequest withGuildModelName(String guildModelName) {
		this.guildModelName = guildModelName;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public SearchGuildsByUserIdRequest withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public SearchGuildsByUserIdRequest withDisplayName(String displayName) {
		this.displayName = displayName;
		return this;
	}
	public List<Integer> getAttributes1() {
		return attributes1;
	}
	public void setAttributes1(List<Integer> attributes1) {
		this.attributes1 = attributes1;
	}
	public SearchGuildsByUserIdRequest withAttributes1(List<Integer> attributes1) {
		this.attributes1 = attributes1;
		return this;
	}
	public List<Integer> getAttributes2() {
		return attributes2;
	}
	public void setAttributes2(List<Integer> attributes2) {
		this.attributes2 = attributes2;
	}
	public SearchGuildsByUserIdRequest withAttributes2(List<Integer> attributes2) {
		this.attributes2 = attributes2;
		return this;
	}
	public List<Integer> getAttributes3() {
		return attributes3;
	}
	public void setAttributes3(List<Integer> attributes3) {
		this.attributes3 = attributes3;
	}
	public SearchGuildsByUserIdRequest withAttributes3(List<Integer> attributes3) {
		this.attributes3 = attributes3;
		return this;
	}
	public List<Integer> getAttributes4() {
		return attributes4;
	}
	public void setAttributes4(List<Integer> attributes4) {
		this.attributes4 = attributes4;
	}
	public SearchGuildsByUserIdRequest withAttributes4(List<Integer> attributes4) {
		this.attributes4 = attributes4;
		return this;
	}
	public List<Integer> getAttributes5() {
		return attributes5;
	}
	public void setAttributes5(List<Integer> attributes5) {
		this.attributes5 = attributes5;
	}
	public SearchGuildsByUserIdRequest withAttributes5(List<Integer> attributes5) {
		this.attributes5 = attributes5;
		return this;
	}
	public List<String> getJoinPolicies() {
		return joinPolicies;
	}
	public void setJoinPolicies(List<String> joinPolicies) {
		this.joinPolicies = joinPolicies;
	}
	public SearchGuildsByUserIdRequest withJoinPolicies(List<String> joinPolicies) {
		this.joinPolicies = joinPolicies;
		return this;
	}
	public Boolean getIncludeFullMembersGuild() {
		return includeFullMembersGuild;
	}
	public void setIncludeFullMembersGuild(Boolean includeFullMembersGuild) {
		this.includeFullMembersGuild = includeFullMembersGuild;
	}
	public SearchGuildsByUserIdRequest withIncludeFullMembersGuild(Boolean includeFullMembersGuild) {
		this.includeFullMembersGuild = includeFullMembersGuild;
		return this;
	}
	public String getPageToken() {
		return pageToken;
	}
	public void setPageToken(String pageToken) {
		this.pageToken = pageToken;
	}
	public SearchGuildsByUserIdRequest withPageToken(String pageToken) {
		this.pageToken = pageToken;
		return this;
	}
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public SearchGuildsByUserIdRequest withLimit(Integer limit) {
		this.limit = limit;
		return this;
	}
	public String getTimeOffsetToken() {
		return timeOffsetToken;
	}
	public void setTimeOffsetToken(String timeOffsetToken) {
		this.timeOffsetToken = timeOffsetToken;
	}
	public SearchGuildsByUserIdRequest withTimeOffsetToken(String timeOffsetToken) {
		this.timeOffsetToken = timeOffsetToken;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public SearchGuildsByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static SearchGuildsByUserIdRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new SearchGuildsByUserIdRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withGuildModelName(data.get("guildModelName") == null || data.get("guildModelName").isNull() ? null : data.get("guildModelName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withDisplayName(data.get("displayName") == null || data.get("displayName").isNull() ? null : data.get("displayName").asText())
            .withAttributes1(data.get("attributes1") == null || data.get("attributes1").isNull() ? new ArrayList<Integer>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("attributes1").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.intValue();
                }
            ).collect(Collectors.toList()))
            .withAttributes2(data.get("attributes2") == null || data.get("attributes2").isNull() ? new ArrayList<Integer>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("attributes2").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.intValue();
                }
            ).collect(Collectors.toList()))
            .withAttributes3(data.get("attributes3") == null || data.get("attributes3").isNull() ? new ArrayList<Integer>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("attributes3").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.intValue();
                }
            ).collect(Collectors.toList()))
            .withAttributes4(data.get("attributes4") == null || data.get("attributes4").isNull() ? new ArrayList<Integer>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("attributes4").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.intValue();
                }
            ).collect(Collectors.toList()))
            .withAttributes5(data.get("attributes5") == null || data.get("attributes5").isNull() ? new ArrayList<Integer>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("attributes5").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.intValue();
                }
            ).collect(Collectors.toList()))
            .withJoinPolicies(data.get("joinPolicies") == null || data.get("joinPolicies").isNull() ? new ArrayList<String>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("joinPolicies").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.asText();
                }
            ).collect(Collectors.toList()))
            .withIncludeFullMembersGuild(data.get("includeFullMembersGuild") == null || data.get("includeFullMembersGuild").isNull() ? null : data.get("includeFullMembersGuild").booleanValue())
            .withPageToken(data.get("pageToken") == null || data.get("pageToken").isNull() ? null : data.get("pageToken").asText())
            .withLimit(data.get("limit") == null || data.get("limit").isNull() ? null : data.get("limit").intValue())
            .withTimeOffsetToken(data.get("timeOffsetToken") == null || data.get("timeOffsetToken").isNull() ? null : data.get("timeOffsetToken").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("guildModelName", getGuildModelName());
                put("userId", getUserId());
                put("displayName", getDisplayName());
                put("attributes1", getAttributes1() == null ? new ArrayList<Integer>() :
                    getAttributes1().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
                put("attributes2", getAttributes2() == null ? new ArrayList<Integer>() :
                    getAttributes2().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
                put("attributes3", getAttributes3() == null ? new ArrayList<Integer>() :
                    getAttributes3().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
                put("attributes4", getAttributes4() == null ? new ArrayList<Integer>() :
                    getAttributes4().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
                put("attributes5", getAttributes5() == null ? new ArrayList<Integer>() :
                    getAttributes5().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
                put("joinPolicies", getJoinPolicies() == null ? new ArrayList<String>() :
                    getJoinPolicies().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
                put("includeFullMembersGuild", getIncludeFullMembersGuild());
                put("pageToken", getPageToken());
                put("limit", getLimit());
                put("timeOffsetToken", getTimeOffsetToken());
            }}
        );
    }
}