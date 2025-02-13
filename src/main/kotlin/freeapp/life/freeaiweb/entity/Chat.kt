package freeapp.life.freeaiweb.entity

class Chat(
    id: Long,
    model: String,
    name:String,
    host:String,
    temperature:Double,
    topP: Double,
    topK: Int,
) {

    var id = id

    var name = name
    var model = model
    var host = host
    var temperature = temperature
    var topP = topP
    var topK = topK

    var messages: MutableList<Message> = mutableListOf()

}
