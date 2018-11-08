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

package io.gs2.schedule.control;

import org.json.JSONObject;
import java.util.List;
import io.gs2.schedule.model.*;
import io.gs2.schedule.Gs2Schedule;
import io.gs2.control.Gs2BasicRequest;

/**
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class UpdateEventMasterRequest extends Gs2BasicRequest<UpdateEventMasterRequest> {

	public static class Constant extends Gs2Schedule.Constant {
		public static final String FUNCTION = "UpdateEventMaster";
	}

	/** スケジュールの名前を指定します。 */
	private String scheduleName;

	/** イベント名を指定します。 */
	private String eventName;

	/** メタデータ */
	private String meta;

	/** 期間 */
	private String type;

	/** 絶対時間を選択した場合の開始日時 */
	private Integer absoluteBegin;

	/** 絶対時間を選択した場合の終了日時 */
	private Integer absoluteEnd;

	/** 相対時間を選択した場合の開始トリガー名 */
	private String relativeTriggerName;

	/** 相対時間を選択した場合のトリガーを引いてからのイベント期間(分) */
	private Integer relativeSpan;


	/**
	 * スケジュールの名前を指定します。を取得
	 *
	 * @return スケジュールの名前を指定します。
	 */
	public String getScheduleName() {
		return scheduleName;
	}

	/**
	 * スケジュールの名前を指定します。を設定
	 *
	 * @param scheduleName スケジュールの名前を指定します。
	 */
	public void setScheduleName(String scheduleName) {
		this.scheduleName = scheduleName;
	}

	/**
	 * スケジュールの名前を指定します。を設定
	 *
	 * @param scheduleName スケジュールの名前を指定します。
	 * @return this
	 */
	public UpdateEventMasterRequest withScheduleName(String scheduleName) {
		setScheduleName(scheduleName);
		return this;
	}

	/**
	 * イベント名を指定します。を取得
	 *
	 * @return イベント名を指定します。
	 */
	public String getEventName() {
		return eventName;
	}

	/**
	 * イベント名を指定します。を設定
	 *
	 * @param eventName イベント名を指定します。
	 */
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	/**
	 * イベント名を指定します。を設定
	 *
	 * @param eventName イベント名を指定します。
	 * @return this
	 */
	public UpdateEventMasterRequest withEventName(String eventName) {
		setEventName(eventName);
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
	public UpdateEventMasterRequest withMeta(String meta) {
		setMeta(meta);
		return this;
	}

	/**
	 * 期間を取得
	 *
	 * @return 期間
	 */
	public String getType() {
		return type;
	}

	/**
	 * 期間を設定
	 *
	 * @param type 期間
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 期間を設定
	 *
	 * @param type 期間
	 * @return this
	 */
	public UpdateEventMasterRequest withType(String type) {
		setType(type);
		return this;
	}

	/**
	 * 絶対時間を選択した場合の開始日時を取得
	 *
	 * @return 絶対時間を選択した場合の開始日時
	 */
	public Integer getAbsoluteBegin() {
		return absoluteBegin;
	}

	/**
	 * 絶対時間を選択した場合の開始日時を設定
	 *
	 * @param absoluteBegin 絶対時間を選択した場合の開始日時
	 */
	public void setAbsoluteBegin(Integer absoluteBegin) {
		this.absoluteBegin = absoluteBegin;
	}

	/**
	 * 絶対時間を選択した場合の開始日時を設定
	 *
	 * @param absoluteBegin 絶対時間を選択した場合の開始日時
	 * @return this
	 */
	public UpdateEventMasterRequest withAbsoluteBegin(Integer absoluteBegin) {
		setAbsoluteBegin(absoluteBegin);
		return this;
	}

	/**
	 * 絶対時間を選択した場合の終了日時を取得
	 *
	 * @return 絶対時間を選択した場合の終了日時
	 */
	public Integer getAbsoluteEnd() {
		return absoluteEnd;
	}

	/**
	 * 絶対時間を選択した場合の終了日時を設定
	 *
	 * @param absoluteEnd 絶対時間を選択した場合の終了日時
	 */
	public void setAbsoluteEnd(Integer absoluteEnd) {
		this.absoluteEnd = absoluteEnd;
	}

	/**
	 * 絶対時間を選択した場合の終了日時を設定
	 *
	 * @param absoluteEnd 絶対時間を選択した場合の終了日時
	 * @return this
	 */
	public UpdateEventMasterRequest withAbsoluteEnd(Integer absoluteEnd) {
		setAbsoluteEnd(absoluteEnd);
		return this;
	}

	/**
	 * 相対時間を選択した場合の開始トリガー名を取得
	 *
	 * @return 相対時間を選択した場合の開始トリガー名
	 */
	public String getRelativeTriggerName() {
		return relativeTriggerName;
	}

	/**
	 * 相対時間を選択した場合の開始トリガー名を設定
	 *
	 * @param relativeTriggerName 相対時間を選択した場合の開始トリガー名
	 */
	public void setRelativeTriggerName(String relativeTriggerName) {
		this.relativeTriggerName = relativeTriggerName;
	}

	/**
	 * 相対時間を選択した場合の開始トリガー名を設定
	 *
	 * @param relativeTriggerName 相対時間を選択した場合の開始トリガー名
	 * @return this
	 */
	public UpdateEventMasterRequest withRelativeTriggerName(String relativeTriggerName) {
		setRelativeTriggerName(relativeTriggerName);
		return this;
	}

	/**
	 * 相対時間を選択した場合のトリガーを引いてからのイベント期間(分)を取得
	 *
	 * @return 相対時間を選択した場合のトリガーを引いてからのイベント期間(分)
	 */
	public Integer getRelativeSpan() {
		return relativeSpan;
	}

	/**
	 * 相対時間を選択した場合のトリガーを引いてからのイベント期間(分)を設定
	 *
	 * @param relativeSpan 相対時間を選択した場合のトリガーを引いてからのイベント期間(分)
	 */
	public void setRelativeSpan(Integer relativeSpan) {
		this.relativeSpan = relativeSpan;
	}

	/**
	 * 相対時間を選択した場合のトリガーを引いてからのイベント期間(分)を設定
	 *
	 * @param relativeSpan 相対時間を選択した場合のトリガーを引いてからのイベント期間(分)
	 * @return this
	 */
	public UpdateEventMasterRequest withRelativeSpan(Integer relativeSpan) {
		setRelativeSpan(relativeSpan);
		return this;
	}

}