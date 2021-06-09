package com.yps.layani.admin.start

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatTextView
import com.yps.layani.R
import com.yps.layani.admin.api.ApiService
import com.yps.layani.admin.model.LoginRequest
import com.yps.layani.admin.preferences.UserPreference
import com.yps.layani.admin.response.LoginResponse
import org.json.JSONObject
import retrofit2.Response
import retrofit2.Call
import retrofit2.Callback

class LoginActivity() : Activity(), View.OnClickListener {

    private lateinit var email : EditText
    private lateinit var etPassword : EditText
    private lateinit var signin : Button
    private lateinit var linkRegister : AppCompatTextView
    val loginPref = UserPreference(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        email = findViewById(R.id.et_email)
        etPassword = findViewById(R.id.et_password)
        signin = findViewById(R.id.btn_sign_in)
        linkRegister = findViewById(R.id.link_register)

        signin.setOnClickListener(this)
        linkRegister.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.link_register -> {
                startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
            }
            R.id.btn_sign_in -> {
                if (validation()) {
                    val json = JSONObject()
                    json.put("email", email.text.toString())
                    json.put("password", etPassword.text.toString())

                    ApiService.loginApiCall().doLogin(
                       LoginRequest(
                            email.text.toString(),
                           etPassword.text.toString()
                        )
                    ).enqueue(object : Callback<LoginResponse> {
                        override fun onResponse(
                            call: Call<LoginResponse>,
                            response: Response<LoginResponse>) {

                            Log.d("Response::::", response.body().toString())
                            if (response.body()!!.status == "admin"){
                                UserPreference.getInstance(applicationContext).saveUser(response.body()?.data!!)
                                finish()
                                val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                                loginPref.saveToken(response.body()!!.token)
                                startActivity(intent)
                            }else{
                                Toast.makeText(applicationContext, response.body()!!.status, Toast.LENGTH_LONG).show()
                            }
                        }

                        override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        }

                    })
                }
            }
        }
    }

    fun validation(): Boolean {
        var value = true

        val password = etPassword.text.toString().trim()
        val name = email.text.toString().trim()

        if (password.isEmpty()) {
            etPassword.error = "Password required"
            etPassword.requestFocus()
            value = false
        }

        if (name.isEmpty()) {
            email.error = "Email required"
            email.requestFocus()
            value = false
        }

        return value
    }
}