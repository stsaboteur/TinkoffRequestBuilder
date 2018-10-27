package main

val mainParameters = listOf("merchantId", "orderId", "amount", "endDate", "shortDesc", "fullDesc", "returnURL", "shopName", "shopId")
const val PASSWORD = "Om4Nom2Nom7Macaron"

fun buildRequest(params: Map<String, String>, type: Request): String {

    var xmlRequest = "<tinkoff:invoicing xmlns:tinkoff=\"http://tinkoff.ru/invoicing/request\">\n<tinkoff:$type>"

    val extFields = mutableMapOf<String, String>()

    for (param in params) {
        if (!mainParameters.contains(param.key) && type == Request.CreateInvoiceRequest) {
            extFields.put(param.key, param.value)
        } else xmlRequest = xmlRequest.plus("\n<tinkoff:${param.key}>${param.value}</tinkoff:${param.key}>")
    }

    if (!extFields.isEmpty()) {
        xmlRequest = xmlRequest.plus("\n<tinkoff:extFields>")
        for (extField in extFields) {
            xmlRequest = xmlRequest.plus("\n<tinkoff:extField key=\"${extField.key}\">${extField.value}</tinkoff:extField>")
        }
        xmlRequest = xmlRequest.plus("\n</tinkoff:extFields>")
    }

    val sign = cryptoHash(params.plus(mapOf("password" to PASSWORD)).toSortedMap().values.joinToString(separator = ""))

    xmlRequest = xmlRequest.plus("\n</tinkoff:$type>")
    xmlRequest = xmlRequest.plus("\n<tinkoff:sign>$sign</tinkoff:sign>")
    xmlRequest = xmlRequest.plus("\n</tinkoff:invoicing>\n")

    return xmlRequest
}

enum class Request {
    CreateInvoiceRequest,
    ConfirmRequest,
    CancelRequest,
    GetStatusRequest
}