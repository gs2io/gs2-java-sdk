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
import io.gs2.core.control.Gs2BasicRequest;

/**
 * イベントマスターを更新 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class UpdateEventMasterRequest extends Gs2BasicRequest<UpdateEventMasterRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return イベントマスターを更新
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName イベントマスターを更新
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName イベントマスターを更新
     * @return this
     */
    public UpdateEventMasterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** イベントの種類名 */
    private String eventName;

    /**
     * イベントの種類名を取得
     *
     * @return イベントマスターを更新
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * イベントの種類名を設定
     *
     * @param eventName イベントマスターを更新
     */
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    /**
     * イベントの種類名を設定
     *
     * @param eventName イベントマスターを更新
     * @return this
     */
    public UpdateEventMasterRequest withEventName(String eventName) {
        setEventName(eventName);
        return this;
    }

    /** イベントマスターの説明 */
    private String description;

    /**
     * イベントマスターの説明を取得
     *
     * @return イベントマスターを更新
     */
    public String getDescription() {
        return description;
    }

    /**
     * イベントマスターの説明を設定
     *
     * @param description イベントマスターを更新
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * イベントマスターの説明を設定
     *
     * @param description イベントマスターを更新
     * @return this
     */
    public UpdateEventMasterRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** イベントの種類のメタデータ */
    private String metadata;

    /**
     * イベントの種類のメタデータを取得
     *
     * @return イベントマスターを更新
     */
    public String getMetadata() {
        return metadata;
    }

    /**
     * イベントの種類のメタデータを設定
     *
     * @param metadata イベントマスターを更新
     */
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    /**
     * イベントの種類のメタデータを設定
     *
     * @param metadata イベントマスターを更新
     * @return this
     */
    public UpdateEventMasterRequest withMetadata(String metadata) {
        setMetadata(metadata);
        return this;
    }

    /** イベント期間の種類 */
    private String scheduleType;

    /**
     * イベント期間の種類を取得
     *
     * @return イベントマスターを更新
     */
    public String getScheduleType() {
        return scheduleType;
    }

    /**
     * イベント期間の種類を設定
     *
     * @param scheduleType イベントマスターを更新
     */
    public void setScheduleType(String scheduleType) {
        this.scheduleType = scheduleType;
    }

    /**
     * イベント期間の種類を設定
     *
     * @param scheduleType イベントマスターを更新
     * @return this
     */
    public UpdateEventMasterRequest withScheduleType(String scheduleType) {
        setScheduleType(scheduleType);
        return this;
    }

    /** イベントの開始日時 */
    private Long absoluteBegin;

    /**
     * イベントの開始日時を取得
     *
     * @return イベントマスターを更新
     */
    public Long getAbsoluteBegin() {
        return absoluteBegin;
    }

    /**
     * イベントの開始日時を設定
     *
     * @param absoluteBegin イベントマスターを更新
     */
    public void setAbsoluteBegin(Long absoluteBegin) {
        this.absoluteBegin = absoluteBegin;
    }

    /**
     * イベントの開始日時を設定
     *
     * @param absoluteBegin イベントマスターを更新
     * @return this
     */
    public UpdateEventMasterRequest withAbsoluteBegin(Long absoluteBegin) {
        setAbsoluteBegin(absoluteBegin);
        return this;
    }

    /** イベントの終了日時 */
    private Long absoluteEnd;

    /**
     * イベントの終了日時を取得
     *
     * @return イベントマスターを更新
     */
    public Long getAbsoluteEnd() {
        return absoluteEnd;
    }

    /**
     * イベントの終了日時を設定
     *
     * @param absoluteEnd イベントマスターを更新
     */
    public void setAbsoluteEnd(Long absoluteEnd) {
        this.absoluteEnd = absoluteEnd;
    }

    /**
     * イベントの終了日時を設定
     *
     * @param absoluteEnd イベントマスターを更新
     * @return this
     */
    public UpdateEventMasterRequest withAbsoluteEnd(Long absoluteEnd) {
        setAbsoluteEnd(absoluteEnd);
        return this;
    }

    /** 繰り返しの種類 */
    private String repeatType;

    /**
     * 繰り返しの種類を取得
     *
     * @return イベントマスターを更新
     */
    public String getRepeatType() {
        return repeatType;
    }

    /**
     * 繰り返しの種類を設定
     *
     * @param repeatType イベントマスターを更新
     */
    public void setRepeatType(String repeatType) {
        this.repeatType = repeatType;
    }

    /**
     * 繰り返しの種類を設定
     *
     * @param repeatType イベントマスターを更新
     * @return this
     */
    public UpdateEventMasterRequest withRepeatType(String repeatType) {
        setRepeatType(repeatType);
        return this;
    }

    /** イベントの繰り返し開始日 */
    private Integer repeatBeginDayOfMonth;

    /**
     * イベントの繰り返し開始日を取得
     *
     * @return イベントマスターを更新
     */
    public Integer getRepeatBeginDayOfMonth() {
        return repeatBeginDayOfMonth;
    }

    /**
     * イベントの繰り返し開始日を設定
     *
     * @param repeatBeginDayOfMonth イベントマスターを更新
     */
    public void setRepeatBeginDayOfMonth(Integer repeatBeginDayOfMonth) {
        this.repeatBeginDayOfMonth = repeatBeginDayOfMonth;
    }

    /**
     * イベントの繰り返し開始日を設定
     *
     * @param repeatBeginDayOfMonth イベントマスターを更新
     * @return this
     */
    public UpdateEventMasterRequest withRepeatBeginDayOfMonth(Integer repeatBeginDayOfMonth) {
        setRepeatBeginDayOfMonth(repeatBeginDayOfMonth);
        return this;
    }

    /** イベントの繰り返し終了日 */
    private Integer repeatEndDayOfMonth;

    /**
     * イベントの繰り返し終了日を取得
     *
     * @return イベントマスターを更新
     */
    public Integer getRepeatEndDayOfMonth() {
        return repeatEndDayOfMonth;
    }

    /**
     * イベントの繰り返し終了日を設定
     *
     * @param repeatEndDayOfMonth イベントマスターを更新
     */
    public void setRepeatEndDayOfMonth(Integer repeatEndDayOfMonth) {
        this.repeatEndDayOfMonth = repeatEndDayOfMonth;
    }

    /**
     * イベントの繰り返し終了日を設定
     *
     * @param repeatEndDayOfMonth イベントマスターを更新
     * @return this
     */
    public UpdateEventMasterRequest withRepeatEndDayOfMonth(Integer repeatEndDayOfMonth) {
        setRepeatEndDayOfMonth(repeatEndDayOfMonth);
        return this;
    }

    /** イベントの繰り返し開始曜日 */
    private String repeatBeginDayOfWeek;

    /**
     * イベントの繰り返し開始曜日を取得
     *
     * @return イベントマスターを更新
     */
    public String getRepeatBeginDayOfWeek() {
        return repeatBeginDayOfWeek;
    }

    /**
     * イベントの繰り返し開始曜日を設定
     *
     * @param repeatBeginDayOfWeek イベントマスターを更新
     */
    public void setRepeatBeginDayOfWeek(String repeatBeginDayOfWeek) {
        this.repeatBeginDayOfWeek = repeatBeginDayOfWeek;
    }

    /**
     * イベントの繰り返し開始曜日を設定
     *
     * @param repeatBeginDayOfWeek イベントマスターを更新
     * @return this
     */
    public UpdateEventMasterRequest withRepeatBeginDayOfWeek(String repeatBeginDayOfWeek) {
        setRepeatBeginDayOfWeek(repeatBeginDayOfWeek);
        return this;
    }

    /** イベントの繰り返し終了曜日 */
    private String repeatEndDayOfWeek;

    /**
     * イベントの繰り返し終了曜日を取得
     *
     * @return イベントマスターを更新
     */
    public String getRepeatEndDayOfWeek() {
        return repeatEndDayOfWeek;
    }

    /**
     * イベントの繰り返し終了曜日を設定
     *
     * @param repeatEndDayOfWeek イベントマスターを更新
     */
    public void setRepeatEndDayOfWeek(String repeatEndDayOfWeek) {
        this.repeatEndDayOfWeek = repeatEndDayOfWeek;
    }

    /**
     * イベントの繰り返し終了曜日を設定
     *
     * @param repeatEndDayOfWeek イベントマスターを更新
     * @return this
     */
    public UpdateEventMasterRequest withRepeatEndDayOfWeek(String repeatEndDayOfWeek) {
        setRepeatEndDayOfWeek(repeatEndDayOfWeek);
        return this;
    }

    /** イベントの繰り返し開始時間 */
    private Integer repeatBeginHour;

    /**
     * イベントの繰り返し開始時間を取得
     *
     * @return イベントマスターを更新
     */
    public Integer getRepeatBeginHour() {
        return repeatBeginHour;
    }

    /**
     * イベントの繰り返し開始時間を設定
     *
     * @param repeatBeginHour イベントマスターを更新
     */
    public void setRepeatBeginHour(Integer repeatBeginHour) {
        this.repeatBeginHour = repeatBeginHour;
    }

    /**
     * イベントの繰り返し開始時間を設定
     *
     * @param repeatBeginHour イベントマスターを更新
     * @return this
     */
    public UpdateEventMasterRequest withRepeatBeginHour(Integer repeatBeginHour) {
        setRepeatBeginHour(repeatBeginHour);
        return this;
    }

    /** イベントの繰り返し終了時間 */
    private Integer repeatEndHour;

    /**
     * イベントの繰り返し終了時間を取得
     *
     * @return イベントマスターを更新
     */
    public Integer getRepeatEndHour() {
        return repeatEndHour;
    }

    /**
     * イベントの繰り返し終了時間を設定
     *
     * @param repeatEndHour イベントマスターを更新
     */
    public void setRepeatEndHour(Integer repeatEndHour) {
        this.repeatEndHour = repeatEndHour;
    }

    /**
     * イベントの繰り返し終了時間を設定
     *
     * @param repeatEndHour イベントマスターを更新
     * @return this
     */
    public UpdateEventMasterRequest withRepeatEndHour(Integer repeatEndHour) {
        setRepeatEndHour(repeatEndHour);
        return this;
    }

    /** イベントの開始トリガー名 */
    private String relativeTriggerName;

    /**
     * イベントの開始トリガー名を取得
     *
     * @return イベントマスターを更新
     */
    public String getRelativeTriggerName() {
        return relativeTriggerName;
    }

    /**
     * イベントの開始トリガー名を設定
     *
     * @param relativeTriggerName イベントマスターを更新
     */
    public void setRelativeTriggerName(String relativeTriggerName) {
        this.relativeTriggerName = relativeTriggerName;
    }

    /**
     * イベントの開始トリガー名を設定
     *
     * @param relativeTriggerName イベントマスターを更新
     * @return this
     */
    public UpdateEventMasterRequest withRelativeTriggerName(String relativeTriggerName) {
        setRelativeTriggerName(relativeTriggerName);
        return this;
    }

    /** イベントの開催期間(秒) */
    private Integer relativeDuration;

    /**
     * イベントの開催期間(秒)を取得
     *
     * @return イベントマスターを更新
     */
    public Integer getRelativeDuration() {
        return relativeDuration;
    }

    /**
     * イベントの開催期間(秒)を設定
     *
     * @param relativeDuration イベントマスターを更新
     */
    public void setRelativeDuration(Integer relativeDuration) {
        this.relativeDuration = relativeDuration;
    }

    /**
     * イベントの開催期間(秒)を設定
     *
     * @param relativeDuration イベントマスターを更新
     * @return this
     */
    public UpdateEventMasterRequest withRelativeDuration(Integer relativeDuration) {
        setRelativeDuration(relativeDuration);
        return this;
    }

}