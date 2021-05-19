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

package io.gs2.account.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.account.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * ゲームプレイヤーアカウントの現在時刻に対する補正値を更新 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class UpdateTimeOffsetRequest extends Gs2BasicRequest<UpdateTimeOffsetRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return ゲームプレイヤーアカウントの現在時刻に対する補正値を更新
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ゲームプレイヤーアカウントの現在時刻に対する補正値を更新
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ゲームプレイヤーアカウントの現在時刻に対する補正値を更新
     * @return this
     */
    public UpdateTimeOffsetRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** アカウントID */
    private String userId;

    /**
     * アカウントIDを取得
     *
     * @return ゲームプレイヤーアカウントの現在時刻に対する補正値を更新
     */
    public String getUserId() {
        return userId;
    }

    /**
     * アカウントIDを設定
     *
     * @param userId ゲームプレイヤーアカウントの現在時刻に対する補正値を更新
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * アカウントIDを設定
     *
     * @param userId ゲームプレイヤーアカウントの現在時刻に対する補正値を更新
     * @return this
     */
    public UpdateTimeOffsetRequest withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    /** 現在時刻に対する補正値（現在時刻を起点とした秒数） */
    private Integer timeOffset;

    /**
     * 現在時刻に対する補正値（現在時刻を起点とした秒数）を取得
     *
     * @return ゲームプレイヤーアカウントの現在時刻に対する補正値を更新
     */
    public Integer getTimeOffset() {
        return timeOffset;
    }

    /**
     * 現在時刻に対する補正値（現在時刻を起点とした秒数）を設定
     *
     * @param timeOffset ゲームプレイヤーアカウントの現在時刻に対する補正値を更新
     */
    public void setTimeOffset(Integer timeOffset) {
        this.timeOffset = timeOffset;
    }

    /**
     * 現在時刻に対する補正値（現在時刻を起点とした秒数）を設定
     *
     * @param timeOffset ゲームプレイヤーアカウントの現在時刻に対する補正値を更新
     * @return this
     */
    public UpdateTimeOffsetRequest withTimeOffset(Integer timeOffset) {
        setTimeOffset(timeOffset);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return ゲームプレイヤーアカウントの現在時刻に対する補正値を更新
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ゲームプレイヤーアカウントの現在時刻に対する補正値を更新
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ゲームプレイヤーアカウントの現在時刻に対する補正値を更新
     * @return this
     */
    public UpdateTimeOffsetRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

}