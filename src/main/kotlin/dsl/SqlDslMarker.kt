package dsl

/**
 * A DSL marker annotation for the SQL-like query builder.
 *
 * This annotation is crucial for creating a type-safe builder. It ensures that
 * functions intended for use only within the [query] DSL block cannot be called
 * from outside of it, preventing scope pollution and accidental API misuse.
 */
@DslMarker
annotation class SqlDslMarker