package com.example.tictactoe

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import com.example.tictactoe.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding:ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.ed1.addTextChangedListener {
            binding.btn.isEnabled = binding.ed1.text.isNotBlank() && binding.ed2.text.isNotBlank()
        }
        binding.ed2.addTextChangedListener {
            binding.btn.isEnabled = binding.ed1.text.isNotBlank() && binding.ed2.text.isNotBlank()
        }
        binding.btn.setOnClickListener {
            val i= Intent(this,MainActivity::class.java)
            i.putExtra("First",binding.ed1.text.toString())
            i.putExtra("Second",binding.ed2.text.toString())
            startActivity(i)
        }
    }
}