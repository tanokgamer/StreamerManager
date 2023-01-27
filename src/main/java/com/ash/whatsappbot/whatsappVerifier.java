package com.ash.whatsappbot;
import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.net.URI;
import java.math.BigDecimal;

public class whatsappVerifier {
    // Find your Account Sid and Token at twilio.com/console
    public static final String ACCOUNT_SID = "AC668b683f8e9176528982de9e8ac90b1c";
    public static final String AUTH_TOKEN = "[AuthToken]";

    public void starts() {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                        new com.twilio.type.PhoneNumber("whatsapp:"),
                        new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
                        "Hello! This is an editable text message. You are free to change it and write whatever you like.")
                .create();

        System.out.println(message.getSid());
    }
}