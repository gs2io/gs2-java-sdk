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

package io.gs2.money.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.money.model.*;
import io.gs2.control.Gs2BasicRequest;

/**
 * レシートを記録 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class RecordReceiptRequest extends Gs2BasicRequest<RecordReceiptRequest> {

    /** ネームスペースの名前 */
    private String namespaceName;

    /**
     * ネームスペースの名前を取得
     *
     * @return レシートを記録
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペースの名前を設定
     *
     * @param namespaceName レシートを記録
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペースの名前を設定
     *
     * @param namespaceName レシートを記録
     * @return this
     */
    public RecordReceiptRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** ユーザーID */
    private String userId;

    /**
     * ユーザーIDを取得
     *
     * @return レシートを記録
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId レシートを記録
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId レシートを記録
     * @return this
     */
    public RecordReceiptRequest withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    /** プラットフォームストアのコンテンツID */
    private String contentsId;

    /**
     * プラットフォームストアのコンテンツIDを取得
     *
     * @return レシートを記録
     */
    public String getContentsId() {
        return contentsId;
    }

    /**
     * プラットフォームストアのコンテンツIDを設定
     *
     * @param contentsId レシートを記録
     */
    public void setContentsId(String contentsId) {
        this.contentsId = contentsId;
    }

    /**
     * プラットフォームストアのコンテンツIDを設定
     *
     * @param contentsId レシートを記録
     * @return this
     */
    public RecordReceiptRequest withContentsId(String contentsId) {
        setContentsId(contentsId);
        return this;
    }

    /** レシート */
    private String receipt;

    /**
     * レシートを取得
     *
     * @return レシートを記録
     */
    public String getReceipt() {
        return receipt;
    }

    /**
     * レシートを設定
     *
     * @param receipt レシートを記録
     */
    public void setReceipt(String receipt) {
        this.receipt = receipt;
    }

    /**
     * レシートを設定
     *
     * @param receipt レシートを記録
     * @return this
     */
    public RecordReceiptRequest withReceipt(String receipt) {
        setReceipt(receipt);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return レシートを記録
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider レシートを記録
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider レシートを記録
     * @return this
     */
    public RecordReceiptRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

}