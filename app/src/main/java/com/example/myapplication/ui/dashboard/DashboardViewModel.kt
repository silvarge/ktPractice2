package com.example.myapplication.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {
  
  //  선택 가능 값 설정
  private val _pickerValues = MutableLiveData<Array<String>>().apply {
    value = arrayOf("하나", "둘", "셋", "넷")
  }
  val pickerValues: LiveData<Array<String>> = _pickerValues
  
  //  현재 선택한 값의 인덱스 데이터
  private val _selectedIndex = MutableLiveData<Int>().apply {
    value = 0 // 기본값 (Default Index)
  }
  val selectedIndex: LiveData<Int> = _selectedIndex
  
  
  private val _text = MediatorLiveData<String>().apply {
    addSource(_selectedIndex) { index ->
      value = _pickerValues.value?.get(index) ?: "선택된 값이 없습닏니다"
    }
  }
  val text: LiveData<String> = _text
  
  //  인덱스 업데이트
  fun updateSelectedIndex(index: Int) {
    _selectedIndex.value = index
  }
  
}

