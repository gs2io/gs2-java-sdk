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
	/** ゲームプレイヤーによるルームの作成を許可するか */
	protected Boolean allowCreateRoom;

	/**
	 * ゲームプレイヤーによるルームの作成を許可するかを取得
	 *
	 * @return ゲームプレイヤーによるルームの作成を許可するか
	 */
	public Boolean getAllowCreateRoom() {
		return allowCreateRoom;
	}

	/**
	 * ゲームプレイヤーによるルームの作成を許可するかを設定
	 *
	 * @param allowCreateRoom ゲームプレイヤーによるルームの作成を許可するか
	 */
	public void setAllowCreateRoom(Boolean allowCreateRoom) {
		this.allowCreateRoom = allowCreateRoom;
	}

	/**
	 * ゲームプレイヤーによるルームの作成を許可するかを設定
	 *
	 * @param allowCreateRoom ゲームプレイヤーによるルームの作成を許可するか
	 * @return this
	 */
	public Namespace withAllowCreateRoom(Boolean allowCreateRoom) {
		this.allowCreateRoom = allowCreateRoom;
		return this;
	}
	/** メッセージを投稿したときに実行するスクリプト */
	protected ScriptSetting postMessageScript;

	/**
	 * メッセージを投稿したときに実行するスクリプトを取得
	 *
	 * @return メッセージを投稿したときに実行するスクリプト
	 */
	public ScriptSetting getPostMessageScript() {
		return postMessageScript;
	}

	/**
	 * メッセージを投稿したときに実行するスクリプトを設定
	 *
	 * @param postMessageScript メッセージを投稿したときに実行するスクリプト
	 */
	public void setPostMessageScript(ScriptSetting postMessageScript) {
		this.postMessageScript = postMessageScript;
	}

	/**
	 * メッセージを投稿したときに実行するスクリプトを設定
	 *
	 * @param postMessageScript メッセージを投稿したときに実行するスクリプト
	 * @return this
	 */
	public Namespace withPostMessageScript(ScriptSetting postMessageScript) {
		this.postMessageScript = postMessageScript;
		return this;
	}
	/** ルームを作成したときに実行するスクリプト */
	protected ScriptSetting createRoomScript;

	/**
	 * ルームを作成したときに実行するスクリプトを取得
	 *
	 * @return ルームを作成したときに実行するスクリプト
	 */
	public ScriptSetting getCreateRoomScript() {
		return createRoomScript;
	}

	/**
	 * ルームを作成したときに実行するスクリプトを設定
	 *
	 * @param createRoomScript ルームを作成したときに実行するスクリプト
	 */
	public void setCreateRoomScript(ScriptSetting createRoomScript) {
		this.createRoomScript = createRoomScript;
	}

	/**
	 * ルームを作成したときに実行するスクリプトを設定
	 *
	 * @param createRoomScript ルームを作成したときに実行するスクリプト
	 * @return this
	 */
	public Namespace withCreateRoomScript(ScriptSetting createRoomScript) {
		this.createRoomScript = createRoomScript;
		return this;
	}
	/** ルームを削除したときに実行するスクリプト */
	protected ScriptSetting deleteRoomScript;

	/**
	 * ルームを削除したときに実行するスクリプトを取得
	 *
	 * @return ルームを削除したときに実行するスクリプト
	 */
	public ScriptSetting getDeleteRoomScript() {
		return deleteRoomScript;
	}

	/**
	 * ルームを削除したときに実行するスクリプトを設定
	 *
	 * @param deleteRoomScript ルームを削除したときに実行するスクリプト
	 */
	public void setDeleteRoomScript(ScriptSetting deleteRoomScript) {
		this.deleteRoomScript = deleteRoomScript;
	}

	/**
	 * ルームを削除したときに実行するスクリプトを設定
	 *
	 * @param deleteRoomScript ルームを削除したときに実行するスクリプト
	 * @return this
	 */
	public Namespace withDeleteRoomScript(ScriptSetting deleteRoomScript) {
		this.deleteRoomScript = deleteRoomScript;
		return this;
	}
	/** ルームを購読したときに実行するスクリプト */
	protected ScriptSetting subscribeRoomScript;

	/**
	 * ルームを購読したときに実行するスクリプトを取得
	 *
	 * @return ルームを購読したときに実行するスクリプト
	 */
	public ScriptSetting getSubscribeRoomScript() {
		return subscribeRoomScript;
	}

	/**
	 * ルームを購読したときに実行するスクリプトを設定
	 *
	 * @param subscribeRoomScript ルームを購読したときに実行するスクリプト
	 */
	public void setSubscribeRoomScript(ScriptSetting subscribeRoomScript) {
		this.subscribeRoomScript = subscribeRoomScript;
	}

	/**
	 * ルームを購読したときに実行するスクリプトを設定
	 *
	 * @param subscribeRoomScript ルームを購読したときに実行するスクリプト
	 * @return this
	 */
	public Namespace withSubscribeRoomScript(ScriptSetting subscribeRoomScript) {
		this.subscribeRoomScript = subscribeRoomScript;
		return this;
	}
	/** ルームの購読を解除したときに実行するスクリプト */
	protected ScriptSetting unsubscribeRoomScript;

	/**
	 * ルームの購読を解除したときに実行するスクリプトを取得
	 *
	 * @return ルームの購読を解除したときに実行するスクリプト
	 */
	public ScriptSetting getUnsubscribeRoomScript() {
		return unsubscribeRoomScript;
	}

	/**
	 * ルームの購読を解除したときに実行するスクリプトを設定
	 *
	 * @param unsubscribeRoomScript ルームの購読を解除したときに実行するスクリプト
	 */
	public void setUnsubscribeRoomScript(ScriptSetting unsubscribeRoomScript) {
		this.unsubscribeRoomScript = unsubscribeRoomScript;
	}

	/**
	 * ルームの購読を解除したときに実行するスクリプトを設定
	 *
	 * @param unsubscribeRoomScript ルームの購読を解除したときに実行するスクリプト
	 * @return this
	 */
	public Namespace withUnsubscribeRoomScript(ScriptSetting unsubscribeRoomScript) {
		this.unsubscribeRoomScript = unsubscribeRoomScript;
		return this;
	}
	/** 購読しているルームに新しい投稿がきたときのプッシュ通知 */
	protected NotificationSetting postNotification;

	/**
	 * 購読しているルームに新しい投稿がきたときのプッシュ通知を取得
	 *
	 * @return 購読しているルームに新しい投稿がきたときのプッシュ通知
	 */
	public NotificationSetting getPostNotification() {
		return postNotification;
	}

	/**
	 * 購読しているルームに新しい投稿がきたときのプッシュ通知を設定
	 *
	 * @param postNotification 購読しているルームに新しい投稿がきたときのプッシュ通知
	 */
	public void setPostNotification(NotificationSetting postNotification) {
		this.postNotification = postNotification;
	}

	/**
	 * 購読しているルームに新しい投稿がきたときのプッシュ通知を設定
	 *
	 * @param postNotification 購読しているルームに新しい投稿がきたときのプッシュ通知
	 * @return this
	 */
	public Namespace withPostNotification(NotificationSetting postNotification) {
		this.postNotification = postNotification;
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
	/** None */
	protected String status;

	/**
	 * Noneを取得
	 *
	 * @return None
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Noneを設定
	 *
	 * @param status None
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Noneを設定
	 *
	 * @param status None
	 * @return this
	 */
	public Namespace withStatus(String status) {
		this.status = status;
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
        JsonNode postMessageScript = this.getPostMessageScript().toJson();
        JsonNode createRoomScript = this.getCreateRoomScript().toJson();
        JsonNode deleteRoomScript = this.getDeleteRoomScript().toJson();
        JsonNode subscribeRoomScript = this.getSubscribeRoomScript().toJson();
        JsonNode unsubscribeRoomScript = this.getUnsubscribeRoomScript().toJson();
        JsonNode postNotification = this.getPostNotification().toJson();
        JsonNode logSetting = this.getLogSetting().toJson();
		ObjectNode body_ = JsonNodeFactory.instance.objectNode()
            .put("namespaceId", this.getNamespaceId())
            .put("ownerId", this.getOwnerId())
            .put("name", this.getName())
            .put("description", this.getDescription())
            .put("allowCreateRoom", this.getAllowCreateRoom())
            .put("status", this.getStatus())
            .put("createdAt", this.getCreatedAt())
            .put("updatedAt", this.getUpdatedAt());
        body_.set("postMessageScript", postMessageScript);
        body_.set("createRoomScript", createRoomScript);
        body_.set("deleteRoomScript", deleteRoomScript);
        body_.set("subscribeRoomScript", subscribeRoomScript);
        body_.set("unsubscribeRoomScript", unsubscribeRoomScript);
        body_.set("postNotification", postNotification);
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
        result = prime * result + ((this.allowCreateRoom == null) ? 0 : this.allowCreateRoom.hashCode());
        result = prime * result + ((this.postMessageScript == null) ? 0 : this.postMessageScript.hashCode());
        result = prime * result + ((this.createRoomScript == null) ? 0 : this.createRoomScript.hashCode());
        result = prime * result + ((this.deleteRoomScript == null) ? 0 : this.deleteRoomScript.hashCode());
        result = prime * result + ((this.subscribeRoomScript == null) ? 0 : this.subscribeRoomScript.hashCode());
        result = prime * result + ((this.unsubscribeRoomScript == null) ? 0 : this.unsubscribeRoomScript.hashCode());
        result = prime * result + ((this.postNotification == null) ? 0 : this.postNotification.hashCode());
        result = prime * result + ((this.logSetting == null) ? 0 : this.logSetting.hashCode());
        result = prime * result + ((this.status == null) ? 0 : this.status.hashCode());
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
		if (allowCreateRoom == null) {
			return other.allowCreateRoom == null;
		} else if (!allowCreateRoom.equals(other.allowCreateRoom)) {
			return false;
		}
		if (postMessageScript == null) {
			return other.postMessageScript == null;
		} else if (!postMessageScript.equals(other.postMessageScript)) {
			return false;
		}
		if (createRoomScript == null) {
			return other.createRoomScript == null;
		} else if (!createRoomScript.equals(other.createRoomScript)) {
			return false;
		}
		if (deleteRoomScript == null) {
			return other.deleteRoomScript == null;
		} else if (!deleteRoomScript.equals(other.deleteRoomScript)) {
			return false;
		}
		if (subscribeRoomScript == null) {
			return other.subscribeRoomScript == null;
		} else if (!subscribeRoomScript.equals(other.subscribeRoomScript)) {
			return false;
		}
		if (unsubscribeRoomScript == null) {
			return other.unsubscribeRoomScript == null;
		} else if (!unsubscribeRoomScript.equals(other.unsubscribeRoomScript)) {
			return false;
		}
		if (postNotification == null) {
			return other.postNotification == null;
		} else if (!postNotification.equals(other.postNotification)) {
			return false;
		}
		if (logSetting == null) {
			return other.logSetting == null;
		} else if (!logSetting.equals(other.logSetting)) {
			return false;
		}
		if (status == null) {
			return other.status == null;
		} else if (!status.equals(other.status)) {
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