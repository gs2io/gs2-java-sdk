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

package io.gs2.watch.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.watch.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * 請求にまつわるアクティビティを取得 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class GetBillingActivityRequest extends Gs2BasicRequest<GetBillingActivityRequest> {

    /** イベントの発生年 */
    private Integer year;

    /**
     * イベントの発生年を取得
     *
     * @return 請求にまつわるアクティビティを取得
     */
    public Integer getYear() {
        return year;
    }

    /**
     * イベントの発生年を設定
     *
     * @param year 請求にまつわるアクティビティを取得
     */
    public void setYear(Integer year) {
        this.year = year;
    }

    /**
     * イベントの発生年を設定
     *
     * @param year 請求にまつわるアクティビティを取得
     * @return this
     */
    public GetBillingActivityRequest withYear(Integer year) {
        setYear(year);
        return this;
    }

    /** イベントの発生月 */
    private Integer month;

    /**
     * イベントの発生月を取得
     *
     * @return 請求にまつわるアクティビティを取得
     */
    public Integer getMonth() {
        return month;
    }

    /**
     * イベントの発生月を設定
     *
     * @param month 請求にまつわるアクティビティを取得
     */
    public void setMonth(Integer month) {
        this.month = month;
    }

    /**
     * イベントの発生月を設定
     *
     * @param month 請求にまつわるアクティビティを取得
     * @return this
     */
    public GetBillingActivityRequest withMonth(Integer month) {
        setMonth(month);
        return this;
    }

    /** サービスの種類 */
    private String service;

    /**
     * サービスの種類を取得
     *
     * @return 請求にまつわるアクティビティを取得
     */
    public String getService() {
        return service;
    }

    /**
     * サービスの種類を設定
     *
     * @param service 請求にまつわるアクティビティを取得
     */
    public void setService(String service) {
        this.service = service;
    }

    /**
     * サービスの種類を設定
     *
     * @param service 請求にまつわるアクティビティを取得
     * @return this
     */
    public GetBillingActivityRequest withService(String service) {
        setService(service);
        return this;
    }

    /** イベントの種類 */
    private String activityType;

    /**
     * イベントの種類を取得
     *
     * @return 請求にまつわるアクティビティを取得
     */
    public String getActivityType() {
        return activityType;
    }

    /**
     * イベントの種類を設定
     *
     * @param activityType 請求にまつわるアクティビティを取得
     */
    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    /**
     * イベントの種類を設定
     *
     * @param activityType 請求にまつわるアクティビティを取得
     * @return this
     */
    public GetBillingActivityRequest withActivityType(String activityType) {
        setActivityType(activityType);
        return this;
    }

}