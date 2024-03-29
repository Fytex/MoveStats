package com.example.myapplication

import CustomAdapterCollection
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CollectionActivity: AppCompatActivity(){

    private var activity: Int = -1

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.collection_activity)
        supportActionBar?.hide()

        val typeRoam : Boolean = intent.getBooleanExtra("typeRoam",false)


        val data = ArrayList<ItemsViewModel>()
        val activities = listOf("Walking", "Running", "UpStairs", "DownStairs", "Idle")
        activities.forEach { e ->
            data.add(
                ItemsViewModel(
                    R.drawable.ic_launcher_foreground,
                    0f,
                    e,
                    Color.WHITE
                )
            )
        }
        val adapter = CustomAdapterCollection(data)
        adapter.setOnItemClickListener(object : CustomAdapterCollection.OnItemClickListener {
            override fun onItemClick(position: Int) {
                activity = position
            }
        })
        val recyclerview = findViewById<RecyclerView>(R.id.recyCollection)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter
        recyclerview.layoutManager = LinearLayoutManager(this)


        val btnNext = findViewById<Button>(R.id.btnNext)

        btnNext.setOnClickListener {
            if (activity == -1) {
                Toast.makeText(this, "Select activity", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val intent  = Intent(this, CollectionActivityType::class.java)
            intent.putExtra("activity", activity)
            intent.putExtra("typeRoam", typeRoam)
            this.startActivity(intent)

        }
    }
}