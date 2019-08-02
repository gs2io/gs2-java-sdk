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
import io.gs2.control.Gs2BasicRequest;

/**
 * レシートの一覧を取得 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class DescribeReceiptsRequest extends Gs2BasicRequest<DescribeReceiptsRequest> {

    /** ネームスペースの名前 */
    private String namespaceName;

    /**
     * ネームスペースの名前を取得
     *
     * @return レシートの一覧を取得
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペースの名前を設定
     *
     * @param namespaceName レシートの一覧を取得
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペースの名前を設定
     *
     * @param namespaceName レシートの一覧を取得
     * @return this
     */
    public DescribeReceiptsRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** ユーザーID */
    private String userId;

    /**
     * ユーザーIDを取得
     *
     * @return レシートの一覧を取得
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId レシートの一覧を取得
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId レシートの一覧を取得
     * @return this
     */
    public DescribeReceiptsRequest withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    /** None */
    private Integer slot;

    /**
     * Noneを取得
     *
     * @return レシートの一覧を取得
     */
    public Integer getSlot() {
        return slot;
    }

    /**
     * Noneを設定
     *
     * @param slot レシートの一覧を取得
     */
    public void setSlot(Integer slot) {
        this.slot = slot;
    }

    /**
     * Noneを設定
     *
     * @param slot レシートの一覧を取得
     * @return this
     */
    public DescribeReceiptsRequest withSlot(Integer slot) {
        setSlot(slot);
        return this;
    }

    /** None */
    private Long begin;

    /**
     * Noneを取得
     *
     * @return レシートの一覧を取得
     */
    public Long getBegin() {
        return begin;
    }

    /**
     * Noneを設定
     *
     * @param begin レシートの一覧を取得
     */
    public void setBegin(Long begin) {
        this.begin = begin;
    }

    /**
     * Noneを設定
     *
     * @param begin レシートの一覧を取得
     * @return this
     */
    public DescribeReceiptsRequest withBegin(Long begin) {
        setBegin(begin);
        return this;
    }

    /** None */
    private Long end;

    /**
     * Noneを取得
     *
     * @return レシートの一覧を取得
     */
    public Long getEnd() {
        return end;
    }

    /**
     * Noneを設定
     *
     * @param end レシートの一覧を取得
     */
    public void setEnd(Long end) {
        this.end = end;
    }

    /**
     * Noneを設定
     *
     * @param end レシートの一覧を取得
     * @return this
     */
    public DescribeReceiptsRequest withEnd(Long end) {
        setEnd(end);
        return this;
    }

    /** データの取得を開始する位置を指定するトークン */
    private String pageToken;

    /**
     * データの取得を開始する位置を指定するトークンを取得
     *
     * @return レシートの一覧を取得
     */
    public String getPageToken() {
        return pageToken;
    }

    /**
     * データの取得を開始する位置を指定するトークンを設定
     *
     * @param pageToken レシートの一覧を取得
     */
    public void setPageToken(String pageToken) {
        this.pageToken = pageToken;
    }

    /**
     * データの取得を開始する位置を指定するトークンを設定
     *
     * @param pageToken レシートの一覧を取得
     * @return this
     */
    public DescribeReceiptsRequest withPageToken(String pageToken) {
        setPageToken(pageToken);
        return this;
    }

    /** データの取得件数 */
    private Long limit;

    /**
     * データの取得件数を取得
     *
     * @return レシートの一覧を取得
     */
    public Long getLimit() {
        return limit;
    }

    /**
     * データの取得件数を設定
     *
     * @param limit レシートの一覧を取得
     */
    public void setLimit(Long limit) {
        this.limit = limit;
    }

    /**
     * データの取得件数を設定
     *
     * @param limit レシートの一覧を取得
     * @return this
     */
    public DescribeReceiptsRequest withLimit(Long limit) {
        setLimit(limit);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return レシートの一覧を取得
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider レシートの一覧を取得
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider レシートの一覧を取得
     * @return this
     */
    public DescribeReceiptsRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

}