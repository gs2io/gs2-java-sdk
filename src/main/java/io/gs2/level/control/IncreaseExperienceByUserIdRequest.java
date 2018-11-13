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

package io.gs2.level.control;

import org.json.JSONObject;
import java.util.List;
import io.gs2.level.model.*;
import io.gs2.level.Gs2Level;
import io.gs2.control.Gs2BasicRequest;

/**
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class IncreaseExperienceByUserIdRequest extends Gs2BasicRequest<IncreaseExperienceByUserIdRequest> {

	public static class Constant extends Gs2Level.Constant {
		public static final String FUNCTION = "IncreaseExperienceByUserId";
	}

	/** リソースプール */
	private String resourcePoolName;

	/** ステータス */
	private String userId;

	/** ステータスID */
	private String statusId;

	/** 経験値の加算量 */
	private Long value;


	/**
	 * リソースプールを取得
	 *
	 * @return リソースプール
	 */
	public String getResourcePoolName() {
		return resourcePoolName;
	}

	/**
	 * リソースプールを設定
	 *
	 * @param resourcePoolName リソースプール
	 */
	public void setResourcePoolName(String resourcePoolName) {
		this.resourcePoolName = resourcePoolName;
	}

	/**
	 * リソースプールを設定
	 *
	 * @param resourcePoolName リソースプール
	 * @return this
	 */
	public IncreaseExperienceByUserIdRequest withResourcePoolName(String resourcePoolName) {
		setResourcePoolName(resourcePoolName);
		return this;
	}

	/**
	 * ステータスを取得
	 *
	 * @return ステータス
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * ステータスを設定
	 *
	 * @param userId ステータス
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * ステータスを設定
	 *
	 * @param userId ステータス
	 * @return this
	 */
	public IncreaseExperienceByUserIdRequest withUserId(String userId) {
		setUserId(userId);
		return this;
	}

	/**
	 * ステータスIDを取得
	 *
	 * @return ステータスID
	 */
	public String getStatusId() {
		return statusId;
	}

	/**
	 * ステータスIDを設定
	 *
	 * @param statusId ステータスID
	 */
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	/**
	 * ステータスIDを設定
	 *
	 * @param statusId ステータスID
	 * @return this
	 */
	public IncreaseExperienceByUserIdRequest withStatusId(String statusId) {
		setStatusId(statusId);
		return this;
	}

	/**
	 * 経験値の加算量を取得
	 *
	 * @return 経験値の加算量
	 */
	public Long getValue() {
		return value;
	}

	/**
	 * 経験値の加算量を設定
	 *
	 * @param value 経験値の加算量
	 */
	public void setValue(Long value) {
		this.value = value;
	}

	/**
	 * 経験値の加算量を設定
	 *
	 * @param value 経験値の加算量
	 * @return this
	 */
	public IncreaseExperienceByUserIdRequest withValue(Long value) {
		setValue(value);
		return this;
	}

}