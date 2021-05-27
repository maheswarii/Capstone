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

    private lateinit var ed_fullname: EditText
    private lateinit var ed_email: EditText
    private lateinit var ed_password: EditText
    private lateinit var btn_signup: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        ed_fullname = findViewById(R.id.et_fullname)
        ed_email = findViewById(R.id.et_email)
        ed_password = findViewById(R.id.et_password)
        btn_signup = findViewById(R.id.btn_register)

        btn_signup.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_register -> {
                if (validation()) {
                    val json = JSONObject()
                    json.put("email", ed_email.text.toString())
                    json.put("name", ed_fullname.text.toString())
                    json.put("password", ed_password.text.toString())

                    ApiService.loginApiCall().doRegister(
                        RegisterRequest(
                            ed_email.text.toString(),
                            ed_fullname.text.toString(), ed_password.text.toString()
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

        val email = ed_email.text.toString().trim()
        val password = ed_password.text.toString().trim()
        val name = ed_fullname.text.toString().trim()

        if (email.isEmpty()) {
            ed_email.error = "Email required"
            ed_email.requestFocus()
            value = false
        }


        if (password.isEmpty()) {
            ed_password.error = "Password required"
            ed_password.requestFocus()
            value = false
        }

        if (name.isEmpty()) {
            ed_fullname.error = "Name required"
            ed_fullname.requestFocus()
            value = false
        }

        return value
    }
}
