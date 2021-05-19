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

package io.gs2.log.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.log.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * スタンプシート実行ログの一覧を取得 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CountExecuteStampSheetLogRequest extends Gs2BasicRequest<CountExecuteStampSheetLogRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return スタンプシート実行ログの一覧を取得
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName スタンプシート実行ログの一覧を取得
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName スタンプシート実行ログの一覧を取得
     * @return this
     */
    public CountExecuteStampSheetLogRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** マイクロサービスの種類を集計軸に使用するか */
    private Boolean service;

    /**
     * マイクロサービスの種類を集計軸に使用するかを取得
     *
     * @return スタンプシート実行ログの一覧を取得
     */
    public Boolean getService() {
        return service;
    }

    /**
     * マイクロサービスの種類を集計軸に使用するかを設定
     *
     * @param service スタンプシート実行ログの一覧を取得
     */
    public void setService(Boolean service) {
        this.service = service;
    }

    /**
     * マイクロサービスの種類を集計軸に使用するかを設定
     *
     * @param service スタンプシート実行ログの一覧を取得
     * @return this
     */
    public CountExecuteStampSheetLogRequest withService(Boolean service) {
        setService(service);
        return this;
    }

    /** マイクロサービスのメソッドを集計軸に使用するか */
    private Boolean method;

    /**
     * マイクロサービスのメソッドを集計軸に使用するかを取得
     *
     * @return スタンプシート実行ログの一覧を取得
     */
    public Boolean getMethod() {
        return method;
    }

    /**
     * マイクロサービスのメソッドを集計軸に使用するかを設定
     *
     * @param method スタンプシート実行ログの一覧を取得
     */
    public void setMethod(Boolean method) {
        this.method = method;
    }

    /**
     * マイクロサービスのメソッドを集計軸に使用するかを設定
     *
     * @param method スタンプシート実行ログの一覧を取得
     * @return this
     */
    public CountExecuteStampSheetLogRequest withMethod(Boolean method) {
        setMethod(method);
        return this;
    }

    /** ユーザIDを集計軸に使用するか */
    private Boolean userId;

    /**
     * ユーザIDを集計軸に使用するかを取得
     *
     * @return スタンプシート実行ログの一覧を取得
     */
    public Boolean getUserId() {
        return userId;
    }

    /**
     * ユーザIDを集計軸に使用するかを設定
     *
     * @param userId スタンプシート実行ログの一覧を取得
     */
    public void setUserId(Boolean userId) {
        this.userId = userId;
    }

    /**
     * ユーザIDを集計軸に使用するかを設定
     *
     * @param userId スタンプシート実行ログの一覧を取得
     * @return this
     */
    public CountExecuteStampSheetLogRequest withUserId(Boolean userId) {
        setUserId(userId);
        return this;
    }

    /** 報酬アクションの種類を集計軸に使用するか */
    private Boolean action;

    /**
     * 報酬アクションの種類を集計軸に使用するかを取得
     *
     * @return スタンプシート実行ログの一覧を取得
     */
    public Boolean getAction() {
        return action;
    }

    /**
     * 報酬アクションの種類を集計軸に使用するかを設定
     *
     * @param action スタンプシート実行ログの一覧を取得
     */
    public void setAction(Boolean action) {
        this.action = action;
    }

    /**
     * 報酬アクションの種類を集計軸に使用するかを設定
     *
     * @param action スタンプシート実行ログの一覧を取得
     * @return this
     */
    public CountExecuteStampSheetLogRequest withAction(Boolean action) {
        setAction(action);
        return this;
    }

    /** データの取得を開始する位置を指定するトークン */
    private String pageToken;

    /**
     * データの取得を開始する位置を指定するトークンを取得
     *
     * @return スタンプシート実行ログの一覧を取得
     */
    public String getPageToken() {
        return pageToken;
    }

    /**
     * データの取得を開始する位置を指定するトークンを設定
     *
     * @param pageToken スタンプシート実行ログの一覧を取得
     */
    public void setPageToken(String pageToken) {
        this.pageToken = pageToken;
    }

    /**
     * データの取得を開始する位置を指定するトークンを設定
     *
     * @param pageToken スタンプシート実行ログの一覧を取得
     * @return this
     */
    public CountExecuteStampSheetLogRequest withPageToken(String pageToken) {
        setPageToken(pageToken);
        return this;
    }

    /** データの取得件数 */
    private Long limit;

    /**
     * データの取得件数を取得
     *
     * @return スタンプシート実行ログの一覧を取得
     */
    public Long getLimit() {
        return limit;
    }

    /**
     * データの取得件数を設定
     *
     * @param limit スタンプシート実行ログの一覧を取得
     */
    public void setLimit(Long limit) {
        this.limit = limit;
    }

    /**
     * データの取得件数を設定
     *
     * @param limit スタンプシート実行ログの一覧を取得
     * @return this
     */
    public CountExecuteStampSheetLogRequest withLimit(Long limit) {
        setLimit(limit);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return スタンプシート実行ログの一覧を取得
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider スタンプシート実行ログの一覧を取得
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider スタンプシート実行ログの一覧を取得
     * @return this
     */
    public CountExecuteStampSheetLogRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

}