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
 * 利用状況の一覧を取得 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class DescribeBillingsRequest extends Gs2BasicRequest<DescribeBillingsRequest> {

    /** GS2アカウントトークン */
    private String accountToken;

    /**
     * GS2アカウントトークンを取得
     *
     * @return 利用状況の一覧を取得
     */
    public String getAccountToken() {
        return accountToken;
    }

    /**
     * GS2アカウントトークンを設定
     *
     * @param accountToken 利用状況の一覧を取得
     */
    public void setAccountToken(String accountToken) {
        this.accountToken = accountToken;
    }

    /**
     * GS2アカウントトークンを設定
     *
     * @param accountToken 利用状況の一覧を取得
     * @return this
     */
    public DescribeBillingsRequest withAccountToken(String accountToken) {
        setAccountToken(accountToken);
        return this;
    }

    /** プロジェクト名 */
    private String projectName;

    /**
     * プロジェクト名を取得
     *
     * @return 利用状況の一覧を取得
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * プロジェクト名を設定
     *
     * @param projectName 利用状況の一覧を取得
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * プロジェクト名を設定
     *
     * @param projectName 利用状況の一覧を取得
     * @return this
     */
    public DescribeBillingsRequest withProjectName(String projectName) {
        setProjectName(projectName);
        return this;
    }

    /** イベントの発生年 */
    private Integer year;

    /**
     * イベントの発生年を取得
     *
     * @return 利用状況の一覧を取得
     */
    public Integer getYear() {
        return year;
    }

    /**
     * イベントの発生年を設定
     *
     * @param year 利用状況の一覧を取得
     */
    public void setYear(Integer year) {
        this.year = year;
    }

    /**
     * イベントの発生年を設定
     *
     * @param year 利用状況の一覧を取得
     * @return this
     */
    public DescribeBillingsRequest withYear(Integer year) {
        setYear(year);
        return this;
    }

    /** イベントの発生月 */
    private Integer month;

    /**
     * イベントの発生月を取得
     *
     * @return 利用状況の一覧を取得
     */
    public Integer getMonth() {
        return month;
    }

    /**
     * イベントの発生月を設定
     *
     * @param month 利用状況の一覧を取得
     */
    public void setMonth(Integer month) {
        this.month = month;
    }

    /**
     * イベントの発生月を設定
     *
     * @param month 利用状況の一覧を取得
     * @return this
     */
    public DescribeBillingsRequest withMonth(Integer month) {
        setMonth(month);
        return this;
    }

    /** サービスの種類 */
    private String region;

    /**
     * サービスの種類を取得
     *
     * @return 利用状況の一覧を取得
     */
    public String getRegion() {
        return region;
    }

    /**
     * サービスの種類を設定
     *
     * @param region 利用状況の一覧を取得
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * サービスの種類を設定
     *
     * @param region 利用状況の一覧を取得
     * @return this
     */
    public DescribeBillingsRequest withRegion(String region) {
        setRegion(region);
        return this;
    }

    /** サービスの種類 */
    private String service;

    /**
     * サービスの種類を取得
     *
     * @return 利用状況の一覧を取得
     */
    public String getService() {
        return service;
    }

    /**
     * サービスの種類を設定
     *
     * @param service 利用状況の一覧を取得
     */
    public void setService(String service) {
        this.service = service;
    }

    /**
     * サービスの種類を設定
     *
     * @param service 利用状況の一覧を取得
     * @return this
     */
    public DescribeBillingsRequest withService(String service) {
        setService(service);
        return this;
    }

}