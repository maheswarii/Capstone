package com.yps.layani

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    private var transitionButton: TransitionButton? = null
    private var etEmail: EditText? = null
    private var etPassword: EditText? = null
    private var mAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        WindowUtils.makeStatusbarTransparent(this)
        supportActionBar!!.hide()
        mAuth = FirebaseAuth.getInstance()
        etEmail = findViewById(R.id.et_email)
        etPassword = findViewById(R.id.et_password)
        transitionButton = findViewById(R.id.login_button)
        transitionButton.setOnClickListener(View.OnClickListener {
            // Then start the loading animation when the user tap the button
            transitionButton.startAnimation()
            val email = etEmail.getText().toString().trim { it <= ' ' }
            val password = etPassword.getText().toString().trim { it <= ' ' }
            var isEmpty = false
            if (TextUtils.isEmpty(email)) {
                isEmpty = true
                etEmail.setError("Enter email")
            }
            if (TextUtils.isEmpty(password)) {
                isEmpty = true
                etPassword.setError("Enter passord")
            }
            if (!isEmpty) {
                // Do your networking task or background work here.
                val handler = Handler()
                handler.postDelayed({
                    mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(
                            this@LoginActivity,
                            object : OnCompleteListener<AuthResult?>() {
                                fun onComplete(task: Task<AuthResult?>) {
                                    if (task.isSuccessful()) {
                                        transitionButton.stopAnimation(
                                            TransitionButton.StopAnimationStyle.EXPAND,
                                            object : OnAnimationStopEndListener() {
                                                fun onAnimationStopEnd() {
                                                    val intent = Intent(
                                                        this@LoginActivity,
                                                        MainActivity::class.java
                                                    )
                                                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                                                    startActivity(intent)
                                                }
                                            })
                                    } else {
                                        Toast.makeText(
                                            this@LoginActivity,
                                            "Wrong email or password",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        transitionButton.stopAnimation(
                                            TransitionButton.StopAnimationStyle.SHAKE,
                                            null
                                        )
                                    }
                                }
                            })
                }, 2000)
            } else {
                transitionButton.stopAnimation(TransitionButton.StopAnimationStyle.SHAKE, null)
            }
        })
    }

    fun btnRegister(view: View?) {
        val intent = Intent(baseContext, RegisterActivity::class.java)
        startActivity(intent)
    }
}