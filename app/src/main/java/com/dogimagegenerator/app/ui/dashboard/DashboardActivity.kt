package com.dogimagegenerator.app.ui.dashboard

import com.dogimagegenerator.app.base.BaseActivity
import com.dogimagegenerator.app.databinding.ActivityDashboardBinding
import com.dogimagegenerator.app.ui.generate.GenerateActivity
import com.dogimagegenerator.app.utils.setDebounceClickListener

class DashboardActivity : BaseActivity<ActivityDashboardBinding>() {

    override fun inflateViewBinding() = ActivityDashboardBinding.inflate(layoutInflater)

    override fun init() {
        binding.generateDogButton.setDebounceClickListener { GenerateActivity.launch(this) }
        binding.dogHistoryButton.setDebounceClickListener { }
    }
}