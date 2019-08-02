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

package io.gs2.identifier.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.identifier.model.*;
import io.gs2.control.Gs2BasicRequest;

/**
 * クレデンシャルの一覧を取得します のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class DescribeIdentifiersRequest extends Gs2BasicRequest<DescribeIdentifiersRequest> {

    /** ユーザー名 */
    private String userName;

    /**
     * ユーザー名を取得
     *
     * @return クレデンシャルの一覧を取得します
     */
    public String getUserName() {
        return userName;
    }

    /**
     * ユーザー名を設定
     *
     * @param userName クレデンシャルの一覧を取得します
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * ユーザー名を設定
     *
     * @param userName クレデンシャルの一覧を取得します
     * @return this
     */
    public DescribeIdentifiersRequest withUserName(String userName) {
        setUserName(userName);
        return this;
    }

    /** データの取得を開始する位置を指定するトークン */
    private String pageToken;

    /**
     * データの取得を開始する位置を指定するトークンを取得
     *
     * @return クレデンシャルの一覧を取得します
     */
    public String getPageToken() {
        return pageToken;
    }

    /**
     * データの取得を開始する位置を指定するトークンを設定
     *
     * @param pageToken クレデンシャルの一覧を取得します
     */
    public void setPageToken(String pageToken) {
        this.pageToken = pageToken;
    }

    /**
     * データの取得を開始する位置を指定するトークンを設定
     *
     * @param pageToken クレデンシャルの一覧を取得します
     * @return this
     */
    public DescribeIdentifiersRequest withPageToken(String pageToken) {
        setPageToken(pageToken);
        return this;
    }

    /** データの取得件数 */
    private Long limit;

    /**
     * データの取得件数を取得
     *
     * @return クレデンシャルの一覧を取得します
     */
    public Long getLimit() {
        return limit;
    }

    /**
     * データの取得件数を設定
     *
     * @param limit クレデンシャルの一覧を取得します
     */
    public void setLimit(Long limit) {
        this.limit = limit;
    }

    /**
     * データの取得件数を設定
     *
     * @param limit クレデンシャルの一覧を取得します
     * @return this
     */
    public DescribeIdentifiersRequest withLimit(Long limit) {
        setLimit(limit);
        return this;
    }

}