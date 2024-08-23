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
@Table(name = "validation_file_history")
@NamedEntityGraph(
        name = "ValidationFileHistory.nodes",
        attributeNodes = {
                @NamedAttributeNode("validationProcess")
        }
)
public class ValidationFileHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "validation_id", nullable = false)
    private UUID validationId;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "doc_ref", nullable = false)
    private String docRef;

    @Column(name = "is_success", nullable = false)
    private Boolean isSuccess;

    @Column(name = "fail_fields", columnDefinition = "TEXT")
    private String failFields;

    @Column(name = "validation_date", nullable = false)
    private LocalDateTime validationDate;

    @ManyToOne
    @JoinColumn(name = "validation_process_id")
    private ValidationProcess validationProcess;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        ValidationFileHistory that = (ValidationFileHistory) o;
        return getValidationId() != null && Objects.equals(getValidationId(), that.getValidationId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "validationId = " + validationId + ", " +
                "fileName = " + fileName + ", " +
                "docRef = " + docRef + ", " +
                "isSuccess = " + isSuccess + ", " +
                "failFields = " + failFields + ", " +
                "validationDate = " + validationDate + ")";
    }
}