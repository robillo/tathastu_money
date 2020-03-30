package ppf

import ppf.calculator.FixedAmountCalculator

private const val MONTHLY_INSTALLMENT_PPF_AMOUNT = 12000
private const val YEARLY_INSTALLMENT_PPF_AMOUNT = 144000

fun main(args: Array<String>) {
    FixedAmountCalculator(MONTHLY_INSTALLMENT_PPF_AMOUNT).computeOverallAmount()
}