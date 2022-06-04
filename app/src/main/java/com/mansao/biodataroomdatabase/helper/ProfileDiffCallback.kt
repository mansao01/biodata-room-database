package com.mansao.biodataroomdatabase.helper

import androidx.recyclerview.widget.DiffUtil
import com.mansao.biodataroomdatabase.database.Profile

class ProfileDiffCallback(
    private val oldProfileList: List<Profile>,
    private val newProfileList: List<Profile>
): DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldProfileList.size
    }

    override fun getNewListSize(): Int {
        return newProfileList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldProfileList[oldItemPosition].id == newProfileList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val newEmployee = newProfileList[newItemPosition]
        val oldEmployee = oldProfileList[oldItemPosition]

        return oldEmployee.name == newEmployee.name && oldEmployee.address == newEmployee.address
    }
}