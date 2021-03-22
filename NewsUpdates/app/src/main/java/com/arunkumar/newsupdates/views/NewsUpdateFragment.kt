package com.arunkumar.newsupdates.views

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.arunkumar.newsupdates.NewsUpdateViewState
import com.arunkumar.newsupdates.R
import com.arunkumar.newsupdates.adapters.NewsArticleAdapter
import com.arunkumar.newsupdates.viewmodels.NewsUpdateViewModel
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_news_update.*
import timber.log.Timber
import javax.inject.Inject

class NewsUpdateFragment : DaggerFragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var articleAdapter: NewsArticleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news_update, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("************** $viewModelFactory")
        val viewModel =
            ViewModelProvider(requireActivity(), viewModelFactory)
                .get(NewsUpdateViewModel::class.java)

        initializeRecyclerView()
        observeNewsArticleResponse(viewModel)
        viewModel.fetchNewsArticle()
    }

    private fun initializeRecyclerView() {
        rvNewsArticle.setHasFixedSize(true)
        rvNewsArticle.layoutManager = LinearLayoutManager(requireContext())
        rvNewsArticle.adapter = articleAdapter
    }

    private fun observeNewsArticleResponse(viewModel: NewsUpdateViewModel) {
        viewModel.newsArticleLiveData.observe(viewLifecycleOwner, {
            when (it) {
                is NewsUpdateViewState.ShowProgress -> {
                    Timber.d("Show progress..........")
                    progress.visibility = VISIBLE
                }
                is NewsUpdateViewState.HideProgress -> {
                    Timber.d("Hide progress..........")
                    progress.visibility = GONE
                }
                is NewsUpdateViewState.ShowNews -> {
                    viewModel.articleList = it.newsUpdateDomainModel
                    articleAdapter.setArticleNews(it.newsUpdateDomainModel) { position: Int ->
                        viewModel.selectedPosition = position
                        findNavController()
                            .navigate(R.id.action_newsUpdateFragment_to_articleDetailFragment)
                    }
                }
                is NewsUpdateViewState.Error -> {
                    Timber.d(it.error)
                }
            }
        })
    }
}
