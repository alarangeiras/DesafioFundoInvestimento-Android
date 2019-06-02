package br.com.allanlarangeiras.desafioorama.model.types

enum class AmountRange(
    val index: Int, val amount: Double) {

    AMOUNT_1(0, 1000.0),
    AMOUNT_2(1, 5000.0),
    AMOUNT_3(2, 10000.0),
    AMOUNT_4(3, 15000.0),
    AMOUNT_5(4, 20000.0),
    AMOUNT_6(5, 25000.0),
    AMOUNT_7(6, 50000.0),
    AMOUNT_8(7, 1000000.0);

    companion object {
        fun createByProgress(progress: Int) : AmountRange {
            val ranges = AmountRange.values()
            for (range in ranges) {
                if (range.index == progress) {
                    return range
                }
            }

            return AMOUNT_8
        }
    }


}