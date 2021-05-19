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

package io.gs2.version.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.version.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * バージョンチェック のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CheckVersionByUserIdRequest extends Gs2BasicRequest<CheckVersionByUserIdRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return バージョンチェック
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName バージョンチェック
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName バージョンチェック
     * @return this
     */
    public CheckVersionByUserIdRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** ユーザーID */
    private String userId;

    /**
     * ユーザーIDを取得
     *
     * @return バージョンチェック
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId バージョンチェック
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId バージョンチェック
     * @return this
     */
    public CheckVersionByUserIdRequest withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    /** 加算するリソース */
    private List<TargetVersion> targetVersions;

    /**
     * 加算するリソースを取得
     *
     * @return バージョンチェック
     */
    public List<TargetVersion> getTargetVersions() {
        return targetVersions;
    }

    /**
     * 加算するリソースを設定
     *
     * @param targetVersions バージョンチェック
     */
    public void setTargetVersions(List<TargetVersion> targetVersions) {
        this.targetVersions = targetVersions;
    }

    /**
     * 加算するリソースを設定
     *
     * @param targetVersions バージョンチェック
     * @return this
     */
    public CheckVersionByUserIdRequest withTargetVersions(List<TargetVersion> targetVersions) {
        setTargetVersions(targetVersions);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return バージョンチェック
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider バージョンチェック
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider バージョンチェック
     * @return this
     */
    public CheckVersionByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

}