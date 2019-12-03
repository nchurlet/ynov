package com.example.myapplication.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.model.Machin
import com.example.myapplication.network.ApiHelpers
import com.example.myapplication.network.ApiRequestCallback
import com.example.myapplication.recyclerview.recyclerview.MachinAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    lateinit var machins : MutableList<Machin>
    lateinit var adapter: MachinAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        machins = loadMachins() // récupérations des données à injecter dans l'adapter du recyclerview
        // (Euh, LoadMachin() c'est une fonction que vous devez créez, quite à ce qu'elle renvoie une liste de données
        // en dur. Mais ce serait dommage puisque vous savez taper dans un api)
        adapter = MachinAdapter(machins/*, this */)
        // Construction de l'adapter qu'on a créé avec les données. This peut être utile pour le cas du click

        val recyclerView = recyclerview_machins // on récupère ici le RV concerné
        recyclerView.layoutManager = LinearLayoutManager(this)
        // Le layout manager peut être utilisé pour styliser le RV.
        // On peut choisir son orientation le nombre de colonnes etc. RTFM
        recyclerView.adapter = adapter
        // Voilà c'est fait. On a plus qu'à voir si ça compile. On peut aussi partir d'ici avec un code qui plante et
        // créer à chaque étape le fichier qui manque

    }

    private fun loadMachins(): MutableList<Machin> {

        val result = mutableListOf<Machin>()
        for (i in 1..15) {
            result.add(Machin("Label$i", "Description$i"))
        }
        return result
    }
}
