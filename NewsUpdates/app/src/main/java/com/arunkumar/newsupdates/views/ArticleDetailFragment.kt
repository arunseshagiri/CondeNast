package com.arunkumar.newsupdates.views

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.arunkumar.newsupdates.R
import com.arunkumar.newsupdates.viewmodels.NewsUpdateViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_article_detail.*
import timber.log.Timber
import javax.inject.Inject

class ArticleDetailFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_article_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("************** $viewModelFactory")
        val viewModel =
            ViewModelProvider(requireActivity(), viewModelFactory)
                .get(NewsUpdateViewModel::class.java)

        val article = viewModel.articleList[viewModel.selectedPosition]

        Glide.with(view)
            .load(article.imageUrl)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .placeholder(R.drawable.ic_place_holder)
            .into(ivNewsImage)

        title.text = article.title
        content.text = article.content
    }
}
