package com.mariana.catapichallenge.catlist.data.remote.respond

import kotlinx.serialization.Serializable

@Serializable
data class Weight (
    val imperial: String = "",
    val metric: String = ""
)
