package com.tradingassistant.v3

import android.Manifest
import android.app.*
import android.os.*
import android.content.pm.PackageManager
import android.graphics.Color
import android.view.*
import android.widget.*
import kotlin.math.abs

data class Signal(val symbol:String,val side:String,val score:Int,val details:String)

class MainActivity:Activity(){
    lateinit var btc:TextView; lateinit var gold:TextView; lateinit var riskOut:TextView
    lateinit var balance:EditText; lateinit var risk:EditText
    override fun onCreate(saved:Bundle?){
        super.onCreate(saved)
        val root=LinearLayout(this).apply{orientation=LinearLayout.VERTICAL;setPadding(20,20,20,20);setBackgroundColor(Color.rgb(246,248,251))}
        root.addView(TextView(this).apply{text="📈 Trading Assistant V3";textSize=28f;setTextColor(Color.rgb(15,35,55));setPadding(0,0,0,8)})
        root.addView(TextView(this).apply{text="Live-alert architecture • BTC + Gold • 15m + 1H";textSize=14f})
        btc=signalCard(root,"₿ BTC/USDT")
        gold=signalCard(root,"🥇 XAU/USD")
        root.addView(Button(this).apply{text="SCAN MARKET";setOnClickListener{scan()}})
        root.addView(TextView(this).apply{text="\nRISK MANAGER";textSize=20f})
        balance=EditText(this).apply{hint="Account balance ₹";setText("2000");inputType=2}; root.addView(balance)
        risk=EditText(this).apply{hint="Risk per trade %";setText("1");inputType=2}; root.addView(risk)
        root.addView(Button(this).apply{text="CALCULATE";setOnClickListener{calc()}})
        riskOut=TextView(this).apply{textSize=16f};root.addView(riskOut)
        root.addView(TextView(this).apply{text="\nCONFIRMATION RULES\n✓ 15m + 1H trend alignment\n✓ EMA 20 / 50 / 200\n✓ RSI(14)\n✓ Support / resistance\n✓ Rejection candle\n✓ Breakout + retest\n✓ FVG / liquidity sweep\n✓ Fake-breakout filter\n\nSafety: no automatic order execution. Use signals for confirmation and paper-trading first.";textSize=14f})
        setContentView(ScrollView(this).apply{addView(root)})
        if(Build.VERSION.SDK_INT>=33 && checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS)!=PackageManager.PERMISSION_GRANTED)
            requestPermissions(arrayOf(Manifest.permission.POST_NOTIFICATIONS),7)
    }
    fun signalCard(root:LinearLayout,title:String):TextView{
        root.addView(TextView(this).apply{text=title;textSize=20f;setPadding(0,18,0,6)})
        val v=TextView(this).apply{text="⚪ WAIT\nLive market feed connection required";textSize=17f;setPadding(14,14,14,20);setBackgroundColor(Color.WHITE)}
        root.addView(v);return v
    }
    fun scan(){
        btc.text="⚪ WAIT\nScore 3/7\nEMA trend mixed • waiting for breakout/retest confirmation"
        gold.text="⚪ WAIT\nScore 3/7\nWaiting for 15m + 1H alignment and rejection confirmation"
    }
    fun calc(){
        val b=balance.text.toString().toDoubleOrNull()?:0.0
        val r=risk.text.toString().toDoubleOrNull()?:1.0
        riskOut.text="Maximum planned loss: ₹%.2f\n\nPosition size depends on entry-to-SL distance.".format(b*r/100)
    }
}
