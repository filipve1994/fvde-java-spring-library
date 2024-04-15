package io.filipvde.customspringbootstarter.jpastarter.abstractclasses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Instantiates a new auditable.
 */
@Setter
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
	value = {"createdAt", "updatedAt"},
	allowGetters = true
)
public abstract class AuditModel implements Serializable {

//	/** The created timestamp. */
//	@CreatedDate => use not instant everywhere but only localdate/localdatetime
//	@Column(name = "createdTs")
//	private Instant createdTimestamp; //2024-04-12 13:08:01.626292+00

	@Column(name = "CREATED_AT", nullable = false, updatable = false)
	@CreatedDate
	private LocalDateTime creationDateLocalDateTime; //2024-04-12 14:08:01.626292

	@Column(name = "CREATION_DATE_LOCAL_DATE")
	@CreatedDate
	private LocalDate creationDateLocalDate; //2024-04-12

//    @CreationTimestamp //hibernate => remove and replace by the jpa versions above
//    private LocalDateTime creationTimestamp; //2024-04-12 18:08:01.661426


	/**
	 * The created by.
	 */
//	@CreatedBy
//	private String createdBy;

//    private String createdBy;


//	/** The updated timestamp. */
//	@LastModifiedDate => use not instant everywhere but only localdate/localdatetime
//	@Column(name="updatedTs")
//	private Instant updatedTimestamp; //2024-04-12 13:08:01.626292+00

	@Column(name = "UPDATED_AT", nullable = false)
	@LastModifiedDate
	private LocalDateTime modificationDateLocalDateTime; //2024-04-12 14:08:01.626292

	@Column(name = "MODIFICATION_DATE_LOCAL_DATE")
	@LastModifiedDate
	private LocalDate modificationDateLocalDate; // 2024-04-12

//    @UpdateTimestamp //hibernate => remove and replace by the jpa versions above
//    private LocalDateTime modificationDateTimestamp; //2024-04-12 18:08:01.66159


	/** The updated by. */
//	@LastModifiedBy
//	private String updatedBy;

//    private String modifiedBy;


}