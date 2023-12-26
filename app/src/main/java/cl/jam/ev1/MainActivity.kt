package cl.jam.ev1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import cl.jam.ev1.restorant.CuentaMesa
import cl.jam.ev1.restorant.ItemMenu
import java.text.NumberFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private var mesa1: CuentaMesa = CuentaMesa(1)
    private var cazuela: ItemMenu = ItemMenu("Cazuela", 10000);
    private var pastel: ItemMenu = ItemMenu("Pastel", 36000);
    private var nf = NumberFormat.getNumberInstance(Locale("es", "CL"))


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

         //Eventos del Pastel
        configurarPastel()

        //Eventos de la cazuela
        configurarCazuela()

        // Eventos del Switch
        configurarSwitch()
    }

    private fun configurarPastel() {
        val cntPastel = findViewById<EditText>(R.id.numPastel)

        // Solo utilizo el evento del text changed
        cntPastel.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                val textoActual = charSequence.toString()
                if (textoActual.isNotEmpty() && textoActual.matches("[0-9]+".toRegex())) {
                    mesa1.agregarItem(pastel, textoActual.toInt())
                    actualizarValores()
                }
            }

            override fun afterTextChanged(p0: Editable?) {}
        })
    }

    private fun configurarCazuela() {
        val cntCazuela = findViewById<EditText>(R.id.numCazuela)

        // Solo utilizo el evento del text changed
        cntCazuela.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                val textoActual = charSequence.toString()
                if (textoActual.isNotEmpty() && textoActual.matches("[0-9]+".toRegex())) {
                    mesa1.agregarItem(cazuela, textoActual.toInt())
                    actualizarValores()
                }
            }

            override fun afterTextChanged(p0: Editable?) {}

        })
    }

    private fun configurarSwitch() {
        val miSwitch = findViewById<Switch>(R.id.switch1)
        miSwitch.isChecked = true

        miSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            mesa1.aceptaPropina = isChecked
            actualizarValores()
        }
    }

    // Funcion de actualizar los valores por pantalla
    // cada vez que se realiza algun cambio
    private fun actualizarValores() {
        // Valor sin propina SP
        val tSP = findViewById<TextView>(R.id.totalSP)

        // Valor de la propina (solo se calcula si esta activo el swith)
        val tP = findViewById<TextView>(R.id.totalP)

        // Valor total con la propina (si es que corresponde)
        val t = findViewById<TextView>(R.id.total)

        tSP.text = "$" + nf.format(mesa1.calcularTotalSinPropina())
        tP.text = "$" + nf.format(mesa1.calcularPropina())
        t.text = "$" + nf.format(mesa1.calcularTotalConPropina())
    }

}


