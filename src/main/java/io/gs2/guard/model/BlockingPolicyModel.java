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

package io.gs2.guard.model;

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
public class BlockingPolicyModel implements IModel, Serializable {
	private List<String> passServices;
	private String defaultRestriction;
	private String locationDetection;
	private List<String> locations;
	private String locationRestriction;
	private String anonymousIpDetection;
	private String anonymousIpRestriction;
	private String hostingProviderIpDetection;
	private String hostingProviderIpRestriction;
	private String reputationIpDetection;
	private String reputationIpRestriction;
	private String ipAddressesDetection;
	private List<String> ipAddresses;
	private String ipAddressRestriction;
	public List<String> getPassServices() {
		return passServices;
	}
	public void setPassServices(List<String> passServices) {
		this.passServices = passServices;
	}
	public BlockingPolicyModel withPassServices(List<String> passServices) {
		this.passServices = passServices;
		return this;
	}
	public String getDefaultRestriction() {
		return defaultRestriction;
	}
	public void setDefaultRestriction(String defaultRestriction) {
		this.defaultRestriction = defaultRestriction;
	}
	public BlockingPolicyModel withDefaultRestriction(String defaultRestriction) {
		this.defaultRestriction = defaultRestriction;
		return this;
	}
	public String getLocationDetection() {
		return locationDetection;
	}
	public void setLocationDetection(String locationDetection) {
		this.locationDetection = locationDetection;
	}
	public BlockingPolicyModel withLocationDetection(String locationDetection) {
		this.locationDetection = locationDetection;
		return this;
	}
	public List<String> getLocations() {
		return locations;
	}
	public void setLocations(List<String> locations) {
		this.locations = locations;
	}
	public BlockingPolicyModel withLocations(List<String> locations) {
		this.locations = locations;
		return this;
	}
	public String getLocationRestriction() {
		return locationRestriction;
	}
	public void setLocationRestriction(String locationRestriction) {
		this.locationRestriction = locationRestriction;
	}
	public BlockingPolicyModel withLocationRestriction(String locationRestriction) {
		this.locationRestriction = locationRestriction;
		return this;
	}
	public String getAnonymousIpDetection() {
		return anonymousIpDetection;
	}
	public void setAnonymousIpDetection(String anonymousIpDetection) {
		this.anonymousIpDetection = anonymousIpDetection;
	}
	public BlockingPolicyModel withAnonymousIpDetection(String anonymousIpDetection) {
		this.anonymousIpDetection = anonymousIpDetection;
		return this;
	}
	public String getAnonymousIpRestriction() {
		return anonymousIpRestriction;
	}
	public void setAnonymousIpRestriction(String anonymousIpRestriction) {
		this.anonymousIpRestriction = anonymousIpRestriction;
	}
	public BlockingPolicyModel withAnonymousIpRestriction(String anonymousIpRestriction) {
		this.anonymousIpRestriction = anonymousIpRestriction;
		return this;
	}
	public String getHostingProviderIpDetection() {
		return hostingProviderIpDetection;
	}
	public void setHostingProviderIpDetection(String hostingProviderIpDetection) {
		this.hostingProviderIpDetection = hostingProviderIpDetection;
	}
	public BlockingPolicyModel withHostingProviderIpDetection(String hostingProviderIpDetection) {
		this.hostingProviderIpDetection = hostingProviderIpDetection;
		return this;
	}
	public String getHostingProviderIpRestriction() {
		return hostingProviderIpRestriction;
	}
	public void setHostingProviderIpRestriction(String hostingProviderIpRestriction) {
		this.hostingProviderIpRestriction = hostingProviderIpRestriction;
	}
	public BlockingPolicyModel withHostingProviderIpRestriction(String hostingProviderIpRestriction) {
		this.hostingProviderIpRestriction = hostingProviderIpRestriction;
		return this;
	}
	public String getReputationIpDetection() {
		return reputationIpDetection;
	}
	public void setReputationIpDetection(String reputationIpDetection) {
		this.reputationIpDetection = reputationIpDetection;
	}
	public BlockingPolicyModel withReputationIpDetection(String reputationIpDetection) {
		this.reputationIpDetection = reputationIpDetection;
		return this;
	}
	public String getReputationIpRestriction() {
		return reputationIpRestriction;
	}
	public void setReputationIpRestriction(String reputationIpRestriction) {
		this.reputationIpRestriction = reputationIpRestriction;
	}
	public BlockingPolicyModel withReputationIpRestriction(String reputationIpRestriction) {
		this.reputationIpRestriction = reputationIpRestriction;
		return this;
	}
	public String getIpAddressesDetection() {
		return ipAddressesDetection;
	}
	public void setIpAddressesDetection(String ipAddressesDetection) {
		this.ipAddressesDetection = ipAddressesDetection;
	}
	public BlockingPolicyModel withIpAddressesDetection(String ipAddressesDetection) {
		this.ipAddressesDetection = ipAddressesDetection;
		return this;
	}
	public List<String> getIpAddresses() {
		return ipAddresses;
	}
	public void setIpAddresses(List<String> ipAddresses) {
		this.ipAddresses = ipAddresses;
	}
	public BlockingPolicyModel withIpAddresses(List<String> ipAddresses) {
		this.ipAddresses = ipAddresses;
		return this;
	}
	public String getIpAddressRestriction() {
		return ipAddressRestriction;
	}
	public void setIpAddressRestriction(String ipAddressRestriction) {
		this.ipAddressRestriction = ipAddressRestriction;
	}
	public BlockingPolicyModel withIpAddressRestriction(String ipAddressRestriction) {
		this.ipAddressRestriction = ipAddressRestriction;
		return this;
	}

