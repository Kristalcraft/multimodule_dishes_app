package com.kristalcraft.location_module

import android.annotation.SuppressLint
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Build
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.FragmentActivity
import com.google.android.gms.location.LocationServices
import java.io.IOException
import java.util.Locale


object CityProvider {

    fun registerPermsToGetCity(context: FragmentActivity, onCityFoundListener: (String) -> Unit): ActivityResultLauncher<String> {
        return LocationPermissionProvider.registerForPermission(context,{
            getCityWhenPermIsGranted(context, onCityFoundListener)
        },{
            Toast.makeText(context.applicationContext,"Can't show your city name because the permission has denied",
                Toast.LENGTH_SHORT).show()
        })
    }

    private fun getCityFromLatsAndLons(
        context: FragmentActivity,
        lats: Double,
        lons: Double,
        onEndListener: (String) -> Unit
    ) {

        val geocoder = Geocoder(context, Locale.getDefault())
        var addresses: List<Address>? = null
        try {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                geocoder.getFromLocation(lats, lons, 1
                ) { addressesFromListener ->
                    val result = if (addressesFromListener.isNotEmpty()) {
                        addressesFromListener[0].locality?:addressesFromListener[0].countryName?:"null"
                    } else {
                        "failed"
                    }
                    onEndListener.invoke(result)
                }
            } else {
                addresses = geocoder.getFromLocation(lats, lons, 1)
                val result = if (!addresses.isNullOrEmpty()) {
                    addresses[0].locality?:addresses[0].countryName?:"null"
                } else {
                    "failed"
                }
                onEndListener.invoke(result)
            }
        } catch (e: IOException) {
            e.printStackTrace()
            onEndListener.invoke("failed")
        }

    }

    fun getCityIfGranted(context: FragmentActivity,
                         onEndListener: (String) -> Unit){
        LocationPermissionProvider.doIfPermissionsGrantedOnly(context) {
            getCityWhenPermIsGranted(context, onEndListener)
        }
    }

    fun getCity(
        context: FragmentActivity,
        requestPermissionLauncher: ActivityResultLauncher<String>,
        onEndListener: (String) -> Unit
    ) {
        LocationPermissionProvider.requestForPermissionIfNeeded(context,
            requestPermissionLauncher,
        {
            getCityWhenPermIsGranted(context, onEndListener)
        },
        {
            Toast.makeText(context,"Please grant permission to show your city name",Toast.LENGTH_SHORT).show()
        })
    }

    @SuppressLint("MissingPermission")
    private fun getCityWhenPermIsGranted(
        context: FragmentActivity,
        onEndListener: (String) -> Unit
    ) {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                if (location != null) {
                    getCityFromLatsAndLons(
                        context,
                        location.latitude,
                        location.longitude,
                        onEndListener
                    )

                }

            }
    }


}