package com.example.myapplication.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.NumberPicker
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {
  
  private var _binding: FragmentDashboardBinding? = null
  
  // This property is only valid between onCreateView and
  // onDestroyView.
  private val binding get() = _binding!!
  
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val dashboardViewModel =
      ViewModelProvider(this).get(DashboardViewModel::class.java)
    
    _binding = FragmentDashboardBinding.inflate(inflater, container, false)
    val root: View = binding.root
    
//     number picker 설정
    dashboardViewModel.pickerValues.observe(viewLifecycleOwner) { values ->
      binding.numberPicker.minValue = 0
      binding.numberPicker.maxValue = values.size - 1
      binding.numberPicker.displayedValues = values
      binding.numberPicker.wrapSelectorWheel = true
    }
    
//    선택된 인덱스 관찰
    dashboardViewModel.selectedIndex.observe(viewLifecycleOwner) { index ->
      binding.numberPicker.value = index
    }
    
//    변경 이벤트 발생 시
    binding.numberPicker.setOnValueChangedListener {_, _, newVal ->
      dashboardViewModel.updateSelectedIndex(newVal)
    }
    
    //  textView 설정
    val textView: TextView = binding.textDashboard
    dashboardViewModel.text.observe(viewLifecycleOwner) {
      textView.text = it
    }
    
    return root
  }
  
  override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
  }
}