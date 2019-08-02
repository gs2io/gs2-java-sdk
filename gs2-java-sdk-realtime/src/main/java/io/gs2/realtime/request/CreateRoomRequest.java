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

package io.gs2.realtime.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.realtime.model.*;
import io.gs2.control.Gs2BasicRequest;

/**
 * ルームを作成。 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class CreateRoomRequest extends Gs2BasicRequest<CreateRoomRequest> {

    /** ネームスペース名 */
    private String namespaceName;

    /**
     * ネームスペース名を取得
     *
     * @return ルームを作成。
     */
    public String getNamespaceName() {
        return namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ルームを作成。
     */
    public void setNamespaceName(String namespaceName) {
        this.namespaceName = namespaceName;
    }

    /**
     * ネームスペース名を設定
     *
     * @param namespaceName ルームを作成。
     * @return this
     */
    public CreateRoomRequest withNamespaceName(String namespaceName) {
        setNamespaceName(namespaceName);
        return this;
    }

    /** ルーム名 */
    private String name;

    /**
     * ルーム名を取得
     *
     * @return ルームを作成。
     */
    public String getName() {
        return name;
    }

    /**
     * ルーム名を設定
     *
     * @param name ルームを作成。
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * ルーム名を設定
     *
     * @param name ルームを作成。
     * @return this
     */
    public CreateRoomRequest withName(String name) {
        setName(name);
        return this;
    }

    /** IPアドレス */
    private String ipAddress;

    /**
     * IPアドレスを取得
     *
     * @return ルームを作成。
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * IPアドレスを設定
     *
     * @param ipAddress ルームを作成。
     */
    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     * IPアドレスを設定
     *
     * @param ipAddress ルームを作成。
     * @return this
     */
    public CreateRoomRequest withIpAddress(String ipAddress) {
        setIpAddress(ipAddress);
        return this;
    }

    /** 待受ポート */
    private Integer port;

    /**
     * 待受ポートを取得
     *
     * @return ルームを作成。
     */
    public Integer getPort() {
        return port;
    }

    /**
     * 待受ポートを設定
     *
     * @param port ルームを作成。
     */
    public void setPort(Integer port) {
        this.port = port;
    }

    /**
     * 待受ポートを設定
     *
     * @param port ルームを作成。
     * @return this
     */
    public CreateRoomRequest withPort(Integer port) {
        setPort(port);
        return this;
    }

    /** 暗号鍵 */
    private String encryptionKey;

    /**
     * 暗号鍵を取得
     *
     * @return ルームを作成。
     */
    public String getEncryptionKey() {
        return encryptionKey;
    }

    /**
     * 暗号鍵を設定
     *
     * @param encryptionKey ルームを作成。
     */
    public void setEncryptionKey(String encryptionKey) {
        this.encryptionKey = encryptionKey;
    }

    /**
     * 暗号鍵を設定
     *
     * @param encryptionKey ルームを作成。
     * @return this
     */
    public CreateRoomRequest withEncryptionKey(String encryptionKey) {
        setEncryptionKey(encryptionKey);
        return this;
    }

}