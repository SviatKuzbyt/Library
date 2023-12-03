package com.sviatkuzbyt.library.ui.book.info

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
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
import com.bumptech.glide.request.RequestOptions
import com.sviatkuzbyt.library.R
import com.sviatkuzbyt.library.databinding.ActivityBookBinding
import com.sviatkuzbyt.library.ui.elements.recycleradapters.LabelDataAdapter
import com.sviatkuzbyt.library.ui.main.catalog.category.CategoryViewModel
import jp.wasabeef.glide.transformations.BlurTransformation
import jp.wasabeef.glide.transformations.ColorFilterTransformation


class BookActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookBinding
    private lateinit var viewModel: Lazy<BookViewModel>
    private var toolBarTitle = ""

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        binding = ActivityBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.orderButton) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                bottomMargin = insets.bottom + 16
            }
            WindowInsetsCompat.CONSUMED
        }

        ViewCompat.setOnApplyWindowInsetsListener(binding.bookToolbar) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(0, insets.top, 0, 0)
            WindowInsetsCompat.CONSUMED
        }

//        setSupportActionBar(binding.bookToolbar)

        val typedValue = TypedValue()
        theme.resolveAttribute(android.R.attr.windowBackground, typedValue, true)
        val color = typedValue.resourceId
        val ct = android.R.color.transparent

        val rect = Rect()
        var isToolBarTransparent = true
        binding.scrollBook.setOnScrollChangeListener { _, _, _, _, _ ->
            if(binding.textLabel.getLocalVisibleRect(rect)){
                if(!isToolBarTransparent) {
                    binding.bookToolbar.title = ""
                    colorAnim(color, ct)
                    isToolBarTransparent = true
                }
            } else if(isToolBarTransparent) {
                colorAnim(ct, color)
                binding.bookToolbar.title = toolBarTitle
                isToolBarTransparent = false
            }
        }

        binding.recylerBookInfo.layoutManager = LinearLayoutManager(this)

        viewModel = viewModels { BookViewModel.factory(intent.getLongExtra("id", 1), this.application) }

        viewModel.value.label.observe(this){
            binding.textLabel.text = it
            toolBarTitle = it
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

        binding.bookToolbar.setNavigationOnClickListener {
            finish()
        }
    }

    private fun colorAnim(s: Int, e: Int){
        val startColor = getColor(s)
        val endColor = getColor(e)
        val colorAnimator = ObjectAnimator.ofObject(
            binding.bookToolbar,
            "backgroundColor",
            ArgbEvaluator(),
            startColor,
            endColor
        )

        colorAnimator.duration = 500
        colorAnimator.start()


    }

    @SuppressLint("CheckResult")
    private fun blurBackground(imageView: ImageView, image: Bitmap){
//        Glide.with(this)
//            .load(image)
//            .apply(RequestOptions().transform(BlurTransformation(12, 3)))
//            .apply(RequestOptions().transform(ColorFilterTransformation(R.color.black_transparent)))
//            .into(imageView)

        Glide.with(this)
            .load(image) // Replace with your image resource or URL
            .apply {
//                transform(ColorFilterTransformation(R.color.black_transparent)) // Adjust alpha for darkness
                transform(BlurTransformation(40)) // Adjust the radius for blur effect
            }
            .into(imageView)
    }
}