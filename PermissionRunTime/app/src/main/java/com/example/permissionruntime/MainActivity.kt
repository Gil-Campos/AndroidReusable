package com.example.permissionruntime

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.permissionruntime.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRequestPermission.setOnClickListener {
            requestRunTimePermissions()
        }
    }

    private fun requestRunTimePermissions() {
        if (ActivityCompat.checkSelfPermission(
                this,
                CAMERA_PERMISSION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            Toast.makeText(
                this,
                "Permission Granted. You can use the CameraX Api which requires the permission",
                Toast.LENGTH_SHORT
            ).show()
        } else if (!ActivityCompat.shouldShowRequestPermissionRationale(this, CAMERA_PERMISSION)) {
            AlertDialog.Builder(this)
                .setTitle("Grant Permission")
                .setMessage("This app requires permisions to function correctly")
                .setCancelable(false)
                .setPositiveButton("Ok") { dialog, _ ->
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(CAMERA_PERMISSION),
                        CAMERA_PERMISSION_REQ_CODE
                    )
                    dialog.dismiss()
                }
                .setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
                .show()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(CAMERA_PERMISSION),
                CAMERA_PERMISSION_REQ_CODE
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == CAMERA_PERMISSION_REQ_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(
                    this,
                    "Permission Granted. You can use the CameraX Api which requires the permission",
                    Toast.LENGTH_SHORT
                ).show()
            } else if (!ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    CAMERA_PERMISSION
                )
            ) {
                AlertDialog.Builder(this)
                    .setTitle("Permission Required")
                    .setMessage(
                        "This feature is unavailable because it requires the camera permission " +
                                "that you have denied. Please allow the camera permission from settings " +
                                "to proceed further"
                    )
                    .setCancelable(false)
                    .setPositiveButton("Ok") { dialog, _ ->
                        val intent = Intent(Settings.ACTION_SETTINGS)
                        startActivity(intent)
                        dialog.dismiss()
                    }
                    .setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
                    .show()
            }
        } else {
            requestRunTimePermissions()
        }
    }


    companion object {
        const val CAMERA_PERMISSION = Manifest.permission.CAMERA
        const val CAMERA_PERMISSION_REQ_CODE = 100
    }
}