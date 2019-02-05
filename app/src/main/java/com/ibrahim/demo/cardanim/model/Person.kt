package com.ibrahim.demo.cardanim.model

import androidx.databinding.BaseObservable
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

//data object with baseobserver for adapter loadings
class Person ( val gender: String, val name : NameModel, val location : LocationModel,
                      val dob : DoB, val picture : PictureModel)  : BaseObservable(){
    companion object {
        @BindingAdapter("imageUrl")
        @JvmStatic
        fun loadImage(view : CircleImageView, imageUrl :String ) {
            Picasso.get().load(imageUrl).into(view)
        }
    }


    class DoB (val date : String, val age : Int )

    class PictureModel (var large: String, var medium: String, var thumbnail: String)

}



