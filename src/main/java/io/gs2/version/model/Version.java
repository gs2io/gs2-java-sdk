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

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.IModel;


@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Version implements IModel, Serializable {
	private Integer major;
	private Integer minor;
	private Integer micro;
	public Integer getMajor() {
		return major;
	}
	public void setMajor(Integer major) {
		this.major = major;
	}
	public Version withMajor(Integer major) {
		this.major = major;
		return this;
	}
	public Integer getMinor() {
		return minor;
	}
	public void setMinor(Integer minor) {
		this.minor = minor;
	}
	public Version withMinor(Integer minor) {
		this.minor = minor;
		return this;
	}
	public Integer getMicro() {
		return micro;
	}
	public void setMicro(Integer micro) {
		this.micro = micro;
	}
	public Version withMicro(Integer micro) {
		this.micro = micro;
		return this;
	}

    public static Version fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Version()
            .withMajor(data.get("major") == null || data.get("major").isNull() ? null : data.get("major").intValue())
            .withMinor(data.get("minor") == null || data.get("minor").isNull() ? null : data.get("minor").intValue())
            .withMicro(data.get("micro") == null || data.get("micro").isNull() ? null : data.get("micro").intValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("major", getMajor());
                put("minor", getMinor());
                put("micro", getMicro());
            }}
        );
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