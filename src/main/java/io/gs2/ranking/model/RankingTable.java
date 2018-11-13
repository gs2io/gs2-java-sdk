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
 * ランキングテーブル
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class RankingTable implements Serializable {

	/** ランキングテーブルGRN */
	private String rankingTableId;

	/** オーナーID */
	private String ownerId;

	/** ランキングテーブル名 */
	private String name;

	/** 説明文 */
	private String description;

	/** スコア登録時 に実行されるGS2-Script */
	private String putScoreTriggerScript;

	/** スコア登録完了時 に実行されるGS2-Script */
	private String putScoreDoneTriggerScript;

	/** 集計処理完了時 に実行されるGS2-Script */
	private String calculateRankingDoneTriggerScript;

	/** 作成日時(エポック秒) */
	private Integer createAt;

	/** 最終更新日時(エポック秒) */
	private Integer updateAt;


	/**
	 * ランキングテーブルGRNを取得
	 *
	 * @return ランキングテーブルGRN
	 */
	public String getRankingTableId() {
		return rankingTableId;
	}

	/**
	 * ランキングテーブルGRNを設定
	 *
	 * @param rankingTableId ランキングテーブルGRN
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
	 * ランキングテーブル名を取得
	 *
	 * @return ランキングテーブル名
	 */
	public String getName() {
		return name;
	}

	/**
	 * ランキングテーブル名を設定
	 *
	 * @param name ランキングテーブル名
	 */
	public void setName(String name) {
		this.name = name;
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
	 * 最終更新日時(エポック秒)を取得
	 *
	 * @return 最終更新日時(エポック秒)
	 */
	public Integer getUpdateAt() {
		return updateAt;
	}

	/**
	 * 最終更新日時(エポック秒)を設定
	 *
	 * @param updateAt 最終更新日時(エポック秒)
	 */
	public void setUpdateAt(Integer updateAt) {
		this.updateAt = updateAt;
	}

}