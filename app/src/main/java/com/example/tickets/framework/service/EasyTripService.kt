package com.example.tickets.framework.service

import com.example.tickets.app.data.model.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface EasyTripService {

    //Cards
    @GET("card/{card_number}")
    suspend fun getCardByCardNumber(@Path("card_number") cardNumber: String): List<Card>

    @GET("transactions_of_card/{card_id}")
    suspend fun getTransactionsByCardNumber(@Path("card_id") cardId: Int): List<Transactions>

    //Transports
    @GET("transports")
    suspend fun getTransportsList() : List<Transports>

    @GET("transports/{transport_id}")
    suspend fun getTransportsById(@Path("transport_id") transportId: Int): Transports

    //NumberOfDays
    @GET("number_of_days")
    suspend fun getNumberOfDaysList() : List<NumberOfDaysOrTrips>

    @POST("number_of_days_tarif/number_of_day_id/transports")
    suspend fun getPriceByNumberOfDaysAndTransports(@Body getPrice: BodyForGetPriceByNumberOfDays)

    //NumberOfTrips
    @GET("number_of_trips")
    suspend fun getNumberOfTripsList() : List<NumberOfDaysOrTrips>

    @POST("number_of_days_tarif/number_of_trip_id/transports")
    suspend fun getPriceByNumberOfTripsAndTransports(@Body getPrice: BodyForGetPriceByNumberOfTrips)
}