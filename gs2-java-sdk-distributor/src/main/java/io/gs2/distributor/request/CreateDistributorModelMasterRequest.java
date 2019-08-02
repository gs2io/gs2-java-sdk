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

package io.gs2.distributor.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.distributor.model.*;
import io.gs2.control.Gs2BasicRequest;

/**
 * 配信設定マスターを新規作成 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateDistributorModelMasterRequest extends Gs2BasicRequest<CreateDistributorModelMasterRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return 配信設定マスターを新規作成
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 配信設定マスターを新規作成
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 配信設定マスターを新規作成
     * @return this
     */
    public CreateDistributorModelMasterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** 配信設定名 */
    private String name;

    /**
     * 配信設定名を取得
     *
     * @return 配信設定マスターを新規作成
     */
    public String getName() {
        return name;
    }

    /**
     * 配信設定名を設定
     *
     * @param name 配信設定マスターを新規作成
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 配信設定名を設定
     *
     * @param name 配信設定マスターを新規作成
     * @return this
     */
    public CreateDistributorModelMasterRequest withName(String name) {
        setName(name);
        return this;
    }

    /** 配信設定マスターの説明 */
    private String description;

    /**
     * 配信設定マスターの説明を取得
     *
     * @return 配信設定マスターを新規作成
     */
    public String getDescription() {
        return description;
    }

    /**
     * 配信設定マスターの説明を設定
     *
     * @param description 配信設定マスターを新規作成
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 配信設定マスターの説明を設定
     *
     * @param description 配信設定マスターを新規作成
     * @return this
     */
    public CreateDistributorModelMasterRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** 配信設定のメタデータ */
    private String metadata;

    /**
     * 配信設定のメタデータを取得
     *
     * @return 配信設定マスターを新規作成
     */
    public String getMetadata() {
        return metadata;
    }

    /**
     * 配信設定のメタデータを設定
     *
     * @param metadata 配信設定マスターを新規作成
     */
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    /**
     * 配信設定のメタデータを設定
     *
     * @param metadata 配信設定マスターを新規作成
     * @return this
     */
    public CreateDistributorModelMasterRequest withMetadata(String metadata) {
        setMetadata(metadata);
        return this;
    }

    /** 所持品の配布処理の権限判定に使用する ユーザ のGRN */
    private String assumeUserId;

    /**
     * 所持品の配布処理の権限判定に使用する ユーザ のGRNを取得
     *
     * @return 配信設定マスターを新規作成
     */
    public String getAssumeUserId() {
        return assumeUserId;
    }

    /**
     * 所持品の配布処理の権限判定に使用する ユーザ のGRNを設定
     *
     * @param assumeUserId 配信設定マスターを新規作成
     */
    public void setAssumeUserId(String assumeUserId) {
        this.assumeUserId = assumeUserId;
    }

    /**
     * 所持品の配布処理の権限判定に使用する ユーザ のGRNを設定
     *
     * @param assumeUserId 配信設定マスターを新規作成
     * @return this
     */
    public CreateDistributorModelMasterRequest withAssumeUserId(String assumeUserId) {
        setAssumeUserId(assumeUserId);
        return this;
    }

    /** 所持品がキャパシティをオーバーしたときに転送するプレゼントボックスのネームスペース のGRN */
    private String inboxNamespaceId;

    /**
     * 所持品がキャパシティをオーバーしたときに転送するプレゼントボックスのネームスペース のGRNを取得
     *
     * @return 配信設定マスターを新規作成
     */
    public String getInboxNamespaceId() {
        return inboxNamespaceId;
    }

    /**
     * 所持品がキャパシティをオーバーしたときに転送するプレゼントボックスのネームスペース のGRNを設定
     *
     * @param inboxNamespaceId 配信設定マスターを新規作成
     */
    public void setInboxNamespaceId(String inboxNamespaceId) {
        this.inboxNamespaceId = inboxNamespaceId;
    }

    /**
     * 所持品がキャパシティをオーバーしたときに転送するプレゼントボックスのネームスペース のGRNを設定
     *
     * @param inboxNamespaceId 配信設定マスターを新規作成
     * @return this
     */
    public CreateDistributorModelMasterRequest withInboxNamespaceId(String inboxNamespaceId) {
        setInboxNamespaceId(inboxNamespaceId);
        return this;
    }

    /** ディストリビューターを通して処理出来る対象のリソースGRNのホワイトリスト */
    private List<String> whiteListTargetIds;

    /**
     * ディストリビューターを通して処理出来る対象のリソースGRNのホワイトリストを取得
     *
     * @return 配信設定マスターを新規作成
     */
    public List<String> getWhiteListTargetIds() {
        return whiteListTargetIds;
    }

    /**
     * ディストリビューターを通して処理出来る対象のリソースGRNのホワイトリストを設定
     *
     * @param whiteListTargetIds 配信設定マスターを新規作成
     */
    public void setWhiteListTargetIds(List<String> whiteListTargetIds) {
        this.whiteListTargetIds = whiteListTargetIds;
    }

    /**
     * ディストリビューターを通して処理出来る対象のリソースGRNのホワイトリストを設定
     *
     * @param whiteListTargetIds 配信設定マスターを新規作成
     * @return this
     */
    public CreateDistributorModelMasterRequest withWhiteListTargetIds(List<String> whiteListTargetIds) {
        setWhiteListTargetIds(whiteListTargetIds);
        return this;
    }

}