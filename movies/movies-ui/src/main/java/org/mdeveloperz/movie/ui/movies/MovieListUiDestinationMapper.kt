package org.mdeveloperz.movie.ui.movies

import org.mdeveloperz.common.presentation.PresentationDestination
import org.mdeveloperz.common.ui.navigation.GlobalDestinationMapper
import org.mdeveloperz.common.ui.navigation.UiDestination
import org.mdeveloperz.common.ui.navigation.UiDestinationMapper

class MovieListUiDestinationMapper(
    private val globalDestinationMapper: GlobalDestinationMapper
) : UiDestinationMapper {
    override fun map(presentationDestination: PresentationDestination): UiDestination {
        return globalDestinationMapper.map(presentationDestination)
    }
}