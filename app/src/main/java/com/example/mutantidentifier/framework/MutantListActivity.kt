package com.example.mutantidentifier.framework

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mutantidentifier.data.models.Adn
import com.example.mutantidentifier.databinding.ActivityMutantListBinding
import androidx.recyclerview.widget.DividerItemDecoration


class MutantListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMutantListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMutantListBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_mutant_list)
        setContentView(binding.root)

        binding.recyclerViewMutantList.addItemDecoration(DividerItemDecoration(binding.recyclerViewMutantList.context, DividerItemDecoration.VERTICAL))
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
        ))
    }
}