package com.elite.elitebank.domain

import com.elite.elitebank.data.model.GuideModel
import com.elite.elitebank.data.model.PopularPerson
import com.elite.elitebank.data.model.Transaction

interface AppRepo {
    fun getPopularPerson(): ArrayList<PopularPerson>
    fun getTransactions(): ArrayList<Transaction>
    fun getGuide(): ArrayList<GuideModel>
}