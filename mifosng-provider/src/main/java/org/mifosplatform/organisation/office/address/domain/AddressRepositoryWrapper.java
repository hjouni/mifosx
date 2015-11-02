package org.mifosplatform.organisation.office.address.domain;

import org.mifosplatform.organisation.office.domain.*;
import org.mifosplatform.organisation.office.address.domain.AddressRepository;
import org.mifosplatform.organisation.office.exception.OfficeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

public class AddressRepositoryWrapper {
    private final AddressRepository repository;

    @Autowired
    public AddressRepositoryWrapper(final AddressRepository repository) {
        this.repository = repository;
    }

    public Address findOneWithNotFoundDetection(final Long id) {
        final Address address = this.repository.findOne(id);
        if (address == null) { throw new OfficeNotFoundException(id); }
        return address;
    }

    public void save(final Address entity) {
        this.repository.save(entity);
    }

    public void saveAndFlush(final Address entity) {
        this.repository.saveAndFlush(entity);
    }

    public void delete(final Address entity) {
        this.repository.delete(entity);
    }
}
