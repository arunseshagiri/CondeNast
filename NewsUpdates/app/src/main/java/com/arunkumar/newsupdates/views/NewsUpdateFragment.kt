package com.arunkumar.newsupdates.views

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.arunkumar.newsupdates.NewsUpdateViewState
import com.arunkumar.newsupdates.R
import com.arunkumar.newsupdates.adapters.NewsArticleAdapter
import com.arunkumar.newsupdates.viewmodels.NewsUpdateViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_news_update.*
import timber.log.Timber
import javax.inject.Inject

class NewsUpdateFragment : Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var articleAdapter: NewsArticleAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news_update, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val viewModel =
            ViewModelProvider(this, viewModelFactory)
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
                }
                is NewsUpdateViewState.HideProgress -> {
                    Timber.d("Hide progress..........")
                }
                is NewsUpdateViewState.ShowNews -> {
                    articleAdapter.setArticleNews(it.newsUpdateDomainModel)
                }
                is NewsUpdateViewState.Error -> {
                    Timber.d(it.error)
                }
            }
        })
    }
}
