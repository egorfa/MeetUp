package com.dtd.thevyshka.fragments.Tabs;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.renderscript.Allocation;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.dtd.thevyshka.R;
import com.dtd.thevyshka.fragments.BaseFragment;

/**
 * Created by Egor on 15.04.2015.
 */
public class TabNews  extends BaseFragment {

    LinearLayout LL;
    ImageView img;

    public static TabNews newInstance() {
        return new TabNews();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_news, container, false);
        mContext = view.getContext();

        img = (ImageView) view.findViewById(R.id.image);


        //if (Integer.valueOf(android.os.Build.VERSION.SDK)>Build.VERSION_CODES.JELLY_BEAN_MR1){
            img.setImageResource(R.drawable.page);
            applyBlur();
        //}
        return view;
    }

    private void applyBlur()
    {
        img.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                img.getViewTreeObserver().removeOnPreDrawListener(this);
                img.buildDrawingCache();

                Bitmap bmp = img.getDrawingCache();
                blur(bmp, img);

                return true;
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private void blur(Bitmap bkg, View view) {
        long startMs = System.currentTimeMillis();

        float radius = 10;

        Bitmap overlay = Bitmap.createBitmap((int) (view.getMeasuredWidth()),
                (int) (view.getMeasuredHeight()), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(overlay);

        canvas.translate(-view.getLeft(), -view.getTop());
        canvas.drawBitmap(bkg, 0, 0, null);

        RenderScript rs = RenderScript.create(getActivity());

        Allocation overlayAlloc = Allocation.createFromBitmap(
                rs, overlay);

        ScriptIntrinsicBlur blur = ScriptIntrinsicBlur.create(
                rs, overlayAlloc.getElement());

        blur.setInput(overlayAlloc);

        blur.setRadius(radius);

        blur.forEach(overlayAlloc);

        overlayAlloc.copyTo(overlay);

        view.setBackground(new BitmapDrawable(
                getResources(), overlay));

        rs.destroy();
    }
}
