package com.dogimagegenerator.app.ui.generate

import android.content.Context
import android.content.Intent
import com.dogimagegenerator.app.base.BaseMVVMActivity
import com.dogimagegenerator.app.data.event.DataType
import com.dogimagegenerator.app.databinding.ActivityGenerateBinding
import com.dogimagegenerator.app.utils.EventObserver
import com.dogimagegenerator.app.utils.hide
import com.dogimagegenerator.app.utils.loadImage
import com.dogimagegenerator.app.utils.setDebounceClickListener
import com.dogimagegenerator.app.utils.show
import org.koin.androidx.viewmodel.ext.android.viewModel

class GenerateActivity : BaseMVVMActivity<GenerateViewModel, ActivityGenerateBinding>() {
    companion object {
        fun launch(context: Context) {
            val intent = Intent(context, GenerateActivity::class.java)
            context.startActivity(intent)
        }
    }

    override val viewModel: GenerateViewModel by viewModel()

    override fun inflateViewBinding() = ActivityGenerateBinding.inflate(layoutInflater)

    override fun isBackButtonEnabled() = true

    override fun init() {
        binding.generateButton.setDebounceClickListener { viewModel.getImage() }
    }

    override fun observers() {
        viewModel.image.observe(this, EventObserver {
            showLoading(it.isLoading)
            when (it.type) {
                DataType.SUCCESS -> updateUI(it.data!!)
                DataType.ERROR -> showError(it.error!!)
                else -> {}
            }
        })
    }

    override fun showLoading(isLoading: Boolean) {
        super.showLoading(isLoading)
        if (isLoading) {
            binding.dogImageView.hide()
            binding.dogImageShimmer.startShimmer()
            binding.dogImageShimmer.show()
        } else {
            binding.dogImageShimmer.stopShimmer()
            binding.dogImageShimmer.hide()
            binding.dogImageView.show()
        }
    }

    private fun updateUI(data: String) {
        binding.dogImageView.loadImage(data)
    }
}