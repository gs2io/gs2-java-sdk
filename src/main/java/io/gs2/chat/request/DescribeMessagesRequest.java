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
 * メッセージの一覧取得 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class DescribeMessagesRequest extends Gs2BasicRequest<DescribeMessagesRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return メッセージの一覧取得
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName メッセージの一覧取得
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName メッセージの一覧取得
     * @return this
     */
    public DescribeMessagesRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** ルーム名 */
    private String roomName;

    /**
     * ルーム名を取得
     *
     * @return メッセージの一覧取得
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * ルーム名を設定
     *
     * @param roomName メッセージの一覧取得
     */
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    /**
     * ルーム名を設定
     *
     * @param roomName メッセージの一覧取得
     * @return this
     */
    public DescribeMessagesRequest withRoomName(String roomName) {
        setRoomName(roomName);
        return this;
    }

    /** メッセージを投稿するために必要となるパスワード */
    private String password;

    /**
     * メッセージを投稿するために必要となるパスワードを取得
     *
     * @return メッセージの一覧取得
     */
    public String getPassword() {
        return password;
    }

    /**
     * メッセージを投稿するために必要となるパスワードを設定
     *
     * @param password メッセージの一覧取得
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * メッセージを投稿するために必要となるパスワードを設定
     *
     * @param password メッセージの一覧取得
     * @return this
     */
    public DescribeMessagesRequest withPassword(String password) {
        setPassword(password);
        return this;
    }

    /** メッセージの取得を開始する時間 */
    private Long startAt;

    /**
     * メッセージの取得を開始する時間を取得
     *
     * @return メッセージの一覧取得
     */
    public Long getStartAt() {
        return startAt;
    }

    /**
     * メッセージの取得を開始する時間を設定
     *
     * @param startAt メッセージの一覧取得
     */
    public void setStartAt(Long startAt) {
        this.startAt = startAt;
    }

    /**
     * メッセージの取得を開始する時間を設定
     *
     * @param startAt メッセージの一覧取得
     * @return this
     */
    public DescribeMessagesRequest withStartAt(Long startAt) {
        setStartAt(startAt);
        return this;
    }

    /** データの取得件数 */
    private Long limit;

    /**
     * データの取得件数を取得
     *
     * @return メッセージの一覧取得
     */
    public Long getLimit() {
        return limit;
    }

    /**
     * データの取得件数を設定
     *
     * @param limit メッセージの一覧取得
     */
    public void setLimit(Long limit) {
        this.limit = limit;
    }

    /**
     * データの取得件数を設定
     *
     * @param limit メッセージの一覧取得
     * @return this
     */
    public DescribeMessagesRequest withLimit(Long limit) {
        setLimit(limit);
        return this;
    }

}