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

package io.gs2.inGamePushNotification.control;

import org.json.JSONObject;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.gs2.inGamePushNotification.model.*;

/**
 * @author Game Server Services, Inc.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class SetFirebaseTokenResult {

	/** Firebase 通知トークン */
	private FirebaseToken item;


	/**
	 * Firebase 通知トークンを取得
	 *
	 * @return Firebase 通知トークン
	 */
	public FirebaseToken getItem() {
		return item;
	}

	/**
	 * Firebase 通知トークンを設定
	 *
	 * @param item Firebase 通知トークン
	 */
	public void setItem(FirebaseToken item) {
		this.item = item;
	}

}