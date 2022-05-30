package com.ihoruch.model.key;

import com.ihoruch.model.Car;
import com.ihoruch.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class UserCarKey implements Serializable {

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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserCarKey that = (UserCarKey) o;

        if (!Objects.equals(user.getId(), that.user.getId())) {
            return false;
        }

        return Objects.equals(car.getId(), that.car.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(user.getId(), car.getId());
    }

}
