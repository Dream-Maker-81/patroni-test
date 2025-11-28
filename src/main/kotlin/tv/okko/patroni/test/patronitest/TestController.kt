package tv.okko.patroni.test.patronitest

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController(
    val service: BService
) {


    @GetMapping("/test")
    fun test() = service.runTest()
}
