package com.fablwesn.www.viennacalling;

import android.content.res.TypedArray;

/**
 * {@link Location} represents a location to be displayed in the list.
 * It contains:
 * - title text String for the name
 * - user rating float for the rating bar
 * - body text for the description/
 * - res id for the preview image
 * - typed array for image switcher drawables
 * - address text of the location
 * - detailed information text for the location
 */
class Location {
    //title text
    private String mTitleText;
    //user rating
    private float mRating;
    //sub text
    private String mSubText;
    //body text
    private String mBodyText;
    //Image displayed on the left
    private int mImageResourceId;

    //array holding images for the switcher inside the detail activity
    private TypedArray mSwitcherImgIds;
    //string holding address for the location inside the detail activity
    private String mAddressText;
    //string holding details for the location inside the detail activity
    private String mDetailText;


    /**
     * @param titleText     location name
     * @param rating        user rating for the location
     * @param subText       possible secondary text besides the rating
     * @param bodyText      body text describing the location
     * @param imageResId    res id of the preview image for the location
     * @param switcherIds   TypedArray containing all drawables to be displayed in the image switcher
     * @param addressText   address of the location
     * @param detailText    detailed description of the location
     */
    Location(String titleText, float rating, String subText, String bodyText, int imageResId, TypedArray switcherIds, String addressText, String detailText) {
        mTitleText = titleText;
        mRating = rating;
        mSubText = subText;
        mBodyText = bodyText;
        mImageResourceId = imageResId;
        mSwitcherImgIds = switcherIds;
        mAddressText = addressText;
        mDetailText = detailText;
    }

    String getTitleText() {
        return mTitleText;
    }

    float getRating() {
        return mRating;
    }

    String getSubText() {
        return mSubText;
    }

    String getBodyText() {
        return mBodyText;
    }

    int getImageResourceId() {
        return mImageResourceId;
    }

    TypedArray getSwitcherImgIds() {
        return mSwitcherImgIds;
    }

    String getAddressText() {
        return mAddressText;
    }

    String getDetailText() {
        return mDetailText;
    }
}
