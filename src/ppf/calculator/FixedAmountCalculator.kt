package ppf.calculator

class FixedAmountCalculator(
    private val standardPeriodDeposit: Int,
    private val extensionPeriodDeposit: Int = 0,
    private val isAmountDepositedMonthly: Boolean = true,
    private val doesUserHaveExistingPpfAccount: Boolean = false,
    private val doesUserExtendMaturityPeriod: Boolean = false,
    private val doesUserDepositInExtensionPeriod: Boolean = false,
    private val doesUserDepositSameAmountPerUnitInExtensionPeriod: Boolean = false
) {

    companion object {
        private const val NUMBER_OF_MONTHS = 12
        private const val ANNUAL_INTEREST_RATE = 7.9f
        private const val BASIC_MATURITY_PERIOD_YEARS = 15
        private const val EXTENDED_MATURITY_PERIOD_YEARS = 5
        private const val MAX_DEPOSIT_AMOUNT_LIMIT_PER_YEAR = 150000
    }

    fun computeOverallAmount(): CalculatedAmount {
        val result = CalculatedAmount()
        if(areStandardAndExtensionAmountsValid(standardPeriodDeposit, extensionPeriodDeposit)) {
            result.standardPeriodSumDeposited =
                    getDepositedSum(standardPeriodDeposit, BASIC_MATURITY_PERIOD_YEARS)

            result.extensionPeriodSumDeposited =
                getDepositedSum(extensionPeriodDeposit, BASIC_MATURITY_PERIOD_YEARS)
        }
        return CalculatedAmount()
    }

    private fun getDepositedSum(depositAmount: Int, numberOfYears: Int): Int {
        var amount = depositAmount

        if(isAmountDepositedMonthly)
            amount *= NUMBER_OF_MONTHS

        return amount * numberOfYears
    }

    private fun areStandardAndExtensionAmountsValid(depositAmountStandardPeriod: Int, depositAmountExtensionPeriod: Int): Boolean {
        val depositAmountStandardPerYear: Int
        val depositAmountExtensionPerYear: Int
        if(isAmountDepositedMonthly) {
            depositAmountStandardPerYear = depositAmountStandardPeriod * NUMBER_OF_MONTHS
            depositAmountExtensionPerYear = depositAmountExtensionPeriod * NUMBER_OF_MONTHS
        }
        else {
            depositAmountStandardPerYear = depositAmountStandardPeriod
            depositAmountExtensionPerYear = depositAmountExtensionPeriod
        }
        return !isAmountPerYearValid(depositAmountStandardPerYear, depositAmountExtensionPerYear)
    }

    private fun isAmountPerYearValid(standardDepositAmountPerYear: Int, extensionDepositAmountPerYear: Int): Boolean {
        return standardDepositAmountPerYear < MAX_DEPOSIT_AMOUNT_LIMIT_PER_YEAR &&
                extensionDepositAmountPerYear < MAX_DEPOSIT_AMOUNT_LIMIT_PER_YEAR
    }
}