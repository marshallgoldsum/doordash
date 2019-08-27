package com.mgoldsum.dash

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mgoldsum.dash.model.RestaurantModel
import com.mgoldsum.dash.network.RestaurantService
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.doAnswer
import com.nhaarman.mockito_kotlin.whenever
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class DashRepositoryTest {

    @Rule
    @JvmField
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mockRestaurantService: RestaurantService

    @Mock
    private lateinit var mockRestaurantCall: Call<ArrayList<RestaurantModel>>

    private lateinit var dashRepository:DashRepository

    private val successfulList: ArrayList<RestaurantModel> = ArrayList(2)

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        dashRepository = DashRepository(mockRestaurantService)

        successfulList.add(RestaurantModel(0,"NAME","food","url","10 mins", "$0"))
        successfulList.add(RestaurantModel(1,"NAME1","food1","url","20 mins", "$2"))
    }

    private fun mockSuccess() {
        whenever(mockRestaurantService.getRestaurants(any(),any(),any(),any())).thenReturn(mockRestaurantCall)
        doAnswer{
            val callback = it.getArgument(0) as Callback<ArrayList<RestaurantModel>>
            callback.onResponse(mockRestaurantCall, Response.success(successfulList))
        }.`when`(mockRestaurantCall).enqueue(any())
    }

    private fun mockFailure() {
        whenever(mockRestaurantService.getRestaurants(any(),any(),any(),any())).thenReturn(mockRestaurantCall)
        doAnswer{
            val callback = it.getArgument(0) as Callback<ArrayList<RestaurantModel>>
            callback.onFailure(mockRestaurantCall, Throwable())
        }.`when`(mockRestaurantCall).enqueue(any())
    }

    @Test
    fun onSuccessAListIsReturned() {
        mockSuccess()
        val list = dashRepository.fetchRestaurants(0.0,0.0,0,50)
        assertEquals(list.value?.size, 2)
    }

    @Test
    fun onFailureListIsEmpty() {
        mockFailure()
        val list = dashRepository.fetchRestaurants(0.0,0.0,0,50)
        assertEquals(list.value?.size, 0)
    }
}