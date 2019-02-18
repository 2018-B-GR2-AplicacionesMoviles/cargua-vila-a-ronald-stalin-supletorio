package com.moviles.supletoriomoviles

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.util.Log
import android.widget.Toast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import es.dmoral.toasty.Toasty

class BatallaMapsActivity : AppCompatActivity(), OnMapReadyCallback,GoogleMap.OnMarkerClickListener {
    override fun onMarkerClick(p0: Marker?): Boolean {
        var latLng2: LatLng? = null
        latLng2=p0?.position
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE )
        }
        mMap.isMyLocationEnabled = true
        fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
            if (location != null){
                lastLocation = location
                val currentLatLng = LatLng(location.latitude,location.longitude)
                val dis = distancia(currentLatLng,latLng2!!)
                Log.i("distancia", "distancia: ${dis}")
            }
        }

        Toasty.success(this, "Marcaste ${p0?.title}", Toast.LENGTH_SHORT,true).show()
        return false
    }

    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE=1
    }

    private lateinit var fusedLocationClient : FusedLocationProviderClient
    private lateinit var lastLocation : Location
    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_batalla_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
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


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap


        marcador(mMap)
        mMap.setOnMarkerClickListener(this)
        mMap.uiSettings.isZoomControlsEnabled = true
        setUpMap()


    }

    fun marcador (googleMap: GoogleMap){
        val nMap = googleMap


        val punto1 = LatLng(-0.210375, -78.488618)
        val punto2 = LatLng(-0.2090554,-78.4971474)
        val punto3 = LatLng(-0.2126844,-78.4939424)
        val punto5 = LatLng(-0.2044114,-78.4923674)
        nMap.addMarker(MarkerOptions().position(punto1).title("EPN").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)))
        nMap.addMarker(MarkerOptions().position(punto2).title("Recolectar").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)))
        nMap.addMarker(MarkerOptions().position(punto3).title("Recolectar").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)))
        nMap.addMarker(MarkerOptions().position(punto5).title("Batalla").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)))

    }
    private fun placeMarker(location: LatLng){
        val markerOptions = MarkerOptions().position(location)
        mMap.addMarker(markerOptions)
    }

    private fun setUpMap(){
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE )
            return

        }
        mMap.isMyLocationEnabled = true
        fusedLocationClient.lastLocation.addOnSuccessListener(this) { location ->
            if (location != null){
                lastLocation = location
                val currentLatLng = LatLng(location.latitude,location.longitude)
                placeMarker(currentLatLng)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng,12f))
            }
        }

    }
    fun distancia(miPosicion:LatLng, markerPosition: LatLng):Double{
        var p = 0.017453292519943295;    // Math.PI / 180
        var a = 0.5 - Math.cos((markerPosition.latitude - miPosicion.latitude) * p)/2 +
                Math.cos( miPosicion.latitude* p) * Math.cos(markerPosition.latitude * p) *
                (1 - Math.cos((markerPosition.longitude - miPosicion.longitude) * p))/2;
        return 12742 * Math.asin(Math.sqrt(a))
    }
}


