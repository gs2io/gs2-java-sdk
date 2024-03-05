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

package io.gs2.formation.result;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import io.gs2.core.model.*;
import io.gs2.formation.model.*;
import io.gs2.formation.model.Slot;
import io.gs2.formation.model.Form;
import io.gs2.formation.model.Mold;
import io.gs2.formation.model.SlotModel;
import io.gs2.formation.model.FormModel;
import io.gs2.formation.model.MoldModel;

@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class SetFormByStampSheetResult implements IResult, Serializable {
    private Form item;
    private Mold mold;
    private MoldModel moldModel;
    private FormModel formModel;

	public Form getItem() {
		return item;
	}

	public void setItem(Form item) {
		this.item = item;
	}

	public SetFormByStampSheetResult withItem(Form item) {
		this.item = item;
		return this;
	}

	public Mold getMold() {
		return mold;
	}

	public void setMold(Mold mold) {
		this.mold = mold;
	}

	public SetFormByStampSheetResult withMold(Mold mold) {
		this.mold = mold;
		return this;
	}

	public MoldModel getMoldModel() {
		return moldModel;
	}

	public void setMoldModel(MoldModel moldModel) {
		this.moldModel = moldModel;
	}

	public SetFormByStampSheetResult withMoldModel(MoldModel moldModel) {
		this.moldModel = moldModel;
		return this;
	}

	public FormModel getFormModel() {
		return formModel;
	}

	public void setFormModel(FormModel formModel) {
		this.formModel = formModel;
	}

	public SetFormByStampSheetResult withFormModel(FormModel formModel) {
		this.formModel = formModel;
		return this;
	}

    public static SetFormByStampSheetResult fromJson(JsonNode data) {
        if (data == null) {
            return null;
        }
        return new SetFormByStampSheetResult()
            .withItem(data.get("item") == null || data.get("item").isNull() ? null : Form.fromJson(data.get("item")))
            .withMold(data.get("mold") == null || data.get("mold").isNull() ? null : Mold.fromJson(data.get("mold")))
            .withMoldModel(data.get("moldModel") == null || data.get("moldModel").isNull() ? null : MoldModel.fromJson(data.get("moldModel")))
            .withFormModel(data.get("formModel") == null || data.get("formModel").isNull() ? null : FormModel.fromJson(data.get("formModel")));
    }

    public JsonNode toJson() {
        return new ObjectMapper().valueToTree(
            new HashMap<String, Object>() {{
                put("item", getItem() != null ? getItem().toJson() : null);
                put("mold", getMold() != null ? getMold().toJson() : null);
                put("moldModel", getMoldModel() != null ? getMoldModel().toJson() : null);
                put("formModel", getFormModel() != null ? getFormModel().toJson() : null);
            }}
        );
    }
}