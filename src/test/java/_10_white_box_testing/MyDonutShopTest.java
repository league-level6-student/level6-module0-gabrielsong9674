package _10_white_box_testing;

import _09_intro_to_white_box_testing.models.DeliveryService;
import _09_intro_to_white_box_testing.models.Order;
import _10_white_box_testing.models.BakeryService;
import _10_white_box_testing.models.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MyDonutShopTest {

	
    MyDonutShop myDonutShop;
    
    @Mock
    PaymentService paymentService;
    
    @Mock
    BakeryService bakeryService;
    
    @Mock
    DeliveryService deliveryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

    	myDonutShop = new MyDonutShop(paymentService, deliveryService, bakeryService);
 
    	
    	myDonutShop.setDeliveryService(deliveryService);
    	myDonutShop.setPaymentService(paymentService);


    }

    @Test
    void itShouldTakeDeliveryOrder() throws Exception {
        //given
    	Order order = new Order("Customer",
                "PhoneNumber",
                1,
                5.00,
                "CreditCard",
                true);
    	when(paymentService.charge(order)).thenReturn(true);

        //when
    	
    	myDonutShop.openForTheDay();
    	myDonutShop.takeOrder(order);
        //then
        verify(deliveryService, times(1)).scheduleDelivery(order);

    }

    @Test
    void givenInsufficientDonutsRemaining_whenTakeOrder_thenThrowIllegalArgumentException() throws Exception {
        //given
    	Order order = new Order("Customer",
                "PhoneNumber",
                10,
                5.00,
                "CreditCard",
                true);
    	myDonutShop.openForTheDay();
    	bakeryService.setDonutsRemaining(1);
  
        //when
    	//then
    	Throwable exceptionThrown = assertThrows(Exception.class, () -> myDonutShop.takeOrder(order));
        assertEquals(exceptionThrown.getMessage(), "Insufficient donuts remaining");
    }
    	
    

    @Test
    void givenNotOpenForBusiness_whenTakeOrder_thenThrowIllegalStateException(){
        //given
    	Order order = new Order("Customer",
                "PhoneNumber",
                1,
                5.00,
                "CreditCard",
                true);
    	bakeryService.setDonutsRemaining(1);
  
        //when
    	//then
    	Throwable exceptionThrown = assertThrows(Exception.class, () -> myDonutShop.takeOrder(order));
        assertEquals(exceptionThrown.getMessage(), "Sorry we're currently closed");
        //when

        //then
    }

}