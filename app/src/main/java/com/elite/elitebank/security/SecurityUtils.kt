package com.elite.elitebank.security

import android.content.Context
import android.content.pm.ApplicationInfo
import android.hardware.camera2.CameraManager
import android.os.Build
import android.os.Debug.isDebuggerConnected
import android.provider.Settings
import android.util.Log
import java.io.File

object SecurityUtils {

    private var isCameraBusy = false
    private var cameraManager: CameraManager? = null
    private var availabilityCallback: CameraManager.AvailabilityCallback? = null
    private val overlayDetection = OverlayDetection()

    fun isDeveloperOptionsEnabled(context: Context): Boolean {
        return try {
            Settings.Global.getInt(
                context.contentResolver,
                Settings.Global.DEVELOPMENT_SETTINGS_ENABLED
            ) == 1
        } catch (e: Exception) {
            Log.e("DeveloperOptions", "Error checking Developer Options", e)
            false
        }
    }

    fun isDebugEnabled(context: Context): Boolean {
        // we can check debug by  2 ways
        val isDebuggable = (context.applicationInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE) != 0
        val isDebuggerConnected = isDebuggerConnected()
        return isDebuggerConnected
    }


    fun isRooted(): Boolean {
        // Check known binary root file paths
        val paths = listOf(
            "/data/local", "/data/local/bin", "/data/local/xbin", "/sbin",
            "/su/bin", "/system", "/system/app", "/system/bin",
            "/system/bin/.ext", "/system/bin/failsafe", "/system/sd/xbin",
            "/system/usr/we-need-root", "/system/xbin"
        )
        val binaries = listOf(
            "magisk", "su", "su2", "superuser.apk", "Superuser.apk",
            "su-backup", ".su", "busybox", "busybox.apk"
        )
        for (path in paths) {
            for (bin in binaries) {
                val filePath = File("$path/$bin")
                if (filePath.exists()) {
                    Log.e("RootDetection", "Smartphone rooted ($filePath)")
                    return true
                }
            }
        }
        // Check for the existence of the "su" binary
        try {
            if (Runtime.getRuntime().exec(arrayOf("/system/xbin/which", "su"))
                    .inputStream.bufferedReader().readLine() != null
            ) {
                return true
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        // Check if AOSP ROM was signed with release keys else means isRooted
        if (android.os.Build.TAGS?.contains("test-keys") == true) {
            return true
        }
        Log.d("RootDetection", "Smartphone not rooted")
        return false
    }

    //  use  function  to check  application  load from  one  src  google  play
    private const val AUTHORIZED_APP_VENDOR = "com.android.vending" // Google Play
    private const val TAG = "InstallCheck"
    private fun getInstallerPackageName(context: Context): String? {
        return try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                // Android 11+ (API 30+)
                context.packageManager
                    .getInstallSourceInfo(context.packageName)
                    .installingPackageName
            } else {
                // Android <= 10 (API 29 and below)
                @Suppress("DEPRECATION")
                context.packageManager.getInstallerPackageName(context.packageName)
            }
        } catch (e: Exception) {
            Log.e(TAG, "Failed to get installer package", e)
            null
        }
    }

    fun isAppInstallFromGooglePlay(context: Context): Boolean {
        val installer = getInstallerPackageName(context)
        Log.d(TAG, "Package installer is: $installer")

        return if (installer == null || installer != AUTHORIZED_APP_VENDOR) {
            Log.e(TAG, "App is sideloaded or from unknown source")
            true
        } else {
            false
        }
    }

    fun initCameraDetector(context: Context) {
        if (cameraManager != null) return // already initialized

        cameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
        availabilityCallback = object : CameraManager.AvailabilityCallback() {
            override fun onCameraUnavailable(cameraId: String) {
                super.onCameraUnavailable(cameraId)
                isCameraBusy = true
                Log.d("SecurityUtils", "Camera $cameraId is busy")
            }

            override fun onCameraAvailable(cameraId: String) {
                super.onCameraAvailable(cameraId)
                isCameraBusy = false
                Log.d("SecurityUtils", "Camera $cameraId is available")
            }
        }

        try {
            cameraManager?.registerAvailabilityCallback(availabilityCallback!!, null)
        } catch (e: Exception) {
            Log.e("SecurityUtils", "Failed to register camera callback", e)
        }
    }

    /**
     * Call this to release resources when no longer needed.
     */
    fun release() {
        try {
            if (cameraManager != null && availabilityCallback != null) {
                cameraManager?.unregisterAvailabilityCallback(availabilityCallback!!)
            }
        } catch (e: Exception) {
            Log.e("SecurityUtils", "Failed to unregister camera callback", e)
        }
        cameraManager = null
        availabilityCallback = null
        isCameraBusy = false
    }

    /**
     * Returns true if camera is currently in use (busy) by any app.
     */
    fun isCameraInUse(): Boolean {
        return isCameraBusy
    }

    suspend fun startOverlayDetection(): Boolean {
        return overlayDetection.detectOverlay()
    }





}