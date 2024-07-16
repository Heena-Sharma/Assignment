

package com.assignment.viewmodals

import androidx.lifecycle.*
import com.assignment.data.RecordRepository
import com.assignment.data.Records
import com.assignment.data.RecordsMain
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * The ViewModel for [RecordListFragment].
 */
@HiltViewModel
class RecordListViewModel @Inject internal constructor(
    recordRepository: RecordRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    var records = MutableLiveData<List<Records>>()

    private var recordMain = MutableLiveData<RecordsMain>()
    val record=recordRepository.getRecords()

     var carouselList : MutableLiveData<List<Records>> = MutableLiveData()
    val selectedRecords: MutableLiveData<List<Records>> = records
    init {
        viewModelScope.launch {
            recordMain.value=(record!!.data)
            records.value=recordMain.value!!.record

            carouselList.postValue(recordMain.value!!.record)
        }
    }

    fun postListData(carouselData: List<Records>) {
        selectedRecords.postValue(carouselData)
    }

    fun getData(position: Int): List<Records> {
        records.value=recordMain.value!!.record
        return records.value?.get(position)?.list ?: listOf()
    }
}

