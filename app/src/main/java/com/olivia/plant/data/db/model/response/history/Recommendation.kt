package com.olivia.plant.data.db.model.response.history


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Recommendation(
    @SerializedName("id")
    val id: Int,
    @SerializedName("disease_id")
    val diseaseId: Int,
    @SerializedName("symptoms")
    val symptoms: String,
    @SerializedName("recomendation")
    val recomendation: String,
    @SerializedName("organic_control")
    val organicControl: String,
    @SerializedName("chemical_control_1")
    val chemicalControl1: String? = "",
    @SerializedName("chemical_control_2")
    val chemicalControl2: String? = "",
    @SerializedName("chemical_control_3")
    val chemicalControl3: String? = "",
    @SerializedName("chemical_control_4")
    val chemicalControl4: String? = "",
    @SerializedName("chemical_control_5")
    val chemicalControl5: String? = "",
    @SerializedName("chemical_control_1_dosage")
    val chemicalControl1Dosage: String? = "",
    @SerializedName("chemical_control_2_dosage")
    val chemicalControl2Dosage: String? = "",
    @SerializedName("chemical_control_3_dosage")
    val chemicalControl3Dosage: String? = "",
    @SerializedName("chemical_control_4_dosage")
    val chemicalControl4Dosage: String? = "",
    @SerializedName("chemical_control_5_dosage")
    val chemicalControl5Dosage: String? = "",
    @SerializedName("additional_info")
    val additionalInfo: String? = ""
) : Parcelable