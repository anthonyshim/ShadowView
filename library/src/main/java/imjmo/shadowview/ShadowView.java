package imjmo.shadowview;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import imjmo.shadowview.databinding.LayoutShadowBinding;

/**
 * Created by anthony on 2017. 6. 26..
 */

public class ShadowView extends ConstraintLayout {
    private static final int INVALID_VALUE = -1;
    private LayoutShadowBinding mBinding;
    private boolean mIsFlatMode;
    private int mMargin;

    public ShadowView(Context context) {
        super(context);
        initView(context);
    }

    public ShadowView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
        getAttrs(attrs);
    }

    public ShadowView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
        getAttrs(attrs, defStyleAttr);
    }

    private void initView(Context context) {
        mBinding = LayoutShadowBinding.inflate(LayoutInflater.from(context), this, false);
        addView(mBinding.getRoot());
    }

    private void getAttrs(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ShadowView);
        setTypeArray(typedArray);
    }

    private void getAttrs(AttributeSet attrs, int defStyle) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.ShadowView, defStyle, 0);
        setTypeArray(typedArray);
    }

    private void setTypeArray(TypedArray typedArray) {
        ShadowViewModel viewModel = new ShadowViewModel();
        String textString = typedArray.getString(R.styleable.ShadowView_textString);
        viewModel.textString.set(textString);

        int textSize = Math.round(typedArray.getDimension(R.styleable.ShadowView_textSize, INVALID_VALUE));
        if (INVALID_VALUE != textSize) {
            mBinding.text.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        }

        ColorStateList textColor = typedArray.getColorStateList(R.styleable.ShadowView_textColor);
        if (null != textColor) {
            mBinding.text.setTextColor(textColor);
        }

        Drawable textBackgroundDrawable = typedArray.getDrawable(R.styleable.ShadowView_textBackground);
        if (null != textBackgroundDrawable) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                mBinding.text.setBackground(textBackgroundDrawable);
            } else {
                mBinding.text.setBackgroundDrawable(textBackgroundDrawable);
            }
        }

        mMargin = Math.round(typedArray.getDimension(R.styleable.ShadowView_margin, INVALID_VALUE));
        if (INVALID_VALUE != mMargin) {
            viewModel.shadowMarginTop.set(mMargin);
            viewModel.textMarginBottom.set(mMargin);
        }

        Drawable shadowBackgroundDrawable = typedArray.getDrawable(R.styleable.ShadowView_shadowBackground);
        if (null != shadowBackgroundDrawable) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
                mBinding.viewShadow.setBackground(shadowBackgroundDrawable);
            } else {
                mBinding.viewShadow.setBackgroundDrawable(shadowBackgroundDrawable);
            }
        }

        mIsFlatMode = typedArray.getBoolean(R.styleable.ShadowView_isFlatMode, true);

        typedArray.recycle();
        mBinding.setViewModel(viewModel);
        mBinding.setShadowView(this);
        mBinding.executePendingBindings();
    }

    @Override
    public void childDrawableStateChanged(View child) {
        super.childDrawableStateChanged(child);

        if (child.isPressed()) {
            if (mIsFlatMode) {
                mBinding.viewShadow.setVisibility(View.INVISIBLE);
            } else {
                mBinding.getViewModel().textMarginTop.set(mMargin);
                mBinding.getViewModel().textMarginBottom.set(0);
            }
        } else {
            if (mIsFlatMode) {
                mBinding.viewShadow.setVisibility(View.VISIBLE);
            } else {
                mBinding.getViewModel().textMarginTop.set(0);
                mBinding.getViewModel().textMarginBottom.set(mMargin);
            }
        }
    }

    public void setText(String text) {
        mBinding.getViewModel().textString.set(text);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        mBinding.getViewModel().setOnClickListener(onClickListener);
    }

    public interface OnClickListener {
        /**
         * Called when a ShadowView has been clicked.
         *
         * @param v The ShadowView that was clicked.
         */
        void onClick(ShadowView v);
    }
}
