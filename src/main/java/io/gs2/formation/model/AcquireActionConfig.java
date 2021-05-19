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

package io.gs2.formation.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * 入手アクションコンフィグ
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class AcquireActionConfig implements IModel, Serializable {
	/** スロット名 */
	protected String name;

	/**
	 * スロット名を取得
	 *
	 * @return スロット名
	 */
	public String getName() {
		return name;
	}

	/**
	 * スロット名を設定
	 *
	 * @param name スロット名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * スロット名を設定
	 *
	 * @param name スロット名
	 * @return this
	 */
	public AcquireActionConfig withName(String name) {
		this.name = name;
		return this;
	}
	/** スタンプシートに使用するコンフィグ */
	protected List<Config> config;

	/**
	 * スタンプシートに使用するコンフィグを取得
	 *
	 * @return スタンプシートに使用するコンフィグ
	 */
	public List<Config> getConfig() {
		return config;
	}

	/**
	 * スタンプシートに使用するコンフィグを設定
	 *
	 * @param config スタンプシートに使用するコンフィグ
	 */
	public void setConfig(List<Config> config) {
		this.config = config;
	}

	/**
	 * スタンプシートに使用するコンフィグを設定
	 *
	 * @param config スタンプシートに使用するコンフィグ
	 * @return this
	 */
	public AcquireActionConfig withConfig(List<Config> config) {
		this.config = config;
		return this;
	}

    public ObjectNode toJson() {
        List<JsonNode> config = new ArrayList<>();
        if(this.config != null) {
            for(Config item : this.config) {
                config.add(item.toJson());
            }
        }
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("name", this.getName());
        body_.set("config", JsonNodeFactory.instance.arrayNode().addAll(config));
        return body_;
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.config == null) ? 0 : this.config.hashCode());
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
		AcquireActionConfig other = (AcquireActionConfig) o;
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (config == null) {
			return other.config == null;
		} else if (!config.equals(other.config)) {
			return false;
		}
		return true;
	}
}