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

package io.gs2.gacha.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * 景品
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Prize implements Serializable {

	/** ガチャプール名 */
	private String gachaPoolName;

	/** ガチャ名 */
	private String gachaName;

	/** 景品リスト */
	private List<String> resourceIds;


	/**
	 * ガチャプール名を取得
	 *
	 * @return ガチャプール名
	 */
	public String getGachaPoolName() {
		return gachaPoolName;
	}

	/**
	 * ガチャプール名を設定
	 *
	 * @param gachaPoolName ガチャプール名
	 */
	public void setGachaPoolName(String gachaPoolName) {
		this.gachaPoolName = gachaPoolName;
	}

	/**
	 * ガチャプール名を設定
	 *
	 * @param gachaPoolName ガチャプール名
	 * @return this
	 */
	public Prize withGachaPoolName(String gachaPoolName) {
		this.gachaPoolName = gachaPoolName;
		return this;
	}

	/**
	 * ガチャ名を取得
	 *
	 * @return ガチャ名
	 */
	public String getGachaName() {
		return gachaName;
	}

	/**
	 * ガチャ名を設定
	 *
	 * @param gachaName ガチャ名
	 */
	public void setGachaName(String gachaName) {
		this.gachaName = gachaName;
	}

	/**
	 * ガチャ名を設定
	 *
	 * @param gachaName ガチャ名
	 * @return this
	 */
	public Prize withGachaName(String gachaName) {
		this.gachaName = gachaName;
		return this;
	}

	/**
	 * 景品リストを取得
	 *
	 * @return 景品リスト
	 */
	public List<String> getResourceIds() {
		return resourceIds;
	}

	/**
	 * 景品リストを設定
	 *
	 * @param resourceIds 景品リスト
	 */
	public void setResourceIds(List<String> resourceIds) {
		this.resourceIds = resourceIds;
	}

	/**
	 * 景品リストを設定
	 *
	 * @param resourceIds 景品リスト
	 * @return this
	 */
	public Prize withResourceIds(List<String> resourceIds) {
		this.resourceIds = resourceIds;
		return this;
	}


    public ObjectNode toJson() {

        List<JsonNode> resourceIdsNode = new ArrayList<>();
        if(this.resourceIds != null) {
            for(String item : this.resourceIds) {
                resourceIdsNode.add(JsonNodeFactory.instance.textNode(item));
            }
        }
		ObjectNode body = JsonNodeFactory.instance.objectNode()

            .put("gachaPoolName", this.getGachaPoolName())
            .put("gachaName", this.getGachaName());

        body.set("resourceIds", JsonNodeFactory.instance.arrayNode().addAll(resourceIdsNode));
        return body;
    }
}