package freeapp.life.freeaiweb.entity

class Chat(
    model: String,
    id: Long,
    name:String,
    host:String,
    systemPrompt:String,
    temperature:Double,
    topP: Double,
    topK: Int,

) {

    var id = id

    var name = name
    var model = model
    var host = host
    var systemPrompt = systemPrompt
    var temperature = temperature
    var topP = topP
    var topK = topK

    var messages: MutableList<Message> = mutableListOf()
    
}
