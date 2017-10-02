package com.fablwesn.www.viennacalling;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title and enter fullscreen mode
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        //set up the click listeners for all category views
        listenerOpenActivity(R.id.main_rel_lay_home_hotels, 0);
        listenerOpenActivity(R.id.main_rel_lay_home_events, 1);
        listenerOpenActivity(R.id.main_rel_lay_home_sightseeing, 2);
        listenerOpenActivity(R.id.main_rel_lay_home_nightlife, 3);
        listenerOpenActivity(R.id.main_rel_lay_home_more, 4);

        //use default font Roboto in case the custom one isn't found
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(getResources().getString(R.string.custom_font_path))
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
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

    /**
     * sets an onClick listener opening the categories activity, displaying the corresponding tab
     *
     * @param viewId   ID of the view to get the listener
     * @param tabIndex ViewPager position to display when loading the categories activity
     */
    private void listenerOpenActivity(final int viewId, final int tabIndex) {

        //find view for the listener
        final View category = findViewById(viewId);

        // Set a click listener on that View
        category.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the category is clicked on.
            @Override
            public void onClick(View view) {
                // Start the new activity with fade in/out animation
                Bundle bundle = ActivityOptionsCompat.makeCustomAnimation(getApplicationContext(),
                        android.R.anim.fade_in, android.R.anim.fade_out).toBundle();

                //save tab number to display before starting the new activity
                Intent categoryIntent = new Intent(MainActivity.this, CategoriesActivity.class);
                categoryIntent.putExtra(getResources().getString(R.string.tab_to_show_key_name), tabIndex);
                startActivity(categoryIntent, bundle);
            }
        });
    }
}
