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

package io.gs2.matchmaking.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.matchmaking.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * ギャザリングを作成して募集を開始 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateGatheringByUserIdRequest extends Gs2BasicRequest<CreateGatheringByUserIdRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return ギャザリングを作成して募集を開始
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ギャザリングを作成して募集を開始
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ギャザリングを作成して募集を開始
     * @return this
     */
    public CreateGatheringByUserIdRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** ユーザーID */
    private String userId;

    /**
     * ユーザーIDを取得
     *
     * @return ギャザリングを作成して募集を開始
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId ギャザリングを作成して募集を開始
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId ギャザリングを作成して募集を開始
     * @return this
     */
    public CreateGatheringByUserIdRequest withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    /** 自身のプレイヤー情報 */
    private Player player;

    /**
     * 自身のプレイヤー情報を取得
     *
     * @return ギャザリングを作成して募集を開始
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * 自身のプレイヤー情報を設定
     *
     * @param player ギャザリングを作成して募集を開始
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * 自身のプレイヤー情報を設定
     *
     * @param player ギャザリングを作成して募集を開始
     * @return this
     */
    public CreateGatheringByUserIdRequest withPlayer(Player player) {
        setPlayer(player);
        return this;
    }

    /** 募集条件 */
    private List<AttributeRange> attributeRanges;

    /**
     * 募集条件を取得
     *
     * @return ギャザリングを作成して募集を開始
     */
    public List<AttributeRange> getAttributeRanges() {
        return attributeRanges;
    }

    /**
     * 募集条件を設定
     *
     * @param attributeRanges ギャザリングを作成して募集を開始
     */
    public void setAttributeRanges(List<AttributeRange> attributeRanges) {
        this.attributeRanges = attributeRanges;
    }

    /**
     * 募集条件を設定
     *
     * @param attributeRanges ギャザリングを作成して募集を開始
     * @return this
     */
    public CreateGatheringByUserIdRequest withAttributeRanges(List<AttributeRange> attributeRanges) {
        setAttributeRanges(attributeRanges);
        return this;
    }

    /** 参加者 */
    private List<CapacityOfRole> capacityOfRoles;

    /**
     * 参加者を取得
     *
     * @return ギャザリングを作成して募集を開始
     */
    public List<CapacityOfRole> getCapacityOfRoles() {
        return capacityOfRoles;
    }

    /**
     * 参加者を設定
     *
     * @param capacityOfRoles ギャザリングを作成して募集を開始
     */
    public void setCapacityOfRoles(List<CapacityOfRole> capacityOfRoles) {
        this.capacityOfRoles = capacityOfRoles;
    }

    /**
     * 参加者を設定
     *
     * @param capacityOfRoles ギャザリングを作成して募集を開始
     * @return this
     */
    public CreateGatheringByUserIdRequest withCapacityOfRoles(List<CapacityOfRole> capacityOfRoles) {
        setCapacityOfRoles(capacityOfRoles);
        return this;
    }

    /** 参加を許可するユーザIDリスト */
    private List<String> allowUserIds;

    /**
     * 参加を許可するユーザIDリストを取得
     *
     * @return ギャザリングを作成して募集を開始
     */
    public List<String> getAllowUserIds() {
        return allowUserIds;
    }

    /**
     * 参加を許可するユーザIDリストを設定
     *
     * @param allowUserIds ギャザリングを作成して募集を開始
     */
    public void setAllowUserIds(List<String> allowUserIds) {
        this.allowUserIds = allowUserIds;
    }

    /**
     * 参加を許可するユーザIDリストを設定
     *
     * @param allowUserIds ギャザリングを作成して募集を開始
     * @return this
     */
    public CreateGatheringByUserIdRequest withAllowUserIds(List<String> allowUserIds) {
        setAllowUserIds(allowUserIds);
        return this;
    }

    /** ギャザリングの有効期限 */
    private Long expiresAt;

    /**
     * ギャザリングの有効期限を取得
     *
     * @return ギャザリングを作成して募集を開始
     */
    public Long getExpiresAt() {
        return expiresAt;
    }

    /**
     * ギャザリングの有効期限を設定
     *
     * @param expiresAt ギャザリングを作成して募集を開始
     */
    public void setExpiresAt(Long expiresAt) {
        this.expiresAt = expiresAt;
    }

    /**
     * ギャザリングの有効期限を設定
     *
     * @param expiresAt ギャザリングを作成して募集を開始
     * @return this
     */
    public CreateGatheringByUserIdRequest withExpiresAt(Long expiresAt) {
        setExpiresAt(expiresAt);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return ギャザリングを作成して募集を開始
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ギャザリングを作成して募集を開始
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ギャザリングを作成して募集を開始
     * @return this
     */
    public CreateGatheringByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

}