package _06_payroll;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PayrollTest {

    Payroll payroll = new Payroll();

    @Test
    void itShouldCalculatePaycheck() {
        //given
    	double hourly = 17;
    	int hours = 40;
    	double expected = 680;
        //when
    	double actual = payroll.calculatePaycheck(hourly, hours);
        //then
    	assertEquals(expected, actual);
    }

    @Test
    void itShouldCalculateMileageReimbursement() {
        //given
    	int miles = 310;
    	double expected = 178.25;
        //when
    	double actual = payroll.calculateMileageReimbursement(miles);
        //then
    	assertEquals(expected, actual);
    }

    @Test
    void itShouldCreateOfferLetter() {
        //given
    	String name = "Grace";
    	double wage = 30;
    	String expected = "Hello " + name + ", We are pleased to offer you an hourly wage of " + wage;
    	//when
    	String actual = payroll.createOfferLetter(name, wage);
        //then
    	assertEquals(expected, actual);
    	
    }

}