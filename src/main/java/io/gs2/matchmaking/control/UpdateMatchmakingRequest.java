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

package io.gs2.matchmaking.control;

import org.json.JSONObject;
import java.util.List;
import io.gs2.matchmaking.Gs2Matchmaking;
import io.gs2.control.Gs2BasicRequest;

/**
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class UpdateMatchmakingRequest extends Gs2BasicRequest<UpdateMatchmakingRequest> {

	public static class Constant extends Gs2Matchmaking.Constant {
		public static final String FUNCTION = "UpdateMatchmaking";
	}

	/** マッチメイキングの名前を指定します。 */
	private String matchmakingName;

	/** マッチメイキングの説明 */
	private String description;

	/** マッチメイキングのサービスクラス */
	private String serviceClass;

	/** GS2-Realtime のギャザリングプール名 */
	private String gatheringPoolName;

	/** マッチメイキング完了コールバックURL */
	private String callback;

	/** GS2-InGamePushNotification のゲーム名 */
	private String notificationGameName;

	/** ギャザリング作成時 に実行されるGS2-Script */
	private String createGatheringTriggerScript;

	/** ギャザリング作成完了時 に実行されるGS2-Script */
	private String createGatheringDoneTriggerScript;

	/** ギャザリング参加時 に実行されるGS2-Script */
	private String joinGatheringTriggerScript;

	/** ギャザリング参加完了時 に実行されるGS2-Script */
	private String joinGatheringDoneTriggerScript;

	/** ギャザリング離脱時 に実行されるGS2-Script */
	private String leaveGatheringTriggerScript;

	/** ギャザリング離脱完了時 に実行されるGS2-Script */
	private String leaveGatheringDoneTriggerScript;

	/** ギャザリング解散時 に実行されるGS2-Script */
	private String breakupGatheringTriggerScript;

	/** マッチメイキング成立時 に実行されるGS2-Script */
	private String matchmakingCompleteTriggerScript;


	/**
	 * マッチメイキングの名前を指定します。を取得
	 *
	 * @return マッチメイキングの名前を指定します。
	 */
	public String getMatchmakingName() {
		return matchmakingName;
	}

	/**
	 * マッチメイキングの名前を指定します。を設定
	 *
	 * @param matchmakingName マッチメイキングの名前を指定します。
	 */
	public void setMatchmakingName(String matchmakingName) {
		this.matchmakingName = matchmakingName;
	}

	/**
	 * マッチメイキングの名前を指定します。を設定
	 *
	 * @param matchmakingName マッチメイキングの名前を指定します。
	 * @return this
	 */
	public UpdateMatchmakingRequest withMatchmakingName(String matchmakingName) {
		setMatchmakingName(matchmakingName);
		return this;
	}

	/**
	 * マッチメイキングの説明を取得
	 *
	 * @return マッチメイキングの説明
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * マッチメイキングの説明を設定
	 *
	 * @param description マッチメイキングの説明
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * マッチメイキングの説明を設定
	 *
	 * @param description マッチメイキングの説明
	 * @return this
	 */
	public UpdateMatchmakingRequest withDescription(String description) {
		setDescription(description);
		return this;
	}

	/**
	 * マッチメイキングのサービスクラスを取得
	 *
	 * @return マッチメイキングのサービスクラス
	 */
	public String getServiceClass() {
		return serviceClass;
	}

	/**
	 * マッチメイキングのサービスクラスを設定
	 *
	 * @param serviceClass マッチメイキングのサービスクラス
	 */
	public void setServiceClass(String serviceClass) {
		this.serviceClass = serviceClass;
	}

	/**
	 * マッチメイキングのサービスクラスを設定
	 *
	 * @param serviceClass マッチメイキングのサービスクラス
	 * @return this
	 */
	public UpdateMatchmakingRequest withServiceClass(String serviceClass) {
		setServiceClass(serviceClass);
		return this;
	}

	/**
	 * GS2-Realtime のギャザリングプール名を取得
	 *
	 * @return GS2-Realtime のギャザリングプール名
	 */
	public String getGatheringPoolName() {
		return gatheringPoolName;
	}

	/**
	 * GS2-Realtime のギャザリングプール名を設定
	 *
	 * @param gatheringPoolName GS2-Realtime のギャザリングプール名
	 */
	public void setGatheringPoolName(String gatheringPoolName) {
		this.gatheringPoolName = gatheringPoolName;
	}

	/**
	 * GS2-Realtime のギャザリングプール名を設定
	 *
	 * @param gatheringPoolName GS2-Realtime のギャザリングプール名
	 * @return this
	 */
	public UpdateMatchmakingRequest withGatheringPoolName(String gatheringPoolName) {
		setGatheringPoolName(gatheringPoolName);
		return this;
	}

	/**
	 * マッチメイキング完了コールバックURLを取得
	 *
	 * @return マッチメイキング完了コールバックURL
	 */
	public String getCallback() {
		return callback;
	}

	/**
	 * マッチメイキング完了コールバックURLを設定
	 *
	 * @param callback マッチメイキング完了コールバックURL
	 */
	public void setCallback(String callback) {
		this.callback = callback;
	}

	/**
	 * マッチメイキング完了コールバックURLを設定
	 *
	 * @param callback マッチメイキング完了コールバックURL
	 * @return this
	 */
	public UpdateMatchmakingRequest withCallback(String callback) {
		setCallback(callback);
		return this;
	}

	/**
	 * GS2-InGamePushNotification のゲーム名を取得
	 *
	 * @return GS2-InGamePushNotification のゲーム名
	 */
	public String getNotificationGameName() {
		return notificationGameName;
	}

	/**
	 * GS2-InGamePushNotification のゲーム名を設定
	 *
	 * @param notificationGameName GS2-InGamePushNotification のゲーム名
	 */
	public void setNotificationGameName(String notificationGameName) {
		this.notificationGameName = notificationGameName;
	}

	/**
	 * GS2-InGamePushNotification のゲーム名を設定
	 *
	 * @param notificationGameName GS2-InGamePushNotification のゲーム名
	 * @return this
	 */
	public UpdateMatchmakingRequest withNotificationGameName(String notificationGameName) {
		setNotificationGameName(notificationGameName);
		return this;
	}

	/**
	 * ギャザリング作成時 に実行されるGS2-Scriptを取得
	 *
	 * @return ギャザリング作成時 に実行されるGS2-Script
	 */
	public String getCreateGatheringTriggerScript() {
		return createGatheringTriggerScript;
	}

	/**
	 * ギャザリング作成時 に実行されるGS2-Scriptを設定
	 *
	 * @param createGatheringTriggerScript ギャザリング作成時 に実行されるGS2-Script
	 */
	public void setCreateGatheringTriggerScript(String createGatheringTriggerScript) {
		this.createGatheringTriggerScript = createGatheringTriggerScript;
	}

	/**
	 * ギャザリング作成時 に実行されるGS2-Scriptを設定
	 *
	 * @param createGatheringTriggerScript ギャザリング作成時 に実行されるGS2-Script
	 * @return this
	 */
	public UpdateMatchmakingRequest withCreateGatheringTriggerScript(String createGatheringTriggerScript) {
		setCreateGatheringTriggerScript(createGatheringTriggerScript);
		return this;
	}

	/**
	 * ギャザリング作成完了時 に実行されるGS2-Scriptを取得
	 *
	 * @return ギャザリング作成完了時 に実行されるGS2-Script
	 */
	public String getCreateGatheringDoneTriggerScript() {
		return createGatheringDoneTriggerScript;
	}

	/**
	 * ギャザリング作成完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param createGatheringDoneTriggerScript ギャザリング作成完了時 に実行されるGS2-Script
	 */
	public void setCreateGatheringDoneTriggerScript(String createGatheringDoneTriggerScript) {
		this.createGatheringDoneTriggerScript = createGatheringDoneTriggerScript;
	}

	/**
	 * ギャザリング作成完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param createGatheringDoneTriggerScript ギャザリング作成完了時 に実行されるGS2-Script
	 * @return this
	 */
	public UpdateMatchmakingRequest withCreateGatheringDoneTriggerScript(String createGatheringDoneTriggerScript) {
		setCreateGatheringDoneTriggerScript(createGatheringDoneTriggerScript);
		return this;
	}

	/**
	 * ギャザリング参加時 に実行されるGS2-Scriptを取得
	 *
	 * @return ギャザリング参加時 に実行されるGS2-Script
	 */
	public String getJoinGatheringTriggerScript() {
		return joinGatheringTriggerScript;
	}

	/**
	 * ギャザリング参加時 に実行されるGS2-Scriptを設定
	 *
	 * @param joinGatheringTriggerScript ギャザリング参加時 に実行されるGS2-Script
	 */
	public void setJoinGatheringTriggerScript(String joinGatheringTriggerScript) {
		this.joinGatheringTriggerScript = joinGatheringTriggerScript;
	}

	/**
	 * ギャザリング参加時 に実行されるGS2-Scriptを設定
	 *
	 * @param joinGatheringTriggerScript ギャザリング参加時 に実行されるGS2-Script
	 * @return this
	 */
	public UpdateMatchmakingRequest withJoinGatheringTriggerScript(String joinGatheringTriggerScript) {
		setJoinGatheringTriggerScript(joinGatheringTriggerScript);
		return this;
	}

	/**
	 * ギャザリング参加完了時 に実行されるGS2-Scriptを取得
	 *
	 * @return ギャザリング参加完了時 に実行されるGS2-Script
	 */
	public String getJoinGatheringDoneTriggerScript() {
		return joinGatheringDoneTriggerScript;
	}

	/**
	 * ギャザリング参加完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param joinGatheringDoneTriggerScript ギャザリング参加完了時 に実行されるGS2-Script
	 */
	public void setJoinGatheringDoneTriggerScript(String joinGatheringDoneTriggerScript) {
		this.joinGatheringDoneTriggerScript = joinGatheringDoneTriggerScript;
	}

	/**
	 * ギャザリング参加完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param joinGatheringDoneTriggerScript ギャザリング参加完了時 に実行されるGS2-Script
	 * @return this
	 */
	public UpdateMatchmakingRequest withJoinGatheringDoneTriggerScript(String joinGatheringDoneTriggerScript) {
		setJoinGatheringDoneTriggerScript(joinGatheringDoneTriggerScript);
		return this;
	}

	/**
	 * ギャザリング離脱時 に実行されるGS2-Scriptを取得
	 *
	 * @return ギャザリング離脱時 に実行されるGS2-Script
	 */
	public String getLeaveGatheringTriggerScript() {
		return leaveGatheringTriggerScript;
	}

	/**
	 * ギャザリング離脱時 に実行されるGS2-Scriptを設定
	 *
	 * @param leaveGatheringTriggerScript ギャザリング離脱時 に実行されるGS2-Script
	 */
	public void setLeaveGatheringTriggerScript(String leaveGatheringTriggerScript) {
		this.leaveGatheringTriggerScript = leaveGatheringTriggerScript;
	}

	/**
	 * ギャザリング離脱時 に実行されるGS2-Scriptを設定
	 *
	 * @param leaveGatheringTriggerScript ギャザリング離脱時 に実行されるGS2-Script
	 * @return this
	 */
	public UpdateMatchmakingRequest withLeaveGatheringTriggerScript(String leaveGatheringTriggerScript) {
		setLeaveGatheringTriggerScript(leaveGatheringTriggerScript);
		return this;
	}

	/**
	 * ギャザリング離脱完了時 に実行されるGS2-Scriptを取得
	 *
	 * @return ギャザリング離脱完了時 に実行されるGS2-Script
	 */
	public String getLeaveGatheringDoneTriggerScript() {
		return leaveGatheringDoneTriggerScript;
	}

	/**
	 * ギャザリング離脱完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param leaveGatheringDoneTriggerScript ギャザリング離脱完了時 に実行されるGS2-Script
	 */
	public void setLeaveGatheringDoneTriggerScript(String leaveGatheringDoneTriggerScript) {
		this.leaveGatheringDoneTriggerScript = leaveGatheringDoneTriggerScript;
	}

	/**
	 * ギャザリング離脱完了時 に実行されるGS2-Scriptを設定
	 *
	 * @param leaveGatheringDoneTriggerScript ギャザリング離脱完了時 に実行されるGS2-Script
	 * @return this
	 */
	public UpdateMatchmakingRequest withLeaveGatheringDoneTriggerScript(String leaveGatheringDoneTriggerScript) {
		setLeaveGatheringDoneTriggerScript(leaveGatheringDoneTriggerScript);
		return this;
	}

	/**
	 * ギャザリング解散時 に実行されるGS2-Scriptを取得
	 *
	 * @return ギャザリング解散時 に実行されるGS2-Script
	 */
	public String getBreakupGatheringTriggerScript() {
		return breakupGatheringTriggerScript;
	}

	/**
	 * ギャザリング解散時 に実行されるGS2-Scriptを設定
	 *
	 * @param breakupGatheringTriggerScript ギャザリング解散時 に実行されるGS2-Script
	 */
	public void setBreakupGatheringTriggerScript(String breakupGatheringTriggerScript) {
		this.breakupGatheringTriggerScript = breakupGatheringTriggerScript;
	}

	/**
	 * ギャザリング解散時 に実行されるGS2-Scriptを設定
	 *
	 * @param breakupGatheringTriggerScript ギャザリング解散時 に実行されるGS2-Script
	 * @return this
	 */
	public UpdateMatchmakingRequest withBreakupGatheringTriggerScript(String breakupGatheringTriggerScript) {
		setBreakupGatheringTriggerScript(breakupGatheringTriggerScript);
		return this;
	}

	/**
	 * マッチメイキング成立時 に実行されるGS2-Scriptを取得
	 *
	 * @return マッチメイキング成立時 に実行されるGS2-Script
	 */
	public String getMatchmakingCompleteTriggerScript() {
		return matchmakingCompleteTriggerScript;
	}

	/**
	 * マッチメイキング成立時 に実行されるGS2-Scriptを設定
	 *
	 * @param matchmakingCompleteTriggerScript マッチメイキング成立時 に実行されるGS2-Script
	 */
	public void setMatchmakingCompleteTriggerScript(String matchmakingCompleteTriggerScript) {
		this.matchmakingCompleteTriggerScript = matchmakingCompleteTriggerScript;
	}

	/**
	 * マッチメイキング成立時 に実行されるGS2-Scriptを設定
	 *
	 * @param matchmakingCompleteTriggerScript マッチメイキング成立時 に実行されるGS2-Script
	 * @return this
	 */
	public UpdateMatchmakingRequest withMatchmakingCompleteTriggerScript(String matchmakingCompleteTriggerScript) {
		setMatchmakingCompleteTriggerScript(matchmakingCompleteTriggerScript);
		return this;
	}

}