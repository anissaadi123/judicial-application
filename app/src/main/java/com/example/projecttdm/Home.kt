package com.example.projecttdm

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.IOError
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Home.newInstance] factory method to
 * create an instance of this fragment.
 */

val arrets = ArrayList<Arret>()
class Home : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var appDb : AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)

        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.context?.let { it -> AppDatabase.getDatabase(it).also { appDb = it } }

        initaliseList()

        rv_arret.layoutManager = LinearLayoutManager(context)
        rv_arret.setHasFixedSize(true)
        val adapter = ArretAdapter(arrets)

        rv_arret.adapter = adapter

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Home.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Home().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    private fun writeData() : List<Arret>?{

        var lst : List<Arret> = listOf()
        GlobalScope.launch {
            lst = appDb.arretDao().getAll()
        }
        return lst

    }



    private fun readData(){

        var lst: List<Arret>? = null
        GlobalScope.launch (Dispatchers.IO){
            lst = appDb.arretDao().getAll()
        }

        Toast.makeText(context, "${lst?.get(0).toString()}", Toast.LENGTH_SHORT).show()
    }

    fun initaliseList(){
        val format = "dd-mm-yyyy"
        val sdf = SimpleDateFormat(format)
        lateinit var test : Arret
        val nums = arrayListOf<Int>(
            123456, 254689, 541289, 512547
        )
        val sources = arrayListOf<String>("الغرفة الادارية", "الغرفة الادارية","الغرفة الادارية","الغرفة الادارية")
        val dates = arrayListOf<String>("10-02-2022", "15-10-2020" , "05-05-2021", "10-02-2015")
        val isFavorites = arrayListOf<Boolean>(false,true,true,false)

        for(i in 0 until nums.size){
            test = Arret(nums[i],sources[i], dates[i],isFavorites[i])
            arrets.add(i,test)
        }

    }

}