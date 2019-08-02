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

package io.gs2.deploy.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.deploy.model.*;
import io.gs2.control.Gs2BasicRequest;

/**
 * 発生したイベントの一覧を取得 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class DescribeEventsRequest extends Gs2BasicRequest<DescribeEventsRequest> {

    /** スタック名 */
    private String stackName;

    /**
     * スタック名を取得
     *
     * @return 発生したイベントの一覧を取得
     */
    public String getStackName() {
        return stackName;
    }

    /**
     * スタック名を設定
     *
     * @param stackName 発生したイベントの一覧を取得
     */
    public void setStackName(String stackName) {
        this.stackName = stackName;
    }

    /**
     * スタック名を設定
     *
     * @param stackName 発生したイベントの一覧を取得
     * @return this
     */
    public DescribeEventsRequest withStackName(String stackName) {
        setStackName(stackName);
        return this;
    }

    /** データの取得を開始する位置を指定するトークン */
    private String pageToken;

    /**
     * データの取得を開始する位置を指定するトークンを取得
     *
     * @return 発生したイベントの一覧を取得
     */
    public String getPageToken() {
        return pageToken;
    }

    /**
     * データの取得を開始する位置を指定するトークンを設定
     *
     * @param pageToken 発生したイベントの一覧を取得
     */
    public void setPageToken(String pageToken) {
        this.pageToken = pageToken;
    }

    /**
     * データの取得を開始する位置を指定するトークンを設定
     *
     * @param pageToken 発生したイベントの一覧を取得
     * @return this
     */
    public DescribeEventsRequest withPageToken(String pageToken) {
        setPageToken(pageToken);
        return this;
    }

    /** データの取得件数 */
    private Long limit;

    /**
     * データの取得件数を取得
     *
     * @return 発生したイベントの一覧を取得
     */
    public Long getLimit() {
        return limit;
    }

    /**
     * データの取得件数を設定
     *
     * @param limit 発生したイベントの一覧を取得
     */
    public void setLimit(Long limit) {
        this.limit = limit;
    }

    /**
     * データの取得件数を設定
     *
     * @param limit 発生したイベントの一覧を取得
     * @return this
     */
    public DescribeEventsRequest withLimit(Long limit) {
        setLimit(limit);
        return this;
    }

}