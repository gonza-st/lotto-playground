import org.gonza.kotlinplayground.domain.LottoNumber
import org.gonza.kotlinplayground.enum.LottoRange

class Lotto private constructor(
    numberSet: Set<LottoNumber>,
) {
    private var lottoNumberSet: Set<LottoNumber> = numberSet

    init {
        require(lottoNumberSet.size == LottoRange.SIZE.value) {
            "로또 번호는 ${LottoRange.SIZE.value}개여야 합니다."
        }
        lottoNumberSet = lottoNumberSet.sortedBy { it.value }.toMutableSet()
    }

    companion object {
        private const val SPLIT_WORD = ","
        private val REGEX = Regex("^[0-9,\\s]*$")

        fun create(numberString: String): Lotto {
            require(REGEX.matches(numberString)) {
                "로또 번호는 숫자와 쉼표(,)만 입력 가능합니다."
            }
            val numbers =
                numberString
                    .split(SPLIT_WORD)
                    .map { LottoNumber(it.trim().toInt()) }
                    .toSet()
            return Lotto(numbers)
        }

        fun create(numberList: List<Int>): Lotto {
            val numbers = numberList.map { LottoNumber(it) }.toSet()
            return Lotto(numbers)
        }

        fun create(lottoNumberSet: Set<LottoNumber>): Lotto = Lotto(numberSet = lottoNumberSet)

        fun create(): Lotto {
            val numbers = mutableSetOf<LottoNumber>()
            while (numbers.size < LottoRange.SIZE.value) {
                val number = LottoRange.createValidNumber()
                numbers.add(LottoNumber(number))
            }
            return Lotto(numbers)
        }

        fun bulkCreate(numberStringList: List<String>): List<Lotto> {
            val lottoList = numberStringList.map { create(numberString = it) }
            return lottoList
        }
    }

    fun matchCount(other: Lotto): Int = lottoNumberSet.intersect(other.lottoNumberSet).size

    fun matchBonus(bonus: LottoNumber): Boolean = lottoNumberSet.contains(bonus)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Lotto) return false

        if (lottoNumberSet != other.lottoNumberSet) return false

        return true
    }

    override fun hashCode(): Int = lottoNumberSet.hashCode()

    override fun toString(): String =
        lottoNumberSet.joinToString(
            separator = ", ",
            prefix = "[",
            postfix = "]",
        ) { it.toString() }
}
