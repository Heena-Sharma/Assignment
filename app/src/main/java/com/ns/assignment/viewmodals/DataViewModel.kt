package com.ns.assignment.viewmodals

import androidx.lifecycle.*
import com.ns.assignment.data.DataRepository
import com.ns.assignment.data.Data
import com.ns.assignment.data.DataList
import com.ns.assignment.data.DataMain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * The ViewModel for [ListFragment].
 */
@HiltViewModel
class DataViewModel @Inject internal constructor(
    val recordRepository: DataRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private var dataList = MutableLiveData<List<Data>>()
    private var dataMain = MutableLiveData<DataMain>()

    var carouselList: MutableLiveData<List<Data>> = MutableLiveData()
    val selectedRecords: MutableLiveData<List<Data>> = dataList
    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    init {
        viewModelScope.launch {
            viewModelScope.launch {
                recordRepository.getData().collect { _data ->
                    dataMain.value = _data.data
                    dataList.value = dataMain.value!!.dataSet
                    carouselList.postValue(dataMain.value!!.dataSet)
                }
            }
        }
    }

    fun postListData(carouselData: List<Data>) {
        selectedRecords.postValue(carouselData)
    }

    fun getData(position: Int): List<Data> {
        dataList.value = dataMain.value!!.dataSet
        return dataList.value?.get(position)?.list ?: listOf()
    }
}

