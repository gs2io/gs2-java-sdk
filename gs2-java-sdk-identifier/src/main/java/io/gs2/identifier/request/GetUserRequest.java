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

package io.gs2.identifier.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.identifier.model.*;
import io.gs2.control.Gs2BasicRequest;

/**
 * ユーザを取得します のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class GetUserRequest extends Gs2BasicRequest<GetUserRequest> {

    /** ユーザー名 */
    private String userName;

    /**
     * ユーザー名を取得
     *
     * @return ユーザを取得します
     */
    public String getUserName() {
        return userName;
    }

    /**
     * ユーザー名を設定
     *
     * @param userName ユーザを取得します
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * ユーザー名を設定
     *
     * @param userName ユーザを取得します
     * @return this
     */
    public GetUserRequest withUserName(String userName) {
        setUserName(userName);
        return this;
    }

}