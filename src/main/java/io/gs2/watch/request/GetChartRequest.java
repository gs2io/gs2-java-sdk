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

package io.gs2.watch.request;

import org.json.JSONObject;
import java.util.List;
import java.util.Map;
import io.gs2.watch.model.*;
import io.gs2.core.control.Gs2BasicRequest;

/**
 * チャートを取得 のリクエストモデル
 *
 * @author Game Server Services, Inc.
 */
@SuppressWarnings("serial")
public class GetChartRequest extends Gs2BasicRequest<GetChartRequest> {

    /** 指標 */
    private String metrics;

    /**
     * 指標を取得
     *
     * @return チャートを取得
     */
    public String getMetrics() {
        return metrics;
    }

    /**
     * 指標を設定
     *
     * @param metrics チャートを取得
     */
    public void setMetrics(String metrics) {
        this.metrics = metrics;
    }

    /**
     * 指標を設定
     *
     * @param metrics チャートを取得
     * @return this
     */
    public GetChartRequest withMetrics(String metrics) {
        setMetrics(metrics);
        return this;
    }

    /** リソースのGRN */
    private String grn;

    /**
     * リソースのGRNを取得
     *
     * @return チャートを取得
     */
    public String getGrn() {
        return grn;
    }

    /**
     * リソースのGRNを設定
     *
     * @param grn チャートを取得
     */
    public void setGrn(String grn) {
        this.grn = grn;
    }

    /**
     * リソースのGRNを設定
     *
     * @param grn チャートを取得
     * @return this
     */
    public GetChartRequest withGrn(String grn) {
        setGrn(grn);
        return this;
    }

    /** クエリリスト */
    private List<String> queries;

    /**
     * クエリリストを取得
     *
     * @return チャートを取得
     */
    public List<String> getQueries() {
        return queries;
    }

    /**
     * クエリリストを設定
     *
     * @param queries チャートを取得
     */
    public void setQueries(List<String> queries) {
        this.queries = queries;
    }

    /**
     * クエリリストを設定
     *
     * @param queries チャートを取得
     * @return this
     */
    public GetChartRequest withQueries(List<String> queries) {
        setQueries(queries);
        return this;
    }

    /** グルーピング対象 */
    private String by;

    /**
     * グルーピング対象を取得
     *
     * @return チャートを取得
     */
    public String getBy() {
        return by;
    }

    /**
     * グルーピング対象を設定
     *
     * @param by チャートを取得
     */
    public void setBy(String by) {
        this.by = by;
    }

    /**
     * グルーピング対象を設定
     *
     * @param by チャートを取得
     * @return this
     */
    public GetChartRequest withBy(String by) {
        setBy(by);
        return this;
    }

    /** データの取得期間 */
    private String timeframe;

    /**
     * データの取得期間を取得
     *
     * @return チャートを取得
     */
    public String getTimeframe() {
        return timeframe;
    }

    /**
     * データの取得期間を設定
     *
     * @param timeframe チャートを取得
     */
    public void setTimeframe(String timeframe) {
        this.timeframe = timeframe;
    }

    /**
     * データの取得期間を設定
     *
     * @param timeframe チャートを取得
     * @return this
     */
    public GetChartRequest withTimeframe(String timeframe) {
        setTimeframe(timeframe);
        return this;
    }

    /** グラフのサイズ */
    private String size;

    /**
     * グラフのサイズを取得
     *
     * @return チャートを取得
     */
    public String getSize() {
        return size;
    }

    /**
     * グラフのサイズを設定
     *
     * @param size チャートを取得
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * グラフのサイズを設定
     *
     * @param size チャートを取得
     * @return this
     */
    public GetChartRequest withSize(String size) {
        setSize(size);
        return this;
    }

    /** フォーマット */
    private String format;

    /**
     * フォーマットを取得
     *
     * @return チャートを取得
     */
    public String getFormat() {
        return format;
    }

    /**
     * フォーマットを設定
     *
     * @param format チャートを取得
     */
    public void setFormat(String format) {
        this.format = format;
    }

    /**
     * フォーマットを設定
     *
     * @param format チャートを取得
     * @return this
     */
    public GetChartRequest withFormat(String format) {
        setFormat(format);
        return this;
    }

    /** 集計方針 */
    private String aggregator;

    /**
     * 集計方針を取得
     *
     * @return チャートを取得
     */
    public String getAggregator() {
        return aggregator;
    }

    /**
     * 集計方針を設定
     *
     * @param aggregator チャートを取得
     */
    public void setAggregator(String aggregator) {
        this.aggregator = aggregator;
    }

    /**
     * 集計方針を設定
     *
     * @param aggregator チャートを取得
     * @return this
     */
    public GetChartRequest withAggregator(String aggregator) {
        setAggregator(aggregator);
        return this;
    }

    /** スタイル */
    private String style;

    /**
     * スタイルを取得
     *
     * @return チャートを取得
     */
    public String getStyle() {
        return style;
    }

    /**
     * スタイルを設定
     *
     * @param style チャートを取得
     */
    public void setStyle(String style) {
        this.style = style;
    }

    /**
     * スタイルを設定
     *
     * @param style チャートを取得
     * @return this
     */
    public GetChartRequest withStyle(String style) {
        setStyle(style);
        return this;
    }

    /** タイトル */
    private String title;

    /**
     * タイトルを取得
     *
     * @return チャートを取得
     */
    public String getTitle() {
        return title;
    }

    /**
     * タイトルを設定
     *
     * @param title チャートを取得
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * タイトルを設定
     *
     * @param title チャートを取得
     * @return this
     */
    public GetChartRequest withTitle(String title) {
        setTitle(title);
        return this;
    }

}