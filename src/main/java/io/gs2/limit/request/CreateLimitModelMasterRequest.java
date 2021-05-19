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

package io.gs2.limit.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.limit.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * 回数制限の種類マスターを新規作成 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateLimitModelMasterRequest extends Gs2BasicRequest<CreateLimitModelMasterRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return 回数制限の種類マスターを新規作成
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 回数制限の種類マスターを新規作成
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 回数制限の種類マスターを新規作成
     * @return this
     */
    public CreateLimitModelMasterRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** 回数制限の種類名 */
    private String name;

    /**
     * 回数制限の種類名を取得
     *
     * @return 回数制限の種類マスターを新規作成
     */
    public String getName() {
        return name;
    }

    /**
     * 回数制限の種類名を設定
     *
     * @param name 回数制限の種類マスターを新規作成
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 回数制限の種類名を設定
     *
     * @param name 回数制限の種類マスターを新規作成
     * @return this
     */
    public CreateLimitModelMasterRequest withName(String name) {
        setName(name);
        return this;
    }

    /** 回数制限の種類マスターの説明 */
    private String description;

    /**
     * 回数制限の種類マスターの説明を取得
     *
     * @return 回数制限の種類マスターを新規作成
     */
    public String getDescription() {
        return description;
    }

    /**
     * 回数制限の種類マスターの説明を設定
     *
     * @param description 回数制限の種類マスターを新規作成
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 回数制限の種類マスターの説明を設定
     *
     * @param description 回数制限の種類マスターを新規作成
     * @return this
     */
    public CreateLimitModelMasterRequest withDescription(String description) {
        setDescription(description);
        return this;
    }

    /** 回数制限の種類のメタデータ */
    private String metadata;

    /**
     * 回数制限の種類のメタデータを取得
     *
     * @return 回数制限の種類マスターを新規作成
     */
    public String getMetadata() {
        return metadata;
    }

    /**
     * 回数制限の種類のメタデータを設定
     *
     * @param metadata 回数制限の種類マスターを新規作成
     */
    public void setMetadata(String metadata) {
        this.metadata = metadata;
    }

    /**
     * 回数制限の種類のメタデータを設定
     *
     * @param metadata 回数制限の種類マスターを新規作成
     * @return this
     */
    public CreateLimitModelMasterRequest withMetadata(String metadata) {
        setMetadata(metadata);
        return this;
    }

    /** リセットタイミング */
    private String resetType;

    /**
     * リセットタイミングを取得
     *
     * @return 回数制限の種類マスターを新規作成
     */
    public String getResetType() {
        return resetType;
    }

    /**
     * リセットタイミングを設定
     *
     * @param resetType 回数制限の種類マスターを新規作成
     */
    public void setResetType(String resetType) {
        this.resetType = resetType;
    }

    /**
     * リセットタイミングを設定
     *
     * @param resetType 回数制限の種類マスターを新規作成
     * @return this
     */
    public CreateLimitModelMasterRequest withResetType(String resetType) {
        setResetType(resetType);
        return this;
    }

    /** リセットをする日にち */
    private Integer resetDayOfMonth;

    /**
     * リセットをする日にちを取得
     *
     * @return 回数制限の種類マスターを新規作成
     */
    public Integer getResetDayOfMonth() {
        return resetDayOfMonth;
    }

    /**
     * リセットをする日にちを設定
     *
     * @param resetDayOfMonth 回数制限の種類マスターを新規作成
     */
    public void setResetDayOfMonth(Integer resetDayOfMonth) {
        this.resetDayOfMonth = resetDayOfMonth;
    }

    /**
     * リセットをする日にちを設定
     *
     * @param resetDayOfMonth 回数制限の種類マスターを新規作成
     * @return this
     */
    public CreateLimitModelMasterRequest withResetDayOfMonth(Integer resetDayOfMonth) {
        setResetDayOfMonth(resetDayOfMonth);
        return this;
    }

    /** リセットする曜日 */
    private String resetDayOfWeek;

    /**
     * リセットする曜日を取得
     *
     * @return 回数制限の種類マスターを新規作成
     */
    public String getResetDayOfWeek() {
        return resetDayOfWeek;
    }

    /**
     * リセットする曜日を設定
     *
     * @param resetDayOfWeek 回数制限の種類マスターを新規作成
     */
    public void setResetDayOfWeek(String resetDayOfWeek) {
        this.resetDayOfWeek = resetDayOfWeek;
    }

    /**
     * リセットする曜日を設定
     *
     * @param resetDayOfWeek 回数制限の種類マスターを新規作成
     * @return this
     */
    public CreateLimitModelMasterRequest withResetDayOfWeek(String resetDayOfWeek) {
        setResetDayOfWeek(resetDayOfWeek);
        return this;
    }

    /** リセット時刻 */
    private Integer resetHour;

    /**
     * リセット時刻を取得
     *
     * @return 回数制限の種類マスターを新規作成
     */
    public Integer getResetHour() {
        return resetHour;
    }

    /**
     * リセット時刻を設定
     *
     * @param resetHour 回数制限の種類マスターを新規作成
     */
    public void setResetHour(Integer resetHour) {
        this.resetHour = resetHour;
    }

    /**
     * リセット時刻を設定
     *
     * @param resetHour 回数制限の種類マスターを新規作成
     * @return this
     */
    public CreateLimitModelMasterRequest withResetHour(Integer resetHour) {
        setResetHour(resetHour);
        return this;
    }

}