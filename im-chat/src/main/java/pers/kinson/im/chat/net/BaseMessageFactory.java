package pers.kinson.im.chat.net;

import jforgame.commons.ClassScanner;
import jforgame.socket.share.annotation.MessageMeta;
import jforgame.socket.share.message.MessageFactory;
import jforgame.socket.support.DefaultMessageFactory;

import java.util.Collection;
import java.util.Set;

public class BaseMessageFactory implements MessageFactory {

    private static volatile DefaultMessageFactory self = new DefaultMessageFactory();


    public BaseMessageFactory(String path) {
        Set<Class<?>> messages = ClassScanner.listClassesWithAnnotation(path, MessageMeta.class);
        messages.forEach(e->{
            MessageMeta annotation = e.getAnnotation(MessageMeta.class);
            registerMessage(annotation.cmd(), e);
        });
    }

    @Override
    public void registerMessage(int cmd, Class<?> clazz) {
        self.registerMessage(cmd, clazz);
    }

    @Override
    public Class<?> getMessage(int cmd) {
        return self.getMessage(cmd);
    }

    @Override
    public int getMessageId(Class<?> clazz) {
        return self.getMessageId(clazz);
    }

    @Override
    public boolean contains(Class<?> clazz) {
        return self.contains(clazz);
    }

    @Override
    public Collection<Class<?>> registeredClassTypes() {
        return self.registeredClassTypes();
    }

}