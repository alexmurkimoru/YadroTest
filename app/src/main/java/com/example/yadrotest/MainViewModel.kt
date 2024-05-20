package com.example.yadrotest

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.GsonBuilder
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    private val _dataSource = MutableLiveData<DataSource>()
    var dataSource: LiveData<DataSource> = _dataSource


    private val gson = GsonBuilder()
        .setLenient()
        .create()

    private val okHttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        .build()

    private val retrofitLocationClient = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://api.openweathermap.org")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
        .create<LocationApi>()

    private val retrofitForecastClient = Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://api.open-meteo.com")
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
        .create<WeatherApi>()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun newRequest(lat: Double, lon: Double) {
        _dataSource.value = DataSource()
        getLocation(lat, lon)
        getForecast(lat, lon)
    }

    private fun getLocation(lat: Double, lon: Double) = compositeDisposable.add(
        retrofitLocationClient.getLocation(lat, lon)
            .subscribeOn(Schedulers.io())
            .map {
                it[0]
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                parseLocation(it)
            }, { Log.d("MyLog", "Errorrequest") })
    )

    private fun getForecast(lat: Double, lon: Double) = compositeDisposable.add(
        retrofitForecastClient.getForecast(lat, lon)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                parseForecast(it)
            }, { Log.d("MyLog", "Error request", it) })

    )

    private fun parseLocation(location: Location) {
        _dataSource.value = _dataSource.value?.copy(
            locationName = location.name ?: "undefined location",
            locationLocalName = location.localNames?.ruName
        )
    }

    private fun parseForecast(forecast: Forecast) {
        val futherForecast = parseForecastItems(forecast)
        _dataSource.value = _dataSource.value?.copy(
            currentTemp = forecast?.currentInfo?.temperature.toString(),
            isDay = (forecast.currentInfo?.isDay == 1),
            apparentTem = forecast.currentInfo?.apparentTemperature.toString(),
            humidity = forecast.currentInfo?.relativeHumidity.toString(),
            windSpeed = forecast.currentInfo?.windSpeed10m.toString(),
            forecast = futherForecast
        )
    }

    private fun parseForecastItems(forecast: Forecast): List<ForecastItem> {
        var list = emptyList<ForecastItem>()
        forecast.dailyInfo?.let {
            for (i in it.time.indices) {
                val transformedDate = DateConverter.convertDate(it.time[i])
                val newItem = ForecastItem(
                    transformedDate,
                    it.temperatureMin[i].toInt().toString(),
                    it.temperatureMax[i].toInt().toString()
                )
                list += newItem
            }
        }
        return list
    }

}