package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


import org.junit.jupiter.api.Test;

import exceptions.NegativeValueException;
import exceptions.NoValueException;

public class BiencoTest {
	
	private Bienco bienco;
	
	public void setupScenary1() {
		bienco = new Bienco(1);
		bienco.getBuildings().add(new Building("Calle 4C # 5-29", "Porvenir", Zone.SUR, TypeOfBuilding.CASA, 580000, false, "Casa de 1 planta que contiene 2 habitaciones, sala, comedor, 1 banio y un pequenio patio trasero."));
		bienco.getBuildings().add(new Building("Calle 5B # 3-10", "Porvenir", Zone.SUR, TypeOfBuilding.LOCAL, 620000, false, "Local de 500 metros cuadrados con segundo piso que contiene un banio"));
		bienco.getBuildings().add(new Building("Calle 7A # 4-15", "Vipasa", Zone.NORTE, TypeOfBuilding.CASA, 205000000, true, "Casa de 3 plantas que contiene 4 habitaciones, sala, terraza, comedor, 2 banios, garaje y patio trasero."));
		bienco.getBuildings().add(new Building("Calle 3A # 5-29", "Porvenir", Zone.SUR, TypeOfBuilding.APARTAESTUDIO, 1000000, false, "Apartaestudio amoblado ubicado en segundo piso. Contiene 2 habitaciones, sala, cocina, 1 banio."));
		bienco.getBuildings().add(new Building("Calle 9B # 8-19", "Vipasa", Zone.NORTE, TypeOfBuilding.CASA, 350000000, true, "Casa de 2 plantas que contiene 3 habitaciones, sala, comedor, 2 banios, garaje pequeÃ±o y un patio trasero."));
		bienco.getBuildings().add(new Building("Calle 11B # 2-16", "Vipasa", Zone.NORTE, TypeOfBuilding.APARTAMENTO, 275000000, false, "Apartamento de 300 metros cuadrados que tiene 2 habitaciones, banio,  comedor, sitio para lavado y un pequenio balcon."));
		bienco.getBuildings().add(new Building("Calle 14C # 6-27", "El Prado", Zone.ESTE, TypeOfBuilding.CASA, 450000000, true, "Casa de 350 metros cuadrados que tiene 4 habitaciones y espacio para estudio, 2 banios,  sala, patio y balcon."));
		bienco.getBuildings().add(new Building("Calle 16C # 9-27", "Pance", Zone.SUR, TypeOfBuilding.FINCA, 800000000, true, "Finca  de 1000 metros cuadrados que tiene 6 habitaciones, 3 banios,  sala, zona verde, piscina."));
		bienco.getBuildings().add(new Building("Calle 77C # 7-43", "Los Cristales", Zone.OESTE, TypeOfBuilding.APARTAMENTO, 360000000, true, "Apartamento de 250 metros cuadrados que cuenta con 3 habitaciones, 2 banios,  sala. Ubicado en primer piso."));
	}
	
	public void setupScenary2() {
		bienco = new Bienco(1);
	}

	@Test
	public void testAddBuilding1() {
		setupScenary2();
		String address = "Calle 15 # 4-89";
		String neighborhood = "Libertadores";
		String zone = "Norte";
		String type = "Apartaestudio";
		String price = "800000";
		boolean forSale = false;
		String observations = "Apartaestudio ubicado en el 5 piso de la torre A del conjunto residencial Verano, cuenta con 1 habitación, baño, balcon y sala de estar.";
		try {
			boolean added = bienco.addBuilding(address, neighborhood, zone, type, price, forSale, observations);
			assertTrue(added);
			assertEquals(bienco.getBuildings().size(), 1);
			assertEquals(bienco.getBuildings().get(0).getAddress(), "Calle 15 # 4-89");
		} catch (NoValueException e) {
			fail("NoValueException not expected");
		} catch (NegativeValueException e) {
			fail("NegativeValueException not expected");
		}
	}
	
