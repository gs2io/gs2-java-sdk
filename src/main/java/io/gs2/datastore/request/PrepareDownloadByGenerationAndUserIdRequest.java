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
 * ユーザIDを指定してデータオブジェクトを世代を指定してダウンロード準備する のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class PrepareDownloadByGenerationAndUserIdRequest extends Gs2BasicRequest<PrepareDownloadByGenerationAndUserIdRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return ユーザIDを指定してデータオブジェクトを世代を指定してダウンロード準備する
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ユーザIDを指定してデータオブジェクトを世代を指定してダウンロード準備する
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ユーザIDを指定してデータオブジェクトを世代を指定してダウンロード準備する
     * @return this
     */
    public PrepareDownloadByGenerationAndUserIdRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** ユーザーID */
    private String userId;

    /**
     * ユーザーIDを取得
     *
     * @return ユーザIDを指定してデータオブジェクトを世代を指定してダウンロード準備する
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId ユーザIDを指定してデータオブジェクトを世代を指定してダウンロード準備する
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId ユーザIDを指定してデータオブジェクトを世代を指定してダウンロード準備する
     * @return this
     */
    public PrepareDownloadByGenerationAndUserIdRequest withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    /** データオブジェクト */
    private String dataObjectId;

    /**
     * データオブジェクトを取得
     *
     * @return ユーザIDを指定してデータオブジェクトを世代を指定してダウンロード準備する
     */
    public String getDataObjectId() {
        return dataObjectId;
    }

    /**
     * データオブジェクトを設定
     *
     * @param dataObjectId ユーザIDを指定してデータオブジェクトを世代を指定してダウンロード準備する
     */
    public void setDataObjectId(String dataObjectId) {
        this.dataObjectId = dataObjectId;
    }

    /**
     * データオブジェクトを設定
     *
     * @param dataObjectId ユーザIDを指定してデータオブジェクトを世代を指定してダウンロード準備する
     * @return this
     */
    public PrepareDownloadByGenerationAndUserIdRequest withDataObjectId(String dataObjectId) {
        setDataObjectId(dataObjectId);
        return this;
    }

    /** 世代 */
    private String generation;

    /**
     * 世代を取得
     *
     * @return ユーザIDを指定してデータオブジェクトを世代を指定してダウンロード準備する
     */
    public String getGeneration() {
        return generation;
    }

    /**
     * 世代を設定
     *
     * @param generation ユーザIDを指定してデータオブジェクトを世代を指定してダウンロード準備する
     */
    public void setGeneration(String generation) {
        this.generation = generation;
    }

    /**
     * 世代を設定
     *
     * @param generation ユーザIDを指定してデータオブジェクトを世代を指定してダウンロード準備する
     * @return this
     */
    public PrepareDownloadByGenerationAndUserIdRequest withGeneration(String generation) {
        setGeneration(generation);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return ユーザIDを指定してデータオブジェクトを世代を指定してダウンロード準備する
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ユーザIDを指定してデータオブジェクトを世代を指定してダウンロード準備する
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ユーザIDを指定してデータオブジェクトを世代を指定してダウンロード準備する
     * @return this
     */
    public PrepareDownloadByGenerationAndUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

}