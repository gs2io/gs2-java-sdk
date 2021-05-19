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

package io.gs2.formation.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.formation.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * 署名付きフォームを取得 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class GetFormWithSignatureRequest extends Gs2BasicRequest<GetFormWithSignatureRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return 署名付きフォームを取得
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 署名付きフォームを取得
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 署名付きフォームを取得
     * @return this
     */
    public GetFormWithSignatureRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** フォームの保存領域の名前 */
    private String moldName;

    /**
     * フォームの保存領域の名前を取得
     *
     * @return 署名付きフォームを取得
     */
    public String getMoldName() {
        return moldName;
    }

    /**
     * フォームの保存領域の名前を設定
     *
     * @param moldName 署名付きフォームを取得
     */
    public void setMoldName(String moldName) {
        this.moldName = moldName;
    }

    /**
     * フォームの保存領域の名前を設定
     *
     * @param moldName 署名付きフォームを取得
     * @return this
     */
    public GetFormWithSignatureRequest withMoldName(String moldName) {
        setMoldName(moldName);
        return this;
    }

    /** 保存領域のインデックス */
    private Integer index;

    /**
     * 保存領域のインデックスを取得
     *
     * @return 署名付きフォームを取得
     */
    public Integer getIndex() {
        return index;
    }

    /**
     * 保存領域のインデックスを設定
     *
     * @param index 署名付きフォームを取得
     */
    public void setIndex(Integer index) {
        this.index = index;
    }

    /**
     * 保存領域のインデックスを設定
     *
     * @param index 署名付きフォームを取得
     * @return this
     */
    public GetFormWithSignatureRequest withIndex(Integer index) {
        setIndex(index);
        return this;
    }

    /** 署名の発行に使用する暗号鍵 のGRN */
    private String keyId;

    /**
     * 署名の発行に使用する暗号鍵 のGRNを取得
     *
     * @return 署名付きフォームを取得
     */
    public String getKeyId() {
        return keyId;
    }

    /**
     * 署名の発行に使用する暗号鍵 のGRNを設定
     *
     * @param keyId 署名付きフォームを取得
     */
    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    /**
     * 署名の発行に使用する暗号鍵 のGRNを設定
     *
     * @param keyId 署名付きフォームを取得
     * @return this
     */
    public GetFormWithSignatureRequest withKeyId(String keyId) {
        setKeyId(keyId);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return 署名付きフォームを取得
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider 署名付きフォームを取得
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider 署名付きフォームを取得
     * @return this
     */
    public GetFormWithSignatureRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

    /** アクセストークン */
    private String accessToken;

    /**
     * アクセストークンを取得
     *
     * @return アクセストークン
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * アクセストークンを設定
     *
     * @param accessToken アクセストークン
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * アクセストークンを設定
     *
     * @param accessToken アクセストークン
     * @return this
     */
    public GetFormWithSignatureRequest withAccessToken(String accessToken) {
        setAccessToken(accessToken);
        return this;
    }

}