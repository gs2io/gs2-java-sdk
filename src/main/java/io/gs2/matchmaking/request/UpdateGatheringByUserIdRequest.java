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
 * ギャザリングを更新する のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class UpdateGatheringByUserIdRequest extends Gs2BasicRequest<UpdateGatheringByUserIdRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return ギャザリングを更新する
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ギャザリングを更新する
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ギャザリングを更新する
     * @return this
     */
    public UpdateGatheringByUserIdRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** ギャザリング名 */
    private String gatheringName;

    /**
     * ギャザリング名を取得
     *
     * @return ギャザリングを更新する
     */
    public String getGatheringName() {
        return gatheringName;
    }

    /**
     * ギャザリング名を設定
     *
     * @param gatheringName ギャザリングを更新する
     */
    public void setGatheringName(String gatheringName) {
        this.gatheringName = gatheringName;
    }

    /**
     * ギャザリング名を設定
     *
     * @param gatheringName ギャザリングを更新する
     * @return this
     */
    public UpdateGatheringByUserIdRequest withGatheringName(String gatheringName) {
        setGatheringName(gatheringName);
        return this;
    }

    /** ユーザーID */
    private String userId;

    /**
     * ユーザーIDを取得
     *
     * @return ギャザリングを更新する
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId ギャザリングを更新する
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId ギャザリングを更新する
     * @return this
     */
    public UpdateGatheringByUserIdRequest withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    /** 募集条件 */
    private List<AttributeRange> attributeRanges;

    /**
     * 募集条件を取得
     *
     * @return ギャザリングを更新する
     */
    public List<AttributeRange> getAttributeRanges() {
        return attributeRanges;
    }

    /**
     * 募集条件を設定
     *
     * @param attributeRanges ギャザリングを更新する
     */
    public void setAttributeRanges(List<AttributeRange> attributeRanges) {
        this.attributeRanges = attributeRanges;
    }

    /**
     * 募集条件を設定
     *
     * @param attributeRanges ギャザリングを更新する
     * @return this
     */
    public UpdateGatheringByUserIdRequest withAttributeRanges(List<AttributeRange> attributeRanges) {
        setAttributeRanges(attributeRanges);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return ギャザリングを更新する
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ギャザリングを更新する
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ギャザリングを更新する
     * @return this
     */
    public UpdateGatheringByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

}