package com.twoculture.twoculture.ui.fragment;

import android.app.Dialog;
import android.content.Context;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.View;

import com.twoculture.twoculture.R;
import com.twoculture.twoculture.ui.interfaces.GenderClickListener;

/**
 * Created by liangcaihong on 2016/12/9.
 */

public class GenderDialogFragment extends BottomSheetDialogFragment implements View.OnClickListener {


    private Context mContext;
    private GenderClickListener genderClickListener;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }


    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_my_profile_gender, null);
        if (mContext instanceof GenderClickListener) {
            genderClickListener = (GenderClickListener) mContext;
        }
        view.findViewById(R.id.tv_female).setOnClickListener(this);
        view.findViewById(R.id.tv_male).setOnClickListener(this);
        dialog.setContentView(view);
    }


    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.tv_male) {
            if (genderClickListener != null) {
                genderClickListener.onGenderClick(1);
            }
        } else if (id == R.id.tv_female) {

            if (genderClickListener != null) {
                genderClickListener.onGenderClick(0);
            }
        }

    }
}
