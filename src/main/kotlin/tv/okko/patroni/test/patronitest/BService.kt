package tv.okko.patroni.test.patronitest

import jakarta.transaction.Transactional
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Service
import java.security.SecureRandom
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CompletionService
import java.util.concurrent.ExecutorCompletionService
import java.util.concurrent.Executors
import java.util.concurrent.Future
import java.util.concurrent.atomic.AtomicInteger
import java.util.stream.Collectors
import java.util.stream.IntStream

@Service
class BService (
    private val template: JdbcTemplate
) {
    private val random= SecureRandom()
    private val executor = Executors.newFixedThreadPool(10)
    //val completionService = ExecutorCompletionService<Runnable>(executor)


    @Transactional
    fun runTest(): String{
        val list=IntStream.range(0, random.nextInt(10)).mapToObj { submitTest() }
            .map { CompletableFuture.supplyAsync { it.get() } }
            .collect(Collectors.toList())

        CompletableFuture.allOf(*list.toTypedArray() ).join();
        return "Ok"
    }

    private fun submitTest()= executor.submit { this.singleTest() }


    private fun singleTest(): String{
        val k=random.nextInt()
        template.execute("SELECT 1")
        template.execute("INSERT INTO tablo VALUES ($k);")
        return k.toString()
    }
}
