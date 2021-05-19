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

package io.gs2.version.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * バージョン
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Version implements IModel, Serializable {
	/** メジャーバージョン */
	protected Integer major;

	/**
	 * メジャーバージョンを取得
	 *
	 * @return メジャーバージョン
	 */
	public Integer getMajor() {
		return major;
	}

	/**
	 * メジャーバージョンを設定
	 *
	 * @param major メジャーバージョン
	 */
	public void setMajor(Integer major) {
		this.major = major;
	}

	/**
	 * メジャーバージョンを設定
	 *
	 * @param major メジャーバージョン
	 * @return this
	 */
	public Version withMajor(Integer major) {
		this.major = major;
		return this;
	}
	/** マイナーバージョン */
	protected Integer minor;

	/**
	 * マイナーバージョンを取得
	 *
	 * @return マイナーバージョン
	 */
	public Integer getMinor() {
		return minor;
	}

	/**
	 * マイナーバージョンを設定
	 *
	 * @param minor マイナーバージョン
	 */
	public void setMinor(Integer minor) {
		this.minor = minor;
	}

	/**
	 * マイナーバージョンを設定
	 *
	 * @param minor マイナーバージョン
	 * @return this
	 */
	public Version withMinor(Integer minor) {
		this.minor = minor;
		return this;
	}
	/** マイクロバージョン */
	protected Integer micro;

	/**
	 * マイクロバージョンを取得
	 *
	 * @return マイクロバージョン
	 */
	public Integer getMicro() {
		return micro;
	}

	/**
	 * マイクロバージョンを設定
	 *
	 * @param micro マイクロバージョン
	 */
	public void setMicro(Integer micro) {
		this.micro = micro;
	}

	/**
	 * マイクロバージョンを設定
	 *
	 * @param micro マイクロバージョン
	 * @return this
	 */
	public Version withMicro(Integer micro) {
		this.micro = micro;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("major", this.getMajor())
            .put("minor", this.getMinor())
            .put("micro", this.getMicro());
        return body_;
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.major == null) ? 0 : this.major.hashCode());
        result = prime * result + ((this.minor == null) ? 0 : this.minor.hashCode());
        result = prime * result + ((this.micro == null) ? 0 : this.micro.hashCode());
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
		Version other = (Version) o;
		if (major == null) {
			return other.major == null;
		} else if (!major.equals(other.major)) {
			return false;
		}
		if (minor == null) {
			return other.minor == null;
		} else if (!minor.equals(other.minor)) {
			return false;
		}
		if (micro == null) {
			return other.micro == null;
		} else if (!micro.equals(other.micro)) {
			return false;
		}
		return true;
	}
}