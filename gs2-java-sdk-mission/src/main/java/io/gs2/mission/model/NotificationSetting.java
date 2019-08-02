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

package io.gs2.mission.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.model.IModel;

/**
 * プッシュ通知設定
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class NotificationSetting implements IModel, Serializable {
	/** プッシュ通知に使用する GS2-Gateway のネームスペース のGRN */
	protected String gatewayNamespaceId;

	/**
	 * プッシュ通知に使用する GS2-Gateway のネームスペース のGRNを取得
	 *
	 * @return プッシュ通知に使用する GS2-Gateway のネームスペース のGRN
	 */
	public String getGatewayNamespaceId() {
		return gatewayNamespaceId;
	}

	/**
	 * プッシュ通知に使用する GS2-Gateway のネームスペース のGRNを設定
	 *
	 * @param gatewayNamespaceId プッシュ通知に使用する GS2-Gateway のネームスペース のGRN
	 */
	public void setGatewayNamespaceId(String gatewayNamespaceId) {
		this.gatewayNamespaceId = gatewayNamespaceId;
	}

	/**
	 * プッシュ通知に使用する GS2-Gateway のネームスペース のGRNを設定
	 *
	 * @param gatewayNamespaceId プッシュ通知に使用する GS2-Gateway のネームスペース のGRN
	 * @return this
	 */
	public NotificationSetting withGatewayNamespaceId(String gatewayNamespaceId) {
		this.gatewayNamespaceId = gatewayNamespaceId;
		return this;
	}
	/** モバイルプッシュ通知へ転送するか */
	protected Boolean enableTransferMobileNotification;

	/**
	 * モバイルプッシュ通知へ転送するかを取得
	 *
	 * @return モバイルプッシュ通知へ転送するか
	 */
	public Boolean getEnableTransferMobileNotification() {
		return enableTransferMobileNotification;
	}

	/**
	 * モバイルプッシュ通知へ転送するかを設定
	 *
	 * @param enableTransferMobileNotification モバイルプッシュ通知へ転送するか
	 */
	public void setEnableTransferMobileNotification(Boolean enableTransferMobileNotification) {
		this.enableTransferMobileNotification = enableTransferMobileNotification;
	}

	/**
	 * モバイルプッシュ通知へ転送するかを設定
	 *
	 * @param enableTransferMobileNotification モバイルプッシュ通知へ転送するか
	 * @return this
	 */
	public NotificationSetting withEnableTransferMobileNotification(Boolean enableTransferMobileNotification) {
		this.enableTransferMobileNotification = enableTransferMobileNotification;
		return this;
	}
	/** モバイルプッシュ通知で使用するサウンドファイル名 */
	protected String sound;

	/**
	 * モバイルプッシュ通知で使用するサウンドファイル名を取得
	 *
	 * @return モバイルプッシュ通知で使用するサウンドファイル名
	 */
	public String getSound() {
		return sound;
	}

	/**
	 * モバイルプッシュ通知で使用するサウンドファイル名を設定
	 *
	 * @param sound モバイルプッシュ通知で使用するサウンドファイル名
	 */
	public void setSound(String sound) {
		this.sound = sound;
	}

	/**
	 * モバイルプッシュ通知で使用するサウンドファイル名を設定
	 *
	 * @param sound モバイルプッシュ通知で使用するサウンドファイル名
	 * @return this
	 */
	public NotificationSetting withSound(String sound) {
		this.sound = sound;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("gatewayNamespaceId", this.getGatewayNamespaceId())
            .put("enableTransferMobileNotification", this.getEnableTransferMobileNotification())
            .put("sound", this.getSound());
        return body_;
    }
}