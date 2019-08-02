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

package io.gs2.showcase.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.showcase.model.*;
import io.gs2.control.Gs2BasicRequest;

/**
 * 陳列棚マスターを取得 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class GetShowcaseMasterRequest extends Gs2BasicRequest<GetShowcaseMasterRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return 陳列棚マスターを取得
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 陳列棚マスターを取得
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 陳列棚マスターを取得
     * @return this
     */
    public GetShowcaseMasterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** 陳列棚名 */
    private String showcaseName;

    /**
     * 陳列棚名を取得
     *
     * @return 陳列棚マスターを取得
     */
    public String getShowcaseName() {
        return showcaseName;
    }

    /**
     * 陳列棚名を設定
     *
     * @param showcaseName 陳列棚マスターを取得
     */
    public void setShowcaseName(String showcaseName) {
        this.showcaseName = showcaseName;
    }

    /**
     * 陳列棚名を設定
     *
     * @param showcaseName 陳列棚マスターを取得
     * @return this
     */
    public GetShowcaseMasterRequest withShowcaseName(String showcaseName) {
        setShowcaseName(showcaseName);
        return this;
    }

}