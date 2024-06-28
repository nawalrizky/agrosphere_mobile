package com.olivia.plant.ui.monitoring_data

import android.content.Context
import android.graphics.Color
import io.data2viz.charts.chart.Chart
import io.data2viz.charts.chart.chart
import io.data2viz.charts.chart.constant
import io.data2viz.charts.chart.discrete
import io.data2viz.charts.chart.mark.area
import io.data2viz.charts.chart.mark.line
import io.data2viz.charts.chart.quantitative
import io.data2viz.color.Colors
import io.data2viz.geom.Size
import io.data2viz.viz.VizContainerView
import kotlinx.serialization.*
import kotlinx.serialization.json.*
import java.text.SimpleDateFormat
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

class LightIntensityChart(context: Context) : VizContainerView(context) {

    private val chart: Chart<LightIntensityData> = chart(lightIntensityData) {
        size = Size(vizSize, vizSize)
        title = "Data Sensor Intensitas Cahaya"

        val hour = discrete({ domain.hour }) {
            name = "Jam Data Diambil"
        }
        val lightIntensity = quantitative({ domain.lightIntensity }) {
            name = "Intensitas Cahaya"
        }

        line(hour, lightIntensity) {
            strokeColor = constant(Colors.Web.orange)
            strokeWidth = constant(2.0)
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        chart.size = Size(vizSize, vizSize * (h*0.7) / w)
    }
}

const val vizSize = 500.0

@Serializable
data class LightIntensityData(val hour: String, val lightIntensity: Double) {
    companion object {
        fun fromJson(jsonString: String): List<LightIntensityData> {
            val json = Json { ignoreUnknownKeys = true }
            val dataList = json.decodeFromString<List<JsonElement>>(jsonString)
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.getDefault())

            return dataList.map { element ->
                val timestamp = element.jsonObject["timestamp"]?.jsonPrimitive?.content
                val lightIntensity = element.jsonObject["light_intensity"]?.jsonPrimitive?.double

                val date = dateFormat.parse(timestamp)
                val calendar = Calendar.getInstance()
                calendar.time = date
                val hour = String.format("%02d", calendar.get(Calendar.HOUR_OF_DAY))

                LightIntensityData(hour, lightIntensity!!)
            }
        }
    }
}

val jsonString = """
    [
      {
        "timestamp": "2024-06-27T12:00:00Z",
        "light_intensity": 500.5,
        "rain_intensity": 12.3,
        "air_temperature": 26.4,
        "air_humidity": 75.2,
        "soil_moisture": 45.3,
        "soil_nutrients": 30.1,
        "wind_speed": 15.6,
        "wind_direction": "NW",
        "air_pressure": 1012.8,
        "air_quality": 98.7,
        "gas_level": 0.03
      },
      {
        "timestamp": "2024-06-27T13:00:00Z",
        "light_intensity": 520.2,
        "rain_intensity": 11.8,
        "air_temperature": 27.0,
        "air_humidity": 76.0,
        "soil_moisture": 44.8,
        "soil_nutrients": 29.9,
        "wind_speed": 14.8,
        "wind_direction": "NW",
        "air_pressure": 1012.0,
        "air_quality": 99.0,
        "gas_level": 0.04
      },
      {
        "timestamp": "2024-06-27T14:00:00Z",
        "light_intensity": 540.1,
        "rain_intensity": 11.5,
        "air_temperature": 27.5,
        "air_humidity": 74.9,
        "soil_moisture": 44.0,
        "soil_nutrients": 30.3,
        "wind_speed": 15.0,
        "wind_direction": "NNW",
        "air_pressure": 1011.5,
        "air_quality": 97.5,
        "gas_level": 0.05
      },
      {
        "timestamp": "2024-06-27T15:00:00Z",
        "light_intensity": 530.0,
        "rain_intensity": 12.0,
        "air_temperature": 26.8,
        "air_humidity": 75.5,
        "soil_moisture": 45.0,
        "soil_nutrients": 30.0,
        "wind_speed": 15.5,
        "wind_direction": "WNW",
        "air_pressure": 1012.3,
        "air_quality": 98.0,
        "gas_level": 0.03
      },
      {
        "timestamp": "2024-06-27T16:00:00Z",
        "light_intensity": 515.0,
        "rain_intensity": 13.0,
        "air_temperature": 27.2,
        "air_humidity": 76.2,
        "soil_moisture": 44.5,
        "soil_nutrients": 29.8,
        "wind_speed": 14.9,
        "wind_direction": "NNW",
        "air_pressure": 1011.8,
        "air_quality": 97.8,
        "gas_level": 0.04
      },
      {
        "timestamp": "2024-06-27T17:00:00Z",
        "light_intensity": 525.5,
        "rain_intensity": 12.5,
        "air_temperature": 27.3,
        "air_humidity": 75.8,
        "soil_moisture": 44.7,
        "soil_nutrients": 30.2,
        "wind_speed": 15.2,
        "wind_direction": "NW",
        "air_pressure": 1012.5,
        "air_quality": 98.3,
        "gas_level": 0.04
      },
      {
        "timestamp": "2024-06-27T18:00:00Z",
        "light_intensity": 510.0,
        "rain_intensity": 12.2,
        "air_temperature": 26.5,
        "air_humidity": 75.0,
        "soil_moisture": 44.3,
        "soil_nutrients": 30.0,
        "wind_speed": 15.1,
        "wind_direction": "WNW",
        "air_pressure": 1011.7,
        "air_quality": 97.2,
        "gas_level": 0.05
      },
      {
        "timestamp": "2024-06-27T19:00:00Z",
        "light_intensity": 520.8,
        "rain_intensity": 11.7,
        "air_temperature": 27.1,
        "air_humidity": 75.7,
        "soil_moisture": 45.1,
        "soil_nutrients": 30.4,
        "wind_speed": 14.7,
        "wind_direction": "NNW",
        "air_pressure": 1011.6,
        "air_quality": 98.1,
        "gas_level": 0.04
      },
      {
        "timestamp": "2024-06-27T20:00:00Z",
        "light_intensity": 530.5,
        "rain_intensity": 12.1,
        "air_temperature": 27.0,
        "air_humidity": 76.1,
        "soil_moisture": 44.9,
        "soil_nutrients": 30.1,
        "wind_speed": 15.3,
        "wind_direction": "NW",
        "air_pressure": 1012.1,
        "air_quality": 98.4,
        "gas_level": 0.03
      },
      {
        "timestamp": "2024-06-27T21:00:00Z",
        "light_intensity": 535.0,
        "rain_intensity": 12.4,
        "air_temperature": 27.6,
        "air_humidity": 75.9,
        "soil_moisture": 44.6,
        "soil_nutrients": 30.0,
        "wind_speed": 15.4,
        "wind_direction": "NNW",
        "air_pressure": 1012.4,
        "air_quality": 97.9,
        "gas_level": 0.05
      }
    ]
""".trimIndent()

val lightIntensityData = LightIntensityData.fromJson(jsonString)
