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

package io.gs2.ranking.control;

import org.json.JSONObject;
import java.util.List;
import io.gs2.ranking.Gs2Ranking;
import io.gs2.control.Gs2BasicRequest;

/**
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateRankingTableRequest extends Gs2BasicRequest<CreateRankingTableRequest> {

	public static class Constant extends Gs2Ranking.Constant {
		public static final String FUNCTION = "CreateRankingTable";
	}

	/** ランキングテーブルの名前 */
	private String name;

	/** 説明文 */
	private String description;

	/** スコア登録時 に実行されるGS2-Script */
	private String putScoreTriggerScript;

	/** スコア登録完了時 に実行されるGS2-Script */
	private String putScoreDoneTriggerScript;

	/** 集計処理完了時 に実行されるGS2-Script */
	private String calculateRankingDoneTriggerScript;


	/**
	 * ランキングテーブルの名前を取得
	 *
	 * @return ランキングテーブルの名前
	 */
	public String getName() {
		return name;
	}

	/**
	 * ランキングテーブルの名前を設定
	 *
	 * @param name ランキングテーブルの名前
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * ランキングテーブルの名前を設定
	 *
	 * @param name ランキングテーブルの名前
	 * @return this
	 */
	public CreateRankingTableRequest withName(String name) {
		setName(name);
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
	public CreateRankingTableRequest withDescription(String description) {
		setDescription(description);
		return this;
	}

	/**
	 * スコア登録時 に実行されるGS2-Scriptを取得
	 *
	 * @return スコア登録時 に実行されるGS2-Script
	 */
	public String getPutScoreTriggerScript() {
		return putScoreTriggerScript;
	}

	/**
	 * スコア登録時 に実行されるGS2-Scriptを設定
	 *
	 * @param putScoreTriggerScript スコア登録時 に実行されるGS2-Script
	 */
	public void setPutScoreTriggerScript(String putScoreTriggerScript) {
		this.putScoreTriggerScript = putScoreTriggerScript;
	}

	/**
	 * スコア登録時 に実行されるGS2-Scriptを設定
	 *
	 * @param putScoreTriggerScript スコア登録時 に実行されるGS2-Script
	 * @return this
	 */
	public CreateRankingTableRequest withPutScoreTriggerScript(String putScoreTriggerScript) {
		setPutScoreTriggerScript(putScoreTriggerScript);
		return this;
	}

	/**
	 * スコア登録完了時 に実行されるGS2-Scriptを取得
	 *
	 * @return スコア登録完了時 に実行されるGS2-Script
	 */
	public String getPutScoreDoneTriggerScript() {
		return putScoreDoneTriggerScript;
	}

	/**
	 * スコア登録完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param putScoreDoneTriggerScript スコア登録完了時 に実行されるGS2-Script
	 */
	public void setPutScoreDoneTriggerScript(String putScoreDoneTriggerScript) {
		this.putScoreDoneTriggerScript = putScoreDoneTriggerScript;
	}

	/**
	 * スコア登録完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param putScoreDoneTriggerScript スコア登録完了時 に実行されるGS2-Script
	 * @return this
	 */
	public CreateRankingTableRequest withPutScoreDoneTriggerScript(String putScoreDoneTriggerScript) {
		setPutScoreDoneTriggerScript(putScoreDoneTriggerScript);
		return this;
	}

	/**
	 * 集計処理完了時 に実行されるGS2-Scriptを取得
	 *
	 * @return 集計処理完了時 に実行されるGS2-Script
	 */
	public String getCalculateRankingDoneTriggerScript() {
		return calculateRankingDoneTriggerScript;
	}

	/**
	 * 集計処理完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param calculateRankingDoneTriggerScript 集計処理完了時 に実行されるGS2-Script
	 */
	public void setCalculateRankingDoneTriggerScript(String calculateRankingDoneTriggerScript) {
		this.calculateRankingDoneTriggerScript = calculateRankingDoneTriggerScript;
	}

	/**
	 * 集計処理完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param calculateRankingDoneTriggerScript 集計処理完了時 に実行されるGS2-Script
	 * @return this
	 */
	public CreateRankingTableRequest withCalculateRankingDoneTriggerScript(String calculateRankingDoneTriggerScript) {
		setCalculateRankingDoneTriggerScript(calculateRankingDoneTriggerScript);
		return this;
	}

}