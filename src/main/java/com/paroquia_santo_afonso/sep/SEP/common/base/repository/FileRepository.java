package com.paroquia_santo_afonso.sep.SEP.common.base.repository;

import com.paroquia_santo_afonso.sep.SEP.common.base.model.FileBaseEntity;
import com.paroquia_santo_afonso.sep.SEP.common.base.projection.FileProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@NoRepositoryBean
public interface FileRepository<T extends FileBaseEntity, ID> extends JpaRepository<T, ID> {
    Optional<FileProjection> findFileProjectionById(ID id);
}
