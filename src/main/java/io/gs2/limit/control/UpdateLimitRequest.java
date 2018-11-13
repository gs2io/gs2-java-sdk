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

package io.gs2.limit.control;

import org.json.JSONObject;
import java.util.List;
import io.gs2.limit.Gs2Limit;
import io.gs2.control.Gs2BasicRequest;

/**
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class UpdateLimitRequest extends Gs2BasicRequest<UpdateLimitRequest> {

	public static class Constant extends Gs2Limit.Constant {
		public static final String FUNCTION = "UpdateLimit";
	}

	/** 回数制限の名前を指定します。 */
	private String limitName;

	/** 説明文 */
	private String description;


	/**
	 * 回数制限の名前を指定します。を取得
	 *
	 * @return 回数制限の名前を指定します。
	 */
	public String getLimitName() {
		return limitName;
	}

	/**
	 * 回数制限の名前を指定します。を設定
	 *
	 * @param limitName 回数制限の名前を指定します。
	 */
	public void setLimitName(String limitName) {
		this.limitName = limitName;
	}

	/**
	 * 回数制限の名前を指定します。を設定
	 *
	 * @param limitName 回数制限の名前を指定します。
	 * @return this
	 */
	public UpdateLimitRequest withLimitName(String limitName) {
		setLimitName(limitName);
		return this;
	}

	/**
	 * 説明文を取得
	 *
	 * @return 説明文
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 説明文を設定
	 *
	 * @param description 説明文
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 説明文を設定
	 *
	 * @param description 説明文
	 * @return this
	 */
	public UpdateLimitRequest withDescription(String description) {
		setDescription(description);
		return this;
	}

}