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
 * ガチャ
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class GachaMaster implements Serializable {

	/** ガチャGRN */
	private String gachaId;

	/** ガチャ名 */
	private String name;

	/** メタデータ */
	private String meta;

	/** 排出確率名リスト */
	private List<String> prizeTableNames;

	/** 景品の排出処理に使用する GS2-JobQueue */
	private String prizeJobQueueName;

	/** 景品の排出処理に使用する GS2-JobQueue に指定する GS2-Script */
	private String prizeJobQueueScriptName;

	/** 作成日時(エポック秒) */
	private Integer createAt;

	/** 最終更新日時(エポック秒) */
	private Integer updateAt;


	/**
	 * ガチャGRNを取得
	 *
	 * @return ガチャGRN
	 */
	public String getGachaId() {
		return gachaId;
	}

	/**
	 * ガチャGRNを設定
	 *
	 * @param gachaId ガチャGRN
	 */
	public void setGachaId(String gachaId) {
		this.gachaId = gachaId;
	}

	/**
	 * ガチャGRNを設定
	 *
	 * @param gachaId ガチャGRN
	 * @return this
	 */
	public GachaMaster withGachaId(String gachaId) {
		this.gachaId = gachaId;
		return this;
	}

	/**
	 * ガチャ名を取得
	 *
	 * @return ガチャ名
	 */
	public String getName() {
		return name;
	}

	/**
	 * ガチャ名を設定
	 *
	 * @param name ガチャ名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * ガチャ名を設定
	 *
	 * @param name ガチャ名
	 * @return this
	 */
	public GachaMaster withName(String name) {
		this.name = name;
		return this;
	}

	/**
	 * メタデータを取得
	 *
	 * @return メタデータ
	 */
	public String getMeta() {
		return meta;
	}

	/**
	 * メタデータを設定
	 *
	 * @param meta メタデータ
	 */
	public void setMeta(String meta) {
		this.meta = meta;
	}

	/**
	 * メタデータを設定
	 *
	 * @param meta メタデータ
	 * @return this
	 */
	public GachaMaster withMeta(String meta) {
		this.meta = meta;
		return this;
	}

	/**
	 * 排出確率名リストを取得
	 *
	 * @return 排出確率名リスト
	 */
	public List<String> getPrizeTableNames() {
		return prizeTableNames;
	}

	/**
	 * 排出確率名リストを設定
	 *
	 * @param prizeTableNames 排出確率名リスト
	 */
	public void setPrizeTableNames(List<String> prizeTableNames) {
		this.prizeTableNames = prizeTableNames;
	}

	/**
	 * 排出確率名リストを設定
	 *
	 * @param prizeTableNames 排出確率名リスト
	 * @return this
	 */
	public GachaMaster withPrizeTableNames(List<String> prizeTableNames) {
		this.prizeTableNames = prizeTableNames;
		return this;
	}

	/**
	 * 景品の排出処理に使用する GS2-JobQueueを取得
	 *
	 * @return 景品の排出処理に使用する GS2-JobQueue
	 */
	public String getPrizeJobQueueName() {
		return prizeJobQueueName;
	}

	/**
	 * 景品の排出処理に使用する GS2-JobQueueを設定
	 *
	 * @param prizeJobQueueName 景品の排出処理に使用する GS2-JobQueue
	 */
	public void setPrizeJobQueueName(String prizeJobQueueName) {
		this.prizeJobQueueName = prizeJobQueueName;
	}

	/**
	 * 景品の排出処理に使用する GS2-JobQueueを設定
	 *
	 * @param prizeJobQueueName 景品の排出処理に使用する GS2-JobQueue
	 * @return this
	 */
	public GachaMaster withPrizeJobQueueName(String prizeJobQueueName) {
		this.prizeJobQueueName = prizeJobQueueName;
		return this;
	}

	/**
	 * 景品の排出処理に使用する GS2-JobQueue に指定する GS2-Scriptを取得
	 *
	 * @return 景品の排出処理に使用する GS2-JobQueue に指定する GS2-Script
	 */
	public String getPrizeJobQueueScriptName() {
		return prizeJobQueueScriptName;
	}

	/**
	 * 景品の排出処理に使用する GS2-JobQueue に指定する GS2-Scriptを設定
	 *
	 * @param prizeJobQueueScriptName 景品の排出処理に使用する GS2-JobQueue に指定する GS2-Script
	 */
	public void setPrizeJobQueueScriptName(String prizeJobQueueScriptName) {
		this.prizeJobQueueScriptName = prizeJobQueueScriptName;
	}

	/**
	 * 景品の排出処理に使用する GS2-JobQueue に指定する GS2-Scriptを設定
	 *
	 * @param prizeJobQueueScriptName 景品の排出処理に使用する GS2-JobQueue に指定する GS2-Script
	 * @return this
	 */
	public GachaMaster withPrizeJobQueueScriptName(String prizeJobQueueScriptName) {
		this.prizeJobQueueScriptName = prizeJobQueueScriptName;
		return this;
	}

	/**
	 * 作成日時(エポック秒)を取得
	 *
	 * @return 作成日時(エポック秒)
	 */
	public Integer getCreateAt() {
		return createAt;
	}

	/**
	 * 作成日時(エポック秒)を設定
	 *
	 * @param createAt 作成日時(エポック秒)
	 */
	public void setCreateAt(Integer createAt) {
		this.createAt = createAt;
	}

	/**
	 * 作成日時(エポック秒)を設定
	 *
	 * @param createAt 作成日時(エポック秒)
	 * @return this
	 */
	public GachaMaster withCreateAt(Integer createAt) {
		this.createAt = createAt;
		return this;
	}

	/**
	 * 最終更新日時(エポック秒)を取得
	 *
	 * @return 最終更新日時(エポック秒)
	 */
	public Integer getUpdateAt() {
		return updateAt;
	}

	/**
	 * 最終更新日時(エポック秒)を設定
	 *
	 * @param updateAt 最終更新日時(エポック秒)
	 */
	public void setUpdateAt(Integer updateAt) {
		this.updateAt = updateAt;
	}

	/**
	 * 最終更新日時(エポック秒)を設定
	 *
	 * @param updateAt 最終更新日時(エポック秒)
	 * @return this
	 */
	public GachaMaster withUpdateAt(Integer updateAt) {
		this.updateAt = updateAt;
		return this;
	}


    public ObjectNode toJson() {

        List<JsonNode> prizeTableNamesNode = new ArrayList<>();
        if(this.prizeTableNames != null) {
            for(String item : this.prizeTableNames) {
                prizeTableNamesNode.add(JsonNodeFactory.instance.textNode(item));
            }
        }
		ObjectNode body = JsonNodeFactory.instance.objectNode()

            .put("gachaId", this.getGachaId())
            .put("name", this.getName())
            .put("meta", this.getMeta())
            .put("prizeJobQueueName", this.getPrizeJobQueueName())
            .put("prizeJobQueueScriptName", this.getPrizeJobQueueScriptName())
            .put("createAt", this.getCreateAt())
            .put("updateAt", this.getUpdateAt());

        body.set("prizeTableNames", JsonNodeFactory.instance.arrayNode().addAll(prizeTableNamesNode));
        return body;
    }
}