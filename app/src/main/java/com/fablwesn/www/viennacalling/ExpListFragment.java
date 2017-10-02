package com.fablwesn.www.viennacalling;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpListFragment extends Fragment {

    //section number displayed
    private static final String ARG_SECTION_NUMBER = "sectionNumber";
    //map view
    private MapView mMapView;
    // google map
    private GoogleMap mGoogleMap;
    //coordinates of the city Vienna
    private LatLng mViennaCoordinates;
    //containing header text of the expandable listview
    private List<String> mListDataHeader;
    //containing child text of the expandable listview
    private HashMap<String, List<String>> mListDataChild;

    public ExpListFragment() {
    }

    public static ExpListFragment newInstance() {
        ExpListFragment fragment = new ExpListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, 4);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.categories_fragment_explist, container, false);

        //get home button
        View homeButton = rootView.findViewById(R.id.categories_image_home_map_clickable);

        //prepare map
        prepareMap(rootView, savedInstanceState);

        //set listener for the home button to center the map on vienna
        homeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                CameraPosition cameraPosition = new CameraPosition.Builder().target(mViennaCoordinates).zoom(11).build();
                mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });

        // get the list view
        ExpandableListView expListView = rootView.findViewById(R.id.categories_expand_list_parent);

        // preparing list data
        prepareListData();

        //create adapter
        ExpandableListAdapter listAdapter = new ExpandableListAdapter(getContext(), mListDataHeader, mListDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        return rootView;
    }


    /**
     * preparing list data
     */
    private void prepareListData() {
        mListDataHeader = new ArrayList<>();
        mListDataChild = new HashMap<>();

        // Adding group header data
        mListDataHeader.add(getResources().getStringArray(R.array.more_explist_item_numbers)[0]);
        mListDataHeader.add(getResources().getStringArray(R.array.more_explist_item_info)[0]);
        mListDataHeader.add(getResources().getStringArray(R.array.more_explist_item_transport)[0]);
        mListDataHeader.add(getResources().getStringArray(R.array.more_explist_item_holidays)[0]);
        mListDataHeader.add(getResources().getStringArray(R.array.more_explist_item_vacations)[0]);
        mListDataHeader.add(getResources().getStringArray(R.array.more_explist_item_units)[0]);

        // Adding child data for each group
        List<String> emergencyNumbers = new ArrayList<>();
        for (int i = 1; i < getResources().getStringArray(R.array.more_explist_item_numbers).length; i++) {
            emergencyNumbers.add(getResources().getStringArray(R.array.more_explist_item_numbers)[i]);
        }

        List<String> generalInfo = new ArrayList<>();
        for (int i = 1; i < getResources().getStringArray(R.array.more_explist_item_info).length; i++) {
            generalInfo.add(getResources().getStringArray(R.array.more_explist_item_info)[i]);
        }

        List<String> publicTransport = new ArrayList<>();
        publicTransport.add(getResources().getStringArray(R.array.more_explist_item_transport)[1]);

        List<String> publicHolidays = new ArrayList<>();
        for (int i = 1; i < getResources().getStringArray(R.array.more_explist_item_holidays).length; i++) {
            publicHolidays.add(getResources().getStringArray(R.array.more_explist_item_holidays)[i]);
        }


        List<String> schoolVacations = new ArrayList<>();
        for (int i = 1; i < getResources().getStringArray(R.array.more_explist_item_vacations).length; i++) {
            schoolVacations.add(getResources().getStringArray(R.array.more_explist_item_vacations)[i]);
        }

        List<String> unitsMeasure = new ArrayList<>();
        for (int i = 1; i < getResources().getStringArray(R.array.more_explist_item_units).length; i++) {
            unitsMeasure.add(getResources().getStringArray(R.array.more_explist_item_units)[i]);
        }

        //connect header with child
        mListDataChild.put(mListDataHeader.get(0), emergencyNumbers);
        mListDataChild.put(mListDataHeader.get(1), generalInfo);
        mListDataChild.put(mListDataHeader.get(2), publicTransport);
        mListDataChild.put(mListDataHeader.get(3), publicHolidays);
        mListDataChild.put(mListDataHeader.get(4), schoolVacations);
        mListDataChild.put(mListDataHeader.get(5), unitsMeasure);
    }

    /**
     * creating map
     *
     * @param rootView           parent of the map view
     * @param savedInstanceState bundle
     */
    private void prepareMap(View rootView, Bundle savedInstanceState) {
//initialize map
        mMapView = rootView.findViewById(R.id.categories_map_view);
        mMapView.onCreate(savedInstanceState);

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //set map to display vienna and add a marker
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                mGoogleMap = mMap;

                // For dropping a marker at a point on the Map
                mViennaCoordinates = new LatLng(48.2082, 16.3738);
                mGoogleMap.addMarker(new MarkerOptions().position(mViennaCoordinates).title(getString(R.string.categories_map_title)).snippet(getString(R.string.categories_map_snippet)));

                // For zooming automatically to the location of the marker
                CameraPosition cameraPosition = new CameraPosition.Builder().target(mViennaCoordinates).zoom(12).build();
                mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
}
