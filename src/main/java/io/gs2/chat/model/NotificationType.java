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

package io.gs2.chat.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * 購読
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class NotificationType implements IModel, Serializable {
	/** 新着メッセージ通知を受け取るカテゴリ */
	protected Integer category;

	/**
	 * 新着メッセージ通知を受け取るカテゴリを取得
	 *
	 * @return 新着メッセージ通知を受け取るカテゴリ
	 */
	public Integer getCategory() {
		return category;
	}

	/**
	 * 新着メッセージ通知を受け取るカテゴリを設定
	 *
	 * @param category 新着メッセージ通知を受け取るカテゴリ
	 */
	public void setCategory(Integer category) {
		this.category = category;
	}

	/**
	 * 新着メッセージ通知を受け取るカテゴリを設定
	 *
	 * @param category 新着メッセージ通知を受け取るカテゴリ
	 * @return this
	 */
	public NotificationType withCategory(Integer category) {
		this.category = category;
		return this;
	}
	/** オフラインだった時にモバイルプッシュ通知に転送するか */
	protected Boolean enableTransferMobilePushNotification;

	/**
	 * オフラインだった時にモバイルプッシュ通知に転送するかを取得
	 *
	 * @return オフラインだった時にモバイルプッシュ通知に転送するか
	 */
	public Boolean getEnableTransferMobilePushNotification() {
		return enableTransferMobilePushNotification;
	}

	/**
	 * オフラインだった時にモバイルプッシュ通知に転送するかを設定
	 *
	 * @param enableTransferMobilePushNotification オフラインだった時にモバイルプッシュ通知に転送するか
	 */
	public void setEnableTransferMobilePushNotification(Boolean enableTransferMobilePushNotification) {
		this.enableTransferMobilePushNotification = enableTransferMobilePushNotification;
	}

	/**
	 * オフラインだった時にモバイルプッシュ通知に転送するかを設定
	 *
	 * @param enableTransferMobilePushNotification オフラインだった時にモバイルプッシュ通知に転送するか
	 * @return this
	 */
	public NotificationType withEnableTransferMobilePushNotification(Boolean enableTransferMobilePushNotification) {
		this.enableTransferMobilePushNotification = enableTransferMobilePushNotification;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("category", this.getCategory())
            .put("enableTransferMobilePushNotification", this.getEnableTransferMobilePushNotification());
        return body_;
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.category == null) ? 0 : this.category.hashCode());
        result = prime * result + ((this.enableTransferMobilePushNotification == null) ? 0 : this.enableTransferMobilePushNotification.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		if (getClass() != o.getClass())
			return false;
		NotificationType other = (NotificationType) o;
		if (category == null) {
			return other.category == null;
		} else if (!category.equals(other.category)) {
			return false;
		}
		if (enableTransferMobilePushNotification == null) {
			return other.enableTransferMobilePushNotification == null;
		} else if (!enableTransferMobilePushNotification.equals(other.enableTransferMobilePushNotification)) {
			return false;
		}
		return true;
	}
}