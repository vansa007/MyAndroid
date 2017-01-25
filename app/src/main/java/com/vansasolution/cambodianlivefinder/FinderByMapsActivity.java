package com.vansasolution.cambodianlivefinder;

import android.*;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.MapsInitializer;
import com.vansasolution.cambodianlivefinder.model.ResD;
import com.vansasolution.cambodianlivefinder.model.Restaruant;
import com.vansasolution.cambodianlivefinder.service.APIService;
import com.vansasolution.cambodianlivefinder.service.ServiceGenerator;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FinderByMapsActivity extends FragmentActivity implements OnMapReadyCallback {


    private GoogleMap mMap;
    //APIService service;
    private final static int MY_PERMISSION_FINE_LOCATION = 101;
    public ArrayList<Restaruant.DATA> resData=new ArrayList<>();
    APIService service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finder_by_maps);


        service= ServiceGenerator.createService(APIService.class);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void getRestaruantLocation(GoogleMap googleMap) {
        mMap=googleMap;
        Call<Restaruant> call =service.getRestaruantLocation();
        call.enqueue(new Callback<Restaruant>() {
            @Override
            public void onResponse(Call<Restaruant> call, Response<Restaruant> response) {
                Log.e("rtk", "Success"+response.body().toString());
                Restaruant rest = response.body();
                for(int i=0; i<rest.getDATA().size(); i++){
                    resData.add(rest.getDATA().get(i));
                }
                for(int i=0; i<resData.size(); i++){
                    double lat = Double.parseDouble(resData.get(i).getLatitude());
                    double lon = Double.parseDouble(resData.get(i).getLongitude());
                    String name = resData.get(i).getRest_name();
                    LatLng locationRes = new LatLng(lat, lon);
                    mMap.addMarker(new MarkerOptions().position(locationRes).title(name).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
                    Log.d("rest",resData.get(i).getRest_name());
                }

                Log.d("vansa",resData.size()+"");
            }

            @Override
            public void onFailure(Call<Restaruant> call, Throwable t) {
                Log.e("rtk", "Failer");
                t.printStackTrace();
            }
        });


//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
////                List<Restaruant> rest = response.body();
////                for(int i=0; i<rest.size(); i++){
////                    resData.add(rest.get(i));
////                    Log.d("data1", resData.get(i).getMESSAGE());
////                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Restaruant>> call, Throwable t) {
//                Log.d("data1", "error");
//                t.printStackTrace();
//            }
//        });
        Toast.makeText(this, "loading...", Toast.LENGTH_SHORT).show();
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
        getRestaruantLocation(googleMap);
        String location = new String("Phnom Penh");
        String location_description = new String("The bigest city of Cambodia");

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                Intent detailHouse = new Intent(FinderByMapsActivity.this, DetailHouse.class);
                startActivity(detailHouse);
            }
        });

        //vansa step over king

        // Add a marker in Sydney and move the camera
        LatLng phnompenh = new LatLng(11.5739627, 104.8789607);
        mMap.addMarker(new MarkerOptions().position(phnompenh).title("Marker in Phnom Penh"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(phnompenh, 15));

        LatLng kosign = new LatLng(11.5756812, 104.8849474);
        mMap.addMarker(new MarkerOptions().position(kosign).title("Marker in Kosign").snippet("").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));

        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        }else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_FINE_LOCATION);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSION_FINE_LOCATION:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        mMap.setMyLocationEnabled(true);
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Requried permission", Toast.LENGTH_SHORT).show();
                    finish();
                }
        }
    }
}


