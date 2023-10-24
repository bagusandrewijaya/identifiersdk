package id.sdkcores.facedetection.analyzer

import id.sdkcores.facedetection.model.LivenessResult

interface LivenessDetectionListener {
    fun onFaceStatusChanged(faceStatus: FaceStatus)
    fun onStartDetection(detectionMode: DetectionMode)
    fun onLiveDetectionSuccess(livenessResult: LivenessResult)
    fun onLiveDetectionFailure(exception: Exception)
}