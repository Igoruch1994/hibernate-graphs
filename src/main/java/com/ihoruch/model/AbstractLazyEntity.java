package com.cnhindustrial.dtc.model;

import com.cnhindustrial.dtc.model.data.AbstractAuditEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.engine.spi.PersistentAttributeInterceptable;
import org.hibernate.engine.spi.PersistentAttributeInterceptor;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

/**
 * Hibernate interceptor is used here to lazy-load entities without direct primary key relations between them.
 */
@Getter
@Setter
@MappedSuperclass
@SuperBuilder
@NoArgsConstructor
public abstract class AbstractLazyAuditEntity extends AbstractAuditEntity implements PersistentAttributeInterceptable {

    @Transient
    private PersistentAttributeInterceptor persistentAttributeInterceptor;

    @Override
    public PersistentAttributeInterceptor $$_hibernate_getInterceptor() {
        return persistentAttributeInterceptor;
    }

    @Override
    public void $$_hibernate_setInterceptor(PersistentAttributeInterceptor persistentAttributeInterceptor) {
        this.persistentAttributeInterceptor = persistentAttributeInterceptor;
    }

}
