package com.example.myapplication.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.model.ForecastResult
import com.example.myapplication.model.Machin
import com.example.myapplication.network.ApiError
import com.example.myapplication.network.ApiHelpers
import com.example.myapplication.network.ApiRequestCallback
import com.example.myapplication.recyclerview.recyclerview.MachinAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    lateinit var machins : MutableList<Machin>
    lateinit var machinAdapter: MachinAdapter
    lateinit var apiHelpers : ApiHelpers

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        apiHelpers = ApiHelpers(this)

        val recyclerView = recyclerview_machins // on récupère ici le RV concerné
        recyclerView.layoutManager = LinearLayoutManager(this)
        // Le layout manager peut être utilisé pour styliser le RV.
        // On peut choisir son orientation le nombre de colonnes etc. Pour en savoir plus lire la doc

        machins = mutableListOf() // La liste doit être initialisée avant d'être passée à l'adapter.
        machinAdapter = MachinAdapter(machins/*, this */)
        // Construction de l'adapter qu'on a créé avec les données. This peut être utile pour capter l'événement de clic.
        // C'est bien l'objet machins qui est passé à l'adapter, donc lorsqu'on modifiera machins, on aura juste à
        // notifier l'adapter que les données ont changé.
        recyclerView.adapter = machinAdapter
        // Voilà c'est fait. On a plus qu'à voir si ça compile. On peut aussi partir d'ici avec un code qui plante et
        // créer à chaque étape le fichier qui manque

        loadMachinsByApi() // récupérations des données à injecter dans l'adapter du recyclerview
        // (LoadMachin() c'est une fonction que vous devez créer, quite à ce qu'elle renvoie une liste de données
        // en dur. Mais ce serait dommage puisque vous savez taper dans un api)
    }

    /**
     * Cette fonction sert à créer une fakeliste de données de type Machin.
     * Remarquons qu'on a pas besoin de lui faire retourner la liste. On modifie directement la liste machins.
     */
    private fun loadMachins() {
        for (i in 1..15) {
            machins.add(Machin("Label$i", "Description$i"))
        }
        machinAdapter.notifyDataSetChanged()
    }

    /**
     *  Cette fonction utilise l'apiHelper et sa fonction getForecastByIdAsync qu'on y a écrit.
     *
     */
    private fun loadMachinsByApi() {
        apiHelpers.getForecastByIdAsync(
            6454573, // Lyon = 6454573
            15, // 15 éléments de prévision météo à charger
            object : ApiRequestCallback<ForecastResult?>() {
                override fun onSuccess(result: ForecastResult?) {
                    super.onSuccess(result)

                    runOnUiThread {
                        if (result != null) {
                            val forecastList = result.forecastList
                            for (item in forecastList){
                                val machinResult = Machin(item.weather[0].main, item.weather[0].description)
                                Log.d(MainActivity::class.java.canonicalName, machinResult.toString())
                                machins.add(machinResult)
                            }
                            machinAdapter.notifyDataSetChanged() // En notifiant l'adapteur du RecyclerView à ce moment là,
                            // on s'assure qu'il est bien notifié au retour de la requête réussie.
                            // Quand la liste machins est modifiée
                        }
                    }
                }

                override fun onError(error: ApiError) {
                    super.onError(error)
                    Log.d(
                        MainActivity::class.java.canonicalName,
                        "onError() called with: error.code  = [" + error.code
                            .toString() + " & error.message${error.message}]"
                    )
                }
            })
    }
}
