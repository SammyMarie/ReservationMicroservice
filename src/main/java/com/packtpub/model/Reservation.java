package com.packtpub.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Reservations")
public class Reservation {

    @Id
    private String reservationId;

    @NotNull
    @ApiModelProperty(required = true)
    private String madeBy;

    @NotNull
    @ApiModelProperty(required = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a")
    private LocalDateTime dateIn;

    @NotNull
    @ApiModelProperty(required = true)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a")
    private LocalDateTime dateOut;

    @NotNull
    @ApiModelProperty(required = true)
    private String customerId;
}