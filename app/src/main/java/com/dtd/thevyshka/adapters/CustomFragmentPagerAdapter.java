package com.dtd.thevyshka.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.dtd.thevyshka.fragments.Tabs.TabNews;

/**
 * Created by Egor on 08.04.2015.
 */
public class CustomFragmentPagerAdapter extends FragmentStatePagerAdapter {
    FragmentManager fm;
    private  int NUM_PAGES;

    public CustomFragmentPagerAdapter(FragmentManager fm, int num_pages) {
        super(fm);
        this.fm = fm;
        this.NUM_PAGES = num_pages;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                return TabNews.newInstance();
                //return TabNews.newInstance();
            case 1:
                return TabNews.newInstance();
                //return TabSport.newInstance();
            case 2:
                return TabNews.newInstance();
                //return TabEntertainment.newInstance();
            case 3:
                return TabNews.newInstance();
                //return TabOther.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }

    @Override
    public CharSequence getPageTitle(int position) {

            String str = "";
            switch(position){
                case 0:
                    str = "Новости";
                    break;
                case 1:
                    str = "Спорт";
                    break;
                case 2:
                    str = "Развлечения";
                    break;
                case 3:
                    str = "Остальное";
                    break;
            }
            return str;
    }

}
