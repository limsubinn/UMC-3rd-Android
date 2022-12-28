package com.example.week8

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.week8.databinding.ItemDataBinding

class DataRVAdapter(private val dataList: ArrayList<Memo>): RecyclerView.Adapter<DataRVAdapter.DataViewHolder>() {

    // 아이템 클릭 인터페이스
    interface ItemClickListener {
        fun onItemClick(view: View, position: Int)
        fun onFavoriteClick(view: View, position: Int)
    }

    // 아이템 클릭 리스너
    private lateinit var itemClickListner: ItemClickListener

    // 아이템 클릭 리스너 등록
    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListner = itemClickListener
    }

    // ViewHolder 객체
    inner class DataViewHolder(val binding: ItemDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Memo) {
            binding.tvMemo.text = data.memo

            if (data.heart == 0) {
                binding.btnHeart.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            } else {
                binding.btnHeart.setImageResource(R.drawable.ic_baseline_favorite_24)
            }
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

        // 아이템 클릭 리스너
        holder.itemView.setOnClickListener {
            itemClickListner.onItemClick(it, position)
        }

        // 즐겨찾기 버튼 클릭 리스너 (추가)
        holder.binding.btnHeart.setOnClickListener {
                holder.binding.btnHeart.setImageResource(R.drawable.ic_baseline_favorite_24)
                itemClickListner.onFavoriteClick(it, position)
        }

    }


    // 표현할 Item의 총 개수
    override fun getItemCount(): Int = dataList.size
}
