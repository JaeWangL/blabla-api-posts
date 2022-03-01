package com.blabla.posts.api.infrastructure.idempotency;

import com.blabla.posts.api.application.infrastructure.requestmanager.RequestManager;
import com.blabla.posts.api.domain.exceptions.PostDomainException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class RequestManagerImpl implements RequestManager {
    private final ClientRequestRepository clientRequestRepository;

    @Override
    public boolean exist(String id) {
        return clientRequestRepository.findById(id).isPresent();
    }

    @Override
    public void createRequestForCommand(String id, String commandName) {
        if (exist(id)) {
            throw new PostDomainException("Request with id: %s already exists".formatted(id));
        }

        clientRequestRepository.save(new ClientRequest(id, commandName, LocalDateTime.now()));
    }
}
