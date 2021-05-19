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

package io.gs2.datastore.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.datastore.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * ユーザIDを指定してデータオブジェクトを更新する のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class UpdateDataObjectByUserIdRequest extends Gs2BasicRequest<UpdateDataObjectByUserIdRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return ユーザIDを指定してデータオブジェクトを更新する
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ユーザIDを指定してデータオブジェクトを更新する
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ユーザIDを指定してデータオブジェクトを更新する
     * @return this
     */
    public UpdateDataObjectByUserIdRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** データの名前 */
    private String dataObjectName;

    /**
     * データの名前を取得
     *
     * @return ユーザIDを指定してデータオブジェクトを更新する
     */
    public String getDataObjectName() {
        return dataObjectName;
    }

    /**
     * データの名前を設定
     *
     * @param dataObjectName ユーザIDを指定してデータオブジェクトを更新する
     */
    public void setDataObjectName(String dataObjectName) {
        this.dataObjectName = dataObjectName;
    }

    /**
     * データの名前を設定
     *
     * @param dataObjectName ユーザIDを指定してデータオブジェクトを更新する
     * @return this
     */
    public UpdateDataObjectByUserIdRequest withDataObjectName(String dataObjectName) {
        setDataObjectName(dataObjectName);
        return this;
    }

    /** ユーザーID */
    private String userId;

    /**
     * ユーザーIDを取得
     *
     * @return ユーザIDを指定してデータオブジェクトを更新する
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId ユーザIDを指定してデータオブジェクトを更新する
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId ユーザIDを指定してデータオブジェクトを更新する
     * @return this
     */
    public UpdateDataObjectByUserIdRequest withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    /** ファイルのアクセス権 */
    private String scope;

    /**
     * ファイルのアクセス権を取得
     *
     * @return ユーザIDを指定してデータオブジェクトを更新する
     */
    public String getScope() {
        return scope;
    }

    /**
     * ファイルのアクセス権を設定
     *
     * @param scope ユーザIDを指定してデータオブジェクトを更新する
     */
    public void setScope(String scope) {
        this.scope = scope;
    }

    /**
     * ファイルのアクセス権を設定
     *
     * @param scope ユーザIDを指定してデータオブジェクトを更新する
     * @return this
     */
    public UpdateDataObjectByUserIdRequest withScope(String scope) {
        setScope(scope);
        return this;
    }

    /** 公開するユーザIDリスト */
    private List<String> allowUserIds;

    /**
     * 公開するユーザIDリストを取得
     *
     * @return ユーザIDを指定してデータオブジェクトを更新する
     */
    public List<String> getAllowUserIds() {
        return allowUserIds;
    }

    /**
     * 公開するユーザIDリストを設定
     *
     * @param allowUserIds ユーザIDを指定してデータオブジェクトを更新する
     */
    public void setAllowUserIds(List<String> allowUserIds) {
        this.allowUserIds = allowUserIds;
    }

    /**
     * 公開するユーザIDリストを設定
     *
     * @param allowUserIds ユーザIDを指定してデータオブジェクトを更新する
     * @return this
     */
    public UpdateDataObjectByUserIdRequest withAllowUserIds(List<String> allowUserIds) {
        setAllowUserIds(allowUserIds);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return ユーザIDを指定してデータオブジェクトを更新する
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ユーザIDを指定してデータオブジェクトを更新する
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ユーザIDを指定してデータオブジェクトを更新する
     * @return this
     */
    public UpdateDataObjectByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

}