package com.hgsplanet.userservice.model;

import com.hgsplanet.userservice.enums.RelationWithUser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssignCityRequest {
    private RelationWithUser relationWithUser;
    private String cityId;
}
