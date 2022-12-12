import kotlin.math.max

data class Item(val weight: Int, val value: Int)

fun knapsack(maximumWeight: Int, items: List<Item>): Int {
    data class MemoKey(val index: Int, val weight: Int)
    fun f(weight: Int, index: Int, memo: MutableMap<MemoKey, Int>): Int {
        if (index == items.size) {
            return 0
        }
        val memoKey = MemoKey(index, weight)
        val m = memo[memoKey]
        if (m != null) {
            return m
        }
        val take = if (weight - items[index].weight >= 0) {
            f(weight - items[index].weight, index + 1, memo) + items[index].value
        } else {
            0
        }
        val skip = f(weight, index + 1,  memo)
        val max =  max(take, skip)
        memo[memoKey] = max
        return max
    }

    return f(maximumWeight, 0, mutableMapOf())
}
