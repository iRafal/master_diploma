package com.medvid.andrii.diplomawork.chart.line;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.common.base.Preconditions;
import com.medvid.andrii.diplomawork.R;

import java.util.ArrayList;

public class LineChartFragment extends Fragment
        implements LineChartContract.View, OnChartValueSelectedListener {

    private LineChart mLineChart;
    private LineChartContract.Presenter mPresenter;

    public static LineChartFragment newInstance() {
        return new LineChartFragment();
    }

    public LineChartFragment() {
        // Required empty public constructor
    }

    /**
     * {@link Fragment} Lifecycle methods
     */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_line_chart, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUi(view);

        setTitle(getParameterName());
//      mPresenter.setTrainingSessionDataOnUi(trainingSampleId);
    }

    @Override
    public void setTitle(String title) {
        ((TextView)getActivity().findViewById(R.id.title)).setText(title);
    }

    /**
     *  {@link LineChartContract.View}
     */

    @Override
    public boolean isActive() {
        return isActive();
    }

    @Override
    public void setPresenter(LineChartContract.Presenter presenter) {
        mPresenter = Preconditions.checkNotNull(presenter);
    }

    /**
     *  {@link OnChartValueSelectedListener}
     */

    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }

    /**
     * Private methods
     */
    private void initUi(@NonNull View rootView) {
        Preconditions.checkNotNull(rootView);

        mLineChart = (LineChart) rootView.findViewById(R.id.lineChart);
        initChart();
    }

    private void initChart()    {

        Preconditions.checkNotNull(mLineChart);

        mLineChart.setOnChartValueSelectedListener(this);

        // no description text
        mLineChart.setContentDescription("");
        mLineChart.setNoDataText("You need to provide data for the chart.");

        // enable value highlighting
        mLineChart.setHighlightPerDragEnabled(true);
        mLineChart.setHighlightPerTapEnabled(true);

        // enable touch gestures
        mLineChart.setTouchEnabled(true);

        mLineChart.setDragDecelerationFrictionCoef(0.95f);

        // enable scaling and dragging
        mLineChart.setDragEnabled(true);
        mLineChart.setScaleEnabled(true);
        mLineChart.setDrawGridBackground(false);
        mLineChart.setHighlightPerDragEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        mLineChart.setPinchZoom(true);

        // set an alternative background color
        mLineChart.setBackgroundColor(Color.WHITE);

        // add data
        setData(20, 30);

        mLineChart.animateX(2500);

        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Regular.ttf");

        // get the legend (only possible after setting data)
        Legend l = mLineChart.getLegend();
        // modify the legend ...
        // l.setPosition(LegendPosition.LEFT_OF_CHART);
        l.setForm(Legend.LegendForm.LINE);
        l.setTypeface(tf);
        l.setTextSize(11f);
        l.setTextColor(Color.BLACK);
//        l.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);

        XAxis xAxis = mLineChart.getXAxis();
        xAxis.setTypeface(tf);
        xAxis.setTextSize(12f);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(false);

        xAxis.setSpaceMax(2);
        xAxis.setSpaceMin(1);

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        YAxis leftAxis = mLineChart.getAxisLeft();
        leftAxis.setTypeface(tf);
        leftAxis.setTextColor(ColorTemplate.getHoloBlue());
        leftAxis.setAxisMaximum(200f);
        leftAxis.setDrawGridLines(true);

        YAxis rightAxis = mLineChart.getAxisRight();
        rightAxis.setTypeface(tf);
        rightAxis.setTextColor(ColorTemplate.getHoloBlue());
        rightAxis.setAxisMinimum(200f);
        rightAxis.setDrawGridLines(true);
    }

    private void setData(int count, float range) {

        ArrayList<String> xVals = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            xVals.add((i) + "");
        }

        ArrayList<Entry> yVals1 = new ArrayList<Entry>();

        for (int i = 0; i < count; i++) {
            float mult = range / 2f;
            float val = (float) (Math.random() * mult) + 50;// + (float)
            // ((mult *
            // 0.1) / 10);
            yVals1.add(new Entry(val, i));
        }

        // create a data set and give it a type
        LineDataSet set1 = new LineDataSet(yVals1, "DataSet 1");
        set1.setAxisDependency(YAxis.AxisDependency.LEFT);
        set1.setColor(ColorTemplate.getHoloBlue());
        set1.setCircleColor(Color.WHITE);
        set1.setFillAlpha(65);
        set1.setFillColor(ColorTemplate.getHoloBlue());

        set1.setLineWidth(2.5f);
        set1.setCircleRadius(4.5f);
        set1.setHighLightColor(Color.rgb(244, 117, 117));

        set1.setCircleColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        set1.setDrawCircleHole(true);
        set1.setCircleColorHole(Color.WHITE);

        set1.setDrawValues(true);




        // create a data object with the data sets
        LineData data = new LineData(set1);
        data.setValueTextColor(Color.BLACK);
        data.setValueTextSize(9f);

        // set data
        mLineChart.setData(data);
    }

    /**
     * @return -1 if fail
     */
    private String getParameterName() {
        Intent intent = getActivity().getIntent();

        if (intent != null
                && intent.getExtras() != null
                && intent.getExtras().containsKey(LineChartActivity.KEY_PARAMETER)) {
            return intent.getExtras().getString(LineChartActivity.KEY_PARAMETER);
        }
        return "";
    }
}
