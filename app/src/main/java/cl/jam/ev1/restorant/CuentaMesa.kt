package cl.jam.ev1.restorant

class CuentaMesa(var mesa: Int) {

    private var _items: MutableList<ItemMesa> = mutableListOf()
    var aceptaPropina: Boolean = true


    fun agregarItem(itemMenu: ItemMenu, cantidad: Int) {
        val itemMesa = ItemMesa(itemMenu, cantidad)
        borrarItemPorNombre(itemMenu.nombre)
        val add = _items.add(itemMesa)
    }

    // Se agrego este metodo ya que de no eliminar el elemnto anterior
    // se realizaban mal los calulos, ya que sumaba platos que no existian.
    fun borrarItemPorNombre(nombre: String) {
        val item = _items.find { it.itemMenu.nombre == nombre }
        if (item != null) _items.remove(item)
    }

    fun agregarItem(itemMenu: ItemMenu) {
        val itemMesa = ItemMesa(itemMenu, 1)
        borrarItemPorNombre(itemMenu.nombre)
        val add = _items.add(itemMesa)
    }

    fun calcularTotalSinPropina(): Int {
        var total = 0
        _items.forEach { itemMesa ->
            total += itemMesa.calcularSubtotal()
        }
        return total
    }

    fun calcularPropina(): Int {
        return if (aceptaPropina) {
            val total = calcularTotalSinPropina() * 0.1
            total.toInt()
        } else 0

    }

    fun calcularTotalConPropina(): Int {
        return calcularPropina() + calcularTotalSinPropina()
    }


}