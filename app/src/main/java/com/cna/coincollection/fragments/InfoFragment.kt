package com.cna.coincollection.fragments

import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.cna.coincollection.R
import com.cna.coincollection.RecyclerAdapter
import java.util.*


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class InfoFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null


    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: RecyclerAdapter

    private var coinList = mutableListOf<String>() /// Lista de banuti
    private var displayList = mutableListOf<String>() // Lista afisata de banuti
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setHasOptionsMenu(true)
        /// Populam listele cu date de test
        if(coinList.isEmpty())
        {
            coinList.add("1913 Liberty Head V Nickel")
            coinList.add("The 1343 Edward III Florin")
            coinList.add("The 1787 Fugio cent")
            coinList.add("The 723 Umayyad Gold Dinar")
            coinList.add("The 1787 Brasher Doubloon")
            coinList.add("1913 Liberty Head V Nickel")
            coinList.add("1914 Liberty Head V Nickel")
            coinList.add("The 1333 Edward III Florin")
            coinList.add("The 1727 Fugio cent")
            coinList.add("The 713 Umayyad Gold Dinar")
            coinList.add("The 1737 Brasher Doubloon")
            coinList.add("1814 Liberty Head V Nickel")
            displayList.addAll(coinList)
        }

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var coinView = inflater.inflate(R.layout.fragment_info, container, false)

        recyclerView = coinView.findViewById(R.id.recyclerView) // In fragmentul nostru cautam elementul cu id-ul recyclerView
        recyclerAdapter = RecyclerAdapter(displayList) // initializam adaptorul nistru si trimitem ca parametru lista displayList cu datele mock

        recyclerView.adapter = recyclerAdapter /// setam adaptorul nostru pe recyclerview


        return coinView
    }
    /// Search
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_menu,menu)

        var item: MenuItem = menu.findItem(R.id.action_search) /// Salvam in variabila item, item-ul action_search

        var searchView = item.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean { /// Cand dam click pe submit
                return true
            }

            override fun onQueryTextChange(searchText: String?): Boolean { /// Daca se schimba textul

                if(searchText!!.isNotEmpty()) // daca textul din search nu e gol
                {
                    displayList.clear() /// curatam lista de afisari
                    var search = searchText.toLowerCase(Locale.getDefault()) /// salvam textul cautat in variabila search
                    for(note in coinList) /// iteram prin array-ul de banuti reali
                    {
                        if(note.toString().toLowerCase(Locale.getDefault()).contains(search)){ /// daca am gasit un match, adaugam la lista de display
                            displayList.add(note)
                        }
                        recyclerView.adapter!!.notifyDataSetChanged() /// Ii spunem recycler view-ului ca s-au schimbat datele si sa faca o rerandare

                    }
                }else{
                    displayList.clear()
                    displayList.addAll(coinList)
                    recyclerView.adapter!!.notifyDataSetChanged()
                }
                return true
            }
        }
        )
        super.onCreateOptionsMenu(menu, inflater)
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            InfoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}