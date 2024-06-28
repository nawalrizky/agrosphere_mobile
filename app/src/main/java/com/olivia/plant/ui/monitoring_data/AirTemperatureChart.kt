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
import java.util.Calendar
import java.util.Locale

class AirTemperatureChart(context: Context) : VizContainerView(context) {

    private val chart: Chart<AirTemperatureData> = chart(airTemperatureData) {
        size = Size(vizSize, vizSize)
        title = "Data Sensor Suhu Udara"

        val hour = discrete({ domain.hour }) {
            name = "Jam Data Diambil"
        }
        val airTemperature = quantitative({ domain.airTemperature }) {
            name = "Suhu Udara"
        }

        line(hour, airTemperature) {
            strokeColor = constant(Colors.Web.yellow)
            strokeWidth = constant(2.0)
        }

    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        chart.size = Size(vizSize, vizSize * (h * 0.7) / w)
    }
}

@Serializable
data class AirTemperatureData(val hour: String, val airTemperature: Double) {
    companion object {
        fun fromJson(jsonString: String): List<AirTemperatureData> {
            val json = Json { ignoreUnknownKeys = true }
            val dataList = json.decodeFromString<List<JsonElement>>(jsonString)
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX", Locale.getDefault())

            return dataList.map { element ->
                val timestamp = element.jsonObject["timestamp"]?.jsonPrimitive?.content
                val airTemperature = element.jsonObject["air_temperature"]?.jsonPrimitive?.double

                val date = dateFormat.parse(timestamp)
                val calendar = Calendar.getInstance()
                calendar.time = date
                val hour = String.format("%02d", calendar.get(Calendar.HOUR_OF_DAY))

                AirTemperatureData(hour, airTemperature!!)
            }
        }
    }
}

val airTemperatureData = AirTemperatureData.fromJson(jsonString)
