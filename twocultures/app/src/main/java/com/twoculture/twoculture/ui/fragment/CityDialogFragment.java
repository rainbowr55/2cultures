package com.twoculture.twoculture.ui.fragment;

import android.app.Dialog;
import android.content.Context;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.twoculture.twoculture.R;
import com.twoculture.twoculture.adapter.CountryAdapter;
import com.twoculture.twoculture.models.City;

import java.util.List;

/**
 * Created by liangcaihong on 2016/12/9.
 */

public class CityDialogFragment extends BottomSheetDialogFragment {


    private CountryAdapter countryAdapter;
    RecyclerView recyclerView;
    protected Dialog loadingDialog;
    private Context mContext;

    private List<City> cities;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View view = getActivity().getLayoutInflater().inflate(R.layout.my_profile_countries, null);
        recyclerView = (RecyclerView) view.findViewById(R.id.my_profile_countries_list);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        countryAdapter = new CountryAdapter(mContext);
        recyclerView.setAdapter(countryAdapter);
        dialog.setContentView(view);
    }

    public void setCityList(List<City> cities) {
        if (countryAdapter != null) {
            countryAdapter.addCity(cities);
        }

    }

}
