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
 * ネームスペースを新規作成 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateNamespaceRequest extends Gs2BasicRequest<CreateNamespaceRequest> {

    /** ネームスペース名 */
    private String name;

    /**
     * ネームスペース名を取得
     *
     * @return ネームスペースを新規作成
     */
    public String getName() {
        return name;
    }

    /**
     * ネームスペース名を設定
     *
     * @param name ネームスペースを新規作成
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * ネームスペース名を設定
     *
     * @param name ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withName(String name) {
        setName(name);
        return this;
    }

    /** 説明文 */
    private String description;

    /**
     * 説明文を取得
     *
     * @return ネームスペースを新規作成
     */
    public String getDescription() {
        return description;
    }

    /**
     * 説明文を設定
     *
     * @param description ネームスペースを新規作成
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 説明文を設定
     *
     * @param description ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** アカウント引き継ぎ時にパスワードを変更するか */
    private Boolean changePasswordIfTakeOver;

    /**
     * アカウント引き継ぎ時にパスワードを変更するかを取得
     *
     * @return ネームスペースを新規作成
     */
    public Boolean getChangePasswordIfTakeOver() {
        return changePasswordIfTakeOver;
    }

    /**
     * アカウント引き継ぎ時にパスワードを変更するかを設定
     *
     * @param changePasswordIfTakeOver ネームスペースを新規作成
     */
    public void setChangePasswordIfTakeOver(Boolean changePasswordIfTakeOver) {
        this.changePasswordIfTakeOver = changePasswordIfTakeOver;
    }

    /**
     * アカウント引き継ぎ時にパスワードを変更するかを設定
     *
     * @param changePasswordIfTakeOver ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withChangePasswordIfTakeOver(Boolean changePasswordIfTakeOver) {
        setChangePasswordIfTakeOver(changePasswordIfTakeOver);
        return this;
    }

    /** アカウント新規作成時 に実行されるスクリプト のGRN */
    private String createAccountTriggerScriptId;

    /**
     * アカウント新規作成時 に実行されるスクリプト のGRNを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getCreateAccountTriggerScriptId() {
        return createAccountTriggerScriptId;
    }

    /**
     * アカウント新規作成時 に実行されるスクリプト のGRNを設定
     *
     * @param createAccountTriggerScriptId ネームスペースを新規作成
     */
    public void setCreateAccountTriggerScriptId(String createAccountTriggerScriptId) {
        this.createAccountTriggerScriptId = createAccountTriggerScriptId;
    }

    /**
     * アカウント新規作成時 に実行されるスクリプト のGRNを設定
     *
     * @param createAccountTriggerScriptId ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withCreateAccountTriggerScriptId(String createAccountTriggerScriptId) {
        setCreateAccountTriggerScriptId(createAccountTriggerScriptId);
        return this;
    }

    /** アカウント新規作成完了時 に実行されるスクリプト のGRN */
    private String createAccountDoneTriggerScriptId;

    /**
     * アカウント新規作成完了時 に実行されるスクリプト のGRNを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getCreateAccountDoneTriggerScriptId() {
        return createAccountDoneTriggerScriptId;
    }

    /**
     * アカウント新規作成完了時 に実行されるスクリプト のGRNを設定
     *
     * @param createAccountDoneTriggerScriptId ネームスペースを新規作成
     */
    public void setCreateAccountDoneTriggerScriptId(String createAccountDoneTriggerScriptId) {
        this.createAccountDoneTriggerScriptId = createAccountDoneTriggerScriptId;
    }

    /**
     * アカウント新規作成完了時 に実行されるスクリプト のGRNを設定
     *
     * @param createAccountDoneTriggerScriptId ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withCreateAccountDoneTriggerScriptId(String createAccountDoneTriggerScriptId) {
        setCreateAccountDoneTriggerScriptId(createAccountDoneTriggerScriptId);
        return this;
    }

    /** アカウント新規作成完了時 にジョブが登録されるネームスペース のGRN */
    private String createAccountDoneTriggerQueueNamespaceId;

    /**
     * アカウント新規作成完了時 にジョブが登録されるネームスペース のGRNを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getCreateAccountDoneTriggerQueueNamespaceId() {
        return createAccountDoneTriggerQueueNamespaceId;
    }

    /**
     * アカウント新規作成完了時 にジョブが登録されるネームスペース のGRNを設定
     *
     * @param createAccountDoneTriggerQueueNamespaceId ネームスペースを新規作成
     */
    public void setCreateAccountDoneTriggerQueueNamespaceId(String createAccountDoneTriggerQueueNamespaceId) {
        this.createAccountDoneTriggerQueueNamespaceId = createAccountDoneTriggerQueueNamespaceId;
    }

    /**
     * アカウント新規作成完了時 にジョブが登録されるネームスペース のGRNを設定
     *
     * @param createAccountDoneTriggerQueueNamespaceId ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withCreateAccountDoneTriggerQueueNamespaceId(String createAccountDoneTriggerQueueNamespaceId) {
        setCreateAccountDoneTriggerQueueNamespaceId(createAccountDoneTriggerQueueNamespaceId);
        return this;
    }

    /** 認証時 に実行されるスクリプト のGRN */
    private String authenticationTriggerScriptId;

    /**
     * 認証時 に実行されるスクリプト のGRNを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getAuthenticationTriggerScriptId() {
        return authenticationTriggerScriptId;
    }

    /**
     * 認証時 に実行されるスクリプト のGRNを設定
     *
     * @param authenticationTriggerScriptId ネームスペースを新規作成
     */
    public void setAuthenticationTriggerScriptId(String authenticationTriggerScriptId) {
        this.authenticationTriggerScriptId = authenticationTriggerScriptId;
    }

    /**
     * 認証時 に実行されるスクリプト のGRNを設定
     *
     * @param authenticationTriggerScriptId ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withAuthenticationTriggerScriptId(String authenticationTriggerScriptId) {
        setAuthenticationTriggerScriptId(authenticationTriggerScriptId);
        return this;
    }

    /** 認証完了時 に実行されるスクリプト のGRN */
    private String authenticationDoneTriggerScriptId;

    /**
     * 認証完了時 に実行されるスクリプト のGRNを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getAuthenticationDoneTriggerScriptId() {
        return authenticationDoneTriggerScriptId;
    }

    /**
     * 認証完了時 に実行されるスクリプト のGRNを設定
     *
     * @param authenticationDoneTriggerScriptId ネームスペースを新規作成
     */
    public void setAuthenticationDoneTriggerScriptId(String authenticationDoneTriggerScriptId) {
        this.authenticationDoneTriggerScriptId = authenticationDoneTriggerScriptId;
    }

    /**
     * 認証完了時 に実行されるスクリプト のGRNを設定
     *
     * @param authenticationDoneTriggerScriptId ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withAuthenticationDoneTriggerScriptId(String authenticationDoneTriggerScriptId) {
        setAuthenticationDoneTriggerScriptId(authenticationDoneTriggerScriptId);
        return this;
    }

    /** 認証完了時 にジョブが登録されるネームスペース のGRN */
    private String authenticationDoneTriggerQueueNamespaceId;

    /**
     * 認証完了時 にジョブが登録されるネームスペース のGRNを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getAuthenticationDoneTriggerQueueNamespaceId() {
        return authenticationDoneTriggerQueueNamespaceId;
    }

    /**
     * 認証完了時 にジョブが登録されるネームスペース のGRNを設定
     *
     * @param authenticationDoneTriggerQueueNamespaceId ネームスペースを新規作成
     */
    public void setAuthenticationDoneTriggerQueueNamespaceId(String authenticationDoneTriggerQueueNamespaceId) {
        this.authenticationDoneTriggerQueueNamespaceId = authenticationDoneTriggerQueueNamespaceId;
    }

    /**
     * 認証完了時 にジョブが登録されるネームスペース のGRNを設定
     *
     * @param authenticationDoneTriggerQueueNamespaceId ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withAuthenticationDoneTriggerQueueNamespaceId(String authenticationDoneTriggerQueueNamespaceId) {
        setAuthenticationDoneTriggerQueueNamespaceId(authenticationDoneTriggerQueueNamespaceId);
        return this;
    }

    /** 引き継ぎ情報登録時 に実行されるスクリプト のGRN */
    private String createTakeOverTriggerScriptId;

    /**
     * 引き継ぎ情報登録時 に実行されるスクリプト のGRNを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getCreateTakeOverTriggerScriptId() {
        return createTakeOverTriggerScriptId;
    }

    /**
     * 引き継ぎ情報登録時 に実行されるスクリプト のGRNを設定
     *
     * @param createTakeOverTriggerScriptId ネームスペースを新規作成
     */
    public void setCreateTakeOverTriggerScriptId(String createTakeOverTriggerScriptId) {
        this.createTakeOverTriggerScriptId = createTakeOverTriggerScriptId;
    }

    /**
     * 引き継ぎ情報登録時 に実行されるスクリプト のGRNを設定
     *
     * @param createTakeOverTriggerScriptId ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withCreateTakeOverTriggerScriptId(String createTakeOverTriggerScriptId) {
        setCreateTakeOverTriggerScriptId(createTakeOverTriggerScriptId);
        return this;
    }

    /** 引き継ぎ情報登録完了時 に実行されるスクリプト のGRN */
    private String createTakeOverDoneTriggerScriptId;

    /**
     * 引き継ぎ情報登録完了時 に実行されるスクリプト のGRNを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getCreateTakeOverDoneTriggerScriptId() {
        return createTakeOverDoneTriggerScriptId;
    }

    /**
     * 引き継ぎ情報登録完了時 に実行されるスクリプト のGRNを設定
     *
     * @param createTakeOverDoneTriggerScriptId ネームスペースを新規作成
     */
    public void setCreateTakeOverDoneTriggerScriptId(String createTakeOverDoneTriggerScriptId) {
        this.createTakeOverDoneTriggerScriptId = createTakeOverDoneTriggerScriptId;
    }

    /**
     * 引き継ぎ情報登録完了時 に実行されるスクリプト のGRNを設定
     *
     * @param createTakeOverDoneTriggerScriptId ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withCreateTakeOverDoneTriggerScriptId(String createTakeOverDoneTriggerScriptId) {
        setCreateTakeOverDoneTriggerScriptId(createTakeOverDoneTriggerScriptId);
        return this;
    }

    /** 引き継ぎ情報登録完了時 にジョブが登録されるネームスペース のGRN */
    private String createTakeOverDoneTriggerQueueNamespaceId;

    /**
     * 引き継ぎ情報登録完了時 にジョブが登録されるネームスペース のGRNを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getCreateTakeOverDoneTriggerQueueNamespaceId() {
        return createTakeOverDoneTriggerQueueNamespaceId;
    }

    /**
     * 引き継ぎ情報登録完了時 にジョブが登録されるネームスペース のGRNを設定
     *
     * @param createTakeOverDoneTriggerQueueNamespaceId ネームスペースを新規作成
     */
    public void setCreateTakeOverDoneTriggerQueueNamespaceId(String createTakeOverDoneTriggerQueueNamespaceId) {
        this.createTakeOverDoneTriggerQueueNamespaceId = createTakeOverDoneTriggerQueueNamespaceId;
    }

    /**
     * 引き継ぎ情報登録完了時 にジョブが登録されるネームスペース のGRNを設定
     *
     * @param createTakeOverDoneTriggerQueueNamespaceId ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withCreateTakeOverDoneTriggerQueueNamespaceId(String createTakeOverDoneTriggerQueueNamespaceId) {
        setCreateTakeOverDoneTriggerQueueNamespaceId(createTakeOverDoneTriggerQueueNamespaceId);
        return this;
    }

    /** 引き継ぎ実行時 に実行されるスクリプト のGRN */
    private String doTakeOverTriggerScriptId;

    /**
     * 引き継ぎ実行時 に実行されるスクリプト のGRNを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getDoTakeOverTriggerScriptId() {
        return doTakeOverTriggerScriptId;
    }

    /**
     * 引き継ぎ実行時 に実行されるスクリプト のGRNを設定
     *
     * @param doTakeOverTriggerScriptId ネームスペースを新規作成
     */
    public void setDoTakeOverTriggerScriptId(String doTakeOverTriggerScriptId) {
        this.doTakeOverTriggerScriptId = doTakeOverTriggerScriptId;
    }

    /**
     * 引き継ぎ実行時 に実行されるスクリプト のGRNを設定
     *
     * @param doTakeOverTriggerScriptId ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withDoTakeOverTriggerScriptId(String doTakeOverTriggerScriptId) {
        setDoTakeOverTriggerScriptId(doTakeOverTriggerScriptId);
        return this;
    }

    /** 引き継ぎ実行完了時 に実行されるスクリプト のGRN */
    private String doTakeOverDoneTriggerScriptId;

    /**
     * 引き継ぎ実行完了時 に実行されるスクリプト のGRNを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getDoTakeOverDoneTriggerScriptId() {
        return doTakeOverDoneTriggerScriptId;
    }

    /**
     * 引き継ぎ実行完了時 に実行されるスクリプト のGRNを設定
     *
     * @param doTakeOverDoneTriggerScriptId ネームスペースを新規作成
     */
    public void setDoTakeOverDoneTriggerScriptId(String doTakeOverDoneTriggerScriptId) {
        this.doTakeOverDoneTriggerScriptId = doTakeOverDoneTriggerScriptId;
    }

    /**
     * 引き継ぎ実行完了時 に実行されるスクリプト のGRNを設定
     *
     * @param doTakeOverDoneTriggerScriptId ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withDoTakeOverDoneTriggerScriptId(String doTakeOverDoneTriggerScriptId) {
        setDoTakeOverDoneTriggerScriptId(doTakeOverDoneTriggerScriptId);
        return this;
    }

    /** 引き継ぎ実行完了時 にジョブが登録されるネームスペース のGRN */
    private String doTakeOverDoneTriggerQueueNamespaceId;

    /**
     * 引き継ぎ実行完了時 にジョブが登録されるネームスペース のGRNを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getDoTakeOverDoneTriggerQueueNamespaceId() {
        return doTakeOverDoneTriggerQueueNamespaceId;
    }

    /**
     * 引き継ぎ実行完了時 にジョブが登録されるネームスペース のGRNを設定
     *
     * @param doTakeOverDoneTriggerQueueNamespaceId ネームスペースを新規作成
     */
    public void setDoTakeOverDoneTriggerQueueNamespaceId(String doTakeOverDoneTriggerQueueNamespaceId) {
        this.doTakeOverDoneTriggerQueueNamespaceId = doTakeOverDoneTriggerQueueNamespaceId;
    }

    /**
     * 引き継ぎ実行完了時 にジョブが登録されるネームスペース のGRNを設定
     *
     * @param doTakeOverDoneTriggerQueueNamespaceId ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withDoTakeOverDoneTriggerQueueNamespaceId(String doTakeOverDoneTriggerQueueNamespaceId) {
        setDoTakeOverDoneTriggerQueueNamespaceId(doTakeOverDoneTriggerQueueNamespaceId);
        return this;
    }

}