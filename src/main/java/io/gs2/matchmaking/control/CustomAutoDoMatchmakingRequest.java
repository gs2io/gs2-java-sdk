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
import io.gs2.matchmaking.Gs2Matchmaking;
import io.gs2.control.Gs2UserRequest;

/**
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CustomAutoDoMatchmakingRequest extends Gs2UserRequest<CustomAutoDoMatchmakingRequest> {

	public static class Constant extends Gs2Matchmaking.Constant {
		public static final String FUNCTION = "DoMatchmaking";
	}

	/** マッチメイキングの名前を指定します。 */
	private String matchmakingName;

	/** ギャザリングを新規作成する場合の属性値1 */
	private Integer attribute1;

	/** ギャザリングを新規作成する場合の属性値2 */
	private Integer attribute2;

	/** ギャザリングを新規作成する場合の属性値3 */
	private Integer attribute3;

	/** ギャザリングを新規作成する場合の属性値4 */
	private Integer attribute4;

	/** ギャザリングを新規作成する場合の属性値5 */
	private Integer attribute5;

	/** 既存のギャザリングに参加する対象とする属性値1の最小値 */
	private Integer searchAttribute1Min;

	/** 既存のギャザリングに参加する対象とする属性値2の最小値 */
	private Integer searchAttribute2Min;

	/** 既存のギャザリングに参加する対象とする属性値3の最小値 */
	private Integer searchAttribute3Min;

	/** 既存のギャザリングに参加する対象とする属性値4の最小値 */
	private Integer searchAttribute4Min;

	/** 既存のギャザリングに参加する対象とする属性値5の最小値 */
	private Integer searchAttribute5Min;

	/** 既存のギャザリングに参加する対象とする属性値1の最大値 */
	private Integer searchAttribute1Max;

	/** 既存のギャザリングに参加する対象とする属性値2の最大値 */
	private Integer searchAttribute2Max;

	/** 既存のギャザリングに参加する対象とする属性値3の最大値 */
	private Integer searchAttribute3Max;

	/** 既存のギャザリングに参加する対象とする属性値4の最大値 */
	private Integer searchAttribute4Max;

	/** 既存のギャザリングに参加する対象とする属性値5の最大値 */
	private Integer searchAttribute5Max;

	/** 中断された検索を再開するためのコンテキスト */
	private String searchContext;


	/**
	 * マッチメイキングの名前を指定します。を取得
	 *
	 * @return マッチメイキングの名前を指定します。
	 */
	public String getMatchmakingName() {
		return matchmakingName;
	}

	/**
	 * マッチメイキングの名前を指定します。を設定
	 *
	 * @param matchmakingName マッチメイキングの名前を指定します。
	 */
	public void setMatchmakingName(String matchmakingName) {
		this.matchmakingName = matchmakingName;
	}

	/**
	 * マッチメイキングの名前を指定します。を設定
	 *
	 * @param matchmakingName マッチメイキングの名前を指定します。
	 * @return this
	 */
	public CustomAutoDoMatchmakingRequest withMatchmakingName(String matchmakingName) {
		setMatchmakingName(matchmakingName);
		return this;
	}

	/**
	 * ギャザリングを新規作成する場合の属性値1を取得
	 *
	 * @return ギャザリングを新規作成する場合の属性値1
	 */
	public Integer getAttribute1() {
		return attribute1;
	}

	/**
	 * ギャザリングを新規作成する場合の属性値1を設定
	 *
	 * @param attribute1 ギャザリングを新規作成する場合の属性値1
	 */
	public void setAttribute1(Integer attribute1) {
		this.attribute1 = attribute1;
	}

	/**
	 * ギャザリングを新規作成する場合の属性値1を設定
	 *
	 * @param attribute1 ギャザリングを新規作成する場合の属性値1
	 * @return this
	 */
	public CustomAutoDoMatchmakingRequest withAttribute1(Integer attribute1) {
		setAttribute1(attribute1);
		return this;
	}

	/**
	 * ギャザリングを新規作成する場合の属性値2を取得
	 *
	 * @return ギャザリングを新規作成する場合の属性値2
	 */
	public Integer getAttribute2() {
		return attribute2;
	}

	/**
	 * ギャザリングを新規作成する場合の属性値2を設定
	 *
	 * @param attribute2 ギャザリングを新規作成する場合の属性値2
	 */
	public void setAttribute2(Integer attribute2) {
		this.attribute2 = attribute2;
	}

	/**
	 * ギャザリングを新規作成する場合の属性値2を設定
	 *
	 * @param attribute2 ギャザリングを新規作成する場合の属性値2
	 * @return this
	 */
	public CustomAutoDoMatchmakingRequest withAttribute2(Integer attribute2) {
		setAttribute2(attribute2);
		return this;
	}

	/**
	 * ギャザリングを新規作成する場合の属性値3を取得
	 *
	 * @return ギャザリングを新規作成する場合の属性値3
	 */
	public Integer getAttribute3() {
		return attribute3;
	}

	/**
	 * ギャザリングを新規作成する場合の属性値3を設定
	 *
	 * @param attribute3 ギャザリングを新規作成する場合の属性値3
	 */
	public void setAttribute3(Integer attribute3) {
		this.attribute3 = attribute3;
	}

	/**
	 * ギャザリングを新規作成する場合の属性値3を設定
	 *
	 * @param attribute3 ギャザリングを新規作成する場合の属性値3
	 * @return this
	 */
	public CustomAutoDoMatchmakingRequest withAttribute3(Integer attribute3) {
		setAttribute3(attribute3);
		return this;
	}

	/**
	 * ギャザリングを新規作成する場合の属性値4を取得
	 *
	 * @return ギャザリングを新規作成する場合の属性値4
	 */
	public Integer getAttribute4() {
		return attribute4;
	}

	/**
	 * ギャザリングを新規作成する場合の属性値4を設定
	 *
	 * @param attribute4 ギャザリングを新規作成する場合の属性値4
	 */
	public void setAttribute4(Integer attribute4) {
		this.attribute4 = attribute4;
	}

	/**
	 * ギャザリングを新規作成する場合の属性値4を設定
	 *
	 * @param attribute4 ギャザリングを新規作成する場合の属性値4
	 * @return this
	 */
	public CustomAutoDoMatchmakingRequest withAttribute4(Integer attribute4) {
		setAttribute4(attribute4);
		return this;
	}

	/**
	 * ギャザリングを新規作成する場合の属性値5を取得
	 *
	 * @return ギャザリングを新規作成する場合の属性値5
	 */
	public Integer getAttribute5() {
		return attribute5;
	}

	/**
	 * ギャザリングを新規作成する場合の属性値5を設定
	 *
	 * @param attribute5 ギャザリングを新規作成する場合の属性値5
	 */
	public void setAttribute5(Integer attribute5) {
		this.attribute5 = attribute5;
	}

	/**
	 * ギャザリングを新規作成する場合の属性値5を設定
	 *
	 * @param attribute5 ギャザリングを新規作成する場合の属性値5
	 * @return this
	 */
	public CustomAutoDoMatchmakingRequest withAttribute5(Integer attribute5) {
		setAttribute5(attribute5);
		return this;
	}

	/**
	 * 既存のギャザリングに参加する対象とする属性値1の最小値を取得
	 *
	 * @return 既存のギャザリングに参加する対象とする属性値1の最小値
	 */
	public Integer getSearchAttribute1Min() {
		return searchAttribute1Min;
	}

	/**
	 * 既存のギャザリングに参加する対象とする属性値1の最小値を設定
	 *
	 * @param searchAttribute1Min 既存のギャザリングに参加する対象とする属性値1の最小値
	 */
	public void setSearchAttribute1Min(Integer searchAttribute1Min) {
		this.searchAttribute1Min = searchAttribute1Min;
	}

	/**
	 * 既存のギャザリングに参加する対象とする属性値1の最小値を設定
	 *
	 * @param searchAttribute1Min 既存のギャザリングに参加する対象とする属性値1の最小値
	 * @return this
	 */
	public CustomAutoDoMatchmakingRequest withSearchAttribute1Min(Integer searchAttribute1Min) {
		setSearchAttribute1Min(searchAttribute1Min);
		return this;
	}

	/**
	 * 既存のギャザリングに参加する対象とする属性値2の最小値を取得
	 *
	 * @return 既存のギャザリングに参加する対象とする属性値2の最小値
	 */
	public Integer getSearchAttribute2Min() {
		return searchAttribute2Min;
	}

	/**
	 * 既存のギャザリングに参加する対象とする属性値2の最小値を設定
	 *
	 * @param searchAttribute2Min 既存のギャザリングに参加する対象とする属性値2の最小値
	 */
	public void setSearchAttribute2Min(Integer searchAttribute2Min) {
		this.searchAttribute2Min = searchAttribute2Min;
	}

	/**
	 * 既存のギャザリングに参加する対象とする属性値2の最小値を設定
	 *
	 * @param searchAttribute2Min 既存のギャザリングに参加する対象とする属性値2の最小値
	 * @return this
	 */
	public CustomAutoDoMatchmakingRequest withSearchAttribute2Min(Integer searchAttribute2Min) {
		setSearchAttribute2Min(searchAttribute2Min);
		return this;
	}

	/**
	 * 既存のギャザリングに参加する対象とする属性値3の最小値を取得
	 *
	 * @return 既存のギャザリングに参加する対象とする属性値3の最小値
	 */
	public Integer getSearchAttribute3Min() {
		return searchAttribute3Min;
	}

	/**
	 * 既存のギャザリングに参加する対象とする属性値3の最小値を設定
	 *
	 * @param searchAttribute3Min 既存のギャザリングに参加する対象とする属性値3の最小値
	 */
	public void setSearchAttribute3Min(Integer searchAttribute3Min) {
		this.searchAttribute3Min = searchAttribute3Min;
	}

	/**
	 * 既存のギャザリングに参加する対象とする属性値3の最小値を設定
	 *
	 * @param searchAttribute3Min 既存のギャザリングに参加する対象とする属性値3の最小値
	 * @return this
	 */
	public CustomAutoDoMatchmakingRequest withSearchAttribute3Min(Integer searchAttribute3Min) {
		setSearchAttribute3Min(searchAttribute3Min);
		return this;
	}

	/**
	 * 既存のギャザリングに参加する対象とする属性値4の最小値を取得
	 *
	 * @return 既存のギャザリングに参加する対象とする属性値4の最小値
	 */
	public Integer getSearchAttribute4Min() {
		return searchAttribute4Min;
	}

	/**
	 * 既存のギャザリングに参加する対象とする属性値4の最小値を設定
	 *
	 * @param searchAttribute4Min 既存のギャザリングに参加する対象とする属性値4の最小値
	 */
	public void setSearchAttribute4Min(Integer searchAttribute4Min) {
		this.searchAttribute4Min = searchAttribute4Min;
	}

	/**
	 * 既存のギャザリングに参加する対象とする属性値4の最小値を設定
	 *
	 * @param searchAttribute4Min 既存のギャザリングに参加する対象とする属性値4の最小値
	 * @return this
	 */
	public CustomAutoDoMatchmakingRequest withSearchAttribute4Min(Integer searchAttribute4Min) {
		setSearchAttribute4Min(searchAttribute4Min);
		return this;
	}

	/**
	 * 既存のギャザリングに参加する対象とする属性値5の最小値を取得
	 *
	 * @return 既存のギャザリングに参加する対象とする属性値5の最小値
	 */
	public Integer getSearchAttribute5Min() {
		return searchAttribute5Min;
	}

	/**
	 * 既存のギャザリングに参加する対象とする属性値5の最小値を設定
	 *
	 * @param searchAttribute5Min 既存のギャザリングに参加する対象とする属性値5の最小値
	 */
	public void setSearchAttribute5Min(Integer searchAttribute5Min) {
		this.searchAttribute5Min = searchAttribute5Min;
	}

	/**
	 * 既存のギャザリングに参加する対象とする属性値5の最小値を設定
	 *
	 * @param searchAttribute5Min 既存のギャザリングに参加する対象とする属性値5の最小値
	 * @return this
	 */
	public CustomAutoDoMatchmakingRequest withSearchAttribute5Min(Integer searchAttribute5Min) {
		setSearchAttribute5Min(searchAttribute5Min);
		return this;
	}

	/**
	 * 既存のギャザリングに参加する対象とする属性値1の最大値を取得
	 *
	 * @return 既存のギャザリングに参加する対象とする属性値1の最大値
	 */
	public Integer getSearchAttribute1Max() {
		return searchAttribute1Max;
	}

	/**
	 * 既存のギャザリングに参加する対象とする属性値1の最大値を設定
	 *
	 * @param searchAttribute1Max 既存のギャザリングに参加する対象とする属性値1の最大値
	 */
	public void setSearchAttribute1Max(Integer searchAttribute1Max) {
		this.searchAttribute1Max = searchAttribute1Max;
	}

	/**
	 * 既存のギャザリングに参加する対象とする属性値1の最大値を設定
	 *
	 * @param searchAttribute1Max 既存のギャザリングに参加する対象とする属性値1の最大値
	 * @return this
	 */
	public CustomAutoDoMatchmakingRequest withSearchAttribute1Max(Integer searchAttribute1Max) {
		setSearchAttribute1Max(searchAttribute1Max);
		return this;
	}

	/**
	 * 既存のギャザリングに参加する対象とする属性値2の最大値を取得
	 *
	 * @return 既存のギャザリングに参加する対象とする属性値2の最大値
	 */
	public Integer getSearchAttribute2Max() {
		return searchAttribute2Max;
	}

	/**
	 * 既存のギャザリングに参加する対象とする属性値2の最大値を設定
	 *
	 * @param searchAttribute2Max 既存のギャザリングに参加する対象とする属性値2の最大値
	 */
	public void setSearchAttribute2Max(Integer searchAttribute2Max) {
		this.searchAttribute2Max = searchAttribute2Max;
	}

	/**
	 * 既存のギャザリングに参加する対象とする属性値2の最大値を設定
	 *
	 * @param searchAttribute2Max 既存のギャザリングに参加する対象とする属性値2の最大値
	 * @return this
	 */
	public CustomAutoDoMatchmakingRequest withSearchAttribute2Max(Integer searchAttribute2Max) {
		setSearchAttribute2Max(searchAttribute2Max);
		return this;
	}

	/**
	 * 既存のギャザリングに参加する対象とする属性値3の最大値を取得
	 *
	 * @return 既存のギャザリングに参加する対象とする属性値3の最大値
	 */
	public Integer getSearchAttribute3Max() {
		return searchAttribute3Max;
	}

	/**
	 * 既存のギャザリングに参加する対象とする属性値3の最大値を設定
	 *
	 * @param searchAttribute3Max 既存のギャザリングに参加する対象とする属性値3の最大値
	 */
	public void setSearchAttribute3Max(Integer searchAttribute3Max) {
		this.searchAttribute3Max = searchAttribute3Max;
	}

	/**
	 * 既存のギャザリングに参加する対象とする属性値3の最大値を設定
	 *
	 * @param searchAttribute3Max 既存のギャザリングに参加する対象とする属性値3の最大値
	 * @return this
	 */
	public CustomAutoDoMatchmakingRequest withSearchAttribute3Max(Integer searchAttribute3Max) {
		setSearchAttribute3Max(searchAttribute3Max);
		return this;
	}

	/**
	 * 既存のギャザリングに参加する対象とする属性値4の最大値を取得
	 *
	 * @return 既存のギャザリングに参加する対象とする属性値4の最大値
	 */
	public Integer getSearchAttribute4Max() {
		return searchAttribute4Max;
	}

	/**
	 * 既存のギャザリングに参加する対象とする属性値4の最大値を設定
	 *
	 * @param searchAttribute4Max 既存のギャザリングに参加する対象とする属性値4の最大値
	 */
	public void setSearchAttribute4Max(Integer searchAttribute4Max) {
		this.searchAttribute4Max = searchAttribute4Max;
	}

	/**
	 * 既存のギャザリングに参加する対象とする属性値4の最大値を設定
	 *
	 * @param searchAttribute4Max 既存のギャザリングに参加する対象とする属性値4の最大値
	 * @return this
	 */
	public CustomAutoDoMatchmakingRequest withSearchAttribute4Max(Integer searchAttribute4Max) {
		setSearchAttribute4Max(searchAttribute4Max);
		return this;
	}

	/**
	 * 既存のギャザリングに参加する対象とする属性値5の最大値を取得
	 *
	 * @return 既存のギャザリングに参加する対象とする属性値5の最大値
	 */
	public Integer getSearchAttribute5Max() {
		return searchAttribute5Max;
	}

	/**
	 * 既存のギャザリングに参加する対象とする属性値5の最大値を設定
	 *
	 * @param searchAttribute5Max 既存のギャザリングに参加する対象とする属性値5の最大値
	 */
	public void setSearchAttribute5Max(Integer searchAttribute5Max) {
		this.searchAttribute5Max = searchAttribute5Max;
	}

	/**
	 * 既存のギャザリングに参加する対象とする属性値5の最大値を設定
	 *
	 * @param searchAttribute5Max 既存のギャザリングに参加する対象とする属性値5の最大値
	 * @return this
	 */
	public CustomAutoDoMatchmakingRequest withSearchAttribute5Max(Integer searchAttribute5Max) {
		setSearchAttribute5Max(searchAttribute5Max);
		return this;
	}

	/**
	 * 中断された検索を再開するためのコンテキストを取得
	 *
	 * @return 中断された検索を再開するためのコンテキスト
	 */
	public String getSearchContext() {
		return searchContext;
	}

	/**
	 * 中断された検索を再開するためのコンテキストを設定
	 *
	 * @param searchContext 中断された検索を再開するためのコンテキスト
	 */
	public void setSearchContext(String searchContext) {
		this.searchContext = searchContext;
	}

	/**
	 * 中断された検索を再開するためのコンテキストを設定
	 *
	 * @param searchContext 中断された検索を再開するためのコンテキスト
	 * @return this
	 */
	public CustomAutoDoMatchmakingRequest withSearchContext(String searchContext) {
		setSearchContext(searchContext);
		return this;
	}

}