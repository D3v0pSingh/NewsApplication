package com.example.cauroselapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.size
import com.daimajia.androidanimations.library.Techniques
import com.daimajia.androidanimations.library.YoYo
import com.example.cauroselapp.databinding.ActivityMainBinding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.jackandphantom.carouselrecyclerview.CarouselLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var adapter: Adapter
    lateinit var adView: AdView
    var pageNum = 1
    var totalResults = -1
    private var articles = mutableListOf<Articles>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //admob code
        MobileAds.initialize(this)//initialization
        adView = binding.adView
        val adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)//loading the ad

        adapter = Adapter(this@MainActivity, articles)
        binding.recyclerVIew.adapter = adapter
        binding.recyclerVIew.set3DItem(true)
        binding.recyclerVIew.setAlpha(true)
        binding.recyclerVIew.setInfinite(false)

        binding.recyclerVIew.setItemSelectListener(object : CarouselLayoutManager.OnSelected {
            override fun onItemSelected(position: Int) {

                try {
                    binding.container.setBackgroundColor(Color.parseColor(Colors.getColor()))
                } catch (e: Exception) {
                    binding.container.setBackgroundColor(Color.parseColor("#FF3322"))
                }
                Log.d(
                    "android",
                    "First Visible Item - ${binding.recyclerVIew.getSelectedPosition()}"
                )
                Log.d("android", "Total fetched Items - ${adapter.itemCount}")
                if (totalResults > adapter.itemCount && binding.recyclerVIew.getSelectedPosition() >= adapter.itemCount - 5) {
                    pageNum++
                    getNews()
                }

            }

        })

        getNews()


    }

    private fun getNews() {
        Log.d("android", "Current Page number is - ${pageNum}")
        val news = newsObject.instance.newsToday(pageNum, "in")
        news.enqueue(object : Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news = response.body()
                if (news != null) {
                    YoYo.with(Techniques.FadeOutLeft).duration(2000).repeat(110)
                        .playOn(binding.swipeText)
                    totalResults = news.totalResults
                    articles.addAll(news.articles)
                    adapter.notifyDataSetChanged()


                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("android", "Unsuccessful fetching")
            }

        })

    }
}