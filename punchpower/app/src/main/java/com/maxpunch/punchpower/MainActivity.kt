package com.maxpunch.punchpower

import android.animation.*
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import kotlin.math.max

class MainActivity : AppCompatActivity() {
    //최대 펀치력
    var maxPower = 0.0
    // 펀치력 측정이 시작되었는지 나타내는 변수
    var isStart = false
    // 펀치력 측정이 시작된 시간
    var startTime = 0L

    //Sensor 관리자 객체. lazy 로 실제 사용될때 초기화 한다.
    val sensorManager: SensorManager by lazy{
        getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    val eventListener: SensorEventListener = object : SensorEventListener {
        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int){
        }

        override fun onSensorChanged(event: SensorEvent?) {
            event?.let{
                if(event.sensor.type != Sensor.TYPE_LINEAR_ACCELERATION) return@let
                // 각 좌표 값을 제곱하여 음수 값을 없애고 , 값의 차이를 극대화
                val power =
                    Math.pow(event.values[0].toDouble(), 2.0) + Math.pow(event.values[1].toDouble(),
                    2.0) + Math.pow(event.values[2].toDouble(), 2.0)

                // 측정된 펀치력이 20 을 넘고 아직 측정이 시작되지 않은 경우
                if (power > 20 && !isStart){
                    startTime = System.currentTimeMillis()
                    isStart = true
                }

                //측정 시작된 경우
                if(isStart){
                    imageView.clearAnimation()

                    if (maxPower < power) maxPower = power
                    stateLabel.text = "펀치력을 측정하고 있습니다"

                    if(System.currentTimeMillis() - startTime > 3000){
                        isStart = false
                        punchPowerTestComplete(maxPower)
                    }
                }

            }
        }
    }

    //
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    //activity 사라졌다 다시 보일 때마다 호출되는 함수
    override fun onStart(){
        super.onStart()
        initGame()
    }

    //게임 초기화
    fun initGame(){
        maxPower = 0.0
        isStart = false
        startTime = 0L
        stateLabel.text = "핸드폰을 손에쥐고 주먹을 내지르세요"

        //센서의 변화 값을 처리할 리스너를 등록한다
        sensorManager.registerListener(
            eventListener,
            sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION),
            SensorManager.SENSOR_DELAY_NORMAL
        )


        //애니메이션 시작
        val animation = AnimationUtils.loadAnimation(this@MainActivity, R.anim.alpha_scale)
        imageView.startAnimation(animation)

        //애니메이션의 리스너 설정
        animation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationRepeat(animation: Animation?){
                //애니메이션이 반복될때 처리 코드
            }
            override fun onAnimationEnd(animation: Animation?){
                //종료될때 코드
            }
            override fun onAnimationStart(animation: Animation?){
                //시작될때 코드
            }
        })


        AnimatorInflater.loadAnimator(this@MainActivity, R.animator.color_anim).apply {

            val colorAnimator = this@apply as? ObjectAnimator

            colorAnimator?.apply {
                setEvaluator(ArgbEvaluator())
                target = window.decorView.findViewById(android.R.id.content)

                start()
            }
        }
        AnimatorInflater.loadAnimator(this@MainActivity, R.animator.property_animation).apply {

            val colorAnimator = this@apply as? ObjectAnimator

            colorAnimator?.apply {
                setEvaluator(ArgbEvaluator())
                target = window.decorView.findViewById(android.R.id.content)

                start()
            }
            addListener(object : AnimatorListenerAdapter(){
                override fun onAnimationEnd(animation: Animator?) { start() }
            })

            setTarget(imageView)

            start()
        }
    }

    // 측정이 완료된 경우 처리 함수
    fun punchPowerTestComplete(power : Double){
        Log.d("MainActivity", "측정완료: power: ${String.format("%.5f",power)}")
        sensorManager.unregisterListener(eventListener)
        val intent = Intent(this@MainActivity, ResultActivity::class.java)
        intent.putExtra("power",power)
        startActivity(intent)
    }

    override fun onStop(){
        super.onStop()
        try {
            sensorManager.unregisterListener(eventListener)
        }catch (e:Exception){}
    }
}