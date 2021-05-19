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

package io.gs2.experience.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.experience.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * 経験値・ランクアップ閾値モデルを取得 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class GetExperienceModelRequest extends Gs2BasicRequest<GetExperienceModelRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return 経験値・ランクアップ閾値モデルを取得
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 経験値・ランクアップ閾値モデルを取得
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 経験値・ランクアップ閾値モデルを取得
     * @return this
     */
    public GetExperienceModelRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** 経験値の種類名 */
    private String experienceName;

    /**
     * 経験値の種類名を取得
     *
     * @return 経験値・ランクアップ閾値モデルを取得
     */
    public String getExperienceName() {
        return experienceName;
    }

    /**
     * 経験値の種類名を設定
     *
     * @param experienceName 経験値・ランクアップ閾値モデルを取得
     */
    public void setExperienceName(String experienceName) {
        this.experienceName = experienceName;
    }

    /**
     * 経験値の種類名を設定
     *
     * @param experienceName 経験値・ランクアップ閾値モデルを取得
     * @return this
     */
    public GetExperienceModelRequest withExperienceName(String experienceName) {
        setExperienceName(experienceName);
        return this;
    }

}