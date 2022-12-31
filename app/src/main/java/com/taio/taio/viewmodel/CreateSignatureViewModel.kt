package com.taio.taio.viewmodel

import androidx.lifecycle.ViewModel
import com.taio.taio.data.CreateState
import kotlinx.coroutines.flow.MutableStateFlow

class CreateSignatureViewModel: ViewModel() {
    val createState = MutableStateFlow(CreateState())

    fun onDocName(documentName: String){
        createState.value = createState.value.copy(documentName = documentName)
    }

    fun onDocDate(documentDate: String){
        createState.value = createState.value.copy(documentDate = documentDate)
    }

    fun onDocNumber(documentNumber: String){
        createState.value = createState.value.copy(documentNumber = documentNumber)
    }

    fun onDocDesc(documentDescription: String){
        createState.value = createState.value.copy(documentDescription = documentDescription)
    }

    fun isFormError(error: Boolean){
        createState.value = createState.value.copy(isFormError = error)
    }

    fun isPageOneValid():Boolean{
        return (createState.value.documentName.isNotEmpty() && createState.value.documentDate.isNotEmpty()
                && createState.value.documentDescription.isNotEmpty())
    }
}