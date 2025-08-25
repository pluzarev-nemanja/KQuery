# 🚀 Kotlin SQL-like DSL for In-Memory Data

This project demonstrates how to build a powerful, type-safe internal Domain-Specific Language (DSL) in Kotlin for querying in-memory collections. The DSL provides a fluent, declarative syntax that mimics SQL, while leveraging Kotlin's native collection operations for efficient and safe data manipulation.

This approach combines the readability of SQL with the compile-time safety and performance of standard Kotlin code.

---

### ✨ Features

* **SQL-like Syntax:** Write queries using familiar keywords like `where`, `select`, and operators like `gt`, `like`, and `in`.
* **Type-Safe:** The compiler enforces type safety at every step, preventing common errors that occur with string-based queries.
* **Extensible:** Easily add new operators, clauses (`groupBy`, `orderBy`), and functionality to the DSL.
* **Lazy Evaluation:** The DSL uses Kotlin's `Sequence` for lazy evaluation, ensuring that operations are only performed when the final result is requested.
* **No External Dependencies:** The entire DSL is built using the standard Kotlin library.

---

### 💻 Usage

The DSL is designed to be highly intuitive. Here's a quick example of how you can query a list of `User` objects.

#### **Example: Find users with a name containing 'o' and an age greater than 25.**

```kotlin
import com.example.dsl.*
import com.example.model.User

fun main() {
    val users = listOf(
        User(1, "John", 28, "New York", 1500.0),
        User(2, "Bob", 30, "London", 2500.0),
        User(3, "Monica", 35, "New York", 500.0)
    )

    val result = query(from = users) {
        where { (name like "%o%") and (age gt 25) }
    }

    println(result)
    // Output: [User(id=1, name=John, age=28, ...), User(id=3, name=Monica, age=35, ...)]
}
