package com.elite.elitebank.ui.theme.viewmodels

import androidx.lifecycle.ViewModel
import com.elite.elitebank.data.model.GuideModel
import com.elite.elitebank.data.model.PopularPerson
import com.elite.elitebank.data.model.Transaction
import com.elite.elitebank.repositoriesImpl.AppImpl
import kotlinx.coroutines.flow.flow

class AppViewModel : ViewModel() {
    private var appImpl: AppImpl = AppImpl()

    val popularListFlow = flow<ArrayList<PopularPerson>> {
        emit(appImpl.getPopularPerson())
    }
    val transactionListFlow = flow<ArrayList<Transaction>> {
        emit(appImpl.getTransactions())
    }

    val guideListFlow = flow<ArrayList<GuideModel>> {
        emit(appImpl.getGuide())
    }
}