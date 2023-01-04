package com.example.back4appshop.activities


import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.view.WindowManager
import com.example.back4appshop.R
import com.example.back4appshop.helper.Setting
import com.parse.ParseException
import com.parse.ParseUser
import kotlinx.android.synthetic.main.activity_log_in.*
import kotlinx.android.synthetic.main.activity_log_in.et_password
import kotlinx.android.synthetic.main.activity_log_in.username
import kotlinx.android.synthetic.main.activity_sign_up.*

class LoginActivity : BaseActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        var setting = Setting(this)
        if(setting.setLoginInfo()!=null){
            startActivity(Intent(this, MainActivity::class.java))
        }

        tv_forgot_password.setOnClickListener(this)
        btn_login.setOnClickListener(this)
        tv_register.setOnClickListener(this)
    }


    private fun validateRegisterDetails(): Boolean {
        return when {
            TextUtils.isEmpty(username.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_first_name), true)
                false
            }


            TextUtils.isEmpty(et_password.text.toString().trim { it <= ' ' }) -> {
                showErrorSnackBar(resources.getString(R.string.err_msg_enter_password), true)
                false
            }

            else -> {
                true
            }
        }
    }
    private fun showAlert(title: String, message: String, error: Boolean) {
        val builder = AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("OK") { dialog, which ->
                dialog.cancel()
                // don't forget to change the line below with the names of your Activities
                if (!error) {
                    val intent = Intent(this@LoginActivity, LoginActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
                }
            }
        val ok = builder.create()
        ok.show()
    }
    private fun loginFun(){
        val user: String = username.text.toString().trim() { it <= ' ' }
        val password: String = et_password.text.toString().trim { it <= ' ' }

        if(validateRegisterDetails()) {
            showProgressDialog("لطفا منتظر بمانید")
            ParseUser.logInInBackground(user, password) { parseUser: ParseUser?, e: ParseException? ->
                if (parseUser != null) {
                    var settings = Setting(this)
                    ParseUser.logOut()
                    settings.saveLogin(parseUser.objectId, parseUser.username)
                    showAlert("ورود موفقیت آمیز", " خوش آمیدید!$user", false)
                } else {
                    ParseUser.logOut()
                    showAlert("ورود نا موفق", e?.message + " لطفا مجدد تلاش کنید", true)
                }
                hideProgressDialog()
            }
        }
    }
    override fun onClick(v: View?) {
        if (v != null){
            when (v.id){
                R.id.tv_forgot_password -> {
                    val intent = Intent(this@LoginActivity, ForgotPasswordActivity::class.java)
                    startActivity(intent)

                }
                R.id.btn_login -> {
                    loginFun()
                }

                R.id.tv_register -> {

                    val intent = Intent(this@LoginActivity, SignUpActivity::class.java)
                    startActivity(intent)

                }
            }
        }
    }
}