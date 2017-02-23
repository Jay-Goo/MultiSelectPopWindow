package com.jaygoo.selector;

import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * ================================================
 * 作    者：JayGoo
 * 版    本：1.1.0
 * 创建日期：2017/2/22
 * 描    述:
 * ================================================
 */
public class MultiSelectPopWindow {

    private PopupWindow mPopupWindow;
    private MultiSelectListAdapter adapter;
    private TextView selectedNumberTV;
    private TextView cancelBtn;
    private TextView confirmBtn;
    private TextView titleTV;
    private CheckBox selectAllBtn;
    private OnConfirmClickListener mOnConfirmListener;
    private ArrayList<Integer> mIndexList;
    private Builder mBuilder;

    public interface OnConfirmClickListener{
        void onClick(ArrayList<Integer> indexList, ArrayList<String> selectedList);
    }

    static public class Builder{
        private Activity mActivity;
        private ArrayList<String> choiceNameList = new ArrayList<>();
        private String title;
        private String confirmText;
        private String cancelText;
        private boolean isOutsideTouchable;
        private View.OnClickListener mOnCancelListener;
        private OnConfirmClickListener mOnConfirmListener;
        private int mConfirmTextColor;
        private int mCancelTextColor;
        private int mTitleTextColor;

        public Builder(Activity mActivity){
            this.mActivity = mActivity;
        }
        public Builder setNameArray(ArrayList<String> list){
            this.choiceNameList = list;
            return this;
        }

        /**
         * set title
         * @param title
         * @return
         */
        public Builder setTitle(String title){
            this.title = title;
            return this;
        }

        /**
         * set confirm button text
         * @param str
         * @return
         */
        public Builder setConfirm(String str){
            this.confirmText = str;
            return this;
        }

        /**
         * set cacel button text
         * @param str
         * @return
         */
        public Builder setCancel(String str){
            this.cancelText = str;
            return this;
        }

        /**
         * set title's text color
         * @param color
         * @return
         */
        public Builder setTitleTextColor(int color){
            this.mTitleTextColor = color;
            return this;
        }

        /**
         * set confirm button's text color
         * @param color
         * @return
         */
        public Builder setConfirmTextColor(int color){
            this.mConfirmTextColor = color;
            return this;
        }

        /**
         * set cancel button's text color
         * @param color
         * @return
         */
        public Builder setCancelTextColor(int color){
            this.mCancelTextColor = color;
            return this;
        }

        /**
         * set if can touchable if your finger touch outside
         * @param isOutsideTouchable
         * @return
         */
        public Builder setOutsideTouchable(boolean isOutsideTouchable){
            this.isOutsideTouchable = isOutsideTouchable;
            return this;
        }

        public MultiSelectPopWindow build(){
            return new MultiSelectPopWindow(this);
        }

        public Builder setConfirmListener(OnConfirmClickListener listener){
            this.mOnConfirmListener = listener;
            return this;
        }

        public Builder setCancelListener(View.OnClickListener listener){
            this.mOnCancelListener = listener;
            return this;
        }


    }
    private MultiSelectPopWindow(final Builder builder){
        mBuilder = builder;

        //init PopWindow
        View popview = View.inflate(builder.mActivity, R.layout.multi_select_list_popwindow, null);
        mPopupWindow = new PopupWindow(popview, WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setAnimationStyle(R.style.popwindow_anim_style);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));
        mPopupWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        mPopupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setOutsideTouchable(builder.isOutsideTouchable);

        initViews(mPopupWindow.getContentView());

        initListener();

    }

    /**
     * init listener
     */
    private void initListener() {
        this.mOnConfirmListener = mBuilder.mOnConfirmListener;

        // change the background's color
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1f);
            }
        });

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBuilder.mOnConfirmListener != null && mIndexList != null){
                    ArrayList<String> stringList = new ArrayList<>();
                    for (int i = 0; i<mIndexList.size(); i++) {
                        stringList.add(mBuilder.choiceNameList.get(mIndexList.get(i)));
                    }
                    mOnConfirmListener.onClick(mIndexList,stringList);
                }
                dismiss();
            }
        });

        // select all or cancel all
        selectAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectAllBtn.isChecked()){
                    adapter.selectAll();
                }else {
                    adapter.cancelAll();
                }
            }
        });


        if (mBuilder.mOnCancelListener != null){
            cancelBtn.setOnClickListener(mBuilder.mOnCancelListener);
        }

        cancelBtn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                dismiss();
                return false;
            }
        });


        // change the badge number
        adapter.setOnSelectChangeListener(new MultiSelectListAdapter.OnSelectChangeListener() {

            @Override
            public void onChanged(ArrayList<Integer> indexList, int selectdNumber) {

                if (selectedNumberTV == null)return;
                if (selectdNumber > 0) {
                    selectedNumberTV.setText(selectdNumber + "");
                    selectedNumberTV.setVisibility(View.VISIBLE);
                }else {
                    selectedNumberTV.setVisibility(View.GONE);
                }

                mIndexList = indexList;

            }
        });

        adapter.setOnSelectAllListener(new MultiSelectListAdapter.OnSelectAllListener() {
            @Override
            public void onChanged(boolean isSelectedAll) {
                selectAllBtn.setChecked(isSelectedAll);
            }
        });
    }

    private void initViews(View root) {
        titleTV = (TextView) root.findViewById(R.id.title);
        cancelBtn = (TextView) root.findViewById(R.id.cancelBtn);
        confirmBtn = (TextView) root.findViewById(R.id.confirmBtn);
        selectedNumberTV = (TextView) root.findViewById(R.id.selectedNumber);
        selectAllBtn = (CheckBox) root.findViewById(R.id.selectAllBtn);

        setText(titleTV,mBuilder.title);
        setText(cancelBtn,mBuilder.cancelText);
        setText(confirmBtn,mBuilder.confirmText);

        setTextColor(titleTV,mBuilder.mTitleTextColor);
        setTextColor(cancelBtn,mBuilder.mCancelTextColor);
        setTextColor(confirmBtn,mBuilder.mConfirmTextColor);

        RecyclerView recyclerView = (RecyclerView) mPopupWindow.getContentView().findViewById(R.id.mRecycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(mBuilder.mActivity.getApplication()));
        adapter = new MultiSelectListAdapter(mBuilder.choiceNameList);
        recyclerView.setAdapter(adapter);


    }

    private void setText(TextView tv, String str){
        if (tv != null && str != null){
            tv.setText(str);
        }
    }

    private void setTextColor(TextView tv, int color){
        if (tv != null && color != 0){
            tv.setTextColor(color);
        }
    }

    public void dismiss() {
        if (mPopupWindow != null){
            mPopupWindow.dismiss();
        }
    }

    /**
     * parent is the popwindow show location
     * @param parent
     */
    public void show(View parent){
        if (mPopupWindow != null){
            backgroundAlpha(0.8f);
            mPopupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        }
    }

    /**
     * set background alpha
     * @param alpha
     */
    public void backgroundAlpha(float alpha) {
        try {
            WindowManager.LayoutParams lp = mBuilder.mActivity.getWindow().getAttributes();
            lp.alpha = alpha; //0.0-1.0
            mBuilder.mActivity.getWindow().setAttributes(lp);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
