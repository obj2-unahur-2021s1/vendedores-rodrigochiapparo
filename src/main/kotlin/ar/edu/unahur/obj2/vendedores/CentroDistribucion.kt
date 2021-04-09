package ar.edu.unahur.obj2.vendedores

class CentroDistribucion() {

  val vendedores = mutableListOf<Vendedor>()

  fun agregarVendedor(vendedor:Vendedor){
     check(vendedores.contains(vendedor)){"El vendedor ya esta registrado"}
    vendedores.add((vendedor))
    }

  fun esVendedorEstrella() = vendedores.maxBy{v -> v.puntajeCertificaciones()}

  fun puedeCubrir(ciudad : Ciudad) = vendedores.any { it.puedeTrabajarEn(ciudad)}

  fun esRobusto() = vendedoresFirmes().size > 3

  fun vendedoresFirmes() = vendedores.filter { v -> v.esFirme() }

  fun vendedoresGenericos() = vendedores.filter { v -> v.esGenerico()}
}