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

package io.gs2.money2.model;

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
public class PlatformSetting implements IModel, Serializable {
	private AppleAppStoreSetting appleAppStore;
	private GooglePlaySetting googlePlay;
	private FakeSetting fake;
	public AppleAppStoreSetting getAppleAppStore() {
		return appleAppStore;
	}
	public void setAppleAppStore(AppleAppStoreSetting appleAppStore) {
		this.appleAppStore = appleAppStore;
	}
	public PlatformSetting withAppleAppStore(AppleAppStoreSetting appleAppStore) {
		this.appleAppStore = appleAppStore;
		return this;
	}
	public GooglePlaySetting getGooglePlay() {
		return googlePlay;
	}
	public void setGooglePlay(GooglePlaySetting googlePlay) {
		this.googlePlay = googlePlay;
	}
	public PlatformSetting withGooglePlay(GooglePlaySetting googlePlay) {
		this.googlePlay = googlePlay;
		return this;
	}
	public FakeSetting getFake() {
		return fake;
	}
	public void setFake(FakeSetting fake) {
		this.fake = fake;
	}
	public PlatformSetting withFake(FakeSetting fake) {
		this.fake = fake;
		return this;
	}

    public static PlatformSetting fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new PlatformSetting()
            .withAppleAppStore(data.get("appleAppStore") == null || data.get("appleAppStore").isNull() ? null : AppleAppStoreSetting.fromJson(data.get("appleAppStore")))
            .withGooglePlay(data.get("googlePlay") == null || data.get("googlePlay").isNull() ? null : GooglePlaySetting.fromJson(data.get("googlePlay")))
            .withFake(data.get("fake") == null || data.get("fake").isNull() ? null : FakeSetting.fromJson(data.get("fake")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("appleAppStore", getAppleAppStore() != null ? getAppleAppStore().toJson() : null);
                put("googlePlay", getGooglePlay() != null ? getGooglePlay().toJson() : null);
                put("fake", getFake() != null ? getFake().toJson() : null);
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.appleAppStore == null) ? 0 : this.appleAppStore.hashCode());
        result = prime * result + ((this.googlePlay == null) ? 0 : this.googlePlay.hashCode());
        result = prime * result + ((this.fake == null) ? 0 : this.fake.hashCode());
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
		PlatformSetting other = (PlatformSetting) o;
		if (appleAppStore == null) {
			return other.appleAppStore == null;
		} else if (!appleAppStore.equals(other.appleAppStore)) {
			return false;
		}
		if (googlePlay == null) {
			return other.googlePlay == null;
		} else if (!googlePlay.equals(other.googlePlay)) {
			return false;
		}
		if (fake == null) {
			return other.fake == null;
		} else if (!fake.equals(other.fake)) {
			return false;
		}
		return true;
	}
}