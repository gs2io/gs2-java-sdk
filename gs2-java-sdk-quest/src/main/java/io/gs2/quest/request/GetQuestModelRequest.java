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

package io.gs2.quest.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.quest.model.*;
import io.gs2.control.Gs2BasicRequest;

/**
 * クエストモデルを取得 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class GetQuestModelRequest extends Gs2BasicRequest<GetQuestModelRequest> {

    /** カテゴリ名 */
    private String namespaceName;

    /**
     * カテゴリ名を取得
     *
     * @return クエストモデルを取得
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * カテゴリ名を設定
     *
     * @param namespaceName クエストモデルを取得
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * カテゴリ名を設定
     *
     * @param namespaceName クエストモデルを取得
     * @return this
     */
    public GetQuestModelRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** クエストグループモデル名 */
    private String questGroupName;

    /**
     * クエストグループモデル名を取得
     *
     * @return クエストモデルを取得
     */
    public String getQuestGroupName() {
        return questGroupName;
    }

    /**
     * クエストグループモデル名を設定
     *
     * @param questGroupName クエストモデルを取得
     */
    public void setQuestGroupName(String questGroupName) {
        this.questGroupName = questGroupName;
    }

    /**
     * クエストグループモデル名を設定
     *
     * @param questGroupName クエストモデルを取得
     * @return this
     */
    public GetQuestModelRequest withQuestGroupName(String questGroupName) {
        setQuestGroupName(questGroupName);
        return this;
    }

    /** クエスト名 */
    private String questName;

    /**
     * クエスト名を取得
     *
     * @return クエストモデルを取得
     */
    public String getQuestName() {
        return questName;
    }

    /**
     * クエスト名を設定
     *
     * @param questName クエストモデルを取得
     */
    public void setQuestName(String questName) {
        this.questName = questName;
    }

    /**
     * クエスト名を設定
     *
     * @param questName クエストモデルを取得
     * @return this
     */
    public GetQuestModelRequest withQuestName(String questName) {
        setQuestName(questName);
        return this;
    }

}