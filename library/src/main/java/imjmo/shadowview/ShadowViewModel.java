package imjmo.shadowview;

import android.databinding.ObservableField;
import android.databinding.ObservableInt;

/**
 * Created by anthony on 2017. 6. 26..
 */

public class ShadowViewModel {
    public ObservableField<String> textString = new ObservableField<>();
    public ObservableInt shadowMarginTop = new ObservableInt();
    public ObservableInt textMarginTop = new ObservableInt();
    public ObservableInt textMarginBottom = new ObservableInt();
    private ShadowView.OnClickListener mOnClickListener;

    public void setOnClickListener(ShadowView.OnClickListener onCLickListener) {
        mOnClickListener = onCLickListener;
    }

    public void onClickView(ShadowView view) {
        if (null != mOnClickListener) {
            mOnClickListener.onClick(view);
        }
    }
}
