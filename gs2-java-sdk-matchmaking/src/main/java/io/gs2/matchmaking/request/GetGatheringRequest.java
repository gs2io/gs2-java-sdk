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
 * ギャザリングを取得 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class GetGatheringRequest extends Gs2BasicRequest<GetGatheringRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return ギャザリングを取得
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ギャザリングを取得
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ギャザリングを取得
     * @return this
     */
    public GetGatheringRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** ギャザリング名 */
    private String gatheringName;

    /**
     * ギャザリング名を取得
     *
     * @return ギャザリングを取得
     */
    public String getGatheringName() {
        return gatheringName;
    }

    /**
     * ギャザリング名を設定
     *
     * @param gatheringName ギャザリングを取得
     */
    public void setGatheringName(String gatheringName) {
        this.gatheringName = gatheringName;
    }

    /**
     * ギャザリング名を設定
     *
     * @param gatheringName ギャザリングを取得
     * @return this
     */
    public GetGatheringRequest withGatheringName(String gatheringName) {
        setGatheringName(gatheringName);
        return this;
    }

}