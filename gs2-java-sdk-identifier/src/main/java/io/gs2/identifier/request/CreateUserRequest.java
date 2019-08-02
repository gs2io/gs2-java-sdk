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
 * ユーザを新規作成します のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateUserRequest extends Gs2BasicRequest<CreateUserRequest> {

    /** ユーザー名 */
    private String name;

    /**
     * ユーザー名を取得
     *
     * @return ユーザを新規作成します
     */
    public String getName() {
        return name;
    }

    /**
     * ユーザー名を設定
     *
     * @param name ユーザを新規作成します
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * ユーザー名を設定
     *
     * @param name ユーザを新規作成します
     * @return this
     */
    public CreateUserRequest withName(String name) {
        setName(name);
        return this;
    }

    /** ユーザの説明 */
    private String description;

    /**
     * ユーザの説明を取得
     *
     * @return ユーザを新規作成します
     */
    public String getDescription() {
        return description;
    }

    /**
     * ユーザの説明を設定
     *
     * @param description ユーザを新規作成します
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * ユーザの説明を設定
     *
     * @param description ユーザを新規作成します
     * @return this
     */
    public CreateUserRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

}