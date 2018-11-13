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
public class DescribeSubscribeByRoomIdRequest extends Gs2BasicRequest<DescribeSubscribeByRoomIdRequest> {

	public static class Constant extends Gs2Chat.Constant {
		public static final String FUNCTION = "DescribeSubscribeByRoomId";
	}

	/** ロビーの名前 */
	private String lobbyName;

	/** ルームID */
	private String roomId;

	/** データの取得を開始する位置を指定するトークン */
	private String pageToken;

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
	public DescribeSubscribeByRoomIdRequest withLobbyName(String lobbyName) {
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
	public DescribeSubscribeByRoomIdRequest withRoomId(String roomId) {
		setRoomId(roomId);
		return this;
	}

	/**
	 * データの取得を開始する位置を指定するトークンを取得
	 *
	 * @return データの取得を開始する位置を指定するトークン
	 */
	public String getPageToken() {
		return pageToken;
	}

	/**
	 * データの取得を開始する位置を指定するトークンを設定
	 *
	 * @param pageToken データの取得を開始する位置を指定するトークン
	 */
	public void setPageToken(String pageToken) {
		this.pageToken = pageToken;
	}

	/**
	 * データの取得を開始する位置を指定するトークンを設定
	 *
	 * @param pageToken データの取得を開始する位置を指定するトークン
	 * @return this
	 */
	public DescribeSubscribeByRoomIdRequest withPageToken(String pageToken) {
		setPageToken(pageToken);
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
	public DescribeSubscribeByRoomIdRequest withLimit(Integer limit) {
		setLimit(limit);
		return this;
	}

}