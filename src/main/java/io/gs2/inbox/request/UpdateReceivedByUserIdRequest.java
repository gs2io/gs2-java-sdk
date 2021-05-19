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

package io.gs2.inbox.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.inbox.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * ユーザーIDを指定して受信済みグローバルメッセージ名を削除 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class UpdateReceivedByUserIdRequest extends Gs2BasicRequest<UpdateReceivedByUserIdRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return ユーザーIDを指定して受信済みグローバルメッセージ名を削除
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ユーザーIDを指定して受信済みグローバルメッセージ名を削除
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ユーザーIDを指定して受信済みグローバルメッセージ名を削除
     * @return this
     */
    public UpdateReceivedByUserIdRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** ユーザーID */
    private String userId;

    /**
     * ユーザーIDを取得
     *
     * @return ユーザーIDを指定して受信済みグローバルメッセージ名を削除
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId ユーザーIDを指定して受信済みグローバルメッセージ名を削除
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId ユーザーIDを指定して受信済みグローバルメッセージ名を削除
     * @return this
     */
    public UpdateReceivedByUserIdRequest withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    /** 受信したグローバルメッセージ名 */
    private List<String> receivedGlobalMessageNames;

    /**
     * 受信したグローバルメッセージ名を取得
     *
     * @return ユーザーIDを指定して受信済みグローバルメッセージ名を削除
     */
    public List<String> getReceivedGlobalMessageNames() {
        return receivedGlobalMessageNames;
    }

    /**
     * 受信したグローバルメッセージ名を設定
     *
     * @param receivedGlobalMessageNames ユーザーIDを指定して受信済みグローバルメッセージ名を削除
     */
    public void setReceivedGlobalMessageNames(List<String> receivedGlobalMessageNames) {
        this.receivedGlobalMessageNames = receivedGlobalMessageNames;
    }

    /**
     * 受信したグローバルメッセージ名を設定
     *
     * @param receivedGlobalMessageNames ユーザーIDを指定して受信済みグローバルメッセージ名を削除
     * @return this
     */
    public UpdateReceivedByUserIdRequest withReceivedGlobalMessageNames(List<String> receivedGlobalMessageNames) {
        setReceivedGlobalMessageNames(receivedGlobalMessageNames);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return ユーザーIDを指定して受信済みグローバルメッセージ名を削除
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ユーザーIDを指定して受信済みグローバルメッセージ名を削除
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ユーザーIDを指定して受信済みグローバルメッセージ名を削除
     * @return this
     */
    public UpdateReceivedByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

}