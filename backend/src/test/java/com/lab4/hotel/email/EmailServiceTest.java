package com.lab4.hotel.email;

import org.junit.Assert;
import org.junit.jupiter.api.Test;


public class EmailServiceTest {
    @Test
    void send(){
        String status=EmailService.send("viiptest@gmail.com","parolaviiptest!1","popvlad24@yahoo.com","test","Hello there!");
        Assert.assertEquals("message sent successfully",status);
    }
}
