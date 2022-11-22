package org.mdeveloperz.common.presentation

import org.mdeveloperz.common.presentation.exception.Error

open class DialogCommand {
    open var show: Boolean = false
    var error: Error = Error.None
}
