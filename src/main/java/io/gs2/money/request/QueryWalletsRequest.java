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

package io.gs2.money.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.money.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * ウォレット一覧を取得します のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class QueryWalletsRequest extends Gs2BasicRequest<QueryWalletsRequest> {

    /** ネームスペースの名前 */
    private String namespaceName;

    /**
     * ネームスペースの名前を取得
     *
     * @return ウォレット一覧を取得します
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペースの名前を設定
     *
     * @param namespaceName ウォレット一覧を取得します
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペースの名前を設定
     *
     * @param namespaceName ウォレット一覧を取得します
     * @return this
     */
    public QueryWalletsRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** ユーザーID */
    private String userId;

    /**
     * ユーザーIDを取得
     *
     * @return ウォレット一覧を取得します
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId ウォレット一覧を取得します
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId ウォレット一覧を取得します
     * @return this
     */
    public QueryWalletsRequest withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    /** データの取得を開始する位置を指定するトークン */
    private String pageToken;

    /**
     * データの取得を開始する位置を指定するトークンを取得
     *
     * @return ウォレット一覧を取得します
     */
    public String getPageToken() {
        return pageToken;
    }

    /**
     * データの取得を開始する位置を指定するトークンを設定
     *
     * @param pageToken ウォレット一覧を取得します
     */
    public void setPageToken(String pageToken) {
        this.pageToken = pageToken;
    }

    /**
     * データの取得を開始する位置を指定するトークンを設定
     *
     * @param pageToken ウォレット一覧を取得します
     * @return this
     */
    public QueryWalletsRequest withPageToken(String pageToken) {
        setPageToken(pageToken);
        return this;
    }

    /** データの取得件数 */
    private Integer limit;

    /**
     * データの取得件数を取得
     *
     * @return ウォレット一覧を取得します
     */
    public Integer getLimit() {
        return limit;
    }

    /**
     * データの取得件数を設定
     *
     * @param limit ウォレット一覧を取得します
     */
    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    /**
     * データの取得件数を設定
     *
     * @param limit ウォレット一覧を取得します
     * @return this
     */
    public QueryWalletsRequest withLimit(Integer limit) {
        setLimit(limit);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return ウォレット一覧を取得します
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ウォレット一覧を取得します
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ウォレット一覧を取得します
     * @return this
     */
    public QueryWalletsRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

}