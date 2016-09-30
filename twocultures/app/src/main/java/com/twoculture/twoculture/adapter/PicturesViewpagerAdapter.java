package com.twoculture.twoculture.adapter;

import android.content.Context;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.twoculture.twoculture.R;
import com.twoculture.twoculture.models.TopicPhoto;

import java.util.List;

/**
 * Created by songxingchao on 30/09/2016.
 */

public class PicturesViewpagerAdapter extends PagerAdapter {
    private Context mContext;
    private List<TopicPhoto> mPicUrls;

    public PicturesViewpagerAdapter(Context context,List<TopicPhoto> picUrls){
        mContext = context;
        mPicUrls = picUrls;
    }
    @Override
    public int getCount() {
        return mPicUrls.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_viewpager_pictures,container,false);
        ImageView iv_pictures = (ImageView) view.findViewById(R.id.iv_pictures);
        Picasso.with(mContext).load(mPicUrls.get(position).url).placeholder(R.drawable.default_image).into(iv_pictures);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

        ((ViewPager) container).removeView((View) object);
    }
}
