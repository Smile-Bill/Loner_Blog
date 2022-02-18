package com.site.blog.my.core.util.kaptcha;

import com.google.code.kaptcha.text.TextProducer;
import com.google.code.kaptcha.util.Configurable;

import java.security.SecureRandom;

public class LonerTextCreator extends Configurable implements TextProducer {

    @Override
    public String getText() {
        int length = this.getConfig().getTextProducerCharLength();
        char[] chars = this.getConfig().getTextProducerCharString();
        SecureRandom rand = new SecureRandom();
        StringBuffer text = new StringBuffer();

        for(int i = 0; i < length; ++i) {
            text.append(chars[rand.nextInt(chars.length)]);
        }

        return text.toString();
    }
}
