/*
 * Copyright (C) 2015 Arthur Gregorio, AG.Software
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package br.com.webbudget.domain.model.security;

import br.com.webbudget.domain.model.entity.PersistentEntity;
import java.time.LocalDateTime;
import static javax.persistence.CascadeType.ALL;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Arthur Gregorio
 *
 * @version 2.0.0
 * @since 2.0.0, 26/05/2015
 */
@Entity
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Table(name = "users", schema = "security")
public class User extends PersistentEntity {

    @Getter
    @Setter
    @NotEmpty(message = "{user.name}")
    @Column(name = "name", length = 90, nullable = false)
    private String name;
    @Setter
    @Getter
    @NotEmpty(message = "{user.username}")
    @Column(name = "username", length = 45, nullable = false)
    private String username;
    @Getter
    @Setter
    @Email(message = "{user.email}")
    @NotEmpty(message = "{user.email}")
    @Column(name = "email", length = 90, nullable = false)
    private String email;
    @Setter
    @Getter
    @NotEmpty(message = "{user.password}")
    @Column(name = "password", length = 256, nullable = false)
    private String password;
    @Getter
    @Setter
    @Column(name = "expired", nullable = false)
    private boolean expired;
    @Getter
    @Setter
    @Column(name = "expiration_date", nullable = false)
    private LocalDateTime expirationDate;
    
    @Getter
    @Setter
    @ManyToOne
    @NotNull(message = "{user.group}")
    @JoinColumn(name = "id_group", nullable = false)
    private Group group;
    @Getter
    @Setter
    @OneToOne(cascade = ALL)
    @JoinColumn(name = "id_profile", nullable = false)
    private Profile profile;

    /**
     * 
     */
    public User() {
        this.profile = new Profile();
        this.expirationDate = LocalDateTime.now().plusMonths(6);
    }
    
    /**
     * @return o nome do grupo deste usuario
     */
    public String getGroupName() {
        return this.group.getName();
    }
}
