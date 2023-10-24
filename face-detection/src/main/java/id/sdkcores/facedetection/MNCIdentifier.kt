package id.sdkcores.facedetection

import android.content.Context
import android.content.Intent
import id.sdkcores.facedetection.analyzer.DetectionMode
import id.sdkcores.facedetection.model.LivenessResult
import id.sdkcores.facedetection.model.SelfieWithKtpResult
import id.sdkcores.identification.core.common.EXTRA_RESULT

object MNCIdentifier {
    private var attempt = 0
    internal var lowMemoryThreshold: Int? = null

    var detectionMode = listOf(
        DetectionMode.HOLD_STILL,
        DetectionMode.OPEN_MOUTH,
        DetectionMode.BLINK,
        DetectionMode.SHAKE_HEAD,
        DetectionMode.SMILE
    )

    @JvmStatic
    fun setDetectionModeSequence(shuffle: Boolean, detectionMode: List<DetectionMode>){
        MNCIdentifier.detectionMode = detectionMode.run {
            if (shuffle) shuffled() else this
        }
    }

    @JvmStatic
    fun setLowMemoryThreshold(threshold: Int){
        lowMemoryThreshold = threshold
    }

    @JvmStatic
    fun getLivenessIntent(context: Context): Intent {
        attempt++
        return Intent(context, SplashLivenessActivity::class.java)
    }

    @JvmStatic
    fun getLivenessResult(intent: Intent?) =
        intent?.getParcelableExtra<LivenessResult>(EXTRA_RESULT)?.apply {
            attempt = MNCIdentifier.attempt
            if(isSuccess) MNCIdentifier.attempt = 0
        }

    @JvmStatic
    fun getSelfieResult(intent: Intent?) =
        intent?.getParcelableExtra(EXTRA_RESULT) as SelfieWithKtpResult?
}