package com.example.mike.mta_testing;


import android.content.res.AssetManager;
        import android.support.v4.app.FragmentActivity;
        import android.os.Bundle;
        import android.widget.Toast;



import com.google.android.gms.maps.CameraUpdateFactory;
        import com.google.android.gms.maps.GoogleMap;
        import com.google.android.gms.maps.OnMapReadyCallback;
        import com.google.android.gms.maps.SupportMapFragment;


        import android.content.Intent;

        import android.view.View;

        import com.google.android.gms.maps.model.BitmapDescriptorFactory;

        import com.google.android.gms.maps.model.LatLng;
        import com.google.android.gms.maps.model.Marker;
        import com.google.android.gms.maps.model.MarkerOptions;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;


import java.util.ArrayList;
        import java.util.List;






public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private List<subwayStation> stationList = new ArrayList<>();


    private void changeActivity(View v) {
        Intent i = new Intent(getApplicationContext(), MapsActivity.class);
        startActivity(i);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        AssetManager assetManager = getAssets();

        String line = "";
        try {
            InputStream inputStream = assetManager.open("data.txt");
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            in.readLine();

            while ((line = in.readLine()) != null) {
                String word = line.trim();
                String[] tokens = word.split(",");

                subwayStation s = new subwayStation(tokens);
                stationList.add(s);

            }

        } catch (IOException e) {

            Toast toast = Toast.makeText(this, "Could not Load Database!", Toast.LENGTH_LONG);
            toast.show();
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Toast.makeText(this, "Choose a Borough.", Toast.LENGTH_LONG).show();

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

            // Add a marker move the camera

            for (subwayStation s : stationList) {

                LatLng ll = new LatLng(s.getLatitude(), s.getLongitude());
                mMap.addMarker(new MarkerOptions()
                        .position(ll)
                        .title(s.getStopName())
                        .draggable(false))
                        .setIcon(BitmapDescriptorFactory.fromAsset("subway.png")
                        );

                mMap.moveCamera(CameraUpdateFactory.newLatLng(ll));
            }

            mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(40.7128, -74.0060)));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(11.5f));
            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    System.out.println(marker.getTitle());
                    for (subwayStation s : stationList) {

                        if (marker.getTitle().equals(s.getStopName())) {
                            System.out.println(marker.getTitle());
                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            i.putExtra("markerTitle", marker.getTitle());
                            i.setFlags(Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP);
                            startActivity(i);
                            return true;
                        }

                    }
                    return false;
                }
            });

        }


    }



