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

package io.gs2.ranking.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.ranking.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * スコアの一覧を取得 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class DescribeScoresRequest extends Gs2BasicRequest<DescribeScoresRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return スコアの一覧を取得
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName スコアの一覧を取得
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName スコアの一覧を取得
     * @return this
     */
    public DescribeScoresRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** カテゴリ名 */
    private String categoryName;

    /**
     * カテゴリ名を取得
     *
     * @return スコアの一覧を取得
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * カテゴリ名を設定
     *
     * @param categoryName スコアの一覧を取得
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * カテゴリ名を設定
     *
     * @param categoryName スコアの一覧を取得
     * @return this
     */
    public DescribeScoresRequest withCategoryName(String categoryName) {
        setCategoryName(categoryName);
        return this;
    }

    /** スコアを獲得したユーザID */
    private String scorerUserId;

    /**
     * スコアを獲得したユーザIDを取得
     *
     * @return スコアの一覧を取得
     */
    public String getScorerUserId() {
        return scorerUserId;
    }

    /**
     * スコアを獲得したユーザIDを設定
     *
     * @param scorerUserId スコアの一覧を取得
     */
    public void setScorerUserId(String scorerUserId) {
        this.scorerUserId = scorerUserId;
    }

    /**
     * スコアを獲得したユーザIDを設定
     *
     * @param scorerUserId スコアの一覧を取得
     * @return this
     */
    public DescribeScoresRequest withScorerUserId(String scorerUserId) {
        setScorerUserId(scorerUserId);
        return this;
    }

    /** データの取得を開始する位置を指定するトークン */
    private String pageToken;

    /**
     * データの取得を開始する位置を指定するトークンを取得
     *
     * @return スコアの一覧を取得
     */
    public String getPageToken() {
        return pageToken;
    }

    /**
     * データの取得を開始する位置を指定するトークンを設定
     *
     * @param pageToken スコアの一覧を取得
     */
    public void setPageToken(String pageToken) {
        this.pageToken = pageToken;
    }

    /**
     * データの取得を開始する位置を指定するトークンを設定
     *
     * @param pageToken スコアの一覧を取得
     * @return this
     */
    public DescribeScoresRequest withPageToken(String pageToken) {
        setPageToken(pageToken);
        return this;
    }

    /** データの取得件数 */
    private Long limit;

    /**
     * データの取得件数を取得
     *
     * @return スコアの一覧を取得
     */
    public Long getLimit() {
        return limit;
    }

    /**
     * データの取得件数を設定
     *
     * @param limit スコアの一覧を取得
     */
    public void setLimit(Long limit) {
        this.limit = limit;
    }

    /**
     * データの取得件数を設定
     *
     * @param limit スコアの一覧を取得
     * @return this
     */
    public DescribeScoresRequest withLimit(Long limit) {
        setLimit(limit);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return スコアの一覧を取得
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider スコアの一覧を取得
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider スコアの一覧を取得
     * @return this
     */
    public DescribeScoresRequest withDuplicationAvoider(String duplicationAvoider) {
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
    public DescribeScoresRequest withAccessToken(String accessToken) {
        setAccessToken(accessToken);
        return this;
    }

}