	@Test
	public void testAddBuilding2() {
		setupScenary1();
		String address = "Calle 15 # 4-89";
		String neighborhood = "Libertadores";
		String zone = "Norte";
		String type = "Apartaestudio";
		String price = "800000";
		boolean forSale = false;
		String observations = "Apartaestudio ubicado en el 5 piso de la torre A del conjunto residencial Verano, cuenta con 1 habitación, baño, balcon y sala de estar.";
		try {
			boolean added = bienco.addBuilding(address, neighborhood, zone, type, price, forSale, observations);
			assertTrue(added);
			assertEquals(bienco.getBuildings().size(), 11);
			assertEquals(bienco.getBuildings().get(0).getAddress(), "Calle 15 # 4-89");
		} catch (NoValueException e) {
			fail("NoValueException not expected");
		} catch (NegativeValueException e) {
			fail("NegativeValueException not expected");
		}
	}
	
	@Test
	public void testAddBuilding3() {
		setupScenary1();
		String address = "Calle 15 # 4-89";
		String neighborhood = "Libertadores";
		String zone = "Norte";
		String type = "Apartaestudio";
		String price = "-800000";
		boolean forSale = false;
		String observations = "Apartaestudio ubicado en el 5 piso de la torre A del conjunto residencial Verano, cuenta con 1 habitación, baño, balcon y sala de estar.";
		boolean added = true;
		try {
			added = bienco.addBuilding(address, neighborhood, zone, type, price, forSale, observations);
		} catch (NoValueException e) {
			fail("NoValueException not expected");
		} catch (NegativeValueException e) {
			assertFalse(added);
			assertEquals(bienco.getBuildings().size(), 10);
		}
	}
	
	@Test
	public void testAddBuilding4() {
		setupScenary1();
		String address = "Calle 15 # 4-89";
		String neighborhood = "Libertadores";
		String zone = "Norte";
		String type = "Apartaestudio";
		String price = "0";
		boolean forSale = false;
		String observations = "Apartaestudio ubicado en el 5 piso de la torre A del conjunto residencial Verano, cuenta con 1 habitación, baño, balcon y sala de estar.";
		boolean added = true;
		try {
			added = bienco.addBuilding(address, neighborhood, zone, type, price, forSale, observations);
		} catch (NoValueException e) {
			assertFalse(added);
			assertEquals(bienco.getBuildings().size(), 10);
		} catch (NegativeValueException e) {
			fail("NegativeValueException not expected");
		}
	}
	
	@Test
	public void testAddBuilding5() {
		setupScenary1();
		String address = "Calle 9B # 8-19";
		String neighborhood = "Libertadores";
		String zone = "Norte";
		String type = "Apartaestudio";
		String price = "800000";
		boolean forSale = false;
		String observations = "Apartaestudio ubicado en el 5 piso de la torre A del conjunto residencial Verano, cuenta con 1 habitación, baño, balcon y sala de estar.";
		try {
			boolean added = bienco.addBuilding(address, neighborhood, zone, type, price, forSale, observations);
			assertFalse(added);
			assertEquals(bienco.getBuildings().size(), 10);
		} catch (NoValueException e) {
			fail("NoValueException not expected");
		} catch (NegativeValueException e) {
			fail("NegativeValueException not expected");
		}
	}
	
	@Test
	public void testDeleteBuilding1() {
		setupScenary1();
		bienco.deleteBuilding(bienco.getBuildings().get(6));
		assertEquals(bienco.getBuildings().size(), 9);
		assertEquals(bienco.searchBuilding("Calle 58A # 1-15"), null);
	}
	
	@Test
	public void testDeleteBuilding2() {
		setupScenary1();
		bienco.deleteBuilding(bienco.getBuildings().get(7));
		assertEquals(bienco.getBuildings().size(), 9);
		assertEquals(bienco.searchBuilding("Calle 14C # 6-27"), null);
	}
	
	@Test
	public void testUpdateBuilding1() {
		setupScenary2();
		String newAddress = "Calle 4C # 5-29";
		String newNeighborhood = "Porvenir";
		String newZone = "Sur";
		String newType = "Casa";
		String newPrice = "550000";
		boolean forSale = false;
		String newObservations = "Casa de 1 planta que contiene 2 habitaciones, sala, comedor, 1 baño y un pequeño patio trasero.";
		try {
			boolean updated = bienco.updateBuilding(bienco.getBuildings().get(0), newAddress, newNeighborhood, newZone, newType, newPrice, forSale, newObservations);
			assertTrue(updated);
			assertEquals(bienco.getBuildings().get(0).getAddress(), newAddress);
			assertEquals(bienco.getBuildings().get(0).getNeighborhood(), newNeighborhood);
			assertEquals(bienco.getBuildings().get(0).getZone(), Zone.SUR);
			assertEquals(bienco.getBuildings().get(0).getType(), TypeOfBuilding.CASA);
			assertEquals(bienco.getBuildings().get(0).getPrice(), 550000.0);
			assertFalse(bienco.getBuildings().get(0).isForSale());
			assertEquals(bienco.getBuildings().get(0).getObservations(), newObservations);
		} catch (NoValueException e) {
			fail("NoValueException not expected");
		} catch (NegativeValueException e) {
			fail("NegativeValueException not expected");
		}
	}
	
