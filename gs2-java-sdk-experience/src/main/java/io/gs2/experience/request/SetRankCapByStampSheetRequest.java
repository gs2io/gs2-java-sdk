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

package io.gs2.experience.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.experience.model.*;
import io.gs2.control.Gs2BasicRequest;

/**
 * ランクキャップを更新 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class SetRankCapByStampSheetRequest extends Gs2BasicRequest<SetRankCapByStampSheetRequest> {

    /** スタンプシート */
    private String stampSheet;

    /**
     * スタンプシートを取得
     *
     * @return ランクキャップを更新
     */
    public String getStampSheet() {
        return stampSheet;
    }

    /**
     * スタンプシートを設定
     *
     * @param stampSheet ランクキャップを更新
     */
    public void setStampSheet(String stampSheet) {
        this.stampSheet = stampSheet;
    }

    /**
     * スタンプシートを設定
     *
     * @param stampSheet ランクキャップを更新
     * @return this
     */
    public SetRankCapByStampSheetRequest withStampSheet(String stampSheet) {
        setStampSheet(stampSheet);
        return this;
    }

    /** スタンプシートの署名検証に使用する 暗号鍵 のGRN */
    private String keyId;

    /**
     * スタンプシートの署名検証に使用する 暗号鍵 のGRNを取得
     *
     * @return ランクキャップを更新
     */
    public String getKeyId() {
        return keyId;
    }

    /**
     * スタンプシートの署名検証に使用する 暗号鍵 のGRNを設定
     *
     * @param keyId ランクキャップを更新
     */
    public void setKeyId(String keyId) {
        this.keyId = keyId;
    }

    /**
     * スタンプシートの署名検証に使用する 暗号鍵 のGRNを設定
     *
     * @param keyId ランクキャップを更新
     * @return this
     */
    public SetRankCapByStampSheetRequest withKeyId(String keyId) {
        setKeyId(keyId);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return ランクキャップを更新
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ランクキャップを更新
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ランクキャップを更新
     * @return this
     */
    public SetRankCapByStampSheetRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

}