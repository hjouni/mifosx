package org.mifosplatform.organisation.office.address.data;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.joda.time.LocalDate;
//import org.mifosplatform.organisation.office.data.OfficeData;

public class AddressData implements Serializable {
    private final Long id;
    private final Long office_id;
    private final String line_1;
    private final String line_2;
    private final String state;
    private final String city;
    private final int type;
    
    
 /*   
    public static AddressData dropdown(final Long id,final Long office_id, final String line_1, final String line_2, final String state, final String city,final int type) {
        return new AddressData(id, office_id, line_1, line_2, state, city, type);
    }
*/
    public AddressData(Long id,Long office_id, String line_1,String line_2,String city,String state,int type){
        this.id=id;
        this.office_id=office_id;
        this.line_1=line_1;
        this.line_2=line_2;
        this.city=city;
        this.state=state;
        this.type=type;
    }
    
    public Long getId() {
        return this.id;
    }
    
    public Long getOffice_id() {
        return this.office_id;
    }
    
    public String getLine_1() {
        return this.line_1;
    }
    
    public String getLine_2() {
        return this.line_2;
    }
    
    public String getState() {
        return this.state;
    }
    
    public String getCity() {
        return this.city;
    }
    
    public int getType() {
        return this.type;
    }
    /*public static AddressData template(final List<AddressData> parentLookups) {
        return new AddressData(null, null, null, null, null, null, null,null);
    }

    public static AddressData appendedTemplate(final OfficeData office, final Collection<OfficeData> allowedParents) {
        return new AddressData(office.id, office.name, office.nameDecorated, office.externalId, office.openingDate, office.hierarchy,
                office.parentId, office.parentName, allowedParents,office.address);
    }

    public OfficeData(final Long id, final String name, final String nameDecorated, final String externalId, final LocalDate openingDate,
            final String hierarchy, final Long parentId, final String parentName, final Collection<OfficeData> allowedParents,final String address) {
        this.id = id;
        this.name = name;
        this.nameDecorated = nameDecorated;
        this.externalId = externalId;
        this.openingDate = openingDate;
        this.hierarchy = hierarchy;
        this.parentName = parentName;
        this.parentId = parentId;
        this.allowedParents = allowedParents;
        this.address=address;
    }

    public boolean hasIdentifyOf(final Long officeId) {
        return this.id.equals(officeId);
    }

    public String name() {
        return this.name;
    }

    public String getHierarchy() {
        return this.hierarchy;
    }
*/}
