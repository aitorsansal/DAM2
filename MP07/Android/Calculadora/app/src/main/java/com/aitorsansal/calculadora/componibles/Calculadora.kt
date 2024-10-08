package com.aitorsansal.calculadora.componibles

import android.annotation.SuppressLint
import android.graphics.Paint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

@SuppressLint("AutoboxingStateCreation")
@Composable
fun Calculadora(
    modifier: Modifier = Modifier,
    colorFons: Color = MaterialTheme.colorScheme.primaryContainer,
    colorText: Color = MaterialTheme.colorScheme.secondaryContainer,
    colorMarcBoto : Color = MaterialTheme.colorScheme.onPrimaryContainer,
    colorBoto: Color = MaterialTheme.colorScheme.primary,
    colorTextVisor: Color = MaterialTheme.colorScheme.onPrimary,
    colorFonsVisor: Color = MaterialTheme.colorScheme.onPrimaryContainer
)
{
    var firstNumber by remember { mutableStateOf("0") }
    var secondNumber by remember {mutableStateOf("0")}
    var memoryNumber by remember { mutableDoubleStateOf(0.0) }
    var operation by remember { mutableStateOf("") }
    var hasDecimal by remember { mutableStateOf(false) }
    var topText by remember { mutableStateOf("") }
    var mainText by remember { mutableStateOf(firstNumber) }
    var result by remember { mutableDoubleStateOf(0.0) }
    Column(modifier = modifier.background(color = colorFons).padding(10.dp)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(3.dp))
                .background(color = colorFonsVisor)
                .padding(1.dp)
        ) {
            Text(
                text = topText, fontSize = 10.sp, color = colorText,
                modifier = Modifier.align(alignment = Alignment.End).padding(horizontal = 5.dp)
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                text = mainText, fontSize = 25.sp, color = colorText,
                modifier = Modifier.align(alignment = Alignment.End).padding(horizontal = 5.dp)
            )
        }
        Column(modifier = Modifier.fillMaxSize()) {
            Row(modifier = Modifier.fillMaxWidth().padding(2.dp)) {
                Button(
                    {
                        firstNumber = "0"
                        secondNumber = "0"
                        operation = ""
                        hasDecimal = false
                        topText = ""
                        mainText = firstNumber
                    },
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier.clip(shape = RoundedCornerShape(5.dp))
                        .padding(horizontal = 1.dp)
                        .background(color = colorBoto).weight(1F)
                        .border(
                            width = 2.dp,
                            color = colorMarcBoto,
                            shape = RoundedCornerShape(5.dp)
                        ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorBoto,
                        contentColor = colorTextVisor
                    )
                ) {
                    Text(
                        "C", fontSize = 13.sp,
                        modifier = Modifier.background(color = Color.Transparent)
                    )
                }
                Button(
                    {},
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier.clip(shape = RoundedCornerShape(5.dp))
                        .padding(horizontal = 1.dp)
                        .background(color = colorBoto).weight(1F)
                        .border(
                            width = 2.dp,
                            color = colorMarcBoto,
                            shape = RoundedCornerShape(5.dp)
                        ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorBoto,
                        contentColor = colorTextVisor
                    )
                ) {
                    Text(
                        "M+", fontSize = 13.sp,
                        modifier = Modifier.background(color = Color.Transparent)
                    )
                }
                Button(
                    {},
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier.clip(shape = RoundedCornerShape(5.dp))
                        .padding(horizontal = 1.dp)
                        .background(color = colorBoto).weight(1F)
                        .border(
                            width = 2.dp,
                            color = colorMarcBoto,
                            shape = RoundedCornerShape(5.dp)
                        ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorBoto,
                        contentColor = colorTextVisor
                    )
                ) {
                    Text(
                        "rM", fontSize = 13.sp,
                        modifier = Modifier.background(color = Color.Transparent)
                    )
                }
                Button(//operationButton
                    {
                        if(result != 0.0)
                        {
                            firstNumber = result.toString()
                            secondNumber = "0"
                            result = 0.0
                            topText = ""
                        }
                        operation = "/"
                        firstNumber = RemoveLastPoint(firstNumber)
                        val pair = CanToInt(firstNumber)
                        topText = if(pair.first)
                            pair.second + operation
                        else
                            firstNumber + operation
                        hasDecimal = false
                    },
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier.clip(shape = RoundedCornerShape(5.dp))
                        .padding(horizontal = 1.dp)
                        .background(color = colorBoto).weight(1F)
                        .border(
                            width = 2.dp,
                            color = colorMarcBoto,
                            shape = RoundedCornerShape(5.dp)
                        ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorBoto,
                        contentColor = colorTextVisor
                    )
                ) {
                    Text(
                        "/", fontSize = 13.sp,
                        modifier = Modifier.background(color = Color.Transparent)
                    )
                }

            }
            Row(modifier = Modifier.fillMaxWidth().padding(2.dp)) {
                Button(
                    {
                        if(result != 0.0)
                        {
                            firstNumber = ""
                            operation = ""
                            result = 0.0
                            secondNumber = "0"
                        }
                        if(operation == "")
                        {
                            if(firstNumber == "0") firstNumber = ""
                            firstNumber += "7"
                            mainText = firstNumber
                        }
                        else
                        {
                            if(secondNumber == "0") secondNumber = ""
                            secondNumber += "7"
                            mainText = secondNumber
                        }
                    },
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier.clip(shape = RoundedCornerShape(5.dp))
                        .padding(horizontal = 1.dp)
                        .background(color = colorBoto).weight(1F)
                        .border(
                            width = 2.dp,
                            color = colorMarcBoto,
                            shape = RoundedCornerShape(5.dp)
                        ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorBoto,
                        contentColor = colorTextVisor
                    )
                ) {
                    Text(
                        "7", fontSize = 13.sp,
                        modifier = Modifier.background(color = Color.Transparent)
                    )
                }
                Button(
                    {
                        if(result != 0.0)
                        {
                            firstNumber = ""
                            operation = ""
                            result = 0.0
                            secondNumber = "0"
                        }
                        if(operation == "")
                        {
                            if(firstNumber == "0") firstNumber = ""
                            firstNumber += "8"
                            mainText = firstNumber
                        }
                        else
                        {
                            if(secondNumber == "0") secondNumber = ""
                            secondNumber += "8"
                            mainText = secondNumber
                        }
                    },
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier.clip(shape = RoundedCornerShape(5.dp))
                        .padding(horizontal = 1.dp)
                        .background(color = colorBoto).weight(1F)
                        .border(
                            width = 2.dp,
                            color = colorMarcBoto,
                            shape = RoundedCornerShape(5.dp)
                        ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorBoto,
                        contentColor = colorTextVisor
                    )
                ) {
                    Text(
                        "8", fontSize = 13.sp,
                        modifier = Modifier.background(color = Color.Transparent)
                    )
                }
                Button(
                    {
                        if(result != 0.0)
                        {
                            firstNumber = ""
                            operation = ""
                            result = 0.0
                            secondNumber = "0"
                        }
                        if(operation == "")
                        {
                            if(firstNumber == "0") firstNumber = ""
                            firstNumber += "9"
                            mainText = firstNumber
                        }
                        else
                        {
                            if(secondNumber == "0") secondNumber = ""
                            secondNumber += "9"
                            mainText = secondNumber
                        }
                    },
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier.clip(shape = RoundedCornerShape(5.dp))
                        .padding(horizontal = 1.dp)
                        .background(color = colorBoto).weight(1F)
                        .border(
                            width = 2.dp,
                            color = colorMarcBoto,
                            shape = RoundedCornerShape(5.dp)
                        ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorBoto,
                        contentColor = colorTextVisor
                    )
                ) {
                    Text(
                        "9", fontSize = 13.sp,
                        modifier = Modifier.background(color = Color.Transparent)
                    )
                }
                Button(//operationButton
                    {
                        if(result != 0.0)
                        {
                            firstNumber = result.toString()
                            secondNumber = "0"
                            result = 0.0
                            topText = ""
                        }
                        operation = "*"
                        firstNumber = RemoveLastPoint(firstNumber)
                        val pair = CanToInt(firstNumber)
                        topText = if(pair.first)
                            pair.second + operation
                        else
                            firstNumber + operation
                        hasDecimal = false
                    },
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier.clip(shape = RoundedCornerShape(5.dp))
                        .padding(horizontal = 1.dp)
                        .background(color = colorBoto).weight(1F)
                        .border(
                            width = 2.dp,
                            color = colorMarcBoto,
                            shape = RoundedCornerShape(5.dp)
                        ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorBoto,
                        contentColor = colorTextVisor
                    )
                ) {
                    Text(
                        "*", fontSize = 13.sp,
                        modifier = Modifier.background(color = Color.Transparent)
                    )
                }

            }
            Row(modifier = Modifier.fillMaxWidth().padding(2.dp)) {
                Button(
                    {
                        if(result != 0.0)
                        {
                            firstNumber = ""
                            operation = ""
                            result = 0.0
                            secondNumber = "0"
                        }
                        if(operation == "")
                        {
                            if(firstNumber == "0") firstNumber = ""
                            firstNumber += "4"
                            mainText = firstNumber
                        }
                        else
                        {
                            if(secondNumber == "0") secondNumber = ""
                            secondNumber += "4"
                            mainText = secondNumber
                        }
                    },
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier.clip(shape = RoundedCornerShape(5.dp))
                        .padding(horizontal = 1.dp)
                        .background(color = colorBoto).weight(1F)
                        .border(
                            width = 2.dp,
                            color = colorMarcBoto,
                            shape = RoundedCornerShape(5.dp)
                        ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorBoto,
                        contentColor = colorTextVisor
                    )
                ) {
                    Text(
                        "4", fontSize = 13.sp,
                        modifier = Modifier.background(color = Color.Transparent)
                    )
                }
                Button(
                    {
                        if(result != 0.0)
                        {
                            firstNumber = ""
                            operation = ""
                            result = 0.0
                            secondNumber = "0"
                        }
                        if(operation == "")
                        {
                            if(firstNumber == "0") firstNumber = ""
                            firstNumber += "5"
                            mainText = firstNumber
                        }
                        else
                        {
                            if(secondNumber == "0") secondNumber = ""
                            secondNumber += "5"
                            mainText = secondNumber
                        }
                    },
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier.clip(shape = RoundedCornerShape(5.dp))
                        .padding(horizontal = 1.dp)
                        .background(color = colorBoto).weight(1F)
                        .border(
                            width = 2.dp,
                            color = colorMarcBoto,
                            shape = RoundedCornerShape(5.dp)
                        ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorBoto,
                        contentColor = colorTextVisor
                    )
                ) {
                    Text(
                        "5", fontSize = 13.sp,
                        modifier = Modifier.background(color = Color.Transparent)
                    )
                }
                Button(
                    {
                        if(result != 0.0)
                        {
                            firstNumber = ""
                            operation = ""
                            result = 0.0
                            secondNumber = "0"
                        }
                        if(operation == "")
                        {
                            if(firstNumber == "0") firstNumber = ""
                            firstNumber += "6"
                            mainText = firstNumber
                        }
                        else
                        {
                            if(secondNumber == "0") secondNumber = ""
                            secondNumber += "6"
                            mainText = secondNumber
                        }
                    },
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier.clip(shape = RoundedCornerShape(5.dp))
                        .padding(horizontal = 1.dp)
                        .background(color = colorBoto).weight(1F)
                        .border(
                            width = 2.dp,
                            color = colorMarcBoto,
                            shape = RoundedCornerShape(5.dp)
                        ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorBoto,
                        contentColor = colorTextVisor
                    )
                ) {
                    Text(
                        "6", fontSize = 13.sp,
                        modifier = Modifier.background(color = Color.Transparent)
                    )
                }
                Button(//operationButton
                    {
                        if(result != 0.0)
                        {
                            firstNumber = result.toString()
                            secondNumber = "0"
                            result = 0.0
                            topText = ""
                        }
                        operation = "-"
                        firstNumber = RemoveLastPoint(firstNumber)
                        val pair = CanToInt(firstNumber)
                        topText = if(pair.first)
                            pair.second + operation
                        else
                            firstNumber + operation
                        hasDecimal = false
                    },
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier.clip(shape = RoundedCornerShape(5.dp))
                        .padding(horizontal = 1.dp)
                        .background(color = colorBoto).weight(1F)
                        .border(
                            width = 2.dp,
                            color = colorMarcBoto,
                            shape = RoundedCornerShape(5.dp)
                        ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorBoto,
                        contentColor = colorTextVisor
                    )
                ) {
                    Text(
                        "-", fontSize = 13.sp,
                        modifier = Modifier.background(color = Color.Transparent)
                    )
                }

            }
            Row(modifier = Modifier.fillMaxWidth().padding(2.dp)) {
                Button(
                    {
                        if(result != 0.0)
                        {
                            firstNumber = ""
                            operation = ""
                            result = 0.0
                            secondNumber = "0"
                        }
                        if(operation == "")
                        {
                            if(firstNumber == "0") firstNumber = ""
                            firstNumber += "1"
                            mainText = firstNumber
                        }
                        else
                        {
                            if(secondNumber == "0") secondNumber = ""
                            secondNumber += "1"
                            mainText = secondNumber
                        }

                    },
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier.clip(shape = RoundedCornerShape(5.dp))
                        .padding(horizontal = 1.dp)
                        .background(color = colorBoto).weight(1F)
                        .border(
                            width = 2.dp,
                            color = colorMarcBoto,
                            shape = RoundedCornerShape(5.dp)
                        ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorBoto,
                        contentColor = colorTextVisor
                    )
                ) {
                    Text(
                        "1", fontSize = 13.sp,
                        modifier = Modifier.background(color = Color.Transparent)
                    )
                }
                Button(
                    {
                        if(result != 0.0)
                        {
                            firstNumber = ""
                            operation = ""
                            result = 0.0
                            secondNumber = "0"
                        }
                        if(operation == "")
                        {
                            if(firstNumber == "0") firstNumber = ""
                            firstNumber += "2"
                            mainText = firstNumber
                        }
                        else
                        {
                            if(secondNumber == "0") secondNumber = ""
                            secondNumber += "2"
                            mainText = secondNumber
                        }
                    },
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier.clip(shape = RoundedCornerShape(5.dp))
                        .padding(horizontal = 1.dp)
                        .background(color = colorBoto).weight(1F)
                        .border(
                            width = 2.dp,
                            color = colorMarcBoto,
                            shape = RoundedCornerShape(5.dp)
                        ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorBoto,
                        contentColor = colorTextVisor
                    )
                ) {
                    Text(
                        "2", fontSize = 13.sp,
                        modifier = Modifier.background(color = Color.Transparent)
                    )
                }
                Button(
                    {
                        if(result != 0.0)
                        {
                            firstNumber = ""
                            operation = ""
                            result = 0.0
                            secondNumber = "0"
                        }
                        if(operation == "")
                        {
                            if(firstNumber == "0") firstNumber = ""
                            firstNumber += "3"
                            mainText = firstNumber
                        }
                        else
                        {
                            if(secondNumber == "0") secondNumber = ""
                            secondNumber += "3"
                            mainText = secondNumber
                        }
                    },
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier.clip(shape = RoundedCornerShape(5.dp))
                        .padding(horizontal = 1.dp)
                        .background(color = colorBoto).weight(1F)
                        .border(
                            width = 2.dp,
                            color = colorMarcBoto,
                            shape = RoundedCornerShape(5.dp)
                        ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorBoto,
                        contentColor = colorTextVisor
                    )
                ) {
                    Text(
                        "3", fontSize = 13.sp,
                        modifier = Modifier.background(color = Color.Transparent)
                    )
                }
                Button(//operationButton
                    {
                        if(result != 0.0)
                        {
                            firstNumber = result.toString()
                            secondNumber = "0"
                            result = 0.0
                            topText = ""
                        }
                        operation = "+"
                        firstNumber = RemoveLastPoint(firstNumber)
                        val pair = CanToInt(firstNumber)
                        topText = if(pair.first)
                            pair.second + operation
                        else
                            firstNumber + operation
                        hasDecimal = false
                    },
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier.clip(shape = RoundedCornerShape(5.dp))
                        .padding(horizontal = 1.dp)
                        .background(color = colorBoto).weight(1F)
                        .border(
                            width = 2.dp,
                            color = colorMarcBoto,
                            shape = RoundedCornerShape(5.dp)
                        ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorBoto,
                        contentColor = colorTextVisor
                    )
                ) {
                    Text(
                        "+", fontSize = 13.sp,
                        modifier = Modifier.background(color = Color.Transparent)
                    )
                }

            }
            Row(modifier = Modifier.fillMaxWidth().padding(2.dp)) {
                Button(
                    {
                        if(result != 0.0)
                        {
                            firstNumber = ""
                            operation = ""
                            result = 0.0
                            secondNumber = "0"
                        }
                        if(operation == "")
                        {
                            if(firstNumber != "0")
                                firstNumber += "0"
                            mainText = firstNumber
                        }
                        else
                        {
                            if(secondNumber != "0")
                                secondNumber += "0"
                            mainText = secondNumber
                        }
                    },
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier.clip(shape = RoundedCornerShape(5.dp))
                        .padding(horizontal = 1.dp)
                        .background(color = colorBoto).weight(2F)
                        .border(
                            width = 2.dp,
                            color = colorMarcBoto,
                            shape = RoundedCornerShape(5.dp)
                        ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorBoto,
                        contentColor = colorTextVisor
                    )
                ) {
                    Text(
                        "0", fontSize = 13.sp,
                        modifier = Modifier.background(color = Color.Transparent)
                    )
                }
                Button(
                    {
                        if(!hasDecimal)
                        {
                            if(operation == "")
                            {
                                firstNumber += "."
                                mainText = firstNumber
                            }
                            else
                            {
                                secondNumber += "."
                                mainText = secondNumber
                            }
                            hasDecimal = true
                        }
                    },
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier.clip(shape = RoundedCornerShape(5.dp))
                        .padding(horizontal = 1.dp)
                        .background(color = colorBoto).weight(1F)
                        .border(
                            width = 2.dp,
                            color = colorMarcBoto,
                            shape = RoundedCornerShape(5.dp)
                        ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorBoto,
                        contentColor = colorTextVisor
                    )
                ) {
                    Text(
                        ".", fontSize = 13.sp,
                        modifier = Modifier.background(color = Color.Transparent)
                    )
                }
                Button(//operationButton
                    {
                        if(secondNumber == "0") secondNumber = firstNumber
                        if(operation == "/" && secondNumber == "0")
                            mainText = "Can't divide by 0"
                        else
                        {
                            firstNumber = RemoveLastPoint(firstNumber)
                            val pairFirst = CanToInt(firstNumber)
                            if(pairFirst.first)
                                firstNumber = pairFirst.second
                            val pairSecond = CanToInt(secondNumber)
                            if(pairSecond.first)
                                secondNumber = pairSecond.second
                            topText = "$firstNumber$operation$secondNumber="
                            result = Operate(firstNumber, secondNumber, operation)
                            firstNumber = result.toString()
                            val resPair = CanToInt(result.toString())
                            mainText = if(resPair.first) resPair.second
                            else result.toString()
                        }
                    },
                    shape = RoundedCornerShape(5.dp),
                    modifier = Modifier.clip(shape = RoundedCornerShape(5.dp))
                        .padding(horizontal = 1.dp)
                        .background(color = colorBoto).weight(1F)
                        .border(
                            width = 2.dp,
                            color = colorMarcBoto,
                            shape = RoundedCornerShape(5.dp)
                        ),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorBoto,
                        contentColor = colorTextVisor
                    )
                ) {
                    Text(
                        "=", fontSize = 13.sp,
                        modifier = Modifier.background(color = Color.Transparent)
                    )
                }

            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewCalculadora()
{
    Calculadora(modifier = Modifier.size(height = 325.dp, width = 300.dp))
}

fun Operate(num1:String, num2:String, operation:String): Double{
    var num1 = num1.toDouble()
    var num2 = num2.toDouble()
    var result = 0.0
    when(operation)
    {
        "+" -> result = num1 + num2
        "-" -> result = num1 - num2
        "/" -> result = num1 / num2
        "*" -> result = num1 * num2
    }
    return result
}

fun RemoveLastPoint(txt : String ) : String {
    if(txt.last() == '.') txt.dropLast(1)
    return txt
}

fun CanToInt(txt : String) : Pair<Boolean,String>
{
    return if(txt.toDouble() == txt.toDouble().toInt().toDouble())
        Pair(true, txt.toDouble().toInt().toString())
    else
        Pair(false, "")
}