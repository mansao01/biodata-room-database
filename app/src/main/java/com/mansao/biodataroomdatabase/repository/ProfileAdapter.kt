package com.mansao.biodataroomdatabase.repository

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mansao.biodataroomdatabase.database.Profile
import com.mansao.biodataroomdatabase.databinding.ItemRowProfileBinding
import com.mansao.biodataroomdatabase.helper.ProfileDiffCallback
import com.mansao.biodataroomdatabase.ui.ProfileAddUpdateActivity

class ProfileAdapter : RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>() {
    private val listProfile = ArrayList<Profile>()

    fun setListProfile(listProfile: List<Profile>){
        val diffCallback = ProfileDiffCallback(this.listProfile, listProfile)
        val diffReslt = DiffUtil.calculateDiff(diffCallback)
        this.listProfile.clear()
        this.listProfile.addAll(listProfile)
        diffReslt.dispatchUpdatesTo(this)
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProfileAdapter.ProfileViewHolder {
        val binding =
            ItemRowProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProfileViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfileAdapter.ProfileViewHolder, position: Int) {
        holder.bind(listProfile[position])
    }

    override fun getItemCount(): Int = listProfile.size

    inner class ProfileViewHolder(private val binding: ItemRowProfileBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(profile: Profile) {
            binding.apply {
                tvItemAddress.text = profile.address
                tvItemName.text = profile.name
                cvItemProfile.setOnClickListener {
                    val intent = Intent(it.context, ProfileAddUpdateActivity::class.java)
                    intent.putExtra(ProfileAddUpdateActivity.EXTRA_DATA, profile)
                    it.context.startActivity(intent)
                }
            }
        }
    }
}