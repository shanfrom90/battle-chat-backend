import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.battle.chat.demo.model.Message;
```
import com.battle.chat.demo.repository.MessageRepository;

@Service
public class MessageService {
    @Autowired
    MessageRepository messageRepository;

    public void saveMessage(Message message){

        messageRepository.save()
    }
}



