package test

import main.Request.*
import main.buildRequest
import org.junit.jupiter.api.Test

class RequestTest {

    @Test
    fun createInvoice() {
        val params = mapOf(
                "merchantId" to "yamoney",
                "orderId" to "100",
                "amount" to "34.00",
                "endDate" to "2018-10-23T18:28:00+03:00",
                "shortDesc" to "Тестовый платёж",
                "fullDesc" to "Тестовый платёж из Яндекс.Денег",
                "returnURL" to "https://front-main.kassa1.ymdev.yandex.ru",
                "email" to "a@test.ru",
                "shopName" to "yamoney",
                "shopId" to "61749"
        )
        println(buildRequest(params, CreateInvoiceRequest))
    }

    @Test
    fun confirm() {
        val params = mapOf(
                "merchantId" to "yamoney",
                "invoiceId" to "100",
                "amount" to "34.00",
                "shopId" to "61749"
        )
        println(buildRequest(params, ConfirmRequest))
    }

    @Test
    fun cancel() {
        val params = mapOf(
                "merchantId" to "yamoney",
                "invoiceId" to "100",
                "amount" to "34.00",
                "shopId" to "61749"
        )
        println(buildRequest(params, CancelRequest))
    }

    @Test
    fun getStatus() {
        val params = mapOf(
                "merchantId" to "yamoney",
                "invoiceId" to "100",
                "shopId" to "61749"
        )
        println(buildRequest(params, GetStatusRequest))
    }
}