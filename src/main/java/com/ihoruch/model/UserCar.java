package com.ihoruch.model;

import com.ihoruch.model.key.UserCarKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(UserCarKey.class)
@Table(name = "user_car", schema = "hibernate_graph")
public class UserCar {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private Car car;

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }
        UserCar that = (UserCar) o;
        return Objects.equals(user.getId(), that.user.getId())
                && Objects.equals(car.getId(), that.car.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(user.getId(), car.getId());
    }

}
