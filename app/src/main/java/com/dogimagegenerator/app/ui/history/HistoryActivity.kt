package com.dogimagegenerator.app.ui.history

import android.content.Context
import android.content.Intent
import com.dogimagegenerator.app.base.BaseMVVMActivity
import com.dogimagegenerator.app.databinding.ActivityHistoryBinding
import com.dogimagegenerator.app.utils.EventObserver
import com.dogimagegenerator.app.utils.setDebounceClickListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryActivity : BaseMVVMActivity<HistoryViewModel, ActivityHistoryBinding>() {
    companion object {
        fun launch(context: Context) {
            val intent = Intent(context, HistoryActivity::class.java)
            context.startActivity(intent)
        }
    }

    override val viewModel: HistoryViewModel by viewModel()

    override fun inflateViewBinding() = ActivityHistoryBinding.inflate(layoutInflater)

    override fun isBackButtonEnabled() = true

    override fun init() {
        viewModel.getImages()
        binding.clearButton.setDebounceClickListener { viewModel.clearDogs() }
    }

    override fun observers() {
        viewModel.images.observe(this, EventObserver { updateUI(it) })
    }

    private fun updateUI(images: List<String>) {

    }
}