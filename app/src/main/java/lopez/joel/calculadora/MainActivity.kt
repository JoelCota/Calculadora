package lopez.joel.calculadora

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private var input = ""
    private var operator = ""
    private var lastInput = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val ac : Button = findViewById(R.id.ac)
        val porcentaje : Button = findViewById(R.id.porcentaje)
        val division : Button = findViewById(R.id.division)
        val multiplicacion : Button = findViewById(R.id.multiplicacion)
        val suma : Button = findViewById(R.id.suma)
        val resta : Button = findViewById(R.id.resta)
        val igual : Button = findViewById(R.id.igual)
        val uno : Button = findViewById(R.id.cero)
        val dos : Button = findViewById(R.id.uno)
        val tres : Button = findViewById(R.id.dos)
        val cuatro : Button = findViewById(R.id.tres)
        val cinco : Button = findViewById(R.id.cuatro)
        val seis : Button = findViewById(R.id.cinco)
        val siete : Button = findViewById(R.id.seis)
        val ocho : Button = findViewById(R.id.siete)
        val nueve : Button = findViewById(R.id.ocho)
        val cero : Button = findViewById(R.id.nueve)
        val display:TextView = findViewById(R.id.display)

        val numberButtons = listOf(uno, dos, tres, cuatro, cinco, seis, siete, ocho, nueve, cero)
        numberButtons.forEach { button ->
            button.setOnClickListener {
                input += button.text.toString()
                display.text = input
            }
        }

        val operatorButtons = listOf(suma, resta, multiplicacion, division, porcentaje)
        operatorButtons.forEach { button ->
            button.setOnClickListener {
                if (input.isNotEmpty()) {
                    lastInput = input
                    operator = button.text.toString()
                    input = ""
                }
            }
        }

        igual.setOnClickListener {
            if (lastInput.isNotEmpty() && input.isNotEmpty()) {
                val num1 = lastInput.toDouble()
                val num2 = input.toDouble()
                val result = when (operator) {
                    "+" -> num1 + num2
                    "-" -> num1 - num2
                    "x" -> num1 * num2
                    "/" -> if (num2 != 0.0) num1 / num2 else "Error"
                    "%" -> num1 % num2
                    else -> "Error"
                }
                display.text = result.toString()
                input = result.toString()
                lastInput = ""
                operator = ""
            }
        }

        ac.setOnClickListener {
            input = ""
            lastInput = ""
            operator = ""
            display.text = "0"
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }


}