	@Test
	public void testUpdateBuilding2() {
		setupScenary2();
		String newAddress = "Calle 4C # 5-29";
		String newNeighborhood = "Porvenir";
		String newZone = "Sur";
		String newType = "Casa";
		String newPrice = "-500000";
		boolean forSale = false;
		String newObservations = "Casa de 1 planta que contiene 2 habitaciones, sala, comedor, 1 baño y un pequeño patio trasero.";
		boolean updated = true;
		try {
			updated = bienco.updateBuilding(bienco.getBuildings().get(0), newAddress, newNeighborhood, newZone, newType, newPrice, forSale, newObservations);
		} catch (NoValueException e) {
			fail("NoValueException not expected");
		} catch (NegativeValueException e) {
			assertFalse(updated);
			assertEquals(bienco.getBuildings().get(0).getAddress(), newAddress);
			assertEquals(bienco.getBuildings().get(0).getNeighborhood(), newNeighborhood);
			assertEquals(bienco.getBuildings().get(0).getZone(), Zone.SUR);
			assertEquals(bienco.getBuildings().get(0).getType(), TypeOfBuilding.CASA);
			assertEquals(bienco.getBuildings().get(0).getPrice(), 580000.0);
			assertFalse(bienco.getBuildings().get(0).isForSale());
			assertEquals(bienco.getBuildings().get(0).getObservations(), newObservations);
		}
	}
	
	@Test
	public void testUpdateBuilding3() {
		setupScenary2();
		String newAddress = "Calle 4C # 5-29";
		String newNeighborhood = "Porvenir";
		String newZone = "Sur";
		String newType = "Casa";
		String newPrice = "0";
		boolean forSale = false;
		String newObservations = "Casa de 1 planta que contiene 2 habitaciones, sala, comedor, 1 baño y un pequeño patio trasero.";
		boolean updated = true;
		try {
			updated = bienco.updateBuilding(bienco.getBuildings().get(0), newAddress, newNeighborhood, newZone, newType, newPrice, forSale, newObservations);
		} catch (NoValueException e) {
			assertFalse(updated);
			assertEquals(bienco.getBuildings().get(0).getAddress(), newAddress);
			assertEquals(bienco.getBuildings().get(0).getNeighborhood(), newNeighborhood);
			assertEquals(bienco.getBuildings().get(0).getZone(), Zone.SUR);
			assertEquals(bienco.getBuildings().get(0).getType(), TypeOfBuilding.CASA);
			assertEquals(bienco.getBuildings().get(0).getPrice(), 580000.0);
			assertFalse(bienco.getBuildings().get(0).isForSale());
			assertEquals(bienco.getBuildings().get(0).getObservations(), newObservations);
		} catch (NegativeValueException e) {
			fail("NegativeValueException not expected");
		}
	}
	
	@Test
	public void testUpdateBuilding4() {
		setupScenary2();
		String newAddress = "Calle 5B # 3-10";
		String newNeighborhood = "Porvenir";
		String newZone = "Sur";
		String newType = "Casa";
		String newPrice = "580000";
		boolean forSale = false;
		String newObservations = "Casa de 1 planta que contiene 2 habitaciones, sala, comedor, 1 baño y un pequeño patio trasero.";
		try {
			boolean updated = bienco.updateBuilding(bienco.getBuildings().get(0), newAddress, newNeighborhood, newZone, newType, newPrice, forSale, newObservations);
			assertFalse(updated);
			assertEquals(bienco.getBuildings().get(0).getAddress(), "Calle 4C # 5-29");
			assertEquals(bienco.getBuildings().get(0).getNeighborhood(), newNeighborhood);
			assertEquals(bienco.getBuildings().get(0).getZone(), Zone.SUR);
			assertEquals(bienco.getBuildings().get(0).getType(), TypeOfBuilding.CASA);
			assertEquals(bienco.getBuildings().get(0).getPrice(), newPrice);
			assertFalse(bienco.getBuildings().get(0).isForSale());
			assertEquals(bienco.getBuildings().get(0).getObservations(), newObservations);
		} catch (NoValueException e) {
			fail("NoValueException not expected");
		} catch (NegativeValueException e) {
			fail("NegativeValueException not expected");
		}
	}
	
