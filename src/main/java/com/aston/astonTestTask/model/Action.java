package com.aston.astonTestTask.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "actions")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
@Getter
@Setter
public class Action {

    @Id
    @Column(name = "id")
    private int id;

    @NonNull
    @Column(name = "dateTime")
    private LocalDateTime dateTime;

    @NonNull
    @Column(name = "billId")
    private int billId;

    @NonNull
    @Column(name = "customerId")
    private int customerId;

    @NonNull
    @Column(name = "actionType")
    private ActionType actionType;

    @NonNull
    @Column(name = "amount")
    private int amount;

}