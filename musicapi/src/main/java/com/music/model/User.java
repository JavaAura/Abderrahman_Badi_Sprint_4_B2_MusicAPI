package com.music.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Id;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a Music entity in the application.
 */
@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
@Document(collection = "users")
@Where(clause = "removed_at IS NULL")
@SQLDelete(sql = "UPDATE users SET removed_at = CURRENT_TIMESTAMP WHERE id=?")
public class User {

    @Id
    @Indexed
    private String id; 

    @Indexed
    private String username;

    private String password;

    @Builder.Default
    private boolean isEnabled = true;

    @Indexed(unique = true)
    private String email;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    private LocalDateTime removedAt;

    @Builder.Default
    @DBRef
    private List<Role> roles = new ArrayList<>();
}
