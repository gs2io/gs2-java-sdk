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

package io.gs2.matchmaking.model;

import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * CustomAutoマッチメイキング ギャザリング
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CustomAutoGathering implements Serializable {

	/** ギャザリングID */
	private String gatheringId;

	/** 属性値1 */
	private Integer attribute1;

	/** 属性値2 */
	private Integer attribute2;

	/** 属性値3 */
	private Integer attribute3;

	/** 属性値4 */
	private Integer attribute4;

	/** 属性値5 */
	private Integer attribute5;

	/** 参加プレイヤー数 */
	private Integer joinPlayer;

	/** 作成日時(エポック秒) */
	private Integer createAt;

	/** 更新日時(エポック秒) */
	private Integer updateAt;


	/**
	 * ギャザリングIDを取得
	 *
	 * @return ギャザリングID
	 */
	public String getGatheringId() {
		return gatheringId;
	}

	/**
	 * ギャザリングIDを設定
	 *
	 * @param gatheringId ギャザリングID
	 */
	public void setGatheringId(String gatheringId) {
		this.gatheringId = gatheringId;
	}

	/**
	 * 属性値1を取得
	 *
	 * @return 属性値1
	 */
	public Integer getAttribute1() {
		return attribute1;
	}

	/**
	 * 属性値1を設定
	 *
	 * @param attribute1 属性値1
	 */
	public void setAttribute1(Integer attribute1) {
		this.attribute1 = attribute1;
	}

	/**
	 * 属性値2を取得
	 *
	 * @return 属性値2
	 */
	public Integer getAttribute2() {
		return attribute2;
	}

	/**
	 * 属性値2を設定
	 *
	 * @param attribute2 属性値2
	 */
	public void setAttribute2(Integer attribute2) {
		this.attribute2 = attribute2;
	}

	/**
	 * 属性値3を取得
	 *
	 * @return 属性値3
	 */
	public Integer getAttribute3() {
		return attribute3;
	}

	/**
	 * 属性値3を設定
	 *
	 * @param attribute3 属性値3
	 */
	public void setAttribute3(Integer attribute3) {
		this.attribute3 = attribute3;
	}

	/**
	 * 属性値4を取得
	 *
	 * @return 属性値4
	 */
	public Integer getAttribute4() {
		return attribute4;
	}

	/**
	 * 属性値4を設定
	 *
	 * @param attribute4 属性値4
	 */
	public void setAttribute4(Integer attribute4) {
		this.attribute4 = attribute4;
	}

	/**
	 * 属性値5を取得
	 *
	 * @return 属性値5
	 */
	public Integer getAttribute5() {
		return attribute5;
	}

	/**
	 * 属性値5を設定
	 *
	 * @param attribute5 属性値5
	 */
	public void setAttribute5(Integer attribute5) {
		this.attribute5 = attribute5;
	}

	/**
	 * 参加プレイヤー数を取得
	 *
	 * @return 参加プレイヤー数
	 */
	public Integer getJoinPlayer() {
		return joinPlayer;
	}

	/**
	 * 参加プレイヤー数を設定
	 *
	 * @param joinPlayer 参加プレイヤー数
	 */
	public void setJoinPlayer(Integer joinPlayer) {
		this.joinPlayer = joinPlayer;
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

}