package com.example.quickquiz.data

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

val cookieJar = object : CookieJar {
    private val cookieStore = mutableMapOf<HttpUrl, List<Cookie>>()

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        cookieStore[url] = cookies
    }

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        return cookieStore[url] ?: emptyList()
    }
}
