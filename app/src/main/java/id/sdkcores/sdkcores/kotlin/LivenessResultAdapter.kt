package id.sdkcores.sdkcores.kotlin

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.sdkcores.facedetection.SelfieWithKtpActivity
import id.sdkcores.facedetection.model.LivenessResult
import id.sdkcores.sdkcores.databinding.ItemLiveDetectionResultBinding

class LivenessResultAdapter(val livenessResult: LivenessResult): RecyclerView.Adapter<LivenessResultAdapter.DetectionResultViewHolder>(){
    inner class DetectionResultViewHolder(val binding: ItemLiveDetectionResultBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetectionResultViewHolder {
        return DetectionResultViewHolder(
            ItemLiveDetectionResultBinding
                .inflate(LayoutInflater.from(parent.context),
                parent,
                false
            ))
    }

    override fun onBindViewHolder(holder: DetectionResultViewHolder, position: Int) {
        livenessResult.detectionResult?.get(position)?.let { item ->
            with(holder.binding){
                tvTitle.text = item.detectionMode.name
                tvTime.text = item.timeMilis?.toString()
                item.image?.let {
                    livenessResult.getBitmap(holder.itemView.context, item.detectionMode, onError = { message ->
                        Log.e(SelfieWithKtpActivity.TAG, message)
                    })
                }?.let {
                    ivResult.setImageBitmap(it)
                }
            }
        }

    }

    override fun getItemCount(): Int {
        return livenessResult.detectionResult?.size?: 0
    }
}