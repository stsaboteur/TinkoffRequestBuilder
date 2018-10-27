package main

import java.math.BigInteger
import java.nio.charset.StandardCharsets
import java.security.MessageDigest


fun cryptoHash(text: String): String {
    val md = MessageDigest.getInstance("SHA-256")

    // Change this to UTF-16 if needed
    md.update(text.toByteArray(StandardCharsets.UTF_8))
    val digest = md.digest()

    return String.format("%064x", BigInteger(1, digest))
}

