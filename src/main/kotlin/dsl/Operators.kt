package dsl

/**
 * Contains infix and logical operators to enable a fluent, SQL-like syntax
 * for querying in-memory data collections.
 *
 * These extension functions are designed to make the `where` clause highly
 * readable and expressive, providing a powerful and type-safe alternative
 * to string-based queries.
 */

// --- Comparison operators ---

/**
 * An infix function for the "greater than" (>) comparison.
 *
 * This allows for a clean, natural syntax like `user.age gt 25`.
 * It is applicable to any comparable type, such as [Int], [Double], or [String].
 *
 * @param other The value to compare against.
 * @return `true` if this value is greater than the other, otherwise `false`.
 */
infix fun <T : Comparable<T>> T.gt(other: T) = this > other

/**
 * An infix function for the "less than" (<) comparison.
 *
 * This allows for a clean, natural syntax like `user.age lt 50`.
 *
 * @param other The value to compare against.
 * @return `true` if this value is less than the other, otherwise `false`.
 */
infix fun <T : Comparable<T>> T.lt(other: T) = this < other

/**
 * An infix function for the "equals" (==) comparison.
 *
 * This allows for a clean, natural syntax like `user.name eq "Alice"`.
 *
 * @param other The value to compare against.
 * @return `true` if the values are equal, otherwise `false`.
 */
infix fun <T : Comparable<T>> T.eq(other: T) = this == other

/**
 * An infix function for the "not equals" (!=) comparison.
 *
 * This allows for a clean, natural syntax like `user.name ne "Bob"`.
 *
 * @param other The value to compare against.
 * @return `true` if the values are not equal, otherwise `false`.
 */
infix fun <T : Comparable<T>> T.ne(other: T) = this != other

/**
 * An infix function for the "greater than or equal to" (>=) comparison.
 *
 * This allows for a clean, natural syntax like `user.age ge 18`.
 *
 * @param other The value to compare against.
 * @return `true` if this value is greater than or equal to the other, otherwise `false`.
 */
infix fun <T : Comparable<T>> T.ge(other: T) = this >= other

/**
 * An infix function for the "less than or equal to" (<=) comparison.
 *
 * This allows for a clean, natural syntax like `user.age le 65`.
 *
 * @param other The value to compare against.
 * @return `true` if this value is less than or equal to the other, otherwise `false`.
 */
infix fun <T : Comparable<T>> T.le(other: T) = this <= other

// --- Logical operators ---

/**
 * An infix function for the logical AND operation.
 *
 * This is used to combine multiple conditions within a `where` clause,
 * for example: `(age ge 18) and (age le 65)`.
 *
 * @param other The boolean value to combine with a logical AND.
 * @return The result of the logical AND operation.
 */
infix fun Boolean.and(other: Boolean) = this && other

/**
 * An infix function for the logical OR operation.
 *
 * This is used to combine multiple conditions within a `where` clause,
 * for example: `(name eq "Alice") or (name eq "Bob")`.
 *
 * @param other The boolean value to combine with a logical OR.
 * @return The result of the logical OR operation.
 */
infix fun Boolean.or(other: Boolean) = this || other

/**
 * A standard extension function for the logical NOT operation.
 *
 * This allows for syntax like `(age eq 30).not()`.
 *
 * @return The logical inverse of this boolean value.
 */
fun Boolean.not() = !this

// --- Nullable checks ---

/**
 * An extension function to check if a nullable value is null.
 *
 * This provides a more readable alternative to `== null`, such as
 * `user.address.isNull()`.
 *
 * @return `true` if the value is null, otherwise `false`.
 */
fun <T> T?.isNull() = this == null

/**
 * An extension function to check if a nullable value is not null.
 *
 * This provides a more readable alternative to `!= null`, such as
 * `user.address.isNotNull()`.
 *
 * @return `true` if the value is not null, otherwise `false`.
 */
fun <T> T?.isNotNull() = this != null

// --- Set membership ---

/**
 * An infix function to check if a value is contained within a collection.
 *
 * This mimics the SQL `IN` operator, allowing for syntax like
 * `user.department in listOf("IT", "HR")`.
 *
 * @param values The collection to check for containment.
 * @return `true` if the value is in the collection, otherwise `false`.
 */
infix fun <T> T.`in`(values: Collection<T>) = values.contains(this)

/**
 * An infix function to check if a value is not contained within a collection.
 *
 * This mimics the SQL `NOT IN` operator, allowing for syntax like
 * `user.role notIn listOf("Admin")`.
 *
 * @param values The collection to check against.
 * @return `true` if the value is not in the collection, otherwise `false`.
 */
infix fun <T> T.notIn(values: Collection<T>) = !values.contains(this)

// --- String-specific operators ---

/**
 * An infix function for SQL-like pattern matching.
 *
 * This simulates the SQL `LIKE` operator using a simple regular expression.
 * The `%` wildcard character is treated as a regular expression `.*`,
 * matching zero or more characters. For example, `user.name like "A%e"`
 * would match "Alice" and "Anne".
 *
 * @param pattern The pattern to match against, where `%` is the wildcard.
 * @return `true` if the string matches the pattern, otherwise `false`.
 */
infix fun String.like(pattern: String) = Regex(pattern.replace("%", ".*")).matches(this)

/**
 * An infix function for SQL-like "not like" pattern matching.
 *
 * This is the logical inverse of the `like` operator.
 *
 * @param pattern The pattern to check against, where `%` is the wildcard.
 * @return `true` if the string does not match the pattern, otherwise `false`.
 */
infix fun String.notLike(pattern: String) = !Regex(pattern.replace("%", ".*")).matches(this)

// --- Arithmetic operators for Int ---

/**
 * An infix function for addition.
 *
 * This allows for a more descriptive syntax in calculations, such as
 * `value add 10`.
 *
 * @param other The integer to add.
 * @return The result of the addition.
 */
infix fun Int.add(other: Int) = this + other
/**
 * An infix function for subtraction.
 *
 * @param other The integer to subtract.
 * @return The result of the subtraction.
 */
infix fun Int.sub(other: Int) = this - other
/**
 * An infix function for multiplication.
 *
 * @param other The integer to multiply by.
 * @return The result of the multiplication.
 */
infix fun Int.mul(other: Int) = this * other
/**
 * An infix function for division.
 *
 * @param other The integer to divide by.
 * @return The result of the division.
 */
infix fun Int.div(other: Int) = this / other

// --- Arithmetic operators for Double ---

/**
 * An infix function for addition on [Double] values.
 *
 * @param other The double to add.
 * @return The result of the addition.
 */
infix fun Double.add(other: Double) = this + other
/**
 * An infix function for subtraction on [Double] values.
 *
 * @param other The double to subtract.
 * @return The result of the subtraction.
 */
infix fun Double.sub(other: Double) = this - other
/**
 * An infix function for multiplication on [Double] values.
 *
 * @param other The double to multiply by.
 * @return The result of the multiplication.
 */
infix fun Double.mul(other: Double) = this * other
/**
 * An infix function for division on [Double] values.
 *
 * @param other The double to divide by.
 * @return The result of the division.
 */
infix fun Double.div(other: Double) = this / other