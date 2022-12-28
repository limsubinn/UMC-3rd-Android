package com.example.week5_1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.week5_1.databinding.ItemDataBinding

class DataRVAdapter: RecyclerView.Adapter<DataRVAdapter.DataViewHolder>() {

//    // SparseBooleanArray -> 정수 값들을 boolean 값들로 매핑시키는 기능을 하는 클래스
//    private val switchStatus = SparseBooleanArray()

    private val dataList = arrayListOf<Data>()
    // 데이터 클래스의 array를 정의
    private val switchStatus = arrayListOf<SwitchStatus>()

    // ViewHolder 객체
    inner class DataViewHolder(private val viewBinding: ItemDataBinding): RecyclerView.ViewHolder(viewBinding.root) {
        fun bind(data: Data, switchStatus: SwitchStatus) = with(viewBinding) {
            viewBinding.tvTitle.text = data.title
            viewBinding.tvDesc.text = data.desc

            viewBinding.swcData.isChecked = switchStatus.isChecked
            viewBinding.swcData.setOnClickListener{
                switchStatus.isChecked = viewBinding.swcData.isChecked
                notifyItemChanged(adapterPosition)
            }

//            viewBinding.swcData.isChecked = switchStatus[adapterPosition]
//            viewBinding.swcData.setOnClickListener{
//                if (!viewBinding.swcData.isChecked) // 스위치 off
//                    switchStatus.put(adapterPosition, false)
//                else // 스위치 on
//                    switchStatus.put(adapterPosition, true)
//
//                notifyItemChanged(adapterPosition)
//            }
        }
    }

    // ViewHolder 만들어질 때 실행할 동작
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val viewBinding = ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(viewBinding)
    }

    // ViewHolder가 실제로 데이터를 표시해야 할 때 호출되는 함수
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        if (holder is DataViewHolder)
            holder.bind(dataList[position], switchStatus[position])
    }

    // 표현할 Item의 총 개수
    override fun getItemCount(): Int = dataList.size
}