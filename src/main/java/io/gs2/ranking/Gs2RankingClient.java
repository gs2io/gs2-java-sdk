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

package io.gs2.ranking;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import io.gs2.model.Region;
import io.gs2.util.EncodingUtil;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.gs2.AbstractGs2Client;
import io.gs2.Gs2Constant;
import io.gs2.model.IGs2Credential;
import io.gs2.ranking.control.*;

/**
 * GS2 Ranking API クライアント
 *
 * @author Game Server Services, Inc.
 *
 */
public class Gs2RankingClient extends AbstractGs2Client<Gs2RankingClient> {

	public static String ENDPOINT = "ranking";

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 */
	public Gs2RankingClient(IGs2Credential credential) {
		super(credential);
	}

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 * @param region リージョン
	 */
	public Gs2RankingClient(IGs2Credential credential, Region region) {
		super(credential, region);
	}

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 * @param region リージョン
	 */
	public Gs2RankingClient(IGs2Credential credential, String region) {
		super(credential, region);
	}


	/**
	 * ゲームモードを作成します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public CreateGameModeResult createGameMode(CreateGameModeRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("gameMode", request.getGameMode())
				.put("asc", request.getAsc())
				.put("calcInterval", request.getCalcInterval());
        if(request.getPutScoreTriggerScript() != null) body.put("putScoreTriggerScript", request.getPutScoreTriggerScript());
        if(request.getPutScoreDoneTriggerScript() != null) body.put("putScoreDoneTriggerScript", request.getPutScoreDoneTriggerScript());
        if(request.getCalculateRankingDoneTriggerScript() != null) body.put("calculateRankingDoneTriggerScript", request.getCalculateRankingDoneTriggerScript());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/ranking/" + (request.getRankingTableName() == null || request.getRankingTableName().equals("") ? "null" : request.getRankingTableName()) + "/mode",
				credential,
				ENDPOINT,
				CreateGameModeRequest.Constant.MODULE,
				CreateGameModeRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, CreateGameModeResult.class);

	}


	/**
	 * ゲームモードを削除します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void deleteGameMode(DeleteGameModeRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/ranking/" + (request.getRankingTableName() == null || request.getRankingTableName().equals("") ? "null" : request.getRankingTableName()) + "/mode/" + (request.getGameMode() == null || request.getGameMode().equals("") ? "null" : request.getGameMode()) + "";



		HttpDelete delete = createHttpDelete(
				url,
				credential,
				ENDPOINT,
				DeleteGameModeRequest.Constant.MODULE,
				DeleteGameModeRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            delete.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		doRequest(delete, null);

	}


	/**
	 * ゲームモードの一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeGameModeResult describeGameMode(DescribeGameModeRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/ranking/" + (request.getRankingTableName() == null || request.getRankingTableName().equals("") ? "null" : request.getRankingTableName()) + "/mode";

        List<NameValuePair> queryString = new ArrayList<>();
        if(request.getPageToken() != null) queryString.add(new BasicNameValuePair("pageToken", String.valueOf(request.getPageToken())));
        if(request.getLimit() != null) queryString.add(new BasicNameValuePair("limit", String.valueOf(request.getLimit())));


		if(queryString.size() > 0) {
			url += "?" + URLEncodedUtils.format(queryString, "UTF-8");
		}
		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				DescribeGameModeRequest.Constant.MODULE,
				DescribeGameModeRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        get.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		return doRequest(get, DescribeGameModeResult.class);

	}


	/**
	 * ゲームモードを取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetGameModeResult getGameMode(GetGameModeRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/ranking/" + (request.getRankingTableName() == null || request.getRankingTableName().equals("") ? "null" : request.getRankingTableName()) + "/mode/" + (request.getGameMode() == null || request.getGameMode().equals("") ? "null" : request.getGameMode()) + "";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetGameModeRequest.Constant.MODULE,
				GetGameModeRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetGameModeResult.class);

	}


	/**
	 * ゲームモードの設定を更新します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public UpdateGameModeResult updateGameMode(UpdateGameModeRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("calcInterval", request.getCalcInterval());
        if(request.getPutScoreTriggerScript() != null) body.put("putScoreTriggerScript", request.getPutScoreTriggerScript());
        if(request.getPutScoreDoneTriggerScript() != null) body.put("putScoreDoneTriggerScript", request.getPutScoreDoneTriggerScript());
        if(request.getCalculateRankingDoneTriggerScript() != null) body.put("calculateRankingDoneTriggerScript", request.getCalculateRankingDoneTriggerScript());
		HttpPut put = createHttpPut(
				Gs2Constant.ENDPOINT_HOST + "/ranking/" + (request.getRankingTableName() == null || request.getRankingTableName().equals("") ? "null" : request.getRankingTableName()) + "/mode/" + (request.getGameMode() == null || request.getGameMode().equals("") ? "null" : request.getGameMode()) + "",
				credential,
				ENDPOINT,
				UpdateGameModeRequest.Constant.MODULE,
				UpdateGameModeRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            put.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(put, UpdateGameModeResult.class);

	}


	/**
	 * ランキングテーブルを新規作成します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public CreateRankingTableResult createRankingTable(CreateRankingTableRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("name", request.getName());
        if(request.getDescription() != null) body.put("description", request.getDescription());
        if(request.getPutScoreTriggerScript() != null) body.put("putScoreTriggerScript", request.getPutScoreTriggerScript());
        if(request.getPutScoreDoneTriggerScript() != null) body.put("putScoreDoneTriggerScript", request.getPutScoreDoneTriggerScript());
        if(request.getCalculateRankingDoneTriggerScript() != null) body.put("calculateRankingDoneTriggerScript", request.getCalculateRankingDoneTriggerScript());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/ranking",
				credential,
				ENDPOINT,
				CreateRankingTableRequest.Constant.MODULE,
				CreateRankingTableRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, CreateRankingTableResult.class);

	}


	/**
	 * ランキングテーブルを削除します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 */

