package com.vpolosov.trainee.mergexml.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "validation_process")
public class ValidationProcess {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name="dir_ref", nullable = false)
    private String dirRef;

    @Column(name="is_succsess", nullable = false)
    private Boolean isSuccess;

    @Column(name="total_doc_ref", nullable = false)
    private String totalDocRef;

    @Column(name = "validation_process_date", nullable = false)
    private LocalDateTime validationProcessDate;


    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        ValidationProcess that = (ValidationProcess) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "dirRef = " + dirRef + ", " +
                "isSuccess = " + isSuccess + ", " +
                "totalDocRef = " + totalDocRef + ", " +
                "validationProcessDate = " + validationProcessDate + ")";
    }
}