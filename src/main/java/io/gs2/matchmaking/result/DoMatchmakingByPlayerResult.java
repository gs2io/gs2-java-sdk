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

package io.gs2.matchmaking.result;

import java.io.Serializable;
import org.json.JSONObject;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.gs2.core.model.*;
import io.gs2.matchmaking.model.*;

/**
 * Player が参加できるギャザリングを探して参加する のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class DoMatchmakingByPlayerResult implements IResult, Serializable {
	/** ギャザリング */
	private Gathering item;
	/** マッチメイキングの状態を保持するトークン */
	private String matchmakingContextToken;

	/**
	 * ギャザリングを取得
	 *
	 * @return Player が参加できるギャザリングを探して参加する
	 */
	public Gathering getItem() {
		return item;
	}

	/**
	 * ギャザリングを設定
	 *
	 * @param item Player が参加できるギャザリングを探して参加する
	 */
	public void setItem(Gathering item) {
		this.item = item;
	}

	/**
	 * マッチメイキングの状態を保持するトークンを取得
	 *
	 * @return Player が参加できるギャザリングを探して参加する
	 */
	public String getMatchmakingContextToken() {
		return matchmakingContextToken;
	}

	/**
	 * マッチメイキングの状態を保持するトークンを設定
	 *
	 * @param matchmakingContextToken Player が参加できるギャザリングを探して参加する
	 */
	public void setMatchmakingContextToken(String matchmakingContextToken) {
		this.matchmakingContextToken = matchmakingContextToken;
	}
}