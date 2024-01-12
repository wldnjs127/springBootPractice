package com.shinhan.sbproject.VO4;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Embeddable //복합키로 사용가능
public class MultiKeyB implements Serializable{
private Integer id1;
private Integer id2;


}
