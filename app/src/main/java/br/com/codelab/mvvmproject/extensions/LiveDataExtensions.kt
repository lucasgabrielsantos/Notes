package br.com.codelab.mvvmproject.extensions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

fun <T> LiveData<T>.toMutableLiveData(): MutableLiveData<T> {
    val mutableLiveData = MutableLiveData<T>()
    mutableLiveData.value = this.value
    return mutableLiveData
}