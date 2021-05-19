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
 * スタンプシートのタスクを実行する のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CalculateSignatureRequest extends Gs2BasicRequest<CalculateSignatureRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return スタンプシートのタスクを実行する
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName スタンプシートのタスクを実行する
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName スタンプシートのタスクを実行する
     * @return this
     */
    public CalculateSignatureRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** バージョンの種類名 */
    private String versionName;

    /**
     * バージョンの種類名を取得
     *
     * @return スタンプシートのタスクを実行する
     */
    public String getVersionName() {
        return versionName;
    }

    /**
     * バージョンの種類名を設定
     *
     * @param versionName スタンプシートのタスクを実行する
     */
    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    /**
     * バージョンの種類名を設定
     *
     * @param versionName スタンプシートのタスクを実行する
     * @return this
     */
    public CalculateSignatureRequest withVersionName(String versionName) {
        setVersionName(versionName);
        return this;
    }

    /** バージョン */
    private Version version;

    /**
     * バージョンを取得
     *
     * @return スタンプシートのタスクを実行する
     */
    public Version getVersion() {
        return version;
    }

    /**
     * バージョンを設定
     *
     * @param version スタンプシートのタスクを実行する
     */
    public void setVersion(Version version) {
        this.version = version;
    }

    /**
     * バージョンを設定
     *
     * @param version スタンプシートのタスクを実行する
     * @return this
     */
    public CalculateSignatureRequest withVersion(Version version) {
        setVersion(version);
        return this;
    }

}