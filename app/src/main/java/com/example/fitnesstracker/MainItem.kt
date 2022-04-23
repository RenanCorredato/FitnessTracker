package com.example.fitnesstracker

class MainItem(private var id: Int?,
               private var drawableId: Int?,
               private var textStringId: Int,
               private var color: Int?) {


    fun setColo(color: Int) {
        this.color = color
    }

    fun setDrawableId(drawableId: Int) {
        this.drawableId = drawableId
    }

    fun setId(id: Int) {
        this.id = id
    }

    fun setTextStringId(textStringId:Int){
        this.textStringId = textStringId
    }

    @JvmName("getId1")
    fun getId() = id

    fun getTextStringId() = textStringId

    @JvmName("getColor1")
    fun getColor() = color

    fun getDrawable() = drawableId
}