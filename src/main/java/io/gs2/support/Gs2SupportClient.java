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

package io.gs2.support;

import java.util.ArrayList;
import java.util.List;

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
import io.gs2.support.control.*;

/**
 * GS2 Support API クライアント
 *
 * @author Game Server Services, Inc.
 *
 */
public class Gs2SupportClient extends AbstractGs2Client<Gs2SupportClient> {

	public static String ENDPOINT = "support";

	/**
	 * コンストラクタ。
	 *
	 * @param credential 認証情報
	 */
	public Gs2SupportClient(IGs2Credential credential) {
		super(credential);
	}


	/**
	 * コメントを新規作成します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public CreateCommentResult createComment(CreateCommentRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("body", request.getBody())
				.put("email", request.getEmail());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/issue/" + (request.getIssueId() == null || request.getIssueId().equals("") ? "null" : request.getIssueId()) + "/comment",
				credential,
				ENDPOINT,
				CreateCommentRequest.Constant.MODULE,
				CreateCommentRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, CreateCommentResult.class);

	}


	/**
	 * ケースを新規作成します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public CreateIssueResult createIssue(CreateIssueRequest request) {

		ObjectNode body = JsonNodeFactory.instance.objectNode()
				.put("category", request.getCategory())
				.put("subCategory", request.getSubCategory())
				.put("description", request.getDescription())
				.put("body", request.getBody())
				.put("email", request.getEmail());

		HttpPost post = createHttpPost(
				Gs2Constant.ENDPOINT_HOST + "/issue",
				credential,
				ENDPOINT,
				CreateIssueRequest.Constant.MODULE,
				CreateIssueRequest.Constant.FUNCTION,
				body.toString());
        if(request.getRequestId() != null) {
            post.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(post, CreateIssueResult.class);

	}


	/**
	 * コメントの一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeCommentResult describeComment(DescribeCommentRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/issue/" + (request.getIssueId() == null || request.getIssueId().equals("") ? "null" : request.getIssueId()) + "/comment";

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
				DescribeCommentRequest.Constant.MODULE,
				DescribeCommentRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, DescribeCommentResult.class);

	}


	/**
	 * ケースの一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeIssueResult describeIssue(DescribeIssueRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/issue";

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
				DescribeIssueRequest.Constant.MODULE,
				DescribeIssueRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, DescribeIssueResult.class);

	}


	/**
	 * カテゴリの一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeIssueCategoryResult describeIssueCategory(DescribeIssueCategoryRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/issue/category";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				DescribeIssueCategoryRequest.Constant.MODULE,
				DescribeIssueCategoryRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, DescribeIssueCategoryResult.class);

	}


	/**
	 * サブカテゴリの一覧を取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public DescribeIssueSubCategoryResult describeIssueSubCategory(DescribeIssueSubCategoryRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/issue/category/" + (request.getCategory() == null || request.getCategory().equals("") ? "null" : request.getCategory()) + "/subCategory";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				DescribeIssueSubCategoryRequest.Constant.MODULE,
				DescribeIssueSubCategoryRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, DescribeIssueSubCategoryResult.class);

	}


	/**
	 * コメントを取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetCommentResult getComment(GetCommentRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/issue/" + (request.getIssueId() == null || request.getIssueId().equals("") ? "null" : request.getIssueId()) + "/comment/" + (request.getCommentId() == null || request.getCommentId().equals("") ? "null" : request.getCommentId()) + "";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetCommentRequest.Constant.MODULE,
				GetCommentRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetCommentResult.class);

	}


	/**
	 * ケースを取得します<br>
	 * <br>
	 *
	 * @param request リクエストパラメータ

	 * @return 結果

	 */

	public GetIssueResult getIssue(GetIssueRequest request) {

	    String url = Gs2Constant.ENDPOINT_HOST + "/issue/" + (request.getIssueId() == null || request.getIssueId().equals("") ? "null" : request.getIssueId()) + "";



		HttpGet get = createHttpGet(
				url,
				credential,
				ENDPOINT,
				GetIssueRequest.Constant.MODULE,
				GetIssueRequest.Constant.FUNCTION);
        if(request.getRequestId() != null) {
            get.setHeader("X-GS2-REQUEST-ID", request.getRequestId());
        }


		return doRequest(get, GetIssueResult.class);

	}


}