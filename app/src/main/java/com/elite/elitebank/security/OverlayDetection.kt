package com.elite.elitebank.security

import android.util.Log
import kotlinx.coroutines.*

class OverlayDetection {

    companion object {
        private val TAG = OverlayDetection::class.java.simpleName
        private const val TIMEOUT_MS = 3000L // وقت كشف التراكب (3 ثواني)
        private const val RETRY_DELAY = 1500L // 1.5 ثانية بين الحقن
    }

    private var lastCallNotCaught = false
    private var overlayDetectionRunning = false

    suspend fun detectOverlay(): Boolean = withContext(Dispatchers.IO) {
        overlayDetectionRunning = true
        lastCallNotCaught = false

        val detectionResult = CompletableDeferred<Boolean>()

        val job = launch {
            delay(1000L) // انتظار مبدئي قبل بدء الحقن

            while (overlayDetectionRunning && !detectionResult.isCompleted) {
                if (lastCallNotCaught) {
                    Log.d(TAG, "Overlay detected")
                    detectionResult.complete(true)
                    overlayDetectionRunning = false
                    break
                } else {
                    try {
                        Log.d(TAG, "Injecting dummy event to detect overlay")
                        lastCallNotCaught = true
                        // في التطبيق الحقيقي، هنا ترسل حدث حقن لمسح التراكب
                    } catch (e: Exception) {
                        Log.e(TAG, "Overlay detection exception: ${e.message}")
                        detectionResult.complete(true)
                        overlayDetectionRunning = false
                        break
                    }
                }
                delay(RETRY_DELAY)
            }
        }

        // انتهاء المهلة بدون كشف
        val timedOut = withTimeoutOrNull(TIMEOUT_MS) {
            detectionResult.await()
        } ?: false

        // لو لم يكتمل الكشف وأوقفنا الانتظار
        if (!timedOut) {
            Log.d(TAG, "Overlay detection timed out without detecting overlay")
            overlayDetectionRunning = false
            detectionResult.complete(false)
        }

        job.cancelAndJoin() // تأكد من إلغاء الجوب

        return@withContext detectionResult.getCompleted()
    }
}
