package com.sviatkuzbyt.library.ui.book.info

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Rect
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.sviatkuzbyt.library.R
import com.sviatkuzbyt.library.databinding.ActivityBookBinding
import com.sviatkuzbyt.library.ui.book.order.MakeOrderActivity
import com.sviatkuzbyt.library.ui.elements.makeToast
import com.sviatkuzbyt.library.ui.elements.recycleradapters.LabelDataAdapter
import jp.wasabeef.glide.transformations.BlurTransformation

class BookActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookBinding
    private lateinit var viewModel: Lazy<BookViewModel>
    private val rect = Rect()

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        binding = ActivityBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = viewModels {
            BookViewModel.factory(intent.getLongExtra("id", 1), this.application)
        }
        setViews()
        setViewModel()
    }

    private fun setViews(){
        setButtonMarginBottom()
        setChangeToolBar()
        binding.recylerBookInfo.layoutManager = LinearLayoutManager(this)

        binding.bookToolbar.setNavigationOnClickListener { finishAfterTransition() }

        binding.orderButton.setOnClickListener {
            startActivity(Intent(this, MakeOrderActivity::class.java))
        }
    }

    private fun setButtonMarginBottom() = ViewCompat.setOnApplyWindowInsetsListener(binding.orderButton) { view, windowInsets ->
        val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
        view.updateLayoutParams<ViewGroup.MarginLayoutParams> {
            bottomMargin = insets.bottom + dpToPx(16.0f)
        }
        WindowInsetsCompat.CONSUMED
    }

    private fun dpToPx(dp: Float): Int {
        val scale = resources.displayMetrics.density
        return (dp * scale + 0.5f).toInt()
    }

    private fun setChangeToolBar()
    = binding.scrollBook.setOnScrollChangeListener { _, _, _, _, _ ->

        if(binding.textLabel.getLocalVisibleRect(rect)){
            if(!binding.bookToolbar.getIsTransparent())
                binding.bookToolbar.setTransparentBackground()
        }
        else if(binding.bookToolbar.getIsTransparent())
            binding.bookToolbar.setColorBackground()
    }

    private fun setViewModel(){
        viewModel.value.label.observe(this){
            binding.textLabel.text = it
            binding.bookToolbar.setTitleForColorBackground(it)
        }

        viewModel.value.infoList.observe(this){
            binding.recylerBookInfo.adapter = LabelDataAdapter(it, this)
        }

        viewModel.value.description.observe(this){
            binding.textDescription.text = it
        }

        viewModel.value.image.observe(this){
            if(it != null){
                binding.bookInfoImage.setImageBitmap(it)
                blurBackground(binding.imageBackground, it)
            } else binding.bookInfoImage.setBackgroundResource(R.drawable.no_image)
        }

        viewModel.value.error.observe(this){
            makeToast(it, this)
        }
    }

    @SuppressLint("CheckResult")
    private fun blurBackground(imageView: ImageView, image: Bitmap){
        Glide.with(this).load(image)
            .apply {
                transform(BlurTransformation(40))
            }.into(imageView)
    }
}