	@Test
	public void testUpdateBuilding5() {
		setupScenary2();
		String newAddress = "Calle 4C # 3-19";
		String newNeighborhood = "Porvenir";
		String newZone = "Sur";
		String newType = "Casa";
		String newPrice = "580000";
		boolean forSale = false;
		String newObservations = "Casa de 1 planta que contiene 2 habitaciones, sala, comedor, 1 baño y un pequeño patio trasero.";
		try {
			boolean updated = bienco.updateBuilding(bienco.getBuildings().get(0), newAddress, newNeighborhood, newZone, newType, newPrice, forSale, newObservations);
			assertFalse(updated);
			assertEquals(bienco.getBuildings().get(0).getAddress(), newAddress);
			assertEquals(bienco.getBuildings().get(0).getNeighborhood(), newNeighborhood);
			assertEquals(bienco.getBuildings().get(0).getZone(), Zone.SUR);
			assertEquals(bienco.getBuildings().get(0).getType(), TypeOfBuilding.CASA);
			assertEquals(bienco.getBuildings().get(0).getPrice(), newPrice);
			assertFalse(bienco.getBuildings().get(0).isForSale());
			assertEquals(bienco.getBuildings().get(0).getObservations(), newObservations);
		} catch (NoValueException e) {
			fail("NoValueException not expected");
		} catch (NegativeValueException e) {
			fail("NegativeValueException not expected");
		}
	}
	
	@Test
	public void testUpdateBuilding6() {
		setupScenary2();
		String newAddress = "Calle 4C # 5-29";
		String newNeighborhood = "Popular";
		String newZone = "Sur";
		String newType = "Casa";
		String newPrice = "580000";
		boolean forSale = false;
		String newObservations = "Casa de 1 planta que contiene 2 habitaciones, sala, comedor, 1 baño y un pequeño patio trasero.";
		try {
			boolean updated = bienco.updateBuilding(bienco.getBuildings().get(0), newAddress, newNeighborhood, newZone, newType, newPrice, forSale, newObservations);
			assertFalse(updated);
			assertEquals(bienco.getBuildings().get(0).getAddress(), newAddress);
			assertEquals(bienco.getBuildings().get(0).getNeighborhood(), newNeighborhood);
			assertEquals(bienco.getBuildings().get(0).getZone(), Zone.SUR);
			assertEquals(bienco.getBuildings().get(0).getType(), TypeOfBuilding.CASA);
			assertEquals(bienco.getBuildings().get(0).getPrice(), newPrice);
			assertFalse(bienco.getBuildings().get(0).isForSale());
			assertEquals(bienco.getBuildings().get(0).getObservations(), newObservations);
		} catch (NoValueException e) {
			fail("NoValueException not expected");
		} catch (NegativeValueException e) {
			fail("NegativeValueException not expected");
		}
	}
	
	@Test
	public void testUpdateBuilding7() {
		setupScenary2();
		String newAddress = "Calle 4C # 5-29";
		String newNeighborhood = "Porvenir";
		String newZone = "Oeste";
		String newType = "Casa";
		String newPrice = "580000";
		boolean forSale = false;
		String newObservations = "Casa de 1 planta que contiene 2 habitaciones, sala, comedor, 1 baño y un pequeño patio trasero.";
		try {
			boolean updated = bienco.updateBuilding(bienco.getBuildings().get(0), newAddress, newNeighborhood, newZone, newType, newPrice, forSale, newObservations);
			assertFalse(updated);
			assertEquals(bienco.getBuildings().get(0).getAddress(), newAddress);
			assertEquals(bienco.getBuildings().get(0).getNeighborhood(), newNeighborhood);
			assertEquals(bienco.getBuildings().get(0).getZone(), Zone.OESTE);
			assertEquals(bienco.getBuildings().get(0).getType(), TypeOfBuilding.CASA);
			assertEquals(bienco.getBuildings().get(0).getPrice(), newPrice);
			assertFalse(bienco.getBuildings().get(0).isForSale());
			assertEquals(bienco.getBuildings().get(0).getObservations(), newObservations);
		} catch (NoValueException e) {
			fail("NoValueException not expected");
		} catch (NegativeValueException e) {
			fail("NegativeValueException not expected");
		}
	}
	
