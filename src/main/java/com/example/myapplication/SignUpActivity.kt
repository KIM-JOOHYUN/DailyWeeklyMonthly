package com.example.myapplication

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.*
import com.google.firebase.database.FirebaseDatabase
import java.util.*


class SignUpActivity : AppCompatActivity() {
    lateinit var mEmailText: EditText
    lateinit var mPasswordText: EditText
    lateinit var mPasswordcheckText: EditText
    lateinit var mName: EditText
    private var firebaseAuth: FirebaseAuth? = null
    private lateinit var SignUP: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        firebaseAuth = FirebaseAuth.getInstance()
        mEmailText = findViewById(R.id.signUp_email)
        mPasswordText = findViewById(R.id.signUp_pw)
        mPasswordcheckText = findViewById(R.id.pwCheck)
        mName = findViewById(R.id.signUp_name)
        SignUP = findViewById(R.id.signUp_btn)
        SignUP.setOnClickListener(View.OnClickListener {
            //가입 정보 가져오기
            val email = mEmailText.getText().toString().trim { it <= ' ' }
            val pwd = mPasswordText.getText().toString().trim { it <= ' ' }
            val pwdcheck = mPasswordcheckText.getText().toString().trim { it <= ' ' }
            val name = mName.getText().toString().trim { it <= ' ' }
            if (pwd == pwdcheck) {
                Log.d(TAG, "등록 버튼 $email , $pwd")
                val mDialog = ProgressDialog(this@SignUpActivity)
                mDialog.setMessage("가입중입니다...")
                mDialog.show()

                //파이어베이스에 신규계정 등록하기
                firebaseAuth!!.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(this@SignUpActivity) { task ->
                    //가입 성공시
                    if (task.isSuccessful) {
                        mDialog.dismiss()
                        val user = firebaseAuth!!.currentUser
                        val uid = user!!.uid
                        val name = mName.getText().toString().trim { it <= ' ' }


                        //해쉬맵 테이블을 파이어베이스 데이터베이스에 저장
                        val hashMap = HashMap<Any, String>()
                        hashMap["uid"] = uid
                        hashMap["email"] = email
                        hashMap["name"] = name
                        val database = FirebaseDatabase.getInstance()
                        val reference = database.getReference("Users")
                        reference.child(uid).setValue(hashMap)


                        //가입이 이루어져을시 가입 화면을 빠져나감.
                        val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                        Toast.makeText(this@SignUpActivity, "회원가입에 성공하셨습니다.", Toast.LENGTH_SHORT).show()
                    } else if (!task.isSuccessful) {
                        mDialog.dismiss()
                        try {
                            throw task.exception!!
                        } catch (e: FirebaseAuthWeakPasswordException) {
                            Toast.makeText(this@SignUpActivity, "비밀번호가 너무 간단합니다.", Toast.LENGTH_SHORT).show()
                        } catch (e: FirebaseAuthInvalidCredentialsException) {
                            Toast.makeText(this@SignUpActivity, "email 형식에 맞지 않습니다.", Toast.LENGTH_SHORT).show()
                        } catch (e: FirebaseAuthUserCollisionException) {
                            Toast.makeText(this@SignUpActivity, "이미존재하는 email 입니다.", Toast.LENGTH_SHORT).show()
                        } catch (e: Exception) {
                            Toast.makeText(this@SignUpActivity, "다시 확인해주세요.", Toast.LENGTH_SHORT).show()
                        }
                    }
                }

                //비밀번호 오류시
            } else {
                Toast.makeText(this@SignUpActivity, "비밀번호가 틀렸습니다. 다시 입력해 주세요.", Toast.LENGTH_SHORT).show()
                return@OnClickListener
            }
        })
    }

    companion object {
        private const val TAG = "RegisterActivity"
    }
}