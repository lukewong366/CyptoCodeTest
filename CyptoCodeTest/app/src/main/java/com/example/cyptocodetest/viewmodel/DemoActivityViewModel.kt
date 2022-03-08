package com.example.cyptocodetest.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cyptocodetest.database.RoomDatabaseHelper
import com.example.cyptocodetest.model.CurrencyInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import javax.inject.Inject

@SuppressLint("CheckResult")
@HiltViewModel
class DemoActivityViewModel @Inject constructor(private val application: Application) :
    ViewModel() {
    lateinit var databaseHelper: RoomDatabaseHelper
    var dataList = MutableLiveData<ArrayList<CurrencyInfo>>(ArrayList())
    var isASC = false

    init {
        viewModelScope.launch {
            Single.fromCallable {
                return@fromCallable RoomDatabaseHelper.invoke(application)
            }
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { database ->
                    databaseHelper = database
                }
        }
    }

    fun onLoadDataClick() {
        viewModelScope.launch {
            Single.fromCallable {
                return@fromCallable databaseHelper.getRoomDao().getAll()
            }
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { list ->
                    dataList.value = ArrayList(list)
                }
        }
    }

    fun onSortingDataClick() {
        viewModelScope.launch {
            Single.fromCallable {
                isASC = !isASC
                if (isASC) {
                    return@fromCallable databaseHelper.getRoomDao().getASCSorting()
                } else {
                    return@fromCallable databaseHelper.getRoomDao().getDESCSorting()
                }
            }
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { list ->
                    dataList.value = ArrayList(list)
                }
        }

    }
}