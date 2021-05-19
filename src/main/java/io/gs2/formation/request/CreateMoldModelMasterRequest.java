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

package io.gs2.formation.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.formation.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * フォームの保存領域マスターを新規作成 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateMoldModelMasterRequest extends Gs2BasicRequest<CreateMoldModelMasterRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return フォームの保存領域マスターを新規作成
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName フォームの保存領域マスターを新規作成
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName フォームの保存領域マスターを新規作成
     * @return this
     */
    public CreateMoldModelMasterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** フォームの保存領域名 */
    private String name;

    /**
     * フォームの保存領域名を取得
     *
     * @return フォームの保存領域マスターを新規作成
     */
    public String getName() {
        return name;
    }

    /**
     * フォームの保存領域名を設定
     *
     * @param name フォームの保存領域マスターを新規作成
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * フォームの保存領域名を設定
     *
     * @param name フォームの保存領域マスターを新規作成
     * @return this
     */
    public CreateMoldModelMasterRequest withName(String name) {
        setName(name);
        return this;
    }

    /** フォームの保存領域マスターの説明 */
    private String description;

    /**
     * フォームの保存領域マスターの説明を取得
     *
     * @return フォームの保存領域マスターを新規作成
     */
    public String getDescription() {
        return description;
    }

    /**
     * フォームの保存領域マスターの説明を設定
     *
     * @param description フォームの保存領域マスターを新規作成
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * フォームの保存領域マスターの説明を設定
     *
     * @param description フォームの保存領域マスターを新規作成
     * @return this
     */
    public CreateMoldModelMasterRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** フォームの保存領域のメタデータ */
    private String metadata;

    /**
     * フォームの保存領域のメタデータを取得
     *
     * @return フォームの保存領域マスターを新規作成
     */
    public String getMetadata() {
        return metadata;
    }

    /**
     * フォームの保存領域のメタデータを設定
     *
     * @param metadata フォームの保存領域マスターを新規作成
     */
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    /**
     * フォームの保存領域のメタデータを設定
     *
     * @param metadata フォームの保存領域マスターを新規作成
     * @return this
     */
    public CreateMoldModelMasterRequest withMetadata(String metadata) {
        setMetadata(metadata);
        return this;
    }

    /** フォーム名 */
    private String formModelName;

    /**
     * フォーム名を取得
     *
     * @return フォームの保存領域マスターを新規作成
     */
    public String getFormModelName() {
        return formModelName;
    }

    /**
     * フォーム名を設定
     *
     * @param formModelName フォームの保存領域マスターを新規作成
     */
    public void setFormModelName(String formModelName) {
        this.formModelName = formModelName;
    }

    /**
     * フォーム名を設定
     *
     * @param formModelName フォームの保存領域マスターを新規作成
     * @return this
     */
    public CreateMoldModelMasterRequest withFormModelName(String formModelName) {
        setFormModelName(formModelName);
        return this;
    }

    /** フォームを保存できる初期キャパシティ */
    private Integer initialMaxCapacity;

    /**
     * フォームを保存できる初期キャパシティを取得
     *
     * @return フォームの保存領域マスターを新規作成
     */
    public Integer getInitialMaxCapacity() {
        return initialMaxCapacity;
    }

    /**
     * フォームを保存できる初期キャパシティを設定
     *
     * @param initialMaxCapacity フォームの保存領域マスターを新規作成
     */
    public void setInitialMaxCapacity(Integer initialMaxCapacity) {
        this.initialMaxCapacity = initialMaxCapacity;
    }

    /**
     * フォームを保存できる初期キャパシティを設定
     *
     * @param initialMaxCapacity フォームの保存領域マスターを新規作成
     * @return this
     */
    public CreateMoldModelMasterRequest withInitialMaxCapacity(Integer initialMaxCapacity) {
        setInitialMaxCapacity(initialMaxCapacity);
        return this;
    }

    /** フォームを保存できるキャパシティ */
    private Integer maxCapacity;

    /**
     * フォームを保存できるキャパシティを取得
     *
     * @return フォームの保存領域マスターを新規作成
     */
    public Integer getMaxCapacity() {
        return maxCapacity;
    }

    /**
     * フォームを保存できるキャパシティを設定
     *
     * @param maxCapacity フォームの保存領域マスターを新規作成
     */
    public void setMaxCapacity(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    /**
     * フォームを保存できるキャパシティを設定
     *
     * @param maxCapacity フォームの保存領域マスターを新規作成
     * @return this
     */
    public CreateMoldModelMasterRequest withMaxCapacity(Integer maxCapacity) {
        setMaxCapacity(maxCapacity);
        return this;
    }

}