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

package io.gs2.quest.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.quest.model.*;
import io.gs2.control.Gs2BasicRequest;

/**
 * ユーザIDを指定してクエスト進行を削除 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class DeleteCompletedQuestListByUserIdRequest extends Gs2BasicRequest<DeleteCompletedQuestListByUserIdRequest> {

    /** カテゴリ名 */
    private String namespaceName;

    /**
     * カテゴリ名を取得
     *
     * @return ユーザIDを指定してクエスト進行を削除
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * カテゴリ名を設定
     *
     * @param namespaceName ユーザIDを指定してクエスト進行を削除
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * カテゴリ名を設定
     *
     * @param namespaceName ユーザIDを指定してクエスト進行を削除
     * @return this
     */
    public DeleteCompletedQuestListByUserIdRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** クエストグループモデル名 */
    private String questGroupName;

    /**
     * クエストグループモデル名を取得
     *
     * @return ユーザIDを指定してクエスト進行を削除
     */
    public String getQuestGroupName() {
        return questGroupName;
    }

    /**
     * クエストグループモデル名を設定
     *
     * @param questGroupName ユーザIDを指定してクエスト進行を削除
     */
    public void setQuestGroupName(String questGroupName) {
        this.questGroupName = questGroupName;
    }

    /**
     * クエストグループモデル名を設定
     *
     * @param questGroupName ユーザIDを指定してクエスト進行を削除
     * @return this
     */
    public DeleteCompletedQuestListByUserIdRequest withQuestGroupName(String questGroupName) {
        setQuestGroupName(questGroupName);
        return this;
    }

    /** ユーザーID */
    private String userId;

    /**
     * ユーザーIDを取得
     *
     * @return ユーザIDを指定してクエスト進行を削除
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId ユーザIDを指定してクエスト進行を削除
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId ユーザIDを指定してクエスト進行を削除
     * @return this
     */
    public DeleteCompletedQuestListByUserIdRequest withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return ユーザIDを指定してクエスト進行を削除
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ユーザIDを指定してクエスト進行を削除
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ユーザIDを指定してクエスト進行を削除
     * @return this
     */
    public DeleteCompletedQuestListByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

}