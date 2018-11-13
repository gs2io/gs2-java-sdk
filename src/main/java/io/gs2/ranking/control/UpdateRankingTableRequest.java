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
public class UpdateRankingTableRequest extends Gs2BasicRequest<UpdateRankingTableRequest> {

	public static class Constant extends Gs2Ranking.Constant {
		public static final String FUNCTION = "UpdateRankingTable";
	}

	/** ランキングテーブルの名前を指定します。 */
	private String rankingTableName;

	/** 説明文 */
	private String description;

	/** スコア登録時 */
	private String putScoreTriggerScript;

	/** スコア登録完了時 に実行されるGS2-Script */
	private String putScoreDoneTriggerScript;

	/** 集計処理完了時 に実行されるGS2-Script */
	private String calculateRankingDoneTriggerScript;


	/**
	 * ランキングテーブルの名前を指定します。を取得
	 *
	 * @return ランキングテーブルの名前を指定します。
	 */
	public String getRankingTableName() {
		return rankingTableName;
	}

	/**
	 * ランキングテーブルの名前を指定します。を設定
	 *
	 * @param rankingTableName ランキングテーブルの名前を指定します。
	 */
	public void setRankingTableName(String rankingTableName) {
		this.rankingTableName = rankingTableName;
	}

	/**
	 * ランキングテーブルの名前を指定します。を設定
	 *
	 * @param rankingTableName ランキングテーブルの名前を指定します。
	 * @return this
	 */
	public UpdateRankingTableRequest withRankingTableName(String rankingTableName) {
		setRankingTableName(rankingTableName);
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
	public UpdateRankingTableRequest withDescription(String description) {
		setDescription(description);
		return this;
	}

	/**
	 * スコア登録時を取得
	 *
	 * @return スコア登録時
	 */
	public String getPutScoreTriggerScript() {
		return putScoreTriggerScript;
	}

	/**
	 * スコア登録時を設定
	 *
	 * @param putScoreTriggerScript スコア登録時
	 */
	public void setPutScoreTriggerScript(String putScoreTriggerScript) {
		this.putScoreTriggerScript = putScoreTriggerScript;
	}

	/**
	 * スコア登録時を設定
	 *
	 * @param putScoreTriggerScript スコア登録時
	 * @return this
	 */
	public UpdateRankingTableRequest withPutScoreTriggerScript(String putScoreTriggerScript) {
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
	public UpdateRankingTableRequest withPutScoreDoneTriggerScript(String putScoreDoneTriggerScript) {
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
	public UpdateRankingTableRequest withCalculateRankingDoneTriggerScript(String calculateRankingDoneTriggerScript) {
		setCalculateRankingDoneTriggerScript(calculateRankingDoneTriggerScript);
		return this;
	}

}