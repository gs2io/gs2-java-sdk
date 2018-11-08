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

package io.gs2.ranking.model;

import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * ゲームモード
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class GameMode implements Serializable {

	/** ゲームモードGRN */
	private String gameModeId;

	/** ランキングテーブル */
	private String rankingTableId;

	/** オーナーID */
	private String ownerId;

	/** ゲームモード名 */
	private String gameMode;

	/** ランキング計算に昇順を適用するか */
	private Boolean asc;

	/** 集計間隔(分) */
	private Integer calcInterval;

	/** 集計処理中か否か */
	private Boolean calculating;

	/** スコア登録時 に実行されるGS2-Script */
	private String putScoreTriggerScript;

	/** スコア登録完了時 に実行されるGS2-Script */
	private String putScoreDoneTriggerScript;

	/** 集計処理完了時 に実行されるGS2-Script */
	private String calculateRankingDoneTriggerScript;

	/** 作成日時(エポック秒) */
	private Integer createAt;

	/** 更新日時(エポック秒) */
	private Integer updateAt;

	/** 最終集計日時(エポック秒) */
	private Integer lastCalcAt;


	/**
	 * ゲームモードGRNを取得
	 *
	 * @return ゲームモードGRN
	 */
	public String getGameModeId() {
		return gameModeId;
	}

	/**
	 * ゲームモードGRNを設定
	 *
	 * @param gameModeId ゲームモードGRN
	 */
	public void setGameModeId(String gameModeId) {
		this.gameModeId = gameModeId;
	}

	/**
	 * ランキングテーブルを取得
	 *
	 * @return ランキングテーブル
	 */
	public String getRankingTableId() {
		return rankingTableId;
	}

	/**
	 * ランキングテーブルを設定
	 *
	 * @param rankingTableId ランキングテーブル
	 */
	public void setRankingTableId(String rankingTableId) {
		this.rankingTableId = rankingTableId;
	}

	/**
	 * オーナーIDを取得
	 *
	 * @return オーナーID
	 */
	public String getOwnerId() {
		return ownerId;
	}

	/**
	 * オーナーIDを設定
	 *
	 * @param ownerId オーナーID
	 */
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	/**
	 * ゲームモード名を取得
	 *
	 * @return ゲームモード名
	 */
	public String getGameMode() {
		return gameMode;
	}

	/**
	 * ゲームモード名を設定
	 *
	 * @param gameMode ゲームモード名
	 */
	public void setGameMode(String gameMode) {
		this.gameMode = gameMode;
	}

	/**
	 * ランキング計算に昇順を適用するかを取得
	 *
	 * @return ランキング計算に昇順を適用するか
	 */
	public Boolean getAsc() {
		return asc;
	}

	/**
	 * ランキング計算に昇順を適用するかを設定
	 *
	 * @param asc ランキング計算に昇順を適用するか
	 */
	public void setAsc(Boolean asc) {
		this.asc = asc;
	}

	/**
	 * 集計間隔(分)を取得
	 *
	 * @return 集計間隔(分)
	 */
	public Integer getCalcInterval() {
		return calcInterval;
	}

	/**
	 * 集計間隔(分)を設定
	 *
	 * @param calcInterval 集計間隔(分)
	 */
	public void setCalcInterval(Integer calcInterval) {
		this.calcInterval = calcInterval;
	}

	/**
	 * 集計処理中か否かを取得
	 *
	 * @return 集計処理中か否か
	 */
	public Boolean getCalculating() {
		return calculating;
	}

	/**
	 * 集計処理中か否かを設定
	 *
	 * @param calculating 集計処理中か否か
	 */
	public void setCalculating(Boolean calculating) {
		this.calculating = calculating;
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
	 * 作成日時(エポック秒)を取得
	 *
	 * @return 作成日時(エポック秒)
	 */
	public Integer getCreateAt() {
		return createAt;
	}

	/**
	 * 作成日時(エポック秒)を設定
	 *
	 * @param createAt 作成日時(エポック秒)
	 */
	public void setCreateAt(Integer createAt) {
		this.createAt = createAt;
	}

	/**
	 * 更新日時(エポック秒)を取得
	 *
	 * @return 更新日時(エポック秒)
	 */
	public Integer getUpdateAt() {
		return updateAt;
	}

	/**
	 * 更新日時(エポック秒)を設定
	 *
	 * @param updateAt 更新日時(エポック秒)
	 */
	public void setUpdateAt(Integer updateAt) {
		this.updateAt = updateAt;
	}

	/**
	 * 最終集計日時(エポック秒)を取得
	 *
	 * @return 最終集計日時(エポック秒)
	 */
	public Integer getLastCalcAt() {
		return lastCalcAt;
	}

	/**
	 * 最終集計日時(エポック秒)を設定
	 *
	 * @param lastCalcAt 最終集計日時(エポック秒)
	 */
	public void setLastCalcAt(Integer lastCalcAt) {
		this.lastCalcAt = lastCalcAt;
	}

}