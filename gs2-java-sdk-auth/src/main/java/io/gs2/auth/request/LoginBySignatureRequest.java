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

package io.gs2.auth.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.auth.model.*;
import io.gs2.control.Gs2BasicRequest;

/**
 * 指定したユーザIDでGS2にログインし、アクセストークンを取得します のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class LoginBySignatureRequest extends Gs2BasicRequest<LoginBySignatureRequest> {

    /** ユーザーID */
    private String userId;

    /**
     * ユーザーIDを取得
     *
     * @return 指定したユーザIDでGS2にログインし、アクセストークンを取得します
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId 指定したユーザIDでGS2にログインし、アクセストークンを取得します
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId 指定したユーザIDでGS2にログインし、アクセストークンを取得します
     * @return this
     */
    public LoginBySignatureRequest withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    /** 署名の作成に使用した暗号鍵 のGRN */
    private String keyId;

    /**
     * 署名の作成に使用した暗号鍵 のGRNを取得
     *
     * @return 指定したユーザIDでGS2にログインし、アクセストークンを取得します
     */
    public String getKeyId() {
        return keyId;
    }

    /**
     * 署名の作成に使用した暗号鍵 のGRNを設定
     *
     * @param keyId 指定したユーザIDでGS2にログインし、アクセストークンを取得します
     */
    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    /**
     * 署名の作成に使用した暗号鍵 のGRNを設定
     *
     * @param keyId 指定したユーザIDでGS2にログインし、アクセストークンを取得します
     * @return this
     */
    public LoginBySignatureRequest withKeyId(String keyId) {
        setKeyId(keyId);
        return this;
    }

    /** アカウント認証情報の署名対象 */
    private String body;

    /**
     * アカウント認証情報の署名対象を取得
     *
     * @return 指定したユーザIDでGS2にログインし、アクセストークンを取得します
     */
    public String getBody() {
        return body;
    }

    /**
     * アカウント認証情報の署名対象を設定
     *
     * @param body 指定したユーザIDでGS2にログインし、アクセストークンを取得します
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     * アカウント認証情報の署名対象を設定
     *
     * @param body 指定したユーザIDでGS2にログインし、アクセストークンを取得します
     * @return this
     */
    public LoginBySignatureRequest withBody(String body) {
        setBody(body);
        return this;
    }

    /** 署名 */
    private String signature;

    /**
     * 署名を取得
     *
     * @return 指定したユーザIDでGS2にログインし、アクセストークンを取得します
     */
    public String getSignature() {
        return signature;
    }

    /**
     * 署名を設定
     *
     * @param signature 指定したユーザIDでGS2にログインし、アクセストークンを取得します
     */
    public void setSignature(String signature) {
        this.signature = signature;
    }

    /**
     * 署名を設定
     *
     * @param signature 指定したユーザIDでGS2にログインし、アクセストークンを取得します
     * @return this
     */
    public LoginBySignatureRequest withSignature(String signature) {
        setSignature(signature);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return 指定したユーザIDでGS2にログインし、アクセストークンを取得します
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider 指定したユーザIDでGS2にログインし、アクセストークンを取得します
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider 指定したユーザIDでGS2にログインし、アクセストークンを取得します
     * @return this
     */
    public LoginBySignatureRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

}