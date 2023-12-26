package cl.jam.ev1.restorant

class ItemMesa(var itemMenu: ItemMenu, var cantidad: Int) {

    fun calcularSubtotal(): Int {
        return cantidad * itemMenu.precio
    }

}