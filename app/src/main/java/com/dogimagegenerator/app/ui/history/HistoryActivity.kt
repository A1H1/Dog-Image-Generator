package com.dogimagegenerator.app.ui.history

import android.content.Context
import android.content.Intent
import androidx.lifecycle.lifecycleScope
import com.dogimagegenerator.app.base.BaseMVVMActivity
import com.dogimagegenerator.app.databinding.ActivityHistoryBinding
import com.dogimagegenerator.app.utils.EventObserver
import com.dogimagegenerator.app.utils.hide
import com.dogimagegenerator.app.utils.setDebounceClickListener
import com.dogimagegenerator.app.utils.show
import org.koin.androidx.viewmodel.ext.android.viewModel

class HistoryActivity : BaseMVVMActivity<HistoryViewModel, ActivityHistoryBinding>() {
    companion object {
        fun launch(context: Context) {
            val intent = Intent(context, HistoryActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val adapter: HistoryAdapter by lazy { HistoryAdapter(lifecycleScope) }

    override val viewModel: HistoryViewModel by viewModel()

    override fun inflateViewBinding() = ActivityHistoryBinding.inflate(layoutInflater)

    override fun isBackButtonEnabled() = true

    override fun init() {
        viewModel.getImages()
        binding.clearButton.setDebounceClickListener { viewModel.clearDogs() }
        binding.historyRV.adapter = adapter
    }

    override fun observers() {
        viewModel.images.observe(this, EventObserver { updateUI(it) })
    }

    private fun updateUI(images: List<String>) {
        if (images.isEmpty()) {
            binding.emptyView.show()
            binding.historyRV.hide()
        } else {
            binding.emptyView.hide()
            binding.historyRV.show()
        }
        adapter.setItems(images)
    }
}