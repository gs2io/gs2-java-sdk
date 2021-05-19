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
 * 投票用紙を取得します。 のレスポンスモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class GetBallotResult implements IResult, Serializable {
	/** 投票用紙 */
	private Ballot item;
	/** 署名対象のデータ */
	private String body;
	/** 署名データ */
	private String signature;

	/**
	 * 投票用紙を取得
	 *
	 * @return 投票用紙を取得します。
	 */
	public Ballot getItem() {
		return item;
	}

	/**
	 * 投票用紙を設定
	 *
	 * @param item 投票用紙を取得します。
	 */
	public void setItem(Ballot item) {
		this.item = item;
	}

	/**
	 * 署名対象のデータを取得
	 *
	 * @return 投票用紙を取得します。
	 */
	public String getBody() {
		return body;
	}

	/**
	 * 署名対象のデータを設定
	 *
	 * @param body 投票用紙を取得します。
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * 署名データを取得
	 *
	 * @return 投票用紙を取得します。
	 */
	public String getSignature() {
		return signature;
	}

	/**
	 * 署名データを設定
	 *
	 * @param signature 投票用紙を取得します。
	 */
	public void setSignature(String signature) {
		this.signature = signature;
	}
}