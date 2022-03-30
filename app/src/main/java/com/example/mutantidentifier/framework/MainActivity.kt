package com.example.mutantidentifier.framework

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.mutantidentifier.R
import com.example.mutantidentifier.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import android.text.InputFilter


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainActivityViewModel>()


    private val blockCharacterSet = "atgc,ATGC"
    private val filter = InputFilter { source, start, end, dest, dstart, dend -> if (source != null && !blockCharacterSet.contains("" + source)) { "" } else null
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

     binding = ActivityMainBinding.inflate(layoutInflater)
     setContentView(binding.root)

        viewModel.isValidInput.observe(this){
            if (it == false){
                binding.imageViewMutant.visibility = View.INVISIBLE
                binding.imageViewHuman.visibility = View.INVISIBLE
                binding.buttonFirst
                binding.imageViewError.visibility = View.VISIBLE
                Snackbar.make(binding.materialTextInputEditTextAdn, getString(R.string.error_invalid_format), Snackbar.LENGTH_LONG).show()

            } else{
                binding.imageViewError.visibility = View.INVISIBLE
                viewModel.onButtonVerifyIsMutant(binding.materialTextInputEditTextAdn.text.toString())
            }
        }

        viewModel.mutantAnswerVisibility.observe(this){

            if (it == true){
                binding.imageViewMutant.visibility = View.VISIBLE
                binding.imageViewHuman.visibility = View.INVISIBLE
                Snackbar.make(binding.materialTextInputEditTextAdn, getString(com.example.mutantidentifier.R.string.is_mutant), Snackbar.LENGTH_LONG).show()
            }
            else{
                binding.imageViewMutant.visibility = View.INVISIBLE
                binding.imageViewHuman.visibility = View.VISIBLE
                Snackbar.make(binding.materialTextInputEditTextAdn, getString(com.example.mutantidentifier.R.string.is_human), Snackbar.LENGTH_LONG).show()
            }

        }

        //binding.materialTextInputEditTextAdn.filters =  arrayOf(filter)

        binding.buttonFirst.setOnClickListener {

            viewModel.verifyIsValidInput(binding.materialTextInputEditTextAdn.text.toString())
            //hideKeyboard(binding.buttonFirst)
        }
        binding.buttonShowList.setOnClickListener {
            val intent = Intent(this, MutantListActivity::class.java).apply {
                putExtra(EXTRA_MESSAGE, "Paso al la otra actividad")
            }
            startActivity(intent)
        }
    }

    //private fun hideKeyboard(currentFocus ?: View(view.context))

}