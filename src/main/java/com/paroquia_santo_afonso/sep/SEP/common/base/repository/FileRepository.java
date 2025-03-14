package com.paroquia_santo_afonso.sep.SEP.common.base.repository;

import com.paroquia_santo_afonso.sep.SEP.common.base.model.FileBaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface FileRepository<T extends FileBaseEntity, ID> extends JpaRepository<T, ID> {

}
