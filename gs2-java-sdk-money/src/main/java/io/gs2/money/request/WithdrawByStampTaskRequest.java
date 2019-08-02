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
 * ウォレットから残高を消費します のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class WithdrawByStampTaskRequest extends Gs2BasicRequest<WithdrawByStampTaskRequest> {

    /** スタンプタスク */
    private String stampTask;

    /**
     * スタンプタスクを取得
     *
     * @return ウォレットから残高を消費します
     */
    public String getStampTask() {
        return stampTask;
    }

    /**
     * スタンプタスクを設定
     *
     * @param stampTask ウォレットから残高を消費します
     */
    public void setStampTask(String stampTask) {
        this.stampTask = stampTask;
    }

    /**
     * スタンプタスクを設定
     *
     * @param stampTask ウォレットから残高を消費します
     * @return this
     */
    public WithdrawByStampTaskRequest withStampTask(String stampTask) {
        setStampTask(stampTask);
        return this;
    }

    /** スタンプタスクの署名検証に使用する 暗号鍵 のGRN */
    private String keyId;

    /**
     * スタンプタスクの署名検証に使用する 暗号鍵 のGRNを取得
     *
     * @return ウォレットから残高を消費します
     */
    public String getKeyId() {
        return keyId;
    }

    /**
     * スタンプタスクの署名検証に使用する 暗号鍵 のGRNを設定
     *
     * @param keyId ウォレットから残高を消費します
     */
    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    /**
     * スタンプタスクの署名検証に使用する 暗号鍵 のGRNを設定
     *
     * @param keyId ウォレットから残高を消費します
     * @return this
     */
    public WithdrawByStampTaskRequest withKeyId(String keyId) {
        setKeyId(keyId);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return ウォレットから残高を消費します
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ウォレットから残高を消費します
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ウォレットから残高を消費します
     * @return this
     */
    public WithdrawByStampTaskRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

}