package com.fablwesn.www.viennacalling;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class CategoryViewPagerAdapter extends FragmentPagerAdapter {

    //stores the mContext
    private Context mContext;

    //constructor
    CategoryViewPagerAdapter(FragmentManager fm, Context mContext) {
        super(fm);
        this.mContext = mContext;
    }

    /**
     * Display the ListView Layout for the first 4 categories
     * Display the ExpandableListViewLayout on the last category
     *
     * @param position viewpager section number
     * @return layout to be displayed in the section
     */
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 4:
                return ExpListFragment.newInstance();
            default:
                return ListFragment.newInstance(position);
        }
    }

    @Override
    public int getCount() {
        // Show 5 total pages.
        return 5;
    }

    /**
     * set the tab titles
     *
     * @param position for which tab to get the title
     * @return string for the tab title
     */
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getResources().getString(R.string.label_category_tab_hotel);
            case 1:
                return mContext.getResources().getString(R.string.label_category_tab_event);
            case 2:
                return mContext.getResources().getString(R.string.label_category_tab_sightseeing);
            case 3:
                return mContext.getResources().getString(R.string.label_category_tab_nightlife);
            case 4:
                return mContext.getResources().getString(R.string.label_category_tab_more);
        }
        return null;
    }
}