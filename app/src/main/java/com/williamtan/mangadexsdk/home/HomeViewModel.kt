package com.williamtan.mangadexsdk.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.williamtan.mangadexlibrary.data.enums.ApiResponse
import com.williamtan.mangadexlibrary.domain.interactors.GetMangaCoverArtUseCase
import com.williamtan.mangadexlibrary.domain.interactors.GetLatestMangaListUseCase
import com.williamtan.mangadexlibrary.domain.model.Manga
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    application: Application,
    private val getLatestMangaListUseCase: GetLatestMangaListUseCase,
    private val getMangaCoverArtUseCase: GetMangaCoverArtUseCase
) : AndroidViewModel(application) {
    private val mangaList = MutableLiveData<List<Manga>>(emptyList())
    private val isLoading = MutableLiveData(false)
    private val isError = MutableLiveData<String>()

    private val coverArtPathAvailableSharedFlow = MutableSharedFlow<Pair<Manga, String>>()
    private var offset: Int = 0
    private val limit: Int = 26

    fun loadMangaList(clearData: Boolean) {
        if (clearData) {
            offset = 0
            mangaList.postValue(emptyList())
        }

        viewModelScope.launch(Dispatchers.IO) {
            getLatestMangaListUseCase(limit, offset).collect {
                when (it) {
                    is ApiResponse.Success -> {
                        val mangaListData = it.data ?: emptyList()
                        mangaList.postValue(mangaListData)
                        offset += mangaListData.size

                        mangaListData.forEach { manga ->
                            manga.coverArtId?.let {
                                getMangaCoverArtUseCase(it).collect {
                                    it.data?.let { coverArt ->
                                        val fileName = coverArt.fileName
                                        val coverArtPath =
                                            "https://uploads.mangadex.org/covers/${manga.id}/$fileName.256.jpg"

                                        coverArtPathAvailableSharedFlow.emit(manga to coverArtPath)
                                    }
                                }
                            }
                        }
                    }
                    is ApiResponse.Loading -> isLoading.postValue(it.isLoading)
                    is ApiResponse.Error -> isError.postValue(it.message ?: "")
                }
            }
        }
    }

    fun getMangaListLiveData(): LiveData<List<Manga>> = mangaList
    fun getIsLoadingLiveData(): LiveData<Boolean> = isLoading
    fun getCoverArtPathAvailableSharedFlow() = coverArtPathAvailableSharedFlow
}