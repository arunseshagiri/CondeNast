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
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class NewsUpdateFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_news_update, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewmodel =
            ViewModelProvider(this, viewModelFactory)
                .get(NewsUpdateViewModel::class.java)

        viewmodel.fetchNewsArticle()
    }
}
