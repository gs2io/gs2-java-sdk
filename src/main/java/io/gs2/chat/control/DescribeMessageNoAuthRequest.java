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

package io.gs2.chat.control;

import org.json.JSONObject;
import java.util.List;
import io.gs2.chat.model.*;
import io.gs2.chat.Gs2Chat;
import io.gs2.control.Gs2BasicRequest;

/**
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class DescribeMessageNoAuthRequest extends Gs2BasicRequest<DescribeMessageNoAuthRequest> {

	public static class Constant extends Gs2Chat.Constant {
		public static final String FUNCTION = "DescribeMessageNoAuth";
	}

	/** ロビーの名前 */
	private String lobbyName;

	/** ルームID */
	private String roomId;

	/** メッセージの取得を開始する日時(エポック秒) */
	private Integer startAt;

	/** データの取得件数 */
	private Integer limit;


	/**
	 * ロビーの名前を取得
	 *
	 * @return ロビーの名前
	 */
	public String getLobbyName() {
		return lobbyName;
	}

	/**
	 * ロビーの名前を設定
	 *
	 * @param lobbyName ロビーの名前
	 */
	public void setLobbyName(String lobbyName) {
		this.lobbyName = lobbyName;
	}

	/**
	 * ロビーの名前を設定
	 *
	 * @param lobbyName ロビーの名前
	 * @return this
	 */
	public DescribeMessageNoAuthRequest withLobbyName(String lobbyName) {
		setLobbyName(lobbyName);
		return this;
	}

	/**
	 * ルームIDを取得
	 *
	 * @return ルームID
	 */
	public String getRoomId() {
		return roomId;
	}

	/**
	 * ルームIDを設定
	 *
	 * @param roomId ルームID
	 */
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	/**
	 * ルームIDを設定
	 *
	 * @param roomId ルームID
	 * @return this
	 */
	public DescribeMessageNoAuthRequest withRoomId(String roomId) {
		setRoomId(roomId);
		return this;
	}

	/**
	 * メッセージの取得を開始する日時(エポック秒)を取得
	 *
	 * @return メッセージの取得を開始する日時(エポック秒)
	 */
	public Integer getStartAt() {
		return startAt;
	}

	/**
	 * メッセージの取得を開始する日時(エポック秒)を設定
	 *
	 * @param startAt メッセージの取得を開始する日時(エポック秒)
	 */
	public void setStartAt(Integer startAt) {
		this.startAt = startAt;
	}

	/**
	 * メッセージの取得を開始する日時(エポック秒)を設定
	 *
	 * @param startAt メッセージの取得を開始する日時(エポック秒)
	 * @return this
	 */
	public DescribeMessageNoAuthRequest withStartAt(Integer startAt) {
		setStartAt(startAt);
		return this;
	}

	/**
	 * データの取得件数を取得
	 *
	 * @return データの取得件数
	 */
	public Integer getLimit() {
		return limit;
	}

	/**
	 * データの取得件数を設定
	 *
	 * @param limit データの取得件数
	 */
	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	/**
	 * データの取得件数を設定
	 *
	 * @param limit データの取得件数
	 * @return this
	 */
	public DescribeMessageNoAuthRequest withLimit(Integer limit) {
		setLimit(limit);
		return this;
	}

}