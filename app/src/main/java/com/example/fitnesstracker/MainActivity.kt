package com.example.fitnesstracker

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fitnesstracker.databinding.ActivityMainBinding
import com.example.fitnesstracker.databinding.MainItemBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.btImc.setOnClickListener {
//            startActivity(Intent(this@MainActivity, ImcActivity::class.java))
//        }

        val mainItem = arrayListOf<MainItem>()
        mainItem.add(
            MainItem(
                1,
                R.drawable.ic_baseline_wb_sunny_24,
                R.string.label_imc,
                Color.GREEN
            )
        )

        mainItem.add(
            MainItem(
                1,
                R.drawable.ic_launcher_background,
                R.string.age,
                Color.BLACK
            )
        )

        //1--> Definir o comportamento de exibição do layout da recyclerVieww
        //mosaic
        //grid
        //linear (horizontal \ Vertical

        binding.rvMain.layoutManager = LinearLayoutManager(this)

        val mainAdapter = MainAdapter(mainItem)
        binding.rvMain.adapter = mainAdapter

    }

    private class MainAdapter(val mainItem: ArrayList<MainItem>) :
        RecyclerView.Adapter<MainViewHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
            val binding =
                MainItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return MainViewHolder(binding)

        }

//        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//            val binding = SearchBinding
//                .inflate(LayoutInflater.from(parent.context), parent, false)
//            return ViewHolder(binding)

        override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
            holder.bind(mainItem[position])
        }

        override fun getItemCount() = mainItem.size

    }

    //Entenda como sendo a View da celula que esta dentro do Recyclerview
    private class MainViewHolder(val binding: MainItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: MainItem) = with(binding) {
            tvName.text = position.getTextStringId().toString()

        }
    }


}