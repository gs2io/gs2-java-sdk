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

    /** 繰り返しの種類 */
    private String repeatType;

    /**
     * 繰り返しの種類を取得
     *
     * @return イベントマスターを新規作成
     */
    public String getRepeatType() {
        return repeatType;
    }

    /**
     * 繰り返しの種類を設定
     *
     * @param repeatType イベントマスターを新規作成
     */
    public void setRepeatType(String repeatType) {
        this.repeatType = repeatType;
    }

    /**
     * 繰り返しの種類を設定
     *
     * @param repeatType イベントマスターを新規作成
     * @return this
     */
    public CreateEventMasterRequest withRepeatType(String repeatType) {
        setRepeatType(repeatType);
        return this;
    }

    /** イベントの繰り返し開始日 */
    private Integer repeatBeginDayOfMonth;

    /**
     * イベントの繰り返し開始日を取得
     *
     * @return イベントマスターを新規作成
     */
    public Integer getRepeatBeginDayOfMonth() {
        return repeatBeginDayOfMonth;
    }

    /**
     * イベントの繰り返し開始日を設定
     *
     * @param repeatBeginDayOfMonth イベントマスターを新規作成
     */
    public void setRepeatBeginDayOfMonth(Integer repeatBeginDayOfMonth) {
        this.repeatBeginDayOfMonth = repeatBeginDayOfMonth;
    }

    /**
     * イベントの繰り返し開始日を設定
     *
     * @param repeatBeginDayOfMonth イベントマスターを新規作成
     * @return this
     */
    public CreateEventMasterRequest withRepeatBeginDayOfMonth(Integer repeatBeginDayOfMonth) {
        setRepeatBeginDayOfMonth(repeatBeginDayOfMonth);
        return this;
    }

    /** イベントの繰り返し終了日 */
    private Integer repeatEndDayOfMonth;

    /**
     * イベントの繰り返し終了日を取得
     *
     * @return イベントマスターを新規作成
     */
    public Integer getRepeatEndDayOfMonth() {
        return repeatEndDayOfMonth;
    }

    /**
     * イベントの繰り返し終了日を設定
     *
     * @param repeatEndDayOfMonth イベントマスターを新規作成
     */
    public void setRepeatEndDayOfMonth(Integer repeatEndDayOfMonth) {
        this.repeatEndDayOfMonth = repeatEndDayOfMonth;
    }

    /**
     * イベントの繰り返し終了日を設定
     *
     * @param repeatEndDayOfMonth イベントマスターを新規作成
     * @return this
     */
    public CreateEventMasterRequest withRepeatEndDayOfMonth(Integer repeatEndDayOfMonth) {
        setRepeatEndDayOfMonth(repeatEndDayOfMonth);
        return this;
    }

    /** イベントの繰り返し開始曜日 */
    private String repeatBeginDayOfWeek;

    /**
     * イベントの繰り返し開始曜日を取得
     *
     * @return イベントマスターを新規作成
     */
    public String getRepeatBeginDayOfWeek() {
        return repeatBeginDayOfWeek;
    }

    /**
     * イベントの繰り返し開始曜日を設定
     *
     * @param repeatBeginDayOfWeek イベントマスターを新規作成
     */
    public void setRepeatBeginDayOfWeek(String repeatBeginDayOfWeek) {
        this.repeatBeginDayOfWeek = repeatBeginDayOfWeek;
    }

    /**
     * イベントの繰り返し開始曜日を設定
     *
     * @param repeatBeginDayOfWeek イベントマスターを新規作成
     * @return this
     */
    public CreateEventMasterRequest withRepeatBeginDayOfWeek(String repeatBeginDayOfWeek) {
        setRepeatBeginDayOfWeek(repeatBeginDayOfWeek);
        return this;
    }

    /** イベントの繰り返し終了曜日 */
    private String repeatEndDayOfWeek;

    /**
     * イベントの繰り返し終了曜日を取得
     *
     * @return イベントマスターを新規作成
     */
    public String getRepeatEndDayOfWeek() {
        return repeatEndDayOfWeek;
    }

    /**
     * イベントの繰り返し終了曜日を設定
     *
     * @param repeatEndDayOfWeek イベントマスターを新規作成
     */
    public void setRepeatEndDayOfWeek(String repeatEndDayOfWeek) {
        this.repeatEndDayOfWeek = repeatEndDayOfWeek;
    }

    /**
     * イベントの繰り返し終了曜日を設定
     *
     * @param repeatEndDayOfWeek イベントマスターを新規作成
     * @return this
     */
    public CreateEventMasterRequest withRepeatEndDayOfWeek(String repeatEndDayOfWeek) {
        setRepeatEndDayOfWeek(repeatEndDayOfWeek);
        return this;
    }

    /** イベントの繰り返し開始時間 */
    private Integer repeatBeginHour;

    /**
     * イベントの繰り返し開始時間を取得
     *
     * @return イベントマスターを新規作成
     */
    public Integer getRepeatBeginHour() {
        return repeatBeginHour;
    }

    /**
     * イベントの繰り返し開始時間を設定
     *
     * @param repeatBeginHour イベントマスターを新規作成
     */
    public void setRepeatBeginHour(Integer repeatBeginHour) {
        this.repeatBeginHour = repeatBeginHour;
    }

    /**
     * イベントの繰り返し開始時間を設定
     *
     * @param repeatBeginHour イベントマスターを新規作成
     * @return this
     */
    public CreateEventMasterRequest withRepeatBeginHour(Integer repeatBeginHour) {
        setRepeatBeginHour(repeatBeginHour);
        return this;
    }

    /** イベントの繰り返し終了時間 */
    private Integer repeatEndHour;

    /**
     * イベントの繰り返し終了時間を取得
     *
     * @return イベントマスターを新規作成
     */
    public Integer getRepeatEndHour() {
        return repeatEndHour;
    }

    /**
     * イベントの繰り返し終了時間を設定
     *
     * @param repeatEndHour イベントマスターを新規作成
     */
    public void setRepeatEndHour(Integer repeatEndHour) {
        this.repeatEndHour = repeatEndHour;
    }

    /**
     * イベントの繰り返し終了時間を設定
     *
     * @param repeatEndHour イベントマスターを新規作成
     * @return this
     */
    public CreateEventMasterRequest withRepeatEndHour(Integer repeatEndHour) {
        setRepeatEndHour(repeatEndHour);
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