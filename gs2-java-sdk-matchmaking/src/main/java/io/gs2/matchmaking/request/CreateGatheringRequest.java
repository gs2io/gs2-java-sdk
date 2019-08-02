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
import io.gs2.control.Gs2BasicRequest;

/**
 * ギャザリングを作成して募集を開始 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateGatheringRequest extends Gs2BasicRequest<CreateGatheringRequest> {

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
    public CreateGatheringRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
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
    public CreateGatheringRequest withPlayer(Player player) {
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
    public CreateGatheringRequest withAttributeRanges(List<AttributeRange> attributeRanges) {
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
    public CreateGatheringRequest withCapacityOfRoles(List<CapacityOfRole> capacityOfRoles) {
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
    public CreateGatheringRequest withAllowUserIds(List<String> allowUserIds) {
        setAllowUserIds(allowUserIds);
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
    public CreateGatheringRequest withDuplicationAvoider(String duplicationAvoider) {
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
    public CreateGatheringRequest withAccessToken(String accessToken) {
        setAccessToken(accessToken);
        return this;
    }

}