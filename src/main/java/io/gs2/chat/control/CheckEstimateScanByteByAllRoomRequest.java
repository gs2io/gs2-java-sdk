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
public class CheckEstimateScanByteByAllRoomRequest extends Gs2BasicRequest<CheckEstimateScanByteByAllRoomRequest> {

	public static class Constant extends Gs2Chat.Constant {
		public static final String FUNCTION = "CheckEstimateScanByteByAllRoom";
	}

	/** ロビーの名前 */
	private String lobbyName;

	/** 検索するユーザID文字列(部分一致) */
	private String userId;

	/** 検索するメッセージテキスト文字列(部分一致) */
	private String message;

	/** 検索するメッセージメタデータ文字列(部分一致) */
	private String meta;

	/** 検索期間 開始日時（エポック秒） */
	private Integer begin;

	/** 検索期間 終了日時（エポック秒） */
	private Integer end;

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
	public CheckEstimateScanByteByAllRoomRequest withLobbyName(String lobbyName) {
		setLobbyName(lobbyName);
		return this;
	}

	/**
	 * 検索するユーザID文字列(部分一致)を取得
	 *
	 * @return 検索するユーザID文字列(部分一致)
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * 検索するユーザID文字列(部分一致)を設定
	 *
	 * @param userId 検索するユーザID文字列(部分一致)
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * 検索するユーザID文字列(部分一致)を設定
	 *
	 * @param userId 検索するユーザID文字列(部分一致)
	 * @return this
	 */
	public CheckEstimateScanByteByAllRoomRequest withUserId(String userId) {
		setUserId(userId);
		return this;
	}

	/**
	 * 検索するメッセージテキスト文字列(部分一致)を取得
	 *
	 * @return 検索するメッセージテキスト文字列(部分一致)
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * 検索するメッセージテキスト文字列(部分一致)を設定
	 *
	 * @param message 検索するメッセージテキスト文字列(部分一致)
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 検索するメッセージテキスト文字列(部分一致)を設定
	 *
	 * @param message 検索するメッセージテキスト文字列(部分一致)
	 * @return this
	 */
	public CheckEstimateScanByteByAllRoomRequest withMessage(String message) {
		setMessage(message);
		return this;
	}

	/**
	 * 検索するメッセージメタデータ文字列(部分一致)を取得
	 *
	 * @return 検索するメッセージメタデータ文字列(部分一致)
	 */
	public String getMeta() {
		return meta;
	}

	/**
	 * 検索するメッセージメタデータ文字列(部分一致)を設定
	 *
	 * @param meta 検索するメッセージメタデータ文字列(部分一致)
	 */
	public void setMeta(String meta) {
		this.meta = meta;
	}

	/**
	 * 検索するメッセージメタデータ文字列(部分一致)を設定
	 *
	 * @param meta 検索するメッセージメタデータ文字列(部分一致)
	 * @return this
	 */
	public CheckEstimateScanByteByAllRoomRequest withMeta(String meta) {
		setMeta(meta);
		return this;
	}

	/**
	 * 検索期間 開始日時（エポック秒）を取得
	 *
	 * @return 検索期間 開始日時（エポック秒）
	 */
	public Integer getBegin() {
		return begin;
	}

	/**
	 * 検索期間 開始日時（エポック秒）を設定
	 *
	 * @param begin 検索期間 開始日時（エポック秒）
	 */
	public void setBegin(Integer begin) {
		this.begin = begin;
	}

	/**
	 * 検索期間 開始日時（エポック秒）を設定
	 *
	 * @param begin 検索期間 開始日時（エポック秒）
	 * @return this
	 */
	public CheckEstimateScanByteByAllRoomRequest withBegin(Integer begin) {
		setBegin(begin);
		return this;
	}

	/**
	 * 検索期間 終了日時（エポック秒）を取得
	 *
	 * @return 検索期間 終了日時（エポック秒）
	 */
	public Integer getEnd() {
		return end;
	}

	/**
	 * 検索期間 終了日時（エポック秒）を設定
	 *
	 * @param end 検索期間 終了日時（エポック秒）
	 */
	public void setEnd(Integer end) {
		this.end = end;
	}

	/**
	 * 検索期間 終了日時（エポック秒）を設定
	 *
	 * @param end 検索期間 終了日時（エポック秒）
	 * @return this
	 */
	public CheckEstimateScanByteByAllRoomRequest withEnd(Integer end) {
		setEnd(end);
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
	public CheckEstimateScanByteByAllRoomRequest withPageToken(String pageToken) {
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
	public CheckEstimateScanByteByAllRoomRequest withLimit(Integer limit) {
		setLimit(limit);
		return this;
	}

}