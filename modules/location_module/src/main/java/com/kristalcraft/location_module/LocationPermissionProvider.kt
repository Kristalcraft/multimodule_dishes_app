package com.kristalcraft.location_module

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity

object LocationPermissionProvider {
    fun registerForPermission(context: FragmentActivity,
                              onGranted: () -> Unit,
                              onWhyAsked:() -> Unit): ActivityResultLauncher<String> {
        return context.registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                onGranted.invoke()
            } else {
               onWhyAsked.invoke()
            }
        }
    }

    fun requestForPermissionIfNeeded(
        context: FragmentActivity,
        requestPermissionLauncher: ActivityResultLauncher<String>,
        onGranted: () -> Unit,
        onWhyAsked:() -> Unit){
        when {
            ContextCompat.checkSelfPermission(context.applicationContext, Manifest.permission.ACCESS_COARSE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED -> {
                onGranted.invoke()
            }
            ActivityCompat.shouldShowRequestPermissionRationale(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) -> {
                onWhyAsked.invoke()
            }
            else -> {
                requestPermissionLauncher.launch(
                    Manifest.permission.ACCESS_COARSE_LOCATION)
            }
        }
    }

    fun doIfPermissionsGrantedOnly(context: FragmentActivity,
                                   onGranted: () -> Unit){
        if (ContextCompat.checkSelfPermission(context.applicationContext, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED){
            onGranted.invoke()
        }
    }
}