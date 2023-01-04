package com.example.back4appshop.activities

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import com.example.back4appshop.R
import com.parse.ParseUser
import kotlinx.android.synthetic.main.activity_forgot_password.*


class ForgotPasswordActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        setupActionBar()

        btn_submit.setOnClickListener {
            showProgressDialog("لطفا صبر کنید")
            val email: String = et_email.text.toString().trim { it <= ' ' }

            if (email.isEmpty()) {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_email), true)
            } else {
                // This piece of code is used to send the reset password link to the user's email id if the user is registered.

                ParseUser.requestPasswordResetInBackground(
                    email
                ) { e ->
                    if (e == null) {
                        showAlert("ایمیل با موفقیت ارسال شد", " لطفا ایمیل خود را چک کنید", false)
                    } else {
                        showAlert("با مشکل مواجه شد", e?.message + " لطفا مجدد تلاش کنید", true)
                    }
                }
            }
            hideProgressDialog()
        }
    }



    private fun setupActionBar() {

        setSupportActionBar(toolbar_forgot_password_activity)

        val actionBar = supportActionBar
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.ic_white_color_back_24dp)
        }

        toolbar_forgot_password_activity.setNavigationOnClickListener { onBackPressed() }
    }

    private fun showAlert(title: String, message: String, error: Boolean) {
        val builder = AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK") { dialog, which ->
                dialog.cancel()
                // don't forget to change the line below with the names of your Activities
                if (!error) {
                    val intent = Intent(this@ForgotPasswordActivity, ForgotPasswordActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }
            }
        val ok = builder.create()
        ok.show()
    }

}