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

package io.gs2.friend.model;

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
public class Profile implements IModel, Serializable, Comparable<Profile> {
	private String profileId;
	private String userId;
	private String publicProfile;
	private String followerProfile;
	private String friendProfile;
	private Long createdAt;
	private Long updatedAt;
	public String getProfileId() {
		return profileId;
	}
	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}
	public Profile withProfileId(String profileId) {
		this.profileId = profileId;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Profile withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public String getPublicProfile() {
		return publicProfile;
	}
	public void setPublicProfile(String publicProfile) {
		this.publicProfile = publicProfile;
	}
	public Profile withPublicProfile(String publicProfile) {
		this.publicProfile = publicProfile;
		return this;
	}
	public String getFollowerProfile() {
		return followerProfile;
	}
	public void setFollowerProfile(String followerProfile) {
		this.followerProfile = followerProfile;
	}
	public Profile withFollowerProfile(String followerProfile) {
		this.followerProfile = followerProfile;
		return this;
	}
	public String getFriendProfile() {
		return friendProfile;
	}
	public void setFriendProfile(String friendProfile) {
		this.friendProfile = friendProfile;
	}
	public Profile withFriendProfile(String friendProfile) {
		this.friendProfile = friendProfile;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public Profile withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	public Long getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Profile withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public static Profile fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Profile()
            .withProfileId(data.get("profileId") == null || data.get("profileId").isNull() ? null : data.get("profileId").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withPublicProfile(data.get("publicProfile") == null || data.get("publicProfile").isNull() ? null : data.get("publicProfile").asText())
            .withFollowerProfile(data.get("followerProfile") == null || data.get("followerProfile").isNull() ? null : data.get("followerProfile").asText())
            .withFriendProfile(data.get("friendProfile") == null || data.get("friendProfile").isNull() ? null : data.get("friendProfile").asText())
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("profileId", getProfileId());
                put("userId", getUserId());
                put("publicProfile", getPublicProfile());
                put("followerProfile", getFollowerProfile());
                put("friendProfile", getFriendProfile());
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
            }}
        );
    }

	@Override
	public int compareTo(Profile o) {
		return profileId.compareTo(o.profileId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.profileId == null) ? 0 : this.profileId.hashCode());
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.publicProfile == null) ? 0 : this.publicProfile.hashCode());
        result = prime * result + ((this.followerProfile == null) ? 0 : this.followerProfile.hashCode());
        result = prime * result + ((this.friendProfile == null) ? 0 : this.friendProfile.hashCode());
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
		Profile other = (Profile) o;
		if (profileId == null) {
			return other.profileId == null;
		} else if (!profileId.equals(other.profileId)) {
			return false;
		}
		if (userId == null) {
			return other.userId == null;
		} else if (!userId.equals(other.userId)) {
			return false;
		}
		if (publicProfile == null) {
			return other.publicProfile == null;
		} else if (!publicProfile.equals(other.publicProfile)) {
			return false;
		}
		if (followerProfile == null) {
			return other.followerProfile == null;
		} else if (!followerProfile.equals(other.followerProfile)) {
			return false;
		}
		if (friendProfile == null) {
			return other.friendProfile == null;
		} else if (!friendProfile.equals(other.friendProfile)) {
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