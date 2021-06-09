package com.yps.layani.admin.start

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.yps.layani.R
import com.yps.layani.admin.api.ApiService
import com.yps.layani.admin.model.RegisterRequest
import com.yps.layani.admin.response.RegisterResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : Activity(), View.OnClickListener {

    private lateinit var fullName: EditText
    private lateinit var edEmail: EditText
    private lateinit var edPassword: EditText
    private lateinit var signup: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        fullName = findViewById(R.id.et_fullname)
        edEmail = findViewById(R.id.et_email)
        edPassword = findViewById(R.id.et_password)
        signup = findViewById(R.id.btn_register)

        signup.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_register -> {
                if (validation()) {
                    val json = JSONObject()
                    json.put("email", edEmail.text.toString())
                    json.put("name", fullName.text.toString())
                    json.put("password", edPassword.text.toString())

                    ApiService.loginApiCall().doRegister(
                        RegisterRequest(
                            edEmail.text.toString(),
                            fullName.text.toString(),
                            edPassword.text.toString()
                        )
                    ).enqueue(object : Callback<RegisterResponse> {
                        override fun onResponse(
                            call: Call<RegisterResponse>,
                            response: Response<RegisterResponse>
                        ) {

                            Log.d("Response::::", response.body().toString())
                            val loginResponse: RegisterResponse = response.body()!!
                            if (loginResponse.message == "Register Sukses") {
                                finish()
                            } else {
                                Toast.makeText(
                                    applicationContext,
                                    response.body()!!.message,
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }

                        override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                        }

                    })
                }
            }
        }
    }

    fun validation(): Boolean {
        var value = true

        val email = edEmail.text.toString().trim()
        val password = edPassword.text.toString().trim()
        val name = fullName.text.toString().trim()

        if (email.isEmpty()) {
            edEmail.error = "Email required"
            edEmail.requestFocus()
            value = false
        }


        if (password.isEmpty()) {
            edPassword.error = "Password required"
            edPassword.requestFocus()
            value = false
        }

        if (name.isEmpty()) {
            fullName.error = "Name required"
            fullName.requestFocus()
            value = false
        }

        return value
    }
}
