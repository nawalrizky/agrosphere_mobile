package com.olivia.plant.ui.augmented_reality

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.olivia.plant.R
import com.olivia.plant.databinding.ActivityObjectSelectionBinding

class ObjectSelectionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityObjectSelectionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityObjectSelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val objects = listOf(
            ARObject("Sofa", "models/sofa.glb"),
            ARObject("Padi", "models/chair.glb"),
            ARObject("Tanaman", "models/table.glb")
        )

        val adapter = ObjectAdapter(objects) { arObject ->
            val intent = Intent(this, AugmentedRealityActivity::class.java)
            intent.putExtra("AR_OBJECT", arObject.modelPath)
            startActivity(intent)
        }

        binding.recyclerViewObjects.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewObjects.adapter = adapter
    }
}
