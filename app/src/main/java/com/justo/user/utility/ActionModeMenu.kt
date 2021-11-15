package com.justo.user.utility

import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.annotation.MenuRes
import com.justo.user.R

class ActionModeMenu(
    private val clickSelect: () -> Unit,
    private val clickBack : () -> Unit
) : ActionMode.Callback {

    private var mode: ActionMode? = null

    @MenuRes
    private var menuResId :  Int  =  0

    override fun onActionItemClicked(mode: ActionMode?, item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.select -> {
                clickSelect()
            }
        }
        return true
    }

    override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
        this.mode = mode
        mode?.let {
            mode.menuInflater.inflate(menuResId, menu)
        }
        return true
    }

    override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
        return false
    }

    override fun onDestroyActionMode(mode: ActionMode?) {
        clickBack()
        this.mode = null
    }

    fun startActionMode(view: View?, @MenuRes menuResId: Int) {
        this.menuResId = menuResId
        view?.let {
            view.startActionMode(this)
        }
    }

    fun getMode() : ActionMode? {
        return mode
    }

}