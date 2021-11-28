package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


import org.junit.jupiter.api.Test;

import exceptions.NegativeValueException;
import exceptions.NoValueException;
import exceptions.SimpleGraphException;

public class BiencoTest {
	
	private Bienco bienco;
	
	public void setupScenary1() {
		bienco = new Bienco(1);
		bienco.getBuildings().add(new Building("Calle 4C # 5-29", "Porvenir", Zone.SUR, TypeOfBuilding.CASA, 580000, false, "Casa de 1 planta que contiene 2 habitaciones, sala, comedor, 1 banio y un pequenio patio trasero."));
		bienco.getBuildings().add(new Building("Calle 5B # 3-10", "Porvenir", Zone.SUR, TypeOfBuilding.LOCAL, 620000, false, "Local de 500 metros cuadrados con segundo piso que contiene un banio"));
		bienco.getBuildings().add(new Building("Calle 7A # 4-15", "Vipasa", Zone.NORTE, TypeOfBuilding.CASA, 205000000, true, "Casa de 3 plantas que contiene 4 habitaciones, sala, terraza, comedor, 2 banios, garaje y patio trasero."));
		bienco.getBuildings().add(new Building("Calle 3A # 5-29", "Porvenir", Zone.SUR, TypeOfBuilding.APARTAESTUDIO, 1000000, false, "Apartaestudio amoblado ubicado en segundo piso. Contiene 2 habitaciones, sala, cocina, 1 banio."));
		bienco.getBuildings().add(new Building("Calle 9B # 8-19", "Vipasa", Zone.NORTE, TypeOfBuilding.CASA, 350000000, true, "Casa de 2 plantas que contiene 3 habitaciones, sala, comedor, 2 banios, garaje pequenio y un patio trasero."));
		bienco.getBuildings().add(new Building("Calle 11B # 2-16", "Vipasa", Zone.NORTE, TypeOfBuilding.APARTAMENTO, 275000000, false, "Apartamento de 300 metros cuadrados que tiene 2 habitaciones, banio,  comedor, sitio para lavado y un pequenio balcon."));
		bienco.getBuildings().add(new Building("Calle 58A # 1-15", "Santa Teresita", Zone.OESTE, TypeOfBuilding.APARTAMENTO, 400000000, true, "Apartamento de 400 metros cuadrados que tiene 4 habitaciones, 2 banios,  sala, y balcon."));
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
			assertEquals(bienco.getBuildings().get(10).getAddress(), "Calle 15 # 4-89");
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
		try {
			bienco.addBuilding(address, neighborhood, zone, type, price, forSale, observations);
		} catch (NoValueException e) {
			fail("NoValueException not expected");
		} catch (NegativeValueException e) {
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
		try {
			bienco.addBuilding(address, neighborhood, zone, type, price, forSale, observations);
		} catch (NoValueException e) {
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
		setupScenary1();
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
			assertEquals(bienco.getBuildings().get(0).getZone().toString(), Zone.SUR.toString());
			assertEquals(bienco.getBuildings().get(0).getType().toString(), TypeOfBuilding.CASA.toString());
			assertTrue(bienco.getBuildings().get(0).getPrice()==550000.0);
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
		setupScenary1();
		String newAddress = "Calle 4C # 5-29";
		String newNeighborhood = "Porvenir";
		String newZone = "Sur";
		String newType = "Casa";
		String newPrice = "-500000";
		boolean forSale = false;
		String newObservations = "Casa de 1 planta que contiene 2 habitaciones, sala, comedor, 1 banio y un pequenio patio trasero.";
		try {
			bienco.updateBuilding(bienco.getBuildings().get(0), newAddress, newNeighborhood, newZone, newType, newPrice, forSale, newObservations);
		} catch (NoValueException e) {
			fail("NoValueException not expected");
		} catch (NegativeValueException e) {
			assertEquals(bienco.getBuildings().get(0).getAddress(), newAddress);
			assertEquals(bienco.getBuildings().get(0).getNeighborhood(), newNeighborhood);
			assertEquals(bienco.getBuildings().get(0).getZone().toString(), Zone.SUR.toString());
			assertEquals(bienco.getBuildings().get(0).getType().toString(), TypeOfBuilding.CASA.toString());
			assertTrue(bienco.getBuildings().get(0).getPrice()==580000.0);
			assertFalse(bienco.getBuildings().get(0).isForSale());
			assertEquals(bienco.getBuildings().get(0).getObservations(), newObservations);
		}
	}
	
	@Test
	public void testUpdateBuilding3() {
		setupScenary1();
		String newAddress = "Calle 4C # 5-29";
		String newNeighborhood = "Porvenir";
		String newZone = "Sur";
		String newType = "Casa";
		String newPrice = "0";
		boolean forSale = false;
		String newObservations = "Casa de 1 planta que contiene 2 habitaciones, sala, comedor, 1 banio y un pequenio patio trasero.";
		try {
			bienco.updateBuilding(bienco.getBuildings().get(0), newAddress, newNeighborhood, newZone, newType, newPrice, forSale, newObservations);
		} catch (NoValueException e) {
			assertEquals(bienco.getBuildings().get(0).getAddress(), newAddress);
			assertEquals(bienco.getBuildings().get(0).getNeighborhood(), newNeighborhood);
			assertEquals(bienco.getBuildings().get(0).getZone().toString(), Zone.SUR.toString());
			assertEquals(bienco.getBuildings().get(0).getType().toString(), TypeOfBuilding.CASA.toString());
			assertTrue(bienco.getBuildings().get(0).getPrice()==580000.0);
			assertFalse(bienco.getBuildings().get(0).isForSale());
			assertEquals(bienco.getBuildings().get(0).getObservations(), newObservations);
		} catch (NegativeValueException e) {
			fail("NegativeValueException not expected");
		}
	}
	
	@Test
	public void testUpdateBuilding4() {
		setupScenary1();
		String newAddress = "Calle 5B # 3-10";
		String newNeighborhood = "Porvenir";
		String newZone = "Sur";
		String newType = "Casa";
		String newPrice = "580000";
		boolean forSale = false;
		String newObservations = "Casa de 1 planta que contiene 2 habitaciones, sala, comedor, 1 banio y un pequenio patio trasero.";
		try {
			boolean updated = bienco.updateBuilding(bienco.getBuildings().get(0), newAddress, newNeighborhood, newZone, newType, newPrice, forSale, newObservations);
			assertFalse(updated);
			assertEquals(bienco.getBuildings().get(0).getAddress(), "Calle 4C # 5-29");
			assertEquals(bienco.getBuildings().get(0).getNeighborhood(), newNeighborhood);
			assertEquals(bienco.getBuildings().get(0).getZone().toString(), Zone.SUR.toString());
			assertEquals(bienco.getBuildings().get(0).getType().toString(), TypeOfBuilding.CASA.toString());
			assertTrue(bienco.getBuildings().get(0).getPrice()==580000);
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
		setupScenary1();
		String newAddress = "Calle 4C # 3-19";
		String newNeighborhood = "Porvenir";
		String newZone = "Sur";
		String newType = "Casa";
		String newPrice = "580000";
		boolean forSale = false;
		String newObservations = "Casa de 1 planta que contiene 2 habitaciones, sala, comedor, 1 baño y un pequeño patio trasero.";
		try {
			boolean updated = bienco.updateBuilding(bienco.getBuildings().get(0), newAddress, newNeighborhood, newZone, newType, newPrice, forSale, newObservations);
			assertTrue(updated);
			assertEquals(bienco.getBuildings().get(0).getAddress(), newAddress);
			assertEquals(bienco.getBuildings().get(0).getNeighborhood(), newNeighborhood);
			assertEquals(bienco.getBuildings().get(0).getZone().toString(), Zone.SUR.toString());
			assertEquals(bienco.getBuildings().get(0).getType().toString(), TypeOfBuilding.CASA.toString());
			assertTrue(bienco.getBuildings().get(0).getPrice()==580000);
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
		setupScenary1();
		String newAddress = "Calle 4C # 5-29";
		String newNeighborhood = "Popular";
		String newZone = "Sur";
		String newType = "Casa";
		String newPrice = "580000";
		boolean forSale = false;
		String newObservations = "Casa de 1 planta que contiene 2 habitaciones, sala, comedor, 1 baño y un pequeño patio trasero.";
		try {
			boolean updated = bienco.updateBuilding(bienco.getBuildings().get(0), newAddress, newNeighborhood, newZone, newType, newPrice, forSale, newObservations);
			assertTrue(updated);
			assertEquals(bienco.getBuildings().get(0).getAddress(), newAddress);
			assertEquals(bienco.getBuildings().get(0).getNeighborhood(), newNeighborhood);
			assertEquals(bienco.getBuildings().get(0).getZone().toString(), Zone.SUR.toString());
			assertEquals(bienco.getBuildings().get(0).getType().toString(), TypeOfBuilding.CASA.toString());
			assertTrue(bienco.getBuildings().get(0).getPrice()==580000);
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
		setupScenary1();
		String newAddress = "Calle 4C # 5-29";
		String newNeighborhood = "Porvenir";
		String newZone = "Oeste";
		String newType = "Casa";
		String newPrice = "580000";
		boolean forSale = false;
		String newObservations = "Casa de 1 planta que contiene 2 habitaciones, sala, comedor, 1 baño y un pequeño patio trasero.";
		try {
			boolean updated = bienco.updateBuilding(bienco.getBuildings().get(0), newAddress, newNeighborhood, newZone, newType, newPrice, forSale, newObservations);
			assertTrue(updated);
			assertEquals(bienco.getBuildings().get(0).getAddress(), newAddress);
			assertEquals(bienco.getBuildings().get(0).getNeighborhood(), newNeighborhood);
			assertEquals(bienco.getBuildings().get(0).getZone().toString(), Zone.OESTE.toString());
			assertEquals(bienco.getBuildings().get(0).getType().toString(), TypeOfBuilding.CASA.toString());
			assertTrue(bienco.getBuildings().get(0).getPrice()==580000);
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
		setupScenary1();
		String newAddress = "Calle 4C # 5-29";
		String newNeighborhood = "Porvenir";
		String newZone = "Sur";
		String newType = "Apartamento";
		String newPrice = "580000";
		boolean forSale = false;
		String newObservations = "Casa de 1 planta que contiene 2 habitaciones, sala, comedor, 1 baño y un pequeño patio trasero.";
		try {
			boolean updated = bienco.updateBuilding(bienco.getBuildings().get(0), newAddress, newNeighborhood, newZone, newType, newPrice, forSale, newObservations);
			assertTrue(updated);
			assertEquals(bienco.getBuildings().get(0).getAddress(), newAddress);
			assertEquals(bienco.getBuildings().get(0).getNeighborhood(), newNeighborhood);
			assertEquals(bienco.getBuildings().get(0).getZone().toString(), Zone.SUR.toString());
			assertEquals(bienco.getBuildings().get(0).getType().toString(), TypeOfBuilding.APARTAMENTO.toString());
			assertTrue(bienco.getBuildings().get(0).getPrice()==580000);
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
		setupScenary1();
		String newAddress = "Calle 4C # 5-29";
		String newNeighborhood = "Porvenir";
		String newZone = "Sur";
		String newType = "Casa";
		String newPrice = "580000";
		boolean forSale = true;
		String newObservations = "Casa de 1 planta que contiene 2 habitaciones, sala, comedor, 1 baño y un pequeño patio trasero.";
		try {
			boolean updated = bienco.updateBuilding(bienco.getBuildings().get(0), newAddress, newNeighborhood, newZone, newType, newPrice, forSale, newObservations);
			assertTrue(updated);
			assertEquals(bienco.getBuildings().get(0).getAddress(), newAddress);
			assertEquals(bienco.getBuildings().get(0).getNeighborhood(), newNeighborhood);
			assertEquals(bienco.getBuildings().get(0).getZone().toString(), Zone.SUR.toString());
			assertEquals(bienco.getBuildings().get(0).getType().toString(), TypeOfBuilding.CASA.toString());
			assertTrue(bienco.getBuildings().get(0).getPrice()==580000);
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
		setupScenary1();
		String newAddress = "Calle 4C # 5-29";
		String newNeighborhood = "Porvenir";
		String newZone = "Sur";
		String newType = "Casa";
		String newPrice = "580000";
		boolean forSale = false;
		String newObservations = "Casa de 1 planta";
		try {
			boolean updated = bienco.updateBuilding(bienco.getBuildings().get(0), newAddress, newNeighborhood, newZone, newType, newPrice, forSale, newObservations);
			assertTrue(updated);
			assertEquals(bienco.getBuildings().get(0).getAddress(), newAddress);
			assertEquals(bienco.getBuildings().get(0).getNeighborhood(), newNeighborhood);
			assertEquals(bienco.getBuildings().get(0).getZone().toString(), Zone.SUR.toString());
			assertEquals(bienco.getBuildings().get(0).getType().toString(), TypeOfBuilding.CASA.toString());
			assertTrue(bienco.getBuildings().get(0).getPrice()==580000);
			assertFalse(bienco.getBuildings().get(0).isForSale());
			assertEquals(bienco.getBuildings().get(0).getObservations(), newObservations);
		} catch (NoValueException e) {
			fail("NoValueException not expected");
		} catch (NegativeValueException e) {
			fail("NegativeValueException not expected");
		}
	}
	
	@Test
	public void testFilterBuildings1() {
		setupScenary1();
		
		String priceFrom="500000";
		String	priceTo="350000000";
		try {
			bienco.filterBuildings("", "", "", priceFrom, priceTo, "");
			
			assertEquals(bienco.getFilterBuildings().size(),6);
			assertTrue(bienco.getBuildings().get(0)==bienco.getFilterBuildings().get(0));
			assertTrue(bienco.getBuildings().get(1)==bienco.getFilterBuildings().get(1));
			assertTrue(bienco.getBuildings().get(2)==bienco.getFilterBuildings().get(2));
			assertTrue(bienco.getBuildings().get(3)==bienco.getFilterBuildings().get(3));
			assertTrue(bienco.getBuildings().get(4)==bienco.getFilterBuildings().get(4));
			assertTrue(bienco.getBuildings().get(5)==bienco.getFilterBuildings().get(5));
			
			
		}  catch (NoValueException e) {
			fail("NoValueException not expected");
		} catch (NegativeValueException e) {
			fail("NegativeValueException not expected");
		}
		
	}
	
	@Test
	public void testFilterBuildings2() {
		setupScenary1();
		
		String zone="NORTE";
		String	type="CASA";
		String purpose="V";
		try {
			bienco.filterBuildings("", zone, type, "", "", purpose);
			
			assertEquals(bienco.getFilterBuildings().size(),2);
			assertTrue(bienco.getBuildings().get(2)==bienco.getFilterBuildings().get(0));
			assertTrue(bienco.getBuildings().get(4)==bienco.getFilterBuildings().get(1));
			
			
		}  catch (NoValueException e) {
			fail("NoValueException not expected");
		} catch (NegativeValueException e) {
			fail("NegativeValueException not expected");
		}
		
	}
	
	@Test
	public void testFilterBuildings3() {
		setupScenary1();
		
		
		String	type="APARTAMENTO";
		String priceFrom="100000000";
		String	priceTo="360000000";
		try {
			bienco.filterBuildings("", "", type, priceFrom, priceTo, "");
			
			assertEquals(bienco.getFilterBuildings().size(),2);
			assertTrue(bienco.getBuildings().get(5)==bienco.getFilterBuildings().get(0));
			assertTrue(bienco.getBuildings().get(9)==bienco.getFilterBuildings().get(1));
			
			
		}  catch (NoValueException e) {
			fail("NoValueException not expected");
		} catch (NegativeValueException e) {
			fail("NegativeValueException not expected");
		}
		
	}
	
	@Test
	public void testFilterBuildings4() {
		setupScenary1();
		
		String purpose="V";
		try {
			bienco.filterBuildings("", "", "", "", "", purpose);
			
			assertEquals(bienco.getFilterBuildings().size(),6);
			assertTrue(bienco.getBuildings().get(2)==bienco.getFilterBuildings().get(0));
			assertTrue(bienco.getBuildings().get(4)==bienco.getFilterBuildings().get(1));
			assertTrue(bienco.getBuildings().get(6)==bienco.getFilterBuildings().get(2));
			assertTrue(bienco.getBuildings().get(7)==bienco.getFilterBuildings().get(3));
			assertTrue(bienco.getBuildings().get(8)==bienco.getFilterBuildings().get(4));
			assertTrue(bienco.getBuildings().get(9)==bienco.getFilterBuildings().get(5));
			
			
		}  catch (NoValueException e) {
			fail("NoValueException not expected");
		} catch (NegativeValueException e) {
			fail("NegativeValueException not expected");
		}
		
	}
	
	@Test
	public void testFilterBuildings5() {
		setupScenary1();
		
		String neighborhood="Porvenir";
		String zone="SUR";
		
		try {
			bienco.filterBuildings(neighborhood, zone, "", "", "", "");
			
			assertEquals(bienco.getFilterBuildings().size(),3);
			assertTrue(bienco.getBuildings().get(0)==bienco.getFilterBuildings().get(0));
			assertTrue(bienco.getBuildings().get(1)==bienco.getFilterBuildings().get(1));
			assertTrue(bienco.getBuildings().get(3)==bienco.getFilterBuildings().get(2));
			
			
		}  catch (NoValueException e) {
			fail("NoValueException not expected");
		} catch (NegativeValueException e) {
			fail("NegativeValueException not expected");
		}
		
	}
	
	@Test
	public void testFilterBuildings6() {
		setupScenary1();
		
		String zone="ESTE";
		String type="APARTAMENTO";
		
		try {
			bienco.filterBuildings("", zone, type, "", "", "");
			
			assertTrue(bienco.getFilterBuildings().isEmpty());
			
			
		}  catch (NoValueException e) {
			fail("NoValueException not expected");
		} catch (NegativeValueException e) {
			fail("NegativeValueException not expected");
		}
		
	}
	
	@Test
	public void testFilterAddDistanceAndCalculateRoute() {
		setupScenary1();
		//******VERSION 1 GRAFO
		String priceFrom="500000";
		String	priceTo="350000000";
		
		try {
			bienco.filterBuildings("", "", "", priceFrom, priceTo, "");

			assertEquals(bienco.getFilterBuildings().size(),6);
		}catch (NoValueException e) {
			fail("NoValueException not expected");
		} catch (NegativeValueException e) {
			fail("NegativeValueException not expected");
		}
		
		try {
			bienco.addDistancesBetweenProperties(bienco.getBuildings().get(0), bienco.getBuildings().get(1), "4");
			bienco.addDistancesBetweenProperties(bienco.getBuildings().get(1), bienco.getBuildings().get(2), "5");
			bienco.addDistancesBetweenProperties(bienco.getBuildings().get(2), bienco.getBuildings().get(3), "8");
			bienco.addDistancesBetweenProperties(bienco.getBuildings().get(3), bienco.getBuildings().get(4), "10");
			boolean connection=bienco.connectionFilterBuildings();
			assertFalse(connection);
			bienco.addDistancesBetweenProperties(bienco.getBuildings().get(4), bienco.getBuildings().get(5), "3");
			bienco.addDistancesBetweenProperties(bienco.getBuildings().get(0), bienco.getBuildings().get(3), "2");
			bienco.addDistancesBetweenProperties(bienco.getBuildings().get(1), bienco.getBuildings().get(3), "1");
			bienco.addDistancesBetweenProperties(bienco.getBuildings().get(2), bienco.getBuildings().get(4), "2");
			bienco.addDistancesBetweenProperties(bienco.getBuildings().get(2), bienco.getBuildings().get(5), "6");
			connection=bienco.connectionFilterBuildings();
			assertTrue(connection);
			
			assertEquals(bienco.getGraph().getEdges().size(),9);
		} catch (SimpleGraphException e) {
			fail("SimpleGraphException not expected");
		}
		
		Building source=bienco.getBuildings().get(0);
		
		String routes=bienco.calculateRoute(source);
		
		String expected=
				"*** Rutas calculadas: ***\n\n"
				+"Calle 4C # 5-29 --> Calle 3A # 5-29 |Distancia: 2 metros\n"
				+ "\n"
				+ "Calle 4C # 5-29 --> Calle 3A # 5-29 --> Calle 5B # 3-10 |Distancia: 3 metros\n"
				+ "\n"
				+ "Calle 4C # 5-29 --> Calle 3A # 5-29 --> Calle 5B # 3-10 --> Calle 7A # 4-15 |Distancia: 8 metros\n"
				+ "\n"
				+ "Calle 4C # 5-29 --> Calle 3A # 5-29 --> Calle 5B # 3-10 --> Calle 7A # 4-15 --> Calle 9B # 8-19 |Distancia: 10 metros\n"
				+ "\n"
				+ "Calle 4C # 5-29 --> Calle 3A # 5-29 --> Calle 5B # 3-10 --> Calle 7A # 4-15 --> Calle 9B # 8-19 --> Calle 11B # 2-16 |Distancia: 13 metros\n"
				+ "\n"
				+ "\n"
				+ "*** Ruta sugerida: ***\n\n"
				+ "Calle 4C # 5-29 --> Calle 3A # 5-29 --> Calle 5B # 3-10 --> Calle 7A # 4-15 --> Calle 9B # 8-19 --> Calle 11B # 2-16 |Distancia: 13 metros\n"
				+ "";
		
		assertEquals(expected,routes);
		
	}
	
}

