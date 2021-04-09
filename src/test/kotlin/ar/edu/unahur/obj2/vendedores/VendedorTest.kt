package ar.edu.unahur.obj2.vendedores

import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue

class VendedorTest : DescribeSpec({
  val misiones = Provincia(1300000)
  val buenosAires = Provincia(10000000)
  val sanIgnacio = Ciudad(misiones)

  describe("Vendedor fijo") {
    val obera = Ciudad(misiones)
    val vendedorFijo = VendedorFijo(obera)
    val certificacion = Certificacion(esDeProducto = true , puntaje = 50)

    vendedorFijo.agregarCertificacion(certificacion)


    describe("puedeTrabajarEn") {
      it("su ciudad de origen") {
        vendedorFijo.puedeTrabajarEn(obera).shouldBeTrue()
      }
      it("otra ciudad") {
        vendedorFijo.puedeTrabajarEn(sanIgnacio).shouldBeFalse()
      }
    }

    describe("esFirme"){
      vendedorFijo.esFirme().shouldBeTrue()
    }

    describe("esInfluyente"){
      vendedorFijo.esInfluyente().shouldBeFalse()
    }
  }

  describe("Viajante") {
    val cordoba = Provincia(2000000)
    val villaDolores = Ciudad(cordoba)
    val viajante = Viajante(listOf(misiones))
    val certificacion = Certificacion(esDeProducto = true , puntaje = 20)

    viajante.agregarCertificacion(certificacion)

    describe("puedeTrabajarEn") {
      it("una ciudad que pertenece a una provincia habilitada") {
        viajante.puedeTrabajarEn(sanIgnacio).shouldBeTrue()
      }
      it("una ciudad que no pertenece a una provincia habilitada") {
        viajante.puedeTrabajarEn(villaDolores).shouldBeFalse()
      }
    }

    describe("esFirme"){
      viajante.esFirme().shouldBeFalse()
    }

    describe("esInfluyente"){
      viajante.esInfluyente().shouldBeFalse()
    }
  }

  describe("comercioCorresponal") {
    val hurlingham = Ciudad(buenosAires)
    val avellaneda = Ciudad(buenosAires)
    val caballito = Ciudad(buenosAires)
    val palomar = Ciudad(buenosAires)
    val haedo = Ciudad(buenosAires)
    val comercio = ComercioCorresponsal(listOf(hurlingham, avellaneda, caballito, palomar, haedo))
    val certificacion = Certificacion(esDeProducto = true, puntaje = 100)

    comercio.agregarCertificacion(certificacion)

    describe("puedeTrabajarEnCiudad"){
      comercio.puedeTrabajarEn(hurlingham).shouldBeTrue()
    }

    describe("esFirme") {
      comercio.esFirme().shouldBeTrue()
    }

    describe("esInfluyente") {
      comercio.esInfluyente().shouldBeTrue()
    }

  }

    describe("centroDistribucion") {
      val centro = CentroDistribucion()
      val hurlingham = Ciudad(buenosAires)
      val viajante = Viajante(listOf(misiones))
      val vendedorFijo = VendedorFijo(hurlingham)




      centro.agregarVendedor(viajante)
      centro.agregarVendedor(vendedorFijo)
      centro.agregarVendedor(vendedorFijo)

      describe("esVendedorEstrella"){
        centro.esVendedorEstrella() == viajante
      }

      describe("puedeCubrirCiudad"){
        centro.puedeCubrir(sanIgnacio).shouldBeTrue()
      }

      describe("esRobusto"){
        centro.esRobusto().shouldBeFalse()
      }

      describe("esta el vendedor 2 veces"){
        shouldThrowAny { centro.agregarVendedor(vendedorFijo) }
      }
    }

})