	@Test
	public void testUpdateBuilding8() {
		setupScenary2();
		String newAddress = "Calle 4C # 5-29";
		String newNeighborhood = "Porvenir";
		String newZone = "Sur";
		String newType = "Apartamento";
		String newPrice = "580000";
		boolean forSale = false;
		String newObservations = "Casa de 1 planta que contiene 2 habitaciones, sala, comedor, 1 baño y un pequeño patio trasero.";
		try {
			boolean updated = bienco.updateBuilding(bienco.getBuildings().get(0), newAddress, newNeighborhood, newZone, newType, newPrice, forSale, newObservations);
			assertFalse(updated);
			assertEquals(bienco.getBuildings().get(0).getAddress(), newAddress);
			assertEquals(bienco.getBuildings().get(0).getNeighborhood(), newNeighborhood);
			assertEquals(bienco.getBuildings().get(0).getZone(), Zone.SUR);
			assertEquals(bienco.getBuildings().get(0).getType(), TypeOfBuilding.CASA);
			assertEquals(bienco.getBuildings().get(0).getPrice(), newPrice);
			assertFalse(bienco.getBuildings().get(0).isForSale());
			assertEquals(bienco.getBuildings().get(0).getObservations(), newObservations);
		} catch (NoValueException e) {
			fail("NoValueException not expected");
		} catch (NegativeValueException e) {
			fail("NegativeValueException not expected");
		}
	}
	
	@Test
	public void testUpdateBuilding9() {
		setupScenary2();
		String newAddress = "Calle 4C # 5-29";
		String newNeighborhood = "Porvenir";
		String newZone = "Sur";
		String newType = "Casa";
		String newPrice = "580000";
		boolean forSale = true;
		String newObservations = "Casa de 1 planta que contiene 2 habitaciones, sala, comedor, 1 baño y un pequeño patio trasero.";
		try {
			boolean updated = bienco.updateBuilding(bienco.getBuildings().get(0), newAddress, newNeighborhood, newZone, newType, newPrice, forSale, newObservations);
			assertFalse(updated);
			assertEquals(bienco.getBuildings().get(0).getAddress(), newAddress);
			assertEquals(bienco.getBuildings().get(0).getNeighborhood(), newNeighborhood);
			assertEquals(bienco.getBuildings().get(0).getZone(), Zone.SUR);
			assertEquals(bienco.getBuildings().get(0).getType(), TypeOfBuilding.CASA);
			assertEquals(bienco.getBuildings().get(0).getPrice(), newPrice);
			assertTrue(bienco.getBuildings().get(0).isForSale());
			assertEquals(bienco.getBuildings().get(0).getObservations(), newObservations);
		} catch (NoValueException e) {
			fail("NoValueException not expected");
		} catch (NegativeValueException e) {
			fail("NegativeValueException not expected");
		}
	}
	
	@Test
	public void testUpdateBuilding10() {
		setupScenary2();
		String newAddress = "Calle 4C # 5-29";
		String newNeighborhood = "Porvenir";
		String newZone = "Sur";
		String newType = "Casa";
		String newPrice = "580000";
		boolean forSale = false;
		String newObservations = "Casa de 1 planta";
		try {
			boolean updated = bienco.updateBuilding(bienco.getBuildings().get(0), newAddress, newNeighborhood, newZone, newType, newPrice, forSale, newObservations);
			assertFalse(updated);
			assertEquals(bienco.getBuildings().get(0).getAddress(), newAddress);
			assertEquals(bienco.getBuildings().get(0).getNeighborhood(), newNeighborhood);
			assertEquals(bienco.getBuildings().get(0).getZone(), Zone.SUR);
			assertEquals(bienco.getBuildings().get(0).getType(), TypeOfBuilding.CASA);
			assertEquals(bienco.getBuildings().get(0).getPrice(), newPrice);
			assertFalse(bienco.getBuildings().get(0).isForSale());
			assertEquals(bienco.getBuildings().get(0).getObservations(), newObservations);
		} catch (NoValueException e) {
			fail("NoValueException not expected");
		} catch (NegativeValueException e) {
			fail("NegativeValueException not expected");
		}
	}
}
