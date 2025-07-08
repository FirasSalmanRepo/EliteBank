package com.elite.elitebank.security

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import java.io.File
import java.io.FileInputStream
import java.io.InputStream
import java.net.NetworkInterface

object EmulatorDetection {
    private val QEMU_DRIVERS = arrayOf("goldfish")
    private val GENY_FILES = arrayOf(
        "/dev/socket/genyd",
        "/dev/socket/baseband_genyd"
    )
    private val PIPES = arrayOf(
        "/dev/socket/qemud",
        "/dev/qemu_pipe"
    )
    private val X86_FILES = arrayOf(
        "ueventd.android_x86.rc",
        "x86.prop",
        "ueventd.ttVM_x86.rc",
        "init.ttVM_x86.rc",
        "fstab.ttVM_x86",
        "fstab.vbox86",
        "init.vbox86.rc",
        "ueventd.vbox86.rc"
    )
    private val ANDY_FILES = arrayOf(
        "fstab.andy",
        "ueventd.andy.rc"
    )
    private val NOX_FILES = arrayOf(
        "fstab.nox",
        "init.nox.rc",
        "ueventd.nox.rc"
    )

    fun isRunningInEmulator(context: Context): Boolean {
        return when {
            checkBasic() -> true
            checkAdvanced() -> true
            checkPackageName(context) -> true
            checkMacAddress() -> true
            else -> false
        }
    }

    /*   this  function  for  check device properities  like
       (Build.BRAND, Build.MODEL, Build.HARDWARE)To match them with known values indicating simulators
       generic
       sdk_google_phone_x86
       vbox86p
       ranchu, goldfish
       If two or more conditions are met, the result is considered a simulator.*/
    private fun checkBasic(): Boolean {
        var rating = 0
        if (Build.PRODUCT == "sdk_x86_64" || Build.PRODUCT == "sdk_google_phone_x86" || Build.PRODUCT == "sdk_google_phone_x86_64" || Build.PRODUCT == "sdk_google_phone_arm64" || Build.PRODUCT == "vbox86p") {
            rating++
        }
        if (Build.MANUFACTURER == "unknown") {
            rating++
        }
        if (Build.BRAND == "generic" ||
            Build.BRAND.equals(
                "android",
                ignoreCase = true
            ) || Build.BRAND == "generic_arm64" || Build.BRAND == "generic_x86" || Build.BRAND == "generic_x86_64"
        ) {
            rating++
        }
        if (Build.DEVICE == "generic" || Build.DEVICE == "generic_arm64" || Build.DEVICE == "generic_x86" || Build.DEVICE == "generic_x86_64" || Build.DEVICE == "vbox86p") {
            rating++
        }
        if (Build.MODEL == "sdk" || Build.MODEL == "Android SDK built for arm64" || Build.MODEL == "Android SDK built for armv7" || Build.MODEL == "Android SDK built for x86" || Build.MODEL == "Android SDK built for x86_64") {
            rating++
        }
        if (Build.HARDWARE == "ranchu") {
            rating++
        }
        if (Build.FINGERPRINT.contains("sdk_google_phone_arm64") ||
            Build.FINGERPRINT.contains("sdk_google_phone_armv7")
        ) {
            rating++
        }
        var result = (Build.FINGERPRINT.startsWith("generic", ignoreCase = true)
                || Build.MODEL.contains("google_sdk", ignoreCase = true)
                || Build.MODEL.lowercase().contains("droid4x", ignoreCase = true)
                || Build.MODEL.contains("Emulator", ignoreCase = true)
                || Build.MODEL.contains("Android SDK built for x86", ignoreCase = true)
                || Build.MANUFACTURER.contains("Genymotion", ignoreCase = true)
                || Build.HARDWARE.contains(
            "goldfish",
            ignoreCase = true
        ) || Build.HARDWARE.contains("vbox86", ignoreCase = true) || Build.PRODUCT.contains(
            "sdk",
            ignoreCase = true
        ) || Build.PRODUCT.startsWith(
            "google_sdk"
        )
                || Build.PRODUCT.contains(
            "sdk_x86",
            ignoreCase = true
        ) || Build.PRODUCT.contains("vbox86p", ignoreCase = true) || Build.BOARD.lowercase()
            .contains("nox", ignoreCase = true)
                || Build.BOOTLOADER.lowercase().contains("nox", ignoreCase = true)
                || Build.HARDWARE.lowercase().contains("nox", ignoreCase = true)
                || Build.PRODUCT.lowercase().contains("nox", ignoreCase = true)
                || Build.SERIAL.lowercase().contains("nox", ignoreCase = true)
                || Build.HOST.contains("Droid4x-BuildStation", ignoreCase = true)
                || Build.MANUFACTURER.startsWith("iToolsAVM", ignoreCase = true)
                || Build.DEVICE.startsWith("iToolsAVM", ignoreCase = true)
                || Build.MODEL.startsWith("iToolsAVM", ignoreCase = true)
                || Build.BRAND.startsWith("generic", ignoreCase = true)
                || Build.HARDWARE.startsWith("vbox86", ignoreCase = true))
        if (result) return true
        result = result or (Build.BRAND.startsWith(
            "generic",
            ignoreCase = true
        ) && Build.DEVICE.startsWith("generic", ignoreCase = true))
        if (result) return true
        result = result or ("google_sdk" == Build.PRODUCT)
        return if (result) true else rating >= 2
    }

