package org.mdeveloperz.common.ui.navigation

import org.mdeveloperz.common.presentation.PresentationDestination


interface UiDestinationMapper {
    fun map(presentationDestination: PresentationDestination): UiDestination

    fun throwInvalidDestinationException(currentDestinationClass: PresentationDestination): Nothing =
        throw IllegalArgumentException(
            "Destination ${currentDestinationClass::class.java.name} is not supported. " +
                    "Please specify appropriate mapper"
        )
}
