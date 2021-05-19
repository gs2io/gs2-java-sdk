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
import io.gs2.core.control.Gs2BasicRequest;

/**
 * 指定したユーザIDでGS2にログインし、アクセストークンを取得します のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class LoginRequest extends Gs2BasicRequest<LoginRequest> {

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
    public LoginRequest withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    /** 現在時刻に対する補正値（現在時刻を起点とした秒数） */
    private Integer timeOffset;

    /**
     * 現在時刻に対する補正値（現在時刻を起点とした秒数）を取得
     *
     * @return 指定したユーザIDでGS2にログインし、アクセストークンを取得します
     */
    public Integer getTimeOffset() {
        return timeOffset;
    }

    /**
     * 現在時刻に対する補正値（現在時刻を起点とした秒数）を設定
     *
     * @param timeOffset 指定したユーザIDでGS2にログインし、アクセストークンを取得します
     */
    public void setTimeOffset(Integer timeOffset) {
        this.timeOffset = timeOffset;
    }

    /**
     * 現在時刻に対する補正値（現在時刻を起点とした秒数）を設定
     *
     * @param timeOffset 指定したユーザIDでGS2にログインし、アクセストークンを取得します
     * @return this
     */
    public LoginRequest withTimeOffset(Integer timeOffset) {
        setTimeOffset(timeOffset);
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
    public LoginRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

}