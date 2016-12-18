package com.twoculture.twoculture.ui;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.twoculture.base.blur.PicassoTransformation;
import com.twoculture.base.widget.RoundedImageView;
import com.twoculture.base.widget.RoundedTransformationBuilder;
import com.twoculture.twoculture.R;
import com.twoculture.twoculture.models.CareerAndEducation;
import com.twoculture.twoculture.models.Identity;
import com.twoculture.twoculture.models.LifeStyle;
import com.twoculture.twoculture.models.Physical;
import com.twoculture.twoculture.models.SingleProfile;

import butterknife.BindView;

public class SingleProfileActivity extends BaseActivity {

    public static final String EXTRA_SINGLE_KEY = "single_profile";

    @BindView(R.id.iv_user_detail_bg)
    ImageView ivUserDetailBg;
    @BindView(R.id.iv_user_head)
    RoundedImageView ivUserHead;
    @BindView(R.id.tv_user_age)
    TextView tvUserAge;
    @BindView(R.id.tv_user_smoking_habits)
    TextView tvUserSmokingHabits;
    @BindView(R.id.tv_user_drinking_habits)
    TextView tvUserDrinkingHabits;
    @BindView(R.id.tv_user_diet)
    TextView tvUserDiet;
    @BindView(R.id.tv_user_height)
    TextView tvUserHeight;
    @BindView(R.id.tv_user_body_type)
    TextView tvUserBodyType;
    @BindView(R.id.tv_user_eye_colour)
    TextView tvUserEyeColour;
    @BindView(R.id.tv_user_hair_colour)
    TextView tvUserHairColour;
    @BindView(R.id.tv_user_current_status)
    TextView tvUserCurrentStatus;
    @BindView(R.id.tv_user_ethnic_background)
    TextView tvUserEthnicBackground;
    @BindView(R.id.tv_user_star_sign)
    TextView tvUserStarSign;
    @BindView(R.id.tv_user_education_level)
    TextView tvUserEducationLevel;
    @BindView(R.id.tv_user_career_fiels)
    TextView tvUserCareerFiels;
    @BindView(R.id.tv_user_have_children)
    TextView tvUserHaveChildren;
    @BindView(R.id.tv_user_my_children)
    TextView tvUserMyChildren;
    @BindView(R.id.tv_user_want_children)
    TextView tvUserWantChildren;
    @BindView(R.id.tv_user_religion)
    TextView tvUserReligion;
    @BindView(R.id.tv_user_intention)
    TextView tvUserIntention;

    private SingleProfile singleProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        if (getIntent().getExtras() != null) {
            singleProfile = (SingleProfile) getIntent().getExtras().get(EXTRA_SINGLE_KEY);
        }
        if (singleProfile == null) return;
        Transformation transformation = new RoundedTransformationBuilder()
                .borderColor(Color.WHITE)
                .borderWidthDp(1)
                .cornerRadiusDp(60)
                .oval(false)
                .build();
        Picasso picasso = Picasso.with(this);
        picasso.load(singleProfile.header_image_url)
                .fit()
                .transform(transformation)
                .placeholder(R.drawable.default_gravatar).fit().centerCrop().into(ivUserHead);

        picasso.load(singleProfile.header_image_url)
                .fit()
                .noFade()
                .config(Bitmap.Config.ARGB_8888)
                .transform(new PicassoTransformation(this, UserInfoDetailActivity.BLUR_RADIUS))
                .placeholder(R.mipmap.user_detail_bg)
                .into(ivUserDetailBg);
        tvUserAge.setText(singleProfile.age + "");
        tvUserHaveChildren.setText(singleProfile.have_children + "");
        tvUserMyChildren.setText(singleProfile.my_children + "");
        //tvUserWantChildren.setText(singleProfile.);
        tvUserReligion.setText(singleProfile.religion_str);
        tvUserIntention.setText(singleProfile.intention_str);
        initLifeStyle(singleProfile.life_style);
        initPhysical(singleProfile.physical);
        initIdentity(singleProfile.identity);
        initCareerAndEducation(singleProfile.career_and_education);
    }

    private void initCareerAndEducation(CareerAndEducation career_and_education) {
        if (career_and_education == null) return;
        tvUserEducationLevel.setText(career_and_education.education_level_str);
        tvUserCareerFiels.setText(career_and_education.job_field_str);
    }

    private void initIdentity(Identity identity) {
        if (identity == null) return;
        tvUserCurrentStatus.setText(identity.relationship_status_str);
        tvUserEthnicBackground.setText(identity.ethnic_str);
        tvUserStarSign.setText(identity.star_sign_str);
    }

    private void initPhysical(Physical physical) {
        if (physical == null) return;
        tvUserBodyType.setText(physical.body_type_str);
        tvUserHeight.setText(physical.height + "");
        tvUserEyeColour.setText(physical.eye_color_str);
        tvUserHairColour.setText(physical.hair_color_str);
    }

    private void initLifeStyle(LifeStyle life_style) {
        if (life_style == null) {
            return;
        }
        tvUserSmokingHabits.setText(life_style.smoking_habits_str);
        tvUserDrinkingHabits.setText(life_style.drinking_habits_str);
        tvUserDiet.setText(life_style.dietary_preferences_str);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_sigle_profile;
    }
}
