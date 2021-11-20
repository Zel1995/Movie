package com.example.movies.ui.main.actors

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.movies.R
import com.example.movies.databinding.FragmentActorsBinding
import com.example.movies.domain.repository.ActorsRepository
import com.example.movies.ui.main.MainActivity
import com.example.movies.ui.main.viewBinding
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class ActorsFragment : Fragment(R.layout.fragment_actors) {
    private val adapter = ActorsAdapter {

    }

    @Inject
    lateinit var factory: ActorsViewModelFactory
    private val viewModel: ActorsViewModel by viewModels { factory }
    private val viewBinding: FragmentActorsBinding by viewBinding(FragmentActorsBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.actorsRecyclerView.adapter = adapter
        viewModel.fetchActors()
        initViewModel()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as? MainActivity)?.mainSubcomponent?.inject(this)
    }

    private fun initViewModel() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.actor.collect {
                it?.let { adapter.setData(it.results) }
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.error.collect {
            }
        }
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.loading.collect {
            }
        }
    }
}

class ActorsViewModelFactory @Inject constructor(private val repository: ActorsRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ActorsViewModel(repository) as T
    }
}
