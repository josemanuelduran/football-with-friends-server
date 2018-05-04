/*
 * Copyright (C) 2018 Jose Manuel Duran
 * 
 * License GPL-3.0 or later (http://www.gnu.org/licenses/gpl-3.0)
 *
 */
package com.jmduran.footballwithfriends.server.models;

import java.io.Serializable;
import java.time.Month;
import java.util.Date;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "payments")
public class Payment implements Serializable {
     
    private static final long serialVersionUID = -2749947434805807976L;
    
    @Id
    String id;
    @NotNull
    String playerId;
    Month month;
    Integer year;
    Date paymentDate;
    String matchId;
    
}
