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
public class FollowUser implements IModel, Serializable {
	private String userId;
	private String publicProfile;
	private String followerProfile;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public FollowUser withUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public String getPublicProfile() {
		return publicProfile;
	}

	public void setPublicProfile(String publicProfile) {
		this.publicProfile = publicProfile;
	}

	public FollowUser withPublicProfile(String publicProfile) {
		this.publicProfile = publicProfile;
		return this;
	}

	public String getFollowerProfile() {
		return followerProfile;
	}

	public void setFollowerProfile(String followerProfile) {
		this.followerProfile = followerProfile;
	}

	public FollowUser withFollowerProfile(String followerProfile) {
		this.followerProfile = followerProfile;
		return this;
	}

    public static FollowUser fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new FollowUser()
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withPublicProfile(data.get("publicProfile") == null || data.get("publicProfile").isNull() ? null : data.get("publicProfile").asText())
            .withFollowerProfile(data.get("followerProfile") == null || data.get("followerProfile").isNull() ? null : data.get("followerProfile").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("userId", getUserId());
                put("publicProfile", getPublicProfile());
                put("followerProfile", getFollowerProfile());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.userId == null) ? 0 : this.userId.hashCode());
        result = prime * result + ((this.publicProfile == null) ? 0 : this.publicProfile.hashCode());
        result = prime * result + ((this.followerProfile == null) ? 0 : this.followerProfile.hashCode());
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
		FollowUser other = (FollowUser) o;
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
		return true;
	}
}