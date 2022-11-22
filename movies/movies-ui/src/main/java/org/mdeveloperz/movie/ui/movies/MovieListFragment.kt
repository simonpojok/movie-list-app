package org.mdeveloperz.movie.ui.movies

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import org.mdeveloperz.common.presentation.DialogCommand
import org.mdeveloperz.common.ui.BaseFragment
import org.mdeveloperz.common.ui.ItemsListAdapter
import org.mdeveloperz.movie.ui.R
import org.mdeveloperz.movie.ui.mapper.MoviePresentationToUiMapper
import org.mdeveloperz.movie.ui.model.MovieUiModel
import org.mdeveloperz.movies.presentation.MovieListViewModel
import org.mdeveloperz.movies.presentation.MovieListViewState
import javax.inject.Inject

@AndroidEntryPoint
class MovieListFragment : BaseFragment<MovieListViewState, DialogCommand>() {
    override val layout: Int = R.layout.fragment_movie_list

    private val moviesListView: RecyclerView get() = requireView().findViewById(R.id.movies_list_items)
    private val searchKeywordInput: EditText get() = requireView().findViewById(R.id.movies_list_search_keyword)
    private val clearSearchKeyword: ImageView get() = requireView().findViewById(R.id.movies_list_clear_keyword)

    @Inject
    override lateinit var destinationMapper: MovieListUiDestinationMapper

    @Inject
    lateinit var moviePresentationToUiMapper: MoviePresentationToUiMapper

    override val viewModel: MovieListViewModel by viewModels()

    private val moviesAdapter: ItemsListAdapter<MovieUiModel> by lazy {
        ItemsListAdapter { parent, _ ->
            MovieItemViewHolder(
                itemView = layoutInflater.inflate(R.layout.view_movie_item, parent, false),
                onItemClickAction = ::onMovieItemClickAction
            )

        }
    }

    private val searchKeywordWatcher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

        override fun onTextChanged(
            exteredText: CharSequence?,
            start: Int,
            before: Int,
            count: Int
        ) {
            val keyword = exteredText.toString()
            if (keyword.isNotEmpty()) {
                viewModel.onSearchMoviesAction(keyword)
            } else {
                moviesAdapter.setAdapterItems(emptyList())
            }
        }

        override fun afterTextChanged(s: Editable?) = Unit
    }

    private fun onMovieItemClickAction(movieUiModel: MovieUiModel) {

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clearSearchKeyword.setOnClickListener {
            searchKeywordInput.setText("")
        }
        searchKeywordInput.addTextChangedListener(searchKeywordWatcher)
        moviesListView.adapter = moviesAdapter
    }

    override fun renderViewState(viewState: MovieListViewState) {
        super.renderViewState(viewState)
        val movies = viewState.movies.map(moviePresentationToUiMapper::toUi)
        moviesAdapter.setAdapterItems(movies)
    }
}
