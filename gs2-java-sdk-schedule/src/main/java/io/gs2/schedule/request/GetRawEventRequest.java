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

package io.gs2.schedule.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.schedule.model.*;
import io.gs2.control.Gs2BasicRequest;

/**
 * イベントを取得 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class GetRawEventRequest extends Gs2BasicRequest<GetRawEventRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return イベントを取得
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName イベントを取得
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName イベントを取得
     * @return this
     */
    public GetRawEventRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** イベントの種類名 */
    private String eventName;

    /**
     * イベントの種類名を取得
     *
     * @return イベントを取得
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * イベントの種類名を設定
     *
     * @param eventName イベントを取得
     */
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    /**
     * イベントの種類名を設定
     *
     * @param eventName イベントを取得
     * @return this
     */
    public GetRawEventRequest withEventName(String eventName) {
        setEventName(eventName);
        return this;
    }

}