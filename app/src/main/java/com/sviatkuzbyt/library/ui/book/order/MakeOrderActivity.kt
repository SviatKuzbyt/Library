package com.sviatkuzbyt.library.ui.book.order

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sviatkuzbyt.library.R
import com.sviatkuzbyt.library.databinding.OrderLayoutBinding
import com.sviatkuzbyt.library.ui.elements.makeShortToast
import com.sviatkuzbyt.library.ui.elements.makeToast
import com.sviatkuzbyt.library.ui.elements.recycleradapters.LabelDataAdapter

class MakeOrderActivity : AppCompatActivity() {
    private lateinit var binding: OrderLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = OrderLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: MakeOrderViewModel by viewModels {
            MakeOrderViewModel.factory(
                intent.getLongExtra("id", 1),
                intent.getStringExtra("name") ?: "Load name error",
                this.application)
        }

        binding.orderTittle.text = getString(R.string.order_book)
        binding.recyclerInfoOrder.layoutManager = LinearLayoutManager(this)

        viewModel.dataList.observe(this){
            binding.recyclerInfoOrder.adapter = LabelDataAdapter(it, this)
        }

        viewModel.successful.observe(this){
            if(it){
                makeShortToast(R.string.order_done, this)
                makeToast(R.string.order_detail, this)
            } else
                makeToast(R.string.error, this)

            finish()
        }

        binding.buttonOrderConfirm.setOnClickListener {
            viewModel.makeOrder()
        }

        binding.buttonOrderCancel.setOnClickListener { finish() }
    }
}