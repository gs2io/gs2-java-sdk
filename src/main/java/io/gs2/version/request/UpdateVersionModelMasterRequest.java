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
 * バージョンマスターを更新 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class UpdateVersionModelMasterRequest extends Gs2BasicRequest<UpdateVersionModelMasterRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return バージョンマスターを更新
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName バージョンマスターを更新
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName バージョンマスターを更新
     * @return this
     */
    public UpdateVersionModelMasterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** バージョン名 */
    private String versionName;

    /**
     * バージョン名を取得
     *
     * @return バージョンマスターを更新
     */
    public String getVersionName() {
        return versionName;
    }

    /**
     * バージョン名を設定
     *
     * @param versionName バージョンマスターを更新
     */
    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    /**
     * バージョン名を設定
     *
     * @param versionName バージョンマスターを更新
     * @return this
     */
    public UpdateVersionModelMasterRequest withVersionName(String versionName) {
        setVersionName(versionName);
        return this;
    }

    /** バージョンマスターの説明 */
    private String description;

    /**
     * バージョンマスターの説明を取得
     *
     * @return バージョンマスターを更新
     */
    public String getDescription() {
        return description;
    }

    /**
     * バージョンマスターの説明を設定
     *
     * @param description バージョンマスターを更新
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * バージョンマスターの説明を設定
     *
     * @param description バージョンマスターを更新
     * @return this
     */
    public UpdateVersionModelMasterRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** バージョンのメタデータ */
    private String metadata;

    /**
     * バージョンのメタデータを取得
     *
     * @return バージョンマスターを更新
     */
    public String getMetadata() {
        return metadata;
    }

    /**
     * バージョンのメタデータを設定
     *
     * @param metadata バージョンマスターを更新
     */
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    /**
     * バージョンのメタデータを設定
     *
     * @param metadata バージョンマスターを更新
     * @return this
     */
    public UpdateVersionModelMasterRequest withMetadata(String metadata) {
        setMetadata(metadata);
        return this;
    }

    /** バージョンアップを促すバージョン */
    private Version warningVersion;

    /**
     * バージョンアップを促すバージョンを取得
     *
     * @return バージョンマスターを更新
     */
    public Version getWarningVersion() {
        return warningVersion;
    }

    /**
     * バージョンアップを促すバージョンを設定
     *
     * @param warningVersion バージョンマスターを更新
     */
    public void setWarningVersion(Version warningVersion) {
        this.warningVersion = warningVersion;
    }

    /**
     * バージョンアップを促すバージョンを設定
     *
     * @param warningVersion バージョンマスターを更新
     * @return this
     */
    public UpdateVersionModelMasterRequest withWarningVersion(Version warningVersion) {
        setWarningVersion(warningVersion);
        return this;
    }

    /** バージョンチェックを蹴るバージョン */
    private Version errorVersion;

    /**
     * バージョンチェックを蹴るバージョンを取得
     *
     * @return バージョンマスターを更新
     */
    public Version getErrorVersion() {
        return errorVersion;
    }

    /**
     * バージョンチェックを蹴るバージョンを設定
     *
     * @param errorVersion バージョンマスターを更新
     */
    public void setErrorVersion(Version errorVersion) {
        this.errorVersion = errorVersion;
    }

    /**
     * バージョンチェックを蹴るバージョンを設定
     *
     * @param errorVersion バージョンマスターを更新
     * @return this
     */
    public UpdateVersionModelMasterRequest withErrorVersion(Version errorVersion) {
        setErrorVersion(errorVersion);
        return this;
    }

    /** 判定に使用するバージョン値の種類 */
    private String scope;

    /**
     * 判定に使用するバージョン値の種類を取得
     *
     * @return バージョンマスターを更新
     */
    public String getScope() {
        return scope;
    }

    /**
     * 判定に使用するバージョン値の種類を設定
     *
     * @param scope バージョンマスターを更新
     */
    public void setScope(String scope) {
        this.scope = scope;
    }

    /**
     * 判定に使用するバージョン値の種類を設定
     *
     * @param scope バージョンマスターを更新
     * @return this
     */
    public UpdateVersionModelMasterRequest withScope(String scope) {
        setScope(scope);
        return this;
    }

    /** 現在のバージョン */
    private Version currentVersion;

    /**
     * 現在のバージョンを取得
     *
     * @return バージョンマスターを更新
     */
    public Version getCurrentVersion() {
        return currentVersion;
    }

    /**
     * 現在のバージョンを設定
     *
     * @param currentVersion バージョンマスターを更新
     */
    public void setCurrentVersion(Version currentVersion) {
        this.currentVersion = currentVersion;
    }

    /**
     * 現在のバージョンを設定
     *
     * @param currentVersion バージョンマスターを更新
     * @return this
     */
    public UpdateVersionModelMasterRequest withCurrentVersion(Version currentVersion) {
        setCurrentVersion(currentVersion);
        return this;
    }

    /** 判定するバージョン値に署名検証を必要とするか */
    private Boolean needSignature;

    /**
     * 判定するバージョン値に署名検証を必要とするかを取得
     *
     * @return バージョンマスターを更新
     */
    public Boolean getNeedSignature() {
        return needSignature;
    }

    /**
     * 判定するバージョン値に署名検証を必要とするかを設定
     *
     * @param needSignature バージョンマスターを更新
     */
    public void setNeedSignature(Boolean needSignature) {
        this.needSignature = needSignature;
    }

    /**
     * 判定するバージョン値に署名検証を必要とするかを設定
     *
     * @param needSignature バージョンマスターを更新
     * @return this
     */
    public UpdateVersionModelMasterRequest withNeedSignature(Boolean needSignature) {
        setNeedSignature(needSignature);
        return this;
    }

    /** 署名検証に使用する暗号鍵 のGRN */
    private String signatureKeyId;

    /**
     * 署名検証に使用する暗号鍵 のGRNを取得
     *
     * @return バージョンマスターを更新
     */
    public String getSignatureKeyId() {
        return signatureKeyId;
    }

    /**
     * 署名検証に使用する暗号鍵 のGRNを設定
     *
     * @param signatureKeyId バージョンマスターを更新
     */
    public void setSignatureKeyId(String signatureKeyId) {
        this.signatureKeyId = signatureKeyId;
    }

    /**
     * 署名検証に使用する暗号鍵 のGRNを設定
     *
     * @param signatureKeyId バージョンマスターを更新
     * @return this
     */
    public UpdateVersionModelMasterRequest withSignatureKeyId(String signatureKeyId) {
        setSignatureKeyId(signatureKeyId);
        return this;
    }

}