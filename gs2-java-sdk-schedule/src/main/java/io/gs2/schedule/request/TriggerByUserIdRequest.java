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

package io.gs2.schedule.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.schedule.model.*;
import io.gs2.control.Gs2BasicRequest;

/**
 * ユーザIDを指定してトリガーを登録 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class TriggerByUserIdRequest extends Gs2BasicRequest<TriggerByUserIdRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return ユーザIDを指定してトリガーを登録
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ユーザIDを指定してトリガーを登録
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ユーザIDを指定してトリガーを登録
     * @return this
     */
    public TriggerByUserIdRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** トリガーの名前 */
    private String triggerName;

    /**
     * トリガーの名前を取得
     *
     * @return ユーザIDを指定してトリガーを登録
     */
    public String getTriggerName() {
        return triggerName;
    }

    /**
     * トリガーの名前を設定
     *
     * @param triggerName ユーザIDを指定してトリガーを登録
     */
    public void setTriggerName(String triggerName) {
        this.triggerName = triggerName;
    }

    /**
     * トリガーの名前を設定
     *
     * @param triggerName ユーザIDを指定してトリガーを登録
     * @return this
     */
    public TriggerByUserIdRequest withTriggerName(String triggerName) {
        setTriggerName(triggerName);
        return this;
    }

    /** ユーザーID */
    private String userId;

    /**
     * ユーザーIDを取得
     *
     * @return ユーザIDを指定してトリガーを登録
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId ユーザIDを指定してトリガーを登録
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId ユーザIDを指定してトリガーを登録
     * @return this
     */
    public TriggerByUserIdRequest withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    /** トリガーの引き方の方針 */
    private String triggerStrategy;

    /**
     * トリガーの引き方の方針を取得
     *
     * @return ユーザIDを指定してトリガーを登録
     */
    public String getTriggerStrategy() {
        return triggerStrategy;
    }

    /**
     * トリガーの引き方の方針を設定
     *
     * @param triggerStrategy ユーザIDを指定してトリガーを登録
     */
    public void setTriggerStrategy(String triggerStrategy) {
        this.triggerStrategy = triggerStrategy;
    }

    /**
     * トリガーの引き方の方針を設定
     *
     * @param triggerStrategy ユーザIDを指定してトリガーを登録
     * @return this
     */
    public TriggerByUserIdRequest withTriggerStrategy(String triggerStrategy) {
        setTriggerStrategy(triggerStrategy);
        return this;
    }

    /** トリガーの有効期限(秒) */
    private Integer ttl;

    /**
     * トリガーの有効期限(秒)を取得
     *
     * @return ユーザIDを指定してトリガーを登録
     */
    public Integer getTtl() {
        return ttl;
    }

    /**
     * トリガーの有効期限(秒)を設定
     *
     * @param ttl ユーザIDを指定してトリガーを登録
     */
    public void setTtl(Integer ttl) {
        this.ttl = ttl;
    }

    /**
     * トリガーの有効期限(秒)を設定
     *
     * @param ttl ユーザIDを指定してトリガーを登録
     * @return this
     */
    public TriggerByUserIdRequest withTtl(Integer ttl) {
        setTtl(ttl);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return ユーザIDを指定してトリガーを登録
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ユーザIDを指定してトリガーを登録
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ユーザIDを指定してトリガーを登録
     * @return this
     */
    public TriggerByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

}