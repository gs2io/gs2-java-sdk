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
public class CreateMatchmakingRequest extends Gs2BasicRequest<CreateMatchmakingRequest> {

	public static class Constant extends Gs2Matchmaking.Constant {
		public static final String FUNCTION = "CreateMatchmaking";
	}

	/** マッチメイキングの名前 */
	private String name;

	/** マッチメイキングの説明 */
	private String description;

	/** マッチメイキングのサービスクラス */
	private String serviceClass;

	/** マッチメイキングの種類 */
	private String type;

	/** 最大プレイヤー数 */
	private Integer maxPlayer;

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
	 * マッチメイキングの名前を取得
	 *
	 * @return マッチメイキングの名前
	 */
	public String getName() {
		return name;
	}

	/**
	 * マッチメイキングの名前を設定
	 *
	 * @param name マッチメイキングの名前
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * マッチメイキングの名前を設定
	 *
	 * @param name マッチメイキングの名前
	 * @return this
	 */
	public CreateMatchmakingRequest withName(String name) {
		setName(name);
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
	public CreateMatchmakingRequest withDescription(String description) {
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
	public CreateMatchmakingRequest withServiceClass(String serviceClass) {
		setServiceClass(serviceClass);
		return this;
	}

	/**
	 * マッチメイキングの種類を取得
	 *
	 * @return マッチメイキングの種類
	 */
	public String getType() {
		return type;
	}

	/**
	 * マッチメイキングの種類を設定
	 *
	 * @param type マッチメイキングの種類
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * マッチメイキングの種類を設定
	 *
	 * @param type マッチメイキングの種類
	 * @return this
	 */
	public CreateMatchmakingRequest withType(String type) {
		setType(type);
		return this;
	}

	/**
	 * 最大プレイヤー数を取得
	 *
	 * @return 最大プレイヤー数
	 */
	public Integer getMaxPlayer() {
		return maxPlayer;
	}

	/**
	 * 最大プレイヤー数を設定
	 *
	 * @param maxPlayer 最大プレイヤー数
	 */
	public void setMaxPlayer(Integer maxPlayer) {
		this.maxPlayer = maxPlayer;
	}

	/**
	 * 最大プレイヤー数を設定
	 *
	 * @param maxPlayer 最大プレイヤー数
	 * @return this
	 */
	public CreateMatchmakingRequest withMaxPlayer(Integer maxPlayer) {
		setMaxPlayer(maxPlayer);
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
	public CreateMatchmakingRequest withGatheringPoolName(String gatheringPoolName) {
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
	public CreateMatchmakingRequest withCallback(String callback) {
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
	public CreateMatchmakingRequest withNotificationGameName(String notificationGameName) {
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
	public CreateMatchmakingRequest withCreateGatheringTriggerScript(String createGatheringTriggerScript) {
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
	public CreateMatchmakingRequest withCreateGatheringDoneTriggerScript(String createGatheringDoneTriggerScript) {
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
	public CreateMatchmakingRequest withJoinGatheringTriggerScript(String joinGatheringTriggerScript) {
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
	public CreateMatchmakingRequest withJoinGatheringDoneTriggerScript(String joinGatheringDoneTriggerScript) {
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
	public CreateMatchmakingRequest withLeaveGatheringTriggerScript(String leaveGatheringTriggerScript) {
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
	public CreateMatchmakingRequest withLeaveGatheringDoneTriggerScript(String leaveGatheringDoneTriggerScript) {
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
	public CreateMatchmakingRequest withBreakupGatheringTriggerScript(String breakupGatheringTriggerScript) {
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
	public CreateMatchmakingRequest withMatchmakingCompleteTriggerScript(String matchmakingCompleteTriggerScript) {
		setMatchmakingCompleteTriggerScript(matchmakingCompleteTriggerScript);
		return this;
	}

}