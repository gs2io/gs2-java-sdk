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
 * ユーザーIDを指定してスコアを登録 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class PutScoreByUserIdRequest extends Gs2BasicRequest<PutScoreByUserIdRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return ユーザーIDを指定してスコアを登録
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ユーザーIDを指定してスコアを登録
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ユーザーIDを指定してスコアを登録
     * @return this
     */
    public PutScoreByUserIdRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** カテゴリ名 */
    private String categoryName;

    /**
     * カテゴリ名を取得
     *
     * @return ユーザーIDを指定してスコアを登録
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * カテゴリ名を設定
     *
     * @param categoryName ユーザーIDを指定してスコアを登録
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * カテゴリ名を設定
     *
     * @param categoryName ユーザーIDを指定してスコアを登録
     * @return this
     */
    public PutScoreByUserIdRequest withCategoryName(String categoryName) {
        setCategoryName(categoryName);
        return this;
    }

    /** ユーザID */
    private String userId;

    /**
     * ユーザIDを取得
     *
     * @return ユーザーIDを指定してスコアを登録
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザIDを設定
     *
     * @param userId ユーザーIDを指定してスコアを登録
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * ユーザIDを設定
     *
     * @param userId ユーザーIDを指定してスコアを登録
     * @return this
     */
    public PutScoreByUserIdRequest withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    /** スコア */
    private Long score;

    /**
     * スコアを取得
     *
     * @return ユーザーIDを指定してスコアを登録
     */
    public Long getScore() {
        return score;
    }

    /**
     * スコアを設定
     *
     * @param score ユーザーIDを指定してスコアを登録
     */
    public void setScore(Long score) {
        this.score = score;
    }

    /**
     * スコアを設定
     *
     * @param score ユーザーIDを指定してスコアを登録
     * @return this
     */
    public PutScoreByUserIdRequest withScore(Long score) {
        setScore(score);
        return this;
    }

    /** メタデータ */
    private String metadata;

    /**
     * メタデータを取得
     *
     * @return ユーザーIDを指定してスコアを登録
     */
    public String getMetadata() {
        return metadata;
    }

    /**
     * メタデータを設定
     *
     * @param metadata ユーザーIDを指定してスコアを登録
     */
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    /**
     * メタデータを設定
     *
     * @param metadata ユーザーIDを指定してスコアを登録
     * @return this
     */
    public PutScoreByUserIdRequest withMetadata(String metadata) {
        setMetadata(metadata);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return ユーザーIDを指定してスコアを登録
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ユーザーIDを指定してスコアを登録
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ユーザーIDを指定してスコアを登録
     * @return this
     */
    public PutScoreByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

}