    public static BlockingPolicyModel fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new BlockingPolicyModel()
            .withPassServices(data.get("passServices") == null || data.get("passServices").isNull() ? new ArrayList<String>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("passServices").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.asText();
                }
            ).collect(Collectors.toList()))
            .withDefaultRestriction(data.get("defaultRestriction") == null || data.get("defaultRestriction").isNull() ? null : data.get("defaultRestriction").asText())
            .withLocationDetection(data.get("locationDetection") == null || data.get("locationDetection").isNull() ? null : data.get("locationDetection").asText())
            .withLocations(data.get("locations") == null || data.get("locations").isNull() ? new ArrayList<String>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("locations").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.asText();
                }
            ).collect(Collectors.toList()))
            .withLocationRestriction(data.get("locationRestriction") == null || data.get("locationRestriction").isNull() ? null : data.get("locationRestriction").asText())
            .withAnonymousIpDetection(data.get("anonymousIpDetection") == null || data.get("anonymousIpDetection").isNull() ? null : data.get("anonymousIpDetection").asText())
            .withAnonymousIpRestriction(data.get("anonymousIpRestriction") == null || data.get("anonymousIpRestriction").isNull() ? null : data.get("anonymousIpRestriction").asText())
            .withHostingProviderIpDetection(data.get("hostingProviderIpDetection") == null || data.get("hostingProviderIpDetection").isNull() ? null : data.get("hostingProviderIpDetection").asText())
            .withHostingProviderIpRestriction(data.get("hostingProviderIpRestriction") == null || data.get("hostingProviderIpRestriction").isNull() ? null : data.get("hostingProviderIpRestriction").asText())
            .withReputationIpDetection(data.get("reputationIpDetection") == null || data.get("reputationIpDetection").isNull() ? null : data.get("reputationIpDetection").asText())
            .withReputationIpRestriction(data.get("reputationIpRestriction") == null || data.get("reputationIpRestriction").isNull() ? null : data.get("reputationIpRestriction").asText())
            .withIpAddressesDetection(data.get("ipAddressesDetection") == null || data.get("ipAddressesDetection").isNull() ? null : data.get("ipAddressesDetection").asText())
            .withIpAddresses(data.get("ipAddresses") == null || data.get("ipAddresses").isNull() ? new ArrayList<String>() :
                StreamSupport.stream(Spliterators.spliteratorUnknownSize(data.get("ipAddresses").elements(), Spliterator.NONNULL), false).map(item -> {
                    return item.asText();
                }
            ).collect(Collectors.toList()))
            .withIpAddressRestriction(data.get("ipAddressRestriction") == null || data.get("ipAddressRestriction").isNull() ? null : data.get("ipAddressRestriction").asText());
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("passServices", getPassServices() == null ? new ArrayList<String>() :
                    getPassServices().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
                put("defaultRestriction", getDefaultRestriction());
                put("locationDetection", getLocationDetection());
                put("locations", getLocations() == null ? new ArrayList<String>() :
                    getLocations().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
                put("locationRestriction", getLocationRestriction());
                put("anonymousIpDetection", getAnonymousIpDetection());
                put("anonymousIpRestriction", getAnonymousIpRestriction());
                put("hostingProviderIpDetection", getHostingProviderIpDetection());
                put("hostingProviderIpRestriction", getHostingProviderIpRestriction());
                put("reputationIpDetection", getReputationIpDetection());
                put("reputationIpRestriction", getReputationIpRestriction());
                put("ipAddressesDetection", getIpAddressesDetection());
                put("ipAddresses", getIpAddresses() == null ? new ArrayList<String>() :
                    getIpAddresses().stream().map(item -> {
                        return item;
                    }
                ).collect(Collectors.toList()));
                put("ipAddressRestriction", getIpAddressRestriction());
            }}
        );
    }

	@Override
	public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.passServices == null) ? 0 : this.passServices.hashCode());
        result = prime * result + ((this.defaultRestriction == null) ? 0 : this.defaultRestriction.hashCode());
        result = prime * result + ((this.locationDetection == null) ? 0 : this.locationDetection.hashCode());
        result = prime * result + ((this.locations == null) ? 0 : this.locations.hashCode());
        result = prime * result + ((this.locationRestriction == null) ? 0 : this.locationRestriction.hashCode());
        result = prime * result + ((this.anonymousIpDetection == null) ? 0 : this.anonymousIpDetection.hashCode());
        result = prime * result + ((this.anonymousIpRestriction == null) ? 0 : this.anonymousIpRestriction.hashCode());
        result = prime * result + ((this.hostingProviderIpDetection == null) ? 0 : this.hostingProviderIpDetection.hashCode());
        result = prime * result + ((this.hostingProviderIpRestriction == null) ? 0 : this.hostingProviderIpRestriction.hashCode());
        result = prime * result + ((this.reputationIpDetection == null) ? 0 : this.reputationIpDetection.hashCode());
        result = prime * result + ((this.reputationIpRestriction == null) ? 0 : this.reputationIpRestriction.hashCode());
        result = prime * result + ((this.ipAddressesDetection == null) ? 0 : this.ipAddressesDetection.hashCode());
        result = prime * result + ((this.ipAddresses == null) ? 0 : this.ipAddresses.hashCode());
        result = prime * result + ((this.ipAddressRestriction == null) ? 0 : this.ipAddressRestriction.hashCode());
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
		BlockingPolicyModel other = (BlockingPolicyModel) o;
		if (passServices == null) {
			return other.passServices == null;
		} else if (!passServices.equals(other.passServices)) {
			return false;
		}
		if (defaultRestriction == null) {
			return other.defaultRestriction == null;
		} else if (!defaultRestriction.equals(other.defaultRestriction)) {
			return false;
		}
		if (locationDetection == null) {
			return other.locationDetection == null;
		} else if (!locationDetection.equals(other.locationDetection)) {
			return false;
		}
		if (locations == null) {
			return other.locations == null;
		} else if (!locations.equals(other.locations)) {
			return false;
		}
		if (locationRestriction == null) {
			return other.locationRestriction == null;
		} else if (!locationRestriction.equals(other.locationRestriction)) {
			return false;
		}
		if (anonymousIpDetection == null) {
			return other.anonymousIpDetection == null;
		} else if (!anonymousIpDetection.equals(other.anonymousIpDetection)) {
			return false;
		}
		if (anonymousIpRestriction == null) {
			return other.anonymousIpRestriction == null;
		} else if (!anonymousIpRestriction.equals(other.anonymousIpRestriction)) {
			return false;
		}
		if (hostingProviderIpDetection == null) {
			return other.hostingProviderIpDetection == null;
		} else if (!hostingProviderIpDetection.equals(other.hostingProviderIpDetection)) {
			return false;
		}
		if (hostingProviderIpRestriction == null) {
			return other.hostingProviderIpRestriction == null;
		} else if (!hostingProviderIpRestriction.equals(other.hostingProviderIpRestriction)) {
			return false;
		}
		if (reputationIpDetection == null) {
			return other.reputationIpDetection == null;
		} else if (!reputationIpDetection.equals(other.reputationIpDetection)) {
			return false;
		}
		if (reputationIpRestriction == null) {
			return other.reputationIpRestriction == null;
		} else if (!reputationIpRestriction.equals(other.reputationIpRestriction)) {
			return false;
		}
		if (ipAddressesDetection == null) {
			return other.ipAddressesDetection == null;
		} else if (!ipAddressesDetection.equals(other.ipAddressesDetection)) {
			return false;
		}
		if (ipAddresses == null) {
			return other.ipAddresses == null;
		} else if (!ipAddresses.equals(other.ipAddresses)) {
			return false;
		}
		if (ipAddressRestriction == null) {
			return other.ipAddressRestriction == null;
		} else if (!ipAddressRestriction.equals(other.ipAddressRestriction)) {
			return false;
		}
		return true;
	}
}