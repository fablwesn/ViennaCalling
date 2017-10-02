package com.fablwesn.www.viennacalling;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class DetailActivity extends Activity {

    //current resId[] index of the drawable being displayed in the imageswitcher
    int mImageDisplayed = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title and set fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_detail);

        //use default font Roboto in case the custom one isn't found
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(getResources().getString(R.string.custom_font_path))
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        //get info of opened location
        Intent intent = getIntent();
        final String title = intent.getStringExtra(getResources().getString(R.string.detail_title));
        String description = intent.getStringExtra(getResources().getString(R.string.detail_desc));
        String address = intent.getStringExtra(getResources().getString(R.string.detail_address));
        String details = intent.getStringExtra(getResources().getString(R.string.detail_info));
        final int[] switcherIds = intent.getIntArrayExtra(getResources().getString(R.string.detail_switcher));

        //look if the user has already rated the location, if so set the rating bar appropriately
        //also set a listener to save any ratings made by the user
        updateRatingAndListener(title);

        //update views
        updateViews(title, description, address, details);

        //set an onclick listener to the home button creating an up navigation button
        setHomeButtonListener();

        //fill the image switcher and set listeners for the next and previous image button
        prepareSwitcherGallery(switcherIds);
    }

    /**
     * displays correct rating and saves the new value, if the user changes the rating
     *
     * @param title location displayed, used as the sharedPref key-name
     */
    private void updateRatingAndListener(final String title) {
        //get prefs
        final SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        //find bar
        RatingBar ratingBar = findViewById(R.id.detail_rating_bar);
        //set rating corresponding to the saved value, if there is none set it to 0
        ratingBar.setRating(sharedPref.getFloat(title, 0));
        //save new value if the user changes it
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                sharedPref.edit().putFloat(title, rating).apply();
            }
        });
    }

    /**
     * updates corresponding views
     *
     * @param title       location title String
     * @param description location description String
     * @param address     location address String
     * @param details     location details String
     */
    private void updateViews(String title, String description, String address, String details) {
        //set the title
        TextView titleText = findViewById(R.id.detail_title_text);
        titleText.setText(title);

        //set description
        TextView descriptionText = findViewById(R.id.detail_short_descr);
        descriptionText.setText(description);

        //set the address
        TextView addressText = findViewById(R.id.detail_address);
        addressText.setText(address);

        //set the info
        TextView infoText = findViewById(R.id.detail_body_text);
        infoText.setText(details);
    }

    /**
     * sets the listener for the home button, which acts similar to the up navigation
     */
    private void setHomeButtonListener() {
        //find button
        findViewById(R.id.detail_home_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the new activity with fade in/out animation
                Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(),
                        android.R.anim.fade_in, android.R.anim.fade_out).toBundle();

                //delete activity history before returning home, simulating up navigation
                Intent homeIntent = new Intent(DetailActivity.this, MainActivity.class);
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(homeIntent, bundle);
            }
        });

    }

    /**
     * readies the ImageSwitcher and sets a listener for the 2 buttons to scroll through the images inside, with animations
     *
     * @param switcherIds   drawable res ids to use for the location's images inside the switcher
     */
    private void prepareSwitcherGallery(final int[] switcherIds) {
        //find view
        final ImageSwitcher gallery = findViewById(R.id.detail_image_switcher);
        //factoring
        gallery.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                //create new image and set properties
                ImageView locationImage = new ImageView(DetailActivity.this);
                locationImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
                locationImage.setAdjustViewBounds(true);

                //set layout params
                ViewGroup.LayoutParams params = new ImageSwitcher.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

                locationImage.setLayoutParams(params);
                return locationImage;
            }
        });
        // set the image to be displayed, depending on the order in the array and the counters value
        gallery.setImageResource(switcherIds[mImageDisplayed]);

        // manages the toggling between the images, when the button is pressed.
        //if the counter is bigger than the length of the array, set the counter to zero
        //to start from the first image again - .length -1 because arrays start at 0.
        //if not, increase the counter by one, displaying the next image in order
        findViewById(R.id.detail_prev_image).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (mImageDisplayed == 0) {
                    mImageDisplayed = switcherIds.length - 1;
                    gallery.setImageResource(switcherIds[mImageDisplayed]);
                } else {
                    gallery.setImageResource(switcherIds[--mImageDisplayed]);
                }
            }
        });

        findViewById(R.id.detail_next_image).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                if (mImageDisplayed == switcherIds.length - 1) {
                    mImageDisplayed = 0;
                    gallery.setImageResource(switcherIds[mImageDisplayed]);
                } else {
                    gallery.setImageResource(switcherIds[++mImageDisplayed]);
                }
            }
        });


        // animation for the switching between images
        Animation animationOut = AnimationUtils.loadAnimation(this, android.R.anim.fade_out);
        Animation animationIn = AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        gallery.setOutAnimation(animationOut);
        gallery.setInAnimation(animationIn);
    }

    /**
     * Inject custom fonts into context
     *
     * @param newBase context
     */
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
