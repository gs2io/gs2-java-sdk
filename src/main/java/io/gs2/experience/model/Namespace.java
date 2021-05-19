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

package io.gs2.experience.model;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.gs2.core.model.IModel;

/**
 * ネームスペース
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Namespace implements IModel, Serializable, Comparable<Namespace> {
	/** ネームスペース */
	protected String namespaceId;

	/**
	 * ネームスペースを取得
	 *
	 * @return ネームスペース
	 */
	public String getNamespaceId() {
		return namespaceId;
	}

	/**
	 * ネームスペースを設定
	 *
	 * @param namespaceId ネームスペース
	 */
	public void setNamespaceId(String namespaceId) {
		this.namespaceId = namespaceId;
	}

	/**
	 * ネームスペースを設定
	 *
	 * @param namespaceId ネームスペース
	 * @return this
	 */
	public Namespace withNamespaceId(String namespaceId) {
		this.namespaceId = namespaceId;
		return this;
	}
	/** オーナーID */
	protected String ownerId;

	/**
	 * オーナーIDを取得
	 *
	 * @return オーナーID
	 */
	public String getOwnerId() {
		return ownerId;
	}

	/**
	 * オーナーIDを設定
	 *
	 * @param ownerId オーナーID
	 */
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}

	/**
	 * オーナーIDを設定
	 *
	 * @param ownerId オーナーID
	 * @return this
	 */
	public Namespace withOwnerId(String ownerId) {
		this.ownerId = ownerId;
		return this;
	}
	/** ネームスペース名 */
	protected String name;

	/**
	 * ネームスペース名を取得
	 *
	 * @return ネームスペース名
	 */
	public String getName() {
		return name;
	}

	/**
	 * ネームスペース名を設定
	 *
	 * @param name ネームスペース名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * ネームスペース名を設定
	 *
	 * @param name ネームスペース名
	 * @return this
	 */
	public Namespace withName(String name) {
		this.name = name;
		return this;
	}
	/** ネームスペースの説明 */
	protected String description;

	/**
	 * ネームスペースの説明を取得
	 *
	 * @return ネームスペースの説明
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * ネームスペースの説明を設定
	 *
	 * @param description ネームスペースの説明
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * ネームスペースの説明を設定
	 *
	 * @param description ネームスペースの説明
	 * @return this
	 */
	public Namespace withDescription(String description) {
		this.description = description;
		return this;
	}
	/** ランクキャップ取得時 に実行されるスクリプト のGRN */
	protected String experienceCapScriptId;

	/**
	 * ランクキャップ取得時 に実行されるスクリプト のGRNを取得
	 *
	 * @return ランクキャップ取得時 に実行されるスクリプト のGRN
	 */
	public String getExperienceCapScriptId() {
		return experienceCapScriptId;
	}

	/**
	 * ランクキャップ取得時 に実行されるスクリプト のGRNを設定
	 *
	 * @param experienceCapScriptId ランクキャップ取得時 に実行されるスクリプト のGRN
	 */
	public void setExperienceCapScriptId(String experienceCapScriptId) {
		this.experienceCapScriptId = experienceCapScriptId;
	}

	/**
	 * ランクキャップ取得時 に実行されるスクリプト のGRNを設定
	 *
	 * @param experienceCapScriptId ランクキャップ取得時 に実行されるスクリプト のGRN
	 * @return this
	 */
	public Namespace withExperienceCapScriptId(String experienceCapScriptId) {
		this.experienceCapScriptId = experienceCapScriptId;
		return this;
	}
	/** 経験値変化したときに実行するスクリプト */
	protected ScriptSetting changeExperienceScript;

	/**
	 * 経験値変化したときに実行するスクリプトを取得
	 *
	 * @return 経験値変化したときに実行するスクリプト
	 */
	public ScriptSetting getChangeExperienceScript() {
		return changeExperienceScript;
	}

	/**
	 * 経験値変化したときに実行するスクリプトを設定
	 *
	 * @param changeExperienceScript 経験値変化したときに実行するスクリプト
	 */
	public void setChangeExperienceScript(ScriptSetting changeExperienceScript) {
		this.changeExperienceScript = changeExperienceScript;
	}

	/**
	 * 経験値変化したときに実行するスクリプトを設定
	 *
	 * @param changeExperienceScript 経験値変化したときに実行するスクリプト
	 * @return this
	 */
	public Namespace withChangeExperienceScript(ScriptSetting changeExperienceScript) {
		this.changeExperienceScript = changeExperienceScript;
		return this;
	}
	/** ランク変化したときに実行するスクリプト */
	protected ScriptSetting changeRankScript;

	/**
	 * ランク変化したときに実行するスクリプトを取得
	 *
	 * @return ランク変化したときに実行するスクリプト
	 */
	public ScriptSetting getChangeRankScript() {
		return changeRankScript;
	}

	/**
	 * ランク変化したときに実行するスクリプトを設定
	 *
	 * @param changeRankScript ランク変化したときに実行するスクリプト
	 */
	public void setChangeRankScript(ScriptSetting changeRankScript) {
		this.changeRankScript = changeRankScript;
	}

	/**
	 * ランク変化したときに実行するスクリプトを設定
	 *
	 * @param changeRankScript ランク変化したときに実行するスクリプト
	 * @return this
	 */
	public Namespace withChangeRankScript(ScriptSetting changeRankScript) {
		this.changeRankScript = changeRankScript;
		return this;
	}
	/** ランクキャップ変化したときに実行するスクリプト */
	protected ScriptSetting changeRankCapScript;

	/**
	 * ランクキャップ変化したときに実行するスクリプトを取得
	 *
	 * @return ランクキャップ変化したときに実行するスクリプト
	 */
	public ScriptSetting getChangeRankCapScript() {
		return changeRankCapScript;
	}

	/**
	 * ランクキャップ変化したときに実行するスクリプトを設定
	 *
	 * @param changeRankCapScript ランクキャップ変化したときに実行するスクリプト
	 */
	public void setChangeRankCapScript(ScriptSetting changeRankCapScript) {
		this.changeRankCapScript = changeRankCapScript;
	}

	/**
	 * ランクキャップ変化したときに実行するスクリプトを設定
	 *
	 * @param changeRankCapScript ランクキャップ変化したときに実行するスクリプト
	 * @return this
	 */
	public Namespace withChangeRankCapScript(ScriptSetting changeRankCapScript) {
		this.changeRankCapScript = changeRankCapScript;
		return this;
	}
	/** 経験値あふれしたときに実行するスクリプト */
	protected ScriptSetting overflowExperienceScript;

	/**
	 * 経験値あふれしたときに実行するスクリプトを取得
	 *
	 * @return 経験値あふれしたときに実行するスクリプト
	 */
	public ScriptSetting getOverflowExperienceScript() {
		return overflowExperienceScript;
	}

	/**
	 * 経験値あふれしたときに実行するスクリプトを設定
	 *
	 * @param overflowExperienceScript 経験値あふれしたときに実行するスクリプト
	 */
	public void setOverflowExperienceScript(ScriptSetting overflowExperienceScript) {
		this.overflowExperienceScript = overflowExperienceScript;
	}

	/**
	 * 経験値あふれしたときに実行するスクリプトを設定
	 *
	 * @param overflowExperienceScript 経験値あふれしたときに実行するスクリプト
	 * @return this
	 */
	public Namespace withOverflowExperienceScript(ScriptSetting overflowExperienceScript) {
		this.overflowExperienceScript = overflowExperienceScript;
		return this;
	}
	/** ログの出力設定 */
	protected LogSetting logSetting;

	/**
	 * ログの出力設定を取得
	 *
	 * @return ログの出力設定
	 */
	public LogSetting getLogSetting() {
		return logSetting;
	}

	/**
	 * ログの出力設定を設定
	 *
	 * @param logSetting ログの出力設定
	 */
	public void setLogSetting(LogSetting logSetting) {
		this.logSetting = logSetting;
	}

	/**
	 * ログの出力設定を設定
	 *
	 * @param logSetting ログの出力設定
	 * @return this
	 */
	public Namespace withLogSetting(LogSetting logSetting) {
		this.logSetting = logSetting;
		return this;
	}
	/** 作成日時 */
	protected Long createdAt;

	/**
	 * 作成日時を取得
	 *
	 * @return 作成日時
	 */
	public Long getCreatedAt() {
		return createdAt;
	}

	/**
	 * 作成日時を設定
	 *
	 * @param createdAt 作成日時
	 */
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * 作成日時を設定
	 *
	 * @param createdAt 作成日時
	 * @return this
	 */
	public Namespace withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	/** 最終更新日時 */
	protected Long updatedAt;

	/**
	 * 最終更新日時を取得
	 *
	 * @return 最終更新日時
	 */
	public Long getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * 最終更新日時を設定
	 *
	 * @param updatedAt 最終更新日時
	 */
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}

	/**
	 * 最終更新日時を設定
	 *
	 * @param updatedAt 最終更新日時
	 * @return this
	 */
	public Namespace withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}

    public ObjectNode toJson() {
        JsonNode changeExperienceScript = this.getChangeExperienceScript().toJson();
        JsonNode changeRankScript = this.getChangeRankScript().toJson();
        JsonNode changeRankCapScript = this.getChangeRankCapScript().toJson();
        JsonNode overflowExperienceScript = this.getOverflowExperienceScript().toJson();
        JsonNode logSetting = this.getLogSetting().toJson();
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("namespaceId", this.getNamespaceId())
            .put("ownerId", this.getOwnerId())
            .put("name", this.getName())
            .put("description", this.getDescription())
            .put("experienceCapScriptId", this.getExperienceCapScriptId())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        body_.set("changeExperienceScript", changeExperienceScript);
        body_.set("changeRankScript", changeRankScript);
        body_.set("changeRankCapScript", changeRankCapScript);
        body_.set("overflowExperienceScript", overflowExperienceScript);
        body_.set("logSetting", logSetting);
        return body_;
    }
	@Override
	public int compareTo(Namespace o) {
		return namespaceId.compareTo(o.namespaceId);
	}

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.namespaceId == null) ? 0 : this.namespaceId.hashCode());
        result = prime * result + ((this.ownerId == null) ? 0 : this.ownerId.hashCode());
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.experienceCapScriptId == null) ? 0 : this.experienceCapScriptId.hashCode());
        result = prime * result + ((this.changeExperienceScript == null) ? 0 : this.changeExperienceScript.hashCode());
        result = prime * result + ((this.changeRankScript == null) ? 0 : this.changeRankScript.hashCode());
        result = prime * result + ((this.changeRankCapScript == null) ? 0 : this.changeRankCapScript.hashCode());
        result = prime * result + ((this.overflowExperienceScript == null) ? 0 : this.overflowExperienceScript.hashCode());
        result = prime * result + ((this.logSetting == null) ? 0 : this.logSetting.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
        result = prime * result + ((this.updatedAt == null) ? 0 : this.updatedAt.hashCode());
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
		Namespace other = (Namespace) o;
		if (namespaceId == null) {
			return other.namespaceId == null;
		} else if (!namespaceId.equals(other.namespaceId)) {
			return false;
		}
		if (ownerId == null) {
			return other.ownerId == null;
		} else if (!ownerId.equals(other.ownerId)) {
			return false;
		}
		if (name == null) {
			return other.name == null;
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (description == null) {
			return other.description == null;
		} else if (!description.equals(other.description)) {
			return false;
		}
		if (experienceCapScriptId == null) {
			return other.experienceCapScriptId == null;
		} else if (!experienceCapScriptId.equals(other.experienceCapScriptId)) {
			return false;
		}
		if (changeExperienceScript == null) {
			return other.changeExperienceScript == null;
		} else if (!changeExperienceScript.equals(other.changeExperienceScript)) {
			return false;
		}
		if (changeRankScript == null) {
			return other.changeRankScript == null;
		} else if (!changeRankScript.equals(other.changeRankScript)) {
			return false;
		}
		if (changeRankCapScript == null) {
			return other.changeRankCapScript == null;
		} else if (!changeRankCapScript.equals(other.changeRankCapScript)) {
			return false;
		}
		if (overflowExperienceScript == null) {
			return other.overflowExperienceScript == null;
		} else if (!overflowExperienceScript.equals(other.overflowExperienceScript)) {
			return false;
		}
		if (logSetting == null) {
			return other.logSetting == null;
		} else if (!logSetting.equals(other.logSetting)) {
			return false;
		}
		if (createdAt == null) {
			return other.createdAt == null;
		} else if (!createdAt.equals(other.createdAt)) {
			return false;
		}
		if (updatedAt == null) {
			return other.updatedAt == null;
		} else if (!updatedAt.equals(other.updatedAt)) {
			return false;
		}
		return true;
	}
}