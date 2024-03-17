package com.qpay.authmanager.model.entity;

import com.qpay.libs.models.UserType;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.time.LocalDateTime;

@Document(collection = "users")
@Data
@Builder
public class UserCredentialsEntity {

    @Id
    private String id;

    @Indexed(unique = true)
    private String email;

    private String password;

    @Field(targetType = FieldType.STRING)
    private UserType userType;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;
}
