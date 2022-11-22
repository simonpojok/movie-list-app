package org.mdeveloperz.movie.ui.movies

import android.view.View
import android.widget.TextView
import org.mdeveloperz.common.ui.ItemsListAdapter
import org.mdeveloperz.movie.ui.R
import org.mdeveloperz.movie.ui.model.MovieUiModel

class MovieItemViewHolder(
    itemView: View,
    private val onItemClickAction: (MovieUiModel) -> Unit
) : ItemsListAdapter.ViewHolder<MovieUiModel>(itemView) {
    private val titleView: TextView by lazy { itemView.findViewById(R.id.movie_item_item) }
    private val descriptionView: TextView by lazy { itemView.findViewById(R.id.movie_item_description) }
    private val container: View by lazy { itemView.findViewById(R.id.movie_item_root_container) }
    override fun bind(item: MovieUiModel) {
        titleView.text = item.title
        descriptionView.text = item.overview
        container.setOnClickListener { onItemClickAction(item) }
    }
}