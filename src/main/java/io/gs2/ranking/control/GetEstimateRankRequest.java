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
public class GetEstimateRankRequest extends Gs2BasicRequest<GetEstimateRankRequest> {

	public static class Constant extends Gs2Ranking.Constant {
		public static final String FUNCTION = "GetEstimateRank";
	}

	/** ランキングテーブルの名前を指定します。 */
	private String rankingTableName;

	/** ゲームモードの名前を指定します。 */
	private String gameMode;

	/** スコア値 */
	private Long score;


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
	public GetEstimateRankRequest withRankingTableName(String rankingTableName) {
		setRankingTableName(rankingTableName);
		return this;
	}

	/**
	 * ゲームモードの名前を指定します。を取得
	 *
	 * @return ゲームモードの名前を指定します。
	 */
	public String getGameMode() {
		return gameMode;
	}

	/**
	 * ゲームモードの名前を指定します。を設定
	 *
	 * @param gameMode ゲームモードの名前を指定します。
	 */
	public void setGameMode(String gameMode) {
		this.gameMode = gameMode;
	}

	/**
	 * ゲームモードの名前を指定します。を設定
	 *
	 * @param gameMode ゲームモードの名前を指定します。
	 * @return this
	 */
	public GetEstimateRankRequest withGameMode(String gameMode) {
		setGameMode(gameMode);
		return this;
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
	 * スコア値を設定
	 *
	 * @param score スコア値
	 * @return this
	 */
	public GetEstimateRankRequest withScore(Long score) {
		setScore(score);
		return this;
	}

}