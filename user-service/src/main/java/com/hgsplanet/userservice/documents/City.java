package com.hgsplanet.userservice.documents;

import com.hgsplanet.userservice.enums.RelationWithUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Document(collection = "cities")
public class City {
    @Id
    private String cityId;
    private String name;
    private String imgPath;
}
