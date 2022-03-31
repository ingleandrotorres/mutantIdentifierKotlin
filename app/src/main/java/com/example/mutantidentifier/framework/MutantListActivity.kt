package com.example.mutantidentifier.framework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mutantidentifier.data.models.Adn
import com.example.mutantidentifier.databinding.ActivityMutantListBinding
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.mutantidentifier.data.models.City
import com.google.firebase.firestore.FirebaseFirestore





class MutantListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMutantListBinding
    var db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMutantListBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_mutant_list)
        setContentView(binding.root)

        var listAdn= mutableListOf<Adn>()
        //db.collection("ADN").document("registros").get().addOnSuccessListener { documentSnapshot ->
        db.collection("ADN").get().addOnSuccessListener { document ->
            document.documents.forEach { documentSnapshot ->
                val tempAdn = documentSnapshot.toObject(Adn::class.java)
                if (tempAdn != null)
                listAdn.add(Adn(tempAdn.value, tempAdn.isMutant))
            }
            binding.recyclerViewMutantList.adapter = MutantListAdapter(listAdn)
        }

        binding.recyclerViewMutantList.addItemDecoration(DividerItemDecoration(binding.recyclerViewMutantList.context, DividerItemDecoration.VERTICAL))

        /*
        binding.recyclerViewMutantList.adapter =MutantListAdapter(listOf(
            Adn("assd,asdd,asd,ffff",true),
            Adn("assd,asdd,asd,ffff",true),
            Adn("assd,asdd,asd,ffff",true),
            Adn("assd,asdd,asd,ffff",true),
            Adn("assd,asdd,asd,ffff",false),
            Adn("assd,asdd,asd,ffff",true),
            Adn("assd,asdd,asd,ffff",true),
            Adn("assd,asdd,asd,ffff",false),
            Adn("assd,asdd,asd,ffff",true),
            Adn("assd,asdd,asd,ffff",false),
            Adn("assd,asdd,asd,ffff",false),
            Adn("assssd,asssddd,asddd,fffddf",false),
            Adn("asdddsd,asddddd,adddsd,ffddddff",false),
            Adn("assdddd,asdd,asd,ffdddff",false),
            Adn("asdddsd,adddsdd,adddsd,ffddff",false)
        ))*/
    }
}