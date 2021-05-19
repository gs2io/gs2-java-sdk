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

package io.gs2.datastore.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.datastore.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * データオブジェクトの一覧を取得 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class DescribeDataObjectsRequest extends Gs2BasicRequest<DescribeDataObjectsRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return データオブジェクトの一覧を取得
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName データオブジェクトの一覧を取得
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName データオブジェクトの一覧を取得
     * @return this
     */
    public DescribeDataObjectsRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** 状態 */
    private String status;

    /**
     * 状態を取得
     *
     * @return データオブジェクトの一覧を取得
     */
    public String getStatus() {
        return status;
    }

    /**
     * 状態を設定
     *
     * @param status データオブジェクトの一覧を取得
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 状態を設定
     *
     * @param status データオブジェクトの一覧を取得
     * @return this
     */
    public DescribeDataObjectsRequest withStatus(String status) {
        setStatus(status);
        return this;
    }

    /** データの取得を開始する位置を指定するトークン */
    private String pageToken;

    /**
     * データの取得を開始する位置を指定するトークンを取得
     *
     * @return データオブジェクトの一覧を取得
     */
    public String getPageToken() {
        return pageToken;
    }

    /**
     * データの取得を開始する位置を指定するトークンを設定
     *
     * @param pageToken データオブジェクトの一覧を取得
     */
    public void setPageToken(String pageToken) {
        this.pageToken = pageToken;
    }

    /**
     * データの取得を開始する位置を指定するトークンを設定
     *
     * @param pageToken データオブジェクトの一覧を取得
     * @return this
     */
    public DescribeDataObjectsRequest withPageToken(String pageToken) {
        setPageToken(pageToken);
        return this;
    }

    /** データの取得件数 */
    private Long limit;

    /**
     * データの取得件数を取得
     *
     * @return データオブジェクトの一覧を取得
     */
    public Long getLimit() {
        return limit;
    }

    /**
     * データの取得件数を設定
     *
     * @param limit データオブジェクトの一覧を取得
     */
    public void setLimit(Long limit) {
        this.limit = limit;
    }

    /**
     * データの取得件数を設定
     *
     * @param limit データオブジェクトの一覧を取得
     * @return this
     */
    public DescribeDataObjectsRequest withLimit(Long limit) {
        setLimit(limit);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return データオブジェクトの一覧を取得
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider データオブジェクトの一覧を取得
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider データオブジェクトの一覧を取得
     * @return this
     */
    public DescribeDataObjectsRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

    /** アクセストークン */
    private String accessToken;

    /**
     * アクセストークンを取得
     *
     * @return アクセストークン
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * アクセストークンを設定
     *
     * @param accessToken アクセストークン
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * アクセストークンを設定
     *
     * @param accessToken アクセストークン
     * @return this
     */
    public DescribeDataObjectsRequest withAccessToken(String accessToken) {
        setAccessToken(accessToken);
        return this;
    }

}