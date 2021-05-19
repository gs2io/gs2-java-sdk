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
 * ユーザIDを指定してデータオブジェクトをアップロード準備する のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class PrepareUploadByUserIdRequest extends Gs2BasicRequest<PrepareUploadByUserIdRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return ユーザIDを指定してデータオブジェクトをアップロード準備する
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ユーザIDを指定してデータオブジェクトをアップロード準備する
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ユーザIDを指定してデータオブジェクトをアップロード準備する
     * @return this
     */
    public PrepareUploadByUserIdRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** ユーザーID */
    private String userId;

    /**
     * ユーザーIDを取得
     *
     * @return ユーザIDを指定してデータオブジェクトをアップロード準備する
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId ユーザIDを指定してデータオブジェクトをアップロード準備する
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId ユーザIDを指定してデータオブジェクトをアップロード準備する
     * @return this
     */
    public PrepareUploadByUserIdRequest withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    /** データの名前 */
    private String name;

    /**
     * データの名前を取得
     *
     * @return ユーザIDを指定してデータオブジェクトをアップロード準備する
     */
    public String getName() {
        return name;
    }

    /**
     * データの名前を設定
     *
     * @param name ユーザIDを指定してデータオブジェクトをアップロード準備する
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * データの名前を設定
     *
     * @param name ユーザIDを指定してデータオブジェクトをアップロード準備する
     * @return this
     */
    public PrepareUploadByUserIdRequest withName(String name) {
        setName(name);
        return this;
    }

    /** アップロードするデータの MIME-Type */
    private String contentType;

    /**
     * アップロードするデータの MIME-Typeを取得
     *
     * @return ユーザIDを指定してデータオブジェクトをアップロード準備する
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * アップロードするデータの MIME-Typeを設定
     *
     * @param contentType ユーザIDを指定してデータオブジェクトをアップロード準備する
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    /**
     * アップロードするデータの MIME-Typeを設定
     *
     * @param contentType ユーザIDを指定してデータオブジェクトをアップロード準備する
     * @return this
     */
    public PrepareUploadByUserIdRequest withContentType(String contentType) {
        setContentType(contentType);
        return this;
    }

    /** ファイルのアクセス権 */
    private String scope;

    /**
     * ファイルのアクセス権を取得
     *
     * @return ユーザIDを指定してデータオブジェクトをアップロード準備する
     */
    public String getScope() {
        return scope;
    }

    /**
     * ファイルのアクセス権を設定
     *
     * @param scope ユーザIDを指定してデータオブジェクトをアップロード準備する
     */
    public void setScope(String scope) {
        this.scope = scope;
    }

    /**
     * ファイルのアクセス権を設定
     *
     * @param scope ユーザIDを指定してデータオブジェクトをアップロード準備する
     * @return this
     */
    public PrepareUploadByUserIdRequest withScope(String scope) {
        setScope(scope);
        return this;
    }

    /** 公開するユーザIDリスト */
    private List<String> allowUserIds;

    /**
     * 公開するユーザIDリストを取得
     *
     * @return ユーザIDを指定してデータオブジェクトをアップロード準備する
     */
    public List<String> getAllowUserIds() {
        return allowUserIds;
    }

    /**
     * 公開するユーザIDリストを設定
     *
     * @param allowUserIds ユーザIDを指定してデータオブジェクトをアップロード準備する
     */
    public void setAllowUserIds(List<String> allowUserIds) {
        this.allowUserIds = allowUserIds;
    }

    /**
     * 公開するユーザIDリストを設定
     *
     * @param allowUserIds ユーザIDを指定してデータオブジェクトをアップロード準備する
     * @return this
     */
    public PrepareUploadByUserIdRequest withAllowUserIds(List<String> allowUserIds) {
        setAllowUserIds(allowUserIds);
        return this;
    }

    /** 既にデータが存在する場合にエラーとするか、データを更新するか */
    private Boolean updateIfExists;

    /**
     * 既にデータが存在する場合にエラーとするか、データを更新するかを取得
     *
     * @return ユーザIDを指定してデータオブジェクトをアップロード準備する
     */
    public Boolean getUpdateIfExists() {
        return updateIfExists;
    }

    /**
     * 既にデータが存在する場合にエラーとするか、データを更新するかを設定
     *
     * @param updateIfExists ユーザIDを指定してデータオブジェクトをアップロード準備する
     */
    public void setUpdateIfExists(Boolean updateIfExists) {
        this.updateIfExists = updateIfExists;
    }

    /**
     * 既にデータが存在する場合にエラーとするか、データを更新するかを設定
     *
     * @param updateIfExists ユーザIDを指定してデータオブジェクトをアップロード準備する
     * @return this
     */
    public PrepareUploadByUserIdRequest withUpdateIfExists(Boolean updateIfExists) {
        setUpdateIfExists(updateIfExists);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return ユーザIDを指定してデータオブジェクトをアップロード準備する
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ユーザIDを指定してデータオブジェクトをアップロード準備する
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ユーザIDを指定してデータオブジェクトをアップロード準備する
     * @return this
     */
    public PrepareUploadByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

}