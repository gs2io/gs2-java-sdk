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
 * ユーザIDを指定してジョブを登録 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class PushByUserIdRequest extends Gs2BasicRequest<PushByUserIdRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return ユーザIDを指定してジョブを登録
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ユーザIDを指定してジョブを登録
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ユーザIDを指定してジョブを登録
     * @return this
     */
    public PushByUserIdRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** ユーザーID */
    private String userId;

    /**
     * ユーザーIDを取得
     *
     * @return ユーザIDを指定してジョブを登録
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId ユーザIDを指定してジョブを登録
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId ユーザIDを指定してジョブを登録
     * @return this
     */
    public PushByUserIdRequest withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    /** 追加するジョブの一覧 */
    private List<JobEntry> jobs;

    /**
     * 追加するジョブの一覧を取得
     *
     * @return ユーザIDを指定してジョブを登録
     */
    public List<JobEntry> getJobs() {
        return jobs;
    }

    /**
     * 追加するジョブの一覧を設定
     *
     * @param jobs ユーザIDを指定してジョブを登録
     */
    public void setJobs(List<JobEntry> jobs) {
        this.jobs = jobs;
    }

    /**
     * 追加するジョブの一覧を設定
     *
     * @param jobs ユーザIDを指定してジョブを登録
     * @return this
     */
    public PushByUserIdRequest withJobs(List<JobEntry> jobs) {
        setJobs(jobs);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return ユーザIDを指定してジョブを登録
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ユーザIDを指定してジョブを登録
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ユーザIDを指定してジョブを登録
     * @return this
     */
    public PushByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

}