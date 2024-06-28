package com.olivia.plant.ui.monitoring_data

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.olivia.plant.R
import com.olivia.plant.databinding.ActivityMonitoringDataBinding
import com.olivia.plant.ui.main.MainActivity
import io.data2viz.charts.chart.Chart
import io.data2viz.charts.chart.chart
import io.data2viz.charts.chart.discrete
import io.data2viz.charts.chart.mark.area
import io.data2viz.charts.chart.quantitative
import io.data2viz.geom.Size
import io.data2viz.viz.VizContainerView

class MonitoringData : AppCompatActivity() {

    private lateinit var binding: ActivityMonitoringDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMonitoringDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.cvLightIntensity.addView(LightIntensityChart(this))
        binding.cvRainIntensity.addView(RainIntensityChart(this))
        binding.cvAirTemperature.addView(AirTemperatureChart(this))
        binding.cvAirHumidity.addView(AirHumidityChart(this))
        binding.cvSoilMoisture.addView(SoilMoistureChart(this))
        binding.cvSoilNutrients.addView(SoilMoistureChart(this))
        binding.cvWindSpeed.addView(WindSpeedChart(this))
        binding.cvAirPressure.addView(AirPressureChart(this))
        binding.cvAirQuality.addView(AirQualityChart(this))
        binding.cvGasLevel.addView(GasLevelChart(this))


    }
}