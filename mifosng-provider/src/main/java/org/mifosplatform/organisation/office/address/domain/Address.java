package org.mifosplatform.organisation.office.address.domain;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang.StringUtils;
import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.organisation.office.address.api.AddressApiConstants;
import org.mifosplatform.organisation.office.domain.Office;
import org.mifosplatform.organisation.office.exception.CannotUpdateOfficeWithParentOfficeSameAsSelf;
import org.mifosplatform.organisation.office.exception.RootOfficeParentCannotBeUpdated;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "m_address", uniqueConstraints = { })

public class Address extends AbstractPersistable<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "officeId")
    private Office office; //another property of Office cause the parent ID

 
    @Column(name = "line1", nullable = false, length = 100) //hibernate property
    private String line1;
    
    @Column(name = "line2", nullable = false, length = 100) //hibernate property
    private String line2;
    
    @Column(name = "city", nullable = false, length = 100) //hibernate property
    private String city;
    
    @Column(name = "state", nullable = false, length = 100) //hibernate property
    private String state;
    
    @Column(name = "type", nullable = false, length = 3) //hibernate property
    private int type;
      

 /*   public Address(final Office office,final String line_1, final String line_2, final String city, final String state, final int type) {
        this.office=office;
        this.line_1=line_1;
        this.line_2=line_2;
        this.city=city;
        this.state=state;
        this.type=type;
    }
*/
    public static Address fromJson(final Office office, final JsonCommand command) {
        
        final String line1 = command.stringValueOfParameterNamed(AddressApiConstants.line1);
        final String line2 = command.stringValueOfParameterNamed(AddressApiConstants.line2);
        final String city = command.stringValueOfParameterNamed(AddressApiConstants.city);
        final String state = command.stringValueOfParameterNamed(AddressApiConstants.state);
        final int type = command.integerValueOfParameterNamed(AddressApiConstants.type);
        return new Address(office, line1, line2, city, state, type);
    }

    
    public Office getOffice() {
        return this.office;
    }

    
    public void setOffice(Office office) {
        this.office = office;
    }

    
    public String getLine1() {
        return this.line1;
    }

    
    public void setLine1(String line1) {
        this.line1 = line1;
    }

    
    public String getLine2() {
        return this.line2;
    }

    
    public void setLine2(String line2) {
        this.line2 = line2;
    }

    
    public String getCity() {
        return this.city;
    }

    
    public void setCity(String city) {
        this.city = city;
    }

    
    public String getState() {
        return this.state;
    }

    
    public void setState(String state) {
        this.state = state;
    }

    
    public int getType() {
        return this.type;
    }

    
    public void setType(int type) {
        this.type = type;
    }

    protected Address() {
        this.line1 = null;
        this.line2 = null;
        this.city = null;
        this.state = null;
        this.type=0;
    }

    private Address(final Office office, final String line1, final String line2, final String city,final String state,final int type) {
        this.office = office;
       

        if (StringUtils.isNotBlank(line1)) {
            this.line1 = line1.trim();
        } else {
            this.line1 = null;
        }
        if (StringUtils.isNotBlank(line2)) {
            this.line2 = line2.trim();
        } else {
            this.line2 = null;
        }
        if (StringUtils.isNotBlank(city)) {
            this.city = city.trim();
        } else {
            this.city = null;
        }
        if (StringUtils.isNotBlank(state)) {
            this.state = state.trim();
        } else {
            this.state = null;
        }
        if (StringUtils.isNotBlank(city)) {
            this.city = city.trim();
        } else {
            this.city = null;
        }
        
        this.type = 0;
        
    }

    

    public Map<String, Object> update(final JsonCommand command) {

        final Map<String, Object> actualChanges = new LinkedHashMap<>(7);

        //final String dateFormatAsInput = command.dateFormat();
        //final String localeAsInput = command.locale();
        final String officeIdParamName = AddressApiConstants.officeId;
        if (command.isChangeInLongParameterNamed(officeIdParamName, this.office.getId())) {
            final Long newValue = command.longValueOfParameterNamed(officeIdParamName);
            actualChanges.put(officeIdParamName, newValue);
        }
        final String line1 = AddressApiConstants.line1;

        if (command.parameterExists(line1) && this.office == null) { throw new RootOfficeParentCannotBeUpdated(); }

        if (this.office != null && command.isChangeInLongParameterNamed(line1, this.office.getId())) {
            final String newValue = command.stringValueOfParameterNamed(line1);
            actualChanges.put(line1, newValue);
        }
        
        final String line2 = AddressApiConstants.line2;

        if (command.parameterExists(line2) && this.office == null) { throw new RootOfficeParentCannotBeUpdated(); }

        if (this.office != null && command.isChangeInLongParameterNamed(line2, this.office.getId())) {
            final String newValue = command.stringValueOfParameterNamed(line2);
            actualChanges.put(line2, newValue);
        }
        
        final String state = AddressApiConstants.state;

        if (command.parameterExists(state) && this.office == null) { throw new RootOfficeParentCannotBeUpdated(); }

        if (this.office != null && command.isChangeInLongParameterNamed(state, this.office.getId())) {
            final String newValue = command.stringValueOfParameterNamed(state);
            actualChanges.put(state, newValue);
        }

        final String city = AddressApiConstants.city;

        if (command.parameterExists(city) && this.office == null) { throw new RootOfficeParentCannotBeUpdated(); }

        if (this.office != null && command.isChangeInLongParameterNamed(city, this.office.getId())) {
            final String newValue = command.stringValueOfParameterNamed(city);
            actualChanges.put(city, newValue);
        }
        
        return actualChanges;
    }
    
    public void update(final Office newParent) {

        if (this.office == null) { throw new RootOfficeParentCannotBeUpdated(); }

        if (identifiedBy(newParent.getId())) { throw new CannotUpdateOfficeWithParentOfficeSameAsSelf(getId(), newParent.getId()); }

        this.office = newParent;
        
    }

    public boolean identifiedBy(final Long id) {
        return getId().equals(id);
    }

   
      public boolean hasParentOf(final Address address) {
        boolean isParent = false;
        if (this.office != null) {
            isParent = this.office.equals(address);
        }
        return isParent;
    }

    

    }
