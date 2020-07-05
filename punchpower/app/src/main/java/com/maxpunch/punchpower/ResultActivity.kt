package com.maxpunch.punchpower

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.games.Games
import kotlinx.android.synthetic.main.activity_result.*

class ResultActivity : AppCompatActivity() {

    val RC_SIGN_IN = 9001
    val RC_LEADERBOARD_UI = 9004
    val signInClient: GoogleSignInClient by lazy {
        GoogleSignIn.getClient(this@ResultActivity, GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN)
    }
    //펀치력을 저장하는 변수. 사용하는 시점에 초기화되도록 lazy 위임 사용
    val power by lazy {
        intent.getDoubleExtra("power", 0.0) * 100
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        title = "펀치력 결과"

        scoreLabel.text = "${String.format("%.0f", power)} 점"


        restartButton.setOnClickListener {
            finish()
        }

        rankingButton.setOnClickListener {
            if (GoogleSignIn.getLastSignedInAccount(this) == null) {
                startActivityForResult(signInClient.signInIntent, RC_SIGN_IN)
            } else {
                uploadScore()
            }
        }
    }
    fun uploadScore(){
        // 인증된 유저 객체 가져옴
        var user = GoogleSignIn.getLastSignedInAccount(this)
        user?.let {
            val leaderboard = Games.getLeaderboardsClient(this@ResultActivity, user)

            //리더보드 객체에 점수를 즉시 올림. 성공시 컬백에서 리더보드 화면으로 이동
            leaderboard.submitScoreImmediate(getString(R.string.leaderboard_id), power.toLong()).
            addOnSuccessListener{ leaderboard.getLeaderboardIntent(getString(R.string.leaderboard_id)).
            addOnSuccessListener{ intent -> startActivityForResult(intent, RC_LEADERBOARD_UI)}}
        }
    }
    //구글 로그인 액티비티에서 결과가 돌아온 경우 호출됨
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == RC_SIGN_IN){
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)

            //인증 성공인 경우
            if (result.isSuccess){
                uploadScore()
            }else {
                //로그인 실패 알림
                var message = result.status.statusMessage
                if (message == null || message.isEmpty()) {
                    message = "로그인 오류!!"






                }
                Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
            }
        }
    }
}