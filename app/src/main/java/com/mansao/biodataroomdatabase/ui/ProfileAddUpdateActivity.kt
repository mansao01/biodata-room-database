package com.mansao.biodataroomdatabase.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.mansao.biodataroomdatabase.R
import com.mansao.biodataroomdatabase.database.Profile
import com.mansao.biodataroomdatabase.databinding.ActivityProfileAddUpdateBinding
import com.mansao.biodataroomdatabase.viewmodel.ProfileAddUpdateViewModel
import com.mansao.biodataroomdatabase.viewmodel.ViewModelFactory

class ProfileAddUpdateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileAddUpdateBinding

    private lateinit var profileAddUpdateViewModel: ProfileAddUpdateViewModel

    private var isEdit = false

    private var profile: Profile? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileAddUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        profileAddUpdateViewModel = obtainViewModelFactory(this)

        profile = intent.getParcelableExtra(EXTRA_DATA)

        if (profile != null) {
            isEdit = true
        } else {
            profile = Profile()
        }

        val actionBarTitle: String
        val btnTitle: String

        if (isEdit) {
            actionBarTitle = "Change"
            btnTitle = "Update"
            if (profile != null) {
                profile?.let { profile ->
                    binding.apply {
                        edtName.setText(profile.name)
                        edtAddress.setText(profile.address)
                    }
                }
            }
        } else {
            actionBarTitle = "Add"
            btnTitle = "Save"
        }

        supportActionBar?.title = actionBarTitle
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnSubmit.text = btnTitle

        binding.btnSubmit.setOnClickListener {
            submitData()
        }
    }

    private fun submitData() {
        val name = binding.edtName.text.toString().trim()
        val address = binding.edtAddress.text.toString().trim()

        when {
            name.isEmpty() -> {
                binding.edtName.error = "Empty"
            }
            address.isEmpty() -> {
                binding.edtAddress.error = "Empty"
            }
            else -> {
                profile.let {
                    it?.name = name
                    it?.address = address
                }
                if (isEdit) {
                    profileAddUpdateViewModel.update(profile as Profile)
                    Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show()
                } else {
                    profileAddUpdateViewModel.insert(profile as Profile)
                    Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show()
                }
                finish()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        if (isEdit) {
            menuInflater.inflate(R.menu.menu_main, menu)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_delete -> showAlertDialog(ALERT_DIALOG_DELETE)
            android.R.id.home -> showAlertDialog(ALERT_DIALOG_CLOSE)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showAlertDialog(type: Int) {
        val isDialogClose = type == ALERT_DIALOG_CLOSE
        val dialogTitle: String
        val dialogMessage: String

        if (isDialogClose) {
            dialogTitle = "Cancel"
            dialogMessage = "Cancel update ?"
        } else {
            dialogTitle = "Delete"
            dialogMessage = "Delete Data ?"
        }

        val alertDialogBuilder = AlertDialog.Builder(this)
        with(alertDialogBuilder) {
            setTitle(dialogTitle)
            setMessage(dialogMessage)
            setCancelable(false)
            setPositiveButton("Yes") { _, _ ->
                if (!isDialogClose) {
                    profileAddUpdateViewModel.delete(profile as Profile)
                    Toast.makeText(this@ProfileAddUpdateActivity, "Deleted", Toast.LENGTH_SHORT)
                        .show()
                }
                finish()
            }

            setNegativeButton("No"){ dialog, _ ->
                dialog.cancel()
            }
        }
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun obtainViewModelFactory(activity: AppCompatActivity): ProfileAddUpdateViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[ProfileAddUpdateViewModel::class.java]
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
        const val ALERT_DIALOG_CLOSE = 10
        const val ALERT_DIALOG_DELETE = 20
    }
}