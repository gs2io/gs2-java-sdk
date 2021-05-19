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
 * 指定したスコア付近のランキングを取得 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class DescribeNearRankingsRequest extends Gs2BasicRequest<DescribeNearRankingsRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return 指定したスコア付近のランキングを取得
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 指定したスコア付近のランキングを取得
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 指定したスコア付近のランキングを取得
     * @return this
     */
    public DescribeNearRankingsRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** カテゴリ名 */
    private String categoryName;

    /**
     * カテゴリ名を取得
     *
     * @return 指定したスコア付近のランキングを取得
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * カテゴリ名を設定
     *
     * @param categoryName 指定したスコア付近のランキングを取得
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    /**
     * カテゴリ名を設定
     *
     * @param categoryName 指定したスコア付近のランキングを取得
     * @return this
     */
    public DescribeNearRankingsRequest withCategoryName(String categoryName) {
        setCategoryName(categoryName);
        return this;
    }

    /** スコア */
    private Long score;

    /**
     * スコアを取得
     *
     * @return 指定したスコア付近のランキングを取得
     */
    public Long getScore() {
        return score;
    }

    /**
     * スコアを設定
     *
     * @param score 指定したスコア付近のランキングを取得
     */
    public void setScore(Long score) {
        this.score = score;
    }

    /**
     * スコアを設定
     *
     * @param score 指定したスコア付近のランキングを取得
     * @return this
     */
    public DescribeNearRankingsRequest withScore(Long score) {
        setScore(score);
        return this;
    }

}