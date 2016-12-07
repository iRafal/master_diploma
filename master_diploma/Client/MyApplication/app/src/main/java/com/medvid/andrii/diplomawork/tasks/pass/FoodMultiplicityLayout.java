package com.medvid.andrii.diplomawork.tasks.pass;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.medvid.andrii.diplomawork.R;

public class FoodMultiplicityLayout extends LinearLayout implements View.OnClickListener {

    private Button plusButton;
    private TextView valueTextView;
    private Button minusButton;

    private OnItemValueChangedListener mListener;

    private int value = 0;

    public FoodMultiplicityLayout(Context context) {
        super(context);
    }

    public FoodMultiplicityLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FoodMultiplicityLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initView();
    }

    private void initView() {
        plusButton = (Button) findViewById(R.id.plusButton);
        valueTextView = (TextView) findViewById(R.id.valueTextView);
        minusButton = (Button) findViewById(R.id.minusButton);

        plusButton.setOnClickListener(this);
        minusButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.plusButton:
                plus();
                valueTextView.setText(Integer.toString(value));
                if (mListener != null) {
                    mListener.onIncreasedValue(getValue());
                }
                break;
            case R.id.minusButton:
                minus();
                valueTextView.setText(Integer.toString(value));
                if (mListener != null) {
                    mListener.onDecreasedValue(getValue());
                }
                break;
        }
    }

    private void minus() {
        if (value > 0) {
            value--;
        }
    }

    private void plus() {
        value++;
    }

    public int getValue() {
        return value;
    }

    public void setListener(OnItemValueChangedListener listener) {
        mListener = listener;
    }

    public interface OnItemValueChangedListener {

        void onDecreasedValue(int value);

        void onIncreasedValue(int value);
    }
}