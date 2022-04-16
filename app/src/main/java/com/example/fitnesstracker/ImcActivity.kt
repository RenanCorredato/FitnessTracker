package com.example.fitnesstracker

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import com.example.fitnesstracker.databinding.ActivityImcBinding

class ImcActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImcBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImcBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnImcSend.setOnClickListener {
            if (!validate()) {
                return@setOnClickListener Toast.makeText(
                    this,
                    R.string.fields_message, Toast.LENGTH_LONG
                ).show()
            }

            // Caputando os dados do usuário

            val sHeight = binding.edtImcHeight.text.toString().toFloat()
            val sWeight = binding.edtImcWeight.text.toString().toDouble()
            val result = calculateIMC(sHeight, sWeight)
            Log.d("Teste", "Resultado: $result")

            val imcResponseID = imcResponse(result)

            //Dialog - com as informações do IMC
            val builder = AlertDialog.Builder(this)
            with(builder) {
                setTitle(getString(R.string.imc_response, result))
                setMessage(imcResponseID)
                setPositiveButton(R.string.ok, DialogInterface.OnClickListener { dialog, id ->
                })

                create()
                show()

                // Esconde o teclado no clik do botão
                val imm: InputMethodManager =
                    getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(binding.edtImcWeight.windowToken, 0)
                imm.hideSoftInputFromWindow(binding.edtImcWeight.windowToken, 0)

            }


//            Log.d("Test3", "IMC: ${imcResponse(result)}")
//            Toast.makeText(
//                this,
//                "IMC: ${"%.2f".format(result)}\n ${imcResponse(result)}",
//                Toast.LENGTH_LONG
//            ).show()

        }
    }


    private fun imcResponse(imc: Double): CharSequence {

        return when {
            imc < 15 -> getText(R.string.imc_severely_low_weight)
            imc < 16 -> getText(R.string.imc_very_low_weight)
            imc < 18.5 -> getText(R.string.imc_low_weight)
            imc < 25 -> getText(R.string.normal)
            imc < 30 -> getText(R.string.imc_high_weight)
            imc < 35 -> getText(R.string.imc_so_high_weight)
            imc < 40 -> getText(R.string.imc_severely_high_weight)
            else -> {
                getText(R.string.imc_extreme_weight)
            }
        }

    }

    // Função calcula IMC
    private fun calculateIMC(height: Float, weight: Double): Double {
        // peso / (altura * altura)
        return weight / (height * height) * 10000

    }

    // Função que valida se o campo está vazio ou como zero
    fun validate(): Boolean {
        return with(binding) {
            !edtImcHeight.text.toString().startsWith("0")
                    && !edtImcWeight.text.toString().startsWith("0")
                    && !edtImcHeight.text.toString().isEmpty()
                    && !edtImcHeight.text.toString().isEmpty()
        }
    }
}