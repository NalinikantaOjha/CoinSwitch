package com.masai.nalini.ui.activity

import com.masai.nalini.R



import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class HomeActivity : AppCompatActivity() {
    private lateinit var adsRecyclerView: RecyclerView
    private lateinit var adsImage: AdsImage
    private lateinit var adsAdapter: AdsAdapter
    private var arrayList: ArrayList<AdsImage> = arrayListOf()
    private lateinit var popularRecyclerView: RecyclerView
    private lateinit var popularImage: popularImage1
    private lateinit var popularAdapter: popularAdapter
    private var arrayList1: ArrayList<popularImage1> = arrayListOf()
    private lateinit var coinswitchRecyclerView: RecyclerView
    private lateinit var coinswitchImage: CoinSwitchImage
    private lateinit var coinSwitchAdapter: coinSwitchAdapter
    private var arrayList2: ArrayList<CoinSwitchImage> = arrayListOf()
    private lateinit var recommendedRecyclerView: RecyclerView
    private lateinit var recommendedImage: RecommendedImage
    private lateinit var recommendedAdapter: RecommendedAdapter
    private var arrayList3: ArrayList<RecommendedImage> = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        AdsData()
        popularData()
        coinswitchData()
        RecommendedData()
    }

    private fun RecommendedData() {
        recyclerView3()
        buildListImage3()
        RecommendedRecyclerView()
    }

    private fun RecommendedRecyclerView() {
        recommendedAdapter = RecommendedAdapter(arrayList3)
        var linearLayoutManager:LinearLayoutManager = LinearLayoutManager(this)
        recommendedRecyclerView.adapter = recommendedAdapter
        recommendedRecyclerView.layoutManager = linearLayoutManager


    }

    private fun buildListImage3() {
        recommendedImage = RecommendedImage(R.drawable.recofirst, "Raghuram Rajan Sees Future in Cryptocurrencies")
        arrayList3.add(recommendedImage)
        recommendedImage = RecommendedImage(R.drawable.recosecond, "Polygon(MATIC) on Spoylight")
        arrayList3.add(recommendedImage)
        recommendedImage= RecommendedImage(R.drawable.recothird, "Best Cryptocurrency Trading Tips in 2021")
        arrayList3.add(recommendedImage)
        recommendedImage= RecommendedImage(R.drawable.recofourth, "Crypocurrency: A Ray of Hope For the Troubled Afghans")
        arrayList3.add(recommendedImage)

    }

    private fun recyclerView3() {
        recommendedRecyclerView= findViewById(R.id.RecyclerViewRecommendedRead)
    }

    private fun coinswitchData() {
        recylerview2()

        buildListImage2()
        coinSwitchRecyclerView()

    }

    private fun coinSwitchRecyclerView() {
        coinSwitchAdapter = coinSwitchAdapter(arrayList2)
        var gridLayoutManager:GridLayoutManager = GridLayoutManager(this, 1, LinearLayoutManager.HORIZONTAL, true)
        coinswitchRecyclerView.adapter = coinSwitchAdapter
        coinswitchRecyclerView.layoutManager = gridLayoutManager
    }

    private fun buildListImage2() {
        coinswitchImage = CoinSwitchImage(R.drawable.youtube1, "Crypto Price Analysis | Episode 07 | Live")
        arrayList2.add(coinswitchImage)
        coinswitchImage = CoinSwitchImage(R.drawable.youtube2, "Crypto News 12Nov | DIGIBYTE UP BY 17.58% | Crypto")
        arrayList2.add(coinswitchImage)
        coinswitchImage = CoinSwitchImage(R.drawable.youtube3, "#Kuch to badlega | India Ki Gully Se World Cup Ke Pitch Tak| Ranveer Singh")
        arrayList2.add(coinswitchImage)
        coinswitchImage = CoinSwitchImage(R.drawable.youtube4, "Crypto | How Much To Invest in Crypto| Crypto")
        arrayList2.add(coinswitchImage)
    }

    private fun recylerview2() {
        coinswitchRecyclerView= findViewById(R.id.CoinSwitchRecyclerView)
    }

    private fun popularData() {
        recylerview1()

        buildListImage1()
        popularRecyclerView()

    }
    private fun recylerview1(){
        popularRecyclerView= findViewById(R.id.popularRecyclerView)
    }
    private fun buildListImage1(){


            popularImage = popularImage1(R.drawable.coinfirst)
            arrayList1.add(popularImage)
        popularImage = popularImage1(R.drawable.coinsecond)
        arrayList1.add(popularImage)
        popularImage = popularImage1(R.drawable.coinfirst)
        arrayList1.add(popularImage)
        popularImage = popularImage1(R.drawable.coinsecond)
        arrayList1.add(popularImage)
    }
    private fun popularRecyclerView(){
        popularAdapter = popularAdapter(arrayList1)
        var gridLayoutManager:GridLayoutManager = GridLayoutManager(this, 1, LinearLayoutManager.HORIZONTAL, true)
        popularRecyclerView.adapter = popularAdapter
        popularRecyclerView.layoutManager = gridLayoutManager
    }

    private fun AdsData() {
        recylerview()

        buildListImage()
        adsRecyclerView()
    }

    private fun recylerview() {
        adsRecyclerView = findViewById(R.id.AdsRecyclerView)
    }

    private fun adsRecyclerView(){
        adsAdapter = AdsAdapter(arrayList)
        var gridLayoutManager:GridLayoutManager = GridLayoutManager(this, 1, LinearLayoutManager.HORIZONTAL, true)
        adsRecyclerView.adapter = adsAdapter
        adsRecyclerView.layoutManager = gridLayoutManager

    }

    private fun buildListImage() {

            adsImage = AdsImage(R.drawable.ads)
            arrayList.add(adsImage)
        adsImage = AdsImage(R.drawable.ads1)
        arrayList.add(adsImage)
        adsImage = AdsImage(R.drawable.ads2)
        arrayList.add(adsImage)
        adsImage = AdsImage(R.drawable.ads3)
        arrayList.add(adsImage)


    }


}





