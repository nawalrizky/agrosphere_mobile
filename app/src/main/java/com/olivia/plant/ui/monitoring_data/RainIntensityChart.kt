package com.olivia.plant.ui.monitoring_data

import android.content.Context
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

class RainIntensityChart(context: Context) : VizContainerView(context) {

    private val chart: Chart<RainIntensityData> = chart(rainIntensityData) {
        size = Size(vizSize, vizSize)
        title = "Data Sensor Intensitas Hujan"

        val hour = discrete({ domain.hour }) {
            name = "Jam Data Diambil"
        }
        val rainIntensity = quantitative({ domain.rainIntensity }) {
            name = "Intensitas Hujan"
        }

        line(hour, rainIntensity) {
            strokeColor = constant(Colors.Web.darkblue)
            strokeWidth = constant(2.0)
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        chart.size = Size(vizSize, vizSize * (h * 0.7) / w)
    }
}

@Serializable
data class RainIntensityData(val hour: String, val rainIntensity: Double) {
    companion object {
        fun fromJson(jsonString: String): List<RainIntensityData> {
            val json = Json { ignoreUnknownKeys = true }
            val dataList = json.decodeFromString<List<JsonElement>>(jsonString)
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.getDefault())

            return dataList.map { element ->
                val timestamp = element.jsonObject["timestamp"]?.jsonPrimitive?.content
                val rainIntensity = element.jsonObject["rain_intensity"]?.jsonPrimitive?.double

                val date = dateFormat.parse(timestamp)
                val calendar = Calendar.getInstance()
                calendar.time = date
                val hour = String.format("%02d", calendar.get(Calendar.HOUR_OF_DAY))

                RainIntensityData(hour, rainIntensity!!)
            }
        }
    }
}

val rainIntensityData = RainIntensityData.fromJson(jsonString)
