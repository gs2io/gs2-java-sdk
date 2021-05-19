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
import io.gs2.core.control.Gs2BasicRequest;

/**
 * オーナーIDを指定してセキュリティポリシーの一覧を取得します のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class DescribeCommonSecurityPoliciesRequest extends Gs2BasicRequest<DescribeCommonSecurityPoliciesRequest> {

    /** オーナーID */
    private String ownerId;

    /**
     * オーナーIDを取得
     *
     * @return オーナーIDを指定してセキュリティポリシーの一覧を取得します
     */
    public String getOwnerId() {
        return ownerId;
    }

    /**
     * オーナーIDを設定
     *
     * @param ownerId オーナーIDを指定してセキュリティポリシーの一覧を取得します
     */
    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    /**
     * オーナーIDを設定
     *
     * @param ownerId オーナーIDを指定してセキュリティポリシーの一覧を取得します
     * @return this
     */
    public DescribeCommonSecurityPoliciesRequest withOwnerId(String ownerId) {
        setOwnerId(ownerId);
        return this;
    }

    /** データの取得を開始する位置を指定するトークン */
    private String pageToken;

    /**
     * データの取得を開始する位置を指定するトークンを取得
     *
     * @return オーナーIDを指定してセキュリティポリシーの一覧を取得します
     */
    public String getPageToken() {
        return pageToken;
    }

    /**
     * データの取得を開始する位置を指定するトークンを設定
     *
     * @param pageToken オーナーIDを指定してセキュリティポリシーの一覧を取得します
     */
    public void setPageToken(String pageToken) {
        this.pageToken = pageToken;
    }

    /**
     * データの取得を開始する位置を指定するトークンを設定
     *
     * @param pageToken オーナーIDを指定してセキュリティポリシーの一覧を取得します
     * @return this
     */
    public DescribeCommonSecurityPoliciesRequest withPageToken(String pageToken) {
        setPageToken(pageToken);
        return this;
    }

    /** データの取得件数 */
    private Long limit;

    /**
     * データの取得件数を取得
     *
     * @return オーナーIDを指定してセキュリティポリシーの一覧を取得します
     */
    public Long getLimit() {
        return limit;
    }

    /**
     * データの取得件数を設定
     *
     * @param limit オーナーIDを指定してセキュリティポリシーの一覧を取得します
     */
    public void setLimit(Long limit) {
        this.limit = limit;
    }

    /**
     * データの取得件数を設定
     *
     * @param limit オーナーIDを指定してセキュリティポリシーの一覧を取得します
     * @return this
     */
    public DescribeCommonSecurityPoliciesRequest withLimit(Long limit) {
        setLimit(limit);
        return this;
    }

}