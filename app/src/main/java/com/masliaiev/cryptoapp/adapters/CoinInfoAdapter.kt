package com.masliaiev.cryptoapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.masliaiev.cryptoapp.R
import com.masliaiev.cryptoapp.pojo.CoinPriceInfo
import com.squareup.picasso.Picasso

class CoinInfoAdapter (private val context: Context) : RecyclerView.Adapter<CoinInfoAdapter.CoinInfoViewHolder>() {

    var coinInfoList: List<CoinPriceInfo> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onCoinClickListener: OnCoinClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinInfoViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_coin_info, parent, false)
        return CoinInfoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CoinInfoViewHolder, position: Int) {
        val coin = coinInfoList[position]
        with(holder) {
            val symbolsTemplate = context.resources.getString(R.string.symbols_template)
            val lastUpdateTemplate = context.resources.getString(R.string.last_update_template)
            tvSymbols.text = String.format(symbolsTemplate, coin.fromSymbol, coin.toSymbol)
            tvPrice.text = coin.price.toString()
            tvRefreshTime.text = String.format(lastUpdateTemplate, coin.getFormattedTime())
            Picasso.get().load(coin.getFullImageUrl()).into(ivLogoCoin)
        }
        holder.itemView.setOnClickListener {
            onCoinClickListener?.onCoinClick(coin)
        }
    }

    override fun getItemCount() = this.coinInfoList.size


    inner class CoinInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivLogoCoin: ImageView = itemView.findViewById(R.id.ivLogoCoin)
        val tvSymbols: TextView = itemView.findViewById(R.id.tvSymbols)
        val tvPrice: TextView = itemView.findViewById(R.id.tvPrice)
        val tvRefreshTime: TextView = itemView.findViewById(R.id.tvRefreshTime)


    }

    interface OnCoinClickListener {
        fun onCoinClick (coinPriceInfo: CoinPriceInfo)
    }
}