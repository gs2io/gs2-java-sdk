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

package io.gs2.chat.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.chat.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * ルーム名を指定して購読しているユーザの一覧取得 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class DescribeSubscribesByRoomNameRequest extends Gs2BasicRequest<DescribeSubscribesByRoomNameRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return ルーム名を指定して購読しているユーザの一覧取得
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ルーム名を指定して購読しているユーザの一覧取得
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ルーム名を指定して購読しているユーザの一覧取得
     * @return this
     */
    public DescribeSubscribesByRoomNameRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** 購読するユーザID */
    private String roomName;

    /**
     * 購読するユーザIDを取得
     *
     * @return ルーム名を指定して購読しているユーザの一覧取得
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * 購読するユーザIDを設定
     *
     * @param roomName ルーム名を指定して購読しているユーザの一覧取得
     */
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    /**
     * 購読するユーザIDを設定
     *
     * @param roomName ルーム名を指定して購読しているユーザの一覧取得
     * @return this
     */
    public DescribeSubscribesByRoomNameRequest withRoomName(String roomName) {
        setRoomName(roomName);
        return this;
    }

    /** データの取得を開始する位置を指定するトークン */
    private String pageToken;

    /**
     * データの取得を開始する位置を指定するトークンを取得
     *
     * @return ルーム名を指定して購読しているユーザの一覧取得
     */
    public String getPageToken() {
        return pageToken;
    }

    /**
     * データの取得を開始する位置を指定するトークンを設定
     *
     * @param pageToken ルーム名を指定して購読しているユーザの一覧取得
     */
    public void setPageToken(String pageToken) {
        this.pageToken = pageToken;
    }

    /**
     * データの取得を開始する位置を指定するトークンを設定
     *
     * @param pageToken ルーム名を指定して購読しているユーザの一覧取得
     * @return this
     */
    public DescribeSubscribesByRoomNameRequest withPageToken(String pageToken) {
        setPageToken(pageToken);
        return this;
    }

    /** データの取得件数 */
    private Long limit;

    /**
     * データの取得件数を取得
     *
     * @return ルーム名を指定して購読しているユーザの一覧取得
     */
    public Long getLimit() {
        return limit;
    }

    /**
     * データの取得件数を設定
     *
     * @param limit ルーム名を指定して購読しているユーザの一覧取得
     */
    public void setLimit(Long limit) {
        this.limit = limit;
    }

    /**
     * データの取得件数を設定
     *
     * @param limit ルーム名を指定して購読しているユーザの一覧取得
     * @return this
     */
    public DescribeSubscribesByRoomNameRequest withLimit(Long limit) {
        setLimit(limit);
        return this;
    }

}