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

package io.gs2.lottery.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.model.IModel;

/**
 * 抽選の種類
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class LotteryModel implements IModel, Serializable, Comparable<LotteryModel> {
	/** 抽選の種類マスター */
	protected String lotteryModelId;

	/**
	 * 抽選の種類マスターを取得
	 *
	 * @return 抽選の種類マスター
	 */
	public String getLotteryModelId() {
		return lotteryModelId;
	}

	/**
	 * 抽選の種類マスターを設定
	 *
	 * @param lotteryModelId 抽選の種類マスター
	 */
	public void setLotteryModelId(String lotteryModelId) {
		this.lotteryModelId = lotteryModelId;
	}

	/**
	 * 抽選の種類マスターを設定
	 *
	 * @param lotteryModelId 抽選の種類マスター
	 * @return this
	 */
	public LotteryModel withLotteryModelId(String lotteryModelId) {
		this.lotteryModelId = lotteryModelId;
		return this;
	}
	/** 抽選モデルの種類名 */
	protected String name;

	/**
	 * 抽選モデルの種類名を取得
	 *
	 * @return 抽選モデルの種類名
	 */
	public String getName() {
		return name;
	}

	/**
	 * 抽選モデルの種類名を設定
	 *
	 * @param name 抽選モデルの種類名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 抽選モデルの種類名を設定
	 *
	 * @param name 抽選モデルの種類名
	 * @return this
	 */
	public LotteryModel withName(String name) {
		this.name = name;
		return this;
	}
	/** 抽選モデルの種類のメタデータ */
	protected String metadata;

	/**
	 * 抽選モデルの種類のメタデータを取得
	 *
	 * @return 抽選モデルの種類のメタデータ
	 */
	public String getMetadata() {
		return metadata;
	}

	/**
	 * 抽選モデルの種類のメタデータを設定
	 *
	 * @param metadata 抽選モデルの種類のメタデータ
	 */
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}

	/**
	 * 抽選モデルの種類のメタデータを設定
	 *
	 * @param metadata 抽選モデルの種類のメタデータ
	 * @return this
	 */
	public LotteryModel withMetadata(String metadata) {
		this.metadata = metadata;
		return this;
	}
	/** 抽選モード */
	protected String mode;

	/**
	 * 抽選モードを取得
	 *
	 * @return 抽選モード
	 */
	public String getMode() {
		return mode;
	}

	/**
	 * 抽選モードを設定
	 *
	 * @param mode 抽選モード
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}

	/**
	 * 抽選モードを設定
	 *
	 * @param mode 抽選モード
	 * @return this
	 */
	public LotteryModel withMode(String mode) {
		this.mode = mode;
		return this;
	}
	/** 抽選回数 */
	protected Integer count;

	/**
	 * 抽選回数を取得
	 *
	 * @return 抽選回数
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * 抽選回数を設定
	 *
	 * @param count 抽選回数
	 */
	public void setCount(Integer count) {
		this.count = count;
	}

	/**
	 * 抽選回数を設定
	 *
	 * @param count 抽選回数
	 * @return this
	 */
	public LotteryModel withCount(Integer count) {
		this.count = count;
		return this;
	}
	/** 抽選方法 */
	protected String method;

	/**
	 * 抽選方法を取得
	 *
	 * @return 抽選方法
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * 抽選方法を設定
	 *
	 * @param method 抽選方法
	 */
	public void setMethod(String method) {
		this.method = method;
	}

	/**
	 * 抽選方法を設定
	 *
	 * @param method 抽選方法
	 * @return this
	 */
	public LotteryModel withMethod(String method) {
		this.method = method;
		return this;
	}
	/** 景品テーブルの名前 */
	protected String prizeTableName;

	/**
	 * 景品テーブルの名前を取得
	 *
	 * @return 景品テーブルの名前
	 */
	public String getPrizeTableName() {
		return prizeTableName;
	}

	/**
	 * 景品テーブルの名前を設定
	 *
	 * @param prizeTableName 景品テーブルの名前
	 */
	public void setPrizeTableName(String prizeTableName) {
		this.prizeTableName = prizeTableName;
	}

	/**
	 * 景品テーブルの名前を設定
	 *
	 * @param prizeTableName 景品テーブルの名前
	 * @return this
	 */
	public LotteryModel withPrizeTableName(String prizeTableName) {
		this.prizeTableName = prizeTableName;
		return this;
	}
	/** 抽選テーブルを確定するスクリプト のGRN */
	protected String choicePrizeTableScriptId;

	/**
	 * 抽選テーブルを確定するスクリプト のGRNを取得
	 *
	 * @return 抽選テーブルを確定するスクリプト のGRN
	 */
	public String getChoicePrizeTableScriptId() {
		return choicePrizeTableScriptId;
	}

	/**
	 * 抽選テーブルを確定するスクリプト のGRNを設定
	 *
	 * @param choicePrizeTableScriptId 抽選テーブルを確定するスクリプト のGRN
	 */
	public void setChoicePrizeTableScriptId(String choicePrizeTableScriptId) {
		this.choicePrizeTableScriptId = choicePrizeTableScriptId;
	}

	/**
	 * 抽選テーブルを確定するスクリプト のGRNを設定
	 *
	 * @param choicePrizeTableScriptId 抽選テーブルを確定するスクリプト のGRN
	 * @return this
	 */
	public LotteryModel withChoicePrizeTableScriptId(String choicePrizeTableScriptId) {
		this.choicePrizeTableScriptId = choicePrizeTableScriptId;
		return this;
	}

    public ObjectNode toJson() {
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("lotteryModelId", this.getLotteryModelId())
            .put("name", this.getName())
            .put("metadata", this.getMetadata())
            .put("mode", this.getMode())
            .put("count", this.getCount())
            .put("method", this.getMethod())
            .put("prizeTableName", this.getPrizeTableName())
            .put("choicePrizeTableScriptId", this.getChoicePrizeTableScriptId());
        return body_;
    }
	@Override
	public int compareTo(LotteryModel o) {
		return lotteryModelId.compareTo(o.lotteryModelId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.lotteryModelId == null) ? 0 : this.lotteryModelId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.metadata == null) ? 0 : this.metadata.hashCode());
        result = prime * result + ((this.mode == null) ? 0 : this.mode.hashCode());
        result = prime * result + ((this.count == null) ? 0 : this.count.hashCode());
        result = prime * result + ((this.method == null) ? 0 : this.method.hashCode());
        result = prime * result + ((this.prizeTableName == null) ? 0 : this.prizeTableName.hashCode());
        result = prime * result + ((this.choicePrizeTableScriptId == null) ? 0 : this.choicePrizeTableScriptId.hashCode());
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
		LotteryModel other = (LotteryModel) o;
		if (lotteryModelId == null) {
			return other.lotteryModelId == null;
		} else if (!lotteryModelId.equals(other.lotteryModelId)) {
			return false;
		}
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (metadata == null) {
			return other.metadata == null;
		} else if (!metadata.equals(other.metadata)) {
			return false;
		}
		if (mode == null) {
			return other.mode == null;
		} else if (!mode.equals(other.mode)) {
			return false;
		}
		if (count == null) {
			return other.count == null;
		} else if (!count.equals(other.count)) {
			return false;
		}
		if (method == null) {
			return other.method == null;
		} else if (!method.equals(other.method)) {
			return false;
		}
		if (prizeTableName == null) {
			return other.prizeTableName == null;
		} else if (!prizeTableName.equals(other.prizeTableName)) {
			return false;
		}
		if (choicePrizeTableScriptId == null) {
			return other.choicePrizeTableScriptId == null;
		} else if (!choicePrizeTableScriptId.equals(other.choicePrizeTableScriptId)) {
			return false;
		}
		return true;
	}
}