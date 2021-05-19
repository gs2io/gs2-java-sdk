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

package io.gs2.project.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.project.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * GS2アカウントを削除します のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class DeleteAccountRequest extends Gs2BasicRequest<DeleteAccountRequest> {

    /** GS2アカウントの名前 */
    private String accountName;

    /**
     * GS2アカウントの名前を取得
     *
     * @return GS2アカウントを削除します
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * GS2アカウントの名前を設定
     *
     * @param accountName GS2アカウントを削除します
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    /**
     * GS2アカウントの名前を設定
     *
     * @param accountName GS2アカウントを削除します
     * @return this
     */
    public DeleteAccountRequest withAccountName(String accountName) {
        setAccountName(accountName);
        return this;
    }

}