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

package io.gs2.jobQueue.result;

import java.io.Serializable;
import org.json.JSONObject;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.gs2.core.model.*;
import io.gs2.jobQueue.model.*;

/**
 * デッドレタージョブの一覧を取得 のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class DescribeDeadLetterJobsByUserIdResult implements IResult, Serializable {
	/** デッドレタージョブのリスト */
	private List<DeadLetterJob> items;
	/** リストの続きを取得するためのページトークン */
	private String nextPageToken;

	/**
	 * デッドレタージョブのリストを取得
	 *
	 * @return デッドレタージョブの一覧を取得
	 */
	public List<DeadLetterJob> getItems() {
		return items;
	}

	/**
	 * デッドレタージョブのリストを設定
	 *
	 * @param items デッドレタージョブの一覧を取得
	 */
	public void setItems(List<DeadLetterJob> items) {
		this.items = items;
	}

	/**
	 * リストの続きを取得するためのページトークンを取得
	 *
	 * @return デッドレタージョブの一覧を取得
	 */
	public String getNextPageToken() {
		return nextPageToken;
	}

	/**
	 * リストの続きを取得するためのページトークンを設定
	 *
	 * @param nextPageToken デッドレタージョブの一覧を取得
	 */
	public void setNextPageToken(String nextPageToken) {
		this.nextPageToken = nextPageToken;
	}
}