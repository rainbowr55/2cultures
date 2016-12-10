package com.twoculture.twoculture.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.twoculture.twoculture.R;
import com.twoculture.twoculture.adapter.CountryAdapter;
import com.twoculture.twoculture.models.City;
import com.twoculture.twoculture.models.Country;
import com.twoculture.twoculture.models.District;
import com.twoculture.twoculture.ui.interfaces.CountryClickListener;

import java.util.ArrayList;

import butterknife.BindView;

public class CountryListActivity extends BaseActivity implements CountryClickListener {

    public static final int REQUEST_CODE = 100;
    private CountryAdapter countryAdapter;
    @BindView(R.id.my_profile_countries_list)
    RecyclerView recyclerView;

    private int countryId = -1;
    private String countryName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ArrayList<City> cities = (ArrayList<City>) getIntent().getSerializableExtra("city");
        ArrayList<Country> countries = (ArrayList<Country>) getIntent().getSerializableExtra("country");
        initView();
        initData(countries, cities);
    }


    @Override
    protected int getLayoutRes() {
        return R.layout.activity_country_list;
    }

    private void initView() {
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        countryAdapter = new CountryAdapter(this);
        recyclerView.setAdapter(countryAdapter);

    }

    private void initData(ArrayList<Country> countries, ArrayList<City> cities) {
        if (countries != null) {
            countryAdapter.addData(countries);
        }
        if (cities != null) {
            countryAdapter.addCity(cities);
        }

    }

    @Override
    protected String getTitleName() {
        return this.getString(R.string.district);
    }


    @Override
    public void onCountryClick(Country country) {
        if (country.cities != null && country.cities.size() > 0) {
            countryId = country.id;
            countryName = country.name;
            Intent intent = new Intent(this, CountryListActivity.class);
            intent.putExtra("city", country.cities);
            startActivityForResult(intent, REQUEST_CODE);
        } else {
            Intent intent = new Intent();
            intent.putExtra("cityId", country.id);
            intent.putExtra("cityName", country.name);
            this.setResult(RESULT_OK, intent);
            this.finish();

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    int cityId = data.getIntExtra("cityId", -1);
                    String cityName = data.getStringExtra("cityName");
                    District district = new District();
                    district.cityId = cityId;
                    district.cityName = cityName;
                    district.countryId = countryId;
                    district.countryName = countryName;
                    Intent intent = new Intent();
                    intent.putExtra("district", district);
                    setResult(RESULT_OK, intent);
                    this.finish();
                }
            }
        }
    }
}
