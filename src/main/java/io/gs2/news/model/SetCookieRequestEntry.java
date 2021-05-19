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

package io.gs2.news.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * ニュース記事
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class SetCookieRequestEntry implements IModel, Serializable {
	/** 記事を閲覧できるようにするために設定してほしい Cookie のキー値 */
	protected String key;

	/**
	 * 記事を閲覧できるようにするために設定してほしい Cookie のキー値を取得
	 *
	 * @return 記事を閲覧できるようにするために設定してほしい Cookie のキー値
	 */
	public String getKey() {
		return key;
	}

	/**
	 * 記事を閲覧できるようにするために設定してほしい Cookie のキー値を設定
	 *
	 * @param key 記事を閲覧できるようにするために設定してほしい Cookie のキー値
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * 記事を閲覧できるようにするために設定してほしい Cookie のキー値を設定
	 *
	 * @param key 記事を閲覧できるようにするために設定してほしい Cookie のキー値
	 * @return this
	 */
	public SetCookieRequestEntry withKey(String key) {
		this.key = key;
		return this;
	}
	/** 記事を閲覧できるようにするために設定してほしい Cookie の値 */
	protected String value;

	/**
	 * 記事を閲覧できるようにするために設定してほしい Cookie の値を取得
	 *
	 * @return 記事を閲覧できるようにするために設定してほしい Cookie の値
	 */
	public String getValue() {
		return value;
	}

	/**
	 * 記事を閲覧できるようにするために設定してほしい Cookie の値を設定
	 *
	 * @param value 記事を閲覧できるようにするために設定してほしい Cookie の値
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * 記事を閲覧できるようにするために設定してほしい Cookie の値を設定
	 *
	 * @param value 記事を閲覧できるようにするために設定してほしい Cookie の値
	 * @return this
	 */
	public SetCookieRequestEntry withValue(String value) {
		this.value = value;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("key", this.getKey())
            .put("value", this.getValue());
        return body_;
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.key == null) ? 0 : this.key.hashCode());
        result = prime * result + ((this.value == null) ? 0 : this.value.hashCode());
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
		SetCookieRequestEntry other = (SetCookieRequestEntry) o;
		if (key == null) {
			return other.key == null;
		} else if (!key.equals(other.key)) {
			return false;
		}
		if (value == null) {
			return other.value == null;
		} else if (!value.equals(other.value)) {
			return false;
		}
		return true;
	}
}