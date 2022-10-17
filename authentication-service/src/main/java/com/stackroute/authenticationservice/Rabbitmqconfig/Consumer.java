package com.stackroute.authenticationservice.Rabbitmqconfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.stackroute.authenticationservice.model.User;
import com.stackroute.authenticationservice.model.UserDTO;
import com.stackroute.authenticationservice.repository.UserRepository;

@Component
public class Consumer {
    private static Logger logger = LogManager.getLogger(Consumer.class.toString());
    private static final String registerQueue="user_queue";
    @Autowired
    private UserRepository Repository;
    @RabbitListener(queues = registerQueue)
    public void getUserDetailsFromRabbitmq(UserDTO user) {
        logger.info("user listener invoked - Consuming Message with user Identifier : " + user.getEmail(),
                user.getPassword());

        User userdeatils = new User();
        userdeatils.setEmail(user.getEmail());
        userdeatils.setPassword(user.getPassword());
        Repository.save(userdeatils);
    }

}