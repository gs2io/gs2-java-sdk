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
 * ユーザーIDを指定してフォローを取得 のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class GetFollowByUserIdResult implements IResult, Serializable {
	/** フォローしているユーザー */
	private FollowUser item;

	/**
	 * フォローしているユーザーを取得
	 *
	 * @return ユーザーIDを指定してフォローを取得
	 */
	public FollowUser getItem() {
		return item;
	}

	/**
	 * フォローしているユーザーを設定
	 *
	 * @param item ユーザーIDを指定してフォローを取得
	 */
	public void setItem(FollowUser item) {
		this.item = item;
	}
}