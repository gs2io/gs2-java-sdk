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

package io.gs2.enhance.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.enhance.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * 強化を開始 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class StartRequest extends Gs2BasicRequest<StartRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return 強化を開始
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 強化を開始
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 強化を開始
     * @return this
     */
    public StartRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** 強化レート名 */
    private String rateName;

    /**
     * 強化レート名を取得
     *
     * @return 強化を開始
     */
    public String getRateName() {
        return rateName;
    }

    /**
     * 強化レート名を設定
     *
     * @param rateName 強化を開始
     */
    public void setRateName(String rateName) {
        this.rateName = rateName;
    }

    /**
     * 強化レート名を設定
     *
     * @param rateName 強化を開始
     * @return this
     */
    public StartRequest withRateName(String rateName) {
        setRateName(rateName);
        return this;
    }

    /** 強化対象の GS2-Inventory アイテムセットGRN */
    private String targetItemSetId;

    /**
     * 強化対象の GS2-Inventory アイテムセットGRNを取得
     *
     * @return 強化を開始
     */
    public String getTargetItemSetId() {
        return targetItemSetId;
    }

    /**
     * 強化対象の GS2-Inventory アイテムセットGRNを設定
     *
     * @param targetItemSetId 強化を開始
     */
    public void setTargetItemSetId(String targetItemSetId) {
        this.targetItemSetId = targetItemSetId;
    }

    /**
     * 強化対象の GS2-Inventory アイテムセットGRNを設定
     *
     * @param targetItemSetId 強化を開始
     * @return this
     */
    public StartRequest withTargetItemSetId(String targetItemSetId) {
        setTargetItemSetId(targetItemSetId);
        return this;
    }

    /** 強化素材リスト */
    private List<Material> materials;

    /**
     * 強化素材リストを取得
     *
     * @return 強化を開始
     */
    public List<Material> getMaterials() {
        return materials;
    }

    /**
     * 強化素材リストを設定
     *
     * @param materials 強化を開始
     */
    public void setMaterials(List<Material> materials) {
        this.materials = materials;
    }

    /**
     * 強化素材リストを設定
     *
     * @param materials 強化を開始
     * @return this
     */
    public StartRequest withMaterials(List<Material> materials) {
        setMaterials(materials);
        return this;
    }

    /** すでに開始している強化がある場合にそれを破棄して開始するか */
    private Boolean force;

    /**
     * すでに開始している強化がある場合にそれを破棄して開始するかを取得
     *
     * @return 強化を開始
     */
    public Boolean getForce() {
        return force;
    }

    /**
     * すでに開始している強化がある場合にそれを破棄して開始するかを設定
     *
     * @param force 強化を開始
     */
    public void setForce(Boolean force) {
        this.force = force;
    }

    /**
     * すでに開始している強化がある場合にそれを破棄して開始するかを設定
     *
     * @param force 強化を開始
     * @return this
     */
    public StartRequest withForce(Boolean force) {
        setForce(force);
        return this;
    }

    /** スタンプシートの変数に適用する設定値 */
    private List<Config> config;

    /**
     * スタンプシートの変数に適用する設定値を取得
     *
     * @return 強化を開始
     */
    public List<Config> getConfig() {
        return config;
    }

    /**
     * スタンプシートの変数に適用する設定値を設定
     *
     * @param config 強化を開始
     */
    public void setConfig(List<Config> config) {
        this.config = config;
    }

    /**
     * スタンプシートの変数に適用する設定値を設定
     *
     * @param config 強化を開始
     * @return this
     */
    public StartRequest withConfig(List<Config> config) {
        setConfig(config);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return 強化を開始
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider 強化を開始
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider 強化を開始
     * @return this
     */
    public StartRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

    /** アクセストークン */
    private String accessToken;

    /**
     * アクセストークンを取得
     *
     * @return アクセストークン
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * アクセストークンを設定
     *
     * @param accessToken アクセストークン
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * アクセストークンを設定
     *
     * @param accessToken アクセストークン
     * @return this
     */
    public StartRequest withAccessToken(String accessToken) {
        setAccessToken(accessToken);
        return this;
    }

}