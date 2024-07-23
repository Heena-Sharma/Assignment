
package com.ns.assignment.data
  data class DataList(
    val status: Int,
    val message: String,
    val data: DataMain

)

data class Data(
    val id: String,
    val title: String,
    val shortDescription: String,
    val collectedValue: String,
    val totalValue: String,
    val startDate: String,
    val endDate: String,
    val mainImageURL: String="",
    val list: MutableList<Data>

)
data class DataMain(
    val totalRecords: Int,
    val dataSet: MutableList<Data>

    )