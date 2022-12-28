package com.example.week5_2

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.week5_2.databinding.ItemDataBinding

class DataRVAdapter(private val dataList: ArrayList<Data>): RecyclerView.Adapter<DataRVAdapter.DataViewHolder>() {

    // ViewHolder 객체
    inner class DataViewHolder(private val binding: ItemDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Data) {
            binding.tvMemo.text = data.memo
        }
    }

    // ViewHolder 만들어질 때 실행할 동작
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val binding =
            ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(binding)
    }

    // ViewHolder가 실제로 데이터를 표시해야 할 때 호출되는 함수
    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(dataList[position])

        // 아이템 클릭 리스너 -> 데이터 삭제
        holder.itemView.setOnClickListener {
            dataList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, itemCount)
        }
    }


    // 표현할 Item의 총 개수
    override fun getItemCount(): Int = dataList.size
}