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
 * フォームマスターを新規作成 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateFormModelMasterRequest extends Gs2BasicRequest<CreateFormModelMasterRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return フォームマスターを新規作成
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName フォームマスターを新規作成
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName フォームマスターを新規作成
     * @return this
     */
    public CreateFormModelMasterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** フォーム名 */
    private String name;

    /**
     * フォーム名を取得
     *
     * @return フォームマスターを新規作成
     */
    public String getName() {
        return name;
    }

    /**
     * フォーム名を設定
     *
     * @param name フォームマスターを新規作成
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * フォーム名を設定
     *
     * @param name フォームマスターを新規作成
     * @return this
     */
    public CreateFormModelMasterRequest withName(String name) {
        setName(name);
        return this;
    }

    /** フォームマスターの説明 */
    private String description;

    /**
     * フォームマスターの説明を取得
     *
     * @return フォームマスターを新規作成
     */
    public String getDescription() {
        return description;
    }

    /**
     * フォームマスターの説明を設定
     *
     * @param description フォームマスターを新規作成
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * フォームマスターの説明を設定
     *
     * @param description フォームマスターを新規作成
     * @return this
     */
    public CreateFormModelMasterRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** フォームのメタデータ */
    private String metadata;

    /**
     * フォームのメタデータを取得
     *
     * @return フォームマスターを新規作成
     */
    public String getMetadata() {
        return metadata;
    }

    /**
     * フォームのメタデータを設定
     *
     * @param metadata フォームマスターを新規作成
     */
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    /**
     * フォームのメタデータを設定
     *
     * @param metadata フォームマスターを新規作成
     * @return this
     */
    public CreateFormModelMasterRequest withMetadata(String metadata) {
        setMetadata(metadata);
        return this;
    }

    /** スロットリスト */
    private List<SlotModel> slots;

    /**
     * スロットリストを取得
     *
     * @return フォームマスターを新規作成
     */
    public List<SlotModel> getSlots() {
        return slots;
    }

    /**
     * スロットリストを設定
     *
     * @param slots フォームマスターを新規作成
     */
    public void setSlots(List<SlotModel> slots) {
        this.slots = slots;
    }

    /**
     * スロットリストを設定
     *
     * @param slots フォームマスターを新規作成
     * @return this
     */
    public CreateFormModelMasterRequest withSlots(List<SlotModel> slots) {
        setSlots(slots);
        return this;
    }

}