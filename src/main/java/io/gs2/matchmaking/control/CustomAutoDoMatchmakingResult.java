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

package io.gs2.matchmaking.control;

import org.json.JSONObject;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.gs2.matchmaking.model.*;

/**
 * @author Game Server Services, Inc.
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class CustomAutoDoMatchmakingResult {

	/** マッチメイキングが完了したか */
	private Boolean done;

	/** CustomAutoマッチメイキング ギャザリング */
	private CustomAutoGathering item;

	/** 検索を再開するためのコンテキスト */
	private String searchContext;


	/**
	 * マッチメイキングが完了したかを取得
	 *
	 * @return マッチメイキングが完了したか
	 */
	public Boolean getDone() {
		return done;
	}

	/**
	 * マッチメイキングが完了したかを設定
	 *
	 * @param done マッチメイキングが完了したか
	 */
	public void setDone(Boolean done) {
		this.done = done;
	}

	/**
	 * CustomAutoマッチメイキング ギャザリングを取得
	 *
	 * @return CustomAutoマッチメイキング ギャザリング
	 */
	public CustomAutoGathering getItem() {
		return item;
	}

	/**
	 * CustomAutoマッチメイキング ギャザリングを設定
	 *
	 * @param item CustomAutoマッチメイキング ギャザリング
	 */
	public void setItem(CustomAutoGathering item) {
		this.item = item;
	}

	/**
	 * 検索を再開するためのコンテキストを取得
	 *
	 * @return 検索を再開するためのコンテキスト
	 */
	public String getSearchContext() {
		return searchContext;
	}

	/**
	 * 検索を再開するためのコンテキストを設定
	 *
	 * @param searchContext 検索を再開するためのコンテキスト
	 */
	public void setSearchContext(String searchContext) {
		this.searchContext = searchContext;
	}

}