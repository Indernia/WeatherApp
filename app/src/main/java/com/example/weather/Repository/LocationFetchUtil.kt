package com.example.weather.Repository

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.app.ActivityCompat
import android.Manifest
import android.util.Log
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import okhttp3.internal.wait
import java.lang.Thread.sleep
import kotlin.coroutines.resumeWithException

@Deprecated(
    message = "This is an attempt to use the android Location API, Which somehow kept failing, for no reason"
)
class LocationFetchUtil {



    companion object {
        const val LOCATION_PERMISSION_REQUEST_CODE = 123

        suspend fun fetchLocation(context: Context): Location? {
            val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

            if (ActivityCompat.checkSelfPermission(
                    context,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                Log.d("LocationFetchUtil", "Requesting location permission")
                if (context is Activity) {
                    ActivityCompat.requestPermissions(
                        context as Activity,
                        arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                        LOCATION_PERMISSION_REQUEST_CODE
                    )
                }
            }

            var counter = 0
            while (counter < 5) {
                Log.d("LocationFetchUtil", "Waiting for location")
                if (ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    Log.d("LocationFetchUtil", "Location granted")
                    return kotlinx.coroutines.suspendCancellableCoroutine { continuation ->
                        fusedLocationClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
                            .addOnSuccessListener { location ->
                                Log.d("LocationFetchUtil", "Location fetched: $location")
                                continuation.resume(location) {}
                            }
                            .addOnFailureListener { exception ->
                                Log.d("LocationFetchUtil", "Location fetch failed: $exception")
                                continuation.resumeWithException(exception)
                            }
                            .addOnCanceledListener {
                                Log.d("LocationFetchUtil", "Location fetch canceled")
                            }
                            .addOnCompleteListener {
                                Log.d("LocationFetchUtil", "Location fetch complete")
                            }
                    }
                }
                sleep(1000)
                Log.d("LocationFetchUtil", "Waiting for location: $counter")
                counter++
            }


                if (ActivityCompat.checkSelfPermission(
                        context,
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) == PackageManager.PERMISSION_GRANTED
                ) {
                    return kotlinx.coroutines.suspendCancellableCoroutine { continuation ->
                        fusedLocationClient.lastLocation
                            .addOnSuccessListener { location ->
                                continuation.resume(location) {}
                            }
                            .addOnFailureListener { exception ->
                                continuation.resumeWithException(exception)
                            }
                    }
                } else {
                    return null
                }
            }
        }
}