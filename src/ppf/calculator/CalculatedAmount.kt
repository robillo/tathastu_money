package ppf.calculator

data class CalculatedAmount(
    var standardPeriodSumDeposited: Int = 0,
    var extensionPeriodSumDeposited: Int = 0,
    var totalStandardPeriodInterestEarned: Int = 0,
    var totalExtensionPeriodInterestEarned: Int = 0,
    var totalCorpusAfterStandardPeriod: Int = 0,
    var totalCorpusAfterExtensionPeriod: Int = 0
)