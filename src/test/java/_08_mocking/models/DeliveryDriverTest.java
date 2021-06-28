package _08_mocking.models;

import _07_intro_to_mocking.models.Car;
import _07_intro_to_mocking.models.Engine;
import _07_intro_to_mocking.models.GasTank;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class DeliveryDriverTest {

    DeliveryDriver deliveryDriver;

    @Mock
    Car car;

    @Mock
    CellPhone cell;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        deliveryDriver = new DeliveryDriver("Steve",car, cell);
    }

    @Test
    void itShouldWasteTime() {
        //given
    	boolean expectedWaste = true;
        //when
    	when(deliveryDriver.wasteTime()).thenReturn(expectedWaste);
        //then
    	boolean actualWaste = deliveryDriver.wasteTime();
    	assertEquals(expectedWaste, actualWaste);
    }

    @Test
    void itShouldRefuel() {
        //given
    	boolean expectedRefueled = true;
        int octane = 85;
        //when
    	when(deliveryDriver.refuel(octane)).thenReturn(expectedRefueled);
        //then
    	boolean actualRefueled = deliveryDriver.refuel(octane);
    	assertEquals(expectedRefueled, actualRefueled);
    }

    @Test
    void itShouldContactCustomer() {
        //given
    	boolean expectedContacted = true;
    	String num = "1234567890";
        //when
    	when(deliveryDriver.contactCustomer(num)).thenReturn(expectedContacted);;
        //then
    	boolean actualContacted = deliveryDriver.contactCustomer(num);
    	assertEquals(expectedContacted, actualContacted);

    }

}