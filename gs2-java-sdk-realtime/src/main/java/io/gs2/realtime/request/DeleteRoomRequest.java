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

package io.gs2.realtime.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.realtime.model.*;
import io.gs2.control.Gs2BasicRequest;

/**
 * ルームを削除 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class DeleteRoomRequest extends Gs2BasicRequest<DeleteRoomRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return ルームを削除
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ルームを削除
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ルームを削除
     * @return this
     */
    public DeleteRoomRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** ルーム名 */
    private String roomName;

    /**
     * ルーム名を取得
     *
     * @return ルームを削除
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * ルーム名を設定
     *
     * @param roomName ルームを削除
     */
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    /**
     * ルーム名を設定
     *
     * @param roomName ルームを削除
     * @return this
     */
    public DeleteRoomRequest withRoomName(String roomName) {
        setRoomName(roomName);
        return this;
    }

}