	public void deleteRankingTable(DeleteRankingTableRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/ranking/" + (request.getRankingTableName() == null || request.getRankingTableName().equals("") ? "null" : request.getRankingTableName()) + "";



		HttpDelete delete = createHttpDelete(
				url,
				credential,
				ENDPOINT,
				DeleteRankingTableRequest.Constant.MODULE,
				DeleteRankingTableRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            delete.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		doRequest(delete, null);

	}


	/**
	 * ランキングテーブルの一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeRankingTableResult describeRankingTable(DescribeRankingTableRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/ranking";

        List<NameValuePair> queryString = new ArrayList<>();
        if(request.getPageToken() != null) queryString.add(new BasicNameValuePair("pageToken", String.valueOf(request.getPageToken())));
        if(request.getLimit() != null) queryString.add(new BasicNameValuePair("limit", String.valueOf(request.getLimit())));


		if(queryString.size() > 0) {
			url += "?" + URLEncodedUtils.format(queryString, "UTF-8");
		}
		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				DescribeRankingTableRequest.Constant.MODULE,
				DescribeRankingTableRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, DescribeRankingTableResult.class);

	}


	/**
	 * ランキングテーブルを取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetRankingTableResult getRankingTable(GetRankingTableRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/ranking/" + (request.getRankingTableName() == null || request.getRankingTableName().equals("") ? "null" : request.getRankingTableName()) + "";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetRankingTableRequest.Constant.MODULE,
				GetRankingTableRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetRankingTableResult.class);

	}


	/**
	 * ランキングテーブルを更新します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public UpdateRankingTableResult updateRankingTable(UpdateRankingTableRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode();
        if(request.getDescription() != null) body.put("description", request.getDescription());
        if(request.getPutScoreTriggerScript() != null) body.put("putScoreTriggerScript", request.getPutScoreTriggerScript());
        if(request.getPutScoreDoneTriggerScript() != null) body.put("putScoreDoneTriggerScript", request.getPutScoreDoneTriggerScript());
        if(request.getCalculateRankingDoneTriggerScript() != null) body.put("calculateRankingDoneTriggerScript", request.getCalculateRankingDoneTriggerScript());
		HttpPut put = createHttpPut(
				Gs2Constant.ENDPOINT_HOST + "/ranking/" + (request.getRankingTableName() == null || request.getRankingTableName().equals("") ? "null" : request.getRankingTableName()) + "",
				credential,
				ENDPOINT,
				UpdateRankingTableRequest.Constant.MODULE,
				UpdateRankingTableRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            put.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(put, UpdateRankingTableResult.class);

	}


	/**
	 * 指定したスコアを取った時のおおよその順位を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetEstimateRankResult getEstimateRank(GetEstimateRankRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/ranking/" + (request.getRankingTableName() == null || request.getRankingTableName().equals("") ? "null" : request.getRankingTableName()) + "/mode/" + (request.getGameMode() == null || request.getGameMode().equals("") ? "null" : request.getGameMode()) + "/ranking/estimate";

        List<NameValuePair> queryString = new ArrayList<>();
        if(request.getScore() != null) queryString.add(new BasicNameValuePair("score", String.valueOf(request.getScore())));


		if(queryString.size() > 0) {
			url += "?" + URLEncodedUtils.format(queryString, "UTF-8");
		}
		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetEstimateRankRequest.Constant.MODULE,
				GetEstimateRankRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetEstimateRankResult.class);

	}


	/**
	 * 現在の順位を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetMyRankResult getMyRank(GetMyRankRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/ranking/" + (request.getRankingTableName() == null || request.getRankingTableName().equals("") ? "null" : request.getRankingTableName()) + "/mode/" + (request.getGameMode() == null || request.getGameMode().equals("") ? "null" : request.getGameMode()) + "/ranking/rank";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetMyRankRequest.Constant.MODULE,
				GetMyRankRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        get.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		return doRequest(get, GetMyRankResult.class);

	}


	/**
	 * ランキングを取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetRankingResult getRanking(GetRankingRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/ranking/" + (request.getRankingTableName() == null || request.getRankingTableName().equals("") ? "null" : request.getRankingTableName()) + "/mode/" + (request.getGameMode() == null || request.getGameMode().equals("") ? "null" : request.getGameMode()) + "/ranking";

        List<NameValuePair> queryString = new ArrayList<>();
        if(request.getOffset() != null) queryString.add(new BasicNameValuePair("offset", String.valueOf(request.getOffset())));
        if(request.getLimit() != null) queryString.add(new BasicNameValuePair("limit", String.valueOf(request.getLimit())));


		if(queryString.size() > 0) {
			url += "?" + URLEncodedUtils.format(queryString, "UTF-8");
		}
		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetRankingRequest.Constant.MODULE,
				GetRankingRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetRankingResult.class);

	}


	/**
	 * スコアを登録します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public PutScoreResult putScore(PutScoreRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("score", request.getScore());
        if(request.getMeta() != null) body.put("meta", request.getMeta());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/ranking/" + (request.getRankingTableName() == null || request.getRankingTableName().equals("") ? "null" : request.getRankingTableName()) + "/mode/" + (request.getGameMode() == null || request.getGameMode().equals("") ? "null" : request.getGameMode()) + "/ranking",
				credential,
				ENDPOINT,
				PutScoreRequest.Constant.MODULE,
				PutScoreRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }

        post.setHeader("X-GS2-ACCESS-TOKEN", request.getAccessToken());

		return doRequest(post, PutScoreResult.class);

	}


}