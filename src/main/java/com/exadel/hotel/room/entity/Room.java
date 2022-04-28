package com.exadel.hotel.room.entity;

import com.exadel.hotel.Floor.entity.Floor;
import com.exadel.hotel.type.entity.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Room {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private Long id;

    private int number;

    @ManyToOne
    private Type type;

    @ManyToOne
    private Floor floor;

}
