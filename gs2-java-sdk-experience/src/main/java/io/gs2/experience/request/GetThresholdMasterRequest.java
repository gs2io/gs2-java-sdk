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
import io.gs2.control.Gs2BasicRequest;

/**
 * ランクアップ閾値マスターを取得 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class GetThresholdMasterRequest extends Gs2BasicRequest<GetThresholdMasterRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return ランクアップ閾値マスターを取得
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ランクアップ閾値マスターを取得
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ランクアップ閾値マスターを取得
     * @return this
     */
    public GetThresholdMasterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** ランクアップ閾値名 */
    private String thresholdName;

    /**
     * ランクアップ閾値名を取得
     *
     * @return ランクアップ閾値マスターを取得
     */
    public String getThresholdName() {
        return thresholdName;
    }

    /**
     * ランクアップ閾値名を設定
     *
     * @param thresholdName ランクアップ閾値マスターを取得
     */
    public void setThresholdName(String thresholdName) {
        this.thresholdName = thresholdName;
    }

    /**
     * ランクアップ閾値名を設定
     *
     * @param thresholdName ランクアップ閾値マスターを取得
     * @return this
     */
    public GetThresholdMasterRequest withThresholdName(String thresholdName) {
        setThresholdName(thresholdName);
        return this;
    }

}