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

package io.gs2.mission.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.mission.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * カウンターの種類マスターを削除 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class DeleteCounterModelMasterRequest extends Gs2BasicRequest<DeleteCounterModelMasterRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return カウンターの種類マスターを削除
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName カウンターの種類マスターを削除
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName カウンターの種類マスターを削除
     * @return this
     */
    public DeleteCounterModelMasterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** カウンター名 */
    private String counterName;

    /**
     * カウンター名を取得
     *
     * @return カウンターの種類マスターを削除
     */
    public String getCounterName() {
        return counterName;
    }

    /**
     * カウンター名を設定
     *
     * @param counterName カウンターの種類マスターを削除
     */
    public void setCounterName(String counterName) {
        this.counterName = counterName;
    }

    /**
     * カウンター名を設定
     *
     * @param counterName カウンターの種類マスターを削除
     * @return this
     */
    public DeleteCounterModelMasterRequest withCounterName(String counterName) {
        setCounterName(counterName);
        return this;
    }

}