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

package io.gs2.adReward.model;

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
public class Namespace implements IModel, Serializable, Comparable<Namespace> {
	private String namespaceId;
	private String name;
	private String description;
	private AdMob admob;
	private UnityAd unityAd;
	private List<AppLovinMax> appLovinMaxes;
	private ScriptSetting acquirePointScript;
	private ScriptSetting consumePointScript;
	private NotificationSetting changePointNotification;
	private LogSetting logSetting;
	private Long createdAt;
	private Long updatedAt;
	private Long revision;
	public String getNamespaceId() {
		return namespaceId;
	}
	public void setNamespaceId(String namespaceId) {
		this.namespaceId = namespaceId;
	}
	public Namespace withNamespaceId(String namespaceId) {
		this.namespaceId = namespaceId;
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Namespace withName(String name) {
		this.name = name;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Namespace withDescription(String description) {
		this.description = description;
		return this;
	}
	public AdMob getAdmob() {
		return admob;
	}
	public void setAdmob(AdMob admob) {
		this.admob = admob;
	}
	public Namespace withAdmob(AdMob admob) {
		this.admob = admob;
		return this;
	}
	public UnityAd getUnityAd() {
		return unityAd;
	}
	public void setUnityAd(UnityAd unityAd) {
		this.unityAd = unityAd;
	}
	public Namespace withUnityAd(UnityAd unityAd) {
		this.unityAd = unityAd;
		return this;
	}
	public List<AppLovinMax> getAppLovinMaxes() {
		return appLovinMaxes;
	}
	public void setAppLovinMaxes(List<AppLovinMax> appLovinMaxes) {
		this.appLovinMaxes = appLovinMaxes;
	}
	public Namespace withAppLovinMaxes(List<AppLovinMax> appLovinMaxes) {
		this.appLovinMaxes = appLovinMaxes;
		return this;
	}
	public ScriptSetting getAcquirePointScript() {
		return acquirePointScript;
	}
	public void setAcquirePointScript(ScriptSetting acquirePointScript) {
		this.acquirePointScript = acquirePointScript;
	}
	public Namespace withAcquirePointScript(ScriptSetting acquirePointScript) {
		this.acquirePointScript = acquirePointScript;
		return this;
	}
	public ScriptSetting getConsumePointScript() {
		return consumePointScript;
	}
	public void setConsumePointScript(ScriptSetting consumePointScript) {
		this.consumePointScript = consumePointScript;
	}
	public Namespace withConsumePointScript(ScriptSetting consumePointScript) {
		this.consumePointScript = consumePointScript;
		return this;
	}
	public NotificationSetting getChangePointNotification() {
		return changePointNotification;
	}
	public void setChangePointNotification(NotificationSetting changePointNotification) {
		this.changePointNotification = changePointNotification;
	}
	public Namespace withChangePointNotification(NotificationSetting changePointNotification) {
		this.changePointNotification = changePointNotification;
		return this;
	}
	public LogSetting getLogSetting() {
		return logSetting;
	}
	public void setLogSetting(LogSetting logSetting) {
		this.logSetting = logSetting;
	}
	public Namespace withLogSetting(LogSetting logSetting) {
		this.logSetting = logSetting;
		return this;
	}
	public Long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}
	public Namespace withCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
		return this;
	}
	public Long getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}
	public Namespace withUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
		return this;
	}
	public Long getRevision() {
		return revision;
	}
	public void setRevision(Long revision) {
		this.revision = revision;
	}
	public Namespace withRevision(Long revision) {
		this.revision = revision;
		return this;
	}

    public static Namespace fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new Namespace()
            .withNamespaceId(data.get("namespaceId") == null || data.get("namespaceId").isNull() ? null : data.get("namespaceId").asText())
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withAdmob(data.get("admob") == null || data.get("admob").isNull() ? null : AdMob.fromJson(data.get("admob")))
            .withUnityAd(data.get("unityAd") == null || data.get("unityAd").isNull() ? null : UnityAd.fromJson(data.get("unityAd")))
            .withAppLovinMaxes(data.get("appLovinMaxes") == null || data.get("appLovinMaxes").isNull() ? null :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("appLovinMaxes").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return AppLovinMax.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withAcquirePointScript(data.get("acquirePointScript") == null || data.get("acquirePointScript").isNull() ? null : ScriptSetting.fromJson(data.get("acquirePointScript")))
            .withConsumePointScript(data.get("consumePointScript") == null || data.get("consumePointScript").isNull() ? null : ScriptSetting.fromJson(data.get("consumePointScript")))
            .withChangePointNotification(data.get("changePointNotification") == null || data.get("changePointNotification").isNull() ? null : NotificationSetting.fromJson(data.get("changePointNotification")))
            .withLogSetting(data.get("logSetting") == null || data.get("logSetting").isNull() ? null : LogSetting.fromJson(data.get("logSetting")))
            .withCreatedAt(data.get("createdAt") == null || data.get("createdAt").isNull() ? null : data.get("createdAt").longValue())
            .withUpdatedAt(data.get("updatedAt") == null || data.get("updatedAt").isNull() ? null : data.get("updatedAt").longValue())
            .withRevision(data.get("revision") == null || data.get("revision").isNull() ? null : data.get("revision").longValue());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("namespaceId", getNamespaceId());
                put("name", getName());
                put("description", getDescription());
                put("admob", getAdmob() != null ? getAdmob().toJson() : null);
                put("unityAd", getUnityAd() != null ? getUnityAd().toJson() : null);
                put("appLovinMaxes", getAppLovinMaxes() == null ? null :
                    getAppLovinMaxes().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("acquirePointScript", getAcquirePointScript() != null ? getAcquirePointScript().toJson() : null);
                put("consumePointScript", getConsumePointScript() != null ? getConsumePointScript().toJson() : null);
                put("changePointNotification", getChangePointNotification() != null ? getChangePointNotification().toJson() : null);
                put("logSetting", getLogSetting() != null ? getLogSetting().toJson() : null);
                put("createdAt", getCreatedAt());
                put("updatedAt", getUpdatedAt());
                put("revision", getRevision());
            }}
        );
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
        result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
        result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
        result = prime * result + ((this.admob == null) ? 0 : this.admob.hashCode());
        result = prime * result + ((this.unityAd == null) ? 0 : this.unityAd.hashCode());
        result = prime * result + ((this.appLovinMaxes == null) ? 0 : this.appLovinMaxes.hashCode());
        result = prime * result + ((this.acquirePointScript == null) ? 0 : this.acquirePointScript.hashCode());
        result = prime * result + ((this.consumePointScript == null) ? 0 : this.consumePointScript.hashCode());
        result = prime * result + ((this.changePointNotification == null) ? 0 : this.changePointNotification.hashCode());
        result = prime * result + ((this.logSetting == null) ? 0 : this.logSetting.hashCode());
        result = prime * result + ((this.createdAt == null) ? 0 : this.createdAt.hashCode());
        result = prime * result + ((this.updatedAt == null) ? 0 : this.updatedAt.hashCode());
        result = prime * result + ((this.revision == null) ? 0 : this.revision.hashCode());
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
		if (admob == null) {
			return other.admob == null;
		} else if (!admob.equals(other.admob)) {
			return false;
		}
		if (unityAd == null) {
			return other.unityAd == null;
		} else if (!unityAd.equals(other.unityAd)) {
			return false;
		}
		if (appLovinMaxes == null) {
			return other.appLovinMaxes == null;
		} else if (!appLovinMaxes.equals(other.appLovinMaxes)) {
			return false;
		}
		if (acquirePointScript == null) {
			return other.acquirePointScript == null;
		} else if (!acquirePointScript.equals(other.acquirePointScript)) {
			return false;
		}
		if (consumePointScript == null) {
			return other.consumePointScript == null;
		} else if (!consumePointScript.equals(other.consumePointScript)) {
			return false;
		}
		if (changePointNotification == null) {
			return other.changePointNotification == null;
		} else if (!changePointNotification.equals(other.changePointNotification)) {
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
		if (revision == null) {
			return other.revision == null;
		} else if (!revision.equals(other.revision)) {
			return false;
		}
		return true;
	}
}