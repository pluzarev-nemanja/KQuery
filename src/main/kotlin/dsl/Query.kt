package dsl

/**
 * The core class of the SQL-like query DSL.
 *
 * This class acts as the receiver for the DSL's lambda block, allowing for
 * a type-safe builder pattern. It holds the state of the query and chains
 * standard Kotlin sequence operations under the hood. The operations are
 * lazy and only executed when the final result is requested.
 *
 * @param T The type of data entity being queried (e.g., [User]).
 * @param source The source data collection, converted to a [Sequence] for efficient lazy evaluation.
 */
@SqlDslMarker
class Query<T>(private val source: Sequence<T>) {
    private var filteredSource: Sequence<T> = source
    private var projection: ((T) -> Any?)? = null

    /**
     * Defines the 'WHERE' clause of the query.
     *
     * This function takes a lambda that serves as a predicate for filtering the data.
     * The `filteredSource` sequence is updated with the result of the filtering operation.
     *
     * @param predicate A lambda expression that evaluates to a Boolean, used to filter the data.
     */
    fun where(predicate: T.() -> Boolean) {
        filteredSource = filteredSource.filter { it.predicate() }
    }

    /**
     * Defines the 'SELECT' clause of the query.
     *
     * This function takes a lambda that defines the projection (mapping) of the data.
     * The projection function is stored and applied lazily during query execution.
     *
     * @param R The return type of the projection.
     * @param projection A lambda expression that transforms each data item into a new value.
     */
    fun <R> select(projection: T.() -> R) {
        this.projection = { projection(it) }
    }

    /**
     * Executes the query and returns the final result as a [List].
     *
     * This is the terminal operation that triggers the execution of all
     * the chained sequence operations (`.filter`, `.map`, etc.).
     *
     * @return A [List] of the resulting objects, which can be of any type, including `null`.
     */
    fun execute(): List<Any?> {
        return if (projection != null) {
            filteredSource.map { projection!!.invoke(it) }.toList()
        } else {
            filteredSource.toList()
        }
    }
}

/**
 * The entry point for the query DSL.
 *
 * This function provides a clean, top-level way to start building a query.
 * It takes an [Iterable] data source and a lambda with a receiver, providing
 * access to the DSL's functions like [where] and [select].
 *
 * @param T The type of the data items in the source.
 * @param from The data source (e.g., a List, Set, or Array) to query.
 * @param init The DSL's lambda block where the query is defined.
 * @return A [List] containing the results of the executed query.
 */
fun <T> query(from: Iterable<T>, init: Query<T>.() -> Unit): List<Any?> {
    val query = Query(from.asSequence())
    query.init()
    return query.execute()
}