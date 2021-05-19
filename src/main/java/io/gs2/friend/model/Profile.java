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

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * プロフィール
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Profile implements IModel, Serializable, Comparable<Profile> {
	/** プロフィール */
	protected String profileId;

	/**
	 * プロフィールを取得
	 *
	 * @return プロフィール
	 */
	public String getProfileId() {
		return profileId;
	}

	/**
	 * プロフィールを設定
	 *
	 * @param profileId プロフィール
	 */
	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}

	/**
	 * プロフィールを設定
	 *
	 * @param profileId プロフィール
	 * @return this
	 */
	public Profile withProfileId(String profileId) {
		this.profileId = profileId;
		return this;
	}
	/** ユーザーID */
	protected String userId;

	/**
	 * ユーザーIDを取得
	 *
	 * @return ユーザーID
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * ユーザーIDを設定
	 *
	 * @param userId ユーザーID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * ユーザーIDを設定
	 *
	 * @param userId ユーザーID
	 * @return this
	 */
	public Profile withUserId(String userId) {
		this.userId = userId;
		return this;
	}
	/** 公開されるプロフィール */
	protected String publicProfile;

	/**
	 * 公開されるプロフィールを取得
	 *
	 * @return 公開されるプロフィール
	 */
	public String getPublicProfile() {
		return publicProfile;
	}

	/**
	 * 公開されるプロフィールを設定
	 *
	 * @param publicProfile 公開されるプロフィール
	 */
	public void setPublicProfile(String publicProfile) {
		this.publicProfile = publicProfile;
	}

	/**
	 * 公開されるプロフィールを設定
	 *
	 * @param publicProfile 公開されるプロフィール
	 * @return this
	 */
	public Profile withPublicProfile(String publicProfile) {
		this.publicProfile = publicProfile;
		return this;
	}
	/** フォロワー向けに公開されるプロフィール */
	protected String followerProfile;

	/**
	 * フォロワー向けに公開されるプロフィールを取得
	 *
	 * @return フォロワー向けに公開されるプロフィール
	 */
	public String getFollowerProfile() {
		return followerProfile;
	}

	/**
	 * フォロワー向けに公開されるプロフィールを設定
	 *
	 * @param followerProfile フォロワー向けに公開されるプロフィール
	 */
	public void setFollowerProfile(String followerProfile) {
		this.followerProfile = followerProfile;
	}

	/**
	 * フォロワー向けに公開されるプロフィールを設定
	 *
	 * @param followerProfile フォロワー向けに公開されるプロフィール
	 * @return this
	 */
	public Profile withFollowerProfile(String followerProfile) {
		this.followerProfile = followerProfile;
		return this;
	}
	/** フレンド向けに公開されるプロフィール */
	protected String friendProfile;

	/**
	 * フレンド向けに公開されるプロフィールを取得
	 *
	 * @return フレンド向けに公開されるプロフィール
	 */
	public String getFriendProfile() {
		return friendProfile;
	}

	/**
	 * フレンド向けに公開されるプロフィールを設定
	 *
	 * @param friendProfile フレンド向けに公開されるプロフィール
	 */
	public void setFriendProfile(String friendProfile) {
		this.friendProfile = friendProfile;
	}

	/**
	 * フレンド向けに公開されるプロフィールを設定
	 *
	 * @param friendProfile フレンド向けに公開されるプロフィール
	 * @return this
	 */
	public Profile withFriendProfile(String friendProfile) {
		this.friendProfile = friendProfile;
		return this;
	}
	/** 作成日時 */
	protected Long createdAt;

	/**
	 * 作成日時を取得
	 *
	 * @return 作成日時
	 */
	public Long getCreatedAt() {
		return createdAt;
	}

	/**
	 * 作成日時を設定
	 *
	 * @param createdAt 作成日時
	 */
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * 作成日時を設定
	 *
	 * @param createdAt 作成日時
	 * @return this
	 */
	public Profile withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	/** 最終更新日時 */
	protected Long updatedAt;

	/**
	 * 最終更新日時を取得
	 *
	 * @return 最終更新日時
	 */
	public Long getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * 最終更新日時を設定
	 *
	 * @param updatedAt 最終更新日時
	 */
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}

	/**
	 * 最終更新日時を設定
	 *
	 * @param updatedAt 最終更新日時
	 * @return this
	 */
	public Profile withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("profileId", this.getProfileId())
            .put("userId", this.getUserId())
            .put("publicProfile", this.getPublicProfile())
            .put("followerProfile", this.getFollowerProfile())
            .put("friendProfile", this.getFriendProfile())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        return body_;
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