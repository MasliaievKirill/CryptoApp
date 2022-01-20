package com.masliaiev.cryptoapp.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.masliaiev.cryptoapp.R
import com.squareup.picasso.Picasso

class CoinDetailActivity : AppCompatActivity() {

    private lateinit var viewModel: CoinViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin_detail)
        val ivLogoCoinDetail: ImageView = findViewById(R.id.ivLogoCoinDetail)
        val tvFromSymbol: TextView = findViewById(R.id.tvFromSymbol)
        val tvToSymbol: TextView = findViewById(R.id.tvToSymbol)
        val tvPrice: TextView = findViewById(R.id.tvPrice)
        val tvMinPrice: TextView = findViewById(R.id.tvMinPrice)
        val tvMaxPrice: TextView = findViewById(R.id.tvMaxPrice)
        val tvLastMarket: TextView = findViewById(R.id.tvLastMarket)
        val tvLastUpdate: TextView = findViewById(R.id.tvLastUpdate)
        if (!intent.hasExtra(EXTRA_FROM_SYMBOL)) {
            finish()
            return
        }
        val fromSymbol = intent.getStringExtra(EXTRA_FROM_SYMBOL) ?: EMPTY_SYMBOL
        viewModel = ViewModelProvider(this)[CoinViewModel(application)::class.java]
        viewModel.getDetailInfo(fromSymbol).observe(this) {
            tvPrice.text = it.price.toString()
            tvMinPrice.text = it.lowDay.toString()
            tvMaxPrice.text = it.highDay.toString()
            tvLastMarket.text = it.lastMarket.toString()
            tvLastUpdate.text = it.lastUpdate
            tvFromSymbol.text = it.fromSymbol
            tvToSymbol.text = it.toSymbol
            Picasso.get().load(it.imageUrl).into(ivLogoCoinDetail)


        }

    }

    companion object {
        private const val EXTRA_FROM_SYMBOL = "fSym"
        private const val EMPTY_SYMBOL = ""

        fun newIntent(context: Context, fromSymbol: String): Intent {
            val intent = Intent(context, CoinDetailActivity::class.java)
            intent.putExtra(EXTRA_FROM_SYMBOL, fromSymbol)
            return intent
        }
    }
}