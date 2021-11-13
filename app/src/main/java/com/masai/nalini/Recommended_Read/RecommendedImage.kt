package com.example.seenu_mobile.Recommended_Read

class RecommendedImage(private var ads_image:Int, private var coin_switch_name:String) {
    @JvmName("getName1")
    fun RecommendedImage():Int{
        return ads_image
    }
    @JvmName("getName1")
    fun RecommendedName():String{
        return coin_switch_name
    }
}
