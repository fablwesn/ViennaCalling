package com.fablwesn.www.viennacalling;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

class LocationAdapter extends ArrayAdapter<Location> {

    /**
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context activity context
     * @param locations array list
     */
    LocationAdapter(Activity context, ArrayList<Location> locations) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, locations);
    }

    /**
     * Provides a view
     *
     * @param position    The position in the list of data that should be displayed in the
     *                    list item view.
     * @param convertView The recycled view to populate.
     * @param parent      The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.categories_list_item, parent, false);
        }

        //update views inside
        updateViews(listItemView, position);

        // Return the whole list item layout
        // so that it can be shown in the ListView
        return listItemView;
    }

    /**
     * updates the views
     *
     * @param listItemView parent of the views to change
     * @param position     location index used inside the array
     */
    private void updateViews(View listItemView, int position) {
        // Get the {@link Location} object located at this position in the list
        Location currentLocation = getItem(position);


        //set title
        TextView titleText = listItemView.findViewById(R.id.categories_list_item_title_text);
        titleText.setText(currentLocation.getTitleText());

        //set rating
        RatingBar ratingBar = listItemView.findViewById(R.id.categories_list_item_ratingbar);
        ratingBar.setRating(currentLocation.getRating());

        //set body text
        TextView subTitleText = listItemView.findViewById(R.id.categories_list_item_body_text);
        subTitleText.setText(currentLocation.getBodyText());

        //set subtext
        TextView starText = listItemView.findViewById(R.id.categories_list_item_sub_text);
        starText.setText(currentLocation.getSubText());

        //set image
        ImageView iconImage = listItemView.findViewById(R.id.categories_list_item_image);
        iconImage.setImageResource(currentLocation.getImageResourceId());
    }
}
