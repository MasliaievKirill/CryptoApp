package com.masliaiev.cryptoapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.masliaiev.cryptoapp.R
import com.masliaiev.cryptoapp.presentation.adapters.CoinInfoAdapter
import com.masliaiev.cryptoapp.data.network.model.CoinInfoDto
import com.masliaiev.cryptoapp.domain.CoinInfo

class CoinPriceListActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel
    private lateinit var rvCoinPriceList: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_price_list)
        viewModel = ViewModelProvider(this)[CoinViewModel(application)::class.java]
        val adapter = CoinInfoAdapter(this)
        adapter.onCoinClickListener = object : CoinInfoAdapter.OnCoinClickListener {
            override fun onCoinClick(coinInfoDto: CoinInfo) {
                val intent = CoinDetailActivity.newIntent(
                    this@CoinPriceListActivity,
                    coinInfoDto.fromSymbol
                )
                startActivity(intent)
            }
        }
        rvCoinPriceList = findViewById(R.id.rvCoinPriceList)
        rvCoinPriceList.adapter = adapter
        viewModel.coinInfoList.observe(this){
            adapter.coinInfoDtoList = it
        }
    }
}