    /* Reads the contents of the system files
     /proc/tty/drivers and /proc/cpuinfo and tries to find the word "goldfish" associated with Emulator*/
    private fun checkQEmuDrivers(): Boolean {
        for (drivers_file in arrayOf(File("/proc/tty/drivers"), File("/proc/cpuinfo"))) {
            if (drivers_file.exists() && drivers_file.canRead()) {
                val data = ByteArray(1024)
                try {
                    val `is`: InputStream = FileInputStream(drivers_file)
                    `is`.read(data)
                    `is`.close()
                } catch (exception: Exception) {
                    exception.printStackTrace()
                }
                val driverData = String(data)
                for (known_qemu_driver in QEMU_DRIVERS) {
                    if (driverData.contains(known_qemu_driver)) {
                        return true
                    }
                }
            }
        }
        return false
    }

    /* Checks for known files or paths that point to specific emulators, such as:
     Genymotion (/dev/socket/genyd)
     Nox (fstab.nox, init.nox.rc)
     Andy
     qemu files
     x86 files that indicate the system is not on ARM devices*/
    private fun checkAdvanced(): Boolean {
        return (checkFiles(GENY_FILES)
                || checkFiles(ANDY_FILES)
                || checkFiles(NOX_FILES)
                || checkQEmuDrivers()
                || checkFiles(PIPES)
                || checkFiles(X86_FILES))
    }

    private fun checkFiles(targets: Array<String>): Boolean {
        for (pipe in targets) {
            val qemuFile = File(pipe)
            if (qemuFile.exists()) {
                return true
            }
        }
        return false
    }

    /*   Scans installed packages and applications starting with:
       com.bluestacks.
       com.vphone.
       com.bignox.
       com.microvirt.
       or services running from ActivityManager.
       This helps detect emulators installed on the device.*/
    fun checkPackageName(context: Context): Boolean {
        val packageManager = context.packageManager
        val intent = Intent(Intent.ACTION_MAIN, null)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)
        val availableActivities = packageManager.queryIntentActivities(intent, 0)
        for (resolveInfo in availableActivities) {
            if (resolveInfo.activityInfo.packageName.startsWith("com.bluestacks.")) {
                return true
            }
        }
        val packages = packageManager
            .getInstalledApplications(PackageManager.GET_META_DATA)
        for (packageInfo in packages) {
            val packageName = packageInfo.packageName
            if (packageName.startsWith("com.vphone.", ignoreCase = true)) {
                return true
            } else if (packageName.startsWith("com.bignox.", ignoreCase = true)) {
                return true
            } else if (packageName.startsWith("com.nox.mopen.app", ignoreCase = true)) {
                return true
            } else if (packageName.startsWith("me.haima.", ignoreCase = true)) {
                return true
            } else if (packageName.startsWith("com.bluestacks.", ignoreCase = true)) {
                return true
            } else if (packageName.startsWith(
                    "cn.itools.",
                    ignoreCase = true
                ) && Build.PRODUCT.startsWith("iToolsAVM", ignoreCase = true)
            ) {
                return true
            } else if (packageName.startsWith("com.kop.", ignoreCase = true)) {
                return true
            } else if (packageName.startsWith("com.kaopu.", ignoreCase = true)) {
                return true
            } else if (packageName.startsWith("com.microvirt.", ignoreCase = true)) {
                return true
            } else if (packageName == "com.google.android.launcher.layouts.genymotion") {
                return true
            }
        }
        val manager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val runningServices = manager.getRunningServices(30)
        for (serviceInfo in runningServices) {
            val serviceName = serviceInfo.service.className
            if (serviceName.startsWith("com.bluestacks.")) {
                return true
            }
        }
        return false
    }

    private fun checkMacAddress(): Boolean {
        try {
            val all = NetworkInterface.getNetworkInterfaces()
            for (ni in all) {
                val mac = ni.hardwareAddress?.joinToString(":") { "%02X".format(it) } ?: continue
                Log.d("MacCheck", "MAC: $mac")
                if (mac.startsWith("00:1A:2A") || mac == "02:00:00:00:00:00") {
                    return true
                }
            }
        } catch (e: Exception) {
        }
        return false
    }
}