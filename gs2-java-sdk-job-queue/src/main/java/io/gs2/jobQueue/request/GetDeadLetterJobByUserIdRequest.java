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

package io.gs2.jobQueue.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.jobQueue.model.*;
import io.gs2.control.Gs2BasicRequest;

/**
 * デッドレタージョブを取得 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class GetDeadLetterJobByUserIdRequest extends Gs2BasicRequest<GetDeadLetterJobByUserIdRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return デッドレタージョブを取得
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName デッドレタージョブを取得
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName デッドレタージョブを取得
     * @return this
     */
    public GetDeadLetterJobByUserIdRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** ユーザーID */
    private String userId;

    /**
     * ユーザーIDを取得
     *
     * @return デッドレタージョブを取得
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId デッドレタージョブを取得
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId デッドレタージョブを取得
     * @return this
     */
    public GetDeadLetterJobByUserIdRequest withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    /** ジョブの名前 */
    private String deadLetterJobName;

    /**
     * ジョブの名前を取得
     *
     * @return デッドレタージョブを取得
     */
    public String getDeadLetterJobName() {
        return deadLetterJobName;
    }

    /**
     * ジョブの名前を設定
     *
     * @param deadLetterJobName デッドレタージョブを取得
     */
    public void setDeadLetterJobName(String deadLetterJobName) {
        this.deadLetterJobName = deadLetterJobName;
    }

    /**
     * ジョブの名前を設定
     *
     * @param deadLetterJobName デッドレタージョブを取得
     * @return this
     */
    public GetDeadLetterJobByUserIdRequest withDeadLetterJobName(String deadLetterJobName) {
        setDeadLetterJobName(deadLetterJobName);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return デッドレタージョブを取得
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider デッドレタージョブを取得
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider デッドレタージョブを取得
     * @return this
     */
    public GetDeadLetterJobByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

}