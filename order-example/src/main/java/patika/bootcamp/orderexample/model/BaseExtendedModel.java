package patika.bootcamp.orderexample.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
//We made it abstract so that an instance of this class is not created.
public abstract class BaseExtendedModel extends BaseModel {

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date createdAt;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date deletedAt;

    @Column(length = 50)
    private String createdBy;
    @Column(length = 50)
    private String updatedBy;
    @Column(length = 50)
    private String deletedBy;

}
