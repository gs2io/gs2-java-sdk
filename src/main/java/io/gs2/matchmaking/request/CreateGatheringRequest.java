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
import io.gs2.matchmaking.model.Attribute;
import io.gs2.matchmaking.model.Player;
import io.gs2.matchmaking.model.AttributeRange;
import io.gs2.matchmaking.model.CapacityOfRole;
import io.gs2.matchmaking.model.TimeSpan;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CreateGatheringRequest extends Gs2BasicRequest<CreateGatheringRequest> {
    private String namespaceName;
    private String accessToken;
    private Player player;
    private List<AttributeRange> attributeRanges;
    private List<CapacityOfRole> capacityOfRoles;
    private List<String> allowUserIds;
    private Long expiresAt;
    private TimeSpan expiresAtTimeSpan;
    private String duplicationAvoider;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public CreateGatheringRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public CreateGatheringRequest withAccessToken(String accessToken) {
		this.accessToken = accessToken;
		return this;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
	public CreateGatheringRequest withPlayer(Player player) {
		this.player = player;
		return this;
	}
	public List<AttributeRange> getAttributeRanges() {
		return attributeRanges;
	}
	public void setAttributeRanges(List<AttributeRange> attributeRanges) {
		this.attributeRanges = attributeRanges;
	}
	public CreateGatheringRequest withAttributeRanges(List<AttributeRange> attributeRanges) {
		this.attributeRanges = attributeRanges;
		return this;
	}
	public List<CapacityOfRole> getCapacityOfRoles() {
		return capacityOfRoles;
	}
	public void setCapacityOfRoles(List<CapacityOfRole> capacityOfRoles) {
		this.capacityOfRoles = capacityOfRoles;
	}
	public CreateGatheringRequest withCapacityOfRoles(List<CapacityOfRole> capacityOfRoles) {
		this.capacityOfRoles = capacityOfRoles;
		return this;
	}
	public List<String> getAllowUserIds() {
		return allowUserIds;
	}
	public void setAllowUserIds(List<String> allowUserIds) {
		this.allowUserIds = allowUserIds;
	}
	public CreateGatheringRequest withAllowUserIds(List<String> allowUserIds) {
		this.allowUserIds = allowUserIds;
		return this;
	}
	public Long getExpiresAt() {
		return expiresAt;
	}
	public void setExpiresAt(Long expiresAt) {
		this.expiresAt = expiresAt;
	}
	public CreateGatheringRequest withExpiresAt(Long expiresAt) {
		this.expiresAt = expiresAt;
		return this;
	}
	public TimeSpan getExpiresAtTimeSpan() {
		return expiresAtTimeSpan;
	}
	public void setExpiresAtTimeSpan(TimeSpan expiresAtTimeSpan) {
		this.expiresAtTimeSpan = expiresAtTimeSpan;
	}
	public CreateGatheringRequest withExpiresAtTimeSpan(TimeSpan expiresAtTimeSpan) {
		this.expiresAtTimeSpan = expiresAtTimeSpan;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public CreateGatheringRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static CreateGatheringRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new CreateGatheringRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withAccessToken(data.get("accessToken") == null || data.get("accessToken").isNull() ? null : data.get("accessToken").asText())
            .withPlayer(data.get("player") == null || data.get("player").isNull() ? null : Player.fromJson(data.get("player")))
            .withAttributeRanges(data.get("attributeRanges") == null || data.get("attributeRanges").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("attributeRanges").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return AttributeRange.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withCapacityOfRoles(data.get("capacityOfRoles") == null || data.get("capacityOfRoles").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("capacityOfRoles").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return CapacityOfRole.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withAllowUserIds(data.get("allowUserIds") == null || data.get("allowUserIds").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("allowUserIds").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.asText();
                }
            ).collect(Collectors.toList()))
            .withExpiresAt(data.get("expiresAt") == null || data.get("expiresAt").isNull() ? null : data.get("expiresAt").longValue())
            .withExpiresAtTimeSpan(data.get("expiresAtTimeSpan") == null || data.get("expiresAtTimeSpan").isNull() ? null : TimeSpan.fromJson(data.get("expiresAtTimeSpan")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("accessToken", getAccessToken());
                put("player", getPlayer() != null ? getPlayer().toJson() : null);
                put("attributeRanges", getAttributeRanges() == null ? null :
                    getAttributeRanges().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("capacityOfRoles", getCapacityOfRoles() == null ? null :
                    getCapacityOfRoles().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("allowUserIds", getAllowUserIds() == null ? null :
                    getAllowUserIds().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
                put("expiresAt", getExpiresAt());
                put("expiresAtTimeSpan", getExpiresAtTimeSpan() != null ? getExpiresAtTimeSpan().toJson() : null);
            }}
        );
    }
}