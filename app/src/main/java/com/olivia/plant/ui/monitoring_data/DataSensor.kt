package com.olivia.plant.ui.monitoring_data

import kotlinx.serialization.Serializable

@Serializable
data class DataSensor(
    val timestamp: String,
    val lightIntensity: Double,
    val rainIntensity: Double,
    val airTemperature: Double,
    val airHumidity: Double,
    val soilMoisture: Double,
    val soilNutrients: Double,
    val windSpeed: Double,
    val windDirection: String,
    val airPressure: Double,
    val airQuality: Double,
    val gasLevel: Double
)

