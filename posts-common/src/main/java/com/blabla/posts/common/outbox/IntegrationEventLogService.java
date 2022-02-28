package com.blabla.posts.common.outbox;

import com.blabla.posts.common.eventhandling.IntegrationEvent;
import java.util.List;

public interface IntegrationEventLogService {
    List<IntegrationEventLogEntry> retrieveEventLogsPendingToPublish();

    void markEventAsInProgress(IntegrationEventLogEntry eventLogEntry);

    void markEventAsPublished(IntegrationEventLogEntry eventLogEntry);

    void markEventAsFailed(IntegrationEventLogEntry eventLogEntry);

    void saveEvent(IntegrationEvent event, String topic);
}
