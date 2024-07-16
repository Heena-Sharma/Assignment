package com.ns.assignment.adapters

import com.assignment.data.Records
import junit.framework.TestCase
import org.junit.Before
import org.junit.Test

class RecordListAdapterTest : TestCase() {

    private lateinit var records: Records

    @Before
    override fun setUp() {
        records = Records("1", "Title1", "description1", "100", "200", "22/06/2018","22/06/2018","imageUrl")
    }

    @Test
    fun test_default_values() {
        val records = Records("2", "Title2", "description2", "150", "200", "22/06/2019","22/06/2019","imageUrl")

    }

    public override fun tearDown() {}

    fun testSubmitList() {}
}