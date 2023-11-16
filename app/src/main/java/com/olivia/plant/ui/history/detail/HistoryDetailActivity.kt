package com.olivia.plant.ui.history.detail

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.olivia.plant.R
import com.olivia.plant.data.db.model.response.detection.DataLeafsDisease
import com.olivia.plant.data.db.model.response.history.DataDetectionHistoryItem
import com.olivia.plant.databinding.ActivityHistoryDetailBinding
import com.olivia.plant.databinding.ActivityResultDetailBinding
import com.oratakashi.viewbinding.core.binding.intent.intent
import com.oratakashi.viewbinding.core.tools.gone
import com.oratakashi.viewbinding.core.tools.loadImage
import com.zero.zerobase.presentation.viewbinding.BaseActivity

class HistoryDetailActivity :BaseActivity<ActivityResultDetailBinding>() {
    private val dataLeafsDisease: DataDetectionHistoryItem by intent("dataLeafsDisease")

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun initUI() {
        super.initUI()
        with(binding) {
            btnBack.setOnClickListener { finish() }

            tvDiagnosis.text = dataLeafsDisease.condition
            tvRekomendasi.text = dataLeafsDisease.recommendation.recomendation
            tvGejala.text = dataLeafsDisease.recommendation.symptoms
            tvKontrolOrganik.text = dataLeafsDisease.recommendation.organicControl

            ivDetection.loadImage(this@HistoryDetailActivity, imageUrl = dataLeafsDisease.imageUrl, errorDrawable = this@HistoryDetailActivity.getDrawable(R.drawable.ic_broken_image))

            var nullCount = 0

            if (dataLeafsDisease.recommendation.chemicalControl1.isNullOrEmpty()) {
                llChemicalControl1.gone()
                nullCount++
            }
            tvChemicalControl1.text = dataLeafsDisease.recommendation.chemicalControl1
            tvChemicalDosage1.text = dataLeafsDisease.recommendation.chemicalControl1Dosage
            if (dataLeafsDisease.recommendation.chemicalControl2.isNullOrEmpty()) {
                llChemicalControl2.gone()
                nullCount++
            }
            tvChemicalControl2.text = dataLeafsDisease.recommendation.chemicalControl2
            tvChemicalDosage2.text = dataLeafsDisease.recommendation.chemicalControl2Dosage
            if (dataLeafsDisease.recommendation.chemicalControl3.isNullOrEmpty()) {
                llChemicalControl3.gone()
                nullCount++
            }
            tvChemicalControl3.text = dataLeafsDisease.recommendation.chemicalControl3
            tvChemicalDosage3.text = dataLeafsDisease.recommendation.chemicalControl3Dosage
            if (dataLeafsDisease.recommendation.chemicalControl4.isNullOrEmpty()) {
                llChemicalControl4.gone()
                nullCount++
            }
            tvChemicalControl4.text = dataLeafsDisease.recommendation.chemicalControl4
            tvChemicalDosage4.text = dataLeafsDisease.recommendation.chemicalControl4Dosage
            if (dataLeafsDisease.recommendation.chemicalControl5.isNullOrEmpty()) {
                llChemicalControl5.gone()
                nullCount++
            }
            tvChemicalControl5.text = dataLeafsDisease.recommendation.chemicalControl5
            tvChemicalDosage5.text = dataLeafsDisease.recommendation.chemicalControl5Dosage

            tvKontrolKimia.text = dataLeafsDisease.recommendation.additionalInfo

            if (nullCount == 5) cvKontolKimia.gone()
        }
    }
}