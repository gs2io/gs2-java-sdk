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
 * スタンプシート発行ログの一覧を取得 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class QueryIssueStampSheetLogRequest extends Gs2BasicRequest<QueryIssueStampSheetLogRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return スタンプシート発行ログの一覧を取得
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName スタンプシート発行ログの一覧を取得
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName スタンプシート発行ログの一覧を取得
     * @return this
     */
    public QueryIssueStampSheetLogRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** マイクロサービスの種類 */
    private String service;

    /**
     * マイクロサービスの種類を取得
     *
     * @return スタンプシート発行ログの一覧を取得
     */
    public String getService() {
        return service;
    }

    /**
     * マイクロサービスの種類を設定
     *
     * @param service スタンプシート発行ログの一覧を取得
     */
    public void setService(String service) {
        this.service = service;
    }

    /**
     * マイクロサービスの種類を設定
     *
     * @param service スタンプシート発行ログの一覧を取得
     * @return this
     */
    public QueryIssueStampSheetLogRequest withService(String service) {
        setService(service);
        return this;
    }

    /** マイクロサービスのメソッド */
    private String method;

    /**
     * マイクロサービスのメソッドを取得
     *
     * @return スタンプシート発行ログの一覧を取得
     */
    public String getMethod() {
        return method;
    }

    /**
     * マイクロサービスのメソッドを設定
     *
     * @param method スタンプシート発行ログの一覧を取得
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * マイクロサービスのメソッドを設定
     *
     * @param method スタンプシート発行ログの一覧を取得
     * @return this
     */
    public QueryIssueStampSheetLogRequest withMethod(String method) {
        setMethod(method);
        return this;
    }

    /** ユーザーID */
    private String userId;

    /**
     * ユーザーIDを取得
     *
     * @return スタンプシート発行ログの一覧を取得
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId スタンプシート発行ログの一覧を取得
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId スタンプシート発行ログの一覧を取得
     * @return this
     */
    public QueryIssueStampSheetLogRequest withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    /** 報酬アクション */
    private String action;

    /**
     * 報酬アクションを取得
     *
     * @return スタンプシート発行ログの一覧を取得
     */
    public String getAction() {
        return action;
    }

    /**
     * 報酬アクションを設定
     *
     * @param action スタンプシート発行ログの一覧を取得
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * 報酬アクションを設定
     *
     * @param action スタンプシート発行ログの一覧を取得
     * @return this
     */
    public QueryIssueStampSheetLogRequest withAction(String action) {
        setAction(action);
        return this;
    }

    /** 検索範囲開始日時 */
    private Long begin;

    /**
     * 検索範囲開始日時を取得
     *
     * @return スタンプシート発行ログの一覧を取得
     */
    public Long getBegin() {
        return begin;
    }

    /**
     * 検索範囲開始日時を設定
     *
     * @param begin スタンプシート発行ログの一覧を取得
     */
    public void setBegin(Long begin) {
        this.begin = begin;
    }

    /**
     * 検索範囲開始日時を設定
     *
     * @param begin スタンプシート発行ログの一覧を取得
     * @return this
     */
    public QueryIssueStampSheetLogRequest withBegin(Long begin) {
        setBegin(begin);
        return this;
    }

    /** 検索範囲終了日時 */
    private Long end;

    /**
     * 検索範囲終了日時を取得
     *
     * @return スタンプシート発行ログの一覧を取得
     */
    public Long getEnd() {
        return end;
    }

    /**
     * 検索範囲終了日時を設定
     *
     * @param end スタンプシート発行ログの一覧を取得
     */
    public void setEnd(Long end) {
        this.end = end;
    }

    /**
     * 検索範囲終了日時を設定
     *
     * @param end スタンプシート発行ログの一覧を取得
     * @return this
     */
    public QueryIssueStampSheetLogRequest withEnd(Long end) {
        setEnd(end);
        return this;
    }

    /** 7日より長い期間のログを検索対象とするか */
    private Boolean longTerm;

    /**
     * 7日より長い期間のログを検索対象とするかを取得
     *
     * @return スタンプシート発行ログの一覧を取得
     */
    public Boolean getLongTerm() {
        return longTerm;
    }

    /**
     * 7日より長い期間のログを検索対象とするかを設定
     *
     * @param longTerm スタンプシート発行ログの一覧を取得
     */
    public void setLongTerm(Boolean longTerm) {
        this.longTerm = longTerm;
    }

    /**
     * 7日より長い期間のログを検索対象とするかを設定
     *
     * @param longTerm スタンプシート発行ログの一覧を取得
     * @return this
     */
    public QueryIssueStampSheetLogRequest withLongTerm(Boolean longTerm) {
        setLongTerm(longTerm);
        return this;
    }

    /** データの取得を開始する位置を指定するトークン */
    private String pageToken;

    /**
     * データの取得を開始する位置を指定するトークンを取得
     *
     * @return スタンプシート発行ログの一覧を取得
     */
    public String getPageToken() {
        return pageToken;
    }

    /**
     * データの取得を開始する位置を指定するトークンを設定
     *
     * @param pageToken スタンプシート発行ログの一覧を取得
     */
    public void setPageToken(String pageToken) {
        this.pageToken = pageToken;
    }

    /**
     * データの取得を開始する位置を指定するトークンを設定
     *
     * @param pageToken スタンプシート発行ログの一覧を取得
     * @return this
     */
    public QueryIssueStampSheetLogRequest withPageToken(String pageToken) {
        setPageToken(pageToken);
        return this;
    }

    /** データの取得件数 */
    private Long limit;

    /**
     * データの取得件数を取得
     *
     * @return スタンプシート発行ログの一覧を取得
     */
    public Long getLimit() {
        return limit;
    }

    /**
     * データの取得件数を設定
     *
     * @param limit スタンプシート発行ログの一覧を取得
     */
    public void setLimit(Long limit) {
        this.limit = limit;
    }

    /**
     * データの取得件数を設定
     *
     * @param limit スタンプシート発行ログの一覧を取得
     * @return this
     */
    public QueryIssueStampSheetLogRequest withLimit(Long limit) {
        setLimit(limit);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return スタンプシート発行ログの一覧を取得
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider スタンプシート発行ログの一覧を取得
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider スタンプシート発行ログの一覧を取得
     * @return this
     */
    public QueryIssueStampSheetLogRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

}