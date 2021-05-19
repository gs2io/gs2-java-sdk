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

package io.gs2.watch.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.watch.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * 累積値を取得 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class GetCumulativeRequest extends Gs2BasicRequest<GetCumulativeRequest> {

    /** 名前 */
    private String name;

    /**
     * 名前を取得
     *
     * @return 累積値を取得
     */
    public String getName() {
        return name;
    }

    /**
     * 名前を設定
     *
     * @param name 累積値を取得
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 名前を設定
     *
     * @param name 累積値を取得
     * @return this
     */
    public GetCumulativeRequest withName(String name) {
        setName(name);
        return this;
    }

    /** リソースのGRN */
    private String resourceGrn;

    /**
     * リソースのGRNを取得
     *
     * @return 累積値を取得
     */
    public String getResourceGrn() {
        return resourceGrn;
    }

    /**
     * リソースのGRNを設定
     *
     * @param resourceGrn 累積値を取得
     */
    public void setResourceGrn(String resourceGrn) {
        this.resourceGrn = resourceGrn;
    }

    /**
     * リソースのGRNを設定
     *
     * @param resourceGrn 累積値を取得
     * @return this
     */
    public GetCumulativeRequest withResourceGrn(String resourceGrn) {
        setResourceGrn(resourceGrn);
        return this;
    }

}