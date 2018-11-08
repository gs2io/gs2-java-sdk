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

package io.gs2.matchmaking.model;

import java.util.List;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * マッチメイキング
 *
 * @author Game Server Services, Inc.
 *
 */
@SuppressWarnings("serial")
@JsonIgnoreProperties(ignoreUnknown=true)
public class Matchmaking implements Serializable {

	/** マッチメイキングGRN */
	private String matchmakingId;

	/** オーナーID */
	private String ownerId;

	/** マッチメイキング名 */
	private String name;

	/** マッチメイキング方式 */
	private String type;

	/** 説明文 */
	private String description;

	/** サービスクラス */
	private String serviceClass;

	/** 最大プレイヤー数 */
	private Integer maxPlayer;

	/** マッチメイキング完了時の通知先URL */
	private String callback;

	/** マッチメイキング完了時に GS2-Realtime と自動的に連携する場合に指定するギャザリングプール名 */
	private String gatheringPoolName;

	/** マッチメイキングの状態変化や完了通知を GS2-InGamePushNotification を使用して通知する場合に指定するゲーム名 */
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

	/** 作成日時(エポック秒) */
	private Integer createAt;

	/** 最終更新日時(エポック秒) */
	private Integer updateAt;


	/**
	 * マッチメイキングGRNを取得
	 *
	 * @return マッチメイキングGRN
	 */
	public String getMatchmakingId() {
		return matchmakingId;
	}

	/**
	 * マッチメイキングGRNを設定
	 *
	 * @param matchmakingId マッチメイキングGRN
	 */
	public void setMatchmakingId(String matchmakingId) {
		this.matchmakingId = matchmakingId;
	}

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
	 * マッチメイキング名を取得
	 *
	 * @return マッチメイキング名
	 */
	public String getName() {
		return name;
	}

	/**
	 * マッチメイキング名を設定
	 *
	 * @param name マッチメイキング名
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * マッチメイキング方式を取得
	 *
	 * @return マッチメイキング方式
	 */
	public String getType() {
		return type;
	}

	/**
	 * マッチメイキング方式を設定
	 *
	 * @param type マッチメイキング方式
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 説明文を取得
	 *
	 * @return 説明文
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 説明文を設定
	 *
	 * @param description 説明文
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * サービスクラスを取得
	 *
	 * @return サービスクラス
	 */
	public String getServiceClass() {
		return serviceClass;
	}

	/**
	 * サービスクラスを設定
	 *
	 * @param serviceClass サービスクラス
	 */
	public void setServiceClass(String serviceClass) {
		this.serviceClass = serviceClass;
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
	 * マッチメイキング完了時の通知先URLを取得
	 *
	 * @return マッチメイキング完了時の通知先URL
	 */
	public String getCallback() {
		return callback;
	}

	/**
	 * マッチメイキング完了時の通知先URLを設定
	 *
	 * @param callback マッチメイキング完了時の通知先URL
	 */
	public void setCallback(String callback) {
		this.callback = callback;
	}

	/**
	 * マッチメイキング完了時に GS2-Realtime と自動的に連携する場合に指定するギャザリングプール名を取得
	 *
	 * @return マッチメイキング完了時に GS2-Realtime と自動的に連携する場合に指定するギャザリングプール名
	 */
	public String getGatheringPoolName() {
		return gatheringPoolName;
	}

	/**
	 * マッチメイキング完了時に GS2-Realtime と自動的に連携する場合に指定するギャザリングプール名を設定
	 *
	 * @param gatheringPoolName マッチメイキング完了時に GS2-Realtime と自動的に連携する場合に指定するギャザリングプール名
	 */
	public void setGatheringPoolName(String gatheringPoolName) {
		this.gatheringPoolName = gatheringPoolName;
	}

	/**
	 * マッチメイキングの状態変化や完了通知を GS2-InGamePushNotification を使用して通知する場合に指定するゲーム名を取得
	 *
	 * @return マッチメイキングの状態変化や完了通知を GS2-InGamePushNotification を使用して通知する場合に指定するゲーム名
	 */
	public String getNotificationGameName() {
		return notificationGameName;
	}

	/**
	 * マッチメイキングの状態変化や完了通知を GS2-InGamePushNotification を使用して通知する場合に指定するゲーム名を設定
	 *
	 * @param notificationGameName マッチメイキングの状態変化や完了通知を GS2-InGamePushNotification を使用して通知する場合に指定するゲーム名
	 */
	public void setNotificationGameName(String notificationGameName) {
		this.notificationGameName = notificationGameName;
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
	 * 作成日時(エポック秒)を取得
	 *
	 * @return 作成日時(エポック秒)
	 */
	public Integer getCreateAt() {
		return createAt;
	}

	/**
	 * 作成日時(エポック秒)を設定
	 *
	 * @param createAt 作成日時(エポック秒)
	 */
	public void setCreateAt(Integer createAt) {
		this.createAt = createAt;
	}

	/**
	 * 最終更新日時(エポック秒)を取得
	 *
	 * @return 最終更新日時(エポック秒)
	 */
	public Integer getUpdateAt() {
		return updateAt;
	}

	/**
	 * 最終更新日時(エポック秒)を設定
	 *
	 * @param updateAt 最終更新日時(エポック秒)
	 */
	public void setUpdateAt(Integer updateAt) {
		this.updateAt = updateAt;
	}

}