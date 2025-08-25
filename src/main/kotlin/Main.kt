import dsl.*

data class User(
    val id: Int,
    val name: String,
    val age: Int,
    val city: String?,
    val accountBalance: Double
)

fun main() {

    val users = listOf(
        User(1, "Alice", 25, "New York", 1500.0),
        User(2, "Bob", 30, "London", 2500.0),
        User(3, "Charlie", 35, "New York", 500.0),
        User(4, "David", 40, null, 100.0),
        User(5, "Eve", 28, "Paris", 3500.0),
        User(6, "Frank", 28, "London", 5500.0)
    )

    val usersOver30InNY = query(from = users) {
        where {
            (age gt 30) and (city?.eq("New York") == true)
        }
    }
    println("Users older than 30 in New York:")
    println(usersOver30InNY)
    val usersWithNameA = query(from = users) {
        where {
            (name like "%a%") and (accountBalance gt 0.0)
        }
    }
    println("\nUsers with 'a' in their name and a positive balance:")
    println(usersWithNameA)

    // Example 3: Find users with a balance between 1000 and 4000.
    val usersWithSpecificBalance = query(from = users) {
        where {
            (accountBalance ge 1000.0) and (accountBalance le 4000.0)
        }
    }
    println("\nUsers with balance between 1000 and 4000:")
    println(usersWithSpecificBalance)

    // Example 4: Find users whose city is either London or Paris.
    val usersInLondonOrParis = query(from = users) {
        where {
            city `in` listOf("London", "Paris")
        }
    }
    println("\nUsers in London or Paris:")
    println(usersInLondonOrParis)

    // Example 5: Find users with no city assigned.
    val usersWithoutCity = query(from = users) {
        where {
            city.isNull()
        }
    }
    println("\nUsers with no city assigned:")
    println(usersWithoutCity)

    // Example 6: Select only the names and cities of users older than 25.
    val namesAndCities = query(from = users) {
        where { age gt 25 }
        select { Pair(name, city) }
    }
    println("\nNames and cities of users older than 25:")
    println(namesAndCities)
}