package com.fablwesn.www.viennacalling;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class CategoriesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // remove title and enter fullscreen mode
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_categories);

        //use default font Roboto in case the custom one isn't found
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(getResources().getString(R.string.custom_font_path))
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        // Create the adapter
        CategoryViewPagerAdapter categoryViewPagerAdapter = new CategoryViewPagerAdapter(getSupportFragmentManager(), getApplicationContext());

        // Set up the ViewPager with the sections adapter.
        ViewPager viewPager = findViewById(R.id.categories_vpager);
        viewPager.setAdapter(categoryViewPagerAdapter);

        //get which tab to show, depending on the button pressed inside the previous activity
        final Intent intent = getIntent();
        int page = intent.getIntExtra(getResources().getString(R.string.tab_to_show_key_name), 0);
        //display the required tab
        viewPager.setCurrentItem(page);

        //find and setup the tabs
        TabLayout tabLayout = findViewById(R.id.categories_tablay_vpager);
        tabLayout.setupWithViewPager(viewPager);
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
     * change animation when pressing the back button
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
