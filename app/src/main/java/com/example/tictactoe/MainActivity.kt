package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import com.example.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var board: Array<Array<Button>>
    val s1 = "PLAYER "
    val s2 = "'S TURN"
    val s3 = " HAS WON!"
    val over="GAME OVER!"
    var cnt=0
    var f=false
    var boardval = Array(3){IntArray(3)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        board= arrayOf(arrayOf(binding.btn1,binding.btn2,binding.btn3),
            arrayOf(binding.btn4,binding.btn5,binding.btn6),
            arrayOf(binding.btn7,binding.btn8,binding.btn9))
        reset()
        binding.resetbtn.setOnClickListener {
            reset()
        }
        for(i in 0..2){
            for(j in 0..2){
                board[i][j].setOnClickListener {
                    board[i][j].apply{
                        var s = if(!f) "O" else "X"
                        isEnabled=false
                        var ss = if(f) "O" else "X"
                        text=ss
                        binding.txt1.setText(s1+s+s2)
                        cnt++
                        boardval[i][j]=if(f) 1 else 0
                        f=!f
                        var x=won()
                        when(x){
                            1->{
                                binding.txt2.setText(s1+ss+s3)
                                binding.txt1.setText(over)
                                for(i in 0..2){
                                    for(j in 0..2){
                                        board[i][j].isEnabled=false
                                    }
                                }
                            }
                        }
                        if(x!=1 && cnt==9){
                            binding.txt1.setText(over)
                            binding.txt2.setText("GAME DRAWN!")
                        }
                    }
                }
            }
        }
    }

    private fun won(): Int {
        for(i in 0..2) {
            if (boardval[i][0] == boardval[i][1] && boardval[i][0] == boardval[i][2] && boardval[i][0] != -1)
                return 1;
            if (boardval[0][i] == boardval[1][i] && boardval[0][i] == boardval[2][i] && boardval[0][i] != -1)
                return 1;
        }
        if(((boardval[0][0]==boardval[1][1] && boardval[1][1]==boardval[2][2])
                    || (boardval[0][2]==boardval[1][1] && boardval[0][2]==boardval[2][0]))
            && (boardval[1][1]!=-1))
            return 1;

        return 0;
    }

    private fun reset() {
        binding.txt1.setText(s1+'X'+s2)
        binding.txt2.text=""
        f=false
        cnt=0
        for(i in 0..2){
            for(j in 0..2){
                boardval[i][j]=-1
                board[i][j].apply {
                    text = ""
                    isEnabled = true
                }
            }
        }
    }
}