package com.williamtan.mangadexsdk.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.williamtan.mangadexlibrary.data.enums.ApiResponse
import com.williamtan.mangadexlibrary.domain.interactors.GetMangaListUseCase
import com.williamtan.mangadexlibrary.domain.model.Manga
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application,
    private val getMangaListUseCase: GetMangaListUseCase
) : AndroidViewModel(application) {
    private val mangaList = MutableLiveData<List<Manga>>(emptyList())
    private val isLoading = MutableLiveData(false)
    private val isError = MutableLiveData<String>()

    fun loadMangaList() {
        viewModelScope.launch(Dispatchers.IO) {
            getMangaListUseCase().collect {
                when (it) {
                    is ApiResponse.Success -> mangaList.postValue(it.data ?: emptyList())
                    is ApiResponse.Loading -> isLoading.postValue(it.isLoading)
                    is ApiResponse.Error -> isError.postValue(it.message ?: "")
                }
            }
        }
    }

    fun getMangaListLiveData(): LiveData<List<Manga>> = mangaList
}