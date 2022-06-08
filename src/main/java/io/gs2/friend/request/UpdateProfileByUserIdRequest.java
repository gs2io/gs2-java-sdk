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

package io.gs2.friend.request;

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
public class UpdateProfileByUserIdRequest extends Gs2BasicRequest<UpdateProfileByUserIdRequest> {
    private String namespaceName;
    private String userId;
    private String publicProfile;
    private String followerProfile;
    private String friendProfile;
    private String duplicationAvoider;
	public String getNamespaceName() {
		return namespaceName;
	}
	public void setNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
	}
	public UpdateProfileByUserIdRequest withNamespaceName(String namespaceName) {
		this.namespaceName = namespaceName;
		return this;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public UpdateProfileByUserIdRequest withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	public String getPublicProfile() {
		return publicProfile;
	}
	public void setPublicProfile(String publicProfile) {
		this.publicProfile = publicProfile;
	}
	public UpdateProfileByUserIdRequest withPublicProfile(String publicProfile) {
		this.publicProfile = publicProfile;
		return this;
	}
	public String getFollowerProfile() {
		return followerProfile;
	}
	public void setFollowerProfile(String followerProfile) {
		this.followerProfile = followerProfile;
	}
	public UpdateProfileByUserIdRequest withFollowerProfile(String followerProfile) {
		this.followerProfile = followerProfile;
		return this;
	}
	public String getFriendProfile() {
		return friendProfile;
	}
	public void setFriendProfile(String friendProfile) {
		this.friendProfile = friendProfile;
	}
	public UpdateProfileByUserIdRequest withFriendProfile(String friendProfile) {
		this.friendProfile = friendProfile;
		return this;
	}

	public String getDuplicationAvoider() {
		return duplicationAvoider;
	}

	public void setDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
	}

	public UpdateProfileByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
		this.duplicationAvoider = duplicationAvoider;
		return this;
	}

    public static UpdateProfileByUserIdRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new UpdateProfileByUserIdRequest()
            .withNamespaceName(data.get("namespaceName") == null || data.get("namespaceName").isNull() ? null : data.get("namespaceName").asText())
            .withUserId(data.get("userId") == null || data.get("userId").isNull() ? null : data.get("userId").asText())
            .withPublicProfile(data.get("publicProfile") == null || data.get("publicProfile").isNull() ? null : data.get("publicProfile").asText())
            .withFollowerProfile(data.get("followerProfile") == null || data.get("followerProfile").isNull() ? null : data.get("followerProfile").asText())
            .withFriendProfile(data.get("friendProfile") == null || data.get("friendProfile").isNull() ? null : data.get("friendProfile").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceName", getNamespaceName());
                put("userId", getUserId());
                put("publicProfile", getPublicProfile());
                put("followerProfile", getFollowerProfile());
                put("friendProfile", getFriendProfile());
            }}
        );
    }
}