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

package io.gs2.distributor.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.distributor.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * 所持品を配布する(溢れた際の救済処置無し) のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class DistributeWithoutOverflowProcessRequest extends Gs2BasicRequest<DistributeWithoutOverflowProcessRequest> {

    /** 加算するリソース */
    private DistributeResource distributeResource;

    /**
     * 加算するリソースを取得
     *
     * @return 所持品を配布する(溢れた際の救済処置無し)
     */
    public DistributeResource getDistributeResource() {
        return distributeResource;
    }

    /**
     * 加算するリソースを設定
     *
     * @param distributeResource 所持品を配布する(溢れた際の救済処置無し)
     */
    public void setDistributeResource(DistributeResource distributeResource) {
        this.distributeResource = distributeResource;
    }

    /**
     * 加算するリソースを設定
     *
     * @param distributeResource 所持品を配布する(溢れた際の救済処置無し)
     * @return this
     */
    public DistributeWithoutOverflowProcessRequest withDistributeResource(DistributeResource distributeResource) {
        setDistributeResource(distributeResource);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return 所持品を配布する(溢れた際の救済処置無し)
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider 所持品を配布する(溢れた際の救済処置無し)
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider 所持品を配布する(溢れた際の救済処置無し)
     * @return this
     */
    public DistributeWithoutOverflowProcessRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

    /** アクセストークン */
    private String accessToken;

    /**
     * アクセストークンを取得
     *
     * @return アクセストークン
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * アクセストークンを設定
     *
     * @param accessToken アクセストークン
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * アクセストークンを設定
     *
     * @param accessToken アクセストークン
     * @return this
     */
    public DistributeWithoutOverflowProcessRequest withAccessToken(String accessToken) {
        setAccessToken(accessToken);
        return this;
    }

}