package com.olivia.plant.ui.image_detection.result.detail

import com.olivia.plant.R
import com.olivia.plant.data.db.model.response.detection.DataLeafsDisease
import com.olivia.plant.databinding.ActivityResultDetailBinding
import com.oratakashi.viewbinding.core.binding.intent.intent
import com.oratakashi.viewbinding.core.tools.gone
import com.oratakashi.viewbinding.core.tools.loadImage
import com.zero.zerobase.presentation.viewbinding.BaseActivity

class ResultDetailActivity : BaseActivity<ActivityResultDetailBinding>() {
    private val dataLeafsDisease: DataLeafsDisease by intent("dataLeafsDisease")

    override fun initUI() {
        super.initUI()
        with(binding) {
            btnBack.setOnClickListener { finish() }

            tvDiagnosis.text = dataLeafsDisease.condition
            tvRekomendasi.text = dataLeafsDisease.dataRecomendation.recomendation
            tvGejala.text = dataLeafsDisease.dataRecomendation.symptoms
            tvKontrolOrganik.text = dataLeafsDisease.dataRecomendation.organicControl

            ivDetection.loadImage(this@ResultDetailActivity, imageUrl = dataLeafsDisease.imageUri, errorDrawable = this@ResultDetailActivity.getDrawable(
                R.drawable.ic_broken_image))

            var nullCount = 0

            if (dataLeafsDisease.dataRecomendation.chemicalControl1.isNullOrEmpty()) {
                llChemicalControl1.gone()
                nullCount++
            }
            tvChemicalControl1.text = dataLeafsDisease.dataRecomendation.chemicalControl1
            tvChemicalDosage1.text = dataLeafsDisease.dataRecomendation.chemicalControl1Dosage
            if (dataLeafsDisease.dataRecomendation.chemicalControl2.isNullOrEmpty()) {
                llChemicalControl2.gone()
                nullCount++
            }
            tvChemicalControl2.text = dataLeafsDisease.dataRecomendation.chemicalControl2
            tvChemicalDosage2.text = dataLeafsDisease.dataRecomendation.chemicalControl2Dosage
            if (dataLeafsDisease.dataRecomendation.chemicalControl3.isNullOrEmpty()) {
                llChemicalControl3.gone()
                nullCount++
            }
            tvChemicalControl3.text = dataLeafsDisease.dataRecomendation.chemicalControl3
            tvChemicalDosage3.text = dataLeafsDisease.dataRecomendation.chemicalControl3Dosage
            if (dataLeafsDisease.dataRecomendation.chemicalControl4.isNullOrEmpty()) {
                llChemicalControl4.gone()
                nullCount++
            }
            tvChemicalControl4.text = dataLeafsDisease.dataRecomendation.chemicalControl4
            tvChemicalDosage4.text = dataLeafsDisease.dataRecomendation.chemicalControl4Dosage
            if (dataLeafsDisease.dataRecomendation.chemicalControl5.isNullOrEmpty()) {
                llChemicalControl5.gone()
                nullCount++
            }
            tvChemicalControl5.text = dataLeafsDisease.dataRecomendation.chemicalControl5
            tvChemicalDosage5.text = dataLeafsDisease.dataRecomendation.chemicalControl5Dosage

            tvKontrolKimia.text = dataLeafsDisease.dataRecomendation.additionalInfo

            if (nullCount == 5) cvKontolKimia.gone()
        }
    }
}