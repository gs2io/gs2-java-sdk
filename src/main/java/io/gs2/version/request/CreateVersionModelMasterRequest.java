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

package io.gs2.version.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.version.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * バージョンマスターを新規作成 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateVersionModelMasterRequest extends Gs2BasicRequest<CreateVersionModelMasterRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return バージョンマスターを新規作成
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName バージョンマスターを新規作成
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName バージョンマスターを新規作成
     * @return this
     */
    public CreateVersionModelMasterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** バージョン名 */
    private String name;

    /**
     * バージョン名を取得
     *
     * @return バージョンマスターを新規作成
     */
    public String getName() {
        return name;
    }

    /**
     * バージョン名を設定
     *
     * @param name バージョンマスターを新規作成
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * バージョン名を設定
     *
     * @param name バージョンマスターを新規作成
     * @return this
     */
    public CreateVersionModelMasterRequest withName(String name) {
        setName(name);
        return this;
    }

    /** バージョンマスターの説明 */
    private String description;

    /**
     * バージョンマスターの説明を取得
     *
     * @return バージョンマスターを新規作成
     */
    public String getDescription() {
        return description;
    }

    /**
     * バージョンマスターの説明を設定
     *
     * @param description バージョンマスターを新規作成
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * バージョンマスターの説明を設定
     *
     * @param description バージョンマスターを新規作成
     * @return this
     */
    public CreateVersionModelMasterRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** バージョンのメタデータ */
    private String metadata;

    /**
     * バージョンのメタデータを取得
     *
     * @return バージョンマスターを新規作成
     */
    public String getMetadata() {
        return metadata;
    }

    /**
     * バージョンのメタデータを設定
     *
     * @param metadata バージョンマスターを新規作成
     */
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    /**
     * バージョンのメタデータを設定
     *
     * @param metadata バージョンマスターを新規作成
     * @return this
     */
    public CreateVersionModelMasterRequest withMetadata(String metadata) {
        setMetadata(metadata);
        return this;
    }

    /** バージョンアップを促すバージョン */
    private Version warningVersion;

    /**
     * バージョンアップを促すバージョンを取得
     *
     * @return バージョンマスターを新規作成
     */
    public Version getWarningVersion() {
        return warningVersion;
    }

    /**
     * バージョンアップを促すバージョンを設定
     *
     * @param warningVersion バージョンマスターを新規作成
     */
    public void setWarningVersion(Version warningVersion) {
        this.warningVersion = warningVersion;
    }

    /**
     * バージョンアップを促すバージョンを設定
     *
     * @param warningVersion バージョンマスターを新規作成
     * @return this
     */
    public CreateVersionModelMasterRequest withWarningVersion(Version warningVersion) {
        setWarningVersion(warningVersion);
        return this;
    }

    /** バージョンチェックを蹴るバージョン */
    private Version errorVersion;

    /**
     * バージョンチェックを蹴るバージョンを取得
     *
     * @return バージョンマスターを新規作成
     */
    public Version getErrorVersion() {
        return errorVersion;
    }

    /**
     * バージョンチェックを蹴るバージョンを設定
     *
     * @param errorVersion バージョンマスターを新規作成
     */
    public void setErrorVersion(Version errorVersion) {
        this.errorVersion = errorVersion;
    }

    /**
     * バージョンチェックを蹴るバージョンを設定
     *
     * @param errorVersion バージョンマスターを新規作成
     * @return this
     */
    public CreateVersionModelMasterRequest withErrorVersion(Version errorVersion) {
        setErrorVersion(errorVersion);
        return this;
    }

    /** 判定に使用するバージョン値の種類 */
    private String scope;

    /**
     * 判定に使用するバージョン値の種類を取得
     *
     * @return バージョンマスターを新規作成
     */
    public String getScope() {
        return scope;
    }

    /**
     * 判定に使用するバージョン値の種類を設定
     *
     * @param scope バージョンマスターを新規作成
     */
    public void setScope(String scope) {
        this.scope = scope;
    }

    /**
     * 判定に使用するバージョン値の種類を設定
     *
     * @param scope バージョンマスターを新規作成
     * @return this
     */
    public CreateVersionModelMasterRequest withScope(String scope) {
        setScope(scope);
        return this;
    }

    /** 現在のバージョン */
    private Version currentVersion;

    /**
     * 現在のバージョンを取得
     *
     * @return バージョンマスターを新規作成
     */
    public Version getCurrentVersion() {
        return currentVersion;
    }

    /**
     * 現在のバージョンを設定
     *
     * @param currentVersion バージョンマスターを新規作成
     */
    public void setCurrentVersion(Version currentVersion) {
        this.currentVersion = currentVersion;
    }

    /**
     * 現在のバージョンを設定
     *
     * @param currentVersion バージョンマスターを新規作成
     * @return this
     */
    public CreateVersionModelMasterRequest withCurrentVersion(Version currentVersion) {
        setCurrentVersion(currentVersion);
        return this;
    }

    /** 判定するバージョン値に署名検証を必要とするか */
    private Boolean needSignature;

    /**
     * 判定するバージョン値に署名検証を必要とするかを取得
     *
     * @return バージョンマスターを新規作成
     */
    public Boolean getNeedSignature() {
        return needSignature;
    }

    /**
     * 判定するバージョン値に署名検証を必要とするかを設定
     *
     * @param needSignature バージョンマスターを新規作成
     */
    public void setNeedSignature(Boolean needSignature) {
        this.needSignature = needSignature;
    }

    /**
     * 判定するバージョン値に署名検証を必要とするかを設定
     *
     * @param needSignature バージョンマスターを新規作成
     * @return this
     */
    public CreateVersionModelMasterRequest withNeedSignature(Boolean needSignature) {
        setNeedSignature(needSignature);
        return this;
    }

    /** 署名検証に使用する暗号鍵 のGRN */
    private String signatureKeyId;

    /**
     * 署名検証に使用する暗号鍵 のGRNを取得
     *
     * @return バージョンマスターを新規作成
     */
    public String getSignatureKeyId() {
        return signatureKeyId;
    }

    /**
     * 署名検証に使用する暗号鍵 のGRNを設定
     *
     * @param signatureKeyId バージョンマスターを新規作成
     */
    public void setSignatureKeyId(String signatureKeyId) {
        this.signatureKeyId = signatureKeyId;
    }

    /**
     * 署名検証に使用する暗号鍵 のGRNを設定
     *
     * @param signatureKeyId バージョンマスターを新規作成
     * @return this
     */
    public CreateVersionModelMasterRequest withSignatureKeyId(String signatureKeyId) {
        setSignatureKeyId(signatureKeyId);
        return this;
    }

}