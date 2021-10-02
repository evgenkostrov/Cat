package com.task5.ui.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.task5.model.CatsResponse
import com.task5.ui.activity.CatImageActivity
import com.task5.ui.viewmodel.CatsViewModel
import com.task5.ui.adapter.CatListAdapter
import com.task5.ui.adapter.CatLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import android.content.Intent
import androidx.core.app.ActivityOptionsCompat
import com.task5.constants.IntentConstants

import android.view.animation.AnimationUtils
import androidx.paging.ExperimentalPagingApi
import com.task5.R
import com.task5.utils.GlideApp

@ExperimentalPagingApi
@AndroidEntryPoint
class CatFragment : Fragment(R.layout.fragment_cat) , CatListAdapter.OnCellClickListener{

    lateinit var rvCat: RecyclerView
    private val catViewModel: CatsViewModel by viewModels()
    lateinit var adapter: CatListAdapter
    lateinit var loaderStateAdapter: CatLoadStateAdapter


    @ExperimentalPagingApi
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        setUpRecycler(view)
        fetchCatImages()
    }


    @ExperimentalPagingApi
    private fun fetchCatImages() {
        lifecycleScope.launch {
            catViewModel.fetchCatData().distinctUntilChanged().collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun initRecyclerView() {
        val glide = GlideApp.with(this)
        adapter = CatListAdapter(glide)
        adapter.setOnClickListener(this)
        loaderStateAdapter = CatLoadStateAdapter (adapter)
    }

    private fun setUpRecycler(view: View) {
        rvCat = view.findViewById(R.id.rvCat)
        rvCat.layoutManager = GridLayoutManager(context, 3)
        rvCat.adapter = adapter.withLoadStateFooter(loaderStateAdapter)
    }

    override fun onCellClicked(image: CatsResponse, view: View) {
        view.animation=AnimationUtils.loadAnimation(requireActivity(), R.anim.fade_inn)



        activity?.let{
            val intent = Intent (it, CatImageActivity::class.java)
            intent.putExtra(IntentConstants.CAT_EXTRA_URL,image.url)


            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                requireActivity(),
                view, "cat_image"
            )
//            val options = ActivityOptionsCompat.makeCustomAnimation(
//                requireActivity(),
//               R.anim.fade_inn,
//                0
//            )
////                        val options = ActivityOptionsCompat.makeScaleUpAnimation(
////                view,0,0,100,100
////            )

            it.startActivity(intent,options.toBundle())
        }
    }
}