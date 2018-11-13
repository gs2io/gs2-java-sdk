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
 * スコア
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Score implements Serializable {

	/** ランキングテーブルGRN */
	private String rankingTableId;

	/** ゲームモード名 */
	private String gameMode;

	/** ユーザID */
	private String userId;

	/** スコア値 */
	private Long score;

	/** メタデータ */
	private String meta;

	/** 登録日時(エポック秒) */
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
	 * ユーザIDを取得
	 *
	 * @return ユーザID
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * ユーザIDを設定
	 *
	 * @param userId ユーザID
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * スコア値を取得
	 *
	 * @return スコア値
	 */
	public Long getScore() {
		return score;
	}

	/**
	 * スコア値を設定
	 *
	 * @param score スコア値
	 */
	public void setScore(Long score) {
		this.score = score;
	}

	/**
	 * メタデータを取得
	 *
	 * @return メタデータ
	 */
	public String getMeta() {
		return meta;
	}

	/**
	 * メタデータを設定
	 *
	 * @param meta メタデータ
	 */
	public void setMeta(String meta) {
		this.meta = meta;
	}

	/**
	 * 登録日時(エポック秒)を取得
	 *
	 * @return 登録日時(エポック秒)
	 */
	public Integer getUpdateAt() {
		return updateAt;
	}

	/**
	 * 登録日時(エポック秒)を設定
	 *
	 * @param updateAt 登録日時(エポック秒)
	 */
	public void setUpdateAt(Integer updateAt) {
		this.updateAt = updateAt;
	}

}