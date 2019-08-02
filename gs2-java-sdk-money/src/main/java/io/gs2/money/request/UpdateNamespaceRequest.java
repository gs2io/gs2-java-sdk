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
 * ネームスペースを更新します のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class UpdateNamespaceRequest extends Gs2BasicRequest<UpdateNamespaceRequest> {

    /** ネームスペースの名前 */
    private String namespaceName;

    /**
     * ネームスペースの名前を取得
     *
     * @return ネームスペースを更新します
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペースの名前を設定
     *
     * @param namespaceName ネームスペースを更新します
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペースの名前を設定
     *
     * @param namespaceName ネームスペースを更新します
     * @return this
     */
    public UpdateNamespaceRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** ネームスペースの説明 */
    private String description;

    /**
     * ネームスペースの説明を取得
     *
     * @return ネームスペースを更新します
     */
    public String getDescription() {
        return description;
    }

    /**
     * ネームスペースの説明を設定
     *
     * @param description ネームスペースを更新します
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * ネームスペースの説明を設定
     *
     * @param description ネームスペースを更新します
     * @return this
     */
    public UpdateNamespaceRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** 消費優先度 */
    private String priority;

    /**
     * 消費優先度を取得
     *
     * @return ネームスペースを更新します
     */
    public String getPriority() {
        return priority;
    }

    /**
     * 消費優先度を設定
     *
     * @param priority ネームスペースを更新します
     */
    public void setPriority(String priority) {
        this.priority = priority;
    }

    /**
     * 消費優先度を設定
     *
     * @param priority ネームスペースを更新します
     * @return this
     */
    public UpdateNamespaceRequest withPriority(String priority) {
        setPriority(priority);
        return this;
    }

    /** Apple AppStore のバンドルID */
    private String appleKey;

    /**
     * Apple AppStore のバンドルIDを取得
     *
     * @return ネームスペースを更新します
     */
    public String getAppleKey() {
        return appleKey;
    }

    /**
     * Apple AppStore のバンドルIDを設定
     *
     * @param appleKey ネームスペースを更新します
     */
    public void setAppleKey(String appleKey) {
        this.appleKey = appleKey;
    }

    /**
     * Apple AppStore のバンドルIDを設定
     *
     * @param appleKey ネームスペースを更新します
     * @return this
     */
    public UpdateNamespaceRequest withAppleKey(String appleKey) {
        setAppleKey(appleKey);
        return this;
    }

    /** Google PlayStore の秘密鍵 */
    private String googleKey;

    /**
     * Google PlayStore の秘密鍵を取得
     *
     * @return ネームスペースを更新します
     */
    public String getGoogleKey() {
        return googleKey;
    }

    /**
     * Google PlayStore の秘密鍵を設定
     *
     * @param googleKey ネームスペースを更新します
     */
    public void setGoogleKey(String googleKey) {
        this.googleKey = googleKey;
    }

    /**
     * Google PlayStore の秘密鍵を設定
     *
     * @param googleKey ネームスペースを更新します
     * @return this
     */
    public UpdateNamespaceRequest withGoogleKey(String googleKey) {
        setGoogleKey(googleKey);
        return this;
    }

    /** UnityEditorが出力する偽のレシートで決済できるようにするか */
    private Boolean enableFakeReceipt;

    /**
     * UnityEditorが出力する偽のレシートで決済できるようにするかを取得
     *
     * @return ネームスペースを更新します
     */
    public Boolean getEnableFakeReceipt() {
        return enableFakeReceipt;
    }

    /**
     * UnityEditorが出力する偽のレシートで決済できるようにするかを設定
     *
     * @param enableFakeReceipt ネームスペースを更新します
     */
    public void setEnableFakeReceipt(Boolean enableFakeReceipt) {
        this.enableFakeReceipt = enableFakeReceipt;
    }

    /**
     * UnityEditorが出力する偽のレシートで決済できるようにするかを設定
     *
     * @param enableFakeReceipt ネームスペースを更新します
     * @return this
     */
    public UpdateNamespaceRequest withEnableFakeReceipt(Boolean enableFakeReceipt) {
        setEnableFakeReceipt(enableFakeReceipt);
        return this;
    }

    /** ウォレット新規作成時 に実行されるスクリプト のGRN */
    private String createWalletTriggerScriptId;

    /**
     * ウォレット新規作成時 に実行されるスクリプト のGRNを取得
     *
     * @return ネームスペースを更新します
     */
    public String getCreateWalletTriggerScriptId() {
        return createWalletTriggerScriptId;
    }

    /**
     * ウォレット新規作成時 に実行されるスクリプト のGRNを設定
     *
     * @param createWalletTriggerScriptId ネームスペースを更新します
     */
    public void setCreateWalletTriggerScriptId(String createWalletTriggerScriptId) {
        this.createWalletTriggerScriptId = createWalletTriggerScriptId;
    }

    /**
     * ウォレット新規作成時 に実行されるスクリプト のGRNを設定
     *
     * @param createWalletTriggerScriptId ネームスペースを更新します
     * @return this
     */
    public UpdateNamespaceRequest withCreateWalletTriggerScriptId(String createWalletTriggerScriptId) {
        setCreateWalletTriggerScriptId(createWalletTriggerScriptId);
        return this;
    }

    /** ウォレット新規作成完了時 に実行されるスクリプト のGRN */
    private String createWalletDoneTriggerScriptId;

    /**
     * ウォレット新規作成完了時 に実行されるスクリプト のGRNを取得
     *
     * @return ネームスペースを更新します
     */
    public String getCreateWalletDoneTriggerScriptId() {
        return createWalletDoneTriggerScriptId;
    }

    /**
     * ウォレット新規作成完了時 に実行されるスクリプト のGRNを設定
     *
     * @param createWalletDoneTriggerScriptId ネームスペースを更新します
     */
    public void setCreateWalletDoneTriggerScriptId(String createWalletDoneTriggerScriptId) {
        this.createWalletDoneTriggerScriptId = createWalletDoneTriggerScriptId;
    }

    /**
     * ウォレット新規作成完了時 に実行されるスクリプト のGRNを設定
     *
     * @param createWalletDoneTriggerScriptId ネームスペースを更新します
     * @return this
     */
    public UpdateNamespaceRequest withCreateWalletDoneTriggerScriptId(String createWalletDoneTriggerScriptId) {
        setCreateWalletDoneTriggerScriptId(createWalletDoneTriggerScriptId);
        return this;
    }

    /** ウォレット新規作成完了時 にジョブを登録するネームスペース のGRN */
    private String createWalletDoneTriggerNamespaceId;

    /**
     * ウォレット新規作成完了時 にジョブを登録するネームスペース のGRNを取得
     *
     * @return ネームスペースを更新します
     */
    public String getCreateWalletDoneTriggerNamespaceId() {
        return createWalletDoneTriggerNamespaceId;
    }

    /**
     * ウォレット新規作成完了時 にジョブを登録するネームスペース のGRNを設定
     *
     * @param createWalletDoneTriggerNamespaceId ネームスペースを更新します
     */
    public void setCreateWalletDoneTriggerNamespaceId(String createWalletDoneTriggerNamespaceId) {
        this.createWalletDoneTriggerNamespaceId = createWalletDoneTriggerNamespaceId;
    }

    /**
     * ウォレット新規作成完了時 にジョブを登録するネームスペース のGRNを設定
     *
     * @param createWalletDoneTriggerNamespaceId ネームスペースを更新します
     * @return this
     */
    public UpdateNamespaceRequest withCreateWalletDoneTriggerNamespaceId(String createWalletDoneTriggerNamespaceId) {
        setCreateWalletDoneTriggerNamespaceId(createWalletDoneTriggerNamespaceId);
        return this;
    }

    /** ウォレット残高加算時 に実行されるスクリプト のGRN */
    private String depositTriggerScriptId;

    /**
     * ウォレット残高加算時 に実行されるスクリプト のGRNを取得
     *
     * @return ネームスペースを更新します
     */
    public String getDepositTriggerScriptId() {
        return depositTriggerScriptId;
    }

    /**
     * ウォレット残高加算時 に実行されるスクリプト のGRNを設定
     *
     * @param depositTriggerScriptId ネームスペースを更新します
     */
    public void setDepositTriggerScriptId(String depositTriggerScriptId) {
        this.depositTriggerScriptId = depositTriggerScriptId;
    }

    /**
     * ウォレット残高加算時 に実行されるスクリプト のGRNを設定
     *
     * @param depositTriggerScriptId ネームスペースを更新します
     * @return this
     */
    public UpdateNamespaceRequest withDepositTriggerScriptId(String depositTriggerScriptId) {
        setDepositTriggerScriptId(depositTriggerScriptId);
        return this;
    }

    /** ウォレット残高加算完了時 に実行されるスクリプト のGRN */
    private String depositDoneTriggerScriptId;

    /**
     * ウォレット残高加算完了時 に実行されるスクリプト のGRNを取得
     *
     * @return ネームスペースを更新します
     */
    public String getDepositDoneTriggerScriptId() {
        return depositDoneTriggerScriptId;
    }

    /**
     * ウォレット残高加算完了時 に実行されるスクリプト のGRNを設定
     *
     * @param depositDoneTriggerScriptId ネームスペースを更新します
     */
    public void setDepositDoneTriggerScriptId(String depositDoneTriggerScriptId) {
        this.depositDoneTriggerScriptId = depositDoneTriggerScriptId;
    }

    /**
     * ウォレット残高加算完了時 に実行されるスクリプト のGRNを設定
     *
     * @param depositDoneTriggerScriptId ネームスペースを更新します
     * @return this
     */
    public UpdateNamespaceRequest withDepositDoneTriggerScriptId(String depositDoneTriggerScriptId) {
        setDepositDoneTriggerScriptId(depositDoneTriggerScriptId);
        return this;
    }

    /** ウォレット残高加算完了時 にジョブを登録するネームスペース のGRN */
    private String depositDoneTriggerNamespaceId;

    /**
     * ウォレット残高加算完了時 にジョブを登録するネームスペース のGRNを取得
     *
     * @return ネームスペースを更新します
     */
    public String getDepositDoneTriggerNamespaceId() {
        return depositDoneTriggerNamespaceId;
    }

    /**
     * ウォレット残高加算完了時 にジョブを登録するネームスペース のGRNを設定
     *
     * @param depositDoneTriggerNamespaceId ネームスペースを更新します
     */
    public void setDepositDoneTriggerNamespaceId(String depositDoneTriggerNamespaceId) {
        this.depositDoneTriggerNamespaceId = depositDoneTriggerNamespaceId;
    }

    /**
     * ウォレット残高加算完了時 にジョブを登録するネームスペース のGRNを設定
     *
     * @param depositDoneTriggerNamespaceId ネームスペースを更新します
     * @return this
     */
    public UpdateNamespaceRequest withDepositDoneTriggerNamespaceId(String depositDoneTriggerNamespaceId) {
        setDepositDoneTriggerNamespaceId(depositDoneTriggerNamespaceId);
        return this;
    }

    /** ウォレット残高消費時 に実行されるスクリプト のGRN */
    private String withdrawTriggerScriptId;

    /**
     * ウォレット残高消費時 に実行されるスクリプト のGRNを取得
     *
     * @return ネームスペースを更新します
     */
    public String getWithdrawTriggerScriptId() {
        return withdrawTriggerScriptId;
    }

    /**
     * ウォレット残高消費時 に実行されるスクリプト のGRNを設定
     *
     * @param withdrawTriggerScriptId ネームスペースを更新します
     */
    public void setWithdrawTriggerScriptId(String withdrawTriggerScriptId) {
        this.withdrawTriggerScriptId = withdrawTriggerScriptId;
    }

    /**
     * ウォレット残高消費時 に実行されるスクリプト のGRNを設定
     *
     * @param withdrawTriggerScriptId ネームスペースを更新します
     * @return this
     */
    public UpdateNamespaceRequest withWithdrawTriggerScriptId(String withdrawTriggerScriptId) {
        setWithdrawTriggerScriptId(withdrawTriggerScriptId);
        return this;
    }

    /** ウォレット残高消費完了時 に実行されるスクリプト のGRN */
    private String withdrawDoneTriggerScriptId;

    /**
     * ウォレット残高消費完了時 に実行されるスクリプト のGRNを取得
     *
     * @return ネームスペースを更新します
     */
    public String getWithdrawDoneTriggerScriptId() {
        return withdrawDoneTriggerScriptId;
    }

    /**
     * ウォレット残高消費完了時 に実行されるスクリプト のGRNを設定
     *
     * @param withdrawDoneTriggerScriptId ネームスペースを更新します
     */
    public void setWithdrawDoneTriggerScriptId(String withdrawDoneTriggerScriptId) {
        this.withdrawDoneTriggerScriptId = withdrawDoneTriggerScriptId;
    }

    /**
     * ウォレット残高消費完了時 に実行されるスクリプト のGRNを設定
     *
     * @param withdrawDoneTriggerScriptId ネームスペースを更新します
     * @return this
     */
    public UpdateNamespaceRequest withWithdrawDoneTriggerScriptId(String withdrawDoneTriggerScriptId) {
        setWithdrawDoneTriggerScriptId(withdrawDoneTriggerScriptId);
        return this;
    }

    /** ウォレット残高消費完了時 にジョブを登録するネームスペース のGRN */
    private String withdrawDoneTriggerNamespaceId;

    /**
     * ウォレット残高消費完了時 にジョブを登録するネームスペース のGRNを取得
     *
     * @return ネームスペースを更新します
     */
    public String getWithdrawDoneTriggerNamespaceId() {
        return withdrawDoneTriggerNamespaceId;
    }

    /**
     * ウォレット残高消費完了時 にジョブを登録するネームスペース のGRNを設定
     *
     * @param withdrawDoneTriggerNamespaceId ネームスペースを更新します
     */
    public void setWithdrawDoneTriggerNamespaceId(String withdrawDoneTriggerNamespaceId) {
        this.withdrawDoneTriggerNamespaceId = withdrawDoneTriggerNamespaceId;
    }

    /**
     * ウォレット残高消費完了時 にジョブを登録するネームスペース のGRNを設定
     *
     * @param withdrawDoneTriggerNamespaceId ネームスペースを更新します
     * @return this
     */
    public UpdateNamespaceRequest withWithdrawDoneTriggerNamespaceId(String withdrawDoneTriggerNamespaceId) {
        setWithdrawDoneTriggerNamespaceId(withdrawDoneTriggerNamespaceId);
        return this;
    }

}