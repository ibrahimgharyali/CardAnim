package com.ibrahim.demo.cardanim

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.ibrahim.demo.cardanim.adapter.PersonRecyclerviewAdapter
import com.ibrahim.demo.cardanim.databinding.ActivityMainBinding
import com.ibrahim.demo.cardanim.model.RetrofitFactory
import com.ibrahim.demo.cardanim.viewmodel.MainViewModel
import jp.wasabeef.recyclerview.animators.SlideInRightAnimator
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.jetbrains.anko.toast
import retrofit2.HttpException

/**
 * Using Kotlin because, why not?
 * MVVM architecture, Viewmodel is better for live data, orientation changes.
 * Using Retrofit with Anko library for clean api calls
 * Reactive programming.
 * Picasso imageloader library, though there are many more options like Glide, Fresco, Volley imageLoader
 * */
class MainActivity : FragmentActivity(), PersonRecyclerviewAdapter.OnItemClickListener {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.setLifecycleOwner(this)
        val viewModel = ViewModelProviders.of(this@MainActivity).get(MainViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.setResultValue(this@MainActivity)
        viewModel.personAdapter.itemClickListener = this@MainActivity
        binding.executePendingBindings()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = viewModel.personAdapter
//        binding.recyclerView.itemAnimator = SlideInOutRightItemAnimator(binding.recyclerView)
        binding.recyclerView.itemAnimator = SlideInRightAnimator()

        val service = RetrofitFactory.makeRetrofitService()
        GlobalScope.launch (Dispatchers.Main){
            val request = service.randomUser();
            try {
                val response = request.await()
                if (response.isSuccessful){
                    val result = response.body()
                    /*viewModel.personList.observe(this@MainActivity,
                            ) = result?.results?.toMutableList()*/
                    viewModel.personList.value = result?.results

                }
                Log.d("RETRO", response.body().toString())
            }
            catch (e: HttpException){
                toast(e.code())
            } catch (e: Throwable){
                toast("Ooops: Something else went wrong")
            }
            progress_circular.visibility = View.GONE
        }
    }


    override fun onItemClick(position: Int) {
        binding.viewModel?.personAdapter?.removeData(position)
    }
}
