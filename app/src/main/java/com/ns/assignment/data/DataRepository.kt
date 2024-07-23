package com.ns.assignment.data

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.ns.assignment.util.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Repository module for handling data operations.
 */
@Singleton
class DataRepository @Inject constructor(@ApplicationContext private val appContext: Context) {


    fun getData() = flow {
        try {
            val obj = loadJSONFromAssets(appContext, Constants.LIST_DATA)
            val recordBase: DataList = Gson().fromJson(obj, DataList::class.java)
            emit(recordBase)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
    private fun loadJSONFromAssets(
        @ApplicationContext appContext: Context,
        fileName: String
    ): String {
        return appContext.assets.open(fileName).bufferedReader().use { reader ->
            reader.readText()
        }
    }

}
