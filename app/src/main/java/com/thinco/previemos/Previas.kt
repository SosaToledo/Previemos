package com.thinco.previemos

class Previas (
        var id: Long,
        var publico: Boolean,
        var nombre:String,
        var ubicacion:String,
        var container:List<String>)
    {
        override fun toString(): String {
            return "\n ID: $id \n Publico:$publico  " +
                    "\n Nombre:$nombre  \n Ubicacion:$ubicacion  " +
                    "\n  Container:${container.toString()}"
        }
    }
