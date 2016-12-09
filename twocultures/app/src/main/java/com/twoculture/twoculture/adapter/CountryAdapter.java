package com.twoculture.twoculture.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.twoculture.twoculture.R;
import com.twoculture.twoculture.models.City;
import com.twoculture.twoculture.models.Country;
import com.twoculture.twoculture.ui.interfaces.CountryClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liangcaihong on 2016/12/9.
 */

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.MyViewHolder> {

    private List<Country> countries;
    private CountryClickListener countryClickListener;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_profile_country_item, parent, false);
        return new CountryAdapter.MyViewHolder(viewItem);

    }

    public void addData(List<Country> countries) {
        this.countries = countries;
        this.notifyDataSetChanged();
    }

    public void addCity(List<City> cities) {
        if (cities != null && cities.size() > 0) {
            this.countries = new ArrayList<>();
            for (City city : cities) {
                this.countries.add(city);
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.districtName.setText(countries.get(position).name);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("test", "onClick: " + countries.get(position).name);
                if (countryClickListener != null) {
                    countryClickListener.onCountryClick(countries.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return countries != null ? countries.size() : 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.district_name_text)
        TextView districtName;


        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    public void setCountryClickListener(CountryClickListener countryClickListener) {
        this.countryClickListener = countryClickListener;
    }
}
