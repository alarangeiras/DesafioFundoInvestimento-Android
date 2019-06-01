package br.com.allanlarangeiras.desafioorama.utils

object NumberUtils {

    fun formatPercentage(number:Float): String {
        return String.format("%.2f", number).replace(".", ",")
    }

    fun formatMoney(number:Double): String {
        return String.format("%,.2f", number)
    }

}