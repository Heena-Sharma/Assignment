
package com.assignment.data

import android.content.Context
import com.google.gson.Gson
import com.assignment.util.Constants
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton


/**
 * Repository module for handling data operations.
  */
@Singleton
class RecordRepository @Inject constructor(@ApplicationContext private val appContext: Context) {

    fun getRecords(): RecordsBase {

      val obj = loadJSONFromAssets(appContext, Constants.LIST_DATA)
        val recordBase: RecordsBase = Gson().fromJson(obj, RecordsBase::class.java)

       return recordBase

          }

    private fun loadJSONFromAssets(@ApplicationContext appContext: Context, fileName: String): String {
        return appContext.assets.open(fileName).bufferedReader().use { reader ->
            reader.readText()
        }
    }
}
