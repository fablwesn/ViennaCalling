package com.fablwesn.www.viennacalling;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ListFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "sectionNumber";

    TypedArray mImageResrIdsTyped;

    public ListFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number and stores the number
     */
    public static ListFragment newInstance(int sectionNumber) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.categories_fragment_list, container, false);
        //get the list view
        ListView listView = rootView.findViewById(R.id.categories_listview_parent);

        //find out which section page is being displayed
        final int sectionNumber = getArguments().getInt(ARG_SECTION_NUMBER);
        //create new location object
        final ArrayList<Location> locations = new ArrayList<>();

        //fill array with location data
        createLocations(sectionNumber, locations);

        //if there is a TypedArray, clear it
        if (mImageResrIdsTyped != null)
            mImageResrIdsTyped.recycle();

        //set adapter
        LocationAdapter adapter = new LocationAdapter(this.getActivity(), locations);
        listView.setAdapter(adapter);

        // Set a click listener opening a detailed view for the location item
        setOnItemClickListener(listView, locations);

        return rootView;
    }

    /**
     * fills the locations array with
     * -title text
     * -rating value
     * -subtext
     * -body text
     * -preview img res id
     * -drawables for the image switcher (typed array)
     * -address text
     * -detailed info text
     *
     * @param sectionNumber the tab being displayed in the viewpager
     * @param locations     Array holding all the list entries
     */
    private void createLocations(int sectionNumber, final ArrayList<Location> locations) {
        switch (sectionNumber) {
            //hotels
            case 0:
                locations.add(new Location(getResources().getStringArray(R.array.location_hotel_titles)[0], 5.0f, getResources().getStringArray(R.array.location_hotel_subtext)[0], getResources().getStringArray(R.array.location_hotel_bodytext)[0], R.drawable.img_list_hotels_hyatt, getResources().obtainTypedArray(R.array.img_id_detail_hotel_hyatt), getResources().getStringArray(R.array.location_hotel_adresses)[0], getResources().getStringArray(R.array.location_hotel_desc)[0]));
                locations.add(new Location(getResources().getStringArray(R.array.location_hotel_titles)[1], 4.5f, getResources().getStringArray(R.array.location_hotel_subtext)[1], getResources().getStringArray(R.array.location_hotel_bodytext)[1], R.drawable.img_list_hotels_imperial, getResources().obtainTypedArray(R.array.img_id_detail_hotel_imperial), getResources().getStringArray(R.array.location_hotel_adresses)[1], getResources().getStringArray(R.array.location_hotel_desc)[1]));
                locations.add(new Location(getResources().getStringArray(R.array.location_hotel_titles)[2], 4.5f, getResources().getStringArray(R.array.location_hotel_subtext)[2], getResources().getStringArray(R.array.location_hotel_bodytext)[2], R.drawable.img_list_hotels_coburg, getResources().obtainTypedArray(R.array.img_id_detail_hotel_coburg), getResources().getStringArray(R.array.location_hotel_adresses)[2], getResources().getStringArray(R.array.location_hotel_desc)[2]));
                locations.add(new Location(getResources().getStringArray(R.array.location_hotel_titles)[3], 4.0f, getResources().getStringArray(R.array.location_hotel_subtext)[3], getResources().getStringArray(R.array.location_hotel_bodytext)[3], R.drawable.img_list_hotels_hilton, getResources().obtainTypedArray(R.array.img_id_detail_hotel_hilton), getResources().getStringArray(R.array.location_hotel_adresses)[3], getResources().getStringArray(R.array.location_hotel_desc)[3]));
                locations.add(new Location(getResources().getStringArray(R.array.location_hotel_titles)[4], 4.0f, getResources().getStringArray(R.array.location_hotel_subtext)[4], getResources().getStringArray(R.array.location_hotel_bodytext)[4], R.drawable.img_list_hotels_arcotel, getResources().obtainTypedArray(R.array.img_id_detail_hotel_arcotel), getResources().getStringArray(R.array.location_hotel_adresses)[4], getResources().getStringArray(R.array.location_hotel_desc)[4]));
                locations.add(new Location(getResources().getStringArray(R.array.location_hotel_titles)[5], 4.0f, getResources().getStringArray(R.array.location_hotel_subtext)[5], getResources().getStringArray(R.array.location_hotel_bodytext)[5], R.drawable.img_list_hotels_starinn, getResources().obtainTypedArray(R.array.img_id_detail_hotel_starinn), getResources().getStringArray(R.array.location_hotel_adresses)[5], getResources().getStringArray(R.array.location_hotel_desc)[5]));
                break;
            //events
            case 1:
                locations.add(new Location(getResources().getStringArray(R.array.location_event_titles)[0], 5.0f, getResources().getStringArray(R.array.location_event_subtext)[0], getResources().getStringArray(R.array.location_event_bodytext)[0], R.drawable.img_list_events_danube, getResources().obtainTypedArray(R.array.img_id_detail_event_danube), getResources().getStringArray(R.array.location_event_adresses)[0], getResources().getStringArray(R.array.location_event_desc)[0]));
                locations.add(new Location(getResources().getStringArray(R.array.location_event_titles)[1], 5.0f, getResources().getStringArray(R.array.location_event_subtext)[1], getResources().getStringArray(R.array.location_event_bodytext)[1], R.drawable.img_list_events_opera, getResources().obtainTypedArray(R.array.img_id_detail_event_opera), getResources().getStringArray(R.array.location_event_adresses)[1], getResources().getStringArray(R.array.location_event_desc)[1]));
                locations.add(new Location(getResources().getStringArray(R.array.location_event_titles)[2], 5.0f, getResources().getStringArray(R.array.location_event_subtext)[2], getResources().getStringArray(R.array.location_event_bodytext)[2], R.drawable.img_list_events_newyear, getResources().obtainTypedArray(R.array.img_id_detail_event_newyear), getResources().getStringArray(R.array.location_event_adresses)[2], getResources().getStringArray(R.array.location_event_desc)[2]));
                locations.add(new Location(getResources().getStringArray(R.array.location_event_titles)[3], 5.0f, getResources().getStringArray(R.array.location_event_subtext)[3], getResources().getStringArray(R.array.location_event_bodytext)[3], R.drawable.img_list_events_lifeball, getResources().obtainTypedArray(R.array.img_id_detail_event_life), getResources().getStringArray(R.array.location_event_adresses)[3], getResources().getStringArray(R.array.location_event_desc)[3]));
                locations.add(new Location(getResources().getStringArray(R.array.location_event_titles)[4], 5.0f, getResources().getStringArray(R.array.location_event_subtext)[4], getResources().getStringArray(R.array.location_event_bodytext)[4], R.drawable.img_list_events_christmas, getResources().obtainTypedArray(R.array.img_id_detail_event_christmas), getResources().getStringArray(R.array.location_event_adresses)[4], getResources().getStringArray(R.array.location_event_desc)[4]));
                locations.add(new Location(getResources().getStringArray(R.array.location_event_titles)[5], 5.0f, getResources().getStringArray(R.array.location_event_subtext)[5], getResources().getStringArray(R.array.location_event_bodytext)[5], R.drawable.img_list_events_dance, getResources().obtainTypedArray(R.array.img_id_detail_event_danube), getResources().getStringArray(R.array.location_event_adresses)[5], getResources().getStringArray(R.array.location_event_desc)[5]));
                break;
            //sights
            case 2:
                locations.add(new Location(getResources().getStringArray(R.array.location_sight_titles)[0], 4.5f, getResources().getStringArray(R.array.location_sight_subtext)[0], getResources().getStringArray(R.array.location_sight_bodytext)[0], R.drawable.img_list_sights_schoenbrunn, getResources().obtainTypedArray(R.array.img_id_detail_sight_schoenbrunn), getResources().getStringArray(R.array.location_sight_adresses)[0], getResources().getStringArray(R.array.location_sight_desc)[0]));
                locations.add(new Location(getResources().getStringArray(R.array.location_sight_titles)[1], 4.5f, getResources().getStringArray(R.array.location_sight_subtext)[1], getResources().getStringArray(R.array.location_sight_bodytext)[1], R.drawable.img_list_sights_cathedral, getResources().obtainTypedArray(R.array.img_id_detail_sight_cathedral), getResources().getStringArray(R.array.location_sight_adresses)[1], getResources().getStringArray(R.array.location_sight_desc)[1]));
                locations.add(new Location(getResources().getStringArray(R.array.location_sight_titles)[2], 5.0f, getResources().getStringArray(R.array.location_sight_subtext)[2], getResources().getStringArray(R.array.location_sight_bodytext)[2], R.drawable.img_list_sights_zoo, getResources().obtainTypedArray(R.array.img_id_detail_sight_zoo), getResources().getStringArray(R.array.location_sight_adresses)[2], getResources().getStringArray(R.array.location_sight_desc)[2]));
                locations.add(new Location(getResources().getStringArray(R.array.location_sight_titles)[3], 4.5f, getResources().getStringArray(R.array.location_sight_subtext)[3], getResources().getStringArray(R.array.location_sight_bodytext)[3], R.drawable.img_list_sights_prater, getResources().obtainTypedArray(R.array.img_id_detail_sight_prater), getResources().getStringArray(R.array.location_sight_adresses)[3], getResources().getStringArray(R.array.location_sight_desc)[3]));
                locations.add(new Location(getResources().getStringArray(R.array.location_sight_titles)[4], 4.5f, getResources().getStringArray(R.array.location_sight_subtext)[4], getResources().getStringArray(R.array.location_sight_bodytext)[4], R.drawable.img_list_sights_museum, getResources().obtainTypedArray(R.array.img_id_detail_sight_museum), getResources().getStringArray(R.array.location_sight_adresses)[4], getResources().getStringArray(R.array.location_sight_desc)[4]));
                locations.add(new Location(getResources().getStringArray(R.array.location_sight_titles)[5], 4.0f, getResources().getStringArray(R.array.location_sight_subtext)[5], getResources().getStringArray(R.array.location_sight_bodytext)[5], R.drawable.img_list_sights_sea, getResources().obtainTypedArray(R.array.img_id_detail_sight_sea), getResources().getStringArray(R.array.location_sight_adresses)[5], getResources().getStringArray(R.array.location_sight_desc)[5]));
                break;
            //nightlife
            case 3:
                locations.add(new Location(getResources().getStringArray(R.array.location_nightlife_titles)[0], 3.5f, getResources().getStringArray(R.array.location_nightlife_subtext)[0], getResources().getStringArray(R.array.location_nightlife_bodytext)[0], R.drawable.img_list_night_dome, getResources().obtainTypedArray(R.array.img_id_detail_nightlife_dome), getResources().getStringArray(R.array.location_nightlife_adresses)[0], getResources().getStringArray(R.array.location_nightlife_desc)[0]));
                locations.add(new Location(getResources().getStringArray(R.array.location_nightlife_titles)[1], 4.0f, getResources().getStringArray(R.array.location_nightlife_subtext)[1], getResources().getStringArray(R.array.location_nightlife_bodytext)[1], R.drawable.img_list_night_onyx, getResources().obtainTypedArray(R.array.img_id_detail_nightlife_onyx), getResources().getStringArray(R.array.location_nightlife_adresses)[1], getResources().getStringArray(R.array.location_nightlife_desc)[1]));
                locations.add(new Location(getResources().getStringArray(R.array.location_nightlife_titles)[2], 4.0f, getResources().getStringArray(R.array.location_nightlife_subtext)[2], getResources().getStringArray(R.array.location_nightlife_bodytext)[2], R.drawable.img_list_night_shack, getResources().obtainTypedArray(R.array.img_id_detail_nightlife_shack), getResources().getStringArray(R.array.location_nightlife_adresses)[2], getResources().getStringArray(R.array.location_nightlife_desc)[2]));
                locations.add(new Location(getResources().getStringArray(R.array.location_nightlife_titles)[3], 4.0f, getResources().getStringArray(R.array.location_nightlife_subtext)[3], getResources().getStringArray(R.array.location_nightlife_bodytext)[3], R.drawable.img_list_night_cabaret, getResources().obtainTypedArray(R.array.img_id_detail_nightlife_fledermaus), getResources().getStringArray(R.array.location_nightlife_adresses)[3], getResources().getStringArray(R.array.location_nightlife_desc)[3]));
                locations.add(new Location(getResources().getStringArray(R.array.location_nightlife_titles)[4], 4.0f, getResources().getStringArray(R.array.location_nightlife_subtext)[4], getResources().getStringArray(R.array.location_nightlife_bodytext)[4], R.drawable.img_list_night_ship, getResources().obtainTypedArray(R.array.img_id_detail_nightlife_badeschiff), getResources().getStringArray(R.array.location_nightlife_adresses)[4], getResources().getStringArray(R.array.location_nightlife_desc)[4]));
                break;
            default:
                break;
        }
    }

    /**
     * starts a new activity on click and saves variables needed for display
     *
     * @param list      list view for the listener
     * @param locations object to get data from
     */
    private void setOnItemClickListener(ListView list, final ArrayList<Location> locations) {
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Location location = locations.get(position);

                mImageResrIdsTyped = location.getSwitcherImgIds();
                int[] imageIdsSwitcher = new int[mImageResrIdsTyped.length()];
                for (int i = 0; i < mImageResrIdsTyped.length(); i++)
                    imageIdsSwitcher[i] = mImageResrIdsTyped.getResourceId(i, 0);

                //open next activity and store the name of the location to be displayed
                Intent detailIntent = new Intent(getContext(), DetailActivity.class);
                detailIntent.putExtra(getResources().getString(R.string.detail_title), location.getTitleText());
                detailIntent.putExtra(getResources().getString(R.string.detail_switcher), imageIdsSwitcher);
                detailIntent.putExtra(getResources().getString(R.string.detail_desc), location.getBodyText());
                detailIntent.putExtra(getResources().getString(R.string.detail_address), location.getAddressText());
                detailIntent.putExtra(getResources().getString(R.string.detail_info), location.getDetailText());
                startActivity(detailIntent);
            }
        });
    }
}
