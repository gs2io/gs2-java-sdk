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
 * ユーザIDを指定して強化実行を作成 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateProgressByUserIdRequest extends Gs2BasicRequest<CreateProgressByUserIdRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return ユーザIDを指定して強化実行を作成
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ユーザIDを指定して強化実行を作成
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ユーザIDを指定して強化実行を作成
     * @return this
     */
    public CreateProgressByUserIdRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** ユーザーID */
    private String userId;

    /**
     * ユーザーIDを取得
     *
     * @return ユーザIDを指定して強化実行を作成
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId ユーザIDを指定して強化実行を作成
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId ユーザIDを指定して強化実行を作成
     * @return this
     */
    public CreateProgressByUserIdRequest withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    /** 強化レート名 */
    private String rateName;

    /**
     * 強化レート名を取得
     *
     * @return ユーザIDを指定して強化実行を作成
     */
    public String getRateName() {
        return rateName;
    }

    /**
     * 強化レート名を設定
     *
     * @param rateName ユーザIDを指定して強化実行を作成
     */
    public void setRateName(String rateName) {
        this.rateName = rateName;
    }

    /**
     * 強化レート名を設定
     *
     * @param rateName ユーザIDを指定して強化実行を作成
     * @return this
     */
    public CreateProgressByUserIdRequest withRateName(String rateName) {
        setRateName(rateName);
        return this;
    }

    /** 強化対象の GS2-Inventory アイテムセットGRN */
    private String targetItemSetId;

    /**
     * 強化対象の GS2-Inventory アイテムセットGRNを取得
     *
     * @return ユーザIDを指定して強化実行を作成
     */
    public String getTargetItemSetId() {
        return targetItemSetId;
    }

    /**
     * 強化対象の GS2-Inventory アイテムセットGRNを設定
     *
     * @param targetItemSetId ユーザIDを指定して強化実行を作成
     */
    public void setTargetItemSetId(String targetItemSetId) {
        this.targetItemSetId = targetItemSetId;
    }

    /**
     * 強化対象の GS2-Inventory アイテムセットGRNを設定
     *
     * @param targetItemSetId ユーザIDを指定して強化実行を作成
     * @return this
     */
    public CreateProgressByUserIdRequest withTargetItemSetId(String targetItemSetId) {
        setTargetItemSetId(targetItemSetId);
        return this;
    }

    /** 強化素材リスト */
    private List<Material> materials;

    /**
     * 強化素材リストを取得
     *
     * @return ユーザIDを指定して強化実行を作成
     */
    public List<Material> getMaterials() {
        return materials;
    }

    /**
     * 強化素材リストを設定
     *
     * @param materials ユーザIDを指定して強化実行を作成
     */
    public void setMaterials(List<Material> materials) {
        this.materials = materials;
    }

    /**
     * 強化素材リストを設定
     *
     * @param materials ユーザIDを指定して強化実行を作成
     * @return this
     */
    public CreateProgressByUserIdRequest withMaterials(List<Material> materials) {
        setMaterials(materials);
        return this;
    }

    /** すでに開始している強化がある場合にそれを破棄して開始するか */
    private Boolean force;

    /**
     * すでに開始している強化がある場合にそれを破棄して開始するかを取得
     *
     * @return ユーザIDを指定して強化実行を作成
     */
    public Boolean getForce() {
        return force;
    }

    /**
     * すでに開始している強化がある場合にそれを破棄して開始するかを設定
     *
     * @param force ユーザIDを指定して強化実行を作成
     */
    public void setForce(Boolean force) {
        this.force = force;
    }

    /**
     * すでに開始している強化がある場合にそれを破棄して開始するかを設定
     *
     * @param force ユーザIDを指定して強化実行を作成
     * @return this
     */
    public CreateProgressByUserIdRequest withForce(Boolean force) {
        setForce(force);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return ユーザIDを指定して強化実行を作成
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ユーザIDを指定して強化実行を作成
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ユーザIDを指定して強化実行を作成
     * @return this
     */
    public CreateProgressByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

}