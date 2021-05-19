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

package io.gs2.friend.result;

import java.io.Serializable;
import org.json.JSONObject;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.gs2.core.model.*;
import io.gs2.friend.model.*;

/**
 * 公開プロフィールを取得 のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class GetPublicProfileResult implements IResult, Serializable {
	/** 公開プロフィール */
	private PublicProfile item;

	/**
	 * 公開プロフィールを取得
	 *
	 * @return 公開プロフィールを取得
	 */
	public PublicProfile getItem() {
		return item;
	}

	/**
	 * 公開プロフィールを設定
	 *
	 * @param item 公開プロフィールを取得
	 */
	public void setItem(PublicProfile item) {
		this.item = item;
	}
}