package com.engie.eea_tech_interview.ui

import org.mdeveloperz.common.presentation.PresentationDestination
import org.mdeveloperz.common.ui.navigation.GlobalDestinationMapper
import org.mdeveloperz.common.ui.navigation.UiDestination

class GlobalDestinationMapperImpl : GlobalDestinationMapper {
    override fun map(presentationDestination: PresentationDestination): UiDestination {
        throw IllegalArgumentException("Destination ${presentationDestination::class.java.simpleName} not found")
    }

}