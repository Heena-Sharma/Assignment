package com.ns.assignment.fragments



import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayoutMediator
import com.ns.assignment.adapters.CarouselAdapter

import com.ns.assignment.util.CustomTextWatcher
import com.ns.assignment.util.hide

import com.ns.assignment.util.show
import com.ns.assignment.viewmodals.DataViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.ns.assignment.databinding.FragmentListBinding
import com.ns.assignment.adapters.DataListAdapter


@AndroidEntryPoint
class ListFragment : Fragment() {

    private lateinit var  binding: FragmentListBinding
    private val viewModel: DataViewModel by viewModels()
    private lateinit var recordListAdapter: DataListAdapter
    private lateinit var carouselAdapter: CarouselAdapter

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        context ?: return binding.root
        setListener()
        setRVData()
        setCarousel()
        subscribeUi()


        return binding.root
    }

    private fun setListener() {
        with(binding) {
            etFilter.addTextChangedListener(CustomTextWatcher {
                recordListAdapter.filter.filter(it)
            })
        }
    }


    private fun setRVData() {
        recordListAdapter = DataListAdapter {
            if (it) binding.tvNoResultFound.show() else {
                binding.tvNoResultFound.hide()
            }
        }
        val mLinearLayoutManager = LinearLayoutManager(activity)
        mLinearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.rvRecords.layoutManager = mLinearLayoutManager
        viewModel.postListData(viewModel.getData(0))
        binding.rvRecords.adapter = recordListAdapter
    }

    private fun subscribeUi() {
        viewModel.selectedRecords.observe(viewLifecycleOwner) { list ->
            recordListAdapter.publishData(list)
        }
        viewModel.carouselList.observe(viewLifecycleOwner) { list ->
            carouselAdapter.submitList(list)
        }
    }

    private fun setCarousel() {

        carouselAdapter = CarouselAdapter()
        binding.vpImageCarousel.adapter = carouselAdapter
        TabLayoutMediator(binding.tlIndicator, binding.vpImageCarousel) { _, position ->
            viewModel.postListData(
                viewModel.getData(
                    position
                )
            )
        }.attach()
        binding.vpImageCarousel.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            // This method is triggered when there is any scrolling activity for the current page
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {

            }

            // triggered when you select a new page
            override fun onPageSelected(position: Int) {

                viewModel.postListData(
                    viewModel.getData(
                        position
                    )
                )
                binding.rvRecords.scrollToPosition(0)

            }
            // triggered when there is
            // scroll state will be changed
            override fun onPageScrollStateChanged(state: Int) {

            }
        })

    }

}

