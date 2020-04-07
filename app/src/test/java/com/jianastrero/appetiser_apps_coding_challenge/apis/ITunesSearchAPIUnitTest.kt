package com.jianastrero.appetiser_apps_coding_challenge.apis

import com.jianastrero.appetiser_apps_coding_challenge.ManagedCoroutineScope
import com.jianastrero.appetiser_apps_coding_challenge.TestScope
import com.jianastrero.appetiser_apps_coding_challenge.singletons.Retrofit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class ITunesSearchAPIUnitTest {

    private val testDispatcher = TestCoroutineDispatcher()
    private val managedCoroutineScope: ManagedCoroutineScope = TestScope(testDispatcher)

    private val searchAPI: ITunesSearchAPI = Retrofit.create()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @Test
    fun search() = runBlockingTest {
        val x = searchAPI.search("star", "au", "movie")
        println(x)
    }
}