
package com.assignment.data



 data class RecordsBase(
    val status: Int,
    val message: String,
    val data: RecordsMain

)

data class Records(
    val id: String,
    val title: String,
    val shortDescription: String,
    val collectedValue: String,
    val totalValue: String,
    val startDate: String,
    val endDate: String,
    val mainImageURL: String="",
    val list: MutableList<Records>
)
data class RecordsMain(
    val totalRecords: Int,
    val record: MutableList<Records>

    )