package com.ibrahim.demo.cardanim.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.ibrahim.demo.cardanim.MainActivity
import com.ibrahim.demo.cardanim.adapter.PersonRecyclerviewAdapter
import com.ibrahim.demo.cardanim.model.Person

class MainViewModel : ViewModel() {
    internal val personAdapter : PersonRecyclerviewAdapter =
        PersonRecyclerviewAdapter(arrayListOf())

//    constructor(application: Application):super(application)

    var personList = MutableLiveData<List<Person>>()

    fun loadpersonList(list : List<Person>){
        personList.value = list
    }
    /*fun buttonClicked( position: Int){

        personAdapter.removeData(position)
    }*/

    fun setResultValue(activity: MainActivity) {
        personList.observe(activity,
            Observer<List<Person>> { it?.let{ personAdapter.replaceData(it)} })

    }
}