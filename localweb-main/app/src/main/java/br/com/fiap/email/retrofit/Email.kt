package br.com.fiap.email.retrofit

data class Email(
    val recipient: String,
    val sender: String,
    val subject: String,
    val content: String,
    val dateTime: String,
    val isRead: Boolean,
    val id: Long
) {

}
