package tv.okko.patroni.test.patronitest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PatroniTestApplication

fun main(args: Array<String>) {
    runApplication<PatroniTestApplication>(*args)
}
