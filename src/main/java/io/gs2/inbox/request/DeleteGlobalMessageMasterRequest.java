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

package io.gs2.inbox.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.inbox.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * 全ユーザに向けたメッセージを削除 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class DeleteGlobalMessageMasterRequest extends Gs2BasicRequest<DeleteGlobalMessageMasterRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return 全ユーザに向けたメッセージを削除
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 全ユーザに向けたメッセージを削除
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 全ユーザに向けたメッセージを削除
     * @return this
     */
    public DeleteGlobalMessageMasterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** 全ユーザに向けたメッセージ名 */
    private String globalMessageName;

    /**
     * 全ユーザに向けたメッセージ名を取得
     *
     * @return 全ユーザに向けたメッセージを削除
     */
    public String getGlobalMessageName() {
        return globalMessageName;
    }

    /**
     * 全ユーザに向けたメッセージ名を設定
     *
     * @param globalMessageName 全ユーザに向けたメッセージを削除
     */
    public void setGlobalMessageName(String globalMessageName) {
        this.globalMessageName = globalMessageName;
    }

    /**
     * 全ユーザに向けたメッセージ名を設定
     *
     * @param globalMessageName 全ユーザに向けたメッセージを削除
     * @return this
     */
    public DeleteGlobalMessageMasterRequest withGlobalMessageName(String globalMessageName) {
        setGlobalMessageName(globalMessageName);
        return this;
    }

}