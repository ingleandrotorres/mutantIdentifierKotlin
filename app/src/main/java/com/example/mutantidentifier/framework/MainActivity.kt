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
import com.example.mutantidentifier.data.models.Adn
import com.example.mutantidentifier.framework.extensions.hideKeyboard
import com.google.firebase.firestore.FirebaseFirestore
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainActivityViewModel>()
    var db = FirebaseFirestore.getInstance()


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

            val currentDateLikeId: String = SimpleDateFormat("yyyy_M_dd_hh_mm_ss").format(Date())

            db.collection("ADN").document(currentDateLikeId).set(
                Adn(binding.materialTextInputEditTextAdn.text.toString(),it)
            )

        }

        //binding.materialTextInputEditTextAdn.filters =  arrayOf(filter)

        binding.buttonFirst.setOnClickListener {


            binding.imageViewMutant
            it.hideKeyboard(this)
            viewModel.verifyIsValidInput(binding.materialTextInputEditTextAdn.text.toString().replace(" ",""))
            //hideKeyboard(binding.buttonFirst)
        }
        binding.buttonShowList.setOnClickListener {
            val intent = Intent(this, MutantListActivity::class.java).apply {
                putExtra(EXTRA_MESSAGE, "Paso al la otra actividad")
            }
            startActivity(intent)
        }
    }


}