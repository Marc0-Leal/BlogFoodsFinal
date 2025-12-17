package com.evalenzuela.navigation

import com.evalenzuela.navigation.ui.viewmodel.MainViewModel
import org.junit.Test

class MainViewModelTest {

    @Test
    fun getItem_returnsCorrectItem() {
        val vm = MainViewModel()
        val item = vm.getItem(1)

        assert(item != null)
        assert(item!!.id == 1)
    }
}

