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

package io.gs2.formation.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.formation.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * ユーザIDを指定してフォームを削除 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class DeleteFormByUserIdRequest extends Gs2BasicRequest<DeleteFormByUserIdRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return ユーザIDを指定してフォームを削除
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ユーザIDを指定してフォームを削除
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ユーザIDを指定してフォームを削除
     * @return this
     */
    public DeleteFormByUserIdRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** ユーザーID */
    private String userId;

    /**
     * ユーザーIDを取得
     *
     * @return ユーザIDを指定してフォームを削除
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId ユーザIDを指定してフォームを削除
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId ユーザIDを指定してフォームを削除
     * @return this
     */
    public DeleteFormByUserIdRequest withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    /** フォームの保存領域の名前 */
    private String moldName;

    /**
     * フォームの保存領域の名前を取得
     *
     * @return ユーザIDを指定してフォームを削除
     */
    public String getMoldName() {
        return moldName;
    }

    /**
     * フォームの保存領域の名前を設定
     *
     * @param moldName ユーザIDを指定してフォームを削除
     */
    public void setMoldName(String moldName) {
        this.moldName = moldName;
    }

    /**
     * フォームの保存領域の名前を設定
     *
     * @param moldName ユーザIDを指定してフォームを削除
     * @return this
     */
    public DeleteFormByUserIdRequest withMoldName(String moldName) {
        setMoldName(moldName);
        return this;
    }

    /** 保存領域のインデックス */
    private Integer index;

    /**
     * 保存領域のインデックスを取得
     *
     * @return ユーザIDを指定してフォームを削除
     */
    public Integer getIndex() {
        return index;
    }

    /**
     * 保存領域のインデックスを設定
     *
     * @param index ユーザIDを指定してフォームを削除
     */
    public void setIndex(Integer index) {
        this.index = index;
    }

    /**
     * 保存領域のインデックスを設定
     *
     * @param index ユーザIDを指定してフォームを削除
     * @return this
     */
    public DeleteFormByUserIdRequest withIndex(Integer index) {
        setIndex(index);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return ユーザIDを指定してフォームを削除
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ユーザIDを指定してフォームを削除
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider ユーザIDを指定してフォームを削除
     * @return this
     */
    public DeleteFormByUserIdRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

}