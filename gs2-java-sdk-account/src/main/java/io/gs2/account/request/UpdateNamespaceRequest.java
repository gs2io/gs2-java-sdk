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
import io.gs2.control.Gs2BasicRequest;

/**
 * ネームスペースを更新 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class UpdateNamespaceRequest extends Gs2BasicRequest<UpdateNamespaceRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return ネームスペースを更新
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ネームスペースを更新
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** 説明文 */
    private String description;

    /**
     * 説明文を取得
     *
     * @return ネームスペースを更新
     */
    public String getDescription() {
        return description;
    }

    /**
     * 説明文を設定
     *
     * @param description ネームスペースを更新
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 説明文を設定
     *
     * @param description ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** アカウント引き継ぎ時にパスワードを変更するか */
    private Boolean changePasswordIfTakeOver;

    /**
     * アカウント引き継ぎ時にパスワードを変更するかを取得
     *
     * @return ネームスペースを更新
     */
    public Boolean getChangePasswordIfTakeOver() {
        return changePasswordIfTakeOver;
    }

    /**
     * アカウント引き継ぎ時にパスワードを変更するかを設定
     *
     * @param changePasswordIfTakeOver ネームスペースを更新
     */
    public void setChangePasswordIfTakeOver(Boolean changePasswordIfTakeOver) {
        this.changePasswordIfTakeOver = changePasswordIfTakeOver;
    }

    /**
     * アカウント引き継ぎ時にパスワードを変更するかを設定
     *
     * @param changePasswordIfTakeOver ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withChangePasswordIfTakeOver(Boolean changePasswordIfTakeOver) {
        setChangePasswordIfTakeOver(changePasswordIfTakeOver);
        return this;
    }

    /** アカウント新規作成時 に実行されるスクリプト のGRN */
    private String createAccountTriggerScriptId;

    /**
     * アカウント新規作成時 に実行されるスクリプト のGRNを取得
     *
     * @return ネームスペースを更新
     */
    public String getCreateAccountTriggerScriptId() {
        return createAccountTriggerScriptId;
    }

    /**
     * アカウント新規作成時 に実行されるスクリプト のGRNを設定
     *
     * @param createAccountTriggerScriptId ネームスペースを更新
     */
    public void setCreateAccountTriggerScriptId(String createAccountTriggerScriptId) {
        this.createAccountTriggerScriptId = createAccountTriggerScriptId;
    }

    /**
     * アカウント新規作成時 に実行されるスクリプト のGRNを設定
     *
     * @param createAccountTriggerScriptId ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withCreateAccountTriggerScriptId(String createAccountTriggerScriptId) {
        setCreateAccountTriggerScriptId(createAccountTriggerScriptId);
        return this;
    }

    /** アカウント新規作成完了時 に実行されるスクリプト のGRN */
    private String createAccountDoneTriggerScriptId;

    /**
     * アカウント新規作成完了時 に実行されるスクリプト のGRNを取得
     *
     * @return ネームスペースを更新
     */
    public String getCreateAccountDoneTriggerScriptId() {
        return createAccountDoneTriggerScriptId;
    }

    /**
     * アカウント新規作成完了時 に実行されるスクリプト のGRNを設定
     *
     * @param createAccountDoneTriggerScriptId ネームスペースを更新
     */
    public void setCreateAccountDoneTriggerScriptId(String createAccountDoneTriggerScriptId) {
        this.createAccountDoneTriggerScriptId = createAccountDoneTriggerScriptId;
    }

    /**
     * アカウント新規作成完了時 に実行されるスクリプト のGRNを設定
     *
     * @param createAccountDoneTriggerScriptId ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withCreateAccountDoneTriggerScriptId(String createAccountDoneTriggerScriptId) {
        setCreateAccountDoneTriggerScriptId(createAccountDoneTriggerScriptId);
        return this;
    }

    /** アカウント新規作成完了時 にジョブが登録されるネームスペース のGRN */
    private String createAccountDoneTriggerQueueNamespaceId;

    /**
     * アカウント新規作成完了時 にジョブが登録されるネームスペース のGRNを取得
     *
     * @return ネームスペースを更新
     */
    public String getCreateAccountDoneTriggerQueueNamespaceId() {
        return createAccountDoneTriggerQueueNamespaceId;
    }

    /**
     * アカウント新規作成完了時 にジョブが登録されるネームスペース のGRNを設定
     *
     * @param createAccountDoneTriggerQueueNamespaceId ネームスペースを更新
     */
    public void setCreateAccountDoneTriggerQueueNamespaceId(String createAccountDoneTriggerQueueNamespaceId) {
        this.createAccountDoneTriggerQueueNamespaceId = createAccountDoneTriggerQueueNamespaceId;
    }

    /**
     * アカウント新規作成完了時 にジョブが登録されるネームスペース のGRNを設定
     *
     * @param createAccountDoneTriggerQueueNamespaceId ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withCreateAccountDoneTriggerQueueNamespaceId(String createAccountDoneTriggerQueueNamespaceId) {
        setCreateAccountDoneTriggerQueueNamespaceId(createAccountDoneTriggerQueueNamespaceId);
        return this;
    }

    /** 認証時 に実行されるスクリプト のGRN */
    private String authenticationTriggerScriptId;

    /**
     * 認証時 に実行されるスクリプト のGRNを取得
     *
     * @return ネームスペースを更新
     */
    public String getAuthenticationTriggerScriptId() {
        return authenticationTriggerScriptId;
    }

    /**
     * 認証時 に実行されるスクリプト のGRNを設定
     *
     * @param authenticationTriggerScriptId ネームスペースを更新
     */
    public void setAuthenticationTriggerScriptId(String authenticationTriggerScriptId) {
        this.authenticationTriggerScriptId = authenticationTriggerScriptId;
    }

    /**
     * 認証時 に実行されるスクリプト のGRNを設定
     *
     * @param authenticationTriggerScriptId ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withAuthenticationTriggerScriptId(String authenticationTriggerScriptId) {
        setAuthenticationTriggerScriptId(authenticationTriggerScriptId);
        return this;
    }

    /** 認証完了時 に実行されるスクリプト のGRN */
    private String authenticationDoneTriggerScriptId;

    /**
     * 認証完了時 に実行されるスクリプト のGRNを取得
     *
     * @return ネームスペースを更新
     */
    public String getAuthenticationDoneTriggerScriptId() {
        return authenticationDoneTriggerScriptId;
    }

    /**
     * 認証完了時 に実行されるスクリプト のGRNを設定
     *
     * @param authenticationDoneTriggerScriptId ネームスペースを更新
     */
    public void setAuthenticationDoneTriggerScriptId(String authenticationDoneTriggerScriptId) {
        this.authenticationDoneTriggerScriptId = authenticationDoneTriggerScriptId;
    }

    /**
     * 認証完了時 に実行されるスクリプト のGRNを設定
     *
     * @param authenticationDoneTriggerScriptId ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withAuthenticationDoneTriggerScriptId(String authenticationDoneTriggerScriptId) {
        setAuthenticationDoneTriggerScriptId(authenticationDoneTriggerScriptId);
        return this;
    }

    /** 認証完了時 にジョブが登録されるネームスペース のGRN */
    private String authenticationDoneTriggerQueueNamespaceId;

    /**
     * 認証完了時 にジョブが登録されるネームスペース のGRNを取得
     *
     * @return ネームスペースを更新
     */
    public String getAuthenticationDoneTriggerQueueNamespaceId() {
        return authenticationDoneTriggerQueueNamespaceId;
    }

    /**
     * 認証完了時 にジョブが登録されるネームスペース のGRNを設定
     *
     * @param authenticationDoneTriggerQueueNamespaceId ネームスペースを更新
     */
    public void setAuthenticationDoneTriggerQueueNamespaceId(String authenticationDoneTriggerQueueNamespaceId) {
        this.authenticationDoneTriggerQueueNamespaceId = authenticationDoneTriggerQueueNamespaceId;
    }

    /**
     * 認証完了時 にジョブが登録されるネームスペース のGRNを設定
     *
     * @param authenticationDoneTriggerQueueNamespaceId ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withAuthenticationDoneTriggerQueueNamespaceId(String authenticationDoneTriggerQueueNamespaceId) {
        setAuthenticationDoneTriggerQueueNamespaceId(authenticationDoneTriggerQueueNamespaceId);
        return this;
    }

    /** 引き継ぎ情報登録時 に実行されるスクリプト のGRN */
    private String createTakeOverTriggerScriptId;

    /**
     * 引き継ぎ情報登録時 に実行されるスクリプト のGRNを取得
     *
     * @return ネームスペースを更新
     */
    public String getCreateTakeOverTriggerScriptId() {
        return createTakeOverTriggerScriptId;
    }

    /**
     * 引き継ぎ情報登録時 に実行されるスクリプト のGRNを設定
     *
     * @param createTakeOverTriggerScriptId ネームスペースを更新
     */
    public void setCreateTakeOverTriggerScriptId(String createTakeOverTriggerScriptId) {
        this.createTakeOverTriggerScriptId = createTakeOverTriggerScriptId;
    }

    /**
     * 引き継ぎ情報登録時 に実行されるスクリプト のGRNを設定
     *
     * @param createTakeOverTriggerScriptId ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withCreateTakeOverTriggerScriptId(String createTakeOverTriggerScriptId) {
        setCreateTakeOverTriggerScriptId(createTakeOverTriggerScriptId);
        return this;
    }

    /** 引き継ぎ情報登録完了時 に実行されるスクリプト のGRN */
    private String createTakeOverDoneTriggerScriptId;

    /**
     * 引き継ぎ情報登録完了時 に実行されるスクリプト のGRNを取得
     *
     * @return ネームスペースを更新
     */
    public String getCreateTakeOverDoneTriggerScriptId() {
        return createTakeOverDoneTriggerScriptId;
    }

    /**
     * 引き継ぎ情報登録完了時 に実行されるスクリプト のGRNを設定
     *
     * @param createTakeOverDoneTriggerScriptId ネームスペースを更新
     */
    public void setCreateTakeOverDoneTriggerScriptId(String createTakeOverDoneTriggerScriptId) {
        this.createTakeOverDoneTriggerScriptId = createTakeOverDoneTriggerScriptId;
    }

    /**
     * 引き継ぎ情報登録完了時 に実行されるスクリプト のGRNを設定
     *
     * @param createTakeOverDoneTriggerScriptId ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withCreateTakeOverDoneTriggerScriptId(String createTakeOverDoneTriggerScriptId) {
        setCreateTakeOverDoneTriggerScriptId(createTakeOverDoneTriggerScriptId);
        return this;
    }

    /** 引き継ぎ情報登録完了時 にジョブが登録されるネームスペース のGRN */
    private String createTakeOverDoneTriggerQueueNamespaceId;

    /**
     * 引き継ぎ情報登録完了時 にジョブが登録されるネームスペース のGRNを取得
     *
     * @return ネームスペースを更新
     */
    public String getCreateTakeOverDoneTriggerQueueNamespaceId() {
        return createTakeOverDoneTriggerQueueNamespaceId;
    }

    /**
     * 引き継ぎ情報登録完了時 にジョブが登録されるネームスペース のGRNを設定
     *
     * @param createTakeOverDoneTriggerQueueNamespaceId ネームスペースを更新
     */
    public void setCreateTakeOverDoneTriggerQueueNamespaceId(String createTakeOverDoneTriggerQueueNamespaceId) {
        this.createTakeOverDoneTriggerQueueNamespaceId = createTakeOverDoneTriggerQueueNamespaceId;
    }

    /**
     * 引き継ぎ情報登録完了時 にジョブが登録されるネームスペース のGRNを設定
     *
     * @param createTakeOverDoneTriggerQueueNamespaceId ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withCreateTakeOverDoneTriggerQueueNamespaceId(String createTakeOverDoneTriggerQueueNamespaceId) {
        setCreateTakeOverDoneTriggerQueueNamespaceId(createTakeOverDoneTriggerQueueNamespaceId);
        return this;
    }

    /** 引き継ぎ実行時 に実行されるスクリプト のGRN */
    private String doTakeOverTriggerScriptId;

    /**
     * 引き継ぎ実行時 に実行されるスクリプト のGRNを取得
     *
     * @return ネームスペースを更新
     */
    public String getDoTakeOverTriggerScriptId() {
        return doTakeOverTriggerScriptId;
    }

    /**
     * 引き継ぎ実行時 に実行されるスクリプト のGRNを設定
     *
     * @param doTakeOverTriggerScriptId ネームスペースを更新
     */
    public void setDoTakeOverTriggerScriptId(String doTakeOverTriggerScriptId) {
        this.doTakeOverTriggerScriptId = doTakeOverTriggerScriptId;
    }

    /**
     * 引き継ぎ実行時 に実行されるスクリプト のGRNを設定
     *
     * @param doTakeOverTriggerScriptId ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withDoTakeOverTriggerScriptId(String doTakeOverTriggerScriptId) {
        setDoTakeOverTriggerScriptId(doTakeOverTriggerScriptId);
        return this;
    }

    /** 引き継ぎ実行完了時 に実行されるスクリプト のGRN */
    private String doTakeOverDoneTriggerScriptId;

    /**
     * 引き継ぎ実行完了時 に実行されるスクリプト のGRNを取得
     *
     * @return ネームスペースを更新
     */
    public String getDoTakeOverDoneTriggerScriptId() {
        return doTakeOverDoneTriggerScriptId;
    }

    /**
     * 引き継ぎ実行完了時 に実行されるスクリプト のGRNを設定
     *
     * @param doTakeOverDoneTriggerScriptId ネームスペースを更新
     */
    public void setDoTakeOverDoneTriggerScriptId(String doTakeOverDoneTriggerScriptId) {
        this.doTakeOverDoneTriggerScriptId = doTakeOverDoneTriggerScriptId;
    }

    /**
     * 引き継ぎ実行完了時 に実行されるスクリプト のGRNを設定
     *
     * @param doTakeOverDoneTriggerScriptId ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withDoTakeOverDoneTriggerScriptId(String doTakeOverDoneTriggerScriptId) {
        setDoTakeOverDoneTriggerScriptId(doTakeOverDoneTriggerScriptId);
        return this;
    }

    /** 引き継ぎ実行完了時 にジョブが登録されるネームスペース のGRN */
    private String doTakeOverDoneTriggerQueueNamespaceId;

    /**
     * 引き継ぎ実行完了時 にジョブが登録されるネームスペース のGRNを取得
     *
     * @return ネームスペースを更新
     */
    public String getDoTakeOverDoneTriggerQueueNamespaceId() {
        return doTakeOverDoneTriggerQueueNamespaceId;
    }

    /**
     * 引き継ぎ実行完了時 にジョブが登録されるネームスペース のGRNを設定
     *
     * @param doTakeOverDoneTriggerQueueNamespaceId ネームスペースを更新
     */
    public void setDoTakeOverDoneTriggerQueueNamespaceId(String doTakeOverDoneTriggerQueueNamespaceId) {
        this.doTakeOverDoneTriggerQueueNamespaceId = doTakeOverDoneTriggerQueueNamespaceId;
    }

    /**
     * 引き継ぎ実行完了時 にジョブが登録されるネームスペース のGRNを設定
     *
     * @param doTakeOverDoneTriggerQueueNamespaceId ネームスペースを更新
     * @return this
     */
    public UpdateNamespaceRequest withDoTakeOverDoneTriggerQueueNamespaceId(String doTakeOverDoneTriggerQueueNamespaceId) {
        setDoTakeOverDoneTriggerQueueNamespaceId(doTakeOverDoneTriggerQueueNamespaceId);
        return this;
    }

}