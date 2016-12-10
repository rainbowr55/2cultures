package com.twoculture.twoculture.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.twoculture.twoculture.R;
import com.twoculture.twoculture.models.Language;
import com.twoculture.twoculture.ui.interfaces.LanguageClickListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by liangcaihong on 2016/12/9.
 */

public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.MyViewHolder> {

    private List<Language> languages;

    private LanguageClickListener languageClickListener;


    public LanguageAdapter(Context context) {
        if (context instanceof LanguageClickListener) {
            this.languageClickListener = (LanguageClickListener) context;
        }


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.my_profile_country_item, parent, false);
        return new LanguageAdapter.MyViewHolder(viewItem);

    }

    public void addData(List<Language> countries) {
        this.languages = countries;
        this.notifyDataSetChanged();
    }



    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.districtName.setText(languages.get(position).name);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(languageClickListener!=null){
                    languageClickListener.onLanguageClick(languages.get(position));
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return languages != null ? languages.size() : 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.district_name_text)
        TextView districtName;


        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

}
