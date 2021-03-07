package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException


class LoginActivity : AppCompatActivity() {
    lateinit var ID: EditText
    lateinit var Pw: EditText
    lateinit var mLoginBtn: Button
    lateinit var mRegistBtn: Button
    private var firebaseAuth: FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        ID = findViewById(R.id.login_id)
        Pw = findViewById(R.id.login_pw)
        firebaseAuth = FirebaseAuth.getInstance()
        mLoginBtn = findViewById(R.id.signIn_btn)
        mRegistBtn = findViewById(R.id.signUp_btn)
        //가입 버튼이 눌리면
        mRegistBtn.setOnClickListener(View.OnClickListener { //intent함수를 통해 register액티비티 함수를 호출한다.
            startActivity(Intent(this@LoginActivity, SignUpActivity::class.java))
        })

        //로그인 버튼이 눌리면
        mLoginBtn.setOnClickListener(View.OnClickListener {
            val email = ID.getText().toString().trim { it <= ' ' }
            val pwd = Pw.getText().toString().trim { it <= ' ' }
            if (email.isEmpty() || pwd.isEmpty()) {
                Toast.makeText(this@LoginActivity, "The field is empty", Toast.LENGTH_SHORT).show()
            } else {
                firebaseAuth!!.signInWithEmailAndPassword(email, pwd)
                        .addOnCompleteListener(this@LoginActivity) { task ->
                            if (task.isSuccessful) {
                                val intent = Intent(this@LoginActivity, MainActivity::class.java)
                                startActivity(intent)
                                Toast.makeText(this@LoginActivity, "Login Successful", Toast.LENGTH_SHORT).show()
                            } else if (!task.isSuccessful) {
                                try {
                                    throw task.exception!!
                                } catch (e: FirebaseAuthInvalidUserException) {
                                    Toast.makeText(this@LoginActivity, "ID doesn't exist", Toast.LENGTH_SHORT).show()
                                } catch (e: FirebaseAuthInvalidCredentialsException) {
                                    Toast.makeText(this@LoginActivity, "Not in email form", Toast.LENGTH_SHORT).show()
                                } catch (e: FirebaseNetworkException) {
                                    Toast.makeText(this@LoginActivity, "Firebase NetworkException", Toast.LENGTH_SHORT).show()
                                } catch (e: Exception) {
                                    Toast.makeText(this@LoginActivity, "Login Error", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
            }
        })
    }
}