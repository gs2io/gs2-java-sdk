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

package io.gs2.schedule.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.schedule.model.*;
import io.gs2.control.Gs2BasicRequest;

/**
 * イベントマスターを新規作成 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateEventMasterRequest extends Gs2BasicRequest<CreateEventMasterRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return イベントマスターを新規作成
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName イベントマスターを新規作成
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName イベントマスターを新規作成
     * @return this
     */
    public CreateEventMasterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** イベントの種類名 */
    private String name;

    /**
     * イベントの種類名を取得
     *
     * @return イベントマスターを新規作成
     */
    public String getName() {
        return name;
    }

    /**
     * イベントの種類名を設定
     *
     * @param name イベントマスターを新規作成
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * イベントの種類名を設定
     *
     * @param name イベントマスターを新規作成
     * @return this
     */
    public CreateEventMasterRequest withName(String name) {
        setName(name);
        return this;
    }

    /** イベントマスターの説明 */
    private String description;

    /**
     * イベントマスターの説明を取得
     *
     * @return イベントマスターを新規作成
     */
    public String getDescription() {
        return description;
    }

    /**
     * イベントマスターの説明を設定
     *
     * @param description イベントマスターを新規作成
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * イベントマスターの説明を設定
     *
     * @param description イベントマスターを新規作成
     * @return this
     */
    public CreateEventMasterRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** イベントの種類のメタデータ */
    private String metadata;

    /**
     * イベントの種類のメタデータを取得
     *
     * @return イベントマスターを新規作成
     */
    public String getMetadata() {
        return metadata;
    }

    /**
     * イベントの種類のメタデータを設定
     *
     * @param metadata イベントマスターを新規作成
     */
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    /**
     * イベントの種類のメタデータを設定
     *
     * @param metadata イベントマスターを新規作成
     * @return this
     */
    public CreateEventMasterRequest withMetadata(String metadata) {
        setMetadata(metadata);
        return this;
    }

    /** イベント期間の種類 */
    private String scheduleType;

    /**
     * イベント期間の種類を取得
     *
     * @return イベントマスターを新規作成
     */
    public String getScheduleType() {
        return scheduleType;
    }

    /**
     * イベント期間の種類を設定
     *
     * @param scheduleType イベントマスターを新規作成
     */
    public void setScheduleType(String scheduleType) {
        this.scheduleType = scheduleType;
    }

    /**
     * イベント期間の種類を設定
     *
     * @param scheduleType イベントマスターを新規作成
     * @return this
     */
    public CreateEventMasterRequest withScheduleType(String scheduleType) {
        setScheduleType(scheduleType);
        return this;
    }

    /** イベントの開始日時 */
    private Long absoluteBegin;

    /**
     * イベントの開始日時を取得
     *
     * @return イベントマスターを新規作成
     */
    public Long getAbsoluteBegin() {
        return absoluteBegin;
    }

    /**
     * イベントの開始日時を設定
     *
     * @param absoluteBegin イベントマスターを新規作成
     */
    public void setAbsoluteBegin(Long absoluteBegin) {
        this.absoluteBegin = absoluteBegin;
    }

    /**
     * イベントの開始日時を設定
     *
     * @param absoluteBegin イベントマスターを新規作成
     * @return this
     */
    public CreateEventMasterRequest withAbsoluteBegin(Long absoluteBegin) {
        setAbsoluteBegin(absoluteBegin);
        return this;
    }

    /** イベントの終了日時 */
    private Long absoluteEnd;

    /**
     * イベントの終了日時を取得
     *
     * @return イベントマスターを新規作成
     */
    public Long getAbsoluteEnd() {
        return absoluteEnd;
    }

    /**
     * イベントの終了日時を設定
     *
     * @param absoluteEnd イベントマスターを新規作成
     */
    public void setAbsoluteEnd(Long absoluteEnd) {
        this.absoluteEnd = absoluteEnd;
    }

    /**
     * イベントの終了日時を設定
     *
     * @param absoluteEnd イベントマスターを新規作成
     * @return this
     */
    public CreateEventMasterRequest withAbsoluteEnd(Long absoluteEnd) {
        setAbsoluteEnd(absoluteEnd);
        return this;
    }

    /** イベントの開始トリガー名 */
    private String relativeTriggerName;

    /**
     * イベントの開始トリガー名を取得
     *
     * @return イベントマスターを新規作成
     */
    public String getRelativeTriggerName() {
        return relativeTriggerName;
    }

    /**
     * イベントの開始トリガー名を設定
     *
     * @param relativeTriggerName イベントマスターを新規作成
     */
    public void setRelativeTriggerName(String relativeTriggerName) {
        this.relativeTriggerName = relativeTriggerName;
    }

    /**
     * イベントの開始トリガー名を設定
     *
     * @param relativeTriggerName イベントマスターを新規作成
     * @return this
     */
    public CreateEventMasterRequest withRelativeTriggerName(String relativeTriggerName) {
        setRelativeTriggerName(relativeTriggerName);
        return this;
    }

    /** イベントの開催期間(秒) */
    private Integer relativeDuration;

    /**
     * イベントの開催期間(秒)を取得
     *
     * @return イベントマスターを新規作成
     */
    public Integer getRelativeDuration() {
        return relativeDuration;
    }

    /**
     * イベントの開催期間(秒)を設定
     *
     * @param relativeDuration イベントマスターを新規作成
     */
    public void setRelativeDuration(Integer relativeDuration) {
        this.relativeDuration = relativeDuration;
    }

    /**
     * イベントの開催期間(秒)を設定
     *
     * @param relativeDuration イベントマスターを新規作成
     * @return this
     */
    public CreateEventMasterRequest withRelativeDuration(Integer relativeDuration) {
        setRelativeDuration(relativeDuration);
        return this;
    }

}