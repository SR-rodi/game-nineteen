package com.example.nineteen_2_0

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.example.nineteen_2_0.databinding.TestBinding

enum class CustomButtonAction {
    ACTION
}

typealias onActionListener = (CustomButtonAction) -> Unit

class CustomButtonView(
    context: Context,
    attrs: AttributeSet?,
    defStileAttrs: Int,
    defStileRes: Int
) : FrameLayout(context, attrs, defStileAttrs, defStileRes) {

    constructor(context: Context, attrs: AttributeSet?, defStileAttrs: Int) : this(
        context,
        attrs,
        defStileAttrs,
        0
    )

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : this(context, null)

    private val binding: TestBinding
    private var listener: onActionListener? = null


    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.test, this, true)
        binding = TestBinding.bind(this)
        initAttrs(attrs, defStileAttrs, defStileRes)
        onCliKc()
    }

    private fun initAttrs(attrs: AttributeSet?, defStileAttrs: Int, defStileRes: Int) {
        if (attrs == null) return

        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.CustomButtonView,
            defStileAttrs,
            defStileRes
        )

        binding.apply {
            gameMode.text = typedArray.getString(R.styleable.CustomButtonView_textModeGame)

            playButton.backgroundTintList = ColorStateList.valueOf(
                typedArray.getColor(
                    R.styleable.CustomButtonView_backgroundButton,
                    Color.GRAY
                )
            )

            playButton.text = typedArray.getString(R.styleable.CustomButtonView_textButtonGame)

            image.setImageResource(
                typedArray.getResourceId(
                    R.styleable.CustomButtonView_image,
                    R.drawable.kitekat_1
                )
            )

            cardOne.backgroundTintList = ColorStateList.valueOf(
                typedArray.getColor(
                    R.styleable.CustomButtonView_backgroundCard,
                    Color.TRANSPARENT
                )
            )

        }
        typedArray.recycle()
    }

    private fun onCliKc(){
        binding.playButton.setOnClickListener {
            this.listener?.invoke(CustomButtonAction.ACTION)
        }
    }

    fun setListener(listener: onActionListener?) {
        this.listener = listener
    }

}