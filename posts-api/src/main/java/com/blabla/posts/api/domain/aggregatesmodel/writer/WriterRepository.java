package com.blabla.posts.api.domain.aggregatesmodel.writer;

import com.blabla.posts.api.domain.seedwork.Repository;
import org.springframework.lang.NonNull;
import java.util.List;
import java.util.Optional;

public interface WriterRepository extends Repository<Writer> {
    Optional<Writer> findById(@NonNull WriterId id);

    List<Writer> findManyByDevice(@NonNull DeviceInfo device);
}
