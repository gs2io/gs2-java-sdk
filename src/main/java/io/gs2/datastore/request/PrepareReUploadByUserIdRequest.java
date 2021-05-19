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
 * ユーザIDを指定してデータオブジェクトを再アップロード準備する のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class PrepareReUploadByUserIdRequest extends Gs2BasicRequest<PrepareReUploadByUserIdRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return ユーザIDを指定してデータオブジェクトを再アップロード準備する
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ユーザIDを指定してデータオブジェクトを再アップロード準備する
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ユーザIDを指定してデータオブジェクトを再アップロード準備する
     * @return this
     */
    public PrepareReUploadByUserIdRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** データの名前 */
    private String dataObjectName;

    /**
     * データの名前を取得
     *
     * @return ユーザIDを指定してデータオブジェクトを再アップロード準備する
     */
    public String getDataObjectName() {
        return dataObjectName;
    }

    /**
     * データの名前を設定
     *
     * @param dataObjectName ユーザIDを指定してデータオブジェクトを再アップロード準備する
     */
    public void setDataObjectName(String dataObjectName) {
        this.dataObjectName = dataObjectName;
    }

    /**
     * データの名前を設定
     *
     * @param dataObjectName ユーザIDを指定してデータオブジェクトを再アップロード準備する
     * @return this
     */
    public PrepareReUploadByUserIdRequest withDataObjectName(String dataObjectName) {
        setDataObjectName(dataObjectName);
        return this;
    }

    /** ユーザーID */
    private String userId;

    /**
     * ユーザーIDを取得
     *
     * @return ユーザIDを指定してデータオブジェクトを再アップロード準備する
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId ユーザIDを指定してデータオブジェクトを再アップロード準備する
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId ユーザIDを指定してデータオブジェクトを再アップロード準備する
     * @return this
     */
    public PrepareReUploadByUserIdRequest withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    /** アップロードするデータの MIME-Type */
    private String contentType;

    /**
     * アップロードするデータの MIME-Typeを取得
     *
     * @return ユーザIDを指定してデータオブジェクトを再アップロード準備する
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * アップロードするデータの MIME-Typeを設定
     *
     * @param contentType ユーザIDを指定してデータオブジェクトを再アップロード準備する
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    /**
     * アップロードするデータの MIME-Typeを設定
     *
     * @param contentType ユーザIDを指定してデータオブジェクトを再アップロード準備する
     * @return this
     */
    public PrepareReUploadByUserIdRequest withContentType(String contentType) {
        setContentType(contentType);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return ユーザIDを指定してデータオブジェクトを再アップロード準備する
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ユーザIDを指定してデータオブジェクトを再アップロード準備する
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ユーザIDを指定してデータオブジェクトを再アップロード準備する
     * @return this
     */
    public PrepareReUploadByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

}