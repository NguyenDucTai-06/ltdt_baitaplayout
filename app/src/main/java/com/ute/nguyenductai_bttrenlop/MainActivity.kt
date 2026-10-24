package com.ute.nguyenductai_bttrenlop

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ute.nguyenductai_bttrenlop.databinding.ActivityMainBinding
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalculate.setOnClickListener {
            calculateResult()
        }

        binding.btnChangeText.setOnClickListener {
            binding.tvResultValue.text = "Đã thay đổi nội dung!"
            binding.tvResultValue.textSize = 24f
        }
    }

    private fun calculateResult() {
        val num1 = binding.edtNumber1.text.toString().toDoubleOrNull()
        val num2 = binding.edtNumber2.text.toString().toDoubleOrNull()

        if (num1 == null || num2 == null) {
            Toast.makeText(this, getString(R.string.err_empty_input), Toast.LENGTH_SHORT).show()
            return
        }

        val result = when (binding.rgOperations.checkedRadioButtonId) {
            R.id.rbAdd -> num1 + num2
            R.id.rbSub -> num1 - num2
            R.id.rbMul -> num1 * num2
            R.id.rbDiv -> {
                if (num2 == 0.0) {
                    Toast.makeText(this, getString(R.string.err_div_zero), Toast.LENGTH_SHORT).show()
                    return
                }
                num1 / num2
            }
            else -> {
                Toast.makeText(this, "Vui lòng chọn phép toán!", Toast.LENGTH_SHORT).show()
                return
            }
        }

        if (binding.switchRoundResult.isChecked) {
            val roundedResult = result.roundToInt()
            binding.tvResultValue.text = "Kết quả: $roundedResult"
        } else {
            binding.tvResultValue.text = "Kết quả: $result"
        }

        binding.tvResultValue.textSize = 28f
    }
}