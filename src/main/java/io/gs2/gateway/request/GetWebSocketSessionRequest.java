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

package io.gs2.gateway.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.gateway.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * Websocketセッションを取得 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class GetWebSocketSessionRequest extends Gs2BasicRequest<GetWebSocketSessionRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return Websocketセッションを取得
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName Websocketセッションを取得
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName Websocketセッションを取得
     * @return this
     */
    public GetWebSocketSessionRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** コネクションID */
    private String connectionId;

    /**
     * コネクションIDを取得
     *
     * @return Websocketセッションを取得
     */
    public String getConnectionId() {
        return connectionId;
    }

    /**
     * コネクションIDを設定
     *
     * @param connectionId Websocketセッションを取得
     */
    public void setConnectionId(String connectionId) {
        this.connectionId = connectionId;
    }

    /**
     * コネクションIDを設定
     *
     * @param connectionId Websocketセッションを取得
     * @return this
     */
    public GetWebSocketSessionRequest withConnectionId(String connectionId) {
        setConnectionId(connectionId);
        return this;
    }

}