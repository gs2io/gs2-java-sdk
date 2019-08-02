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

package io.gs2.stamina.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.stamina.model.*;
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

    /** スタミナオーバーフロー上限に当たって回復できなかったスタミナを通知する スクリプト のGRN */
    private String overflowTriggerScriptId;

    /**
     * スタミナオーバーフロー上限に当たって回復できなかったスタミナを通知する スクリプト のGRNを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getOverflowTriggerScriptId() {
        return overflowTriggerScriptId;
    }

    /**
     * スタミナオーバーフロー上限に当たって回復できなかったスタミナを通知する スクリプト のGRNを設定
     *
     * @param overflowTriggerScriptId ネームスペースを新規作成
     */
    public void setOverflowTriggerScriptId(String overflowTriggerScriptId) {
        this.overflowTriggerScriptId = overflowTriggerScriptId;
    }

    /**
     * スタミナオーバーフロー上限に当たって回復できなかったスタミナを通知する スクリプト のGRNを設定
     *
     * @param overflowTriggerScriptId ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withOverflowTriggerScriptId(String overflowTriggerScriptId) {
        setOverflowTriggerScriptId(overflowTriggerScriptId);
        return this;
    }

    /** スタミナオーバーフロー上限に当たって回復できなかったスタミナを追加する ネームスペース のGRN */
    private String overflowTriggerNamespaceId;

    /**
     * スタミナオーバーフロー上限に当たって回復できなかったスタミナを追加する ネームスペース のGRNを取得
     *
     * @return ネームスペースを新規作成
     */
    public String getOverflowTriggerNamespaceId() {
        return overflowTriggerNamespaceId;
    }

    /**
     * スタミナオーバーフロー上限に当たって回復できなかったスタミナを追加する ネームスペース のGRNを設定
     *
     * @param overflowTriggerNamespaceId ネームスペースを新規作成
     */
    public void setOverflowTriggerNamespaceId(String overflowTriggerNamespaceId) {
        this.overflowTriggerNamespaceId = overflowTriggerNamespaceId;
    }

    /**
     * スタミナオーバーフロー上限に当たって回復できなかったスタミナを追加する ネームスペース のGRNを設定
     *
     * @param overflowTriggerNamespaceId ネームスペースを新規作成
     * @return this
     */
    public CreateNamespaceRequest withOverflowTriggerNamespaceId(String overflowTriggerNamespaceId) {
        setOverflowTriggerNamespaceId(overflowTriggerNamespaceId);
        return this;
    }

}