package com.example.testtask.presentation.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testtask.R
import com.example.testtask.data.RepositoryInitializer
import com.example.testtask.databinding.FragmentMenuBinding
import com.example.testtask.domain.models.Banner
import com.example.testtask.domain.models.Category
import com.example.testtask.presentation.viewmodel.PizzaViewModel
import com.example.testtask.presentation.viewmodel.ViewModelFactory


class MenuFragment : Fragment() {

    private lateinit var binding: FragmentMenuBinding
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var pizzaAdapter: PizzaAdapter
    private lateinit var bannerAdapter: BannerAdapter

    private lateinit var pizzaViewModel: PizzaViewModel
    private lateinit var pizzaViewModelFactory: ViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentMenuBinding.inflate(layoutInflater)

        binding.recyclerviewMenu.layoutManager = LinearLayoutManager(requireContext(),
        LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerviewItems.layoutManager = LinearLayoutManager(requireContext(),
        LinearLayoutManager.VERTICAL, false)
        binding.recyclerviewBanner.layoutManager = LinearLayoutManager(requireContext(),
        LinearLayoutManager.HORIZONTAL, false)

        categoryAdapter = CategoryAdapter()
        binding.recyclerviewMenu.adapter = categoryAdapter
        categoryAdapter.set(mutableListOf(Category("Пицца"),
            Category("Комбо"), Category("Десерты"), Category("Напитки")))

        pizzaAdapter = PizzaAdapter()
        binding.recyclerviewItems.adapter = pizzaAdapter

        bannerAdapter = BannerAdapter()
        binding.recyclerviewBanner.adapter = bannerAdapter
        bannerAdapter.set(mutableListOf(Banner(R.drawable.banner1),
            Banner(R.drawable.banner1), Banner(R.drawable.banner1)))

        val isOnline = isNetworkAvailable(requireContext())

        pizzaViewModelFactory = ViewModelFactory(RepositoryInitializer.getRepository(requireContext()))
        pizzaViewModel = ViewModelProvider(this, pizzaViewModelFactory)
            .get(PizzaViewModel::class.java)

        setCategoriesObserver()

        if (isOnline)
            getPositionsFromRemoteDataSource()
        else
            getPositionsFromLocalFataSource()


        return binding.root
    }

    private fun getPositionsFromLocalFataSource() {
        pizzaViewModel.getPositionsFromLocalDataSource()
        val liveData = pizzaViewModel.getLiveData()
        liveData.observe(requireActivity(), Observer {
            liveData.value?.let { positions->
                pizzaAdapter.set(positions)
            }
        })
    }

    private fun setCategoriesObserver(){
        categoryAdapter.getClickedLiveData().observe(requireActivity(), Observer {
            var i = 0
            while (i < binding.recyclerviewMenu.childCount){
                val holder =
                    binding.recyclerviewMenu.getChildViewHolder(binding.recyclerviewMenu.getChildAt(
                        i))
                            as CategoryAdapter.CategoryViewHolder
                if (it != i) {
                    holder.binding.categoryName.background=
                        AppCompatResources.getDrawable(requireContext(), R.drawable.round_rect)

                    holder.binding.categoryName.setTextColor( AppCompatResources.getColorStateList(requireContext(), R.color.text))
                }
                else {
                    holder.binding.categoryName.background=
                        AppCompatResources.getDrawable(requireContext(), R.drawable.button_rect_selected)
                    holder.binding.categoryName.background.alpha = 51
                    holder.binding.categoryName.setTextColor( AppCompatResources.getColorStateList(requireContext(), R.color.base))
                }
                i++
            }
        })
    }

    private fun getPositionsFromRemoteDataSource(){
        pizzaViewModel.getPositions()
        val liveData = pizzaViewModel.getLiveData()
        liveData.observe(requireActivity(), Observer{
            liveData.value?.let { positions->
                pizzaAdapter.set(positions)
                pizzaViewModel.savePositions(positions)
            }
        })
    }

    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivity = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager ?: return false

        val info = connectivity.allNetworkInfo

        for (i in info.indices) {
            if (info[i].state == NetworkInfo.State.CONNECTED) {
                return true
            }
        }
        return false
    }

    companion object {
        @JvmStatic
        fun newInstance(){}
    }
}