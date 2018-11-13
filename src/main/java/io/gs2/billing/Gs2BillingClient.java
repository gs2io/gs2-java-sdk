package io.gs2.billing;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import io.gs2.AbstractGs2Client;
import io.gs2.Gs2Constant;
import io.gs2.billing.control.DescribeBillingRequest;
import io.gs2.billing.control.DescribeBillingResult;
import io.gs2.model.IGs2Credential;

/**
 * GS2 Billing API クライアント
 * 
 * @author Game Server Services, Inc.
 *
 */
public class Gs2BillingClient extends AbstractGs2Client<Gs2BillingClient> {

	public static String ENDPOINT = "billing";

	/**
	 * コンストラクタ。
	 * 
	 * @param credential 認証情報
	 */
	public Gs2BillingClient(IGs2Credential credential) {
		super(credential);
	}

	/**
	 * 請求情報一覧を取得。
	 * 
	 * @param request リクエストパラメータ
	 * @return 請求情報一覧
	 */
	public DescribeBillingResult describeBilling(DescribeBillingRequest request) {
		String url = Gs2Constant.ENDPOINT_HOST + "/bill/" + request.getYear() + "/" + request.getMonth();
		List<NameValuePair> queryString = new ArrayList<>();
		if(request.getLimit() != null) queryString.add(new BasicNameValuePair("limit", String.valueOf(request.getLimit())));
		if(request.getPageToken() != null) queryString.add(new BasicNameValuePair("pageToken", request.getPageToken()));
		if(queryString.size() > 0) {
			url += "?" + URLEncodedUtils.format(queryString, "UTF-8");
		}
		HttpGet get = createHttpGet(
				url, 
				credential, 
				ENDPOINT,
				DescribeBillingRequest.Constant.MODULE, 
				DescribeBillingRequest.Constant.FUNCTION);
		return doRequest(get, DescribeBillingResult.class);
	}

}
