package com.example.intent.network

import com.google.gson.annotations.SerializedName
import java.util.ArrayList

data class GitHubSearchModel(

    @SerializedName("items")
    val items: ArrayList<GitHubSearchItemModel>? = null
) {
    data class GitHubSearchItemModel(
        @SerializedName("id") val id: Int,
        @SerializedName("name") val name: String,
        @SerializedName("full_name") val fullName: String,
        @SerializedName("owner") val owner: Owner,
        @SerializedName("description") val description: String?,
        @SerializedName("html_url") val htmlUrl: String,
        @SerializedName("created_at") val createdAt: String,
        @SerializedName("updated_at") val updatedAt: String,
        @SerializedName("stargazers_count") val stars: Int
    )

    data class Owner(
        @SerializedName("id") val id: Int,
        @SerializedName("login") val loginName: String,
        @SerializedName("avatar_url") val thumbnailUrl: String,
        @SerializedName("html_url") val htmlUrl: String
    )
}