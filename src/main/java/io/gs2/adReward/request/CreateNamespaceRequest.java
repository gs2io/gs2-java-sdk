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

package io.gs2.adReward.request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.control.Gs2BasicRequest;
import io.gs2.adReward.model.AdMob;
import io.gs2.adReward.model.UnityAd;
import io.gs2.adReward.model.AppLovinMax;
import io.gs2.adReward.model.ScriptSetting;
import io.gs2.adReward.model.NotificationSetting;
import io.gs2.adReward.model.LogSetting;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class CreateNamespaceRequest extends Gs2BasicRequest<CreateNamespaceRequest> {
    private String name;
    private AdMob admob;
    private UnityAd unityAd;
    private List<AppLovinMax> appLovinMaxes;
    private String description;
    private ScriptSetting acquirePointScript;
    private ScriptSetting consumePointScript;
    private NotificationSetting changePointNotification;
    private LogSetting logSetting;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CreateNamespaceRequest withName(String name) {
		this.name = name;
		return this;
	}
	public AdMob getAdmob() {
		return admob;
	}
	public void setAdmob(AdMob admob) {
		this.admob = admob;
	}
	public CreateNamespaceRequest withAdmob(AdMob admob) {
		this.admob = admob;
		return this;
	}
	public UnityAd getUnityAd() {
		return unityAd;
	}
	public void setUnityAd(UnityAd unityAd) {
		this.unityAd = unityAd;
	}
	public CreateNamespaceRequest withUnityAd(UnityAd unityAd) {
		this.unityAd = unityAd;
		return this;
	}
	public List<AppLovinMax> getAppLovinMaxes() {
		return appLovinMaxes;
	}
	public void setAppLovinMaxes(List<AppLovinMax> appLovinMaxes) {
		this.appLovinMaxes = appLovinMaxes;
	}
	public CreateNamespaceRequest withAppLovinMaxes(List<AppLovinMax> appLovinMaxes) {
		this.appLovinMaxes = appLovinMaxes;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public CreateNamespaceRequest withDescription(String description) {
		this.description = description;
		return this;
	}
	public ScriptSetting getAcquirePointScript() {
		return acquirePointScript;
	}
	public void setAcquirePointScript(ScriptSetting acquirePointScript) {
		this.acquirePointScript = acquirePointScript;
	}
	public CreateNamespaceRequest withAcquirePointScript(ScriptSetting acquirePointScript) {
		this.acquirePointScript = acquirePointScript;
		return this;
	}
	public ScriptSetting getConsumePointScript() {
		return consumePointScript;
	}
	public void setConsumePointScript(ScriptSetting consumePointScript) {
		this.consumePointScript = consumePointScript;
	}
	public CreateNamespaceRequest withConsumePointScript(ScriptSetting consumePointScript) {
		this.consumePointScript = consumePointScript;
		return this;
	}
	public NotificationSetting getChangePointNotification() {
		return changePointNotification;
	}
	public void setChangePointNotification(NotificationSetting changePointNotification) {
		this.changePointNotification = changePointNotification;
	}
	public CreateNamespaceRequest withChangePointNotification(NotificationSetting changePointNotification) {
		this.changePointNotification = changePointNotification;
		return this;
	}
	public LogSetting getLogSetting() {
		return logSetting;
	}
	public void setLogSetting(LogSetting logSetting) {
		this.logSetting = logSetting;
	}
	public CreateNamespaceRequest withLogSetting(LogSetting logSetting) {
		this.logSetting = logSetting;
		return this;
	}

    public static CreateNamespaceRequest fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new CreateNamespaceRequest()
            .withName(data.get("name") == null || data.get("name").isNull() ? null : data.get("name").asText())
            .withAdmob(data.get("admob") == null || data.get("admob").isNull() ? null : AdMob.fromJson(data.get("admob")))
            .withUnityAd(data.get("unityAd") == null || data.get("unityAd").isNull() ? null : UnityAd.fromJson(data.get("unityAd")))
            .withAppLovinMaxes(data.get("appLovinMaxes") == null || data.get("appLovinMaxes").isNull() ? new ArrayList<AppLovinMax>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("appLovinMaxes").elements(), Spliterator.NONNULL), false).map(item -> {
                    //noinspection Convert2MethodRef
                    return AppLovinMax.fromJson(item);
                }
            ).collect(Collectors.toList()))
            .withDescription(data.get("description") == null || data.get("description").isNull() ? null : data.get("description").asText())
            .withAcquirePointScript(data.get("acquirePointScript") == null || data.get("acquirePointScript").isNull() ? null : ScriptSetting.fromJson(data.get("acquirePointScript")))
            .withConsumePointScript(data.get("consumePointScript") == null || data.get("consumePointScript").isNull() ? null : ScriptSetting.fromJson(data.get("consumePointScript")))
            .withChangePointNotification(data.get("changePointNotification") == null || data.get("changePointNotification").isNull() ? null : NotificationSetting.fromJson(data.get("changePointNotification")))
            .withLogSetting(data.get("logSetting") == null || data.get("logSetting").isNull() ? null : LogSetting.fromJson(data.get("logSetting")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("name", getName());
                put("admob", getAdmob() != null ? getAdmob().toJson() : null);
                put("unityAd", getUnityAd() != null ? getUnityAd().toJson() : null);
                put("appLovinMaxes", getAppLovinMaxes() == null ? new ArrayList<AppLovinMax>() :
                    getAppLovinMaxes().stream().map(item -> {
                        //noinspection Convert2MethodRef
                        return item.toJson();
                    }
                ).collect(Collectors.toList()));
                put("description", getDescription());
                put("acquirePointScript", getAcquirePointScript() != null ? getAcquirePointScript().toJson() : null);
                put("consumePointScript", getConsumePointScript() != null ? getConsumePointScript().toJson() : null);
                put("changePointNotification", getChangePointNotification() != null ? getChangePointNotification().toJson() : null);
                put("logSetting", getLogSetting() != null ? getLogSetting().toJson() : null);
            }}
        );
    }
}