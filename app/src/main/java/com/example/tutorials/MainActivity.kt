package com.example.tutorials

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_pins.*


/*
TO DO:
1.Sign Out button
2. Fix bottomNav highlighting
 */

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        Log.i(TAG,"OnCreate")
        supportActionBar?.hide()

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.miHome -> {
                    Intent(this,MainActivity::class.java).also { startActivity(it) }

                }

                R.id.miProfile ->{
                    Intent(this,ProfileActivity::class.java).also { startActivity(it) }

                }
            }
            true
        }

        //rvPins.setBackgroundColor(Color.WHITE)
        val pins = mutableListOf<Pin>()
        val pinAdapter = PinAdapter(this,pins)
        rvPins.adapter = PinAdapter(this, pins)

        val gridLayoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        // Attach the layout manager to the recycler view
        rvPins.setLayoutManager(gridLayoutManager)
        //rvPins.layoutManager = LinearLayoutManager(this)

        val model = ViewModelProviders.of(this)[MainActivityViewModel::class.java]
        model.getPins().observe(this, Observer {
            // Update UI
            Log.w("Received pins","observer")
            pins.clear()
            pins.addAll(it)
            pinAdapter.notifyDataSetChanged()
        })


        // Swipe Refresh
        model.getRefreshingLiveData().observe(this, Observer { isRefreshing ->
            swipeContainer.isRefreshing = isRefreshing
        })
        swipeContainer.setOnRefreshListener {
            // show the refreshing UI and fetch new data
            model.fetchNewPins()
        }

    }



}