package com.shinhan.sbproject.VO4;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name="tbl_child2")
public class MultiKeyBDTO {
@EmbeddedId
MultiKeyB id;

private String userName;
private String address;
}

