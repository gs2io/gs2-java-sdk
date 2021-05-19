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
 * フォローしているユーザー
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class FollowUser implements IModel, Serializable {
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
	public FollowUser withUserId(String userId) {
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
	public FollowUser withPublicProfile(String publicProfile) {
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
	public FollowUser withFollowerProfile(String followerProfile) {
		this.followerProfile = followerProfile;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("userId", this.getUserId())
            .put("publicProfile", this.getPublicProfile())
            .put("followerProfile", this.getFollowerProfile());
        return body_;
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