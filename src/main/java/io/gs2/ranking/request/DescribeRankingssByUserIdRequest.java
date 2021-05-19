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
 * ユーザIDを指定してランキングを取得 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class DescribeRankingssByUserIdRequest extends Gs2BasicRequest<DescribeRankingssByUserIdRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return ユーザIDを指定してランキングを取得
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ユーザIDを指定してランキングを取得
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ユーザIDを指定してランキングを取得
     * @return this
     */
    public DescribeRankingssByUserIdRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** カテゴリ名 */
    private String categoryName;

    /**
     * カテゴリ名を取得
     *
     * @return ユーザIDを指定してランキングを取得
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * カテゴリ名を設定
     *
     * @param categoryName ユーザIDを指定してランキングを取得
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * カテゴリ名を設定
     *
     * @param categoryName ユーザIDを指定してランキングを取得
     * @return this
     */
    public DescribeRankingssByUserIdRequest withCategoryName(String categoryName) {
        setCategoryName(categoryName);
        return this;
    }

    /** ユーザID */
    private String userId;

    /**
     * ユーザIDを取得
     *
     * @return ユーザIDを指定してランキングを取得
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザIDを設定
     *
     * @param userId ユーザIDを指定してランキングを取得
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * ユーザIDを設定
     *
     * @param userId ユーザIDを指定してランキングを取得
     * @return this
     */
    public DescribeRankingssByUserIdRequest withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    /** ランキングの取得を開始するインデックス */
    private Long startIndex;

    /**
     * ランキングの取得を開始するインデックスを取得
     *
     * @return ユーザIDを指定してランキングを取得
     */
    public Long getStartIndex() {
        return startIndex;
    }

    /**
     * ランキングの取得を開始するインデックスを設定
     *
     * @param startIndex ユーザIDを指定してランキングを取得
     */
    public void setStartIndex(Long startIndex) {
        this.startIndex = startIndex;
    }

    /**
     * ランキングの取得を開始するインデックスを設定
     *
     * @param startIndex ユーザIDを指定してランキングを取得
     * @return this
     */
    public DescribeRankingssByUserIdRequest withStartIndex(Long startIndex) {
        setStartIndex(startIndex);
        return this;
    }

    /** データの取得を開始する位置を指定するトークン */
    private String pageToken;

    /**
     * データの取得を開始する位置を指定するトークンを取得
     *
     * @return ユーザIDを指定してランキングを取得
     */
    public String getPageToken() {
        return pageToken;
    }

    /**
     * データの取得を開始する位置を指定するトークンを設定
     *
     * @param pageToken ユーザIDを指定してランキングを取得
     */
    public void setPageToken(String pageToken) {
        this.pageToken = pageToken;
    }

    /**
     * データの取得を開始する位置を指定するトークンを設定
     *
     * @param pageToken ユーザIDを指定してランキングを取得
     * @return this
     */
    public DescribeRankingssByUserIdRequest withPageToken(String pageToken) {
        setPageToken(pageToken);
        return this;
    }

    /** データの取得件数 */
    private Long limit;

    /**
     * データの取得件数を取得
     *
     * @return ユーザIDを指定してランキングを取得
     */
    public Long getLimit() {
        return limit;
    }

    /**
     * データの取得件数を設定
     *
     * @param limit ユーザIDを指定してランキングを取得
     */
    public void setLimit(Long limit) {
        this.limit = limit;
    }

    /**
     * データの取得件数を設定
     *
     * @param limit ユーザIDを指定してランキングを取得
     * @return this
     */
    public DescribeRankingssByUserIdRequest withLimit(Long limit) {
        setLimit(limit);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return ユーザIDを指定してランキングを取得
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ユーザIDを指定してランキングを取得
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ユーザIDを指定してランキングを取得
     * @return this
     */
    public DescribeRankingssByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

}