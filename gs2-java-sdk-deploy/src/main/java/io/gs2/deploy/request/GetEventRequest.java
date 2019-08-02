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

package io.gs2.deploy.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.deploy.model.*;
import io.gs2.control.Gs2BasicRequest;

/**
 * 発生したイベントを取得 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class GetEventRequest extends Gs2BasicRequest<GetEventRequest> {

    /** スタック名 */
    private String stackName;

    /**
     * スタック名を取得
     *
     * @return 発生したイベントを取得
     */
    public String getStackName() {
        return stackName;
    }

    /**
     * スタック名を設定
     *
     * @param stackName 発生したイベントを取得
     */
    public void setStackName(String stackName) {
        this.stackName = stackName;
    }

    /**
     * スタック名を設定
     *
     * @param stackName 発生したイベントを取得
     * @return this
     */
    public GetEventRequest withStackName(String stackName) {
        setStackName(stackName);
        return this;
    }

    /** イベント名 */
    private String eventName;

    /**
     * イベント名を取得
     *
     * @return 発生したイベントを取得
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * イベント名を設定
     *
     * @param eventName 発生したイベントを取得
     */
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    /**
     * イベント名を設定
     *
     * @param eventName 発生したイベントを取得
     * @return this
     */
    public GetEventRequest withEventName(String eventName) {
        setEventName(eventName);
        return this;
    }

}