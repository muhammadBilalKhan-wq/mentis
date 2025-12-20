package com.socialnetwork.mentis.core.data.system

import kotlinx.coroutines.flow.Flow

/**
 * A component that monitors network connectivity.
 */
interface NetworkMonitor {
    /**
     * A flow that emits `true` if the device is connected to a network with internet access,
     * and `false` otherwise.
     */
    val isOnline: Flow<Boolean>
}
