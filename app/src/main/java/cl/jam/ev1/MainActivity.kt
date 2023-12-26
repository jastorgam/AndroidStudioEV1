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

    var mesa1: CuentaMesa = CuentaMesa(1)
    var cazuela: ItemMenu = ItemMenu("Cazuela", 10000);
    var pastel: ItemMenu = ItemMenu("Pastel", 36000);
    var nf = NumberFormat.getNumberInstance(Locale("es", "CL"))


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configurarSwitch()
        configurarPastel()
        configurarCazuela()

    }

    private fun configurarPastel() {
        val cntPastel = findViewById<EditText>(R.id.numPastel)

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

    private fun actualizarValores() {
        val tSP = findViewById<TextView>(R.id.totalSP)
        val tP = findViewById<TextView>(R.id.totalP)
        val tCP = findViewById<TextView>(R.id.totalCP)

        tSP.text = "$" + nf.format(mesa1.calcularTotalSinPropina())
        tP.text = "$" + nf.format(mesa1.calcularPropina())
        tCP.text = "$" + nf.format(mesa1.calcularTotalConPropina())
    }

}


