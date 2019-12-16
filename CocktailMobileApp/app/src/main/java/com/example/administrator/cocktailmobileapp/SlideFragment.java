package com.example.administrator.cocktailmobileapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SlideFragment extends Fragment {
    private static final String TAG = "SlideView";

    private TextView mName;
    private TextView mTitle;
    private TextView mDesciption;
    private Button mButton;
    private ImageView mCardImage;
    private RelativeLayout mBackground;

    public static SlideFragment createSlideFragment (String name, String title, String description, int imageID , GradientDrawable.Orientation gradientAngle, int gradientStartColor, int gradientEndColor, int position) {
        SlideFragment slideFragment = new SlideFragment();

        Bundle bundle = new Bundle();

        bundle.putString("name", name);
        bundle.putString("title", title);
        bundle.putString("description", description);
        bundle.putInt("imageID", imageID);
        bundle.putInt("gradientStartColor", gradientStartColor);
        bundle.putInt("gradientEndColor", gradientEndColor);
        bundle.putInt("pos", position);

        slideFragment.setArguments(bundle);

        return slideFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_slideview, container, false);

        mName = view.findViewById(R.id.name);
        mTitle = view.findViewById(R.id.title);
        mDesciption = view.findViewById(R.id.description);
        mButton = view.findViewById(R.id.button);
        mCardImage = view.findViewById(R.id.card_image);
        mBackground = view.findViewById(R.id.background);

        final Bundle bundle = getArguments();

        mName.setText(bundle.getString("name"));
        mTitle.setText(bundle.getString("title"));
        mDesciption.setText(bundle.getString("description"));
        mCardImage.setImageDrawable(getRoundedBitmap(bundle.getInt("imageID")));

        GradientDrawable gradientDrawable = new GradientDrawable(GradientDrawable.Orientation.BL_TR, new int[] {getResources().getColor(bundle.getInt("gradientStartColor")), getResources().getColor(bundle.getInt("gradientEndColor"))});
        gradientDrawable.setCornerRadius(0f);
        mBackground.setBackgroundDrawable(gradientDrawable);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomDialog.getInstance(getContext()).show(bundle.getInt("pos"), bundle.getString("name"));
            }
        });

        return view;
    }

    //이미지의 모서리 라운딩 처리
    private RoundedBitmapDrawable getRoundedBitmap(int imageId) {
        Bitmap bitmap = BitmapFactory.decodeResource( getResources(), imageId);
        RoundedBitmapDrawable roundedDrawable = RoundedBitmapDrawableFactory.create(getResources(), bitmap);

        roundedDrawable.setCircular(true);
        roundedDrawable.setCornerRadius(64);
        roundedDrawable.setAntiAlias(true);

        return roundedDrawable;
    }

//    RoundedBitmapDrawable drawable = RoundedBitmapDrawableFactory.create(context.getResources(), source);
//        drawable.setCircular(true);
//        drawable.setCornerRadius(source.getWidth() / 2.0f);
//        return drawable.getBitmap();
//
//    Bitmap source = ((BitmapDrawable) mProfileImage.getDrawable()).getBitmap();
//    RoundedBitmapDrawable drawable =
//            RoundedBitmapDrawableFactory.create(getContext().getResources(), source);
//            drawable.setCircular(true);
//            drawable.setCornerRadius(Math.max(source.getWidth() / 2.0f, source.getHeight() / 2.0f));
//            mProfileImage.setImageDrawable(drawable);
}