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

package io.gs2.gateway.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.gateway.model.*;
import io.gs2.control.Gs2BasicRequest;

/**
 * 通知を送信 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class SendNotificationRequest extends Gs2BasicRequest<SendNotificationRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return 通知を送信
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 通知を送信
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName 通知を送信
     * @return this
     */
    public SendNotificationRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** ユーザーID */
    private String userId;

    /**
     * ユーザーIDを取得
     *
     * @return 通知を送信
     */
    public String getUserId() {
        return userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId 通知を送信
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * ユーザーIDを設定
     *
     * @param userId 通知を送信
     * @return this
     */
    public SendNotificationRequest withUserId(String userId) {
        setUserId(userId);
        return this;
    }

    /** 通知元のサービス */
    private String issuer;

    /**
     * 通知元のサービスを取得
     *
     * @return 通知を送信
     */
    public String getIssuer() {
        return issuer;
    }

    /**
     * 通知元のサービスを設定
     *
     * @param issuer 通知を送信
     */
    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    /**
     * 通知元のサービスを設定
     *
     * @param issuer 通知を送信
     * @return this
     */
    public SendNotificationRequest withIssuer(String issuer) {
        setIssuer(issuer);
        return this;
    }

    /** タイトル */
    private String subject;

    /**
     * タイトルを取得
     *
     * @return 通知を送信
     */
    public String getSubject() {
        return subject;
    }

    /**
     * タイトルを設定
     *
     * @param subject 通知を送信
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * タイトルを設定
     *
     * @param subject 通知を送信
     * @return this
     */
    public SendNotificationRequest withSubject(String subject) {
        setSubject(subject);
        return this;
    }

    /** ペイロード */
    private String payload;

    /**
     * ペイロードを取得
     *
     * @return 通知を送信
     */
    public String getPayload() {
        return payload;
    }

    /**
     * ペイロードを設定
     *
     * @param payload 通知を送信
     */
    public void setPayload(String payload) {
        this.payload = payload;
    }

    /**
     * ペイロードを設定
     *
     * @param payload 通知を送信
     * @return this
     */
    public SendNotificationRequest withPayload(String payload) {
        setPayload(payload);
        return this;
    }

    /** 通知先のユーザがオフラインだったときにモバイルプッシュ通知に転送するか */
    private Boolean enableTransferMobileNotification;

    /**
     * 通知先のユーザがオフラインだったときにモバイルプッシュ通知に転送するかを取得
     *
     * @return 通知を送信
     */
    public Boolean getEnableTransferMobileNotification() {
        return enableTransferMobileNotification;
    }

    /**
     * 通知先のユーザがオフラインだったときにモバイルプッシュ通知に転送するかを設定
     *
     * @param enableTransferMobileNotification 通知を送信
     */
    public void setEnableTransferMobileNotification(Boolean enableTransferMobileNotification) {
        this.enableTransferMobileNotification = enableTransferMobileNotification;
    }

    /**
     * 通知先のユーザがオフラインだったときにモバイルプッシュ通知に転送するかを設定
     *
     * @param enableTransferMobileNotification 通知を送信
     * @return this
     */
    public SendNotificationRequest withEnableTransferMobileNotification(Boolean enableTransferMobileNotification) {
        setEnableTransferMobileNotification(enableTransferMobileNotification);
        return this;
    }

    /** 再生する音声ファイル名 */
    private String sound;

    /**
     * 再生する音声ファイル名を取得
     *
     * @return 通知を送信
     */
    public String getSound() {
        return sound;
    }

    /**
     * 再生する音声ファイル名を設定
     *
     * @param sound 通知を送信
     */
    public void setSound(String sound) {
        this.sound = sound;
    }

    /**
     * 再生する音声ファイル名を設定
     *
     * @param sound 通知を送信
     * @return this
     */
    public SendNotificationRequest withSound(String sound) {
        setSound(sound);
        return this;
    }

    /** 重複実行回避機能に使用するID */
    private String xGs2DuplicationAvoider;

    /**
     * 重複実行回避機能に使用するIDを取得
     *
     * @return 通知を送信
     */
    public String getDuplicationAvoider() {
        return xGs2DuplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider 通知を送信
     */
    public void setDuplicationAvoider(String duplicationAvoider) {
        this.xGs2DuplicationAvoider = duplicationAvoider;
    }

    /**
     * 重複実行回避機能に使用するIDを設定
     *
     * @param duplicationAvoider 通知を送信
     * @return this
     */
    public SendNotificationRequest withDuplicationAvoider(String duplicationAvoider) {
        setDuplicationAvoider(duplicationAvoider);
        return this;
    }

}