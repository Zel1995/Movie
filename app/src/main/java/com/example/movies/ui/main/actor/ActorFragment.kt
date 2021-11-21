package com.example.movies.ui.main.actor

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.movies.R
import com.example.movies.databinding.FragmentActorBinding
import com.example.movies.domain.model.actor.Actor
import com.example.movies.domain.model.actor.ActorDetails
import com.example.movies.domain.repository.ActorsRepository
import com.example.movies.ui.main.MainActivity
import com.example.movies.ui.main.categories.MoviesAdapter.Companion.BASE_IMAGE_URL
import com.example.movies.ui.main.viewBinding
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class ActorFragment : Fragment(R.layout.fragment_actor) {
    companion object {
        private const val ACTOR_ARG = "ACTOR_ARG"
        fun newInstance(actor: Actor): ActorFragment =
            ActorFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ACTOR_ARG, actor)
                }
            }
    }


    @Inject
    lateinit var factory: ActorViewModelFactory
    private val viewModel: ActorViewModel by viewModels { factory }
    private val viewBinding: FragmentActorBinding by viewBinding(FragmentActorBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val actor = arguments?.let { it.getParcelable(ACTOR_ARG) as Actor? }
        val safeActor = actor ?: return
        initViewModel()
        viewModel.fetchActor(safeActor.id.toString())
    }

    private fun initViewModel() {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.actor.collect { actor ->
                actor?.let { initViews(it) }
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

    private fun initViews(actor: ActorDetails) {
        Glide.with(viewBinding.root)
            .load(BASE_IMAGE_URL + actor.profilePath)
            .into(viewBinding.actorImageView)
        viewBinding.biographyTextView.text = actor.biography
        viewBinding.nameTextView.text = actor.name
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity() as? MainActivity)?.mainSubcomponent?.inject(this)
    }
}

class ActorViewModelFactory @Inject constructor(private val repository: ActorsRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ActorViewModel(repository) as T
    }
}